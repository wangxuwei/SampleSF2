;(function() {

	/**
	 * View: Token
	 *
	 */
	brite.registerView("Token", {
		loadTmpl : true,
		parent : "body"
	}, {
		
		create : function(data, config) {
			var view = this;
			var $html = app.render("#tmpl-Token");
				//show a screen to prevent use click other places
			view.$screen = $("<div class='notTransparentScreen'></div>").appendTo("body");
			return $html;
		},
		
		events : {
	 		"btap; .btnClose": function(){
	 			var view = this;
	 			view.close();
	 		}, 
	 		"btap; .btnSave": function(){
	 			saveToken.call(this);
	 		}
		},

		close : function(update) {
			var view = this;
			var $e = view.$el;

			$e.bRemove();
			view.$screen.remove();
		},
	});

	// --------- View Private Methods --------- //
	function saveToken() {
		var view = this;
		var $e = view.$el;
		var code = $e.find("input[name='code']").val();

		app.oauth.setToken(code).done(function() {
			view.close();
		}); 


	}

	// --------- /View Private Methods --------- //

})();
