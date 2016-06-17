<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page language="java" import="javax.naming.*, javax.sql.*, java.sql.*" %>
<%@ page language="java" import="com.khbclass.memo.model.*" %>
<%@ page language="java" import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>메모장</title>
</head>
<body>
<h1>메모장 - 목록</h1>
<a href="Memo/new">글쓰기</a><br/>
<table>
<thead>
	<th>내용</th>
	<th>작성자</th>
	<th>파일</th>
	<th>날짜</th>
</thead>
<tbody>
<%
	ArrayList<MemoVO> list = (ArrayList<MemoVO>)request.getAttribute("list");
	// 반복문을 돌면서 list 를 출력

	int len = list.size();
	
	for(int i=0;i<len;i++){
		
		MemoVO vo = list.get(i);
		
		out.print("<tr>");
		out.print("<td>"+vo.getContent()+"</td>");
		out.print("<td>"+vo.getUser_name()+"</td>");
		out.print("<td>"+vo.getFile()+"</td>");
		out.print("<td>"+vo.getN_date()+"</td>");
		out.print("</tr>");
	}
	
%>
</tbody>
</table>

</body>
</html>




