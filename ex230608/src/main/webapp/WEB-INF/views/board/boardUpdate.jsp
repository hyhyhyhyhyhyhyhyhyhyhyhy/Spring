<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Board</title>
</head>
<body>
    <form name="updateForm" action="boardUpdate" method="post" onsubmit="return false;">
        <div>
            <h3>Update Detail</h3>
        </div>
        <table>
            <tr>
                <th>No.</th>
                <td><input type="number" name="bno" value="${board.bno}" readonly></td>
            </tr>
            <tr>
                <th>Title</th>
                <td><input type="text" name="title" value="${board.title}"></td>
            </tr>
            <tr>
                <th>Writer</th>
                <td><input type="text" name="writer" value="${board.writer}"></td>
            </tr>
            <tr>
                <th>Content</th>
                <td><textarea name="contents" cols="30" rows="10">${board.contents}</textarea></td>
            </tr>
            <tr>
                <th>Attachment</th>
                <td><input type="text" name="image" value="${board.image}"></td>
            </tr>
            <tr>
                <th>Updated</th>
                <td><input type="date" name="updatedate" value="<fmt:formatDate value="${board.updatedate}" pattern="yyyy-MM-dd"/>"></td>
            </tr>
            <tr>
            	<td><button type="submit">complete</button></td>
            	<td><button type="button" onclick="location.href='boardInfo?bno=${board.bno}'">cancel</button></td>
            </tr>
        </table>
    </form>
    <script>
    	function updateAjax(e){
    		// 'FormData' is the easiest way to get the data from Form tag of html code. 
    		// File updates like pom.xml dependency and servlet-context.xml are needed when it comes to transfer the data type through FormData.
    		let boardData = new FormData(document.querySelector("[name='updateForm']"));
    		
    		fetch(updateForm.action, {
    			method: 'post',
    			body: boardData
    		})
    		.then(response => response.json())
    		.then(data => {
    			let message = 'RESULT : ' + data.result +
    						   '\nBoard No. : ' + data['bno'];
    			alert(message);
    		})
    		.catch(err => console.log(err));
    	}
    	
    	document.querySelector('button[type="submit"]').addEventListener('click', updateAjax);
    	
    	
    	
    	
    </script>
</body>
</html>