jQuery.fn.extend({
	getUrlParam : function(name) {
		 var results = new RegExp('[\\?&]' + name + '=([^&#]*)').exec(window.location.href);
		 if(results != null && $.isArray(results)) {
			 return results[1];
		 } else {
			 return null;
		 }
	}
}); 