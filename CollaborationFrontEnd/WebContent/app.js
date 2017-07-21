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
		templateUrl : 'home.html'
	})

	.otherwise({
		redirectTo : '/register'
	});
});