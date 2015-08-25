package com.probestar.psutils;

public class PSConvert {
	public static String bytes2HexString(byte[] value) {
		StringBuilder sb = new StringBuilder();
		for (byte b : value) {
			sb.append(String.format("%02X", b));
		}
		return sb.toString();
	}

	public static byte[] hexString2Bytes(String hexString) {
		hexString = hexString.toLowerCase();
		final byte[] byteArray = new byte[hexString.length() / 2];
		int k = 0;
		for (int i = 0; i < byteArray.length; i++) {
			// Because it is a hexadecimal, most will only take up to four,
			// converted into bytes need two hexadecimal characters, high first
			byte high = (byte) (Character.digit(hexString.charAt(k), 16) & 0xff);
			byte low = (byte) (Character.digit(hexString.charAt(k + 1), 16) & 0xff);
			byteArray[i] = (byte) (high << 4 | low);
			k += 2;
		}
		return byteArray;
	}
}