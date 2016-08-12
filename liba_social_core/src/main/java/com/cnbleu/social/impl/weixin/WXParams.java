package com.cnbleu.social.impl.weixin;

import android.text.TextUtils;

import com.cnbleu.social.utils.lg;
import com.cnbleu.social.shareable.ShareParams;

import static com.cnbleu.social.SocialDebug.DEBUG;
import static com.cnbleu.social.SocialDebug.TAG;

/**
 * <b>Project:</b> android<br>
 * <b>Create Date:</b> 16/3/29<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class WXParams extends ShareParams<WXParams> {

    /** 音乐链接 */
    public String musicUrl;
    /** 缩略图 */
    public String thumb;

    /** 缩略图, 分享emoji时需要（可选） */
//    public String thumbImage;
    public WXParams musicUrl(String url) {
        this.musicUrl = url;
        return this;
    }

    public WXParams thumb(String thumb) {
        this.thumb = thumb;
        return this;
    }

    @Override
    public WXParams imagePath(String image) {
        return super.imagePath(image);
    }

    @Override
    public WXParams imageRes(int res) {
        return super.imageRes(res);
    }

    @Override
    public WXParams imageUrl(String image) {
        return super.imageUrl(image);
    }

    @Override
    public WXParams text(String text) {
        return super.text(text);
    }

    @Override
    public WXParams title(String title) {
        return super.title(title);
    }

    @Override
    public WXParams type(Type type) {
        return super.type(type);
    }

    @Override
    public WXParams url(String url) {
        return super.url(url);
    }

    @Override
    public boolean validParams() {
        if (!super.validParams()) {
            return false;
        }

        final Type type = this.type;

        if (type == Type.TEXT) {
            return WXValidUtils.validText(text);
        } else if (type == Type.TEXT_IMAGE) {
            return WXValidUtils.validText(title) &&
                   WXValidUtils.validText(text) &&
                   WXValidUtils.validImage(imagePath, imageUrl, imageRes);
        } else if (type == Type.IMAGE) {
            return WXValidUtils.validImage(imagePath, imageUrl, imageRes);
        } else if (type == Type.MUSIC) {
            // 微信音乐分享时需要以下参数:
            // 1. title
            // 2. text
            // 3. image
            // 4. url
            // 5. musicUrl
            return WXValidUtils.validText(title) &&
                   WXValidUtils.validText(text) &&
                   WXValidUtils.validImage(imagePath, imageUrl, imageRes) &&
                   WXValidUtils.validText(url) &&
                   validMusicUrl();
        } else if (type == Type.VIDEO) {
            // 微信视频分享时需要以下参数:
            // 1. title
            // 2. text
            // 3. image
            // 4. url
            return WXValidUtils.validText(title) &&
                   WXValidUtils.validText(text) &&
                   WXValidUtils.validImage(imagePath, imageUrl, imageRes) &&
                   WXValidUtils.validText(url);
        } else if (type == Type.WEBPAGE) {
            // 微信网页分享时需要以下参数:
            // 1. title
            // 2. text
            // 3. image
            // 4. url
            return WXValidUtils.validText(title) &&
                   WXValidUtils.validText(text) &&
                   WXValidUtils.validImage(imagePath, imageUrl, imageRes) &&
                   WXValidUtils.validText(url);
        } else if (type == Type.APPS) {
// TODO: 16/3/30 支持app数据分享
        } else if (type == Type.FILE) {
// TODO: 16/3/30 支持文件数据分享
        } else if (type == Type.EMOJ) {
            // 微信表情分享时需要以下参数:
            // 1. image
            return WXValidUtils.validImage(imagePath, imageUrl, imageRes);
        }

        lg.w(TAG, "Wechat not support this type of share action. type: " + type);

        return false;
    }

    protected boolean validMusicUrl() {
        final boolean result = !TextUtils.isEmpty(musicUrl);
        if (!result) {
            lg.w(TAG, "musicUrl must not be null !!");
        } else if (DEBUG) {
            lg.v(TAG, "musicUrl is valid, url: " + musicUrl);
        }

        return result;
    }
}
