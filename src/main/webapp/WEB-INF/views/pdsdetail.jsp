<%@page import="mul.cam.a.dto.MemberDto"%>
<%@page import="mul.cam.a.dto.PdsDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	int seq = (Integer.parseInt(request.getParameter("seq")));	
	PdsDto dto = (PdsDto)request.getAttribute("pdsdto");	
%>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>자료 상세보기</h1>
<hr>

<div align="center">

<table border="1">
<tr>
	<th>아이디</th>
	<td>
		<%=dto.getId() %>
	</td>
</tr>
<tr>
	<th>제목</th>
	<td>
		<%=dto.getTitle()%>
	</td>
</tr>
<tr>
	<th>다운로드</th>
	<td>
		<input type="button" value="다운로드" 
		onclick="filedown(<%=dto.getSeq()%>, '<%=dto.getNewfilename()%>','<%=dto.getFilename()%>')">			
	</td>
</tr>
<tr>
	<th>조회수</th>
	<td>
		<%=dto.getReadcount()%>
	</td>
</tr>
<tr>
	<th>다운로드수</th>
	<td>
		<%=dto.getDowncount()%>
	</td>
</tr>
<tr>
	<th>파일명</th>
	<td>
		<%=dto.getFilename()%>
	</td>
</tr>
<tr>
	<th>등록일</th>
	<td>
		<%=dto.getRegdate() %>
	</td>
</tr>
<tr>
	<th>내용</th>
	<td>
		<textarea rows="10" cols="50" name="content"><%=dto.getContent() %></textarea>
	</td>
</tr>
</table>
<br>
<input type="button" onclick="location.href='pdslist.do'" value="글목록">

<%
MemberDto login = (MemberDto)session.getAttribute("login");	
if(dto.getId().equals(login.getId())){
	%>
	<button type="button" onclick="updatePbs(<%=dto.getSeq() %>)">수정</button>
	
	<button type="button" onclick="deletePbs(<%=dto.getSeq() %>)">삭제</button>
	<%-- <input type="button" onclick="location.href='pdsupdate.do'?seq=<%=dto.getSeq() %>" value="수정">
	<input type="button" onclick="location.href='pdsdelete.do'?seq=<%=dto.getSeq() %>" value="삭제"> --%>
	<%
}
%>


</div>

<form name="file_down" action="filedownLoad.do" method="post">
	<input type="hidden" name="newfilename">
	<input type="hidden" name="filename">
	<input type="hidden" name="seq">
</form>


<script type="text/javascript">

function filedown(seq, newfilename, filename) {
	document.file_down.newfilename.value = newfilename;	
	document.file_down.filename.value = filename;
	document.file_down.seq.value = seq;
	document.file_down.submit();
}

function updatePbs(seq) {
	location.href = "pdsupdate.do?seq=" + seq ;
}
function deletePbs(seq) {
	location.href = "pdsdelete.do?seq=" + seq ;
}

</script>

</body>
</html>