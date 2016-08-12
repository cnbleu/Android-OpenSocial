package com.cnbleu.social.impl.weixin;

import java.io.File;

/**
 * <b>Project:</b> android<br>
 * <b>Create Date:</b> 16/3/31<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
class WXValidUtils {

    private static final int KB_10 = 10240;
    private static final int MB_5 = 10485760;

    protected WXValidUtils() {
        // hide
    }

    public static boolean validText(String text) {
        return null != text && text.length() != 0 && text.length() < KB_10;
    }

    public static boolean validImage(String path, String url, int res) {
        if (0 != res) {
            return true;
        } else if (null != path && path.length() != 0 && path.length() < KB_10) {
            final File file = new File(path);
            if (!(file.exists() && file.length() < MB_5)) {
                if (null != url && url.length() != 0) {
                    return true;
                }
            }
            return true;
        } else if (null != url && url.length() != 0) {
            return true;
        }
        return false;
    }


}
