<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false" %>
	
	<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
	

<h1>hello</h1>


<h1>${uid}</h1>
<h1>${hid}</h1>

<form:form modelAttribute="hospitalcmd" method="POST">

${dto.name }


</form:form>
