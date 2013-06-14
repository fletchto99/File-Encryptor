package me.matt.file.util;

import java.io.UnsupportedEncodingException;

public class StringUtils {

	public static byte[] getBytesUtf8(final String string) {
		try {
			return string.getBytes("UTF-8");
		} catch (final UnsupportedEncodingException e) {
			throw new IllegalStateException(e);
		}
	}

	public static String reverse(String str) {
		if (str == null) {
			return null;
		}
		return new StringBuilder(str).reverse().toString();
	}

}
