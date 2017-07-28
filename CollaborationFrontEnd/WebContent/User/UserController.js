'use strict';

app.controller('UserController',['$scope','UserService','$cookies','$rootScope','$route','$http','$location','$cookieStore',function($scope,UserService,$cookies,$rootScope,$route,$http,$location,$cookieStore){
	console.log("UserController...")
	var self = this;
	self.user={userId:'',user_name:'',first_name:'',last_name:'', password:'', email_id:'', dob:'', role:'', status:'', is_online:''};
	self.currentUser={userId:'',user_name:'',first_name:'',last_name:'', password:'', email_id:'', dob:'', role:'', status:'', is_online:''};
	self.users=[];
	
					self.createUser = function(user) {
						console.log('create user call');
						UserService.createUser(user).then(function(d) {
							alert("Thank you for registration")
							$location.path("/login")
						}, function(errResponse) {
							console.error('Error while creating User.');
						});
					};
					self.userDetails = function(user){
						console.log('acces user details')
						UserService.userDetails().then(function(d){
							console.log(d)
							self.userdetails=d;
							console.log(self.userdetails)
						})
					};
					self.login = function() {
						UserService.login(self.user).then(function(response) {
							console.log(response.status)
							$scope.user = response.data;
						
							$rootScope.currentUser = response.data;
							$cookieStore.put("currentUser", response.data);
							alert('Logged in succesfully...');
							if($scope.user.role == 'STUDENT'){
								 $location.path('/home');
							  }else if($scope.user.role == 'ADMIN'){
								  $location.path('/admin');
							}else if($scope.user.role == 'USER'){
								  $location.path('/homeMain');}
							else{
								 $location.path('/blog');
								}
							
						}, function(response) {
							console.log(response.status)
							$scope.message = response.data.message
							$location.path('/login')
						})  
					};
					
					self.logout = function() {
						console.log("logout")
						$rootScope.currentUser = {};
						$cookieStore.remove('currentUser');
						$cookies.remove('currentUser');
						UserService.logout()
						$location.path('/login')	
						};
					self.submit = function() {
						console.log('Calling Submit...')
						self.createUser(self.user);

						self.reset();
					};
					self.reset = function() {
						console.log('calling Reset');
						self.user = {
							userId : null,
							user_name : '',
							first_name : '',
							last_name : '',
							password : '',
							email_id : '',
							dob : '',
							role : '',
							status : '',
							is_online : ''
						};
						self.users = [];
					};

}
	
	
	])