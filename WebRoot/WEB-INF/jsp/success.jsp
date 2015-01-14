<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    
    <title>success</title>
    <style>
    			body{
		   			background-image:url("/ATP-beta1/pic/bg.jpg");
		   			background-repeat: no-repeat;
		   			background-position:top right;
		   		}
			    #logo{
			   			margin-top:10px;
			   			width:100%;
			   			height:80px;
			   			background-image:url("/ATP-beta1/pic/logo1.gif");
			   			background-repeat:no-repeat;
			   			background-position:top right;
			   	}
			   	
    			#one {
					width:800px;
					height:50px;
					font-size:40px;
					color:green;
					padding-left:30px;
					text-align:center;
					margin:auto;
					margin-top: 70px;
					background-color: purple;
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

  </head>
  
  <body>
	  	<div id="logo">
	  	</div>
	  	<div id="welco">
  				你好，${user}<span id="right"><a href="/ATP-beta1/servlet/Back2FirstServlet">返回首页</a>&nbsp;<a href="/ATP-beta1/login.jsp">退出登录</a></span>
  		</div>
	  	<div id="one">
	   		 ${result }
	    </div>
  </body>
</html>
