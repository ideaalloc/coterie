/**
 * Topic controllers.
 * Author: Bill Lv<billcc.lv@hotmail.com>
 * Date: 2014-12-10
 */
topicApp.controller('topicController', function ($scope, $location, contextPath, topicService, commentService, voteService) {
    $scope.model = {
        topic: null,
        comments: [],
        votes: [],
        vote: {
            id: 0,
            userId: 0,
            topicId: 0,
            _csrf: null
        },
        comment: {
            id: 0,
            topicId: 0,
            userId: 0,
            content: null,
            _csrf: null
        },
        newComment: {
            content: null
        }
    };

    $scope.initiator = false;

    $scope.socket = {
        client: null,
        stomp: null
    };

    $scope.init = function (topicId) {
        $scope.getTopic(topicId);
        $scope.getComments(topicId);
        $scope.getVotes(topicId);
    };

    $scope.getTopic = function (id) {
        topicService.getTopic(id).then(function (response) {
            $scope.model.topic = response;
        });
    };

    $scope.getComments = function (topicId) {
        commentService.getComments(topicId).then(function (response) {
            if (response) {
                $scope.model.comments = response;
            } else {
                $scope.model.comments = [];
            }
        });
    };

    $scope.getVotes = function (topicId) {
        voteService.getVotes(topicId).then(function (response) {
            $scope.model.votes = response;
        });
    };

    $scope.vote = function (topicId, userId) {
        $scope.initiator = true;
        $scope.model.vote.userId = userId;
        $scope.model.vote.topicId = topicId;
        $scope.model.vote._csrf = $('#csrf').val();
        voteService.vote($scope.model.vote).then(function (response) {
            $scope.model.votes.push(response);
        });
    };

    $scope.comment = function () {
        $scope.initiator = true;
        $scope.model.comment.userId = $('#userId').val();
        $scope.model.comment.topicId = $('#topicId').val();
        $scope.model.comment._csrf = $('#csrf').val();
        $scope.model.comment.content = $('#commentContent').val();
        commentService.comment($scope.model.comment).then(function (response) {
            if ($scope.model.comments === undefined) {
                $scope.model.comments = [];
            }
            $scope.model.comments.push(response);
        });
        $scope.model.newComment.content = '';
    };

    $scope.notify = function (/** Message */ message) {
        if (!$scope.initiator) {
            $scope.init();
        }
        $scope.initiator = false;
    };

    $scope.reconnect = function () {
        setTimeout($scope.initSockets, 10000);
    };

    $scope.initSockets = function () {
        if (contextPath == null || contextPath == '') {
            $scope.socket.client = new SockJS('/notify');
        } else {
            $scope.socket.client = new SockJS('/' + contextPath + '/notify');
        }
        $scope.socket.stomp = Stomp.over($scope.socket.client);
        $scope.socket.stomp.connect({}, function () {
            $scope.socket.stomp.subscribe("/topic/notify", $scope.notify);
        });
        $scope.socket.client.onclose = $scope.reconnect;
    };

    $scope.initSockets();
});
