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
    		#two,#three{
    			margin-bottom:20px;
    			text-align:left;
    			margin-left:100px;
    		}
    		
    		#sub,#sub1{
    			width:180px;
    			height:41px;
    			font-size:15px;
    			background-color:blue;
    			color:white;
    			padding-top:-2px;
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
		   		padding:10px;
		   		text-align:left;
		   	}
		   	
		   	.l1:hover {
		   		background:blue;
		   	}
		   	
		   	table {
		   		text-align:center;
		   		margin-left:100px;
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
    	
    		var brd=document.createElement("br");
    		formd.appendChild(brd);
    		
    	}
    	function addInput1(){
    		var formd=document.getElementById("three");
    		var inputd=document.createElement("input");
    		var textd=document.createTextNode("添加厂家配置文件：")
    		formd.appendChild(textd);
    		inputd.type="file";
    		inputd.name="file1";
    		formd.appendChild(inputd);
    	
    		var brd=document.createElement("br");
    		formd.appendChild(brd);
    		
    	}
    	
    	function submitt(){
    		var b=document.getElementById('sub');
    		b.disabled=true;
    	//	alert("数据已提交，正在处理中，请稍候！");
    	}
    	
    	$(function(){
   			$('#peizhi').click(function(){			
   				$.ajax({ 						
   						type:'post',
   						url:'/ATP-beta1/servlet/PeiZhi',
   						success:function(msg){
				//			alert("成功！");
							$('.rightcontent').html(msg);
					//	alert(msg.length);
							if(msg.length<10){
								window.open("http://localhost/ATP/index.php");
							}
							
						}
   				});
   				
   			});
   			
   			$('#jiance').click(function(){
   		//		$.post("/ATP-beta1/servlet/clearData");
   				window.location.href="/ATP-beta1/servlet/InputServlet";
   			});
   			$('#help').click(function(){
   				$.ajax({ 						
   						type:'post',
   						url:'/ATP-beta1/servlet/HelpServlet',
   						success:function(msg){
							$('.rightcontent').html(msg);
							
							}
   				});
   			});
   			
   			$('#lishi').click(function(){
   				$('.rightcontent').html('<input type="button" id="clearLishi" value="清除测试历史记录"/><div id="outer"><div class="l1" id="l0">点击查看华为PCRF测试历史记录</div><div id="huawei"></div><div class="l1" id="l1">点击查看爱立信PCRF测试历史记录</div><div id="ailixin"></div><div class="l1" id="l2">点击查看中兴PCRF测试历史记录</div><div id="zhongxing"></div><div class="l1" id="l3">点击查看贝尔PCRF测试历史记录</div><div id="beier"></div><div class="l1" id="l4">点击查看新邮通PCRF测试历史记录</div><div id="xinyoutong"></div><div class="l1" id="l5">点击查看诺基亚PCRF测试历史记录</div><div id="nuojiya"></div></div>');
   				$('#clearLishi').click(function(){
   					$.ajax({ 						
   						type:'post',
   						url:'/ATP-beta1/servlet/ClearHistory',
   						success:function(msg){
							alert(msg);
							
							}
   					});
   				});
   				
   				$('#l0').click(function(){
	   				$.ajax({
							type:'post',
							url:'/ATP-beta1/servlet/GetHistoryMsg',
							data:{companyName:'huawei_pcrf_gx'},
							dataType:'text',
							success:function(msg){
						//		alert("afdasf");
							//	var aaa="<p>插入的asfdasfaf</p>"
								$('#huawei').html(msg);
							}
	   				});
	   				
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
													$('#ailixin').html(msg);
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
													$('#zhongxing').html(msg);
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
													$('#beier').html(msg);
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
													$('#xinyoutong').html(msg);
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
												$('#nuojiya').html(msg);
												}
						   				});
					});
   				
   			
   		
   			});});
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
  						<li><input type='button' id="help" value='版本介绍'></li>
  						
  					</ul>
  			</div>
	  		<div class="rightcontent">
	  			<h1 id="one">请导入需要检测的包</h1>
		   		<form action="${pageContext.request.contextPath}/servlet/Checking" method="post" enctype="multipart/form-data" onsubmit="submitt();">
		   		
		   			<div class="two" id="two">
		   				添加包：<input type="file" name="file1" >&nbsp;&nbsp;&nbsp;&nbsp;<br>
		   			</div>
		   			<div class="three" id="three">
		   				添加厂家配置文件：<input type="file" name="file1"><br>
		   			</div>
		   			<input type="button" id="sub1" value="继续添加测试用例包" onclick="addInput();"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		   			<input type="button" id="sub1" value="继续添加 厂家配置文件" onclick="addInput1();"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		   			<input type="submit" id="sub" value="核对" onclick="return check_file()"/>
		   			
		   		</form>
		   		
	   		</div>
	   	</div>
   
   		
  
  </body>
  <script>
  		function check_file(){
  			var file_input=document.getElementsByName("file1")
  			var flag_file=false;
  			for(var i=0;i<file_input.length;i++){
  				if(file_input[i].value.length){
  					flag_file=true;
  					break;
  				}
  			}
  			if(!flag_file){
  				alert("请上传需要测试的包！！");
  			}
  			return flag_file;
  		}
  </script>
</html>
