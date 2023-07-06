<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Board</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
</head>
<body>
    <form name="insertForm" action="boardInsert" method="post">
        <div>
            <h3>Board Details</h3>
        </div>
        <table>
            <tr>
                <th>Title</th>
                <td><input type="text" name="title" autofocus></td>
            </tr>
            <tr>
                <th>Writer</th>
                <td><input type="text" name="writer"></td>
            </tr>
            <tr>
                <th>Contents</th>
                <td><textarea name="contents" id="" cols="30" rows="10"></textarea></td>
            </tr>
            <tr>
                <th>Attachment</th>
                <td><input type="text" name="image"></td>
            </tr>
        </table>
        <button type="submit">register</button>
        <button type="button" onclick="location.href='boardList'">list</button>
    </form>
    <script>
//    	function listCheck(e){
//   			let title = $('input[name="title"]').text();
//   			let writer = $('input[name="writer"]').text();
//   			let contents = $('input[name="contents"]').text();
//   			
//   			if(title.equals('null') || title.equals('')){
//   				alert('Please make sure your title.')
//   			}
//   			if(writer.equals('null') || writer.equals('')){
//   				alert('Please write your nickname down.')
//   			}
//    		if(contents.equlas('null') || writer.equals('')){
//    			alert('Please write anything to upload your board!')
//    		}
//    	}
    	
    	document.querySelector('form[name="insertForm"]')
    			.addEventListener('submit', function(e){
    				e.preventDefault();
    				
    				let title = document.getElementsByName('title')[0];
    				let writer = document.getElementsByName('writer')[0];
    				let contents = document.getElementsByName('contents')[0];
    			
    			
    			if(title.value == ''){
    				alert('Please make sure your title.');
    				title.focus();
    				return;
    			}
    	
    			if(writer.value == ''){
    				alert('Please write your nickname down.');
    				writer.focus();
    				return;
    			}
    			
    			if(contents.value == ''){
    				alert('Please write anything to upload your board!');
    				contents.focus();
    				return;
    			}
    	
    			insertForm.submit();
            
            })
   </script>
</body>
</html>
   