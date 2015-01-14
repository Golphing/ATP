<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    
    <title>loginsuccess</title>
    
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
			color:blue;
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
   		
   		#num {
   			color:red;
   		}
	</style>
	<script>
		function showLog(){
			window.location.href="/ATP-beta1/servlet/ShowLogServlet";
			
		}
		
		function jump1(){
		//	alert("dsdfsd");
			window.setTimeout("jump()",5000);
			window.setInterval("chg()",1000);
		}
		
		function jump(){
			window.location.href="/ATP-beta1/login.jsp";
		}
		
		function chg(){
			var numb=document.getElementById("num");
			var num=numb.innerHTML;
			numb.innerHTML=num-1;
		//	alert(numb.innerHTML);
		}
	</script>

  </head>
  
  <body bgcolor="black" onload="jump1();">
  		<div id="logo">
  		</div>
    	<div id="error">
    		${message }
    	</div>
    	
  </body>
</html>
