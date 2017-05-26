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


<form:form action="webinar/edit.do" modelAttribute="webinar">

    <form:hidden path="id" />
    <form:hidden path="version" />
    <form:hidden path="owner" />
    <form:hidden path="comments" />
    <form:hidden path="partakers" />


    <acme:textbox path="name" code="webinar.name"/>
    <br />
    <acme:textarea path="description" code="webinar.description"/>

    <acme:textbox path="startDate" code="webinar.startDate"/>
    <br />
    <acme:textbox path="price" code="webinar.price"/>
    <br />
    <acme:textbox path="picture" code="webinar.picture"/>
    <br />

    <acme:select path="categories" code="question.categories" items="${categories}" itemLabel="name"/>
    <br />

    <%--<form:label path="categories">--%>
    <%--<spring:message code="question.categories"/>:--%>
    <%--</form:label>--%>
    <%--<form:select path="categories" code="question.categories">--%>
    <%--<form:options/>--%>
    <%--</form:select>--%>
    <br />

    <!---------------------------- BOTONES -------------------------->

    <acme:submit name="save" code="general.save"/>

    <acme:cancel url="welcome/index" code="general.cancel"/>
</form:form>