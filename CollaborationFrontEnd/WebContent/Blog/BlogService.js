'use strict';

app.service('BlogService', [ '$http', '$q', '$rootScope',
	function($http, $q, $rootScope) {
	
	console.log('blog Service......');
	var BASE_URL = 'http://localhost:8080/RestController'
		return {

			createBlog : function(blog) {
				console.log("calling create blog")
				return $http.post(BASE_URL + '/blog', blog) // 1
				.then(function(response) {
					return response.data;
				}, function(errResponse) {
					console.error('Error while creating Blog');
					return $q.reject(errResponse);
				});
			}
		}

	
}])