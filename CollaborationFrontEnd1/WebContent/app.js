var app = angular.module('Kishore', [ 'ngRoute', 'ngCookies' ]);

app.config(function($routeProvider) {
	
	$routeProvider
	
         
         
	.when('/', {
		templateUrl : 'Home/Home.html'
})
.when('')
.otherwise({
	redirectTo : 'Home/Home.html'
});
});