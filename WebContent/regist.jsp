<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>

</head>
<body>
    <br/><br/><br/><br/><br/><br/>
   
   
     <font size=3 color=red>${msg }</font>
	<form action="${pageContext.request.contextPath }/user_regist.action" method="post">
用 户  名 :<input type="text" name="username" /><font color="red">${errors.name}</font><br/>
密        码:<input type="password" name="password1"/><font size=3 color=red>${errors.password }</font><br/>
确认密码:<input type="password" name="password2"/> <font size=3 color=red>${errors.password }</font><br/>
     
          <input type="submit"value="注册" />
	</form>
	
</body>
</html>