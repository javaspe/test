<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

<form action="/ValueTransfer/Memo" method="post">
	내용 : <textarea id="content" name="content" cols="25" rows="3"></textarea><br/>
	작성자 : <input type="text" id="user_name" name="user_name"> <br/>
	파일 : <input type="file" id="file" name="file" /> <br/>
	<input type="submit" value="전송" />
</form>

</body>
</html>