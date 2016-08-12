package com.cnbleu.social.shareable;

import com.cnbleu.social.utils.lg;
import com.cnbleu.social.SocialDebug;

import java.io.Serializable;

/**
 * <b>Project:</b> android<br>
 * <b>Create Date:</b> 16/3/29<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public abstract class ShareParams<T extends ShareParams> implements Serializable {

    public enum Type {
        /** 默认, 必须要指定分享类型 */
        DEFAULT,
        /** 文本 */
        TEXT,
        /** 图片 */
        IMAGE,
        /** 图文 */
        TEXT_IMAGE,
        /** 音频 */
        MUSIC,
        /** 视频 */
        VIDEO,
        /** 网页 */
        WEBPAGE,
        /** 应用 */
        APPS,
        /** 文件 */
        FILE,
        /** 表情 */
        EMOJ,

    }

    /** 分享类型 */
    protected Type type;
    /** 标题 */
    protected String title;
    /** 内容 */
    protected String text;
    /** 图片链接 */
    protected String imageUrl;
    /** 图片文件地址 */
    protected String imagePath;
    /** 图片资源id */
    protected int imageRes;
    /** URL */
    protected String url;
    /** 缩略图 */
    public String thumb;

    @SuppressWarnings("unchecked")
    protected T type(Type type) {
        this.type = type;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    protected T imagePath(String image) {
        this.imagePath = image;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    protected T imageUrl(String image) {
        this.imageUrl = image;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    protected T imageRes(int res) {
        this.imageRes = res;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    protected T title(String title) {
        this.title = title;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    protected T text(String text) {
        this.text = text;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    protected T thumb(String thumb) {
        this.thumb = thumb;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    protected T url(String url) {
        this.url = url;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public boolean validParams() {
        final boolean resutl = type != Type.DEFAULT;
        if (!resutl && SocialDebug.DEBUG) {
            lg.w(SocialDebug.TAG, "You must set the type of share with 'ShareParams.type() method!!'");
        }
        return resutl;
    }
}
