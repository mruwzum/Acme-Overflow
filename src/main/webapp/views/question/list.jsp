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

<security:authorize access="permitAll">
    <div>
        <H5>
            <a href="question/create.do"> <spring:message
                    code="general.create" />
            </a>
        </H5>
    </div>
</security:authorize>

<!-- Listing grid -->
<display:table pagesize="5" class="displaytag" keepStatus="true"
               name="questions" requestURI="${requestURI}" id="row">


    <!-- Attributes -->

    <security:authorize access="permitAll">
        <display:column>
            <a href="question/edit.do?questionId=${row.id}"> <spring:message
                    code="general.edit" />
            </a>
        </display:column>
    </security:authorize>

    <spring:message code="question.title" var="title" />
    <display:column property="title" title="${title}" sortable="true" />
    <spring:message code="question.summary" var="description" />
    <display:column property="description" title="${description}" sortable="true" />
    <spring:message code="question.createdDate" var="originAddress" />
    <display:column property="originAddress" title="${originAddress}" sortable="true" />


</display:table>