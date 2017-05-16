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
            <a href="message/create.do"> <spring:message
                    code="general.create" />
            </a>
        </H5>
    </div>
</security:authorize>

<!-- Listing grid -->
<display:table pagesize="5" class="displaytag" keepStatus="true"
               name="messages1" requestURI="${requestURI}" id="row">


    <!-- Attributes -->

    <security:authorize access="permitAll">
        <display:column>
            <a href="message/edit.do?messageId=${row.id}"> <spring:message
                    code="general.edit" />
            </a>
        </display:column>
    </security:authorize>

    <spring:message code="message.subject" var="title" />
    <display:column property="subject" title="${title}" sortable="true" />
    <spring:message code="message.body" var="description" />
    <display:column property="body" title="${description}" sortable="true" />
    <spring:message code="message.sendDate" var="originAddress" />
    <display:column property="sendDate" title="${originAddress}" sortable="true" />
    <spring:message code="message.sender" var="destinationAddress" />
    <display:column property="sender" title="${destinationAddress}" sortable="true" />
    <spring:message code="message.receiver" var="keyword" />
    <display:column property="receiver" title="${keyword}" sortable="true" />
    <spring:message code="message.folder" var="keyword" />
    <display:column property="folder" title="${keyword}" sortable="true" />

</display:table>