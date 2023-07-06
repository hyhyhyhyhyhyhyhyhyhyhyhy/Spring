<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Dept. Details</title>
</head>

<body>
	<form name="frm" onsubmit="return false">
		<!-- 폼 태그는 key-value/key-value 만 보낼 수 있어서 사용 안하는 걸로 바꾸기 -->
		<div>
			<label for="id">Dept. No.</label>
			<input type="number" id="id" name="departmentId" value="${deptInfo.departmentId}" readonly>
		</div>
		<div>
			<label for="name">Dept. Name</label>
			<input type="text" id="name" name="departmentName" value="${deptInfo.departmentName}">
		</div>
		<div>
			<label for="mId">Mgr. Id</label>
			<input type="number" id="mId" name="managerId" value="${deptInfo.managerId}">
		</div>
		<div>
			<label for="lId">loc. Id</label>
			<input type="number" id="lId" name="locationId" value="${deptInfo.locationId}">
		</div>
		<button type="submit">Update</button>
		<button type="button">List</button>
	</form>
	<script>
		document.querySelector('button[type="submit"]').addEventListener('click', function (e) {
			let data = [{
				'departmentId': frm.departmentId.value,
				'departmentName': frm.departmentName.value,
				'managerId': frm.managerId.value,
				'locationId': frm.locationId.value
			}]

			fetch('deptUpdate', {
					method: 'post',
					headers: {
						'content-type': 'application/json'
					},
					body: JSON.stringify(data)
				})
				.then(response => response.json())
				.then(result => {
					if (result != "" && result != null) {
						let msg = `결과 : ${result.result} \n 
								   성공 : ${result.success} \n
								   대상 : ${result.deptList[0]}`;
						alert(msg);
					}
				})
				.catch(err => console.log(err));

		})
	</script>
</body>

</html>