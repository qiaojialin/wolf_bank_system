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
		if(!(/^\w{3,15}$/.test(f.userpass.value))){
			alert("用户密码必须是3~15位！") ;
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

	<body background="8.jpg">
		<center>
	<br/><br/><br/><br/>
	<div align="center" width="100px" height="50px" >
	<form action="changepassword.change" method="post" onSubmit="return validate(this)">
	<p style="font-size:19px">账　　号<input id="userid" type="text" name="userid" /></p>
	<p style="font-size:19px">原始密码<input id="oldpassword" type="password" name="olduserpass" /></p>
	<p style="font-size:19px">新的密码<input id="password" type="password" name="userpass" /></p>
    <p style="font-size:19px">确认密码<input id="repassword" type="password" name="reuserpass" /></p>
	<input type="submit" value="修改密码">
	</form>
</div>
<h4>${changepassword_info}</h4>
		</center>
	</body>
</html>
