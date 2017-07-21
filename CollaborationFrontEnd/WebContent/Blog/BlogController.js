'use strict';

app.controller('BlogController',['$scope','BlogService','$cookies','$rootScope','$route','$http','$location'
	,function($scope,BlogService,$cookies,$rootScope,$route,$http,$location){
	
	console.log('Blog Controller...');
	var self = this;
	self.blog={blogId:'',blog_name:'',blog_content:'',user_id:'',email_Id:'',user_name:'',createdate:'',status:'',likes:''};
	self.blogs=[];
	
	self.createBlog = function(blog){
	console.log('calling create blog...');
	BlogService.createBlog(blog).then(function(d){
		prompt('blog has been created');
		$location.path("/login")
	},
	function(errResponse) {
		console.error('Error while creating Job.');
	})
	};
	self.submit = function(){
		console.log('Calling Submit...')
		self.createBlog(self.blog);
	};
	self.reset = function(){
		console.log('calling Reset');
		self.blog = { blogId:null,blog_name:'',blog_content:'',user_id:'',email_Id:'',user_name:'',createdate:'',status:'',likes:''};
		self.blogs=[];
		
	};

	
}])