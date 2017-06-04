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

<security:authorize access="permitAll">
    <div>
        <H5>
            <a href="other/create.do"> <spring:message
                    code="general.create"/>
            </a>
        </H5>
    </div>
</security:authorize>

<!-- Listing grid -->
<display:table pagesize="5" class="displaytag" keepStatus="true"
               name="others" requestURI="${requestURI}" id="row">


    <!-- Attributes -->

    <security:authorize access="permitAll">
        <display:column>
            <a href="other/edit.do?otherId=${row.id}"> <spring:message
                    code="general.edit"/>
            </a>
        </display:column>
    </security:authorize>

    <spring:message code="other.name" var="title"/>
    <display:column property="name" title="${title}" sortable="true"/>
    <spring:message code="other.surname" var="description"/>
    <display:column property="surname" title="${description}" sortable="true"/>
    <spring:message code="other.email" var="originAddress"/>
    <display:column property="email" title="${originAddress}" sortable="true"/>
    <spring:message code="other.phone" var="destinationAddress"/>
    <display:column property="phone" title="${destinationAddress}" sortable="true"/>


</display:table>