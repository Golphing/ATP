package wang.service;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
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
import wang.domain.Content;
import wang.domain.FrameTitle;
import wang.domain.Page;


public class ShowResultServlet extends HttpServlet {

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
		Map<String,Page> map4page=new HashMap<String, Page>();
		response.setContentType("text/html;charset=utf-8");
		GetT1DATA gt1d=new GetT1DATA();
		String username=(String) request.getSession().getAttribute("user");
		Map<String,String> map0=gt1d.getAllData(username);
		gt1d=null;
		Set<String> set0=map0.keySet();
		for(String key : set0){
	//		System.out.println(key+"______"+map0.get(key));
			String result=map0.get(key);
			Page page=new Page();
			page.setResult(result);           //设置结果
			GetT2DATA gt2d=new GetT2DATA();
			List<Content> frameDetail_list=new ArrayList<Content>();
			List<String> list = gt2d.getAllData(key+"_"+username, username);
			gt2d=null;
			for(String li : list){
				Content content=new Content();
	//			System.out.println(li);	
				content.setFrametitle(li);
				GetT3DATA gt3d=new GetT3DATA();
				String frameDetail=gt3d.getAllData(li); //只是一个frame的具体信息
	//			System.out.println(frameDetail+"**************************");
				content.setFramedetail(frameDetail);
				frameDetail_list.add(content);
			}
			page.setContent(frameDetail_list);
			map4page.put(key, page);
		}
		
		request.setAttribute("PcapResult", map4page);
		File logfile=(File) request.getSession().getAttribute("logFile");
		boolean exists_flag=false;
		if(logfile.exists()){
			exists_flag=true;
		}
		request.setAttribute("logfile", exists_flag);
//		System.out.println(map);
		request.getRequestDispatcher("/WEB-INF/jsp/showResult.jsp").forward(request, response);
		
			
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
