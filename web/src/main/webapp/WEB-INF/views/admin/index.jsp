<%--
  Admin index page.
  User: Bill Lv (Lv, Chao)
  Date: 2014/12/8
  Time: 18:09
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
  <meta charset="UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title><spring:message code="label.admin.title" /></title>
  <link rel="stylesheet" href="libs/semantic-ui/dist/semantic.min.css"/>
</head>
<body>
<div class="ignored ui info message">Under Construction....</div>
<form action="/logout" method="post">
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
  <button type="submit" class="ui fluid button"><spring:message code="label.logout" /></button>
</form>
</body>
</html>
