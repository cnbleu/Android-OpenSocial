package com.cnbleu.social.utils;

import com.cnbleu.social.SocialConstants;
import com.cnbleu.social.SocialDebug;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <b>Project:</b> android<br>
 * <b>Create Date:</b> 16/3/31<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class NetUtils implements SocialConstants {

    private NetUtils() {
        // hide
    }

    public static File downloadImage(String url) {
        try {
            final URLConnection connection = new URL(url).openConnection();
            connection.setReadTimeout(15 * 1000);
            connection.setDoInput(true);
            connection.connect();
            final InputStream ins = connection.getInputStream();

            final File cache =
                    new File(android.os.Environment.getExternalStorageDirectory().getAbsolutePath(),
                             CACHE_PATH);
            if (!cache.exists()) {
                cache.mkdir();
            }

            final String fileName = md5(url);
            final File file = new File(cache, fileName);
            final OutputStream ops = new FileOutputStream(file);

            int len;
            byte[] byts = new byte[1024 * 10];
            while ((len = ins.read(byts)) != -1) {
                ops.write(byts, 0, len);
            }

            ops.flush();
            ops.close();

            if (SocialDebug.DEBUG) {
                lg.v(SocialDebug.TAG, "download image success. file path: " + file.getAbsolutePath() + ", url: " + url);
            }
            return file;
        } catch (IOException e) {
            e.printStackTrace();
            lg.w(SocialDebug.TAG, "download image error, url: " + url);
        }

        return null;
    }

    private static String md5(String str) {
        StringBuffer buffer = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] byts = md.digest(str.getBytes(Charset.forName("utf-8")));

            for (byte byt : byts) {
                int d = byt & 0xFF;
                if (d < 16) {
                    buffer.append(0);
                }

                buffer.append(Integer.toHexString(d));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }
}
