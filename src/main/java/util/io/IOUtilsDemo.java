package util.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.IOUtils;

/**
 * IOUtils contains utility methods dealing with reading, writing and copying. 
 * The methods work on InputStream, OutputStream, Reader and Writer.
 *
 */
public class IOUtilsDemo {
	public static void main(String[] args) throws Exception {
		 InputStream in = new URL( "http://commons.apache.org" ).openStream();
		 try {
		   System.out.println( IOUtils.toString( in ) );
		 } finally {
		   IOUtils.closeQuietly(in);
		 }
	}
}
