package wang.service;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wang.dao.GetT1DATA;
import wang.dao.GetT2DATA;
import wang.dao.GetT3DATA;

public class DownLoadResultServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		boolean lflag=(Boolean) request.getSession().getAttribute("isLogin");
		if(!lflag){
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		response.setContentType("application/octet-stream");
        response.setContentType("application/OCTET-STREAM;charset=UTF-8");
        String username=(String) request.getSession().getAttribute("user");
        Writer write=response.getWriter();
      try{
	        SimpleDateFormat df = new SimpleDateFormat("yyyy_MM_dd_HH_mm");
			String date=df.format(new Date());
	        String download_file_name=username+"_"+date+"测试结果.html";
			response.setHeader("Content-Disposition", "attachment;filename=" + download_file_name);
			
			write.write("<html><head><title>测试结果</title><style>*{padding:0;margin:0;}body{background-color:teal;background-repeat:repeat-y;background-position:topright;}");
			write.flush();
			write.write("#itemlist{width:1000px;height:50px;text-align:left;margin-left:0px;}#container{margin:auto;margin-top:50px;font-size:25px;width:1000px;}");
			write.flush();
			write.write(".detail{width:800px;font-size:15px;margin-left:200px;margin-bottom:12px;}table{text-align:center;width:900px;}#frame_title{margin:auto;margin-bottom:5px;font-size:22px;text-align:left;color:navy;}#frame_content{margin-bottom:10px;}</style></head><body>");
			write.flush();
			GetT1DATA gt1d = new GetT1DATA();
			Map<String,String> map0=gt1d.getAllData(username);
			Set<String> set=map0.keySet();
			
			write.write("<div id='container'>");
			write.flush();
			for(String key : set){
				write.write("<div id='itemlist'>");
				write.flush();
		//		System.out.println(key);
				write.write(key+"&nbsp;：&nbsp;&nbsp;&nbsp;<span style='color:red;'>"+map0.get(key)+"</span>");
				write.flush();
				
				write.write("</div>");
				write.flush();
				write.write("<div class='detail'>");
				write.flush();
				GetT2DATA gt2d=new GetT2DATA();
				List<String> frameDetail=gt2d.getAllData(key+"_"+username, username);
				for(String table3_name : frameDetail){
			//		System.out.println(table3_name);
					write.write("<div id='frame_title'>");
					write.write(table3_name+"</div>");
					write.flush();
					GetT3DATA gt3d=new GetT3DATA();
					String test_result=gt3d.getAllData(table3_name);
				//	System.out.println(test_result);
					write.write("<div id='frame_content'>");
					write.write(test_result+"</div>");
					write.flush();
					
				}
				
				write.write("</div>");
				write.flush();
			}
			
			write.write("</div></body></html>");
			write.close();
      }catch(Exception e){
    	  write.write("下载失败");
      }finally{
    	  if(write != null){
    		  write.close();
    	  }
      }
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
