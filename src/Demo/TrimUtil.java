package Demo;

import org.junit.Test;

public class TrimUtil {
	@Test
	public void tt(){
		
		String one="<title>测试结果</title>      	<style> 		* { 			padding: 0; 			margin: 0; 		} 		body{ 	   			background-color:teal; 	   			background-repeat: repeat-y; 	   			background-position:top right; 	   	}  	   	#itemlist{ 	   		width:1000px; 	   		height:50px; 	   		text-align:left; 	   		margin-left:0px ; 	   	} 	   	  	   	#container{ 	   		margin-top: 50px; 	   		font-size: 25px;	 	   	} 	 	   	  	  	   	.detail{ 	   		width:800px; 			font-size:15px; 	   		margin-left:200px; 	   		margin-bottom:12px;	 	   		display: none; 	   	} 	   	 	   	table { 	   		text-align: center; 	   		width: 900px;	 	   	} 	   	 	   	#frame_title { 	   		margin:auto; 	   		margin-bottom: 5px; 	   		font-size: 22px; 	   		text-align:left; 	   		color: navy; 	   	} 	   	 	   	#frame_content { 	   		margin-bottom: 10px; 	   	} 	</style>";
		one=one.replaceAll("\\s", "");
		System.out.println(one);
	}
}
