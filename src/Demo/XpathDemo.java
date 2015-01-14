package Demo;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.*;

import org.w3c.dom.Document;

public class XpathDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); 
			DocumentBuilder builder=factory.newDocumentBuilder();
			String path = "F:/upload/one.xml";
			File file = new File(path);
			Document document=builder.parse(file);
			XPathFactory xFactory = XPathFactory.newInstance();  
	        XPath xpath = xFactory.newXPath(); 
	        String value=(String) xpath.evaluate("/pdml/test[@name='gao']/text()", document, XPathConstants.STRING);
	        System.out.println(value);
	        System.out.println("end");
		}catch(Exception e){
			throw new RuntimeException();
		}
	}

}
