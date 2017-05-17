<%--
 * header.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div>
    <a href="welcome/index.do"> <img src="images/logo.png" alt="Acme Overflow, Inc."/>
    </a>

</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->

        <%--****************************************ADMIN****************************************--%>

        <security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message	code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>

				</ul>
			</li>
		</security:authorize>


        <%--****************************************USER****************************************--%>


		<security:authorize access="hasRole('USER')">
			<li><a class="fNiv"><spring:message code="master.page.user"/></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="user/editp.do"><spring:message code="master.page.edit.profile" /> </a></li>
				</ul>
			</li>



		</security:authorize>




        <%--****************************************TEACHER****************************************--%>


        <security:authorize access="hasRole('TEACHER')">
			<li><a class="fNiv"><spring:message code="master.page.teacher"/></a>
                <ul>
                    <li class="arrow"></li>
                    <li><a href="teacher/editp.do"><spring:message code="master.page.edit.profile"/> </a></li>
                </ul>
            </li>
        </security:authorize>


        <%--****************************************MODERATOR****************************************--%>


        <security:authorize access="hasRole('MODERATOR')">
			<li><a class="fNiv"><spring:message code="master.page.moderator"/></a>
                <ul>
                    <li class="arrow"></li>
                </ul>
            </li>
        </security:authorize>

        <%--****************************************ANONYMOUS****************************************--%>

		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
		<li><a class="fNiv" href="user/create.do"><spring:message code="master.page.register.user"/></a>
		<li><a class="fNiv" href="teacher/create.do"><spring:message code="master.page.register.teacher"/></a>
			</security:authorize>


			<%--****************************************AUTHENTICATED****************************************--%>

			<security:authorize access="isAuthenticated()">
		<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

