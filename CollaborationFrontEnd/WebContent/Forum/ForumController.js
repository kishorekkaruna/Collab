'use strict'

app.controller('ForumController',['$scope','ForumService','$cookies','$rootScope','$route','$http','$location'
		,function($scope,ForumService,$cookies,$rootScope,$route,$http,$location){
	console.log("Forum Controller....")
	var self=this;
	self.forum={forumid:'',title:'',user_id:'',description:'', forumDate:'', status:''};
	self.forums=[];
	
	self.createForum=function(forum){
		console.log('create all forum');
		ForumService.createForum(forum).then(function(d){
			alert("Thank you for posting the Forum")
			$location.path("/login")
		},
		function(errResponse) {
			console.error('Error while creating Forum.');
		});
	};
	self.submit=function(){
		console.log('Calling Submit...')
		self.createForum(self.forum);

	self.reset();
	};
	self.reset=function(){
		console.log('calling Reset');
	self.forum = { forumid:null,title:'',user_id:'',description:'', forumDate:'', status:''};
	self.forums=[];
	};
	
}
])