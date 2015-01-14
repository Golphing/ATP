<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    
    <title>showLog</title>
    <style>
    	#logo{
	   			margin-top:10px;
	   			width:100%;
	   			height:80px;
	   			background-image:url("/ATP-beta1/pic/logo1.gif");
	   			background-repeat:no-repeat;
	   			background-position:top right;
	   	}
    	
    	body{
   			background-image:url("/ATP-beta1/pic/bg.jpg");
   		}
   		
   		#content {
   			width:750px;
   			margin: auto;
   			text-align:left;
   		}
   		
   		#tit {
   			width:200px;
   			height:50px;
   			font-size: 35px;
   			color:red;
   			margin:auto;
   			
   		}
   		
   		#welco {
	   			width:70%;
	   			margin:auto;
	   			border-bottom: 2px solid blue;
	   			height:24px;
	   			text-align: left;
	   		}
	   		
	   	#right {
	   			float:right;	
	   	}
	   	
	   	#logbut {
   			width:100%;
   			text-align:center;
   		}
   		
   		#logbut input {
   			width:90px;
   			height:30px;
   			background-color:#2C7F2D;
   		}
    </style>
    
    <script>
    	function downloadLog(){
			window.location.href="/ATP-beta1/servlet/DownLoadLog";
		}
    </script>
  </head>
  
  <body>
  	<div id="logo">
  	</div>
  	<div id="welco">
  				你好，${user}<span id="right"><a href="/ATP-beta1/servlet/Back2FirstServlet">返回首页</a>&nbsp;<a href="/ATP-beta1/login.jsp">退出登录</a></span>
  	</div>
  	<div id="tit">错误日志</div>
  	
    <div id="content">
    	${log_content }
    </div>
    <div id="logbut">
    		
    		<input type="button" onclick="downloadLog();" value="Download"/>
    </div>
   
  </body>
</html>
