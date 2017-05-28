<%--
  Created by IntelliJ IDEA.
  User: mruwzum
  Date: 19/12/16
  Time: 23:24
  To change this template use File | Settings | File Templates.
--%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<br>
<spring:message code="dashboard.q1" var="q1b"/>
<jstl:out value="${q1b}"/>:
<jstl:out value="${q1}"/>
<br/>
<br>
<spring:message code="dashboard.q2" var="q2b"/>
<jstl:out value="${q2b}"/>:
<jstl:out value="${q2}"/>
<br>
<br/>
<spring:message code="dashboard.q3" var="q3b"/>
<jstl:out value="${q3b}"/>:
<jstl:out value="${q3}"/>
<br>
<br/>
<spring:message code="dashboard.q4" var="q4b"/>
<jstl:out value="${q4b}"/>:
<display:table pagesize="10" class="displaytag" keepStatus="true"
               name="q4" requestURI="${requestURI}" id="row">


    <spring:message code="webinar.name" var="title" />
    <display:column property="name" title="${title}" sortable="true" />
    <spring:message code="webinar.description" var="description" />
    <display:column property="description" title="${description}" sortable="true" />


</display:table><br>
<br/>
<spring:message code="dashboard.q5" var="q5b"/>
<jstl:out value="${q5b}"/>:
<display:table pagesize="15" class="displaytag" keepStatus="true"
               name="q5" requestURI="${requestURI}" id="row">


    <spring:message code="user.name" var="name"/>
    <display:column property="name" title="${name}" sortable="true"/>
    <spring:message code="user.surname" var="surname"/>
    <display:column property="surname" title="${surname}" sortable="true"/>

</display:table><br>
<br/>



