package apache.commons;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import app.common.AppBase;

public class MyCodec extends AppBase {
	
	@Test
	public void md5() throws IOException {
		String password = "abc123";
		byte[] passwordByte = "abc123".getBytes();
		InputStream passwordInputStream = new ByteArrayInputStream("abc123".getBytes());
		
		String text1 = DigestUtils.md5Hex(password);
		String text2 = DigestUtils.md5Hex(passwordByte);
		String text3 = DigestUtils.md5Hex(passwordInputStream);
		
		info(text1);
		info(text2);
		info(text3);
	}
	
	
}
