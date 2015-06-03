package util.io;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class FileUtilsDemo {
	public static void main(String[] args) throws IOException {
		 File file = new File("src/main/resources/conf.properties");
		 System.out.println(file.getAbsolutePath());
		 List<String> lines = FileUtils.readLines(file, "UTF-8");
		 for(String line : lines) {
			 System.out.println(line);
		 }
	}
}
