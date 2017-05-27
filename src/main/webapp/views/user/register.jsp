<%@page language="java" contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1" %>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="security"
          uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>


<form:form action="user/register.do" modelAttribute="user">

    <form:hidden path="id"/>
    <form:hidden path="version"/>


    <acme:textbox path="name" code="user.name"/>
    <acme:textbox path="surname" code="user.surname"/>
    <acme:textbox path="email" code="user.email"/>
    <acme:textbox path="phoneNumber" code="user.phone"/>


    <%--<h3><spring:message code="edit.creditcard" var="cc"/>--%>
    <%--<jstl:out value="${cc}"/>--%>
    <%--</h3>--%>
    <%--<acme:textbox path="creditCard.holder" code="creditCard.holderName"/>--%>
    <%--<br />--%>
    <%--<form:label path="creditCard.brand">--%>
    <%--<spring:message code="creditCard.brandName"/>:--%>
    <%--</form:label>--%>
    <%--<form:select path="creditCard.brand" code="creditCard.brandName">--%>
    <%--<form:options/>--%>
    <%--</form:select>--%>
    <%--<acme:textbox path="creditCard.number" code="creditCard.number"/>--%>
    <%--<br />--%>
    <%--<acme:textbox path="creditCard.ExpirationYear" code="creditCard.expirationYear"/>--%>
    <%--<br />--%>
    <%--<acme:textbox path="creditCard.ExpirationMonth" code="creditCard.expirationMonth"/>--%>
    <%--<br />--%>
    <%--<acme:textbox path="creditCard.CVV" code="creditCard.CVV"/>--%>
    <%--<br />--%>


    <br/>
    <h1>User Account</h1>
    <br>
    <form:label path="userAccount.username">
        <spring:message code="user.username"/>:
    </form:label>
    <form:input path="userAccount.username"/>
    <form:errors cssClass="error" path="UserAccount.username"/>
    <br/>
    <br>
    <form:label path="userAccount.password">
        <spring:message code="user.password"/>:
    </form:label>
    <form:password path="userAccount.password"/>
    <form:errors cssClass="error" path="UserAccount.password"/>
    <br/>
    <br/>

    <!---------------------------- BOTONES -------------------------->
    <input class="button2" type="submit" name="save"
           value="<spring:message code="user.save" />"/>
    <a class="button" href="/welcome/index.do"><spring:message code="general.cancel"/></a>

</form:form>