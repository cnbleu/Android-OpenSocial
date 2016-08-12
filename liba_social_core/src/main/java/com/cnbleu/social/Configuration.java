package com.cnbleu.social;

/**
 * <b>Project:</b> android<br>
 * <b>Create Date:</b> 16/3/29<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class Configuration implements Comparable<Configuration> {

    public static final String TAG_PLATFORMS = "platforms";
    public static final String TAG_ID = "id";
    public static final String TAG_SORT = "sort";
    public static final String TAG_KEY = "key";
    public static final String TAG_SECRET = "secret";
    public static final String TAG_URL = "url";
    public static final String TAG_APPID = "appid";
    public static final String TAG_CLIENT = "client";
    public static final String TAG_ENABLE = "enable";
    public static final String TAG_BYPASSED = "bypassed";


    /** 平台ID */
    public int id;
    /** 平台排序ID, 越大越靠后 */
    public int sort;
    /** 开放平台的key */
    public String key;
    /** 开放平台的secret */
    public String secret;
    /** 回调URL */
    public String url;
    /** 微信等平台的appid */
    public String appid;
    /** 是否通过客户端, 默认需要通过客户端 */
    public boolean client = true;
    /** 是否有效, 默认无效 */
    public boolean enable = false;
    /** 是否绕过客户端验证 */
    public boolean bypassed = false;
    /** 平台名字 */
    public String platformName;
    /** 平台唯一标识 */
    public String platform;

    public final void set(String name, String value) {
        if (TAG_ID.equals(name)) {
            this.id = Integer.valueOf(value);
        } else if (TAG_SORT.equals(name)) {
            this.sort = Integer.valueOf(value);
        } else if (TAG_KEY.equals(name)) {
            this.key = value;
        } else if (TAG_SECRET.equals(name)) {
            this.secret = value;
        } else if (TAG_URL.equals(name)) {
            this.url = value;
        } else if (TAG_APPID.equals(name)) {
            this.appid = value;
        } else if (TAG_CLIENT.equals(name)) {
            this.client = Boolean.valueOf(value);
        } else if (TAG_ENABLE.equals(name)) {
            this.enable = Boolean.valueOf(value);
        } else if (TAG_BYPASSED.equals(name)) {
            this.bypassed = Boolean.valueOf(value);
        }
    }

    public String getAppid() {
        return appid;
    }

    public boolean isBypassed() {
        return bypassed;
    }

    public boolean isClient() {
        return client;
    }

    public boolean isEnable() {
        return enable;
    }

    public int getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public String getPlatformName() {
        return platformName;
    }

    public String getPlatform(){
        return platform;
    }
    public String getSecret() {
        return secret;
    }

    public int getSort() {
        return sort;
    }

    public String getUrl() {
        return url;
    }

    @SuppressWarnings("unchecked")
    public final <T> T get(String name) {
        if (TAG_ID.equals(name)) {
            return (T) Integer.valueOf(this.id);
        } else if (TAG_SORT.equals(name)) {
            return (T) Integer.valueOf(this.sort);
        } else if (TAG_KEY.equals(name)) {
            return (T) this.key;
        } else if (TAG_SECRET.equals(name)) {
            return (T) this.secret;
        } else if (TAG_URL.equals(name)) {
            return (T) this.url;
        } else if (TAG_APPID.equals(name)) {
            return (T) this.appid;
        } else if (TAG_CLIENT.equals(name)) {
            return (T) Boolean.valueOf(this.client);
        } else if (TAG_ENABLE.equals(name)) {
            return (T) Boolean.valueOf(this.enable);
        } else if (TAG_BYPASSED.equals(name)) {
            return (T) Boolean.valueOf(this.bypassed);
        }
        return null;
    }

    @Override
    public int compareTo(Configuration another) {
        return sort - another.sort;
    }
}