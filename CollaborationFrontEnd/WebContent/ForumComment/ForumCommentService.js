'use strict'

app.service('ForumCommentService',['$http','$q','$rootScope',function($http,$q,$rootScope){
	console.log("ForumCommentService")
	var BASE_URL =' http://localhost:8080/RestController'
		return{
		
		creareForumComment:function(forumComment){
			console.log("calling create comment")
			console.log(forumComment);
			return $http.post(BASE_URL + 'forumComment',forumComment)
			.then(function(response) {
				return response.data;
			}, function(errResponse) {
				console.error('Error while creating ForumComments');
				return $q.reject(errResponse);
			});
		},
		
		viewComment : function(forumCommentId){
			console.log(forumCommentId)
			return $http.get(BASE_URL + '/forumComment/'+ forumCommentId).then(function(response){
				return response.data;
			}, function(errResponse){
				console.error('Error while getting ForumComment');
				return $q.reject(errResponse);
			});
			
		},
		
		
		
	};
}]);