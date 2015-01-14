<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    
    <title>result</title>
    <style>
    			body{
		   			background-image:url("/ATP-beta1/pic/bg.jpg");
		   			background-repeat: no-repeat;
		   			background-position:top right;
		   		}
			    #logo{
			   			margin-top:10px;
			   			width:100%;
			   			height:55px;
			   			background-image:url("/ATP-beta1/pic/logo1.gif");
			   			background-repeat:no-repeat;
			   			background-position:top right;
			   	}
			   	
    			#one {
					width:800px;
					height:50px;
					font-size:40px;
					color:red;
					padding-left:30px;
					text-align:center;
					margin:auto;
					margin-top: 70px;
					background-color: purple;
					border-bottom: center;
				}
	
			   	#right{
			   			float:right;	
			   	}
			   	
			   	#logo h1{
		   			color: blue;
		   		}
    	
    </style>

  </head>
  
  <body>
	  	<div id="logo">
	  		<h1>ATP测试平台</h1>
	  	</div>
	  	
	  	<div id="one">
	   		 ${message }
	    </div>
  </body>
</html>
