<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
	</head>
	
	
<script language="javascript">
	function validate(f){
		if(!(/^\w{3,15}$/.test(f.userid.value))){
			alert("用户ID必须是3~15位！") ;
			f.userid.focus() ;
			return false ;
		}
		if(f.userpass.value!=f.reuserpass.value){
			alert("两次新密码不相等，请确认新密码！") ;
			f.userpass.focus() ;
			return false ;
		}
		
	}
</script>


	<body background="7.jpg">
		<center>
<br/><br/>
<div align="center" width="100px" height="50px" >
	<form action="register.reg" method="post" onSubmit="return validate(this)">
	<p style="font-size:19px">账　　号 <input id="userid" type="text" name="userid" /></p>
	<p style="font-size:19px">密　　码 <input id="password" type="password" name="userpass" /></p>
    <p style="font-size:19px">确认密码 <input id="repassword" type="password" name="reuserpass" /></p>
 	<p style="font-size:19px">角   色 ID <input id="roleid" type="text" name="roleid" /></p>
 	<p style="font-size:19px">昵　　称 <input id="username" type="text" name="username" /></p>
 	<p style="font-size:19px">性　　别 <input id="usersex" type="text" name="usersex" /></p>
 	<p style="font-size:19px">年　　龄 <input id="userage" type="text" name="userage" /></p>
 	<p style="font-size:19px">身份证号 <input id="useridentity" type="text" name="useridentity" /></p>
 	<p style="font-size:19px">联系电话 <input id="userphone" type="text" name="userphone" /></p>
	<input type="submit" value="注册">
	</form>
</div>

	<h4>${register_info}</h4>
	</center>
	
	</body>
</html>
