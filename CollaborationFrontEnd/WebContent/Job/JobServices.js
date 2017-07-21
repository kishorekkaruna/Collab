'use strict'

app.service('JobServices', [ '$http', '$q', '$rootScope',
		function($http, $q, $rootScope) {
			console.log("JobService...")

			var BASE_URL = 'http://localhost:8080/RestController'
			return {

				createJob : function(job) {
					console.log("calling create job")
					return $http.post(BASE_URL + '/Job', job) // 1
					.then(function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while creating job');
						return $q.reject(errResponse);
					});
				}
			}
		}

])