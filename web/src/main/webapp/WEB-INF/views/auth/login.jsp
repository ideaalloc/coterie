<%--
  Login Page.
  User: Bill Lv <billcc.lv@hotmail.com>
  Date: 2014/12/7
  Time: 17:32
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title><spring:message code="label.title"/></title>
    <link rel="stylesheet" href="libs/semantic-ui/dist/semantic.min.css"/>
</head>
<body>
<form action="/login" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <div class="ui error warning form segment">
        <c:if test="${param.error != null}">
            <div class="ui error message">
                <spring:message code="label.error.operation" var="errorOperation"/>
                <div class="header">${errorOperation}</div>
                <spring:message code="label.error.invalid.login" var="invalidLogin"/>
                <p>${invalidLogin}</p>
            </div>
        </c:if>

        <c:if test="${param.logout != null}">
            <div class="ui warning message">
                <spring:message code="label.warning.notice" var="notice"/>
                <div class="header">${notice}</div>
                <p><spring:message code="label.warning.logout"/></p>
            </div>
        </c:if>

        <div class="field">
            <spring:message code="label.login.username" var="username"/>
            <label>${username}</label>
            <input name="username" placeholder="${username}" type="text">
        </div>
        <div class="field">
            <spring:message code="label.login.password" var="password"/>
            <label>${password}</label>
            <input name="password" type="password">
        </div>
        <spring:message code="label.login" var="login"/>
        <button type="submit" class="ui submit button">${login}</button>
    </div>
</form>

</body>
</html>
