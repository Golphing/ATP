<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    
    <title>showResult</title>
    
	<style>
		* {
			padding: 0;
			margin: 0;
		}
		body{
	   			background-color:#ccffff;
	   			background-repeat: repeat-y;
	   			background-position:top right;
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
	   	
	   	#itemlist{
	   		width:1000px;
	   		height:50px;
	   		text-align:left;
	   		margin-left:0px ;
	   	}
	   	
	   	#header {
	   		width:800px;
	   		height:50px;
	   		margin-left:100px;
	   		text-align: left;
	   	}
	   	
	   	#container{
	   		margin:auto;
	   		margin-top: 150px;
	   		font-size: 25px;
	   		width:90%;	
	   	}
	   	
	   	#key{
	   		
	   		font-size: 25px;
	   	}
	   	
	   	#value{
	   		color:red;
	   		font-size: 30px;
	   	}
	   	
	   	#but1{
	   		
	   		text-align: right;
	   		
	   		float:right;
	   	}
	   	
	   	#but1 input{
	   		width:70px;
	   		height:30px;
	   		background-color: black;
	   		color:white;
	   	}
	   	#header input{
	   		width:70px;
	   		height:30px;
	   		background-color: blue;
	   		color: white;
	   	}
	   	.detail{
	   		width:800px;
			font-size:15px;
	   		margin-left:200px;
	   		margin-bottom:12px;
	
	   		display: none;
	   	}
	   	
	   	table {
	   		text-align: center;
	   		width: 900px;
	  		
	   	
	   	
	   		
	   	}
	   	
	   	#frame_title {
	   		margin:auto;
	   		margin-bottom: 5px;
	   		font-size: 22px;
	   		text-align:left;
	   		color: navy;
	   	}
	   	
	   	#frame_content {
	   		margin-bottom: 10px;
	   	}
	   	
	   	#blueColor{
	   		color:blue;
	   		font-weight:bold;
	   	}
	   	
	   	#redColor{
	   		color:red;
	   		font-weight:bold;
	   	}
	   	
	   	#greenColor{
	   		color:green;
	   		font-weight:bold;
	   	}
	</style>
	<script>
		
		function showDetail(idValue){
	//		alert(idValue);
			var e=document.getElementById(idValue);
	//		var divs=document.getElementsByName("detail");
	//		for(var i=0;i<divs.length;i++){				
	//			divs[i].style.display="none";
	//		}
			//alert(divs.length);
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
	//		alert("cuowu");
			window.open("/ATP-beta1/servlet/ShowLogServlet?cid="+Math.random());
		}
		
		function downLoadResult(){
			window.open("/ATP-beta1/servlet/DownLoadResultServlet");
		}
	</script>

  </head>
  
  <body bgcolor="black">
  		<div id="atp_logo">
  				<img src="/ATP-beta1/pic/logo.png" />
  		</div>
  		<div id="logo">
  		<h1>ATP测试平台</h1>
  		</div>
  		<div id="welco">
  				你好，${user}
  		</div>
  		<div id="container">
  			
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
							<%--
							<c:if test="${me.value.result == '配置文件不存在'}">
								<span id="value" style="color: red;">${me.value.result}</span>
								</c:if>
			    			--%>
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
	    	<div id="header">
  				<input type="button" onclick="expandAll()" value="全部展开">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  				<input type="button" onclick="closeAll()" value="全部关闭">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  				<c:if test="${logfile}">
  					<input type="button" onclick="showLog()" value="查看错误日志" style="width: 130px;">
  				
  				</c:if>
  				<input type="button" onclick="downLoadResult()" value="下载测试结果" style="width: 130px;">
  				<input type="button" onclick="clearAll()" value="清除所有操作记录" style="width: 130px;">
  			</div>
    	</div>
    	
    	
    
    	
    	
    	
</body>
</html>
