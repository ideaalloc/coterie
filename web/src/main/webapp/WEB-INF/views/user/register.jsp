<%--
  User registration.
  User: Bill Lv (Lv, Chao)
  Date: 2014/12/9
  Time: 9:10
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title><spring:message code="label.title"/></title>
    <link rel="stylesheet" href="libs/semantic-ui/dist/semantic.min.css"/>
</head>
<body>
<div class="ui form segment">
    <div class="two fields">
        <c:set value="${pageContext.response.locale}" var="locale"/>
        <c:set value="zh" var="chinese"/>
        <spring:message code="label.register.first.name" var="firstName"/>
        <spring:message code="label.register.last.name" var="lastName"/>
        <c:choose>
            <c:when test="${chinese.equalsIgnoreCase(fn:substring(locale, 0, 2))}">
                <div class="field">
                    <label>${lastName}</label>
                    <input placeholder="${lastName}" type="text">
                </div>
                <div class="field">
                    <label>${firstName}</label>
                    <input placeholder="${firstName}" type="text">
                </div>
            </c:when>
            <c:otherwise>
                <div class="field">
                    <label>${firstName}</label>
                    <input placeholder="${firstName}" type="text">
                </div>
                <div class="field">
                    <label>${lastName}</label>
                    <input placeholder="${lastName}" type="text">
                </div>
            </c:otherwise>
        </c:choose>
    </div>
    <div class="field">
        <spring:message code="label.register.username" var="username"/>
        <label>${username}</label>
        <input placeholder="${username}" type="text">
    </div>
    <div class="field">
        <label><spring:message code="label.register.password"/></label>
        <input type="password">
    </div>
    <div class="ui submit button"><spring:message code="label.register.submit"/></div>
</div>
</body>
</html>
