package com.file;

import java.io.IOException;

import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.io.IOUtils;

public class Generator {
	
	public static String generateSha256Hash(InputStream fileContent) {
		MessageDigest md;
		String hash = null;

		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(IOUtils.toByteArray(fileContent));
			byte[] bytes = md.digest();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));			
			}
			hash = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return hash;
	}

}
