'use strict';

app.controller('BlogController',['$scope','BlogService','BlogCommentService','$cookies','$rootScope','$route','$http','$location'
	,function($scope,BlogService,BlogCommentService,$cookies,$rootScope,$route,$http,$location){
	
	console.log('Blog Controller...');
	var self = this;
	self.blog={blogId:'',blog_name:'',blog_content:'',user_id:'',email_Id:'',user_name:'',createdate:'',status:'',likes:''};
	self.blogs=[];
	self.blogComment={blogId:'',blog_name:'',message:'',commentdate:'',user_id:'',user_name:'',email_Id:''};
	self.blogComments=[];
	
	
	

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
					BlogCommentService.viewComment(blog.blogId).then (function(d){
						console.log(d)
						self.viewMessage = d;
						$scope.cmt=self.viewMessage;
						console.log(self.viewMessage)
						$rootScope.comment= $scope.cmt
					},function(errResponse){
						console.error('Error while viewing comment');
					});
					
					
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
			
			self.createBlogComment = function(blogComment){
				console.log('Calling Comment')
				$scope.recentblog=$rootScope.blog;
				BlogCommentService.createBlogComment(blogComment).then(function(d){
					self.message=d;
					alert("thank you for your valuable comment");
					self.get($scope.recentblog);
					self.reset();
				}, function(errResponse){
					console.log('error while creating Comment');
				})
			};
			
			self.submit = function() {
				console.log('Calling Submit...')
				self.createBlog(self.blog);
			};
	self.reset = function(){
		console.log('calling Reset');
		self.blog = { blogId:null,blog_name:'',blog_content:'',user_id:'',email_Id:'',user_name:'',createdate:'',status:'',likes:''};
		self.blogComment={blogId:null,blog_name:'',message:'',commentdate:'',user_id:'',user_name:'',email_Id:''};
		self.blogs=[];
		self.blogComments=[];
	};
	
	
}])