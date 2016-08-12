package com.cnbleu.social;

import android.content.Context;
import android.util.Xml;

import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.cnbleu.social.utils.lg;
import com.cnbleu.social.impl.weixin.WXPlatform;
import com.cnbleu.social.platform.IPlatform;

import org.xmlpull.v1.XmlPullParser;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.HashMap;

import static com.cnbleu.social.SocialDebug.TAG;

/**
 * <b>Project:</b> android<br>
 * <b>Create Date:</b> 16/3/30<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class PlatformFactory {
    private static final String XML_NAME = "social_platforms.xml";

    private static final int[] sLock = new int[0];
    private static PlatformFactory INSTANCE;

    private WeakReference<Context> mContext;
    private HashMap<String, Configuration> mConfigurations;

    private PlatformFactory(Context context) {
        this.mContext = new WeakReference<Context>(context.getApplicationContext());
        initPlatforms();
    }

    private static void instance(Context context) {
        if (null == INSTANCE) {
            synchronized (sLock) {
                if (null == INSTANCE) {
                    INSTANCE = new PlatformFactory(context);
                }
            }
        }
    }

    private void initPlatforms() {
        final Context context = mContext.get();

        final InputStream ins;
        try {
            ins = context.getAssets().open(XML_NAME);
        } catch (IOException e) {
            lg.w(TAG, "Open " + XML_NAME + " file error !!");
            return;
        }

        XmlPullParser parser = Xml.newPullParser();
        try {
            parser.setInput(ins, "utf-8");

            int eventType = parser.getEventType();
            Configuration configuration = null;
            while (XmlPullParser.END_DOCUMENT != eventType) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT: {
                        mConfigurations = new HashMap<>();
                        break;
                    }

                    case XmlPullParser.START_TAG: {
                        final int depth = parser.getDepth();
                        final String tagName = parser.getName();
                        if (1 == depth) {
                            if (!Configuration.TAG_PLATFORMS.equals(tagName)) {
                                lg.w(TAG, "Root tag must be " + Configuration.TAG_PLATFORMS);
                                return;
                            }

                            break;
                        }

                        if (2 != depth) {
                            break;
                        }

                        configuration = new Configuration();
                        configuration.platform = tagName;
                        configuration.platformName = getPlatformName(context, tagName);

                        String name;
                        String value;
                        for (int i = 0; i < parser.getAttributeCount(); i++) {
                            name = parser.getAttributeName(i);
                            value = parser.getAttributeValue(i);
                            configuration.set(name, value);
                        }
                        break;
                    }

                    case XmlPullParser.END_TAG: {
                        if (null != configuration) {
                            mConfigurations.put(configuration.platformName, configuration);
                            configuration = null;
                        }
                        break;
                    }

                }
                eventType = parser.next();
            }
        } catch (Exception e) {
            lg.w(TAG, Configuration.TAG_PLATFORMS + " has error, please check !!");
        }
    }

    private String getString(Context context, int resid) {
        return context.getString(resid);
    }

    public static HashMap<String, Configuration> getConfigurations(Context context) {
        instance(context);
        return INSTANCE.mConfigurations;
    }

    public static Configuration getConfiguration(Context context, IPlatform.Name name) {
        instance(context);
        return INSTANCE.mConfigurations.get(INSTANCE.getPlatformName(context, name.getName()));
    }

    public static IPlatform create(Context context, IPlatform.Name name) {
        if (null == name) {
            return null;
        }
        instance(context);

        IPlatform platform;
        switch (name) {
            case WEIXIN_CHAT:
                platform = INSTANCE.createWXPlatform(context, SendMessageToWX.Req.WXSceneSession, name);
                break;
            case WEIXIN_MOMENTS: {
                platform = INSTANCE.createWXPlatform(context, SendMessageToWX.Req.WXSceneTimeline, name);
                break;
            }
            default: {
                platform = null;
            }
        }

        if (null == platform) {
            throw new IllegalArgumentException("Platform is null!!");
        }

        return platform;
    }

    private WXPlatform createWXPlatform(Context context, int scen, IPlatform.Name name) {
        final String platform = getPlatformName(context, name.getName());
        final Configuration configuration = mConfigurations.get(platform);
        return new WXPlatform(context, scen, configuration);
    }

    private String getPlatformName(Context context, String name) {
        return getString(context, context.getResources()
                                         .getIdentifier(name.toLowerCase(),
                                                        "string",
                                                        context.getPackageName()));
    }
}
