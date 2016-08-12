package com.cnbleu.social.oauthable;

/**
 * <b>Project:</b> android<br>
 * <b>Create Date:</b> 16/3/29<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public interface IOauthable {

    void auth(IOauthParams params);

    void doAuthorize();

    IOauthParams getOauthParams();

    void setOauthActionListener(OauthActionListener listener);
}
