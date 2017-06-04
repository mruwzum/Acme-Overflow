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

<!-- Listing grid -->
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
    <security:authorize access="hasRole('USER')">
        <display:column>
            <a href="user/view.do?userId=${row.id}"> <spring:message
                    code="user.view"/>
            </a>
        </display:column>
    </security:authorize>
    <spring:message code="user.name" var="name"/>
    <display:column property="name" title="${name}" sortable="true"/>
    <spring:message code="user.surname" var="surname"/>
    <display:column property="surname" title="${surname}" sortable="true"/>
    <spring:message code="user.email" var="email"/>
    <display:column property="email" title="${email}" sortable="true"/>
    <spring:message code="user.phone" var="phoneNumber"/>
    <display:column property="phoneNumber" title="${phoneNumber}" sortable="true"/>
</display:table>