package wang.thread;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.output.FileWriterWithEncoding;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import wang.dao.UpdateTable1;
import wang.dao.Write2Table2;
import wang.utils.FileDao;

import exception.NoPropertiesFileException;

import Demo.PropertiesDemo;

public class MatchThread extends Thread{
	
	private FileItem item;
	private DealPacket lastThread;
	private FileDao file_dao;
	private String username;
	private UpdateTable1 utt1;
	private String path;
//	private List list;
	
	
	public MatchThread(FileItem item,FileDao file_dao,String name,String path) {
		super();
		this.item = item;
		this.username=name;	
		this.file_dao = file_dao;
		utt1=new UpdateTable1();
		this.path=path;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			super.run();
//			System.out.println("*************:"+this.getName()+"************");
			 String filename = item.getName(); 
			 if(!filename.trim().equals("")){
	//			   System.out.println(filename);
				   int pos=filename.lastIndexOf("_");
				   int pos1=filename.lastIndexOf(".");
				   String caseName=filename.substring(++pos,pos1);	   
		//		   System.out.println(caseName);
				  
				   Properties prop=new Properties();
					ClassLoader loader=this.getClass().getClassLoader();
					InputStream is1=loader.getResourceAsStream("Properties/enviroment.properties");
					prop.load(is1);
					String propPath=prop.getProperty("yongli_peizhi")+caseName+".xml";
					System.out.println(propPath);
			//			String propPath="Properties/"+username+"_"+caseName+".property";
	//				System.out.println("---"+propPath);
					is1.close();
			//		String filesave_path=prop.getProperty("tempfile_savePath");
					
			//	   String path = filesave_path+username+"_"+filename;
	//			   list.add(path);
		//		   System.out.println(path);
				   File pcap_file = new File(path);
				   if (!pcap_file.getParentFile().exists()) {
					   pcap_file.getParentFile().mkdirs();
				   }
				   pcap_file.createNewFile();
				   InputStream is=item.getInputStream();
				   byte[] buf = new byte[4048];
				   int length=0;
				   FileOutputStream fops=new FileOutputStream(pcap_file);
				   while((length=is.read(buf))>0){
					   String line=new String(buf, 0, length);
					  	fops.write(buf,0, length);
				   }
				   fops.close();
				   is.close();
				   
				   	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); 
					DocumentBuilder builder=factory.newDocumentBuilder();
		//			ClassLoader loader=this.getClass().getClassLoader();
		//			URL url=loader.getResource(propPath);
					File xmlfile=new File(propPath);
					if(xmlfile.exists()){
			//			String p=url.getPath();
				//		String dd=URLDecoder.decode(p, "utf-8");
			//			System.out.println(dd);
						
						Document document=builder.parse(xmlfile);
						XPathFactory xFactory = XPathFactory.newInstance();  
				        XPath xpath = xFactory.newXPath(); 
				       /* String value=(String) xpath.evaluate("/pcap/packet/property[@name='frame_name']/text()", document, XPathConstants.STRING);
				        System.out.println(value);*/  
						NodeList packets = (NodeList) xpath.evaluate("/pcap/packet",document, XPathConstants.NODESET);
						for (int i = 0; i < packets.getLength(); i++) {
							Node prop_packet = packets.item(i);
							Element pp=(Element) prop_packet;			
							String pname=xpath.evaluate("property[@name='frame_name']", prop_packet);
							Node frame_node=(Node) xpath.evaluate("property[@name='frame_name']", prop_packet, XPathConstants.NODE);
							Element frame_ele=(Element) frame_node;
					//		System.out.println(frame_ele);
							String frame_real_name=frame_ele.getAttribute("alias");
							System.out.println(frame_real_name+"-------------------");
							int lastindex=filename.lastIndexOf(".");
							 String db_file_name= filename.substring(0, lastindex);
							Write2Table2 w2t2=new Write2Table2();
							String tableName="Frame2Pcap_"+username;
					//		String volum1=pname+"_"+db_file_name+"_"+username;
							String volum1=pname+"_"+frame_real_name+"_"+db_file_name+"_"+username;
							String volum2=db_file_name+"_"+username;
							w2t2.insert2T2(tableName, volum1, volum2);
							DealPacket dp=new DealPacket(prop_packet,pcap_file,file_dao,tableName, username,volum2);
							lastThread=dp;
							dp.setName(volum1);
							dp.start();
							w2t2=null;
						}
						if(lastThread !=null)
							lastThread.join();
							
					}else{
						 int lastindex=filename.lastIndexOf(".");
						   String db_file_name= filename.substring(0, lastindex)+"_"+username;
						 String sql="update "+username+" set check_result='2' where packet_name='"+db_file_name.replace('.', '_')+"'" ;
			//			 System.out.println(sql);
						 utt1.fileError(sql);
						 
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        			String date=df.format(new Date());
	        			synchronized (file_dao){
							
	        				file_dao.getfilewriter("********   "+date+"   *********\n");
	        				file_dao.getfilewriter("配置文件 "+caseName+" 不存在！请检查输入的包是否符合规范或增加相应的配置文件！！\n\n");
						}
						throw new NoPropertiesFileException("配置文件不存在！");
					}
			 }else{
				
				 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     			String date=df.format(new Date());
     			synchronized (file_dao) {
     				file_dao.getfilewriter("********   "+date+"   *********\n");
     				file_dao.getfilewriter("未导入任何包\n\n");
				}
			 }
		}catch(NoPropertiesFileException e){
		//	e.printStackTrace();
		//	throw new RuntimeException(e);
		}catch(Exception e){
			e.printStackTrace();
		//	throw new RuntimeException(e);
			utt1=null;
			
		}
	
	}
	
}
