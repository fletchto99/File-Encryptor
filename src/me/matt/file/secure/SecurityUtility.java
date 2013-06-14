package me.matt.file.secure;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import me.matt.file.util.StringUtils;

public class SecurityUtility {

	private static SecretKey generateKey(final String password)
			throws UnsupportedEncodingException, NoSuchAlgorithmException,
			InvalidKeySpecException {
		final SecretKeyFactory factory = SecretKeyFactory
				.getInstance("PBKDF2WithHmacSHA1");
		final KeySpec spec = new PBEKeySpec(password.toCharArray(),
				StringUtils.getBytesUtf8(StringUtils.reverse(password)), 65536,
				128);
		return new SecretKeySpec(factory.generateSecret(spec).getEncoded(),
				"AES");
	}

	public static byte[] encrypt(final byte[] file, final String password)
			throws Exception {
		final IvParameterSpec iv = new IvParameterSpec(new byte[16]);
		final Key key = SecurityUtility.generateKey(password);
		final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key, iv);
		return cipher.doFinal(file);
	}

	public static byte[] decrypt(final byte[] file, final String password)
			throws Exception {
		final IvParameterSpec iv = new IvParameterSpec(new byte[16]);
		final Key key = SecurityUtility.generateKey(password);
		final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, key, iv);
		return cipher.doFinal(file);
	}
}
