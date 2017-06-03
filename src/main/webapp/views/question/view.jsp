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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<security:authorize access="isAnonymous()">
    <div>
        <H5>
            <spring:message code="youshouldregister" var="yoShouldReg"/>
            <h3><jstl:out value="${yoShouldReg}"/></h3>
        </H5>
    </div>
</security:authorize>
<div id="wrapper">

    <div id="sidebar">

        <security:authorize access="isAuthenticated()">

            <spring:message code="question.related" var="rel"/>
            <h1><jstl:out value="${rel}"/></h1>

            <c:forEach items="${webinars}" var="webinar">

                <img src="${webinar.picture}" width="190px" height="100%" alt="webinar_picture"/>
                <br>


                <a href="/webinar/view.do?webinarId=${webinar.id}"><h3><jstl:out value="${webinar.name}"/></h3></a>


                <jstl:out value="${webinar.description}"/>
                <br>


            </c:forEach>
        </security:authorize>

    </div>

    <div id="content">

        <%--<spring:message code="question.title" var="title1"/>--%>
        <%--<h3><jstl:out value="${title1}"/></h3>--%>
        <h2><jstl:out value="${title}"/></h2>


        <%--<spring:message code="question.summary" var="summary1"/>--%>
        <%--<h3><jstl:out value="${summary1}"/></h3>--%>
        <jstl:out value="${summary}"/>


        <spring:message code="question.owner" var="owner1"/>
        <h3><jstl:out value="${owner1}"/></h3>
        <jstl:out value="${owner}"/>

        <spring:message code="general.catego" var="catego11"/>
        <h3><jstl:out value="${catego11}"/></h3>

        <spring:message code="question.categorie" var="cagetogie1"/>
        <h3><jstl:out value="${categorie1}"/></h3>
        <jstl:out value="${categorie}"/>

        <spring:message code="general.answers" var="register11"/>
        <h3 class="highlighted"><jstl:out value="${register11}"/></h3>
        <!-- Listing grid socialIdentities -->


        <c:forEach items="${answers}" var="answer">

            <jstl:if test="${answer.teacher}">

                <div class="highlighted3">

                    <h3 class="highlighted2">${answer.title}</h3>
                        ${answer.description}
                        ${answer.likes}
                    <br>



                    <security:authorize access="hasAnyRole('ADMIN','MODERATOR')">
                        <jstl:if test="${not answer.banned}">
                            <a href="answer/ban.do?answerId=${answer.id}"> <spring:message
                                    code="user.ban"/>
                            </a>
                        </jstl:if>
                    </security:authorize>
                    <security:authorize access="hasAnyRole('USER')">

                        <c:choose>

                            <c:when test="${likeda.contains(answer)}">
                                <spring:message code="anwer.rate" var="rate"/>
                                <a href="answer/ratenegative.do?answerId=${answer.id}"> <img src="images/disk.jpg" width="40px" height="40px" alt="dislike"/>

                                </a>
                                ${answer.likes}
                            </c:when>

                            <c:otherwise>
                                <a href="answer/ratepositive.do?answerId=${answer.id}"> <img src="images/like.png" width="50px" height="50px" alt="like"/>

                                </a>
                                ${answer.likes}

                            </c:otherwise>

                        </c:choose>





                    </security:authorize>
                    <hr>
                </div>
            </jstl:if>


            <jstl:if test="${not answer.teacher}">

                <h3 class="highlighted2">${answer.title}</h3>
                ${answer.description}
                <br>



                <br>



                <security:authorize access="hasAnyRole('ADMIN','MODERATOR')">
                    <jstl:if test="${not answer.banned}">
                        <a href="answer/ban.do?answerId=${answer.id}"> <spring:message
                                code="user.ban"/>
                        </a>
                    </jstl:if>
                </security:authorize>
                <security:authorize access="hasAnyRole('USER')">
                    <c:choose>

                    <c:when test="${likeda.contains(answer)}">
                        <spring:message code="anwer.rate" var="rate"/>
                        <a href="answer/ratenegative.do?answerId=${answer.id}"> <img src="images/disk.jpg" width="40px" height="40px" alt="dislike"/>

                        </a>
                        ${answer.likes}
                    </c:when>

                    <c:otherwise>
                        <a href="answer/ratepositive.do?answerId=${answer.id}"> <img src="images/like.png" width="50px" height="50px" alt="like"/>

                        </a>
                        ${answer.likes}

                    </c:otherwise>

                    </c:choose>




                </security:authorize>

                <hr>
            </jstl:if>

        </c:forEach>



        <security:authorize access="hasAnyRole('USER','TEACHER')">
            <div>
                <H5>
                    <a class="button2" href="answer/create.do?questionId=${questionId}"> <spring:message
                            code="answer.create"/>
                    </a>
                </H5>
            </div>
        </security:authorize>



            <c:forEach items="${myAnswers}" var="answer">



                    <h3 class="highlighted2">${answer.title}</h3>
                        ${answer.description}
                        ${answer.likes}
                    <br>

                    <a class="button2" href="/answer/edit.do?answerId=${answer.id}"> <spring:message
                            code="answer.edit"/>
                    </a>

                    <a class="button2" href="/answer/delete.do?answerId=${answer.id}"> <spring:message
                            code="general.delete"/>
                    </a>



            </c:forEach>

    </div>





</div>