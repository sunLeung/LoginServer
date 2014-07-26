package utils;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class StringUtils extends org.apache.commons.lang3.StringUtils{
	/**
	 * 数据解密
	 * 
	 * @param data
	 * @param key 密钥
	 * @return
	 * @throws Exception
	 */
	public final static String decrypt(String data, String key) {
		if (StringUtils.isBlank(data)||StringUtils.isBlank(key))
			return null;
		try {
			return new String(decrypt(hex2byte(data.getBytes()), key.getBytes()));
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		String a=encrypt("123456", "KUkf16w_kafadey");
		System.out.println(a);
		String b=decrypt(a, "KUkf16w_kafadey");
		System.out.println(b);
	}

	/**
	 * 数据加密
	 * 
	 * @param data
	 * @param key 密钥
	 * @return
	 * @throws Exception
	 */
	public final static String encrypt(String data, String key) {
		if (data == null || key == null)
			return null;
		try {
			return byte2hex(encrypt(data.getBytes(), key.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static byte[] hex2byte(byte[] b) {
		if ((b.length % 2) != 0)
			throw new IllegalArgumentException("The length is not even");
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}
		return b2;
	}
	
	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs.toUpperCase();
	}
	
	/**
	 * 加密
	 * @param src 数据源
	 * @param key 密钥，长度必须是8的倍数
	 * @return 返回加密后的数据
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] src, byte[] key) throws Exception {
		SecureRandom sr = new SecureRandom();
		DESKeySpec dks = new DESKeySpec(key);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey securekey = keyFactory.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
		return cipher.doFinal(src);
	}
	
	/**
	 * 解密
	 * 
	 * @param src 数据源
	 * @param key 密钥，长度必须是8的倍数
	 * @return 返回解密后的原始数据
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] src, byte[] key) throws Exception {
		SecureRandom sr = new SecureRandom();
		DESKeySpec dks = new DESKeySpec(key);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey securekey = keyFactory.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
		return cipher.doFinal(src);
	}
}
