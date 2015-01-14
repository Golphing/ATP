package Demo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.junit.Test;

import wang.utils.FileDao;

public class PropertiesDemo {
		private static final Object lock=new Object();
	/**
	 * @param args
	 */
	public  static void main(String[] a) {
		
		
	}

	
	@Test
	public  void getLogFile1() throws IOException{
		Properties prop=new Properties();
		ClassLoader loader=FileDao.class.getClassLoader();
		InputStream is=loader.getResourceAsStream("Properties/enviroment.properties");
		prop.load(is);
		prop.setProperty("name", "hello");
		String log_path=prop.getProperty("logFile_savePath");
		System.out.println(log_path);
		File log_file=new File(log_path);
	//	return log_file;
		
		
		
		
	}

}
