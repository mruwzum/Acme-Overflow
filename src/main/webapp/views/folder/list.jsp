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

<security:authorize access="hasAnyRole('ADMIN','USER','TEACHER','MODERATOR')">
    <div>
        <H5>
            <a href="folder/create.do"> <spring:message
                    code="general.create" />
            </a>
        </H5>
    </div>
</security:authorize>

<!-- Listing grid -->
<display:table pagesize="5" class="displaytag" keepStatus="true"
               name="folders" requestURI="${requestURI}" id="row">


    <!-- Attributes -->

    <security:authorize access="hasAnyRole('ADMIN','USER','TEACHER','MODERATOR')">
        <display:column>
            <a href="folder/edit.do?folderId=${row.id}"> <spring:message
                    code="general.edit" />
            </a>
        </display:column>
        <display:column>
            <a href="folder/view.do?folderId=${row.id}"> <spring:message
                    code="general.view"/>
            </a>
        </display:column>
    </security:authorize>

    <spring:message code="folder.name" var="title" />
    <display:column property="name" title="${title}" sortable="true" />

</display:table>