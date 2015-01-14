<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    <script src="/ATP-beta1/js/jquery.js"></script>
    <title>input</title>
    <style>
    		body{
	   			background-image:url("/ATP-beta1/pic/bg.jpg");
	   			background-repeat: no-repeat;
	   			background-position:top right;
	   		}
	   		
    		.one{
    			width:100%;   		
    			margin:auto;   			
    		}
    		
    		.rightcontent{
    			width:80%;
    			height:100%;
    			align:center;
    			text-align:center;
    			float:right;
    			border-left:1px solid red;
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
		   	
		   	#clearFloat {
		   		clear:both;
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
   				window.location.href="/ATP-beta1/servlet/InputServlet";
   			});
   			
   			$('#lishi').click(function(){
   				$('.rightcontent').html('<img src="/ATP-beta1/pic/3.jpg" />');
   			});
   		});
    	function showDetail(idValue){
			var e=document.getElementById(idValue);
			e.style.display=e.style.display=="block"?"none":"block";
		}
		
		function expandAll(){
			var divs=document.getElementsByName("detail");
			for(var i=0;i<divs.length;i++){				
				divs[i].style.display="block";
			}
		}
		
		function closeAll(){
			var divs=document.getElementsByName("detail");
			for(var i=0;i<divs.length;i++){				
				divs[i].style.display="none";
			}
		}
		
		function clearAll(){
		
			var answer=confirm("确定要清除用户所有的操作数据？");
			if(answer){
				window.location.href="/ATP-beta1/servlet/DeleteAllUserFileServlet";
			}
		}
		
		function showLog(){
			window.open("/ATP-beta1/servlet/ShowLogServlet?cid="+Math.random());
		}
		
		function downLoadResult(){
			window.open("/ATP-beta1/servlet/DownLoadResultServlet");
		}
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
		  		<c:forEach var="me" items="${PcapResult}">
		    			<div id="itemlist">
		    				用例&nbsp;&nbsp;<span id="key">${me.key}</span>&nbsp;&nbsp;
		    				测试结果：&nbsp;&nbsp;
								<c:if test="${me.value.result == '通过'}">
									<span id="value" style="color: blue;">${me.value.result}</span>
								</c:if>
								<c:if test="${me.value.result != '通过'}">
									<span id="value" style="color: red;">${me.value.result}</span>
								</c:if>
				    			<span id="but1">
				    					<input type="button" onclick="showDetail('${me.key}')" value="查看详情">
				    			</span>
		    			</div>
		    			<div id="${me.key}" class="detail" name="detail">
		    
		    				<c:forEach var="content" items="${me.value.content}">
		    						<div id="frame_title">${content.frametitle}</div>
		    						<div id="frame_content">${content.framedetail}</div>
		    				</c:forEach>
		    			</div>
		    			
		    	</c:forEach>
		    	<div id="clearFloat"></div>
		    	<div id="header">
	  				<input type="button" onclick="expandAll()" value="全部展开">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  				<input type="button" onclick="closeAll()" value="全部关闭">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  				<c:if test="${logfile}">
	  					<input type="button" onclick="showLog()" value="查看错误日志" style="width: 130px;">
	  				
	  				</c:if>
	  				<input type="button" onclick="downLoadResult()" value="下载测试结果" style="width: 130px;">
	  				<input type="button" onclick="clearAll()" value="清除所有操作记录" style="width: 130px;">
	  			</div>
	  			<div id="clearFloat"></div>
	  			
	   		</div>
	   	</div>
   
   		
   		
  </body>
</html>
