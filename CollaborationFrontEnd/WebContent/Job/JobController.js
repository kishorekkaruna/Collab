'use strict'

app.controller('JobController',['$scope','JobServices','$cookies','$rootScope','$route','$http','$location'
	,function($scope,JobServices,$cookies,$rootScope,$route,$http,$location){
	console.log("JobController......")
	var self=this;
	self.job={jobid:'',jobProfile:'',jobDescription:'',qualification:'', status:'', postDate:''};
	self.jobs=[];
	
		self.createJob=function(job){
			console.log('create all job');
			JobServices.createJob(job).then(function(d){
				alert("Thank you for posting the job")
				$location.path("/login")
			},
			function(errResponse) {
				console.error('Error while creating Job.');
			});
		};
		self.submit = function(){
			console.log('Calling Submit...')
			self.createJob(self.job);

		self.reset();
		};
		
		self.reset = function(){
			console.log('calling Reset');
			self.job = { jobid:null,jobProfile:'',jobDescription:'',qualification:'', status:'', postDate:''	};
			self.jobs=[];
			
		};
	
}

])