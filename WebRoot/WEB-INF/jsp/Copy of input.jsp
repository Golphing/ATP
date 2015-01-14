<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    <script src="/ATP-beta1/js/jquery.js"></script>
    <title>input</title>
    <style>
    		body{
	   			background-image:url("/ATP-beta1/pic/bg.jpg");
	   			background-repeat: repeat-y;
	   			background-position:top right;
	   		}
	   		
    		.one{
    			width:100%;
    			height:600px;
    		
    			margin:auto;
    			
    		}
    		
    		.rightcontent{
    			width:80%;
    			height:100%;
    			align:center;
    			text-align:center;
    			float:right;
    			
    			box-sizing:border-box;
    			padding-top:50px;
    			font-size:20px;
    		}
    		
    		.leftbar{
    			width:20%;
    			height:100%;
    			float:left;
    			border-right:1px solid red;
    			box-sizing:border-box;
    			padding-top:100px;
    		}
    		
    		#one {
    			width:400px;
	   			color:black;
	   			font-size:35px;
	   			margin:auto;
	   			margin-bottom:20px;
	   		}
    		#two{
    			margin-bottom:20px;
    		}
    		
    		#sub,#sub1{
    			width:80px;
    			height:41px;
    			font-size:15px;
    			background-color:green;
    			padding-top:-2px;
    		}
    		
    		#logo{
	   			margin-top:10px;
	   			width:100%;
	   			height:55px;
	   			background-image:url("/ATP-beta1/pic/logo1.gif");
	   			background-repeat:no-repeat;
	   			background-position:top right;
	   			margin-bottom: 5px;
	   			text-align: center;
	   		}
	   		
	   		#welco{
	   			width:100%;
	   			margin:auto;
	   			border-bottom: 2px solid blue;
	   			height:24px;
	   			text-align: left;
	   		}
	   		
	   		#right{
	   			float:right;	
	   		}
	   		#logo h1{
		   		color: blue;
		   	}
		   	
		   	#outer{
		   		width:80%;
		   		margin:auto;
		   	}
		   	.l1{
		   		width:100%;
		   		height:30px;
		   		
		   		margin:auto;
		   		padding:30px;
		   		text-align:left;
		   	}
		   	
		   	.l1:hover {
		   		background:blue;
		   	}
		   	
		   	
    </style>
    <script>
    	function addInput(){
    		var formd=document.getElementById("two");
    		var inputd=document.createElement("input");
    		var textd=document.createTextNode("添加包：")
    		formd.appendChild(textd);
    		inputd.type="file";
    		inputd.name="file1";
    		formd.appendChild(inputd);
    		
    		var span=document.createElement("span");
 			span.innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;";
    		formd.appendChild(span);
    		
    		var inputd1=document.createElement("input");
    		var textd1=document.createTextNode("添加配置文件：")
    		document.c
    		formd.appendChild(textd1);
    		inputd1.type="file";
    		inputd1.name="file1";
    		formd.appendChild(inputd1);
    		
    		var brd=document.createElement("br");
    		formd.appendChild(brd);
    		
    	}
    	
    	function submitt(){
    		var b=document.getElementById('sub');
    		b.disabled=true;
    		alert("数据已提交，正在处理中，请稍候！");
    	}
    	
    	$(function(){
   			$('#peizhi').click(function(){
   				
   				$('.rightcontent').html('<img src="/ATP-beta1/pic/2.jpg" />');
   			});
   			
   			$('#jiance').click(function(){
   		//		$.post("/ATP-beta1/servlet/clearData");
   				window.location.href="/ATP-beta1/servlet/InputServlet";
   			});
   			
   			$('#lishi').click(function(){
   				$('.rightcontent').html('<div id="outer"><div class="l1" id="l0">点击查看华为PCRF测试历史记录</div><div class="l1" id="l1">点击查看爱立信PCRF测试历史记录</div><div class="l1" id="l2">点击查看中兴PCRF测试历史记录</div><div class="l1" id="l3">点击查看贝尔PCRF测试历史记录</div><div class="l1" id="l4">点击查看新邮通PCRF测试历史记录</div><div class="l1" id="l5">点击查看诺基亚PCRF测试历史记录</div></div>');
   				$('#l0').click(function(){
	   				$.ajax({
							type:'post',
							url:'/ATP-beta1/servlet/GetHistoryMsg',
							data:{companyName:'huawei_pcrf_gx'},
							dataType:'text',
							success:function(msg){
						//		alert("afdasf");
							//	var aaa="<p>插入的asfdasfaf</p>"
								$('#l0').after(msg);
							}
	   				});
	   				$('#l1').click(function(){
						   				$.ajax({
												type:'post',
												url:'/ATP-beta1/servlet/GetHistoryMsg',
												data:{companyName:'ailixin_pcrf_gx'},
												dataType:'text',
												success:function(msg){
							//						alert("afdasf");
												//	var aaa="<p>插入的asfdasfaf</p>"
													$('#l1').after(msg);
												}
						   				});
					});
					$('#l2').click(function(){
						   				$.ajax({
												type:'post',
												url:'/ATP-beta1/servlet/GetHistoryMsg',
												data:{companyName:'zhongxing_pcrf_gx'},
												dataType:'text',
												success:function(msg){
								//					alert("afdasf");
												//	var aaa="<p>插入的asfdasfaf</p>"
													$('#l2').after(msg);
												}
						   				});
					});
					$('#l3').click(function(){
						   				$.ajax({
												type:'post',
												url:'/ATP-beta1/servlet/GetHistoryMsg',
												data:{companyName:'beier_pcrf_gx'},
												dataType:'text',
												success:function(msg){
										//			alert("afdasf");
												//	var aaa="<p>插入的asfdasfaf</p>"
													$('#l3').after(msg);
												}
						   				});
					});
					$('#l4').click(function(){
						   				$.ajax({
												type:'post',
												url:'/ATP-beta1/servlet/GetHistoryMsg',
												data:{companyName:'xinyoutong_pcrf_gx'},
												dataType:'text',
												success:function(msg){
										//			alert("afdasf");
												//	var aaa="<p>插入的asfdasfaf</p>"
													$('#l4').after(msg);
												}
						   				});
					});
					$('#l5').click(function(){
						   				$.ajax({
												type:'post',
												url:'/ATP-beta1/servlet/GetHistoryMsg',
												data:{companyName:'nuoxi_pcrf_gx'},
												dataType:'text',
												success:function(msg){
											//		alert("afdasf");
												//	var aaa="<p>插入的asfdasfaf</p>"
													$('#l5').after(msg);
												}
						   				});
					});
   				});
   				
   			
   		
   			});});
    </script>
  </head>
  
  <body bgcolor="#4171F4">
  		<div id="logo">
  			<h1>ATP测试平台</h1>
  		</div>
  		<div id="welco">
  				你好，${user}<span id="right"><a href="/ATP-beta1/servlet/Back2FirstServlet">返回首页</a>&nbsp;<a href="/ATP-beta1/login.jsp">退出登录</a></span>
  		</div>
  		
  		<div class="one">
  			<div class='leftbar'>
  					<h3>Gx接口测试</h3>
  					<br>
  					<ul>
  						
  						<li><input type='button' id="peizhi" value='检查点配置'></li>
  						<li><input type='button' id="jiance" value='用例检测'></li>
  						<li><input type='button' id="lishi" value='测试历史'></li>
  						
  					</ul>
  			</div>
	  		<div class="rightcontent">
	  			<h1 id="one">请导入需要检测的包</h1>
		   		<form action="${pageContext.request.contextPath}/servlet/Checking" method="post" enctype="multipart/form-data" onsubmit="submitt();">
		   		
		   			<div class="two" id="two">
		   				添加包：<input type="file" name="file1" >&nbsp;&nbsp;&nbsp;&nbsp;添加配置文件：<input type="file" name="file1"><br>
		   			</div>
		   			<input type="button" id="sub1" value="继续添加" onclick="addInput();"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" id="sub" value="核对" />
		   			
		   		</form>
		   		
	   		</div>
	   	</div>
   
   		
   		
  </body>
</html>
