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

<security:authorize access="hasRole('TEACHER')">
    <div>
        <H5>
            <a href="webinar/create.do"> <spring:message
                    code="general.create"/>
            </a>
        </H5>
    </div>
</security:authorize>


<security:authorize access="isAnonymous()">
    <div>
        <H5>
            <spring:message code="youshouldregister" var="yoShouldReg"/>
            <h3><jstl:out value="${yoShouldReg}"/></h3>
        </H5>
    </div>
</security:authorize>

<!-- Listing grid -->
<display:table pagesize="10" class="displaytag" keepStatus="true"
               name="webinars" requestURI="${requestURI}" id="row">


    <!-- Attributes -->
    <security:authorize access="hasAnyRole('USER','TEACHER')">
        <display:column>
            <a href="webinar/view.do?webinarId=${row.id}"> <spring:message
                    code="general.view"/>
            </a>

        </display:column>
    </security:authorize>
    <spring:message code="webinar.name" var="title"/>
    <display:column property="name" title="${title}" sortable="true"/>
    <spring:message code="webinar.description" var="description"/>
    <display:column property="description" title="${description}" sortable="true"/>
    <spring:message code="webinar.startDate" var="originAddress"/>
    <display:column property="startDate" title="${originAddress}" sortable="true"/>
    <spring:message code="webinar.price" var="destinationAddress"/>
    <display:column property="price" title="${destinationAddress}" sortable="true"/>
    <spring:message code="webinar.categories" var="categories"/>
    <display:column property="categories" title="${categories}" sortable="true"/>

    <jstl:if test="${my}">
        <display:column>
            <a href="webinar/edit.do?webinarId=${row.id}"> <spring:message
                    code="general.edit"/>
            </a>
        </display:column>
        <display:column>
            <a href="webinar/delete.do?webinarId=${row.id}"
               onclick="return confirm('<spring:message code="general.confirm.delete"/>')">
                <spring:message code="general.delete"/>
            </a>
        </display:column>
        <display:column>
            <a href="webinar/broadcastMessage.do?webinarId=${row.id}"> <spring:message
                    code="general.broadcastMessage"/>
            </a>
        </display:column>
    </jstl:if>
</display:table>
