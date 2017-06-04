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


<%--
  ~ Copyright © 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
  --%>

<form:form action="credit-card/edit.do" modelAttribute="creditcard">

    <form:hidden path="id"/>
    <form:hidden path="version"/>

    <acme:textbox path="holderName" code="credit-card.holderName"/>
    <br/>
    <acme:textbox path="number" code="credit-card.number"/>
    <br/>
    <acme:textbox path="year" code="credit-card.year"/>
    <br/>
    <acme:textbox path="month" code="credit-card.month"/>
    <br/>
    <acme:textbox path="CVV" code="credit-card.CVV"/>
    <br/>
    <form:label path="type">
        <spring:message code="credit-card.type"/>:
    </form:label>
    <form:select path="type" code="credit-card.type">
        <form:options/>
    </form:select>
    <br/>

    <!---------------------------- BOTONES -------------------------->

    <acme:submit name="save" code="general.save"/>


    <acme:cancel url="credit-card/list.do" code="general.cancel"/>


</form:form>