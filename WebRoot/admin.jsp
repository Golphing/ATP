<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    
    <title>用户注册</title>
	<style>
       		body{
	   			background-image:url("/ATP-beta1/pic/bg.jpg");
	   		}
	   		table{
	   			margin-top:40px;
	   		}
	   		
	   		h1{
	   			margin-bottom:30px;
	   		}
	   		
	   		.span{
	   			width:290px;
	   			height:23px;
	   			text-align:left;
	   			padding-left:60px;
	   			color: blue;
	   			
	   		}
	   		
	   		span{
	   			color: red;		
	   		}
	   		
	   		.input{
	   			width:200px;
	   			height:23px;
	   		}
	   		
	   		.butt{
	   			margin-left:10px;
	   			width:70px;
	   			height:25px;
	   			background-color: green;
	   		}
	   		
	   		#logo{
	   			margin-top:10px;
	   			width:100%;
	   			height:55px;
	   			background-image:url("/ATP-beta1/pic/logo1.gif");
	   			background-repeat:no-repeat;
	   			background-position:top right;
	   			border-bottom: 2px solid black;
	   			text-align: center;
	   		}
	   		
	   		#logo h1{
	   			color: blue;
	   		}
	   </style>
	   <script>
	   		function gologin(){
	   			window.location.href="/ATP-beta1/login.jsp";
	   		}
	   </script>
  </head>
  
  <body>
    		<div id="logo">
    			<h1>ATP测试平台</h1>
  			</div>
	    	<table align="center" width="420px" height="100px" border=0 cellspacing=0>
	    		<caption><h1>用户注册</h1></caption>
	    		
	    		<form action="/ATP-beta1/servlet/RegisterServlet4Admin" method="post">
	    		<input type="hidden" name="power" value="yes" />
	    		<tr><td>用户名:</td><td><input type="text" name="username" class="input" value="${username}"><span>${username_msg}</span></td></tr>
	    			<tr><td colspan="2" class="span">用户名必须为字母或数字，不得超过8位</td></tr>
	    		<tr><td>密&nbsp;&nbsp;&nbsp;&nbsp;码:</td><td><input type="password" name="password" class="input"><span>${password_msg}</span></td></tr>
	    		<tr><td colspan="2" class="span">密码必须为字母或数字，不得超过8位</td></tr>
	    		<tr><td>确认密码:</td><td><input type="password" name="password1" class="input"><span>${password1_msg}</span></td></tr>
	    		<tr><td align="center" colspan=2><input type="reset" value="重置" class="butt"><input class="butt" type="submit" value="注册"><input class="butt" type="button" value="去登录" onclick="gologin();"></td></tr>
	    		
	    			
	    		</form>
	    	</table>
  </body>
</html>
