'use strict';

angular.module('jobvacancyApp')
    .factory('InfoService', function ($resource) {
        return $resource('api/offers', {}, {
            'get': { method: 'GET', isArray: true}
        });
    });
