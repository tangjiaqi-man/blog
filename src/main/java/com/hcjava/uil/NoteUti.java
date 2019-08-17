package com.hcjava.uil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.apache.tomcat.util.codec.binary.Base64;

/**
 * 1.����UUID 2.�������
 * 
 * @author lenovo
 *
 */
public class NoteUti {
	public static String createUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}

	public static String md5(String src) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] digest = md.digest(src.getBytes());
		// ��MD5����������base64ת���ַ�
		String s = Base64.encodeBase64String(digest);
		return s;
	}

	public static void main(String[] args) throws NoSuchAlgorithmException {
		System.out.println(createUUID());
		System.out.println(md5("123456"));
	}
}
