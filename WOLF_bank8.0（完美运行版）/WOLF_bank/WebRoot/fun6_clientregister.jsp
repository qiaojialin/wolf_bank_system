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
		
		
	}
</script>

	<body background="4.jpg">
		<center>
<br/><br/>
<div align="center" >
	
	<form action="clientregister.creg" method="post" onSubmit="return validate(this)">
	<p style="font-size:19px">客户号　<input id="clientid" type="text" name="clientid" value="<%=session.getAttribute("clientid") %>"/></p>
	<p style="font-size:19px">中文名称<input id="clientname" type="text" name="clientname" /></p>
	<p style="font-size:19px">所在城市<input id="city" type="text" name="city" /></p>  	
 	<p style="font-size:19px">地　　址<input id="address" type="text" name="address" /></p>
 	<p style="font-size:19px">国　　籍<input id="country" type="text" name="country" /></p>
 	<p style="font-size:19px">行　　业<input id="job" type="text" name="job" /></p>
	<input type="submit" value="注册">
	</form>
</div>
	<h4>${clientregister_info}</h4>
	</center>
	
	</body>
</html>
