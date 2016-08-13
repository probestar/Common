package com.probestar.psutils;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * Created by probestar on 16/8/8.
 */
public class PSZip {

    public static void zipFiles(ArrayList<File> srcfile, File zipfile) throws IOException {
        byte[] buf = new byte[1024];
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile));
        for (int i = 0; i < srcfile.size(); i++) {
            FileInputStream in = new FileInputStream(srcfile.get(i));
            out.putNextEntry(new ZipEntry(srcfile.get(i).getName()));
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.closeEntry();
            in.close();
        }
        out.close();
    }

    public static void unZipFiles(File zipfile, String descDir) throws IOException {
        ZipFile zf = new ZipFile(zipfile);
        for (Enumeration entries = zf.entries(); entries.hasMoreElements(); ) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            String zipEntryName = entry.getName();
            InputStream in = zf.getInputStream(entry);
            OutputStream out = new FileOutputStream(descDir + zipEntryName);
            byte[] buf1 = new byte[1024];
            int len;
            while ((len = in.read(buf1)) > 0) {
                out.write(buf1, 0, len);
            }
            in.close();
            out.close();
        }
    }
}
