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

<form:form action="mezzage/edit.do" modelAttribute="mezzage">

    <form:hidden path="id"/>
    <form:hidden path="version"/>
    <form:hidden path="sender"/>
    <form:hidden path="sendDate"/>
    <form:hidden path="folder"/>
    <form:hidden path="receiver"/>
    <%--<form:hidden path="senderEmail" />--%>
    <form:hidden path="senderEmail"/>


    <acme:textbox path="receiverEmail" code="mezzage.receiver"/>
    <br/>

    <acme:textbox path="subject" code="mezzage.subject"/>
    <br/>

    <acme:textarea path="body" code="mezzage.body"/>
    <br/>


    <form:label path="priority">
        <spring:message code="mezzage.priority"/>:
    </form:label>
    <form:select path="priority" code="mezzage.priority">
        <form:options/>
    </form:select>
    <br/>


    <!---------------------------- BOTONES -------------------------->


    <button name="save" type="submit" class="button2">
        <spring:message code="general.send"/>
    </button>
    <button name="cancel" type="reset" class="button">
        <spring:message code="general.empty"/>
    </button>

    <a class="button" href="/folder/list.do"><spring:message code="general.cancel"/></a>


</form:form>