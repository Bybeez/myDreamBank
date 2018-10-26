<%@ page import="models.User" %>
<%@ page import="java.util.List" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt"%>

<c:if test="${param.locale != null}">
    <fmt:setLocale value="${param.locale}" scope="session"/>
</c:if>
<fmt:bundle basename="locales" prefix="password.">

<html>

<%@include file="includes/header.jsp"%>

<body>
<%@include file="includes/navbar.jsp"%>

<div class="container">
    <br>

    <div class="row">
        <form class="col s12 " action="/TheBankProject-1.0-SNAPSHOT/password" method="post">
            <c:if test="${errorMsg != null}">
                <div class="row">
                    <span class="left new badge red" data-badge-caption="<fmt:message key = "${errorMsg}" />"></span><br>
                </div>
            </c:if>
            <div class="row">
                <div class="input-field col s12">
                    <input id="oldPassword" type="password" class="validate" name="oldPassword" required placeholder=<fmt:message key = "oldPasswordLabel"/> >
                    <label for="oldPassword"><fmt:message key = "oldPasswordLabel"/></label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <input id="password" type="password" class="validate" name="newPassword" required placeholder=<fmt:message key = "passwordLabel"/> >
                    <label for="password"><fmt:message key = "passwordLabel"/></label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <input id="password_confirmation" type="password" class="validate" name="confPassword" required placeholder=<fmt:message key = "passwordConfLabel"/> >
                    <label for="password_confirmation"><fmt:message key = "passwordConfLabel"/></label>
                </div>
            </div>
            <button class="btn waves-effect waves-light" type="submit" name="action"><fmt:message key="submitLabel"/>
                <i class="material-icons right">send</i>
            </button>
        </form>
    </div>

</div>

<%@include file="includes/footer.jsp"%>
</body>
</html>

</fmt:bundle>