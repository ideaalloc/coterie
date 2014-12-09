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
<form class="ui form" action="/register" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <div class="ui form segment">
        <div class="two fields">
            <c:set value="${pageContext.response.locale}" var="locale"/>
            <c:set value="zh" var="chinese"/>
            <spring:message code="label.register.first.name" var="firstName"/>
            <spring:message code="label.register.last.name" var="lastName"/>
            <c:choose>
                <c:when test="${chinese.equalsIgnoreCase(fn:substring(locale, 0, 2))}">
                    <div class="required field">
                        <label>${lastName}</label>
                        <input placeholder="${lastName}" type="text" name="lastName">
                    </div>
                    <div class="required field">
                        <label>${firstName}</label>
                        <input placeholder="${firstName}" type="text" name="firstName">
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="required field">
                        <label>${firstName}</label>
                        <input placeholder="${firstName}" type="text" name="firstName">
                    </div>
                    <div class="required field">
                        <label>${lastName}</label>
                        <input placeholder="${lastName}" type="text" name="lastName">
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="required field">
            <spring:message code="label.register.username" var="username"/>
            <label>${username}</label>
            <input placeholder="${username}" type="text" name="username">
        </div>
        <div class="required field">
            <label><spring:message code="label.register.password"/></label>
            <input type="password" name="password">
        </div>
        <button type="submit" class="ui submit button"><spring:message code="label.register.submit"/></button>
        <div class="ui error message"></div>
    </div>
</form>

<spring:message code="label.register.error.first.name" var="firstNameErrorMessage"/>
<spring:message code="label.register.error.last.name" var="lastNameErrorMessage"/>
<spring:message code="label.register.error.username" var="usernameErrorMessage"/>
<spring:message code="label.register.error.password.empty" var="passwordEmptyErrorMessage"/>
<spring:message code="label.register.error.password.length" var="passwordLengthErrorMessage"/>
<spring:message code="label.register.error.username.alphanumeric" var="usernameAlphanumericErrorMessage"/>

<script type="text/javascript" src="libs/jquery/dist/jquery.min.js"></script>
<script type="text/javascript" src="libs/semantic-ui/dist/semantic.min.js"></script>
<script>
    $.fn.form.settings.rules.alphanumeric = function (value) {
        var regularExpression = /^(?=[a-zA-Z])([a-zA-Z0-9_]+)$/;
        return regularExpression.test(value);
    }
    $('.ui.form')
            .form({
                firstName: {
                    identifier: 'firstName',
                    rules: [
                        {
                            type: 'empty',
                            prompt: '${firstNameErrorMessage}'
                        }
                    ]
                },
                lastName: {
                    identifier: 'lastName',
                    rules: [
                        {
                            type: 'empty',
                            prompt: '${lastNameErrorMessage}'
                        }
                    ]
                },
                username: {
                    identifier: 'username',
                    rules: [
                        {
                            type: 'empty',
                            prompt: '${usernameErrorMessage}'
                        },
                        {
                            type: 'alphanumeric',
                            prompt: '${usernameAlphanumericErrorMessage}'
                        }
                    ]
                },
                password: {
                    identifier: 'password',
                    rules: [
                        {
                            type: 'empty',
                            prompt: '${passwordEmptyErrorMessage}'
                        },
                        {
                            type: 'length[6]',
                            prompt: '${passwordLengthErrorMessage}'
                        }
                    ]
                }
            });
</script>
</body>
</html>
