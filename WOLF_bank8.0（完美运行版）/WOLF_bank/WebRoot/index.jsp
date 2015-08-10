<%@ page contentType="text/html" pageEncoding="GBK"%>
<%@ page import="java.util.*"%>
<html>
<head><title>登陆</title></head>
<body background="5.jpg"   >
<% 
	request.setCharacterEncoding("GBK");
%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script language="javascript">
	function validate(f){
		if(!(/^\w{3,15}$/.test(f.userid.value))){
			alert("用户密码必须是3~15位！") ;
			f.userid.focus() ;
			return false ;
		}
		if(!(/^\w{3,15}$/.test(f.userpass.value))){
			alert("用户ID必须是3~15位！") ;
			f.userid.focus() ;
			return false ;
		}
	}
</script>


</br></br></br></br></br></br> 


<div align="center" width="100px" height="50px">
<form action="login.do" method="post" onSubmit="return validate(this)">
 <p style="font-size:19px">账号 <input id="userid" type="text" name="userid" /></p>
  <p style="font-size:19px">密码 <input id="password" type="password" name="userpass" /></p>
	<input type="submit" value="登陆">
	<input type="reset" value="重置">
</form>
<h4>${info}</h4>

</div>


</body>
</html>