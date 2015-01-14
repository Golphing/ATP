<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<html>

  <head>
   		<title>select</title>
   		<script src="/ATP-beta1/js/jquery.js"></script>
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
   		
   		#selec {
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
   		
   		#atp_logo{
			height:55px;
			float:left;
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
   			width:87%;
   			float:right;
   			padding-bottom:10px;
   			border-bottom:4px solid blue;
   			margin-right:0;
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
	   	
	   	.butt {
	   		width:130px;
	   		height:50px;
	   		margin:10px;
	   	}
	   	
	   	#abl{
	   		color:white;
	   		background-color: blue;
	   	}
	   	#hidMsg{
	   		display:none;
	   		text-align: center;
	   		font-size: 50px;
	   		color:blue;
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
   		$(function(){
   			$('#abl').click(function(){
   		//	alert("aa");
   			window.location.href="/ATP-beta1/servlet/InputServlet";
   		});
   		});
   		
   	</script>
   </head>
  <body bgcolor="#4171F4">	
  			<div id="atp_logo">
  				<img src="/ATP-beta1/pic/logo.png" />
  			</div>
  			<div id="logo">
  				<h1>ATP测试平台</h1>
  			</div>
  			<div id="welco">
  				你好，${user}<span id="right"><a href="/ATP-beta1/login.jsp">退出登录</a></span>
  			</div>
  			<h1 id="one">欢迎使用中国移动研究院ATP测试系统</h1>
  			<div id="selec">
  				<input type='button' value="Gx接口测试" class="butt" id="abl">
  				<input type='button' value="S1接口测试" class="butt disabl showHid"><br>
  				<input type='button' value="S11接口测试" class="butt disabl showHid">
  				<input type='button' value="S6a接口测试" class="butt disabl showHid">
  			</div>
  			<div id="hidMsg">
  				该功能暂未开通，尽请期待。。。
  			</div>
  			
  		
 </body>
 <script>
 	$(".showHid").click(function(){
 		alert("该功能暂未开通，尽请期待。。。。");
 		
 	});
 </script>
</html>
