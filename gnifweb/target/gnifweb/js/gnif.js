var gnif;
var debug=0;
var session_valid = true;
var logger;
try {
  if (console) {
	logger = console;
  }
}
catch (e) {
  logger = {
	log: function(a,b,c,d,e,f,g) {
	  alert(a+b+c+d+e+f+g);
	}
  }
}

$(function() {
  gnif = (function(){
    // init tab and menu
    var tab = $('#gnif-tab');
    var menu = $('#gnif-menu');

    if (menu) {
      $('#gnif-menu a').click(function(){
        if (!tab) {
          return;
        }
        gnif.opentab($(this).text(), $(this).attr('turl'));
      });
    }

    // init event handler
    var eventContainer = {};
    var types = {};

    function handleTimerMessage() {
      var type = types.pop();
      while (type) {
        handleMessage(type);
        type = types.pop();
      }
    }

    function handleMessage(type) {
      if (eventContainer[type] instanceof Array){
        var handlers = eventContainer[type];
        for (var i=handlers.length-1; i>=0; i--) {
          if ($(handlers[i].oid).length == 0) {
            handlers.splice(i, 1);
          }
          else {
            handlers[i].handler();
          }
        }
      }
    }

    // init datagrid handler
    /*
      数据对象处理器，适用于一般的数据对象处理
      opts 需要设置以下内容：

      dg: 对应的数据表
      fm: 对应的保存表单
      en: 所操作的对象名称
      dlg: 对话框
      lu: 加载数据的 url
      su: 保存数据的 url
      du: 删除数据的 url
    */
    function DatagridHandler(opts) {

      var handler = this
      
      // 删除
      handler.remove = function () {
        var row = opts.dg.datagrid('getSelected');
        $.messager.confirm('确认删除', '是否确认删除当前' + opts.en + '？', function(r) {
          if (r) {
            if (row) {
              $.post(opts.du, {id: row.id}, function(data) {
                opts.dg.datagrid('reload');
                $.messager.alert('提示', data.message);
              });
            }
            else {
              $.messager.alert('提示', '请选择要删除的' + opts.en + '！')
            }
          }
        });
      }

      // 创建新的对象
      handler.create = function() {
        opts.dlg.dialog({
          title: '添加' + opts.en,
          closed: false,
          modal: false,
          buttons: [{text:'保存', handler: handler.save}],
          onClose: function() {
            opts.fm.form('clear');
          }
        });
      }

      // 编辑对象
      handler.edit = function() {
        var row = opts.dg.datagrid('getSelected');
        if (!row) {
          $.messager.alert('提示', '请选择要编辑的' + opts.en);
          return;
        }
        opts.fm.form('load', opts.lu + '?id=' + row.id)
        opts.dlg.dialog({
          title: '编辑' + opts.en,
          closed: false,
          buttons: [{text:'保存', handler: handler.save}],
          onClose: function() {
            opts.fm.form('clear');
          }
        });
      }
      
      // 保存
      handler.save = function () {
        $.messager.progress();
        opts.fm.form('submit', {
          url: opts.su,
          onSubmit: function() {
            var isValid = $(this).form('validate');
            if (!isValid) {
              $.messager.progress('close');
            }
            return isValid;
          },
          success: function(data) {
            $.messager.progress('close');
            var data = eval('(' + data + ')');
            if (data.success) {
              opts.dlg.dialog('close');
              opts.dg.datagrid('reload');
              $.messager.show({
                title:'提示',
                msg: data.message,
                showType:'show',
              });
            }
            else {
              $.messager.alert('错误', data.message);
            }
          }
        });
      }
    }

    $.extend($.fn.datagrid.methods, {
      editCell: function(jq,param){
        return jq.each(function(){
          var opts = $(this).datagrid('options');
          var fields = $(this).datagrid('getColumnFields',true).concat($(this).datagrid('getColumnFields'));
          for(var i=0; i<fields.length; i++){
            var col = $(this).datagrid('getColumnOption', fields[i]);
            col.editor1 = col.editor;
            if (fields[i] != param.field){
              col.editor = null;
            }
          }
          $(this).datagrid('beginEdit', param.index);
          for(var i=0; i<fields.length; i++){
            var col = $(this).datagrid('getColumnOption', fields[i]);
            col.editor = col.editor1;
          }
        });
      }
    });

    //处理键盘事件 禁止后退键（Backspace）密码或单行、多行文本框除外
    function banBackSpace(e) {
      var ev = e || window.event; //获取event对象
      var obj = ev.target || ev.srcElement; //获取事件源
      var t = obj.type || obj.getAttribute('type'); //获取事件源类型
      //获取作为判断条件的事件类型
      var vReadOnly = obj.readOnly;
      var vDisabled = obj.disabled;
      //处理undefined值情况
      vReadOnly = (vReadOnly == undefined) ? false : vReadOnly;
      vDisabled = (vDisabled == undefined) ? true : vDisabled;
      //当敲Backspace键时，事件源类型为密码或单行、多行文本的，
      //并且readOnly属性为true或disabled属性为true的，则退格键失效
      var flag1 = ev.keyCode == 8 && (t == "password" || t == "text" || t == "textarea") && (vReadOnly == true || vDisabled == true);
      //当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效
      var flag2 = ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea";
      //判断
      if (flag2 || flag1) return false;
    }

    var gnifObject = {
	  vars: {},
      opentab: function(title, url) {
        if (!tab) {
          return;
        }
        if (tab.tabs('exists', title)) {
          tab.tabs('select', title);
        }
        else {
          tab.tabs('add', {
            title: title,
            href: url,
            closable: true,
          });
        }
      },
      on: function(type, objectId, handler) {
        if(typeof eventContainer[type]=='undefined'){
          eventContainer[type] = new Array();
        }
        eventContainer[type].push({oid: objectId, handler: handler});
      },
      trigger: function(type, timer) {
        if (timer) {
          function whenTimeout() {
            handleMessage(type);
          }
          setTimeout(whenTimeout, timer);
        }
        else {
          handleMessage(type);
        }
      },
      post: function(url, params) {
        var form = $('<form method="post" action="'+url+'"></form>');
        form.appendTo($(document.body));
        if (params) {
          $.each(params, function(key, value) {
            $('<input type="hidden" name="'+key+'" value="'+value+'" />').appendTo(form);
          });
        }
        form.submit();
      },
      create_dgh: function(opts) {
        return new DatagridHandler(opts);
      },
      banBackspace: function() {
        //禁止后退键 作用于Firefox、Opera
        document.onkeypress = banBackSpace;
        //禁止后退键  作用于IE、Chrome
        document.onkeydown = banBackSpace;
      }
    };
    return gnifObject;
  })();
  $(document).ajaxComplete(function(event, request, settings ) {
	if (session_valid && request.status != 200) {
	  session_valid = false;
	  $.get('validate.html', function(data) {
		if (debug > 0) {
		  logger.log(data);
		}
		if (data == 'SUCCESS') {
		  session_valid = true;
		}
	  });
	}
	else if (!session_valid && request.status != 200) {
	  $.messager.confirm('页面失效', '当前页面已经失效，是否刷新页面？', function(r) {
        if (r) {
		  window.location.reload();
		  session_valid = true;
        }
	  });
	}
  });
});
