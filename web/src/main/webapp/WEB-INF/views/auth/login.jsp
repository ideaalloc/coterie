<%--
  Login Page.
  User: Bill Lv <billcc.lv@hotmail.com>
  Date: 2014/12/7
  Time: 17:32
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>圈子</title>
    <link rel="stylesheet" href="libs/semantic-ui/dist/semantic.min.css"/>
</head>
<body>
<form action="/login" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <div class="ui error warning form segment">
        <c:if test="${param.error != null}">
            <div class="ui error message">
                <div class="header">操作失败</div>
                <p>用户名或者密码非法。</p>
            </div>
        </c:if>

        <c:if test="${param.logout != null}">
            <div class="ui warning message">
                <div class="header">请注意</div>
                <p>您已登出。</p>
            </div>
        </c:if>

        <div class="field">
            <label>用户名</label>
            <input name="username" placeholder="用户名" type="text">
        </div>
        <div class="field">
            <label>密码</label>
            <input name="password" type="password">
        </div>
        <div class="three wide column">
            <button type="submit" class="ui fluid button">登录</button>
        </div>
    </div>
</form>

</body>
</html>
