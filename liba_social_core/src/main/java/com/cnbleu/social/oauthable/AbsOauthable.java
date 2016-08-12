package com.cnbleu.social.oauthable;

/**
 * <b>Project:</b> android<br>
 * <b>Create Date:</b> 16/3/30<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public abstract class AbsOauthable implements IOauthable {


    @Override
    public void auth(IOauthParams params) {

    }

    @Override
    public IOauthParams getOauthParams() {
        return null;
    }

    @Override
    public void setOauthActionListener(OauthActionListener listener) {

    }
}
