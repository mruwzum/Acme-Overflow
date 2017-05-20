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


<form:form action="message/edit.do" modelAttribute="message">

    <form:hidden path="id" />
    <form:hidden path="version" />
    <form:hidden path="sender" />
    <form:hidden path="sendDate" />
    <form:hidden path="folder" />





    <acme:textbox path="receiver" code="message.receiver"/>
    <br />
    <acme:select path="receiver" code="message.receiver" items="${users}" itemLabel="name"/>
    <br/>

    <acme:textbox path="subject" code="message.subject"/>
    <br />

    <acme:textarea path="body" code="message.body"/>
    <br/>

    <form:label path="priority">
        <spring:message code="message.priority"/>:
    </form:label>
    <form:select path="priority" code="message.priority">
        <form:options/>
    </form:select>
    <br />


    <!---------------------------- BOTONES -------------------------->

    <acme:submit name="save" code="general.save"/>

    <jstl:if test="\$\{message.id != 0}">
        <input type="submit" name="delete"
               value="<spring:message code="general.delete" />"
               onclick="return confirm('<spring:message code="general.confirm.delete" />')" />&nbsp;
    </jstl:if>
    <acme:cancel url="message/list.do" code="general.cancel"/>


</form:form>