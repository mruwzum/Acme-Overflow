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
            <a href="mezzage/create.do"> <spring:message
                    code="general.create" />
            </a>
        </H5>
    </div>
</security:authorize>

<!-- Listing grid -->
<display:table pagesize="200" class="displaytag" keepStatus="true"
               name="mezzages" requestURI="${requestURI}" id="row">


    <!-- Attributes -->

    <security:authorize access="hasAnyRole('ADMIN','USER','TEACHER','MODERATOR')">
        <display:column>
            <a href="mezzage/view.do?mezzageId=${row.id}"> <spring:message
                    code="general.view"/>
            </a>
        </display:column>
        <display:column>
            <a href="mezzage/delete.do?mezzageId=${row}"> <spring:message
                    code="general.delete"/>
            </a>
        </display:column>
    </security:authorize>

    <spring:message code="mezzage.subject" var="subject"/>
    <display:column property="subject" title="${subject}" sortable="true"/>
    <spring:message code="mezzage.body" var="body"/>
    <display:column property="body" title="${body}" sortable="true"/>
    <spring:message code="mezzage.sendDate" var="sendDate"/>
    <display:column property="sendDate" title="${sendDate}" sortable="true"/>
    <spring:message code="mezzage.sender" var="sender"/>
    <display:column property="sender" title="${sender}" sortable="true"/>
    <spring:message code="mezzage.receiver" var="receiver"/>
    <display:column property="receiver" title="${receiver}" sortable="true"/>
    <spring:message code="mezzage.priority" var="priority"/>
    <display:column property="priority" title="${priority}" sortable="true"/>

</display:table>