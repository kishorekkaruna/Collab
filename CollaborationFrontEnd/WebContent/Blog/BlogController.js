'use strict';

app.controller('BlogController',['$scope','BlogService','$cookies','$rootScope','$route','$http','$location'
	,function($scope,BlogService,$cookies,$rootScope,$route,$http,$location){
	
	console.log('Blog Controller...');
	var self = this;
	self.blog={blogId:'',blog_name:'',blog_content:'',user_id:'',email_Id:'',user_name:'',createdate:'',status:'',likes:''};
	self.blogs=[];
	
	
	

				self.fetchAllBlogs = function() {
				BlogService.fetchAllBlogs().then(function(d) {
					self.blogs = d;
					console.log(self.blogs)
				}, function(errResponse) {
					console.error('Error while fetching Blogs');
				});
			},
			

				self.AcceptedBlogs = function () {
				console.log("AcceptedBlogs...")
				BlogService.AcceptedBlogs().then(function(d) {
					// alert("Thank you for creating message")
					console.log(d)
					self.blogsAccept = d;
				}, function(errResponse) {
					console.error('Error while creating AcceptedBlogs.');
				});
			};
			
			self.notAcceptedBlogs = function() {
			console.log("notAcceptedBlogs...")
			BlogService.notAcceptedBlogs().then(function(d) {
				console.log(d)
								self.blogsNotAccepted = d;
								console.log(self.blogsNotAccepted)
							},
							function(errResponse) {
								console.error('Error while creating notAcceptedBlogs.');
							});
				};
				
			self.get = function(blog){
					$scope.bc=blog;
					console.log($scope.bc);
					$rootScope.blog=$scope.bc;
					$location.path("/viewBlog");
				};
				
			self.adminGet = function (blogsget){
					$scope.bvv=blogsget;
					console.log($scope.bvv);
					$rootScope.viewBlogs=$scope.bvv;
					$location.path("/adminBlogdetail");
				};
	
			self.accept = 	function (viewBlogs) {
						console.log('accept the Blog details')
						BlogService.accept(viewBlogs);
						console.log(viewBlogs)
						$location.path("/admin")
					};
			
				self.createBlog = function(blog) {
				console.log('calling create blog...');
				BlogService.createBlog(blog).then(function(d) {
					prompt('blog has been created');
					$location.path("/login")
				}, function(errResponse) {
					console.error('Error while creating Job.');
				})
			};
			self.submit = function() {
				console.log('Calling Submit...')
				self.createBlog(self.blog);
			};
	self.reset = function(){
		console.log('calling Reset');
		self.blog = { blogId:null,blog_name:'',blog_content:'',user_id:'',email_Id:'',user_name:'',createdate:'',status:'',likes:''};
		self.blogs=[];
		
	};

	
}])