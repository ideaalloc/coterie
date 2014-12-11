/**
 * Topic app.
 * Author: Bill Lv<billcc.lv@hotmail.com>
 * Date: 2014-12-10
 */
var topicApp = angular.module("topic", []);
topicApp.value('contextPath', '');

topicApp.config(['$httpProvider', function ($httpProvider) {
    $httpProvider.defaults.useXDomain = true;
    delete $httpProvider.defaults.headers.common['X-Requested-With'];
}]);
