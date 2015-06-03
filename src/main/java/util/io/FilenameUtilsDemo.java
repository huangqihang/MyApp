package util.io;

import org.apache.commons.io.FilenameUtils;

public class FilenameUtilsDemo {
	public static void main(String[] args) {
		String filename = "C:/commons/io/../lang/project.xml";
		String normalized = FilenameUtils.normalize(filename);
	}
}
