<%@ page import="models.User" %>
<%@ page import="models.Account" %>
<%@ page import="java.util.Set" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:if test="${param.locale != null}">
    <fmt:setLocale value="${param.locale}" scope="session"/>
</c:if>
<fmt:bundle basename="locales" prefix="user.">
<html>

<%@include file="includes/header.jsp"%>

<body>
<%@include file="includes/navbar.jsp"%>
<div class="container">
    <div class="row">
        <a class="waves-effect waves-light btn" href="/TheBankProject-1.0-SNAPSHOT/newAccount"><fmt:message key="openAccount"/> </a>
        <a class="waves-effect waves-light btn" href="/TheBankProject-1.0-SNAPSHOT/password"><fmt:message key="newPassword"/> </a>
    </div>
    <ul class="collection with-header col s12">
        <li class="collection-header"><h4><fmt:message key="user"/> : <c:out value = "${user.name}"/> <c:out value = "${user.firstname}"/></h4></li>
        <c:forEach items = "${user.accounts}" var = "account">
            <li class="collection-item">
                <div class="container">
                    <div class="row">
                        <div class="col s12 m6">
                            <div class="card blue-grey darken-1">
                                <div class="card-content white-text">
                                    <span class="card-title"><c:out value = "${account.accountType.description}"/></span>
                                    <p>
                                    <ul class="collection blue-grey-text text-darken-1">
                                        <li class="collection-item"><fmt:message key="balance"/> : <c:out value="${account.balance}"/>€</li>
                                        <li class="collection-item"><fmt:message key="rate"/> : <c:out value="${account.accountType.rate}"/>%</li>
                                        <li class="collection-item"><a class="waves-effect waves-light btn" href="/TheBankProject-1.0-SNAPSHOT/transactions?id=<c:out value="${account.id}"/>"><fmt:message key="more"/></a></li>
                                    </ul>
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </li>
        </c:forEach>
    </ul>
</div>

<%@include file="includes/footer.jsp"%>
</body>
</html>
</fmt:bundle>