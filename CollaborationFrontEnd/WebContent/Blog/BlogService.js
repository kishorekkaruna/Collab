'use strict';

app.service('BlogService', [ '$http', '$q', '$rootScope',
	function($http, $q, $rootScope) {
	
	console.log('blog Service......');
	var BASE_URL = 'http://localhost:8080/RestController'
		return {

		fetchAllBlogs : function(){
			console.log("calling fetchAllblogs ")
			return $http.get(BASE_URL + '/blogs').then(function(response) {
				return response.data;
				}, null);
		},
		
		 AcceptedBlogs:function() {
			console.log("calling AcceptedBlogs ")

			return $http.get(BASE_URL + '/acceptedblog').then(
					function(response) {
						console.log('response');
						return response.data;
						console.log(response)
					}, null);
			},
		
		
		notAcceptedBlogs : function(){
		console.log("calling notAcceptedBlogs ")
		return $http.get(BASE_URL + '/notAcceptedblog').then(
				function(response) {
					console.log(response)
					return response.data;

				}, null);
		},
		
		accept : function(Blog) {
			console.log("calling accept Blogs ")
			return $http.put(BASE_URL + '/acceptBlog', Blog) // 2
			.then(function(response) {
				return response.data;
			}, function(errResponse) {
				console.error('Error while accepting Blog');
				return $q.reject(errResponse);
			});
		},
		
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