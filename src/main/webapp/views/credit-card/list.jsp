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
            <a href="credit-card/create.do"> <spring:message
                    code="general.create"/>
            </a>
        </H5>
    </div>
</security:authorize>

<!-- Listing grid -->
<display:table pagesize="5" class="displaytag" keepStatus="true"
               name="credit-cards" requestURI="${requestURI}" id="row">


    <!-- Attributes -->

    <security:authorize access="permitAll">
        <display:column>
            <a href="credit-card/edit.do?credit-cardId=${row.id}"> <spring:message
                    code="general.edit"/>
            </a>
        </display:column>
    </security:authorize>

    <spring:message code="credit-card.holderName" var="title"/>
    <display:column property="holderName" title="${title}" sortable="true"/>
    <spring:message code="credit-card.number" var="description"/>
    <display:column property="number" title="${description}" sortable="true"/>
    <spring:message code="credit-card.year" var="originAddress"/>
    <display:column property="year" title="${originAddress}" sortable="true"/>
    <spring:message code="credit-card.month" var="destinationAddress"/>
    <display:column property="month" title="${destinationAddress}" sortable="true"/>
    <spring:message code="credit-card.CVV" var="keyword"/>
    <display:column property="CVV" title="${keyword}" sortable="true"/>

</display:table>