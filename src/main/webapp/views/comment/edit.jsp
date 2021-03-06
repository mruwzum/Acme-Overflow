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
  ~ Copyright � 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
  --%>

<form:form action="comment/edit.do" modelAttribute="comment">

    <form:hidden path="id"/>
    <form:hidden path="version"/>
    <form:hidden path="owner"/>
    <form:hidden path="webinar"/>
    <form:hidden path="creationDate"/>


    <acme:textbox path="title" code="comment.title"/>
    <br/>
    <acme:textarea path="text" code="comment.text"/>
    <br/>


    <!---------------------------- BOTONES -------------------------->
    <input class="button2" type="submit" name="save"
           value="<spring:message code="general.save" />"/>


    <a class="button" href="/welcome/index.do"><spring:message code="general.cancel"/></a>


</form:form>