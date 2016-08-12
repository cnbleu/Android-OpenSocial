package com.cnbleu.social.shareable;

/**
 * <b>Project:</b> project_hotclub<br>
 * <b>Create Date:</b> 7/29/16<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class InternalShareParams extends ShareParams<InternalShareParams> {

    /** 分享类型 */
    public Type type;
    /** 标题 */
    public String title;
    /** 内容 */
    public String text;
    /** 图片链接 */
    public String imageUrl;
    /** 图片文件地址 */
    public String imagePath;
    /** 图片资源id */
    public int imageRes;
    /** URL */
    public String url;
    /** 缩略图 */
    public String thumb;

    public InternalShareParams() {

    }

    public InternalShareParams(ShareParams params) {
        this.type = super.type = params.type;
        this.title = super.title = params.title;
        this.text = super.text = params.text;
        this.imageUrl = super.imageUrl = params.imageUrl;
        this.imagePath = super.imagePath = params.imagePath;
        this.imageRes = super.imageRes = params.imageRes;
        this.url = super.url = params.url;
        this.thumb = super.thumb = params.thumb;
    }

    @Override
    public InternalShareParams imagePath(String image) {
        return super.imagePath(image);
    }

    @Override
    public InternalShareParams imageRes(int res) {
        return super.imageRes(res);
    }

    @Override
    public InternalShareParams imageUrl(String image) {
        return super.imageUrl(image);
    }

    @Override
    public InternalShareParams text(String text) {
        return super.text(text);
    }

    @Override
    public InternalShareParams title(String title) {
        return super.title(title);
    }

    @Override
    public InternalShareParams type(Type type) {
        return super.type(type);
    }

    @Override
    public InternalShareParams url(String url) {
        return super.url(url);
    }

    @Override
    public InternalShareParams thumb(String thumb) {
        return super.thumb(thumb);
    }
}
