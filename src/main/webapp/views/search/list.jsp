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
            <a href="search/create.do"> <spring:message
                    code="general.create"/>
            </a>
        </H5>
    </div>
</security:authorize>

<!-- Listing grid -->
<display:table pagesize="15" class="displaytag" keepStatus="true"
               name="searches" requestURI="${requestURI}" id="row">


    <!-- Attributes -->

    <security:authorize access="permitAll">
        <display:column>
            <a href="search/edit2.do?searchId=${row.id}"> <spring:message
                    code="search.view.result"/>
            </a>
        </display:column>
    </security:authorize>

    <spring:message code="search.keyword" var="title"/>
    <display:column property="keyword" title="${title}" sortable="true"/>
    <spring:message code="search.category" var="description"/>
    <display:column property="category" title="${description}" sortable="true"/>


</display:table>