<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Board Info.</title>
<style>
	img {
		width: 150px;
		height: 150px;
	}
</style>
</head>

<body>
    <table>
        <tr>
            <th>No.</th>
            <td>${board.bno}</td>
        </tr>
        <tr>
            <th>title</th>
            <td>${board.title}</td>
        </tr>
        <tr>
            <th>content</th>
            <td><textarea rows="8" cols="50" style="width: 100px;" readonly>
                ${board.contents}</textarea></td>
        </tr>
        <tr>
            <th>attachment</th>
            <c:choose>
            	<c:when test="${not empty board.image}">
            		<td><img src='<c:url value="/resources/image/${board.image}"/>'/></td>
            	</c:when>
            	<c:otherwise>
            		<td>no attachment</td>
            	</c:otherwise>
            </c:choose>
        </tr>
        <tr>
            <th>upload date</th>
            <td><fmt:formatDate value="${board.regdate}" pattern="yyyy/MM/dd" /></td>
        </tr>
    </table>
    <button type="button" onclick="location.href='boardUpdate?bno=${board.bno}'">update</button>
    <button type="button" onclick="location.href='boardDelete?bno=${board.bno}'">delete</button>
    <button type="button" onclick="location.href='boardList'">list</button>
</body>

</html>