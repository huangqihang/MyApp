package util.crypto.digest;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	public static void main(String[] args) throws NoSuchAlgorithmException {
		String rs = MungPass("java");
		System.out.println(rs+"\t length:"+rs.length());
	}
	
	public static String MungPass(String pass) throws NoSuchAlgorithmException {
	    MessageDigest m = MessageDigest.getInstance("MD5");
	    byte[] data = pass.getBytes(); 
	    m.update(data,0,data.length);
	    BigInteger i = new BigInteger(1,m.digest());
	    return String.format("%1$032X", i);
	}
}
