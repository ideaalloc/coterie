<%--
  Index Page.
  User: Bill Lv <billcc.lv@hotmail.com>
  Date: 2014/12/7
  Time: 11:27
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title><spring:message code="label.title" /></title>
    <link rel="stylesheet" href="libs/semantic-ui/dist/semantic.min.css"/>
</head>
<body>
<div class="ignored ui info message">Under Construction....</div>
<form action="/logout" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <button type="submit" class="ui submit button"><spring:message code="label.logout" /></button>
</form>
</body>
</html>
