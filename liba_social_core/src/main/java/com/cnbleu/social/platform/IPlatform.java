package com.cnbleu.social.platform;

import com.cnbleu.social.oauthable.IOauthParams;
import com.cnbleu.social.oauthable.OauthActionListener;
import com.cnbleu.social.shareable.ShareActionListener;
import com.cnbleu.social.shareable.ShareParams;

/**
 * <b>Project:</b> android<br>
 * <b>Create Date:</b> 16/3/29<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public interface IPlatform {

    /**
     * 支持的第三方平台
     */
    enum Name {
        /** 微博 */
        WEIBO("weibo"),
        /** 微信聊天 */
        WEIXIN_CHAT("wechat"),
        /** 微信朋友圈 */
        WEIXIN_MOMENTS("moments"),
        /** 微信收藏 */
        WEIXIN_FAVORITE("favorite"),
        /** QQ */
        QQ("qq"),
        /** QQ空间 */
        QZONE("qzone");

        private String name;

        Name(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public static Name nameOf(String name) {
            if (WEIBO.name.equals(name)) {
                return WEIBO;
            } else if (WEIXIN_CHAT.name.equals(name)) {
                return WEIXIN_CHAT;
            } else if (WEIXIN_MOMENTS.name.equals(name)) {
                return WEIXIN_MOMENTS;
            } else if (WEIXIN_FAVORITE.name.equals(name)) {
                return WEIXIN_FAVORITE;
            } else if (QQ.name.equals(name)) {
                return QQ;
            } else if (QZONE.name.equals(name)) {
                return QZONE;
            }
            return null;
        }

    }

    void setShareParams(ShareParams params);

    void setShareActionListener(ShareActionListener listener);

    void startShare();

    void startOauth();

    void setOauthParams(IOauthParams params);

    void setOauthActionListener(OauthActionListener listener);
}
