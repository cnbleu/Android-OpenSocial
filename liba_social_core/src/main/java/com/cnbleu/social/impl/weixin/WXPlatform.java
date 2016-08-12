package com.cnbleu.social.impl.weixin;

import android.content.Context;

import com.cnbleu.social.oauthable.IOauthable;
import com.cnbleu.social.platform.AbsPlatform;
import com.cnbleu.social.Configuration;
import com.cnbleu.social.shareable.IShareable;

/**
 * <b>Project:</b> android<br>
 * <b>Create Date:</b> 16/3/30<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class WXPlatform extends AbsPlatform {

    public WXPlatform(Context context, int scen, Configuration configuration) {
        this(configuration, new WXShareable(context, scen, configuration.appid), null);
    }


    protected WXPlatform(Configuration configuration, IShareable shareable,
                         IOauthable oauthable) {
        super(configuration, shareable, oauthable);
    }
}
