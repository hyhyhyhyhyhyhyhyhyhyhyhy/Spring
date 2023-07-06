<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
</style>
</head>
<body>
	<h3>사원정보 수정</h3>
	<form action="empUpdate" method="post" name="forSelected">
		사원번호<input name="employeeId" readonly="readonly"
					value="${emp.employeeId}"><br> <!-- disabled는 값을 넘길 수 없어서 여기선 쓰면 안된다! -->
		이름<input name="firstName" autofocus value="${emp.firstName }"><br> 
		성<input name="lastName" value="${emp.lastName }"><br> 
		이메일<input name="email" value="${emp.email}"><br>
		업무코드 <select name="jobId">
			<option value="">--선택--</option>
			<c:forEach items="${jobs}" var="job">
				<!-- map으로 보냈기 때문에 키값이 sql developer에서 보이는 칼럼명 그대로 써야함 -->
				<!-- <option value="${job.JOB_ID}">${job.JOB_TITLE}</option>  -->
				<option value="${job.jobId}">${job.jobTitle}</option>
				<!-- mapper.xml에서 alias로 지정해주면 그 값을 키 값으로 받는다 -->
			</c:forEach>
		</select>
		<br>
		부서번호 
		<c:forEach items="${depts}" var="dept">
			<label><input type="radio" name="departmentId" value="${dept.departmentId}" 
						<c:if test="${dept.departmentId == emp.departmentId}">checked</c:if>>
					${dept.departmentName}</label>
		</c:forEach>
		<br>
		<button>저장</button>
	</form>
</body>
<script>
// forSelected.departmentId.value = "${emp.departmentId}";
forSelected.jobId.value = "${emp.jobId}";
</script>
</html>