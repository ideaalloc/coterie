<%--
  403 access denied page.
  User: Bill Lv (Lv, Chao)
  Date: 2014/12/8
  Time: 16:54
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
<div class="ignored ui error message"><spring:message code="label.error.access.denied" /></div>
</body>
</html>
