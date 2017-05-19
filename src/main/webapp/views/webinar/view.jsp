<%--
  Created by IntelliJ IDEA.
  User: mruwzum
  Date: 1/3/17
  Time: 11:46
  To change this template use File | Settings | File Templates.
--%>
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


<security:authorize access="hasRole('USER')">


    <jstl:if test="${reg}">

        <a href="webinar/unregister.do?webinarId=${webinarId}"> <spring:message
                code="general.unregister"/>
        </a>


    </jstl:if>


    <jstl:if test="${not reg}">

        <a href="webinar/register.do?webinarId=${webinarId}"> <spring:message
                code="general.register"/>
        </a>


    </jstl:if>


</security:authorize>


<spring:message code="webinar.name" var="name1"/>
<h3><jstl:out value="${name1}"/></h3>
<jstl:out value="${name}"/>


<spring:message code="webinar.description" var="description1"/>
<h3><jstl:out value="${description1}"/></h3>
<jstl:out value="${description}"/>


<spring:message code="webinar.startDate" var="startDate1"/>
<h3><jstl:out value="${startDate1}"/></h3>
<jstl:out value="${startDate}"/>

<spring:message code="webinar.price" var="price1"/>
<h3><jstl:out value="${price1}"/></h3>
<jstl:out value="${price}"/>
<spring:message code="general.catego" var="catego11"/>
<h3><jstl:out value="${catego11}"/></h3>

<spring:message code="webinar.categories" var="cagetogies1"/>
<h3><jstl:out value="${categories1}"/></h3>
<jstl:out value="${categories}"/>
<br/>

<spring:message code="general.comments" var="register11"/>
<h3><jstl:out value="${register11}"/></h3>

<security:authorize access="hasAnyRole('USER','TEACHER')">
    <a href="comment/create.do?webinarId=${webinarId}"> <spring:message
                code="general.create"/>
        </a>

</security:authorize>
<br/>
<!-- Listing grid comments -->
<display:table pagesize="5" class="displaytag" keepStatus="true"
               name="comments" requestURI="${requestURI}" id="row">
    <!-- Attributes -->
    <security:authorize access="hasAnyRole('USER','TEACHER','MODERATOR')">
        <display:column>
            <a href="comment/edit.do?commentId=${row.id}"> <spring:message
                    code="general.edit"/>
            </a>
        </display:column>
    </security:authorize>

    <spring:message code="comment.title" var="title"/>
    <display:column property="title" title="${title}" sortable="true"/>
    <spring:message code="comment.text" var="text"/>
    <display:column property="text" title="${text}" sortable="true"/>
    <spring:message code="comment.creationDate" var="creationDate"/>
    <display:column property="creationDate" title="${creationDate}" sortable="true"/>
    <spring:message code="comment.owner" var="owner"/>
    <display:column property="owner" title="${owner}" sortable="true"/>
</display:table>

<spring:message code="general.registeredUsers" var="register112"/>
<h3><jstl:out value="${register112}"/></h3>
<display:table pagesize="15" class="displaytag" keepStatus="true"
               name="users" requestURI="${requestURI}" id="row">
    <!-- Attributes -->
    <security:authorize access="hasAnyRole('ADMIN','MODERATOR')">
        <display:column>
            <jstl:if test="${not row.banned}">
                <a href="user/ban.do?userId=${row.id}"> <spring:message
                        code="user.ban"/>
                </a>
            </jstl:if>
            <jstl:if test="${row.banned}">
                <a href="user/unban.do?userId=${row.id}"> <spring:message
                    code="user.unban"/>
            </jstl:if>
        </display:column>
    </security:authorize>

    <spring:message code="user.name" var="name"/>
    <display:column property="name" title="${name}" sortable="true"/>
    <spring:message code="user.surname" var="surname"/>
    <display:column property="surname" title="${surname}" sortable="true"/>
    <spring:message code="user.email" var="email"/>
    <display:column property="email" title="${email}" sortable="true"/>
</display:table>
