<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration</title>
</head>
<body>
	<form action="deptInsert" method="post">
		<div>
			<label for="name">Dept. Name</label>
			<input type="text" id="name" name="departmentName" required>
		</div>
		<div>
			<label for="mId">Mgr. Id</label>
			<input type="number" id="mId" name="managerId">
		</div>
		<div>
			<label for="lId">loc. Id</label>
			<input type="number" id="lId" name="locationId">
		</div>
		<button type="submit">Update</button>
		<button type="button">List</button>
	</form>
</body>
</html>