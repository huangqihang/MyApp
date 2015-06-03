package util.crypto.digest;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 使用apache的工具包
 *
 */
public class MyDigestUtil {
	
	public static String MD5(String text) {
		return DigestUtils.md5Hex(text);
	}
	
	public static String SHA(String text) {
		return DigestUtils.sha512Hex(text);
	}
	
	public static void main(String[] args) {
		System.out.println(MD5("hello"));
		System.out.println(SHA("hello"));
	}
}
