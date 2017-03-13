package com.rongdu.eloan.common.utils;

import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public abstract class RSAUtil {

	public static final String KEY_ALGORITHM = "RSA";

	/**
	 * BASE64解密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptBASE64(String key) throws Exception {
		return (new BASE64Decoder()).decodeBuffer(key);
	}

	/**
	 * BASE64加密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encryptBASE64(byte[] key) throws Exception {
		return (new BASE64Encoder()).encodeBuffer(key);
	}

	/**
	 * 解密<br>
	 * 用公钥解密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPublicKey(byte[] data, String key)
			throws Exception {
		// 对密钥解密
		byte[] keyBytes = decryptBASE64(key);// 取得公钥
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key publicKey = keyFactory.generatePublic(x509KeySpec);
		// 对数据解密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		return cipher.doFinal(data);
	}

	/**
	 * 加密<br>
	 * 用公钥加密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPublicKey(byte[] data, String key)
			throws Exception {
		// 对公钥解密
		byte[] keyBytes = decryptBASE64(key);
		// 取得公钥
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key publicKey = keyFactory.generatePublic(x509KeySpec);
		// 对数据加密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		return cipher.doFinal(data);
	}
	
	public static void main(String[] args) throws Exception {
		 String PUB_KYE = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCqh51DJuRJCm5um+fKYbY5Nu/hGZC1VDYTJKf1fHMnKAnplYDBaHKceLuR0ri/stC1DGXnuf+UeJqyVr20CEQh/p6kQByQcs7c44bxCgTbuQYWvSr622gEEfBcW/SexkZ337rATymT5+AnPpjJm/Au8w67fs4JCVKyGOBd6ldAoQIDAQAB";

	      String PRI_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKqHnUMm5EkKbm6b58phtjk27+EZkLVUNhMkp/V8cycoCemVgMFocpx4u5HSuL+y0LUMZee5/5R4mrJWvbQIRCH+nqRAHJByztzjhvEKBNu5Bha9KvrbaAQR8Fxb9J7GRnffusBPKZPn4Cc+mMmb8C7zDrt+zgkJUrIY4F3qV0ChAgMBAAECgYEAqNv21mrpfIYOygopYNaWuVrz94L4LwX/Mb+HbWdZI3nbLuhygZGqg9Rtb70oYfJwBgcL1YTxVpSCInwQBvh0dUtZYCUU2LlPzZMjBuEBIemmsuziKPOZHd4kc+uoFPvAXR6uNf1mXB3vrSHdd7DrkhxK5XIUkJ2JS5La7jCShX0CQQD99Ms4l/rmbN9h7C3UTx4iQhaxnVMpop26qX79DuFxV6mo7Cc7Jl0U6JfVUZFsQPyUggiHjzJ9pxNsWC/tq1MXAkEAq+bxg12yX2XTOMnWD+IDhxorOhgahYeOBV925zgS2Tm041lLsaOLw1mp/EawTQ14ZANfX/cJEA8y5objtjC9BwJBAKqFBTOR8Plp3zBcKoWlPkGYiK5oSd/p/ejJSKhYpo25ZAU11D3GS7fvWYv1wiSjGjYJffqv7yIp3kPgjwYSBK8CQFlWBQ+bQxb26SWrJzdL4z90JyDi4+2fO/yo1zdk2c7QgaRhhMgF0Z2tXH0CE5+Hr5hydD+AN9KDw5UwZukJPUMCQDzOloOAM/Pwk4N1FtVlaDA6W672K6cZm6FAi+VebP8bdFJ7in1xNA0Yz5ilYSx57RHtN0ViZdguIpkqCavWtTY=";

		System.out.println(decryptBASE64(PUB_KYE));
	}
}
