'use strict'

app.service('UserService',['$http','$q','$rootScope',function($http,$q,$rootScope){
console.log("UserService...")
	
	var BASE_URL='http://localhost:8080/RestController'
		return {
	
	 createUser: function(user){
     	console.log("calling create user")
             return $http.post(BASE_URL+'/user', user) //1
                     .then(
                             function(response){
                                 return response.data;
                             }, 
                             function(errResponse){
                                 console.error('Error while creating user');
                                 return $q.reject(errResponse);
                             }
                     );
     },
     
     userDetails:function(user){
    	 	console.log("user details")
    	 	return $http(BASE_URL+'/#contact',user).then(function(response){
    	 		return response;
    	 	})
    	 
     },
     
     login: function(user){
  	   console.log("Calling the method authenticate with the user :"+user)
		 
      return $http.post(BASE_URL+'/login', user).then(function(response){
                          return response; 
                       
                      }, 
                     null
              );
     	},
     	logout: function(){
        	console.log('logout....')
            return $http.get(BASE_URL+'/logout')
                    .then(
                            function(response){
                                return response.data;
                            }, 
                          null
                    );
     	},
	}
}
	
])