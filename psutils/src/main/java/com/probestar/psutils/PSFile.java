package com.probestar.psutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.util.ArrayList;

import com.google.common.io.Files;

public class PSFile {
	private static ArrayList<String> _pictureExtensionNames;
	private static ArrayList<String> _movieExtensionNames;

	static {
		_pictureExtensionNames = new ArrayList<String>();
		_pictureExtensionNames.add("jpg");
		_pictureExtensionNames.add("png");
		_pictureExtensionNames.add("bmp");
		_pictureExtensionNames.add("gif");
		_pictureExtensionNames.add("psd");
		_pictureExtensionNames.add("jpeg");

		_movieExtensionNames = new ArrayList<String>();
		_movieExtensionNames.add("mov");
		_movieExtensionNames.add("mp4");
		_movieExtensionNames.add("m4v");
		_movieExtensionNames.add("3gp");
		_movieExtensionNames.add("mpg");
		_movieExtensionNames.add("avi");
		_movieExtensionNames.add("mpeg");
		_movieExtensionNames.add("dat");
		_movieExtensionNames.add("ra");
		_movieExtensionNames.add("rm");
		_movieExtensionNames.add("qt");
		_movieExtensionNames.add("asf");
		_movieExtensionNames.add("wmv");
		_movieExtensionNames.add("rmvb");
		_movieExtensionNames.add("vob");
	}

	public static boolean isPicture(File file) {
		String extension = Files.getFileExtension(file.getAbsolutePath()).toLowerCase();
		return _pictureExtensionNames.contains(extension);
	}

	public static boolean isMovie(File file) {
		String extension = Files.getFileExtension(file.getAbsolutePath()).toLowerCase();
		return _movieExtensionNames.contains(extension);
	}

	public static byte[] getHashCode(File file) {
		return getHashCode(file, file.length());
	}

	public static byte[] getHashCode(File file, long hashSize) {
		byte[] value = null;
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
			MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0,
					file.length() > hashSize ? hashSize : file.length());
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(byteBuffer);
			value = md5.digest();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return value;
	}
}
