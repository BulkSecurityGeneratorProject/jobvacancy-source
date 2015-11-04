'use strict';

angular.module('jobvacancyApp').controller('ApplicationDialogController',
    ['$scope', '$stateParams', '$modalInstance', 'entity', 'JobOffer','Application', 'User',
        function($scope, $stateParams, $modalInstance, entity, JobOffer,Application, User) {

        $scope.jobApplication = entity;


        var onSaveFinished = function (result) {
            $scope.$emit('jobvacancyApp:jobOfferUpdate', result);
            $modalInstance.close(result);
        };

        $scope.save = function () {
            Application.save($scope.jobApplication, onSaveFinished);
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
        
        $scope.uploadFiles = function(file, errFiles) {
        $scope.f = file;
        alert($scope.f.toSource());
        $scope.errFile = errFiles && errFiles[0];
        if (file) {
            file.upload = Upload.upload({
                url: 'api/Application',
                data: {file: file}
            });

            file.upload.then(function (response) {
                $timeout(function () {
                    file.result = response.data;
                });
            }, function (response) {
                if (response.status > 0)
                    $scope.errorMsg = response.status + ': ' + response.data;
            }, function (evt) {
                file.progress = Math.min(100, parseInt(100.0 * 
                                         evt.loaded / evt.total));
            });
        }   
    }
}]);
