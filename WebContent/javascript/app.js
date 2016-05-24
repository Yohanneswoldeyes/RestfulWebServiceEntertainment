(function() {	
	var app = angular.module("app", []);
	app.controller("HttpCtrl", function($scope, $http) {
		//default url
		var url = "https://www.youtube.com/embed/52AjNsofvNQ?=rel=0"; 
		var title = "Best Trucks in Football History";
		var count = 5678;
		jQuery(".iframeSource").attr("src", url );
		$scope.movieTitle = title;
		$scope.count = count;

		//default display
		$scope.defaultcall = function(url) {
			var url = jQuery(".iframeSource").attr("src");
			var response = $http.post('/RestfulWebServiceEntertainment/rest/comment/search' , url);
			response.success(function(data) {
				$scope.comments = data;
			});
			response.error(function(data, status, headers, config) {
				alert("AJAX failed to get data, status=" + status);
			});
		};

		//calling the default call 
		$scope.defaultcall(url);
		//get videos passing a videotype so we can you this method for different type of videoTypes
		$scope.getVideos = function() {
			var app = this;
			var videoType = "AmharicEntertainment";
			var response = $http.get('/RestfulWebServiceEntertainment/rest/comment/videos/'+  videoType);
			response.success(function(data) {
				$scope.videos = data;
			});

			response.error(function(data, status, headers, config) {
				alert("AJAX failed to get data, status=" + status);
			});
		};

		//calling the get video method to
		$scope.getVideos();

		$scope.upload = function(){
			alert("Coming Soon !!!")
		};

		$scope.search = function(){
			alert("Coming Soon !!!")
		};

		// change the url for the iframe when user select or click another video
		$scope.changeUrlForVideo = function(url, title, count){
			jQuery(".iframeSource").attr("src", url );
			$scope.defaultcall(url);
			$scope.movieTitle = title;
			$scope.count = count;
		};

		//submit comment to the server. Each video has its own comment area.
		$scope.submitComment = function() {
			$scope.jsonObj = $scope.c;
			$scope.jsonObj.entertainmentTitle ="amharicEntertainment";
			$scope.jsonObj.url =jQuery(".iframeSource").attr("src");

			var response = $http.post('/RestfulWebServiceEntertainment/rest/comment/add', $scope.jsonObj);
			response.success(function(data) {
				$scope.comments = data;
				$scope.c = '';
				console.log("[main] # of items: " + data.length);
				angular.forEach(data, function(element) {
					console.log("[main] comment: " + element.name);
				});

				$scope.defaultcall($scope.jsonObj.url);
				$scope.movieTitle = $scope.jsonObj.title;
				$scope.count = $scope.jsonObj.count;
			});
			response.error(function(data, status, headers, config) {
				alert("AJAX failed to get data, status=" + status);
			});	
		};
	});	
})();
