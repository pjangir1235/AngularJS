var app = angular.module('module1', ['ngRoute']);

app.controller('getAllEmployee', function ($scope, $http, fetchEmployee) {
    $scope.id;
    $scope.name;
    $scope.mobile;
    $scope.isHidden = true;
    $scope.dataEmp = fetchEmployee.getAll().then(function (data) {
        $scope.details = data;

    })
    $scope.delet = function (id, name, mobile) {
        fetchEmployee.deleteEmp(id).then(function () {
            $scope.details = $scope.details.filter(function (e) {
                return e.id != id;
            })
        })


    }
    $scope.updateFunc = function (id, name, mobile) {
        $scope.id = id;
        $scope.name = name;
        $scope.mobile = mobile;
        $scope.isHidden = false;


    }
    $scope.doUpdate = function () {
        var dat = {
            "id": $scope.id,
            "name": $scope.name,
            "mobile": $scope.mobile
        };
        fetchEmployee.doUpdateEmployee(dat).then(function () {
            detail = $scope.details.find(function (e) {
                return e.id == dat.id;
            });
            detail.name = dat.name;
            detail.mobile = dat.mobile;
            $scope.isHidden = true;


        })
        $scope.id = "";
        $scope.name = "";
        $scope.mobile = "";
    }
});

app.service('fetchEmployee', ['$http', function ($http) {
    this.getAll = function () {
        return (
            $http.get("/restCRUD/webapi/employee/").then(function (response) {
                return (response.data);
            }, function (err) {
            })
        );
    }
    this.deleteEmp = function (id) {
        return ($http.delete("/restCRUD/webapi/employee/" + id).then(function (response) {
            return response
        }, function (err) {
        }))

    }
    this.doUpdateEmployee = function (data) {
        return (
            $http.put("/restCRUD/webapi/employee/", data).then(function (response) {
                return response;
            }, function (err) {
            }))

    }
}]);


app.controller('addEmployee', function ($scope, $http, addEmp) {

    $scope.name;
    $scope.mobile;
    $scope.add = function () {
        if ($scope.name != null && $scope.mobile != null) {
            var data = {
                "name": $scope.name,
                "mobile": $scope.mobile
            };
            addEmp.addEmpl(data);
                $scope.name = "";
                $scope.mobile = ""
            
        }
    } 
});

app.service('addEmp', ['$http', function ($http) {
    this.addEmpl = function (data) {

        $http.post('/restCRUD/webapi/employee/', data).success(function (response) {
            console.log(response);
        }).error(function (response) {
            console.log('piyush jangir');
        });
    }

}]);


app.controller('searchEmployee', function ($scope, $http, searchEmp) {
    $scope.id;
    $scope.datails = {};
    $scope.search = function () {
        $scope.datails = {};
        console.log("CheckPoint 1 id :" + $scope.id);
        searchEmp.findOne($scope.id).then(function (data) {
            $scope.details = data;
            console.log('finalLog' + data);

            console.log(data);
        }
        )
    }
});


app.service('searchEmp', ['$http', function ($http) {
    this.findOne = function (id) {
        return (
            $http.get("/restCRUD/webapi/employee/" + id).then(function (response) {
                console.log(response);
                return (response.data);
            }, function (err) {
                console.log(err);
            })
        )
    }
}])


app.config(function ($routeProvider, $locationProvider) {
    console.log("config");
    $routeProvider
        .when('/ListEmployee', {
            templateUrl: '/getAllEmp.html',
            controller: 'getAllEmployee'
        })
        .when('/NewEmployee', {
            templateUrl: '/addEmp.html',
            controller: 'addEmployee'
        })
        .when('/FindEmployee', {
            templateUrl: '/searchEmp.html',
            controller: 'searchEmployee'
        })
    $locationProvider.html5Mode({
        enabled: true,
        requireBase: false
    });
})