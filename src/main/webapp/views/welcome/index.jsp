<%--
 * index.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--<p><spring:message code="welcome.greeting.prefix" /> ${name}<spring:message code="welcome.greeting.suffix" /></p>--%>

<%--<p><spring:message code="welcome.greeting.current.time" /> ${moment}</p>--%>


<security:authorize access="hasRole('BAN')">
    <spring:message code="actor.banneda" var="suc"/>
    <h1><jstl:out value="${suc}"/></h1>


</security:authorize>

<security:authorize access="hasRole('USER')">
        <a class="button2" href="question/create.do"> <spring:message
                code="question.create" />
        </a>
</security:authorize>



<c:forEach items="${questions}" var="question">
    <h3 class="highlighted2">${question.title}</h3>
    ${question.summary}
    <br>
    <security:authorize access="hasAnyRole('USER','TEACHER')">
        <a class="button" href="question/view.do?questionId=${question.id}"> <spring:message
                code="general.view"/>
        </a>
    </security:authorize>

    <security:authorize access="hasRole('MODERATOR')">
            <a href="question/view.do?questionId=${question.id}"> <spring:message
                    code="general.view"/>
            </a>
    </security:authorize>
    <security:authorize access="hasRole('MODERATOR')">
            <jstl:if test="${not row.banned}">
                <a href="question/ban.do?questionId=${question.id}"> <spring:message
                        code="user.ban"/>
                </a>
            </jstl:if>
            <jstl:if test="${row.banned}">
                <a href="question/unban.do?questionId=${question.id}"> <spring:message
                    code="user.unban"/>
            </jstl:if>
    </security:authorize>
    <hr>




</c:forEach>



<%--<display:table pagesize="25" class="displaytag" keepStatus="true"--%>
               <%--name="questions" requestURI="${requestURI}" id="row">--%>


    <%--<!-- Attributes -->--%>


    <%--<spring:message code="question.title" var="title" />--%>
    <%--<display:column property="title" title="${title}" sortable="true" />--%>
    <%--<spring:message code="question.summary" var="description" />--%>
    <%--<display:column property="summary" title="${description}" sortable="true" />--%>
    <%--<spring:message code="question.createdDate" var="originAddress" />--%>
    <%--<display:column property="createdDate" title="${originAddress}" sortable="true" />--%>
    <%--<security:authorize access="isAnonymous()">--%>
        <%--<display:column>--%>
            <%--<a href="question/viewAn.do?questionId=${row.id}"> <spring:message--%>
                    <%--code="question.view"/>--%>
            <%--</a>--%>
        <%--</display:column>--%>
    <%--</security:authorize>--%>
    <%--<security:authorize access="hasAnyRole('USER','TEACHER')">--%>
        <%--<display:column>--%>
            <%--<a href="question/view.do?questionId=${row.id}"> <spring:message--%>
                    <%--code="general.view"/>--%>
            <%--</a>--%>
        <%--</display:column>--%>
    <%--</security:authorize>--%>
    <%--<security:authorize access="hasRole('MODERATOR')">--%>
        <%--<display:column>--%>
            <%--<a href="question/view.do?questionId=${row.id}"> <spring:message--%>
                    <%--code="general.view"/>--%>
            <%--</a>--%>
        <%--</display:column>--%>
    <%--</security:authorize>--%>
    <%--<security:authorize access="hasRole('MODERATOR')">--%>
        <%--<display:column>--%>
            <%--<jstl:if test="${not row.banned}">--%>
                <%--<a href="question/ban.do?questionId=${row.id}"> <spring:message--%>
                        <%--code="user.ban"/>--%>
                <%--</a>--%>
            <%--</jstl:if>--%>
            <%--<jstl:if test="${row.banned}">--%>
                <%--<a href="question/unban.do?questionId=${row.id}"> <spring:message--%>
                    <%--code="user.unban"/>--%>
            <%--</jstl:if>--%>
        <%--</display:column>--%>
    <%--</security:authorize>--%>
<%--</display:table>--%>

    <br>

<br/>
<security:authorize access="isAnonymous()">
    ${banner}
</security:authorize>