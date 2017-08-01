'use strict'

app.service('FriendService',['$q','$rootScope','$http', function($q,$rootScope,$http){
	console.log('Friend Service....')
	
	var BASE_URL='http://localhost:8080/RestController'
		return {
		
		fetchAllRequestedfriends:function(name){
			console.log('Requested Friends')
			return $http.get(BASE_URL+'/friendss/'+name).then(function(response){
				return response.data;
					},function(errResponse){
						console.error('Error while getting Requested Friend');
						return $q.reject(errResponse);
					});
		},
		
		createFriend:function(friendUser) {
			console.log("calling create Friend")
			return $http.post(BASE_URL + '/friends', friendUser) // 1
			.then(function(response) {
				console.log(response.data)
				return response.data;
			}, function(errResponse) {
				console.error('Error while creating friends');
				return $q.reject(errResponse);
			});
		},
		
		fetchRequestedfriends : function(friendName) {
			console.log("calling fetchBy User name ")
			return $http.get(BASE_URL + '/friends/' +friendName).then(
					function(response) {
						return response.data;
					}, null);
		},
			
		updateFriendReq:function(friend) {
			console.log("updating Friends Requested")
			return $http.put(BASE_URL + '/friendAccept/', friend).then(function(response) {
				return response.data;
			}, function(errResponse) {
				console.error('Error while updating Friend');
				return $q.reject(errResponse);
			});
			},
			
			fetchAcceptedFriends:	function(friendName) {
				console.log("calling fetchBy User name ")
				return $http.get(BASE_URL + '/friendsAccepted/' +friendName).then(
						function(response) {
							return response.data;
						}, null);
			}

		};
}]);