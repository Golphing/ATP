<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    
    <title>系统登录</title>
       <style>
       		body{
	   			background-image:url("/ATP-beta1/pic/bg.jpg");
	   		}
	   		table{
	   			margin-top:40px;
	   		}
	   		
	   		h1{
	   			margin-bottom:40px;
	   		}
	   		
	   		span{
	   			color: red;
	   			
	   		}
	   		
	   		.input{
	   			width:200px;
	   			height:20px;
	   		}
	   		
	   		.butt{
	   			margin-left:10px;
	   			width:70px;
	   			height:25px;
	   			color:white;
	   			background-color: blue;
	   		}
	   		
	   		
	   		#logo h1{
	   			color: blue;
	   		}
	   		
	   		#atp_logo{
				height:55px;
				float:left;
			}
			
			#logo{
	   			margin-top:10px;
	   			width:87%;
	   			float:right;
	   			height:55px;
	   			background-image:url("/ATP-beta1/pic/logo1.gif");
	   			background-repeat:no-repeat;
	   			background-position:top right;
	   			text-align: center;
	   			margin-bottom: 15px;
	   			border-bottom:4px solid blue;
	   		
	   		}
	   </style>
	   
	   <script>
	   		function register(){
	   			window.location.href="/ATP-beta1/register.jsp?cid="+Math.random();
	   		}
	   
	   </script>
	
  </head>
  
  <body>
    		<%
	  			request.getSession().removeAttribute("user");
	  		%>
	  		<div id="atp_logo">
  				<img src="/ATP-beta1/pic/logo.png" />
  			</div>
	  		<div id="logo">
	  			<h1>ATP测试平台</h1>
  			</div>
	    	<table align="center" width="350px" height="100px" border=0 cellspacing=0>
	    		<caption><h1>用户登录系统</h1></caption>
	    		
	    		<form action="/ATP-beta1/servlet/LoginServlet" method="post">
	    		<tr><td>用户名:</td><td><input type="text" name="username" class="input" value="${username}"></td></tr>
	    		<tr><td>密&nbsp;&nbsp;&nbsp;&nbsp;码:</td><td><input type="password" name="password" class="input"></td></tr>
	    		<tr><td colspan=2 align="center"><span>${message }</span></td></tr>
	    		<tr><td align="center" colspan=2><input type="reset" value="重置" class="butt"><input class="butt" type="submit" value="登录"><input type="button" class="butt" value="注册" onclick="register();"/></td></tr>
	    		</form>
	    	</table>
  </body>
</html>
