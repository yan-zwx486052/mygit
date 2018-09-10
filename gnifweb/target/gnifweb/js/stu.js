/**
 * 当创建用户时，打开一个对话框并清空表单数据。
 */
function newStu(){
	$('#dlg').dialog('open').dialog('setTitle','新增');
	$('#fm').form('clear');
	url = 'save_stu.json';
}

/**
 * 当编辑（修改）用户时，打开一个对话框并从 datagrid 选择的行中加载表单数据
 */
var row = $('#dg').datagrid('getSelected');
if (row){
	$('#dlg').dialog('open').dialog('setTitle','修改');
	$('#fm').form('load',row);
	url = 'update_stu.json?id='+row.id;
}

/**
 * 保存用户数据
 */
function saveStu(){
	$('#fm').form('submit',{
		url: url,
		onSubmit: function(){
			return $(this).form('validate');
		},
		success: function(result){
			var result = eval('('+result+')');
			if (result.errorMsg){
				$.messager.show({
					title: 'Error',
					msg: result.errorMsg
				});
			} else {
				$('#dlg').dialog('close');		// 关闭对话框
				$('#dg').datagrid('reload');	// 刷新页面信息
			}
		}
	});
}

/**
 * 移除一个用户
 */
function destroyStu(){
	var row = $('#dg').datagrid('getSelected');
	if (row){
		$.messager.confirm('Confirm','你确定要删除这条信息吗?',function(r){
			if (r){
				$.post('destroy_stu.json',{id:row.id},function(result){
					if (result.success){
						$('#dg').datagrid('reload');	// 刷新页面信息
					} else {
						$.messager.show({	// 显示错误提示
							title: 'Error',
							msg: result.errorMsg
						});
					}
				},'json');
			}
		});
	}
}
/**
 * 避免重复提交
 */
function submitform() {
	  $.messager.progress();
	  $('#form').form('submit', {
	    url: 'save.html',
	    onSubmit: function() {
	      var isValid = $(this).form('validate');
	      if (!isValid) {
	        $.messager.progress('close');
	      }
	      return isValid;
	    },
	    success: function(data) {
	    	$.messager.progress('close');
	    }
	  });
	}

