package util.crypto;

import java.io.ObjectInputStream.GetField;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Hex;

public class RSA {
	
	private static final String ALGORITHM = "RSA";
	
	public static byte[] encrypt(RSAPublicKey pubKey, byte[] srcBytes) throws Exception {
		
		Cipher c = Cipher.getInstance(ALGORITHM);
		c.init(Cipher.ENCRYPT_MODE, pubKey);
		byte[] bytes = c.doFinal(srcBytes);
		
		return bytes;
	}
	
	public static byte[] decrypt(RSAPrivateKey privateKey, byte[] encryptBytes) throws Exception {
		byte[] srcBytes = null;
		if(privateKey != null) {
			Cipher c = Cipher.getInstance(ALGORITHM);
			c.init(Cipher.DECRYPT_MODE, privateKey);
			srcBytes = c.doFinal(encryptBytes);
		}
		return srcBytes;
	}
	
	public static KeyPair getKeyPair(int keysize) throws Exception {
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(ALGORITHM);
		keyPairGen.initialize(keysize);
		KeyPair pair = keyPairGen.generateKeyPair();
		return pair;
	}
	
	public static void main(String[] args) throws Exception {
		String text = "随机密码";
		
		int keysize = 1024;
		KeyPair pair = getKeyPair(keysize);
		
		RSAPublicKey pubKey = (RSAPublicKey) pair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) pair.getPrivate();
		
		// 使用公钥对“随机密码”进行加密
		byte[] encryptBytes = encrypt(pubKey, text.getBytes());
		System.out.println(Hex.encodeHex(encryptBytes));
		
		// transfer on internet
		
		// 使用私钥获取“随机密码”
		byte[] decrptyBytes = decrypt(privateKey, encryptBytes);
		System.out.println(new String(decrptyBytes));
		
	}
}
