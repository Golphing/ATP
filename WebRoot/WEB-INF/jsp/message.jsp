<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    
    <title>message</title>
    
	<style>
		body{
	   			background-image:url("/ATP-beta1/pic/bg.jpg");
	   			background-repeat: no-repeat;
	   			background-position:top right;
	   	}
	   		
		#error{
			width:500px;
			height:200px;
			margin:auto;
			margin-top:30px;
			color:red;
			font-size: 35px;
			text-align: center;
			padding-top: 30px;
		}
		
		#logo{
   			margin-top:10px;
   			width:100%;
   			height:80px;
   			background-image:url("/ATP-beta1/pic/logo1.gif");
   			background-repeat:no-repeat;
   			background-position:top right;
   		}
   		
   		#logbut {
   			width:100%;
   			text-align:center;
   		}
   		
   		#logbut input {
   			width:110px;
   			height:50px;
   			background-color:#2C7F2D;
   		}
   		
   		#welco{
	   			width:70%;
	   			margin:auto;
	   			border-bottom: 2px solid blue;
	   			height:24px;
	   			text-align: left;
	   		}
	   		
	   	#right{
	   			float:right;	
	   	}
	</style>
	<script>
		function showLog(){
			window.location.href="/ATP-beta1/servlet/ShowLogServlet?cid="+Math.random();
			
		}
		
		function downloadLog(){
			window.location.href="/ATP-beta1/servlet/DownLoadLog";
		}
	</script>

  </head>
  
  <body bgcolor="black">
  		<div id="logo">
  		</div>
  		<div id="welco">
  				你好，${user}<span id="right"><a href="/ATP-beta1/servlet/Back2FirstServlet">返回首页</a>&nbsp;<a href="/ATP-beta1/login.jsp">退出登录</a></span>
  		</div>
    	<div id="error">
    		${message }
    	</div>
    	<div id="logbut">
    		<input type="button" onclick="showLog();" value="查看错误日志"/>
    		<input type="button" onclick="downloadLog();" value="下载错误日志"/>
    	</div>
  </body>
</html>
