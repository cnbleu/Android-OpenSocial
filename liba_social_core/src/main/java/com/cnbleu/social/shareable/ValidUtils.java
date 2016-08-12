package com.cnbleu.social.shareable;

import android.text.TextUtils;

import com.cnbleu.social.utils.lg;

import static com.cnbleu.social.SocialDebug.DEBUG;
import static com.cnbleu.social.SocialDebug.TAG;

/**
 * <b>Project:</b> android<br>
 * <b>Create Date:</b> 16/3/29<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class ValidUtils {

    private ValidUtils() {

    }

    public static boolean validTitle(String text) {
        if (TextUtils.isEmpty(text)) {
            lg.w(TAG, "title must not be null !!");
            return false;
        }

        if (DEBUG) {
            lg.v(TAG, "title is valid. title: " + text);
        }
        return true;
    }

    public static boolean validText(String text) {
        if (TextUtils.isEmpty(text)) {
            lg.w(TAG, "text must not be null !!");
            return false;
        }

        if (DEBUG) {
            lg.v(TAG, "text is valid. text: " + text);
        }
        return true;
    }

    public static boolean validText(String text, int length) {
        if (TextUtils.isEmpty(text)) {
            lg.w(TAG, "text must not be null !!");
            return false;
        }

        if (text.length() < length) {
            lg.w(TAG, "text length must not over " + length);
            return false;
        }

        if (DEBUG) {
            lg.v(TAG, "text is valid. text: " + text);
        }
        return true;
    }

    public static boolean validImage(String image) {
        return !TextUtils.isEmpty(image);
    }

    public static boolean validImage(int res) {
        final boolean resutl = 0 != res;
        if (!resutl) {
            lg.w(TAG, "image resource must not be 0 !!");
        } else if (DEBUG) {
            lg.v(TAG, "image is valid, resource: " + res);
        }

        return resutl;
    }

    public static boolean validImage(String image, int res) {
        final boolean result = !TextUtils.isEmpty(image) || 0 != res;
        if (!result) {
            lg.w(TAG, "Neither image resource or image path or image url must not be null or 0 !!");
        } else if (DEBUG) {
            if (!TextUtils.isEmpty(image)) {
                lg.v(TAG, "image: " + image);
            }

            if (0 != res) {
                lg.v(TAG, "image resource: " + res);
            }
        }
        return result;
    }

    public static boolean validImage(String path, String url, int res) {
        final boolean result = !TextUtils.isEmpty(path) || !TextUtils.isEmpty(url) || 0 != res;
        if (!result) {
            lg.w(TAG, "Neither image resource or image path or image url must not be null or 0 !!");
        } else if (DEBUG) {
            if (!TextUtils.isEmpty(path)) {
                lg.v(TAG, "image path: " + path);
            }

            if (!TextUtils.isEmpty(url)) {
                lg.v(TAG, "image url: " + url);
            }

            if (0 != res) {
                lg.v(TAG, "image resource: " + res);
            }
        }

        return result;
    }

}
