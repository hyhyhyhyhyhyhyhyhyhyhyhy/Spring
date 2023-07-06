<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table {
	border: 1px solid;
	border-collapse: collapse;
	border-color: lightgray;
	text-align: center;
	margin-top: 10px;
}

#id {
	font-weight: bold;
	color: rgb(95, 95, 95);
}

th, td {
	border: 1px solid;
	border-color : lightgray;
	color: rgb(95, 95, 95);
}
</style>
</head>
<body>
	<a href="empInsert">등록</a>
	<h2>사원조회</h2>
	<form action="empList">
		부서 <input type="text" name="departmentId" value="${emp.departmentId }">
		이름 <input type="text" name="firstName" value="${emp.firstName }">
		<button>검색</button>
		<button type="reset">리셋</button>
	</form>
	<table>
		<thead>
			<tr>
				<th>사원번호</th>
				<th>이름</th>
				<th>성</th>
				<th>이메일</th>
				<th>업무</th>
				<th>부서번호</th>
				<th>기능</th>
			</tr>
		</thead>
		<c:forEach items="${empList }" var="emp">
			<tr>
				<td id="id">${emp.employeeId}</td>
				<td>${emp.firstName}</td>
				<td>${emp.lastName}</td>
				<td>${emp.email}</td>
				<td>${emp.jobId}</td>
				<td>${emp.departmentId}</td>
				<td><a href="empUpdate?empId=${emp.employeeId}">수정</a> 
				<a href="empDelete?empId=${emp.employeeId}">삭제</a></td> <!-- get방식으로 받는거라 a 태그로 받아도 됨 -->
		</c:forEach>
	</table>
</body>
</html>