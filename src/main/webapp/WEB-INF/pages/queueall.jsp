<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


 <div class="content_wrapper">

	<div class="content_page_heading_section">
		<div class="container-fluid">
			<h3 class="content_page_heading">Edit Admin</h3>
		</div>
	</div>
	

	<div class="mid_content_section section_padding">
		<div class="container-fluid">
			<div class="row">




<c:choose>
   <c:when test="${!empty  listdtoo}">
      <table class="table table-bordered bg-white">
      
       <tr> 
        <th>Deparment</th>
        <th>Room no</th>
        <th>Department Floor</th>
        <th>Doctor First Name</th>
        <th>Last name</th>




         <c:forEach var="dto"  items="${listdtoo}">
         
              <tr>
              
            
                <td>${dto.dpt_name}</td>
                <td>${dto.room_no}</td>
                <td>${dto.dpt_location}</td>
                <td>${dto.fname}</td>
                <td>${dto.lname}</td>

             </tr> 
         </c:forEach>
      </table>
       </c:when>
    <c:otherwise>
       <h1 style="color:red;text-align:center"> No Records found </h1>
   </c:otherwise>
</c:choose>

</div>
</div>
</div>
</div>
<br><br><br>
<c:if test="${!empty result }">
 <h3 style="color:red"> Result status ::: ${result}  </h3>
</c:if>
<br><br><br><br>
<a href="/home">home</a>
