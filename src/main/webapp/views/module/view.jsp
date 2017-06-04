<%--
  ~ Copyright © 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
  --%>

<%--
  Created by IntelliJ IDEA.
  User: mruwzum
  Date: 1/3/17
  Time: 11:46
  To change this template use File | Settings | File Templates.
--%>
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


<spring:message code="module.title" var="name1"/>
<h3><jstl:out value="${name1}"/></h3>
<jstl:out value="${title}"/>


<spring:message code="module.description" var="name1"/>
<h3><jstl:out value="${name1}"/></h3>
<jstl:out value="${description}"/>


<spring:message code="module.learning" var="name1"/>
<h3 class="highlighted"><jstl:out value="${name1}"/></h3>

<jstl:if test="${my}">
    <security:authorize access="permitAll">

        <a class="button2" href="learningMaterial/create.do?moduleId=${id}"> <spring:message
                code="general.create"/>
        </a>


    </security:authorize>

</jstl:if>

<!-- Listing grid -->
<display:table pagesize="5" class="displaytag" keepStatus="true"
               name="learnings" requestURI="${requestURI}" id="row">


    <!-- Attributes -->
    <jstl:if test="${my}">

        <security:authorize access="permitAll">
            <display:column>
                <a href="learningMaterial/edit.do?learningMaterialId=${row.id}"> <spring:message
                        code="general.edit"/>
                </a>
            </display:column>
        </security:authorize>
    </jstl:if>

    <spring:message code="learning-material.title" var="title"/>
    <display:column property="title" title="${title}" sortable="true"/>
    <spring:message code="learning-material.attachments" var="attachmentsURLs"/>
    <display:column property="attachmentsURLs" title="${attachmentsURLs}" sortable="true"/>

    <jstl:if test="${my}">

        <security:authorize access="permitAll">
            <display:column>
                <a href="learningMaterial/delete.do?learningMaterialId=${row.id}"> <spring:message
                        code="general.delete"/>
                </a>
            </display:column>
        </security:authorize>
    </jstl:if>

</display:table>