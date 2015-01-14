<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<html>

  <head>
   		<title>peizhi</title>
   		<script src="/ATP-beta1/js/jquery.js"></script>
   	<style>
   		*{
   			padding:0;
   			margin:0;
   		}
   		body{
   			background-image:url("/ATP-beta1/pic/bg.jpg");
   		}
   		
   		
   		.header{
   			width:550px;
   			height:100px;
   			text-align:center;
   			margin:auto;
   			margin-top:70px;
   		}
   		
   		#right{
   			float:right;
   			
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
   		
   	
   		
   		#logo h1{
	   			color: blue;
	   	}
	   	
	   	#content{
	   		width：100%;
	   		padding:10px 50px;
	   		box-sizing:border-box;
	   		margin-top:-10px;
	   		height:500px;
	   	}
	   	
	   	#navbar{
	   		width:20%;
	   		height:100%;
	   		border-right:1px solid blue;
	   		float:left;
	   		box-sizing:border-box;
	   		padding-top:150px;
	   	}
	   	
	   	#rightContent{
	   		width:77%;
	   		height:100%;
	   		float:right;
	   	
	   		box-sizing:border-box;
	   		padding:90px;
	   	}
	   	
	   	li{
	   		margin:10px;
	   	}
	   	
	   	li input{
	   		width:80px;
	   		height:20px;
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
   			$('#peizhi').click(function(){
   				
   				$('#rightContent').html('<img src="/ATP-beta1/pic/2.jpg" />');
   			});
   			
   			$('#jiance').click(function(){
   				window.location.href="/ATP-beta1/servlet/InputServlet";
   			});
   			
   			$('#lishi').click(function(){
   				$('#rightContent').html('<img src="/ATP-beta1/pic/3.jpg" />');
   			});
   		});
   	</script>
   </head>
  <body bgcolor="#4171F4">	
  			<div id="logo" class="header">
  				<h1>ATP测试平台</h1>
  			</div>
  			<div id="welco" class="header">
  				你好，${user}<span id="right"><a href="/ATP-beta1/login.jsp">退出登录</a></span>
  			</div>
  			<div id="content">
  				<div id="navbar">
  					<h3>Gx接口测试</h3>
  					<br>
  					<ul>
  						
  						<li><input type='button' id="peizhi" value='检查点配置'></li>
  						<li><input type='button' id="jiance" value='用例检测'></li>
  						<li><input type='button' id="lishi" value='测试历史'></li>
  						
  					</ul>
  				</div>
  				<div id="rightContent">
  					欢迎使用Gx接口测试模块！
  				</div>
  			</div>
  		
  			
  		
 </body>
</html>
