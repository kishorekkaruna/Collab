'use strict'

app.service('ForumService',['$http', '$q', '$rootScope',
		function($http, $q, $rootScope) {
	console.log("ForumService.....")
	
	var BASE_URL = 'http://localhost:8080/RestController'
			return {
		
				notAcceptedForum : function() {
					console.log("calling notAcceptedForum ")
					return $http.get(BASE_URL + '/notAcceptedForum').then(
							function(response) {
								console.log(response)
								return response.data;

							}, null);
				},
				AcceptedForums : function(){
					console.log("calling AcceptedForum")
					return $http.get(BASE_URL+'/AcceptedForum').then(
							function(response){
								console.log(response)
								return response.data;
							}, null);				
				},
				
				accept : function(Forum) {
					console.log("calling accept Forum ")
					return $http.put(BASE_URL + '/forumAccept', Forum) // 2
					.then(function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while accepting Forum');
						return $q.reject(errResponse);
					});
				},

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