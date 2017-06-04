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

<%--
  ~ Copyright © 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
  --%>

<security:authorize access="hasRole('USER')">
    <div>
        <H5>
            <a href="question/create.do"> <spring:message
                    code="general.create"/>
            </a>
        </H5>
    </div>
</security:authorize>
<spring:message code="general.questions" var="register1111"/>
<h3><jstl:out value="${register1111}"/></h3>
<!-- Listing grid -->
<display:table pagesize="100" class="displaytag" keepStatus="true"
               name="questions" requestURI="${requestURI}" id="row">


    <!-- Attributes -->
    <security:authorize access="isAnonymous()">
        <display:column>
            <a href="question/viewAn.do?questionId=${row.id}"> <spring:message
                    code="question.view"/>
            </a>
        </display:column>
    </security:authorize>
    <security:authorize access="hasAnyRole('USER','TEACHER')">
        <display:column>
            <a href="question/view.do?questionId=${row.id}"> <spring:message
                    code="general.view"/>
            </a>
        </display:column>
    </security:authorize>
    <security:authorize access="hasRole('MODERATOR')">
        <display:column>
            <a href="question/view.do?questionId=${row.id}"> <spring:message
                    code="general.view"/>
            </a>
        </display:column>
    </security:authorize>
    <security:authorize access="hasRole('MODERATOR')">
        <display:column>
            <jstl:if test="${not row.banned}">
                <a href="question/ban.do?questionId=${row.id}"> <spring:message
                        code="user.ban"/>
                </a>
            </jstl:if>
            <jstl:if test="${row.banned}">
                <a href="question/unban.do?questionId=${row.id}"> <spring:message
                    code="user.unban"/>
            </jstl:if>
        </display:column>
    </security:authorize>
    <spring:message code="question.title" var="title"/>
    <display:column property="title" title="${title}" sortable="true"/>
    <spring:message code="question.summary" var="description"/>
    <display:column property="summary" title="${description}" sortable="true"/>
    <spring:message code="question.createdDate" var="originAddress"/>
    <display:column property="createdDate" title="${originAddress}" sortable="true"/>


</display:table>
<spring:message code="general.myQuestions" var="register11"/>
<h3><jstl:out value="${register11}"/></h3>
<display:table pagesize="100" class="displaytag" keepStatus="true"
               name="myquestions" requestURI="${requestURI}" id="row">


    <!-- Attributes -->

    <security:authorize access="hasRole('USER')">
        <display:column>
            <a href="question/edit.do?questionId=${row.id}"> <spring:message
                    code="general.edit"/>
            </a>
        </display:column>
    </security:authorize>
    <security:authorize access="hasRole('USER')">
        <display:column>
            <a href="question/delete.do?questionId=${row.id}"> <spring:message
                    code="general.delete"/>
            </a>
        </display:column>
    </security:authorize>
    <security:authorize access="hasRole('USER')">
        <display:column>
            <a href="question/view.do?questionId=${row.id}"> <spring:message
                    code="general.view"/>
            </a>
        </display:column>
    </security:authorize>
    <security:authorize access="hasAnyRole('ADMIN','MODERATOR')">
        <display:column>
            <jstl:if test="${not row.banned}">
                <a href="question/ban.do?questionId=${row.id}"> <spring:message
                        code="user.ban"/>
                </a>
            </jstl:if>
            <jstl:if test="${row.banned}">
                <a href="question/unban.do?questionId=${row.id}"> <spring:message
                    code="user.unban"/>
            </jstl:if>
        </display:column>
    </security:authorize>
    <spring:message code="question.title" var="title"/>
    <display:column property="title" title="${title}" sortable="true"/>
    <spring:message code="question.summary" var="description"/>
    <display:column property="summary" title="${description}" sortable="true"/>
    <spring:message code="question.createdDate" var="originAddress"/>
    <display:column property="createdDate" title="${originAddress}" sortable="true"/>


</display:table>