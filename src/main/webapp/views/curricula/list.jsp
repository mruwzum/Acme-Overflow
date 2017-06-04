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
<display:table pagesize="5" class="displaytag" keepStatus="true"
               name="curriculas" requestURI="${requestURI}" id="row">


    <!-- Attributes -->

    <security:authorize access="hasRole('ADMIN')">
        <display:column>
            <jstl:if test="${not row.approbed}">
                <a href="curricula/approbe.do?curriculaId=${row.id}"> <spring:message
                        code="user.approbe"/>
                </a>
            </jstl:if>
            <jstl:if test="${row.approbed}">
                <a href="curricula/unapprobe.do?curriculaId=${row.id}"> <spring:message
                    code="user.unapprobe"/>
            </jstl:if>
        </display:column>
    </security:authorize>
    <spring:message code="curricula.photo" var="photo"/>
    <display:column property="photo" title="${photo}" sortable="false"/>
    <spring:message code="curricula.educationSection" var="educationSection"/>
    <display:column property="educationSection" title="${educationSection}" sortable="true"/>
    <spring:message code="curricula.experienceSection" var="experienceSection"/>
    <display:column property="experienceSection" title="${experienceSection}" sortable="true"/>
    <spring:message code="curricula.referencias" var="referencias"/>
    <display:column property="referencias" title="${referencias}" sortable="true"/>
    <spring:message code="curricula.hobbiesSection" var="hobbiesSection"/>
    <display:column property="hobbiesSection" title="${hobbiesSection}" sortable="true"/>
    <spring:message code="curricula.approbed" var="approbed"/>
    <display:column property="approbed" title="${approbed}" sortable="true"/>
</display:table>