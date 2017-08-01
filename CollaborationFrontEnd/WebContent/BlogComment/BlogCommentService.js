'user strict'

app.service('BlogCommentService', ['$http','$q','$rootScope',
	function($http, $q, $rootScope) {

			console.log("BlogCommentService...") 

var BASE_URL =' http://localhost:8080/RestController'
	return{
				createBlogComment : function(blogcomment) {
					console.log("calling create BlogComment")
					console.log(blogcomment)
					return $http.post(BASE_URL + '/blogcomment', blogcomment) // 1
					.then(function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while creating BlogComments');
						return $q.reject(errResponse);
					});
				},
				
				viewComment : function(blogId){
					console.log(blogId)
					return $http.get(BASE_URL + '/blogcomment/'+ blogId).then(function(response){
						return response.data;
					}, function(errResponse){
						console.error('Error while getting Blogcomment');
						return $q.reject(errResponse);
					});
					
				},
				
			}
			}]);