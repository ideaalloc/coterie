<%--
  Topic index page.
  User: Bill Lv (Lv, Chao)
  Date: 2014/12/10
  Time: 21:21
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html class="no-js">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>圈子</title>
    <c:set value="${pageContext.request.contextPath}" var="contextPath"/>
    <link rel="stylesheet" href="${contextPath}/libs/semantic-ui/dist/semantic.min.css"/>
</head>
<body ng-app="topic">
<div ng-controller="topicController" data-ng-init="init(${topicId})">
    <h2 class="ui header">
        <i class="settings icon"></i>

        <div class="content">
            {{model.topic.title}}
            <div class="sub header">{{model.topic.description}}</div>
            <div class="right floated description" ng-class="{red: model.votes.length <= 0}">
                投票 {{model.votes.length}} 次
                <div class="mini ui buttons">
                    <button class="ui button" ng-click="vote(${topicId}, ${userId})">+1</button>
                </div>
            </div>
        </div>
    </h2>
    <div class="ui info icon message" ng-hide="model.comments.length">
        <i class="thumbs up icon"></i>

        <h2 class="header">哇塞！</h2>
        您似乎是本话题第一个读者，来发表一下自己的看法吧。
    </div>
    <div class="ui divided list">
        <div class="item" ng-repeat="comment in model.comments">
            <div class="content">
                <div class="description">{{comment.content}}</div>
            </div>
        </div>
    </div>
    <form name="commentForm" ng-submit="comment()" class="ui form" novalidate>
        <input type="hidden" id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="hidden" id="userId" value="${userId}"/>
        <input type="hidden" id="topicId" value="${topicId}"/>

        <div class="ui grid">
            <div class="thirteen wide column">
                <div class="ui field"
                     ng-class="{error: commentForm.commentContent.$invalid && (!commentForm.commentContent.$pristine)}">
                    <textarea id="commentContent" name="commentContent" placeholder="评论..."
                              ng-model="model.newComment.content" rows="3"
                              ng-required="true"></textarea>
                </div>
            </div>
            <div class="three wide column">
                <button type="submit" class="ui fluid button">评论</button>
            </div>
        </div>
    </form>
</div>

<script type="text/javascript" src="${contextPath}/libs/jquery/dist/jquery.min.js"></script>
<script type="text/javascript" src="${contextPath}/libs/angular/angular.min.js"></script>
<script type="text/javascript" src="${contextPath}/libs/angular-resource/angular-resource.min.js"></script>
<script type="text/javascript" src="${contextPath}/libs/sockjs/sockjs.min.js"></script>
<script type="text/javascript" src="${contextPath}/libs/stomp-websocket/lib/stomp.min.js"></script>
<script type="text/javascript" src="${contextPath}/app/topic/app.js"></script>
<script type="text/javascript" src="${contextPath}/app/topic/controllers.js"></script>
<script type="text/javascript" src="${contextPath}/app/topic/services.js"></script>
<script>
    topicApp.value('contextPath', "${contextPath}");
</script>
</body>
</html>
