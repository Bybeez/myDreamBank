<%@ page import="models.User" %>
<%@ page import="java.util.List" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt"%>

<c:if test="${param.locale != null}">
    <fmt:setLocale value="${param.locale}" scope="session"/>
</c:if>
<fmt:bundle basename="locales" prefix="login.">

<html>

<%@include file="includes/header.jsp"%>

<body>
<%@include file="includes/navbar.jsp"%>

<% String errorMsg = (String)request.getAttribute("errorMsg");%>

<div class="container">
    <br>

    <div class="row">
        <form class="col s12 " action="/TheBankProject-1.0-SNAPSHOT/login" method="post">
            <div class="row">
                <c:if test="${errorMsg != null}">
                    <span class="left new badge red" data-badge-caption="<fmt:message key = "${errorMsg}" />"></span><br>
                </c:if>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <input id="login" type="text" class="validate" name="login" placeholder=<fmt:message key = "loginLabel"/> >
                    <label for="login"><fmt:message key = "loginLabel"/></label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <input id="password" type="password" class="validate" name="password" placeholder=<fmt:message key = "passwordLabel"/> >
                    <label for="password"><fmt:message key = "passwordLabel"/></label>
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