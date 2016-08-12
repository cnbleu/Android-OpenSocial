package com.cnbleu.social;

import com.cnbleu.social.oauthable.IOauthParams;
import com.cnbleu.social.oauthable.OauthActionListener;
import com.cnbleu.social.platform.IPlatform;
import com.cnbleu.social.shareable.ShareActionListener;
import com.cnbleu.social.shareable.ShareParams;

/**
 * <b>Project:</b> android<br>
 * <b>Create Date:</b> 16/3/29<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class SocialAgent {

    private IPlatform mPlatform;
    private ShareParams mShareParams;
    private IOauthParams mOauthParams;
    private ShareActionListener mShareActionListener;
    private OauthActionListener mOauthActionListener;

    private SocialAgent() {
    }

    public static SocialAgent newInstance() {
        return new SocialAgent();
    }

    public SocialAgent platform(IPlatform platform) {
        this.mPlatform = platform;
        return this;
    }

    public SocialAgent shareParams(ShareParams params) {
        mPlatform.setShareParams(params);
        return this;
    }

    public SocialAgent shareActionListener(ShareActionListener listener) {
        mPlatform.setShareActionListener(listener);
        return this;
    }

    public void share() {
        mPlatform.startShare();
    }

    public SocialAgent oauthParams(IOauthParams params) {
        this.mOauthParams = params;
        return this;
    }

    public SocialAgent oauthActionListener(OauthActionListener listener) {
        this.mOauthActionListener = listener;
        return this;
    }

    public void oauth() {
        mPlatform.startOauth();
    }


}
