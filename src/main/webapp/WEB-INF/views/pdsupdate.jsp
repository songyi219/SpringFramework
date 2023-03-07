<%@page import="mul.cam.a.dto.PdsDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
	// int seq = Integer.parseInt(request.getParameter("seq"));	// pdsdetail에서 넘겨받은 값.
	PdsDto dto = (PdsDto)request.getAttribute("pdsdto");
	
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>자료수정</h1>
<hr>


<div align="center">
<form action="pdsupdateAf.do" method="post" enctype="multipart/form-data">	<!-- miltipart 꼭해줘야함 -->
<input type="hidden" name="seq" value="<%=dto.getSeq() %>">
<table border="1">	
<col width="200"><col width="500">
<tr>
	<th>아이디</th>
	<td>
		<%=dto.getId() %>		
	</td>
</tr>
<tr>
	<th>제목</th>
	<td>
		<input type="text" name="title" value="<%=dto.getTitle() %>	"> 
	</td>
</tr>
<tr>
	<th>파일명</th>
	<td>
		 <%=dto.getFilename()%>		
		<!-- <input type="file" name="fileload"> -->
		<!-- 파일이 변경되지 않았을 경우를 위해서 값을 보내줌 -->
		<input type="hidden" name="filename" value="<%=dto.getFilename() %>">
		<input type="hidden" name="newfilename" value="<%=dto.getNewfilename() %>">
	</td>
</tr>

<tr>
	<th>수정(대체)할 파일</th>
	<td>
		<input type="file" name="fileload">
	</td>
</tr>
<tr>
	<th>내용</th>
	<td>
		<textarea rows="10" cols="50" name="content"><%=dto.getContent() %></textarea>
	</td>
</tr>
<tr>
	<td colspan="2">
		<button type="submit">수정완료</button>
	</td>
</tr>
</table>
</form>

</div>


</body>
</html>