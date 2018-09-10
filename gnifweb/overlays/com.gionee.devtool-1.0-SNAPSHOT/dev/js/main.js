function devtool(tabId, menuId) {
  var tab = $('#'+tabId);
  var ret = {
	opentab: function(title, url) {
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
	}
  }
  $('#'+menuId + ' a').click(function(){
	ret.opentab($(this).text(), $(this).attr('turl'));
  });
  return ret;
}

var dev;
$(function() {
  dev = devtool('tt', 'menu');
})

function eventmessage() {
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
  
  return {
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
	}
  };
}

var emsg = eventmessage();
