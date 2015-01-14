package wang.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Properties;

import org.apache.commons.io.output.FileWriterWithEncoding;
import org.junit.Test;

import Demo.PropertiesDemo;

public class FileDao {
	
	private String savepath;
	
	
	public FileDao(String savepath) {
		super();
		this.savepath = savepath;
	}

	public  synchronized  void getfilewriter(String mesg) throws IOException{
		
			File log_file=getLogFile();
			if (!log_file.getParentFile().exists()) {
				log_file.getParentFile().mkdirs();
			}
			if(!log_file.exists()){				
				log_file.createNewFile();
				getfilewriter("@@@错误日志记录文件，记录每次测试所有的错误消息。每次会产生新的错误日志文件@@@\n\n");
				
			}
			FileWriterWithEncoding fwrite=new FileWriterWithEncoding(log_file, "utf-8", true);
			fwrite.write(mesg);
			fwrite.flush();
			fwrite.close();
	
	}
	
	public  File getLogFile() throws IOException{
		/*Properties prop=new Properties();
		ClassLoader loader=LogFileDao.class.getClassLoader();
		InputStream is=loader.getResourceAsStream("Properties/enviroment.properties");
		prop.load(is);
		String log_path=prop.getProperty("logFile_savePath");
		System.out.println(log_path);*/
		File log_file=new File(savepath);
		return log_file;
		
	}

}
