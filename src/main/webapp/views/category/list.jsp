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

<security:authorize access="hasRole('ADMIN')">
    <div>
        <H5>
            <a href="category/create.do"> <spring:message
                    code="general.create" />
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
            <a href="category/edit.do?categoryId=${row.id}"> <spring:message
                    code="general.edit" />
            </a>
        </display:column>
    </security:authorize>

    <spring:message code="category.name" var="title" />
    <display:column property="name" title="${title}" sortable="true" />
    <spring:message code="category.description" var="description" />
    <display:column property="description" title="${description}" sortable="true" />

    <security:authorize access="hasRole('ADMIN')">
        <display:column>
            <a href="category/delete.do?categoryId=${row.id}"> <spring:message
                    code="general.delete" />
            </a>
        </display:column>
    </security:authorize>
</display:table>