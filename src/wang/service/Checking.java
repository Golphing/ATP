package wang.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;

import wang.dao.FinalUpdateT1;
import wang.dao.UpdateHistory;
import wang.dao.Write2Table1;
import wang.thread.MatchThread;
import wang.thread.UploadProperties;
import wang.utils.FileDao;

public class Checking extends HttpServlet {
	
	
//	private List<String> file_list=new ArrayList<String>();
	/**
	 * Constructor of the object.
	 */
	public Checking() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

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
		try {
			MatchThread lastThread=null;
			response.setContentType("text/html;charset=utf-8");
			boolean lflag=(Boolean) request.getSession().getAttribute("isLogin");
			if(!lflag){
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
			String username1=(String) request.getSession().getAttribute("user");
			SimpleDateFormat df = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
			String date=df.format(new Date());
			Properties prop=new Properties();
			ClassLoader loader=FileDao.class.getClassLoader();
			InputStream is=loader.getResourceAsStream("Properties/enviroment.properties");
			prop.load(is);
			String log_path=prop.getProperty("logFile_savePath");
			String filesave_path=prop.getProperty("tempfile_savePath");
			String prop_save_path=prop.getProperty("propertyfile_savePath");
			is.close();
			String save_path=log_path+username1+"_"+date+".txt";
			request.getSession().setAttribute("logpath", save_path);
			FileDao file_dao=new FileDao(save_path);
			File log_file=file_dao.getLogFile();
			request.getSession().setAttribute("logFile", log_file);
			/*if(log_file.exists()){
				while(log_file.delete());
			}*/
			DiskFileUpload upload=new DiskFileUpload();
			
			upload.setHeaderEncoding("UTF-8");	
			List list=upload.parseRequest(request);
	//		System.out.println(list.size());
			if(list != null){
				for(int i=0;i<list.size();i++)    
				{  
				   FileItem item = (FileItem)list.get(i);
				   String filename=item.getName();
				   if(!(filename.trim().equals(""))){
					   if(filename.endsWith(".property")){
			//			   System.out.println("上传的是配置文件");
			//			   UploadProperties up=new UploadProperties(item, username1);
				//		   up.start();
					   }else if(filename.endsWith(".properties")){
						   System.out.println("私有配置文件");
		//	------------------------------------------------------------------
						  
							
				//			未完成
							String save_name=item.getName();
					//		String save_name=username1+"_"+newFileName;
							String path=prop_save_path+username1+"/"+save_name;
							 File property_file = new File(path);
							   if (!property_file.getParentFile().exists()) {
								   property_file.getParentFile().mkdirs();
							   }
							   property_file.createNewFile();
							   InputStream is1=item.getInputStream();
							   byte[] buf = new byte[4048];
							   int length=0;
							   FileOutputStream fops=new FileOutputStream(property_file);
							   while((length=is1.read(buf))>0){
								   String line=new String(buf, 0, length);
								  	fops.write(buf,0, length);
							   }
							   fops.close();
							   is1.close();
		//	-------------------------------------------------------------------
					   }else{
						   continue;
					   }
				   }
				} 
				Thread.sleep(3000);
				for(int i=0;i<list.size();i++)    
				{  
				   FileItem item = (FileItem)list.get(i);
				   String filename=item.getName();
				   if(!(filename.trim().equals(""))){
					   if(filename.endsWith(".property") || filename.endsWith(".properties")){
						  continue;
					   }else{
						   int lastindex=filename.lastIndexOf(".");
						   String db_file_name= filename.substring(0, lastindex)+"_"+username1;
					//		   System.out.println("-----"+db_file_name);
						   Write2Table1 w2t1=new Write2Table1();
						   w2t1.insertPcapFile(username1, db_file_name);
						   w2t1=null;
						   String path = filesave_path+username1+"/"+username1+"_"+filename;
						   MatchThread mt=new MatchThread(item,file_dao,username1,path);
						   mt.setName("Thread"+i);
						   lastThread=mt;  
						   mt.start();
					   }
				   }
				} 
				if(lastThread != null)
					lastThread.join();
			}
				Thread.sleep(1000);
				FinalUpdateT1 fut1=new FinalUpdateT1(username1);
				fut1.updateT1();
				UpdateHistory uh=new UpdateHistory();
				uh.updateHistory(username1);
				request.setAttribute("message", "测试完成<script>window.open('/ATP-beta1/servlet/ShowResultServlet?cid='+Math.random());</script>");
				request.getRequestDispatcher("/WEB-INF/jsp/result.jsp").forward(request, response);
				/*if(log_file.exists()){
					request.setAttribute("message", "处理有异常，点击打开错误日志，查看错误消息！");
					request.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(request, response);
				}else{
					request.setAttribute("result", "处理成功，点击查看结果!!");
					request.getRequestDispatcher("/WEB-INF/jsp/success.jsp").forward(request, response);
				}*/
		//		int file_num=MatchThread.list.size();
			//	System.out.println(file_num);
				//System.out.println(MatchThread.list);
		}catch(SecurityException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		} 
		catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
