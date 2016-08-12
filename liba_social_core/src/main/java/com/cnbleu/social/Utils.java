package com.cnbleu.social;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ThumbnailUtils;
import android.support.v4.graphics.BitmapCompat;

import com.cnbleu.social.utils.lg;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * <b>Project:</b> android<br>
 * <b>Create Date:</b> 16/3/31<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class Utils implements SocialConstants {

    private static final float THUMB_SIZE = 128f;
    private static final float THUMB_SIZE_MIN = 72f;
    private static final String THUMB_SUFFX = ".thumb";

    public static final int WX_THUMB_MAX = (int) '耀';

    private Utils() {
        // hide
    }

    public static byte[] bmpToByte(final Bitmap bitmap, final boolean recycle) {
        ByteArrayOutputStream ops = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, ops);
        if (recycle) {
            bitmap.recycle();
        }

        byte[] byts = ops.toByteArray();

        try {
            ops.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byts;
    }


    public static Bitmap resToBmp(Resources resources, int res) {
        return BitmapFactory.decodeResource(resources, res);
    }

    public static Bitmap fileToBmp(String filepath) {
        return BitmapFactory.decodeFile(filepath);
    }

    public static boolean bmpToFile(Bitmap bitmap, File target) {
        try {
            FileOutputStream fos = new FileOutputStream(target);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fos);
            fos.flush();
            fos.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static byte[] readFromFile(String fileName, int offset, int len) {
        if (fileName == null) {
            return null;
        }

        File file = new File(fileName);
        if (!file.exists()) {
            lg.w("file not found");
            return null;
        }

        if (len == -1) {
            len = (int) file.length();
        }

        lg.v("offset = " + offset + " len = " + len + " offset + len = " + (offset + len));

        if (offset < 0) {
            lg.w("invalid offset:" + offset);
            return null;
        }
        if (len <= 0) {
            lg.w("invalid len:" + len);
            return null;
        }
        if (offset + len > (int) file.length()) {
            lg.w("invalid file len:" + file.length());
            return null;
        }

        byte[] b = null;
        try {
            RandomAccessFile in = new RandomAccessFile(fileName, "r");
            b = new byte[len]; // 创建合适文件大小的数组
            in.seek(offset);
            in.readFully(b);
            in.close();

        } catch (Exception e) {
            lg.e("errMsg = " + e.getMessage());
            e.printStackTrace();
        }
        return b;
    }

    public static Bitmap createThumb(File in) {
        return createThumb(BitmapFactory.decodeFile(in.getAbsolutePath()));
    }

    public static Bitmap createThumb(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        // if width and height smaller than THUMB_WIDTH, ignore
        if (width > THUMB_SIZE || height > THUMB_SIZE) {
            float scale = Math.min(THUMB_SIZE / width, THUMB_SIZE / height);
            width *= scale;
            height *= scale;
        }

        bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height, ThumbnailUtils.OPTIONS_RECYCLE_INPUT);

        int length = BitmapCompat.getAllocationByteCount(bitmap);
        if (length > WX_THUMB_MAX) {
            bitmap = compressBitmap(bitmap, WX_THUMB_MAX);
        }

        return bitmap;
    }

    public static Bitmap compressBitmap(Bitmap bitmap, int size) {
        return compressBitmap(bitmap, 85, size);
    }

    private static Bitmap compressBitmap(Bitmap in, int quanlity, int want) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        in.compress(Bitmap.CompressFormat.JPEG, quanlity, bos);
        float scale = (float) Math.sqrt((float) want / bos.toByteArray().length);

        Matrix matrix = new Matrix();
        matrix.setScale(scale, scale);

        Bitmap out = Bitmap.createBitmap(in, 0, 0, in.getWidth(), in.getHeight(), matrix, true);
        bos.reset();
        out.compress(Bitmap.CompressFormat.JPEG, quanlity, bos);
        if (bos.toByteArray().length < want) {
            return out;
        }

        while (bos.toByteArray().length > want) {
            matrix.setScale(0.9f, 0.9f);
            out = Bitmap.createBitmap(out, 0, 0, out.getWidth(), out.getHeight(), matrix, true);
            bos.reset();
            out.compress(Bitmap.CompressFormat.JPEG, quanlity, bos);
        }

        return out;
    }

}
