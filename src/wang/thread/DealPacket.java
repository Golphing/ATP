package wang.thread;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.io.output.FileWriterWithEncoding;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import wang.dao.UpdateTable1;
import wang.dao.UpdateTable2;
import wang.dao.Write2Table3;
import wang.utils.FileDao;

import exception.NoFrameException;

public class DealPacket extends Thread {
	private Node prop_packet;
	private File pcap_file;
	private Node selected_packet=null;
	private FileDao file_dao;
	private String table_name;
	private String username;
	private String source_company_name;
	private String des_company_name;
	private String case_num;
	private String table1_name;

	public DealPacket(Node packet, File file,FileDao file_dao,String table_name,String name,String t1name) {
		super();
		this.prop_packet = packet;
		this.pcap_file = file;
		this.file_dao=file_dao;
		this.table_name=table_name;
		this.username=name;
		this.table1_name=t1name;
	}

	@Override
	public void run(){
		// TODO Auto-generated method stub
		try{
			int startIndex=this.getName().indexOf("_")+1;
	//		System.out.println(this.getName());
			int endIndex=this.getName().indexOf("_",8);
			//this.getName().indexOf("_", 6);
//			char ch=this.getName().charAt(this.getName().indexOf("_"));
		//	System.out.println(ch);
			String company_name=this.getName().substring(startIndex,endIndex);
	//		System.out.println(company_name);
			this.source_company_name=company_name;
			int startIndex1=endIndex+1;
			int endIndex1=this.getName().indexOf("_", startIndex1);
			des_company_name=this.getName().substring(startIndex1, endIndex1);
			int startIndex2=endIndex1+1;
			int endIndex2=this.getName().lastIndexOf("_");
			case_num=this.getName().substring(startIndex2, endIndex2);
		//	System.out.println(case_num);
		//	System.out.println(des_company_name);
	//		System.out.println(pcap_file.getName());
			super.run();
	//		System.out.println(this.getName());
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); 
			DocumentBuilder builder=factory.newDocumentBuilder();
			Document document=builder.parse(pcap_file);
			
			XPathFactory xFactory = XPathFactory.newInstance();  
	        XPath xpath = xFactory.newXPath(); 
	        NodeList plist=(NodeList) xpath.evaluate("/pdml/packet", document, XPathConstants.NODESET);
	        String frameName=xpath.evaluate("property[@name='frame_name']", prop_packet);
	//        System.out.println("要匹配的frame:"+frameName);
	        //在导入的包中选择配置文件中对应的frame
	        for(int i=0;i<plist.getLength();i++){
	        	XPathFactory xFactory1 = XPathFactory.newInstance();  
		        XPath xpath1 = xFactory1.newXPath(); 
	        	Node pcap_packet=plist.item(i);
	        	Node li=(Node) xpath1.evaluate("proto[@name='frame']",pcap_packet, XPathConstants.NODE);
	        	Element proto=(Element) li;
	//        	System.out.println(proto.getAttribute("showname"));
	        	String showname=proto.getAttribute("showname");
	        	if(showname != null){
	        		
			        	if(showname.contains(frameName)){
			        		selected_packet=pcap_packet;
			        		break;
			        	}
	        	}
	        }
	        
	        //在选出的frame中按照配置文件中的字段进行核对结果
	        if(selected_packet == null){
        		try{
        			UpdateTable2 ut2=new UpdateTable2();
        			UpdateTable1 ut1=new UpdateTable1();
        			table1_name=table1_name.replaceAll("\\.", "_");
        			String sql="update "+username+" set check_result='1' where packet_name='"+table1_name+"'";
       // 			System.out.println(sql);
        			ut1.fileError(sql);
        			
        			String frame_name=this.getName();
      //  			System.out.println(frame_name+"************");
        			ut2.noFrame(table_name,frame_name);
        			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        			String date=df.format(new Date());
        			synchronized (file_dao) {
        				file_dao.getfilewriter("********   "+date+"   *********\n");
        				file_dao.getfilewriter(frame_name+"未找到对应的frame\n\n");	
					}
	        		throw new NoFrameException("未找到对应的frame");
	        	}catch(Exception e){
        	//		e.printStackTrace();
        		//	throw new RuntimeException(e);
        		}
        	}else{
        		
        	//具体的核对过程。。
    //    		System.out.println("找到");
      //  		System.out.println("匹配ing...");
        		
		   /*     NodeList nodel=(NodeList) xpath2.evaluate("field", selected_packet, XPathConstants.NODESET);
		        System.out.println(frameName+"中field的个数为："+nodel.getLength());
		        for(int j=0;j<nodel.getLength();j++){
		        	Element e=(Element) nodel.item(j);
		        	System.out.println(e.getAttribute("name"));	
		        }*/
        		Write2Table3 wt3=new Write2Table3();
        		wt3.createTable3(this.getName());
        //		System.out.println(this.getName());
        	
        		XPathFactory xFactory2 = XPathFactory.newInstance();  
		        XPath xpath2 = xFactory2.newXPath();
		        NodeList field_list=(NodeList) xpath2.evaluate("field", prop_packet,XPathConstants.NODESET);
		        for(int j=0;j<field_list.getLength();j++){
		        	Element e=(Element) field_list.item(j);
		        	String path=e.getAttribute("path");
		        	String showName=e.getAttribute("showname");
		        	String check_value=e.getTextContent();	
		        	String mustOrnot=e.getAttribute("must");		        
		        	String subField=e.getAttribute("subField");	
		        	Node pcap_field=(Node) xpath2.evaluate(path, selected_packet,XPathConstants.NODE);
		        	if("false".equals(mustOrnot)){
		        		// 针对选项进行处理，此处处理可选项
		//        		System.out.println("可选项");
		        		if(pcap_field != null ){
		        			// 携带了可选项，分几种情况：  1. 携带的信息无法判定
		        			//  					    2. 能够判定的固定值
		        			//							3. 特有的值，需要从特殊的哦配置文件中读取
		        			//							4. 判断是否包含字段，只用输出即可，无需判定值
	//------------------------------------------可选开始----------------------------------------------------------------------
		        			Element pf=(Element) pcap_field;
			        		if(subField.equals("true")){													//检测是否包含特定的字段
			      //  			System.out.println("需要检测是否包含字段");
			        			String AVPName=showName;
		        				String mustOrNot="可选";
		        				String preset_value="检查是否包含字段：<br>";
		        				String actual_value="";
		        				String final_result="待办";;
		        		//-------------------start-------
		        				XPathFactory xFactory3 = XPathFactory.newInstance();  
		        		        XPath xpath3 = xFactory3.newXPath();
		        		        NodeList preset_field=(NodeList) xpath3.evaluate("field", e, XPathConstants.NODESET);
		        		        boolean tflag=true;
		        		        for(int i=0;i<preset_field.getLength();i++){
		        		        	Node sub_field=preset_field.item(i);
		        		        	Element sub_element=(Element) sub_field;
		        	//	        	String content=sub_element.getTextContent();
		        		        	String filed_name=sub_element.getAttribute("name");
		        		        	preset_value+=sub_element.getAttribute("name")+"<br>";
		        		        	String subFieldPath=path+"/field/field[@name='"+filed_name+"']";
		        		  //      	System.out.println(subFieldPath);
		        		        	XPathFactory xFactory4 = XPathFactory.newInstance();  
			        		        XPath xpath4 = xFactory4.newXPath();
			        		        Node subNode=(Node) xpath4.evaluate(subFieldPath, selected_packet, XPathConstants.NODE);
			        		        if(subNode != null){
			        		        	Element e1=(Element) subNode;
			        		        	String value=e1.getAttribute("showname");
			        		        	if(value.length()>40){
			        		        		value=value.substring(0, 38)+"...";
			        		        	}
			        		        	actual_value+=value+"<br>";
			        		        }else{
			        		        	actual_value+=filed_name+"：不存在<br>";
			        		        	tflag=false;
			        		        }
		   //     		        	System.out.println(content);
		        		        }
		        		        if(tflag){
		        		        	final_result="通过";
		        		        }else{
		        		        	final_result="未携带";
		        		        }
		        		        wt3.insert2T3(AVPName, mustOrNot, preset_value, actual_value, final_result);
		        				//--------------end检测是否包含特定字段----------
		        		//		wt3.insert2T3(AVPName, mustOrNot, preset_value, actual_value, final_result);
			        		}else{
			        			if("OutPut".equals(check_value)){											//直接输出结果就可以           Okay
			 //       				System.out.println("直接输出结果");
			        				String AVPName=showName;
			        				String mustOrNot="可选";
			        				String preset_value="直接输出值";
			        				String actual_value=pf.getAttribute("showname");
			        				String final_result="通过";
			        				wt3.insert2T3(AVPName, mustOrNot, preset_value, actual_value, final_result);
			        			}else{
			        				if("PrivateProp".equals(check_value)){
			  //      					System.out.println("从特殊配置文件中取值匹配");						//需要从特定的配置文件中匹配
			        					/*String AVPName=showName;
				        				String mustOrNot="可选";
				        				String preset_value="特定配置文件核对";
				        				String actual_value="";
				        				String final_result="待办";
				        				wt3.insert2T3(AVPName, mustOrNot, preset_value, actual_value, final_result);*/
			        					
		//************************************
			        					String AVPName=showName;
				        				String mustOrNot="可选";
				        				String preset_value="";
				        				String actual_value="";
				        				String final_result="";
				        				InputStream is=null;
				        				Properties prop=null;
				        				//-----------------------从私有的配置文件中取出匹配信息--------------------------------------------------------
				        				try{
					        				String private_file_name=source_company_name+".properties";
					        				//    				System.out.println(private_file_name);
					        				
					        				is=this.getClass().getClassLoader().getResourceAsStream("Properties/enviroment.properties");
					        				prop=new Properties();
					        				prop.load(is);
					        				String path4prop=prop.getProperty("propertyfile_savePath")+username+"/"+private_file_name;
					        				//   				System.out.println(path4prop);
					        				File private_file=new File(path4prop);
					        				actual_value=pf.getAttribute("showname");
					        		//		System.out.println("******"+actual_value+"---------");
					        				if(private_file.exists()){
						        				InputStream is1=new FileInputStream(private_file);
						        				prop.load(is1);
						        				preset_value=prop.getProperty("CC-Request-Number2");
						        				
						        				//******************
						        				if(preset_value.contains("|")){														//多值的情况
									        		String[] check_values=preset_value.split("\\|");
									        		boolean flag4mult=false;
									        		for(int n=0;n<check_values.length;n++){
									        			if(actual_value.contains(check_values[n])){
									        				flag4mult=true;
									        				break;
									        			}
									        		}
									        		if(flag4mult){
									        			//        			System.out.println(pf.getAttribute("showname")+"已匹配");
									        			final_result="通过";
									        			wt3.insert2T3(AVPName, mustOrNot, preset_value, actual_value, final_result);
									        		}else{
									        			//	        			System.out.println(pf.getAttribute("showname")+"未匹配");
									        			final_result="未通过";
									        			wt3.insert2T3(AVPName, mustOrNot, preset_value, actual_value, final_result);
									        		}
									        		//  固定值
									        	}else{			      																//单值的情况    Okay
									        		if(actual_value.contains(preset_value)){
								//	        			System.out.println(pf.getAttribute("showname")+"已匹配");
									        		
								        				final_result="通过";
								        				wt3.insert2T3(AVPName, mustOrNot, preset_value, actual_value, final_result);
									        		}else{
								//	        			System.out.println(pf.getAttribute("showname")+"未匹配");
									        			
								        				final_result="未通过";
								        				wt3.insert2T3(AVPName, mustOrNot, preset_value, actual_value, final_result);
									        		}
									        	}
						        				
						        						//******************************************************************************
						        //				System.out.println("****************"+prop.getProperty("CC-Request-Number2"));
					        				}else{
					        					preset_value="厂家配置文件不存在";
					        					final_result="厂家配置文件不存在";
					        					wt3.insert2T3(AVPName, mustOrNot, preset_value, actual_value, final_result);
					        //					System.out.println("厂家配置文件不存在");
					        				}
					        		//		String private_value=prop.getProperty(showName);
					        	//			System.out.println(private_value);
				        				}catch(Exception e1){
				        					e1.printStackTrace();
				        				}
				        				finally{
				        					if(is !=null)
				        						is.close();
				        					if(prop !=null)
				        						prop=null;
				        				}
	//*****************************************************************************
			        					
			        				}else{
					        			String pcap_value=pf.getAttribute("showname");						//固定值，单值或多值                    Okay
							        	//     多选值
							        	if(check_value.contains("|")){														//多值的情况
							        		String[] check_values=check_value.split("\\|");
							        		boolean flag4mult=false;
							        		for(int n=0;n<check_values.length;n++){
							        			if(pcap_value.contains(check_values[n])){
							        				flag4mult=true;
							        				break;
							        			}
							        		}
							        		String AVPName=showName;
					        				String mustOrNot="可选";
					        				String preset_value=check_value;
					        				String actual_value=pcap_value;
							        		if(flag4mult){
					//		        			System.out.println(pf.getAttribute("showname")+"已匹配");
							        			String final_result="通过";
							        			wt3.insert2T3(AVPName, mustOrNot, preset_value, actual_value, final_result);
							        		}else{
					//		        			System.out.println(pf.getAttribute("showname")+"未匹配");
							        			String final_result="未通过";
							        			wt3.insert2T3(AVPName, mustOrNot, preset_value, actual_value, final_result);
							        		}
							        		//  固定值
							        	}else{			      																//单值的情况    Okay
							        		if(pcap_value.contains(check_value)){
				//			        			System.out.println(pf.getAttribute("showname")+"已匹配");
							        			String AVPName=showName;
						        				String mustOrNot="可选";
						        				String preset_value=check_value;
						        				String actual_value=pcap_value;
						        				String final_result="通过";
						        				wt3.insert2T3(AVPName, mustOrNot, preset_value, actual_value, final_result);
							        		}else{
				//			        			System.out.println(pf.getAttribute("showname")+"未匹配");
							        			String AVPName=showName;
						        				String mustOrNot="可选";
						        				String preset_value=check_value;
						        				String actual_value=pcap_value;
						        				String final_result="未通过";
						        				wt3.insert2T3(AVPName, mustOrNot, preset_value, actual_value, final_result);
							        		}
							        	}
			        				}
			        			}
			        		}
//----------------------------------------------可选结束----------------------------------------------------------------------
		        		}else{
		 //       			System.out.println("未携带");
		        			String AVPName=showName;
	        				String mustOrNot="可选";
	        				String preset_value="";
	        				String actual_value="未找到检测点";
	        				String final_result="未携带";
	        				wt3.insert2T3(AVPName, mustOrNot, preset_value, actual_value, final_result);
		        		}
		        	}else{
		        		// 此处处理必选项，分为几种情况：  1.无法判断的信息，直接输出      ----okay,通过把值设置成为OutPut来判定
		        		// 								2.能够判断的固定值   -----直接写值就好了    多选值通过值中间的空格区分
		        		//								3. 特有的值，需要从特殊的哦配置文件中读取    -----通过把值设置成为 PrivateProp
		        		//								4. 判断是否包含字段，只用输出即可，无需判断值    --------通过增加属性subField="true"来判断。然后遍历子标签
		  //      		System.out.println("必选项");
		        		if(pcap_field != null){
		        			// 判断是否还有子标签，如果还有子标签，则要进行判断是否包含字段的操作
			 //       		System.out.println(mustOrnot);			        		
			        		Element pf=(Element) pcap_field;
			        		if(subField.equals("true")){													//检测是否包含特定的字段
			//        			System.out.println("需要检测是否包含字段");
			        			String AVPName=showName;
		        				String mustOrNot="必选";
		        				String preset_value="检查是否包含字段：<br>";
		        				String actual_value="";
		        				String final_result="待办";
		        				
		        				//--------------检测是否包含特定字段----------
		        				XPathFactory xFactory3 = XPathFactory.newInstance();  
		        		        XPath xpath3 = xFactory3.newXPath();
		        		        NodeList preset_field=(NodeList) xpath3.evaluate("field", e, XPathConstants.NODESET);
		        		        boolean tflag=true;
		        		        for(int i=0;i<preset_field.getLength();i++){
		        		        	Node sub_field=preset_field.item(i);
		        		        	Element sub_element=(Element) sub_field;
		        	//	        	String content=sub_element.getTextContent();
		        		        	String filed_name=sub_element.getAttribute("name");
		        		        	preset_value+=sub_element.getAttribute("name")+"<br>";
		        		        	String subFieldPath=path+"/field/field[@name='"+filed_name+"']";
		        		//        	System.out.println(subFieldPath);
		        		        	XPathFactory xFactory4 = XPathFactory.newInstance();  
			        		        XPath xpath4 = xFactory4.newXPath();
			        		        Node subNode=(Node) xpath4.evaluate(subFieldPath, selected_packet, XPathConstants.NODE);
			        		        if(subNode != null){
			        		        	Element e1=(Element) subNode;
			        		        	String value=e1.getAttribute("showname");
			        		        	if(value.length()>40){
			        		        		value=value.substring(0, 38)+"...";
			        		        	}
			        		        	actual_value+=value+"<br>";
			        		        }else{
			        		        	actual_value+=filed_name+"：不存在<br>";
			        		        	tflag=false;
			        		        }
		   //     		        	System.out.println(content);
		        		        }
		        		        if(tflag){
		        		        	final_result="通过";
		        		        }else{
		        		        	final_result="未通过";
		        		        }
		        		        wt3.insert2T3(AVPName, mustOrNot, preset_value, actual_value, final_result);
		        				//--------------end检测是否包含特定字段----------
		        				
		        				
			        		}else{
			        			if("OutPut".equals(check_value)){											//直接输出结果就可以           Okay
			  //      				System.out.println("直接输出结果");
			        				String AVPName=showName;
			        				String mustOrNot="必选";
			        				String preset_value="直接输出值";
			        				String actual_value=pf.getAttribute("showname");
			        				String final_result="通过";
			        				wt3.insert2T3(AVPName, mustOrNot, preset_value, actual_value, final_result);
			        			}else{
			        				if("PrivateProp".equals(check_value)){
			        //					System.out.println("从特殊配置文件中取值匹配");						//需要从特定的配置文件中匹配
			        					String AVPName=showName;
				        				String mustOrNot="必选";
				        				String preset_value="";
				        				String actual_value="";
				        				String final_result="";
				        				InputStream is=null;
				        				Properties prop=null;
				        //-----------------------从私有的配置文件中取出匹配信息--------------------------------------------------------
				        				try{
					        				String private_file_name=source_company_name+".properties";
					    //    				System.out.println(private_file_name);
					        				
					        				is=this.getClass().getClassLoader().getResourceAsStream("Properties/enviroment.properties");
					        				prop=new Properties();
					        				prop.load(is);
					        				String path4prop=prop.getProperty("propertyfile_savePath")+username+"/"+private_file_name;
					     //   				System.out.println(path4prop);
					        				File private_file=new File(path4prop);
					        				actual_value=pf.getAttribute("showname");
					        				if(private_file.exists()){
						        				InputStream is1=new FileInputStream(private_file);
						        				prop.load(is1);
						        				preset_value=prop.getProperty("CC-Request-Number2");
						        				
						        				//******************
						        				if(preset_value.contains("|")){														//多值的情况
									        		String[] check_values=preset_value.split("\\|");
									        		boolean flag4mult=false;
									        		for(int n=0;n<check_values.length;n++){
									        			if(actual_value.contains(check_values[n])){
									        				flag4mult=true;
									        				break;
									        			}
									        		}
									        		if(flag4mult){
									//        			System.out.println(pf.getAttribute("showname")+"已匹配");
									        			final_result="通过";
									        			wt3.insert2T3(AVPName, mustOrNot, preset_value, actual_value, final_result);
									        		}else{
								//	        			System.out.println(pf.getAttribute("showname")+"未匹配");
									        			final_result="未通过";
									        			wt3.insert2T3(AVPName, mustOrNot, preset_value, actual_value, final_result);
									        		}
									        		//  固定值
									        	}else{			      																//单值的情况    Okay
									        		if(actual_value.contains(preset_value)){
								//	        			System.out.println(pf.getAttribute("showname")+"已匹配");
									        		
								        				final_result="通过";
								        				wt3.insert2T3(AVPName, mustOrNot, preset_value, actual_value, final_result);
									        		}else{
								//	        			System.out.println(pf.getAttribute("showname")+"未匹配");
									        			
								        				final_result="未通过";
								        				wt3.insert2T3(AVPName, mustOrNot, preset_value, actual_value, final_result);
									        		}
									        	}
						        				
						        				//******************
						        //				System.out.println("****************"+prop.getProperty("CC-Request-Number2"));
					        				}else{
					        					preset_value="厂家配置文件不存在";
					        					final_result="未通过";
					        					wt3.insert2T3(AVPName, mustOrNot, preset_value, actual_value, final_result);
					        //					System.out.println("厂家配置文件不存在");
					        				}
					        		//		String private_value=prop.getProperty(showName);
					        	//			System.out.println(private_value);
				        				}catch(Exception e1){
				        					e1.printStackTrace();
				        				}
				        				finally{
				        					if(is !=null)
				        						is.close();
				        					if(prop !=null)
				        						prop=null;
				        				}
				        				
				        //---------------------------------------------------------------------------------
				       // 				wt3.insert2T3(AVPName, mustOrNot, preset_value, actual_value, final_result);
			        				}else{
					        			String pcap_value=pf.getAttribute("showname");						//固定值，单值或多值                    Okay
							        	//     多选值
							        	if(check_value.contains("|")){														//多值的情况
							        		String[] check_values=check_value.split("\\|");
							        		boolean flag4mult=false;
							        		for(int n=0;n<check_values.length;n++){
							        			if(pcap_value.contains(check_values[n])){
							        				flag4mult=true;
							        				break;
							        			}
							        		}
							        		String AVPName=showName;
					        				String mustOrNot="必选";
					        				String preset_value=check_value;
					        				String actual_value=pcap_value;
							        		if(flag4mult){
							//        			System.out.println(pf.getAttribute("showname")+"已匹配");
							        			String final_result="通过";
							        			wt3.insert2T3(AVPName, mustOrNot, preset_value, actual_value, final_result);
							        		}else{
						//	        			System.out.println(pf.getAttribute("showname")+"未匹配");
							        			String final_result="未通过";
							        			wt3.insert2T3(AVPName, mustOrNot, preset_value, actual_value, final_result);
							        		}
							        		//  固定值
							        	}else{			      																//单值的情况    Okay
							        		if(pcap_value.contains(check_value)){
						//	        			System.out.println(pf.getAttribute("showname")+"已匹配");
							        			String AVPName=showName;
						        				String mustOrNot="必选";
						        				String preset_value=check_value;
						        				String actual_value=pcap_value;
						        				String final_result="通过";
						        				wt3.insert2T3(AVPName, mustOrNot, preset_value, actual_value, final_result);
							        		}else{
						//	        			System.out.println(pf.getAttribute("showname")+"未匹配");
							        			String AVPName=showName;
						        				String mustOrNot="必选";
						        				String preset_value=check_value;
						        				String actual_value=pcap_value;
						        				String final_result="未通过";
						        				wt3.insert2T3(AVPName, mustOrNot, preset_value, actual_value, final_result);
							        		}
							        	}
			        				}
			        			}
			        		}
				        	
				//        	System.out.println(pcap_value);
			        	}else{
			  //      		System.out.println(pcap_file.getName()+"/"+this.getName()+"::"+"未找到对应的检测点"+showName);    //未找到，判断错误
			        		String AVPName=showName;
	        				String mustOrNot="必选";
	        				String preset_value="";
	        				String actual_value="未找到检测点";
	        				String final_result="未通过";
	        				wt3.insert2T3(AVPName, mustOrNot, preset_value, actual_value, final_result);
			        	}
		        	}
		        }
        //		System.out.println("匹配结束");
        	}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
