;(function() {

	/**
	 * View: MainScreen
	 *
	 */
    (function ($) {
        brite.registerView("MainScreen",  {loadTmpl:true}, {
            create:function (data, config) {
                var $html = app.render("#tmpl-MainScreen");
                var $e = $($html);
                return $e;
            },
            postDisplay:function (data, config) {
                var view = this;
                var $e = view.$el;
                brite.display("Top");
                brite.display("Contacts");
            },
            events:{
            },

            docEvents:{
            },

            daoEvents:{
            }
            
        });
    })(jQuery);


})();
