'use strict'

app.controller('ForumController',['$scope','ForumService','ForumCommentService','$cookies','$rootScope','$route','$http','$location'
		,function($scope,ForumService,ForumCommentService,$cookies,$rootScope,$route,$http,$location){
	console.log("Forum Controller....")
	var self=this;
	self.forum={forumid:'',title:'',user_id:'',description:'', forumDate:'', status:''};
	self.forums=[];
	self.forumComment={id:'',forumid:'',userId:'',user_name:'',forumComments:'',createdate:'',email_id:''};
	self.froumComments=[];
	



			self.notAcceptedForum = function() {
				console.log("notAcceptedForum...")
				ForumService.notAcceptedForum().then(function(d) {
					console.log(d)
					self.forumNotAccepted = d;
					console.log(self.forumNotAccepted)
				}, function(errResponse) {
					console.error('Error while creating notAcceptedForum');
				});
			}, 
			self.AcceptedForums = function() {
				console.log("AcceptedForums...")
				ForumService.AcceptedForums().then(function(d) {
					// alert("Thank you for creating message")
					console.log(d)
					self.forumsAccept = d;
				}, function(errResponse) {
					console.error('Error while creating Acceptedforums.');
				});
			},
			
			self.get = function(forum){
				$scope.fc=forum;
				console.log($scope.fc);
				$rootScope.forum=$scope.fc;
				ForumCommentService.viewComment(forum.forumid).then (function(d){
					console.log(d)
					self.viewMessage = d;
					$scope.cmt=self.viewMessage;
					console.log(self.viewMessage)
					$rootScope.comment= $scope.cmt
				},function(errResponse){
					console.error('Error while viewing comment');
				});
				$location.path("/viewForum");
			};
			
			self.adminGet = function (Forumget){
				$scope.fv=Forumget;
				console.log($scope.fv);
				$rootScope.viewForum=$scope.fv;
				$location.path("/adminForumdetail");
			};
			
			self.accept = 	function (viewForum) {
				console.log('accept the Forum details')
				ForumService.accept(viewForum);
				console.log(viewForum)
				$location.path("/admin")
			};

			self.createForum = function(forum) {
				console.log('create all forum');
				ForumService.createForum(forum).then(function(d) {
					alert("Thank you for posting the Forum")
					$location.path("/login")
				}, function(errResponse) {
					console.error('Error while creating Forum.');
				});
			};
			self.submit = function() {
				console.log('Calling Submit...')
				self.createForum(self.forum);

				self.reset();
			};
			self.reset = function() {
				console.log('calling Reset');
				self.forum = { forumid:null,title:'',user_id:'',description:'', forumDate:'', status:''};
				self.forums=[];
				self.forumComment={id:null,forumid:'',userId:'',user_name:'',forumComments:'',createdate:'',email_id:''};
				self.froumComments=[];
			};
	
}
])