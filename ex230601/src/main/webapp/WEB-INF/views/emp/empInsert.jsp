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
	<h3>사원등록</h3>
	<form action="empInsert" method="post">
		이름<input name="firstName" autofocus><br> 
		성<input name="lastName"><br> 
		이메일<input name="email"><br>
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
			<label><input type="radio" name="departmentId"
						value="${dept.departmentId}">${dept.departmentName}</label>
		</c:forEach>
		<br>
		<button>저장</button>
	</form>
</body>
</html>