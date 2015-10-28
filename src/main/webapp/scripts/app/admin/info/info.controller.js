'use strict';

angular.module('jobvacancyApp')
    .controller('InfoController', function ($scope, InfoService) {
        $scope.offers = InfoService.get();
    });
