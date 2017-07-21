'use strict'

app.service('ForumService',['$http', '$q', '$rootScope',
		function($http, $q, $rootScope) {
	console.log("ForumService.....")
	
	var BASE_URL = 'http://localhost:8080/RestController'
			return {

				createForum : function(forum) {
					console.log("calling create forum")
					return $http.post(BASE_URL + '/forum', forum) // 1
					.then(function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while creating forum');
						return $q.reject(errResponse);
					});
				}
			}

}])