<%@ page import="models.User" %>
<%@ page import="models.Account" %>
<%@ page import="java.util.Set" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:if test="${param.locale != null}">
    <fmt:setLocale value="${param.locale}" scope="session"/>
</c:if>
<fmt:bundle basename="locales" prefix="transaction.">
<html>

<%@include file="includes/header.jsp"%>
<body>
<%@include file="includes/navbar.jsp"%>

<div class="container">
    <h4><fmt:message key="account"/> : <c:out value = "${account.accountType.description}"/></h4>
    <ul class="collection with-header col s12">
        <li class="collection-header"><h4><fmt:message key="from"/></h4></li>
        <c:forEach items = "${user.accounts}" var = "account">
            <c:if test="${account.id == param.id}">
                <c:forEach items = "${account.transactionsFrom}" var = "transaction">
                    <li class="collection-item"><fmt:message key="amount"/> <c:out value="${transaction.amount}"/></li>
                </c:forEach>
            </c:if>
        </c:forEach>
    </ul>
    <ul class="collection with-header col s12">
        <li class="collection-header"><h4><fmt:message key="to"/></h4></li>
        <c:forEach items = "${user.accounts}" var = "account">
            <c:if test="${account.id == param.id}">
                <c:forEach items = "${account.transactionsTo}" var = "transaction">
                    <li class="collection-item"><fmt:message key="amount"/> <c:out value="${transaction.amount}"/></li>
                    <li class="collection-item"><fmt:message key="source"/> <c:out value="" </li>
                </c:forEach>
            </c:if>
        </c:forEach>
    </ul>
</div>

<%@include file="includes/footer.jsp"%>
</body>
</html>
</fmt:bundle>