package util.crypto;

import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import org.apache.commons.codec.binary.Hex;

public class MyEncrypt {
	private KeyGenerator keygen;
	private SecretKey secretkey;
	private Cipher c;
	
	private static final String ALGORITHM_DES = "DES";
	private static final String ALGORITHM_3DES = "TripleDES"; //DESede
	private static final String ALGORITHM_AES = "AES";
	
	public MyEncrypt(String algorithm) throws NoSuchAlgorithmException, NoSuchPaddingException {
		keygen = KeyGenerator.getInstance(algorithm);
		secretkey = keygen.generateKey();
		c = Cipher.getInstance(algorithm);
	}
	
	public byte[] encrypt(String source) throws Exception {
		c.init(Cipher.ENCRYPT_MODE, secretkey);
		byte[] input = source.getBytes();
		byte[] output = c.doFinal(input);
		return output;
	}
	
	public String decrypt(byte[] bytes) throws Exception {
		c.init(Cipher.DECRYPT_MODE, secretkey);
		byte[] output = c.doFinal(bytes);
		return new String(output);
	}

	
	public static void main(String[] args) throws Exception {
		// 10进制转16进制
		//System.out.println(Character.forDigit(10, 16));
		//System.out.println(Integer.toHexString(10));
		
		test(ALGORITHM_DES);
		test(ALGORITHM_3DES);
		test("DESede");
		test(ALGORITHM_AES);
	}

	private static void test(String alg) throws NoSuchAlgorithmException,
			NoSuchPaddingException, Exception {
		String srcText = "一段明文";
		
		MyEncrypt encryptor = new MyEncrypt(alg);
		
		// 加密
		byte[] bytes = encryptor.encrypt(srcText);

		// 解密
		String string = encryptor.decrypt(bytes);

		System.out.println(Hex.encodeHexString(bytes));
		System.out.println(string);
	}
}
