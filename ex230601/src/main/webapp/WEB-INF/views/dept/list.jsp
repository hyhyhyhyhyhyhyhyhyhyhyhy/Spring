<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dept. List</title>
</head>
<body>
<div>
	<h3>Departments</h3>
	<a href="deptInsert">Add</a>
	<button type="button" id="checkDel">Delete</button>
	<table>
		<thead>
			<tr>
				<th><input type="checkbox"></th>
				<th>Dept. No.</th>
				<th>Dept. Name</th>
				<th>Mgr. Id</th>
				<th>Loc. Id</th>
				<th>Del.</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${deptList}" var="dept">
				<!-- <tr onclick="location.href='deptInfo?departmentId=${dept.departmentId}'"> -->
				<tr onclick="findDeptInfo(event, ${dept.departmentId})">
					<td><input type="checkbox"></td>
					<td>${dept.departmentId}</td>
					<td>${dept.departmentName}</td>
					<td>${dept.managerId}</td>
					<td>${dept.locationId}</td>
					<td><button type="button">Delete</button></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<form name="del" action="deptDelete" method="post">
	</form>
</div>
<script>
	let result = '${departmentId}';
	if(result != ""){
		alert(result);
	}
	
	document.getElementById('checkDel').addEventListener('click', function(e){
		let checked = document.querySelectorAll('input[type="checkbox"]:checked');
		for(let i=0; i < checked.length; i++){
			let deptNo = checked[i].parentElement.nextElementSibling.textContent; // input, select, textarea 만 value값을 가짐 나머진느text라 textContent로 값 가져옴
			insertDeptInfo(i, deptNo);
		}
		del.submit();
	})

	function insertDeptInfo(index, deptNo){
		let inputTag = document.createElement('input');
		inputTag.type='hidden';
		inputTag.name = 'deptList['+index+'].departmentId'; // 서버쪽에서 받는 배열 이름, 인덱스, 필드값 으로 해야 함수 안에 배열/객체가 변수에 담기는 구나~ 하고 앎
		inputTag.value = deptNo;

		let formTag = document.getElementsByName('del')[0];
		formTag.append(inputTag);

	}

	let delBtnList = Array.from(document.getElementsByTagName('button')).filter(item => item.id != 'checkDel');

	delBtnList.forEach(el => {
		let tdList = this.parentElement.parentElement.children;
		let deptNo = tdList[1].textContent;
		insertDeptInfo(0, deptNo);
		del.submit();
	})
	
	function findDeptInfo(event, deptNo){
	if(event.target.tagName != 'INPUT' && event.target.tagName != 'BUTTON')
		location.href='deptInfo?departmentId='+deptNo;

		/*
		event.target; // 실제 이벤트가 발생한 태그
		event.currentTarget; // this 같은 의미 -> 지금 해당 이벤트에 대해 동작을 하는 태그
		event.preventDefault(); // 기본으로 등록된 이벤트 동작 막음
		event.stopPropagation(); // 이벤트 버블링(전파) 막음
		*/
	}


	// document.getElementById('checkdel').addEventListener('click', function(e){
	// 	let tar = event.target
	// 	for(let i=0; i<)

	// })

</script>
</body>
</html>