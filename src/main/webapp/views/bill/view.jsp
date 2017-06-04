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
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>


<%--
  ~ Copyright © 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
  --%>

<spring:message code="bill.number" var="name1"/>
<h3><jstl:out value="${name1}"/></h3>
<jstl:out value="${number}"/>


<spring:message code="bill.value" var="name1"/>
<h3><jstl:out value="${name1}"/></h3>
<jstl:out value="${value}"/>

<spring:message code="bill.webinar" var="name1"/>
<h3><jstl:out value="${name1}"/></h3>
<jstl:out value="${webinar}"/>

<spring:message code="bill.owner" var="name1"/>
<h3><jstl:out value="${name1}"/></h3>
<jstl:out value="${owner}"/>

<br>
<a class="button" href="/bill/list.do"><spring:message code="general.cancel"/></a>
