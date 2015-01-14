<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<html>

  <head>
   		<title>index</title>
   	<style>
   		*{
   			padding:0;
   			margin:0;
   		}
   		body{
   			background-image:url("/ATP-beta1/pic/bg.jpg");
   		}
   		#one {
   			width:650px;
   			height:60px;
   			color:black;
   			font-size:35px;
   			margin:auto;
   			margin-top:80px;
   		}
   		
   		div{
   			width:550px;
   			height:100px;
   			text-align:center;
   			margin:auto;
   			margin-top:70px;
   		}
   		
   		.two{
   			margin:0;
   			padding:0;
   			width:100px;
   			height:50px;
   			background:url("/ATP-beta1/pic/kscs.jpg");
   		}
   		
   		#logo{
   			margin-top:10px;
   			width:100%;
   			height:55px;
   			background-image:url("/ATP-beta1/pic/logo1.gif");
   			background-repeat:no-repeat;
   			background-position:top right;
   			text-align: center;
   			margin-bottom: 15px;
   		}
   		
   		#welco{
   			width:100%;
   			margin:auto;
   			border-bottom: 2px solid blue;
   			height:24px;
   			text-align: left;
   		}
   		
   		#sub {
   			border:2px solid yellow;
   		}
   		#sub2 {
   			border:2px dashed blue;
   			width:96px;
   			height:46px;
   		}
   		
   		#right{
   			float:right;
   			
   		}
   		
   		#logo h1{
	   			color: blue;
	   	}
   	</style>
   	<script>
   	
   		function jump(){
   			window.location.href="/ATP-beta1/servlet/InputServlet";
   		}
   		
   		function clickit(){
   			var sub=document.getElementById("sub");
   			sub.id="sub2";
   		}
   		
   		function back2it(){
   			var sub=document.getElementById("sub2");
   			sub.id="sub";
   			
   		}
   	</script>
   </head>
  <body bgcolor="#4171F4">	
  			<div id="logo">
  				<h1>ATP测试平台</h1>
  			</div>
  			<div id="welco">
  				你好，${user}<span id="right"><a href="/ATP-beta1/login.jsp">退出登录</a></span>
  			</div>
  			<h1 id="one">欢迎使用中国移动研究院ATP测试系统</h1>
  			<div>
  				<img src="/ATP-beta1/pic/kscs.jpg" onclick="jump();" onmouseover="clickit();" onmouseout="back2it();" id="sub"/>
  			</div>
  			
  			<%--<div id="footer">
  				<input type="button" onclick="clearAll()" value="查看上次测试记录" style="width: 130px;">
  				<input type="button" onclick="clearAll()" value="清除所有测试记录" style="width: 130px;">		
  			</div>
 			--%>
 </body>
</html>
