var app = app || {};
(function($) {
	app.sf = {};
	
	app.sf.listContacts = function(){
		var params = {mehotd:"Get"};
		return app.getJsonData(contextPath+"/listSFContacts.json",params);
	}
	app.sf.getContact = function(id){
		var params = {mehotd:"Get"};
		params.id = id;
		return app.getJsonData(contextPath+"/getSFContact.json",params);
	}
	app.sf.saveContact = function(id,name){
		var params = {mehotd:"Post"};
		params.id = id;
		params.name = name;
		return app.getJsonData(contextPath+"/saveSFContact.do",params);
	}
	app.sf.deleteContact = function(id){
		var params = {mehotd:"Post"};
		params.id = id;
		return app.getJsonData(contextPath+"/deleteSFContact.do",params);
	}
	
	app.sf.listCountryCustoms = function(){
		var params = {mehotd:"Get"};
		return app.getJsonData(contextPath+"/listSFCountryCustoms.json",params);
	}
	app.sf.getCountryCustom = function(id){
		var params = {mehotd:"Get"};
		params.id = id;
		return app.getJsonData(contextPath+"/getSFCountryCustom.json",params);
	}
	app.sf.saveCountryCustom = function(id,name){
		var params = {mehotd:"Post"};
		params.id = id;
		params.name = name;
		return app.getJsonData(contextPath+"/saveSFCountryCustom.do",params);
	}
	app.sf.deleteCountryCustom = function(id){
		var params = {mehotd:"Post"};
		params.id = id;
		return app.getJsonData(contextPath+"/deleteSFCountryCustom.do",params);
	}
})(jQuery);
