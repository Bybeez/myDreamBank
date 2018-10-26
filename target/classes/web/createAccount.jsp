<%@ page import="models.User" %>
<%@ page import="java.util.List" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt"%>

<c:if test="${param.locale != null}">
    <fmt:setLocale value="${param.locale}" scope="session"/>
</c:if>
<fmt:bundle basename="locales" prefix="account.">

<html>

<%@include file="includes/header.jsp"%>

<body>
<%@include file="includes/navbar.jsp"%>

<div class="container">
    <br>

    <div class="row">
        <form class="col s12 " action="/TheBankProject-1.0-SNAPSHOT/newAccount" method="post">
            <div class="row">
                <div class="input-field col s12">
                    <select name="accountType">
                        <option value="" disabled selected><fmt:message key="accountType"/></option>
                        <c:forEach items="${accountTypes}" var="accountType">
                            <option value=<c:out value="${accountType.id}"/>><c:out value="${accountType.description}"/> - <fmt:message key="rate"/> : <c:out value="${accountType.rate}"/> </option>
                        </c:forEach>
                    </select>
                    <label>Materialize Select</label>
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