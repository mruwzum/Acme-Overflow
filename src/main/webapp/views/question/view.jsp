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


<spring:message code="question.title" var="title1"/>
<h3><jstl:out value="${title1}"/></h3>
<jstl:out value="${title}"/>


<spring:message code="question.summary" var="summary1"/>
<h3><jstl:out value="${summary1}"/></h3>
<jstl:out value="${summary}"/>


<spring:message code="question.owner" var="owner1"/>
<h3><jstl:out value="${owner1}"/></h3>
<jstl:out value="${owner}"/>

<spring:message code="general.catego" var="catego11"/>
<h3><jstl:out value="${catego11}"/></h3>

<spring:message code="question.categorie" var="cagetogie1"/>
<h3><jstl:out value="${categorie1}"/></h3>
<jstl:out value="${categorie}"/>

<spring:message code="general.answers" var="register11"/>
<h3><jstl:out value="${register11}"/></h3>
<!-- Listing grid socialIdentities -->
<display:table pagesize="5" class="displaytag" keepStatus="true"
               name="answers" requestURI="${requestURI}" id="row">


    <!-- Attributes -->
    <security:authorize access="hasAnyRole('USER','TEACHER','MODERATOR')">
        <display:column>
            <a href="question/edit.do?questionId=${row.id}"> <spring:message
                    code="general.edit" />
            </a>
        </display:column>
    </security:authorize>
    <security:authorize access="hasAnyRole('ADMIN','MODERATOR')">
        <display:column>
            <jstl:if test="${not row.banned}">
                <a href="answer/ban.do?answerId=${row.id}"> <spring:message
                        code="user.ban"/>
                </a>
            </jstl:if>
            <jstl:if test="${row.banned}">
                <a href="answer/unban.do?answerId=${row.id}"> <spring:message
                    code="user.unban"/>
            </jstl:if>
        </display:column>
    </security:authorize>
    <spring:message code="answer.title" var="title"/>
    <display:column property="title" title="${title}" sortable="true"/>
    <spring:message code="answer.description" var="description"/>
    <display:column property="description" title="${description}" sortable="true"/>
    <spring:message code="answer.likes" var="likes"/>
    <display:column property="likes" title="${likes}" sortable="true"/>
    <spring:message code="answer.dislikes" var="dislikes"/>
    <display:column property="dislikes" title="${dislikes}" sortable="true"/>
</display:table>


<security:authorize access="hasAnyRole('USER','TEACHER')">
    <div>
        <H5>
            <a href="answer/create.do?questionId=${questionId}"> <spring:message
                    code="answer.create"/>
            </a>
        </H5>
    </div>
</security:authorize>

