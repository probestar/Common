package com.probestar.psutils;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;

/**
 * Created by ProbeStar on 16/8/12.
 */
public class PSFtp {

    public static boolean upload(String host, int port, String userName, String passwrod, String localPath) {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(host, port);
            ftpClient.login(userName, passwrod);
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                ftpClient.disconnect();
                return false;
            }

            ftpClient.setControlEncoding("UTF-8");
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();

            File f = new File(localPath);
            InputStream in = new FileInputStream(f);
            boolean flag = ftpClient.storeFile(f.getName(), in);
            in.close();
            return flag;
        } catch (Throwable t) {
            t.printStackTrace();
            return false;
        } finally {
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean download(String host, int port, String userName, String passwrod, String remoteFileName, String localPath) {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(host, port);
            ftpClient.login(userName, passwrod);
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                ftpClient.disconnect();
                return false;
            }

            ftpClient.setControlEncoding("UTF-8");
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();

            File f = new File(localPath);
            FileOutputStream out = new FileOutputStream(f);
            boolean flag = ftpClient.retrieveFile(remoteFileName, out);
            out.close();
            return flag;
        } catch (Throwable t) {
            t.printStackTrace();
            return false;
        } finally {
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean delete(String host, int port, String userName, String passwrod, String remoteFileName) {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(host, port);
            ftpClient.login(userName, passwrod);
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                ftpClient.disconnect();
                return false;
            }

            ftpClient.setControlEncoding("UTF-8");
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            boolean flag = ftpClient.deleteFile(remoteFileName);
            return flag;
        } catch (Throwable t) {
            t.printStackTrace();
            return false;
        } finally {
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
