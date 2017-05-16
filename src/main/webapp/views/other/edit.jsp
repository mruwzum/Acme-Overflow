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

TODO hacer aqui la vista de edición completa de usuarios

<form:form action="finder/find.do" modelAttribute="finder">

    <form:hidden path="id" />
    <form:hidden path="version" />

    <acme:textbox path="title" code="finder.title"/>
    <br />

    <!---------------------------- BOTONES -------------------------->

    <acme:submit name="save" code="finder.save"/>

    <jstl:if test="\$\{finder.id != 0}">
        <input type="submit" name="delete"
               value="<spring:message code="finder.delete" />"
               onclick="return confirm('<spring:message code="finder.confirm.delete" />')" />&nbsp;
    </jstl:if>
    <acme:cancel url="finder/list.do" code="finder.cancel"/>


</form:form>