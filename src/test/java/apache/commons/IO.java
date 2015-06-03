package apache.commons;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;

/**
 * 方便地读取本地文件
 *
 */
public class IO {
	public static void main(String[] args) throws IOException {
		List<String> list = IOUtils.readLines(getConfig(), "UTF-8");
		System.out.println(list);
	}
	
	private static InputStream getConfig() {
		return Thread.currentThread().getContextClassLoader().getResourceAsStream("conf.properties");
	}
}
