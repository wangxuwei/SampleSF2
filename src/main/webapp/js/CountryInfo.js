;(function() {

	/**
	 * View: CountryInfo
	 *
	 */
	brite.registerView("CountryInfo", {
		loadTmpl : true,
		parent : "body"
	}, {
		
		create : function(data, config) {
			var view = this;
			var dfd = $.Deferred();
			var createDfd = $.Deferred();
			data = data || {};
			if (data.id) {
				app.sf.getCountryCustom(data.id).done(function(data) {
					dfd.resolve(data.result);
				});
			} else {
				dfd.resolve({});
			}
			dfd.done(function(country) {
				console.log(country);
				view.countryId = country.Id || null;
				var $html = app.render("#tmpl-CountryInfo",country);
				//show a screen to prevent use click other places
				view.$screen = $("<div class='notTransparentScreen'></div>").appendTo("body");
				createDfd.resolve($html);
			});

			return createDfd.promise();
		},
		
		events : {
	 		"btap; .btnClose": function(){
	 			var view = this;
	 			view.close();
	 		}, 
	 		"btap; .btnCreate": function(){
	 			saveCountry.call(this);
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
	function saveCountry() {
		var view = this;
		var $e = view.$el;

		var name = $e.find("input[name='name']").val();

		// if country id exist do update,else do create
		app.sf.saveCountryCustom(view.countryId, name).done(function() {
			$(document).trigger("DO_REFRESH_CONTACT");
			view.close();
		}); 


	}

	// --------- /View Private Methods --------- //

})();
