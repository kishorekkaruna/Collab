var app = angular.module('Kishore', [ 'ngRoute', 'ngCookies' ]);

app.config(function($routeProvider) {

	$routeProvider
	
	.when('/', {
		templateUrl : 'Home/Home.html'
	})
	
	.when('/register', {
		templateUrl : 'User/login.html',
		controller : 'UserController',
		controllerAs : 'uc'
	})
	
	.when('/login', {
		templateUrl : 'User/login.html',
		controller : 'UserController',
		controllerAs : 'uc'
	})
	
	.when('/job', {
		templateUrl : 'Job/Job.html',
		controller : 'JobController',
		controllerAs : 'jc'
	})
	
	.when('/viewJob', {
		templateUrl : 'Job/SingleView.html',
	})
	
	.when('/blog', {
		templateUrl : 'Blog/Blog.html',
		controller : 'BlogController',
		controllerAs : 'bc'
	})
	
	.when('/forum', {
		templateUrl : 'Forum/Forum.html',
		controller : 'ForumController',
		controllerAs : 'fc'
	})

	.when('/homeMain', {
		templateUrl : 'Home/Home.html'
	})
	
	.when('/admin', {
		templateUrl : 'Admin/ActApp.html'
	})
	
	.when('/adminBlogdetail', {
		templateUrl : 'Admin/BlogDetails.html',
		controller : 'BlogController',
		controllerAs : 'bcc'
	})
	
	.when('/adminForumdetail', {
		templateUrl : 'Admin/ForumDetails.html',
		controller : 'ForumController',
		controllerAs : 'fcc'
	})
	
	.when('/viewBlog',{
		templateUrl : 'Blog/SingleView.html'
	})
	
	.when('/viewForum',{
		templateUrl : 'Forum/Singleview.html'
	})
	

	.otherwise({
		redirectTo : '/register'
	});
});