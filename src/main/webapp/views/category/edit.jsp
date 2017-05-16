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


<form:form action="category/edit.do" modelAttribute="category">

    <form:hidden path="id" />
    <form:hidden path="version" />

    <acme:textbox path="name" code="category.name"/>
    <br />
    <acme:textbox path="description" code="category.description"/>
    <br />

    <!---------------------------- BOTONES -------------------------->

    <acme:submit name="save" code="general.save"/>

    <jstl:if test="\$\{category.id != 0}">
        <input type="submit" name="delete"
               value="<spring:message code="general.delete" />"
               onclick="return confirm('<spring:message code="general.confirm.delete" />')" />&nbsp;
    </jstl:if>
    <acme:cancel url="category/list.do" code="general.cancel"/>


</form:form>