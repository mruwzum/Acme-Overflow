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


<!-- Listing grid -->
<display:table pagesize="5" class="displaytag" keepStatus="true"
               name="bills" requestURI="${requestURI}" id="row">


    <!-- Attributes -->



    <spring:message code="bill.number" var="number" />
    <display:column property="number" title="${number}" sortable="true" />
    <spring:message code="bill.value" var="value" />
    <display:column property="value" title="${value}" sortable="true" />
    <spring:message code="bill.webinar" var="webinar" />
    <display:column property="webinar" title="${webinar}" sortable="true" />



    <security:authorize access="hasRole('USER')">
        <display:column>
            <a href="bill/view.do?billId=${row.id}"> <spring:message
                    code="general.view" />
            </a>
        </display:column>
    </security:authorize>

</display:table>


<spring:message code="bill.totalToPay" var="name1"/>
<h3><jstl:out value="${name1}"/></h3>
<jstl:out value="${total}"/>