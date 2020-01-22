package com.file;

import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Encryption {
	private static SecretKey getSecretKey(String aesKey) {
		SecretKey secretKey = null;
		try {
			byte[] key = aesKey.getBytes();
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16);
			secretKey = new SecretKeySpec(key, "AES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return secretKey;

	}

	public static InputStream encryptFile(InputStream initialContent, String aesKey) {
		Cipher enCipher = null;
		try {
			enCipher = Cipher.getInstance("AES");
			SecretKey sk = getSecretKey(aesKey);
			enCipher.init(Cipher.ENCRYPT_MODE, sk);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new CipherInputStream(initialContent, enCipher);

	}
	
	public static InputStream decryptFile(InputStream content, String aesKey)  {
		Cipher deCipher = null;
		try {
			deCipher = Cipher.getInstance("AES");
			SecretKey sk = getSecretKey(aesKey);
			deCipher.init(Cipher.DECRYPT_MODE, sk);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new CipherInputStream(content, deCipher);
	}

}
