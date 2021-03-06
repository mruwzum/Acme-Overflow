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

<form:form action="curricula/edit.do" modelAttribute="curricula">

    <form:hidden path="id"/>
    <form:hidden path="version"/>

    <acme:textbox path="title" code="curricula.photo"/>
    <br/>
    <acme:textarea path="educationSection" code="curricula.educationSection"/>
    <br/>
    <acme:textarea path="experienceSection" code="curricula.experienceSection"/>
    <br/>
    <acme:textarea path="referencias" code="curricula.referencias"/>
    <br/>
    <acme:textarea path="hobbiesSection" code="curricula.hobbiesSection"/>
    <br/>
    <!---------------------------- BOTONES -------------------------->

    <acme:submit name="save" code="general.save"/>

    <jstl:if test="\$\{curricula.id != 0}">
        <input type="submit" name="delete"
               value="<spring:message code="general.delete" />"
               onclick="return confirm('<spring:message code="general.confirm.delete"/>')"/>&nbsp;
    </jstl:if>
    <acme:cancel url="curricula/list.do" code="general.cancel"/>


</form:form>