'use strict'

app.controller('JobController',['$scope','JobServices','$cookies','$rootScope','$route','$http','$location'
	,function($scope,JobServices,$cookies,$rootScope,$route,$http,$location){
	console.log("JobController......")
	var self=this;
	self.job={jobid:'',jobProfile:'',jobDescription:'',qualification:'', status:'', postDate:''};
	self.jobs=[];
		

	
	self.fetchAllJobs = function () {
		console.log("fetchAllJobs...")
		JobServices.fetchAllJobs().then(function(d) {
							self.jobs = d;
							console.log(self.jobs)
						},function(errResponse) {  
							console.error('Error while fetching Jobs');
						});
	};

	
		self.createJob=function(job){
			console.log('create all job');
			JobServices.createJob(job).then(function(d){
				alert("Thank you for posting the job")
				$location.path("/admin")
			},
			function(errResponse) {
				console.error('Error while creating Job.');
			});
		};
		
		
		self.get = function (job){
			$scope.jv=job;
			console.log($scope.jv);
			$rootScope.viewJob=$scope.jv;
			console.log('viewJob')
			$location.path("/viewJob");
			
			
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