'use strict';

app.controller('UserController',['$scope','UserService','FriendService','$cookies','$rootScope','$route','$http','$location','$cookieStore',
						function($scope,UserService,FriendService,$cookies,$rootScope,$route,$http,$location,$cookieStore){
	console.log("UserController...")
	var i = 0;
	var j = 0;
	var self = this;
	self.user={userId:'',user_name:'',first_name:'',last_name:'', password:'', email_id:'', dob:'', role:'', status:'', is_online:''};
	self.currentUser={userId:'',user_name:'',first_name:'',last_name:'', password:'', email_id:'', dob:'', role:'', status:'', is_online:''};
	self.users=[];
	self.friend={friendId:'',friendName:'',userId:'',userName:'',status:''}
	self.friends=[];
	var arr=[];
	var friendarr=[];
	
					self.createUser = function(user) {
						console.log('create user call');
						UserService.createUser(user).then(function(d) {
							alert("Thank you for registration")
							$location.path("/login")
						}, function(errResponse) {
							console.error('Error while creating User.');
						});
					};

					self.login = function() {
						UserService.login(self.user).then(function(d) {
							self.user=d;
							console.log(self.user);
						
							$rootScope.currentUser = self.user;
							$cookieStore.put("currentUser", self.user);
							alert('Logged in succesfully...');
							if(self.user.role == 'STUDENT'){
								 $location.path('/homeMain');
							  }else if(self.user.role == 'ADMIN'){
								  $location.path('/admin');
							}else if(self.user.role == 'USER'){
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
					
					self.fetchAllUsers = function() {
						self.asd = null;
						self.us = '';
						console.log("fetchAllUsers...")
						$scope.loginUser =$rootScope.currentUser;
						console.log("fetchUserList...")
						UserService .fetchAllUsers().then(function(d) {
							self.users = d;
							for(i=0; i<self.users.length; i++)
								{
								if(self.users[i].role!='ADMIN'){
									arr.push(self.users[i])													
								}
								}
							self.us = arr;	
							console.log(self.us)	
							
							console.log("fetchAllRequestedFriend...")
							FriendService.fetchAllRequestedfriends($scope.loginUser.userId).then(function(d) {
								self.friends = d;
								console.log(self.friends)					
					
								
									for(j=0; j<self.us.length; j++){
										for(i=0; i<self.friends.length; i++){
											console.log();
										if(self.friends[i].friendId === self.us[j].id){
											self.us.splice(j, 1);
											console.log(self.us)
										}
									}
								}
								self.asd = self.us;
								
								
								},function(errResponse) {
									console.error('Error while fetching Friends');
								} );
							
							
							},function(errResponse) {
								console.error('Error while fetching Users');
							});	
						};
						
						self.send = function(friendUser){
							console.log("sending friend request...")
							FriendService.createFriend(friendUser).then(function(d) {
								console.log(d)
							
								$location.path("/Friends")
								
											},
											function(errResponse) {
												console.error('Error while creating friend..');
						});
						};
						
							self.requestedFriend = function() {
							$rootScope.loginUser =$rootScope.currentUser;
							console.log("GetAllRequestedFriends...")
							FriendService.fetchRequestedfriends($rootScope.loginUser.user_name).then(function(d) {
												self.reqFriend = d;
												
												console.log(self.reqFriend)
											},function(errResponse) {  
												console.error('Error while fetching By Friend Name');
											});
							};
							
							self.acceptFriend = function(reqFriend) {
							console.log('accept the friend request')
								FriendService.updateFriendReq(reqFriend);
								console.log('Accepted')
							$location.path("/Friends")
							};
							
							self.AcceptedFriendCurrentUser = function() {
								$rootScope.loginUser =$rootScope.currentUser;
								console.log("GetAllAcceptedFriendCurrentUser...")
								FriendService.fetchAcceptedFriends($rootScope.loginUser.user_name).then(function(d) {
													self.accFriend = d;
													
													console.log(self.accFriend)
												},function(errResponse) {  
													console.error('Error while fetching Accepted list');
												});
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
						self.user = {userId : null,user_name : '',first_name : '',last_name : '',password : '',email_id : '',dob : '',role : '',status : '',is_online : ''};
						self.users = [];
						self.friend={friendId:null,friendName:'',userId:'',userName:'',status:''};
						self.friends=[];
					};
					
					
}
	
	
	])