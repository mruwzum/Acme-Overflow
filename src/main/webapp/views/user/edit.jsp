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


<form:form action="user/edit.do" modelAttribute="user">

    <form:hidden path="id"/>
    <form:hidden path="version"/>
    <form:hidden path="userAccount"/>

    <acme:textbox path="name" code="user.name"/>
    <acme:textbox path="surname" code="user.surname"/>
    <acme:textbox path="email" code="user.email"/>
    <acme:textbox path="phoneNumber" code="user.phone"/>


    <br/>

    <acme:textbox path="creditCard.holderName" code="credit-card.holderName"/>
    <acme:textbox path="creditCard.number" code="credit-card.number"/>
    <acme:textbox path="creditCard.year" code="credit-card.year"/>
    <acme:textbox path="creditCard.month" code="credit-card.month"/>
    <form:label path="creditCard.type">
        <spring:message code="credit-card.type"/>:
    </form:label>
    <form:select path="creditCard.type" code="creditCard.credit-card.type">
        <form:options/>
    </form:select>
    <acme:textbox path="creditCard.CVV" code="credit-card.CVV"/>

    <!---------------------------- BOTONES -------------------------->

    <acme:submit name="save" code="general.save"/>


    <acme:cancel url="welcome/index" code="general.cancel"/>


</form:form>