<%@page import="mul.cam.a.dto.PdsDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	List<PdsDto> list = (List<PdsDto>)request.getAttribute("pdslist");
	String choice = (String)request.getAttribute("choice");
	String search = (String)request.getAttribute("search");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- bootstrap -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.3/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

</head>
<body>

<h1>자료실</h1>

<hr>
<div align="center">

<table border="1">
<col width="50"><col width="100"><col width="300"><col width="50">
<col width="50"><col width="50"><col width="100">

<thead>
<tr>
	<th>번호</th><th>작성자</th><th>제목</th><th>다운로드</th>
	<th>조회수</th><th>다운수</th><th>작성일</th>
</tr>
</thead>

<tbody>
<%
for(int i = 0; i < list.size(); i++){
	PdsDto pds = list.get(i);
	%>
	<tr>
		<th><%=i + 1 %></th>
		<td><%=pds.getId() %></td>
		<td><a href ="pdsdetail.do?seq=<%=pds.getSeq()%>"><%=pds.getTitle() %></a></td>
		<td>
			<input type="button" value="다운로드" 
				onclick="filedown(<%=pds.getSeq()%>, '<%=pds.getNewfilename()%>','<%=pds.getFilename()%>')">
		</td>
		<td><%=pds.getReadcount() %></td>
		<td><%=pds.getDowncount() %></td>
		<td><%=pds.getRegdate() %></td>		
	</tr>
	<%
}
%>	

</tbody>
</table>
<br><br>
<div class="form-row align-items-center d-flex justify-content-center align-items-center container">
<select id="choice">
	<option value="">검색</option>
	<option value="title">제목</option>
	<option value="filename">자료명</option>
	<option value="writer">작성자</option>
</select>

<div class="col-sm-2 my-1">
	<input type="text" id="search" class="form-control"  value="<%=search %>">
</div>

<button type="button" onclick="searchBtn()" class="btn btn-primary">검색</button>
</div>
<br><br>

<button type="button" onclick="pdsWrite()">자료추가</button>
</div>

<!-- post 방식으로 보내주는 방법 -->
<form name="file_down" action="filedownLoad.do" method="post">
	<input type="hidden" name="newfilename">
	<input type="hidden" name="filename">
	<input type="hidden" name="seq">
</form>

<script type="text/javascript">
function pdsWrite() {
	location.href = "pdswrite.do";
}

function filedown(seq, newfilename, filename) {
	document.file_down.newfilename.value = newfilename;	
	document.file_down.filename.value = filename;
	document.file_down.seq.value = seq;
	document.file_down.submit();				// form을 자바스크립트로 보내주기  post방식을 사용하기 위해
}

let search = "<%=search %>";
console.log("search = " + search);
if(search != ""){
	let obj = document.getElementById("choice");
	obj.value = "<%=choice %>";
	obj.setAttribute("selected", "selected");		// 검색어를 적었을때 계속 choice값을 유지
}

function searchBtn() {
	let choice = document.getElementById('choice').value;
	let search = document.getElementById('search').value;
	
	/* if(choice == ""){
		alert("카테고리를 선택해 주십시오");
		return;
	} */
	/* 
	if(search.trim() == ""){
		alert("검색어를 선택해 주십시오");
		return;
	} */
	
	location.href = "pdslist.do?choice=" + choice + "&search=" + search;
}



</script>


</body>
</html>






