<%@page language="java" contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
          uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>


<form:form action="search/edit.do" modelAttribute="search">

    <form:hidden path="id" />
    <form:hidden path="version" />
    <form:hidden path="results" />
    <form:hidden path="owner" />


    <acme:textbox path="keyword" code="search.keyword"/>
    <br />

    <acme:select path="category" code="question.categories" items="${categories}" itemLabel="name"/>
    <br />


    <!---------------------------- BOTONES -------------------------->

    <acme:submit name="save" code="general.save"/>

    <a class="button" href="/welcome/index.do"><spring:message code="general.cancel"/></a>



</form:form>