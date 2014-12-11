/**
 * Topic services.
 * Author: Bill Lv<billcc.lv@hotmail.com>
 * Date: 2014-12-10
 */
topicApp.service('topicService', function ($http, $q) {
    this.getTopic = function (id) {
        var d = $q.defer();
        $http.get('/topics/' + id).success(function (response) {
            d.resolve(response);
        });
        return d.promise;
    };
});

topicApp.service('commentService', function ($http, $q) {
    this.getComments = function (topicId) {
        var d = $q.defer();
        $http.get('/topics/' + topicId + '/comments').success(function (response) {
            d.resolve(response);
        });
        return d.promise;
    };

    this.comment = function (comment) {
        var d = $q.defer();
        $http.defaults.useXDomain = true;
        var config = {
            headers: {'X-CSRF-Token': comment._csrf}
        };
        $http.post('/comment/', comment, config).success(function (data, status, headers, config) {
            d.resolve(data);
        });
        return d.promise;
    };
});

topicApp.service('voteService', function ($http, $q) {
    this.getVotes = function (topicId) {
        var d = $q.defer();
        $http.get('/topics/' + topicId + '/votes').success(function (response) {
            d.resolve(response);
        });
        return d.promise;
    };

    this.vote = function (vote) {
        var d = $q.defer();
        var config = {
            headers: {'X-CSRF-Token': vote._csrf}
        };
        $http.post('/vote/', vote, config).success(function (response) {
            d.resolve(response);
        });
        return d.promise;
    };
});