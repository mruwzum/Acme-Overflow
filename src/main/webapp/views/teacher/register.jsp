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


<form:form action="teacher/register.do" modelAttribute="teacher">

    <form:hidden path="id"/>
    <form:hidden path="version"/>


    <acme:textbox path="name" code="user.name"/>
    <acme:textbox path="surname" code="user.surname"/>
    <acme:textbox path="email" code="user.email"/>
    <acme:textbox path="phoneNumber" code="user.phone"/>
    <acme:textbox path="IBAN" code="user.IBAN"/>

    <%--<h3><spring:message code="edit.creditcard" var="cc"/>--%>
    <%--<jstl:out value="${cc}"/>--%>
    <%--</h3>--%>
    <%--<acme:textbox path="creditCard.holder" code="creditCard.holderName"/>--%>
    <%--<br />--%>
    <%--<form:label path="creditCard.brand">--%>
    <%--<spring:message code="creditCard.brandName"/>:--%>
    <%--</form:label>--%>
    <%--<form:select path="creditCard.brand" code="creditCard.brandName">--%>
    <%--<form:options/>--%>
    <%--</form:select>--%>
    <%--<acme:textbox path="creditCard.number" code="creditCard.number"/>--%>
    <%--<br />--%>
    <%--<acme:textbox path="creditCard.ExpirationYear" code="creditCard.expirationYear"/>--%>
    <%--<br />--%>
    <%--<acme:textbox path="creditCard.ExpirationMonth" code="creditCard.expirationMonth"/>--%>
    <%--<br />--%>
    <%--<acme:textbox path="creditCard.CVV" code="creditCard.CVV"/>--%>
    <%--<br />--%>
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
    <form:input path="curricula.hobbiesSection"/>
    <form:errors cssClass="error" path="curricula.hobbiesSection"/>
    <br/>

    <form:label path="curricula.educationSection">
        <spring:message code="curricula.educationSection"/>
    </form:label>
    <form:input path="curricula.educationSection"/>
    <form:errors cssClass="error" path="curricula.educationSection"/>
    <br/>

    <form:label path="curricula.experienceSection">
        <spring:message code="curricula.experienceSection"/>
    </form:label>
    <form:input path="curricula.experienceSection"/>
    <form:errors cssClass="error" path="curricula.experienceSection"/>
    <br/>
    <form:label path="curricula.photo">
        <spring:message code="curricula.photo"/>
    </form:label>
    <form:input path="curricula.photo"/>
    <form:errors cssClass="error" path="curricula.photo"/>
    <br/>
    <!---------------------------- BOTONES -------------------------->
    <input type="submit" name="save"
           value="<spring:message code="user.save" />"/>

    <input type="button" name="cancel"
           value="<spring:message code="user.cancel" />"
           onclick="window.location.replace('question/list.do')"/>
</form:form>