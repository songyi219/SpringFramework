<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
String message = (String)request.getAttribute("message");
// null이 아니고 빈문자가 아닐 때
if(message != null && !message.equals("")){
	if(message.equals("MEMBER_ADD_YES")){
	%>	
	<script type="text/javascript">
	alert("성공적으로 가입되었습니다");
	location.href = "login.do";
	</script>
	<%
	}else{
	%>
	<script type="text/javascript">
	alert("가입되지 않았습니다 다시 가입해 주십시오");
	location.href = "regi.do";
	</script>
	<%
	}
	
}
%>
<%
String login = (String)request.getAttribute("login");
// null이 아니고 빈문자가 아닐 때
if(login != null && !login.equals("")){
	if(login.equals("LOGIN_OK")){
	%>	
	<script type="text/javascript">
	alert("로그인되었습니다");
	location.href = "bbslist.do";
	</script>
	<%
	}else{
	%>
	<script type="text/javascript">
	alert("아이디나 패스워드를 확인해 주십시오");
	location.href = "login.do";
	</script>
	<%
	}
	
}	


String bbswrite = (String)request.getAttribute("bbswrite");
if(bbswrite != null && !bbswrite.equals("")){
	if(bbswrite.equals("BBS_ADD_OK")){
		%>	
		<script type="text/javascript">
		alert("성공적으로 작성되었습니다");
		location.href = "bbslist.do";
		</script>
		<%
	}else{
		%>	
		<script type="text/javascript">
		alert("다시 작성해 주십시오");
		location.href = "bbswrite.do";
		</script>
		<%
	}
}
String sessionOut = (String)request.getAttribute("sessionOut");
if(sessionOut != null && !sessionOut.equals("")){
	%>
	<script type="text/javascript">
	alert("로그인 해 주십시오");
	location.href = "login.do";
	</script>
	<%
}
%>

<%
String update = (String)request.getAttribute("update");
// null이 아니고 빈문자가 아닐 때
if(update != null && !update.equals("")){
	if(update.equals("UPDATE_OK")){
	%>	
	<script type="text/javascript">
	alert("수정되었습니다");
	location.href = "bbslist.do";
	</script>
	<%
	}else{
	%>
	<script type="text/javascript">
	alert("다시 수정해 주십시오");
	location.href = "updatebbs.do";
	</script>
	<%
	}
	

}	
%>

<%
String delete = (String)request.getAttribute("delete");
// null이 아니고 빈문자가 아닐 때
if(delete != null && !delete.equals("")){
	if(delete.equals("DELETE_OK")){
	%>	
	<script type="text/javascript">
	alert("삭제되었습니다");
	location.href = "bbslist.do";
	</script>
	<%
	}else{
	%>
	<script type="text/javascript">
	alert("삭제되지않았습니다");
	location.href = "bbsdetail.do";
	</script>
	<%
	}
	

}	
%>

<%
String answer = (String)request.getAttribute("answer");
// null이 아니고 빈문자가 아닐 때
if(answer != null && !answer.equals("")){
	if(answer.equals("ANSWER_OK")){
	%>	
	<script type="text/javascript">
	alert("답글이 등록되었습니다");
	location.href = "bbslist.do";
	</script>
	<%
	}else{
	%>
	<script type="text/javascript">
	alert("등록되지않았습니다");
	location.href = "answerbbs.do";
	</script>
	<%
	}
	

}	
%>

<%
String pdsupdate = (String)request.getAttribute("pdsupdate");
// null이 아니고 빈문자가 아닐 때
if(pdsupdate != null && !pdsupdate.equals("")){
	if(pdsupdate.equals("PDS_UPDATE_OK")){
	%>	
	<script type="text/javascript">
	alert("수정되었습니다");
	location.href = "pdslist.do";
	</script>
	<%
	}else{
	%>
	<script type="text/javascript">
	alert("다시 수정해 주십시오");
	location.href = "pdsupdate.do";
	</script>
	<%
	}
	

}	
%>


<%
String pdsdelete = (String)request.getAttribute("pdsdelete");
// null이 아니고 빈문자가 아닐 때
if(pdsdelete != null && !pdsdelete.equals("")){
	if(pdsdelete.equals("PDS_DELETE_OK")){
	%>	
	<script type="text/javascript">
	alert("삭제되었습니다");
	location.href = "pdslist.do";
	</script>
	<%
	}else{
	%>
	<script type="text/javascript">
	alert("삭제되지않았습니다");
	location.href = "pdsdetail.do";
	</script>
	<%
	}
	

}	
%>
