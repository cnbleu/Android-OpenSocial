package com.cnbleu.social.impl.weibo;

import android.text.TextUtils;

import com.cnbleu.social.SocialDebug;
import com.cnbleu.social.shareable.ShareParams;
import com.cnbleu.social.utils.lg;

/**
 * <b>Project:</b> android<br>
 * <b>Create Date:</b> 16/3/29<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * 新浪微博的分享参数.
 * <br>
 */
public class WeiboParams extends ShareParams<WeiboParams> {
    /** 新浪微博允许的最大分享文字长度 */
    private static final int TEXT_MAX_LENGTH = 2000;


    /**
     * 校验分享参数是否通过
     *
     * @return true, 校验通过
     */
    @Override
    public boolean validParams() {
        if (!super.validParams()) {
            return false;
        }

        final Type type = this.type;

        if (type == Type.TEXT) {
            return validText();
        } else if (type == Type.IMAGE) {
            return validText() && validImage();
        } else if (type == Type.WEBPAGE) {
            boolean resutl = validText() && validImage();
            if (!resutl) {
                return false;
            }

            if (TextUtils.isEmpty(url)) {
                lg.w(SocialDebug.TAG, "Url must not be null !!");
                return false;
            }

            if (SocialDebug.DEBUG) {
                lg.v(SocialDebug.TAG, "Url is valid. url: " + url);
            }

            return true;
        }

        lg.w(SocialDebug.TAG, "Weibo not support this type of share action. type: " + type);

        return false;
    }

    private boolean validText() {
        final String text = this.text;
        if (null == text || text.length() >= TEXT_MAX_LENGTH) {
            lg.w(SocialDebug.TAG, "Text length is overlong!!");
            return false;
        }

        if (SocialDebug.DEBUG) {
            lg.v(SocialDebug.TAG, "Text is valid. text: " + text);
        }

        return true;
    }

    private boolean validImage() {
        final String imagePath = this.imagePath;
        final String imageUrl = this.imageUrl;

        if (TextUtils.isEmpty(imagePath) && TextUtils.isEmpty(imageUrl)) {
            lg.w(SocialDebug.TAG, "Neither imagePath or imageUrl must not be null.");
            return false;
        }

        if (SocialDebug.DEBUG) {
            lg.v(SocialDebug.TAG, "Image is valid. path: " + imagePath + ", url: " + imageUrl);
        }
        return true;
    }

}
