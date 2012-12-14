;(function() {

	/**
	 * View: Countries
	 *
	 */
    (function ($) {
        brite.registerView("Countries",  {loadTmpl:true,emptyParent:true,parent:".MainScreen-main"}, {
            create:function (data, config) {
                var $html = app.render("#tmpl-Countries",data);
                var $e = $($html);
                return $e;
            },
            postDisplay:function (data, config) {
                var view = this;
                var $e = view.$el;
                
                refresh.call(view);
            },
            events:{
            	"click;.btnAdd":function(e){
            		brite.display("CountryInfo",null,{id:null});
            	},
            	"click;.btnEdit":function(e){
            		var view = this;
            		var $el = view.$el;
            		var $btn = $(e.currentTarget);
            		var id = $btn.bEntity().id;
            		brite.display("CountryInfo",null,{id:id});
            	},
            	"click;.btnDelete":function(e){
            		var view = this;
            		var $el = view.$el;
            		var $btn = $(e.currentTarget);
            		var id = $btn.bEntity().id;
            		app.sf.deleteCountryCustom(id);
            		refresh.call(view);
            	}
            },

            docEvents:{
            	"DO_REFRESH_CONTACT":function(){
            		refresh.call(this);
            	}
            },

            daoEvents:{
            }
        });
        
        function refresh(){
        	var view = this;
        	var $e = view.$el;
        	var $tbody = $e.find(".lists").empty();
        	app.sf.listCountryCustoms().done(function(data){
        		var list = data.result;
        		for(var i = 0; i < list.length; i++){
        			var obj = list[i];
        			obj.index = i+1;
        			obj.type="Country";
        			console.log(list);
        			var $item = app.render("#tmpl-Countries-rowItem",obj);
        			$tbody.append($item);
        		}
			});

        }
    })(jQuery);


})();
