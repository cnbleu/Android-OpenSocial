package com.cnbleu.social.shareable;

import com.cnbleu.social.platform.IPlatform;

/**
 * <b>Project:</b> android<br>
 * <b>Create Date:</b> 16/3/29<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public interface IShareable {

    /**
     * 分享调用用口函数
     *
     * @param params
     */
    void share(ShareParams params);

    /**
     * 分享平台执行分享过程
     */
    void doShare();

    <T> T getShareParams();

    void setShareActionListener(ShareActionListener listener);

    void setPlatform(IPlatform platform);

}
