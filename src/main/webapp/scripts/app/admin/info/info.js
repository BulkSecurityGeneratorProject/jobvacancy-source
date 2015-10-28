'use strict';

angular.module('jobvacancyApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('info', {
                parent: 'admin',
                url: '/info',
                data: {
                    authorities: ['ROLE_ADMIN'],
                    pageTitle: 'Information'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/admin/info/info.html',
                        controller: 'InfoController'
                    }
                },
                resolve: {
                    
                }
            });
    });
