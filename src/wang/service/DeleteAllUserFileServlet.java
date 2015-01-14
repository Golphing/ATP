package wang.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wang.dao.ClearUserTable;

public class DeleteAllUserFileServlet extends HttpServlet {

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

		response.setContentType("text/html;charset=utf-8");
//		response.getWriter().write("删除成功！");
		ClassLoader loader=this.getClass().getClassLoader();
		InputStream is=loader.getResourceAsStream("Properties/enviroment.properties");
		Properties prop=new Properties();
		prop.load(is);
	//	String prop.getProperty("logFile_savePath");
		String username=(String) request.getSession().getAttribute("user");
		String pcap_file=prop.getProperty("tempfile_savePath");
		String property_file=prop.getProperty("propertyfile_savePath");
		String pcapPath=pcap_file+username;
		String propPath=property_file+username;
	//	System.out.println(pcapPath);
//		System.out.println(propPath);
		File file=new File(propPath);
		File file1=new File(pcapPath);
		deleteFile(file1);
		deleteFile(file);
		ClearUserTable cut1=new ClearUserTable();
		cut1.getAllTable(username);
		cut1=null;
		request.setAttribute("result", "操作记录清除成功,谢谢！！");
		request.getRequestDispatcher("/WEB-INF/jsp/success.jsp").forward(request, response);
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

	private void deleteFile(File file) {
		if (file.exists()) { // 判断文件是否存在
			if (file.isFile()) { // 判断是否是文件
				file.delete(); // delete()方法 你应该知道 是删除的意思;
			} else if (file.isDirectory()) { // 否则如果它是一个目录
				File files[] = file.listFiles(); // 声明目录下所有的文件 files[];
				for (int i = 0; i < files.length; i++) { // 遍历目录下所有的文件
					this.deleteFile(files[i]); // 把每个文件 用这个方法进行迭代
				}
			}
			file.delete();
		} else {
			System.out.println("所删除的文件不存在！" + '\n');
		}
	}
	
}
