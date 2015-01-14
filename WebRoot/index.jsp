<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<html>

  <head>
   		<title>ATP</title>
   	<style>
   		*{
   			padding:0;
   			margin:0;
   		}
   		body{
   			background-image:url("/ATP-beta1/pic/bg.jpg");
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
   		
   		#title{
   			width:87%;
   			float:right;
   			padding-bottom:10px;
   			border-bottom:4px solid blue;
   			margin-right:0;
   		}
   		
   		#logo h1{
	   			color: blue;
	   	}
	   	
	   	#content{
	   		height:500px;
	   		width:100%;
	   		box-sizing:border-box;
	   	}
	   	#left,#right {
	   		width:48%;
	   		height:100%;
	   		
	   		box-sizing:border-box;
	   		
	   	}
	   	
	   	#left{
	   		float:left;
	   		padding:20px;
	   		padding-top:110px;
	   		padding-left:90px;
	   		color:black;
	   		font-size:30px;
	   	}
	   	
	   	#right{
	   		float:right;
	   /*		background:url("/ATP-beta1/pic/1.jpg");
	   		background-repeat:no-repeat;*/
	   		padding-top:180px;
	   		padding-left:90px;
	   	}
   	</style>
   	<script>
   	
   	
   	</script>
   </head>
  <body bgcolor="#4171F4">	
  			<div id="atp_logo">
  				<img src="/ATP-beta1/pic/logo.png" />
  			</div>
  			<div id="logo">
  				<h1>ATP测试平台</h1>
  			</div>
  			<div id="title">
  				<a href="/ATP-beta1/login.jsp">登陆</a>
  				<a href="/ATP-beta1/register.jsp">注册</a>
  			</div>
  			<div id="content">
  				<div id="left">
  					
  					 ATP(Auto Test Platform)是一款专门为
核心网网元接口互通测试而设计的测试工具，旨在实现测试自动化，减少人力物力，它逻辑清晰，
使用方便，即刻体验！
        目前Beta版本仅针对PCC的Gx接口进行测试验证，更多接口类型，欢迎访问cmri.hq.cmcc！
  					
  				</div>
  				<div id="right">
  						<img src="/ATP-beta1/pic/1.jpg">
  				</div>
  			</div>

 </body>
</html>
