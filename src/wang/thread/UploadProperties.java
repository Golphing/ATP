package wang.thread;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.fileupload.FileItem;

public class UploadProperties extends Thread {
	private FileItem item;
	private String username;

	public UploadProperties(FileItem item,String username) {
		super();
		this.item = item;
		this.username=username;
	}
	
	@Override
	
	public void run() {
		// TODO Auto-generated method stub
		try{
			super.run();
			Properties prop=new Properties();
			ClassLoader loader=this.getClass().getClassLoader();
			InputStream is=loader.getResourceAsStream("Properties/enviroment.properties");
			prop.load(is);
			String save_path=prop.getProperty("propertyfile_savePath");
			is.close();
			String fileName=item.getName();
			int index1=fileName.lastIndexOf(".");
			String newFileName=fileName.substring(0,index1)+".xml";
			String save_name=username+"_"+newFileName;
			String path=save_path+username+"/"+save_name;
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
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
