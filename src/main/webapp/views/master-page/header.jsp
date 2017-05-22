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

			<li><a href="category/list.do"><spring:message code="master.page.categories" /> </a></li>
			<li><a href="search/editCache.do"><spring:message code="master.page.changecache" /> </a></li>
			<li><a href="administrator/editDuty.do"><spring:message code="master.page.changeDuty" /> </a></li>
			<li><a href="admin/dashboard.do"><spring:message code="master.page.dashboard" /> </a></li>
			<li><a href="evaluationQuestion/list.do"><spring:message code="master.page.evalution" /> </a></li>
            <li><a href="curricula/list.do"><spring:message code="master.curriculas.list"/> </a></li>



		</security:authorize>


        <%--****************************************USER****************************************--%>


		<security:authorize access="hasRole('USER')">
			<li><a class="fNiv"><spring:message code="master.page.user"/></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="user/editp.do"><spring:message code="master.page.edit.profile" /> </a></li>
                    <li><a href="user/list.do"><spring:message code="master.page.user.list"/> </a></li>
				</ul>
			<li><a href="question/list.do"><spring:message code="master.page.question.list"/> </a></li>
			<li><a class="fNiv" href="webinar/listAn.do"><spring:message code="master.page.webinar.list"/></a>
			<li><a class="fNiv" href="webinar/listToGo.do"><spring:message code="master.page.webinar.mylist"/></a>
			<li><a class="fNiv" href="bill/list.do"><spring:message code="master.page.webinar.mybill"/></a>

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

		<li><a class="fNiv"><spring:message code="master.page.webinars"/></a>
			<ul>
				<li class="arrow"></li>
				<li><a href="webinar/list.do"><spring:message code="master.page.webinar.list"/></a>
				<li><a href="webinar/listMy.do"><spring:message code="master.page.webinar.list.my"/></a>
			</ul>
		</li>

			<li><a href="admin/dashboardT.do"><spring:message code="master.page.dashboard" /> </a></li>
			<li><a href="teacher/bill.do"><spring:message code="master.page.earns" /> </a></li>


		</security:authorize>


			<%--****************************************MODERATOR****************************************--%>


			<security:authorize access="hasRole('MODERATOR')">
		<li><a class="fNiv"><spring:message code="master.page.moderator"/></a>
                <ul>
                    <li class="arrow"></li>
				</ul>
			<li><a href="user/listAll.do"><spring:message code="master.page.user.list"/></a></li>
			<li><a href="question/listAll.do"><spring:message code="master.page.question.list"/> </a></li>
            </li>
        </security:authorize>

        <%--****************************************ANONYMOUS****************************************--%>

		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
		<li><a class="fNiv" href="user/create.do"><spring:message code="master.page.register.user"/></a>
		<li><a class="fNiv" href="teacher/create.do"><spring:message code="master.page.register.teacher"/></a>
		<li><a class="fNiv" href="question/listPopular.do"><spring:message code="master.page.question.listpop"/></a>
		<li><a class="fNiv" href="webinar/listIncoming.do"><spring:message code="master.page.webinar.listIncom"/></a>
		<li><a class="fNiv" href="webinar/listAn.do"><spring:message code="master.page.webinar.list"/></a>
			</security:authorize>


			<%--****************************************AUTHENTICATED****************************************--%>

			<security:authorize access="isAuthenticated()">
		<li><a class="fNiv"><spring:message code="master.page.search"/></a>
			<ul>
				<li class="arrow"></li>
				<li><a href="search/create.do"><spring:message code="master.page.search.new"/> </a></li>
				<li><a href="search/mySearches.do"><spring:message code="master.page.search.last"/> </a></li>

			</ul>
		</li>
		<li><a href="/folder/list.do"><spring:message
				code="master.page.actor.mezzages"/></a></li>
		<li>
				<a class="fNiv">
					<spring:message code="master.page.profile"/>
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

