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
  ~ Copyright � 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
  --%>

<security:authorize access="hasRole('ADMIN')">
    <div>
        <H5>
            <a href="evaluationQuestion/create.do"> <spring:message
                    code="general.create"/>
            </a>
        </H5>
    </div>
</security:authorize>

<!-- Listing grid -->
<display:table pagesize="5" class="displaytag" keepStatus="true"
               name="categories" requestURI="${requestURI}" id="row">


    <!-- Attributes -->

    <security:authorize access="hasRole('ADMIN')">
        <display:column>
            <a href="evaluationQuestion/edit.do?evaluationQuestionId=${row.id}"> <spring:message
                    code="general.edit"/>
            </a>
        </display:column>
    </security:authorize>

    <spring:message code="evaluationq.title" var="title"/>
    <display:column property="title" title="${title}" sortable="true"/>

    <security:authorize access="hasRole('ADMIN')">
        <display:column>
            <a href="evaluationQuestion/delete.do?evaluationQuestionId=${row.id}"> <spring:message
                    code="general.delete"/>
            </a>
        </display:column>
    </security:authorize>
</display:table>