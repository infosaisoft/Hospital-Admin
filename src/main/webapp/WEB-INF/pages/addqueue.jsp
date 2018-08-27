<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>


<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="content_wrapper">

	<div class="content_page_heading_section">
		<div class="container-fluid">
			<h3 class="content_page_heading">Queue Management</h3>
		</div>
	</div>

	<div class="mid_content_section section_padding">
		<form:form modelAttribute="addQueue" method="POST">
			<div class="container-fluid">
				<div class="row">

					<div class="col-8">
						<div class="form-group">
							<div>
								<label for="formGroupExampleInput">Department name</label>
							</div>
							<form:select path="dpt_name" multiple="true">
								<form:options items="${select_Dptt}" />
							</form:select>
						</div>
					</div>
					
					<div class="col-8">
						<div class="form-group">
							<div>
								<label for="formGroupExampleInput">Department Floor</label>
							</div>
							<form:select path="dpt_location" multiple="true">
								<form:options items="${dpt_location}" />
							</form:select>
						</div>
					</div>

					
					

<div class="col-12">
	                    <div class="form-group">
	                        <div class="input-group">
	                            <div class="input-group-addon"><i class="fa fa-user"></i></div>
	                            <div>
								<label for="formGroupExampleInput">ROOM NO</label>
							</div>
	                            <form:input type="text" path="room_no" class="form-control" id="room_no" placeholder="Enter Room no"/>
	                        </div>					
	                        <form:errors class="validation_msg" path="room_no"></form:errors>
	                    </div>
	                </div>
	


<div class="col-8">
						<div class="form-group">
							<div>
								<label for="formGroupExampleInput">Doctor First Name</label>
							</div>
							<form:select path="fname" multiple="true">
							<form:options items="${firstname}" />  
							</form:select>
						</div>
					</div>
						
						<div class="form-group">
							<div>
								<label for="formGroupExampleInput"> Doctor Last Name</label>
							</div>
							<form:select path="lname" multiple="true">
								<form:options items="${lname}" />
							</form:select>
						</div>
					





					<div class="col-8">

						<input type="submit" value="AddAdmin" class="btn btn-success">
					</div>
				</div>
			</div>


		</form:form>
		${result}

	</div>
</div>