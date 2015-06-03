package util.old.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

/**
 * 从类路径下加载文件
 *
 */
public class ClasspathResourceLoader {
	
	public static URL getClassPathURL() {
		URL url = Thread.currentThread().getContextClassLoader().getResource("");
		return url;
	}
	
	public static File getFile(String filename) {
		File file = null;
		try {
			File parent = new File(getClassPathURL().toURI());
			file = new File(parent, filename);
			if(!file.exists()) {
				System.out.println("文件不存在: " + file.getAbsolutePath());
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return file;
	}
	
	public static List<String> readFile(File file) {
		List<String> content = new LinkedList<String>();
		BufferedReader bufr = null;
		try {
			bufr = new BufferedReader(new FileReader(file));
			String line = null;
			while((line=bufr.readLine())!=null) {
				content.add(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("文件不存在："+file.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(bufr!=null) {
				try { bufr.close(); } 
				catch (IOException e) { e.printStackTrace(); }
			}
		}
		return content;
	}
	
	public static void main(String[] args) {
		File file = getFile("vips.txt");
		List<String> list = readFile(file);
		
		for(String line : list) {
			System.out.println(line);
		}
	}
}
