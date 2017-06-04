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

<form:form action="teacher/register.do" modelAttribute="teacher">

    <form:hidden path="id"/>
    <form:hidden path="version"/>


    <acme:textbox path="name" code="user.name"/>
    <acme:textbox path="surname" code="user.surname"/>
    <acme:textbox path="email" code="user.email"/>
    <acme:textbox path="phoneNumber" code="user.phone"/>
    <acme:textbox path="IBAN" code="user.IBAN"/>


    <br/>
    <h1>User Account</h1>
    <br>
    <form:label path="userAccount.username">
        <spring:message code="user.username"/>:
    </form:label>
    <form:input path="userAccount.username"/>
    <form:errors cssClass="error" path="UserAccount.username"/>
    <br/>
    <br>
    <form:label path="userAccount.password">
        <spring:message code="user.password"/>:
    </form:label>
    <form:password path="userAccount.password"/>
    <form:errors cssClass="error" path="UserAccount.password"/>
    <br/>
    <br/>
    <h1>Curriculum</h1>
    <form:label path="curricula.hobbiesSection">
        <spring:message code="curricula.hobbiesSection"/>
    </form:label>
    <form:textarea path="curricula.hobbiesSection"/>
    <form:errors cssClass="error" path="curricula.hobbiesSection"/>
    <br/>

    <form:label path="curricula.educationSection">
        <spring:message code="curricula.educationSection"/>
    </form:label>
    <form:textarea path="curricula.educationSection"/>
    <form:errors cssClass="error" path="curricula.educationSection"/>
    <br/>

    <form:label path="curricula.experienceSection">
        <spring:message code="curricula.experienceSection"/>
    </form:label>
    <form:textarea path="curricula.experienceSection"/>
    <form:errors cssClass="error" path="curricula.experienceSection"/>
    <br/>
    <form:label path="curricula.photo">
        <spring:message code="curricula.photo"/>
    </form:label>
    <form:input path="curricula.photo"/>
    <form:errors cssClass="error" path="curricula.photo"/>
    <br/>
    <br/>

    <spring:message code="terms" var="terms11"/>
    <h3></h3>
    <a href="http://ourdisclaimer.com/?i=AcmeOverflow.,Inc."><jstl:out value="${terms11}"/></a>

    <br/>
    <br/>
    <!---------------------------- BOTONES -------------------------->
    <input class="button2" type="submit" name="save"
           value="<spring:message code="user.save" />"/>

    <a class="button" href="/welcome/index.do"><spring:message code="general.cancel"/></a>

</form:form>