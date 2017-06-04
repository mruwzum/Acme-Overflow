<%--
  ~ Copyright © 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
  --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="date" class="java.util.Date"/>

<hr/>


<div id="barraaceptacion" style="display: block;">
    <div class="inner">
        Solicitamos su permiso para obtener datos estadísticos de su navegación en esta web, en cumplimiento del Real
        Decreto-ley 13/2012. Si continúa navegando consideramos que acepta el uso de cookies.
        <a href="javascript:void(0);" class="ok" onclick="PonerCookie();"><b>OK</b></a> |
        <a href="http://politicadecookies.com" target="_blank" class="info">Más información</a>
    </div>
</div>


<b>Copyright &copy; <fmt:formatDate value="${date}" pattern="yyyy"/> Acme Co., Inc.</b>


<br>
