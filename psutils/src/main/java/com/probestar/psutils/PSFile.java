package com.probestar.psutils;

import java.io.File;

import com.google.common.io.Files;

public class PSFile {
	private static String pictureExtensionName = "jpgpngbmp";
	private static String movieExtensionName = "movmp4";

	public static boolean isPicture(File file) {
		String extension = Files.getFileExtension(file.getAbsolutePath()).toLowerCase();
		return pictureExtensionName.indexOf(extension) >= 0;
	}

	public static boolean isMovie(File file) {
		String extension = Files.getFileExtension(file.getAbsolutePath()).toLowerCase();
		return movieExtensionName.indexOf(extension) >= 0;
	}
}
