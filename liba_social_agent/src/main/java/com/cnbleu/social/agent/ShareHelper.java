package com.cnbleu.social.agent;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.cnbleu.social.PlatformFactory;
import com.cnbleu.social.Configuration;
import com.cnbleu.social.agent.ui.SocialDialog;
import com.cnbleu.social.platform.IPlatform;
import com.cnbleu.social.shareable.InternalShareParams;
import com.cnbleu.social.shareable.ShareActionListener;
import com.cnbleu.social.shareable.ShareParams;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <b>Project:</b> project_hotclub<br>
 * <b>Create Date:</b> 7/22/16<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class ShareHelper {

    private ShareActionListener mShareActionListener;
    private SocialDialog.OnDialogListener mDialogListener;
    private IPlatformProxy mPlatformProxy;

    private ShareHelper() {
        // hide
    }

    public static ShareHelper newInstance() {
        return new ShareHelper();
    }

    public ShareHelper setShareActionListener(ShareActionListener listener) {
        this.mShareActionListener = listener;
        return this;
    }

    public ShareHelper setDialogListener(SocialDialog.OnDialogListener listener) {
        this.mDialogListener = listener;
        return this;
    }

    public ShareHelper setPlatformProxy(IPlatformProxy proxy) {
        this.mPlatformProxy = proxy;
        return this;
    }

    public static List<Configuration> getAvailablePlatforms(Context context) {
        final HashMap<String, Configuration> configurations = PlatformFactory.getConfigurations(context);
        final List<Configuration> platforms = new ArrayList<>();
        for (Map.Entry<String, Configuration> entry : configurations.entrySet()) {
            if (entry.getValue().isEnable()) {
                platforms.add(entry.getValue());
            }
        }

        // sort by configuration.sort
        Collections.sort(platforms);

        return platforms;
    }

    public static Configuration getPlatform(IPlatform.Name name, Context context) {
        return PlatformFactory.getConfiguration(context, name);
    }

    public void shareText(Context context, String text) {
        share(context, new InternalShareParams().text(text));
    }

    public void shareTextImage(Context context, String text, int imageRes) {
        shareTextImage(context, text, imageRes, null, null);
    }

    public void shareTextImage(Context context, String text, File imageFile) {
        shareTextImage(context, text, 0, imageFile, null);
    }

    public void shareTextImage(Context context, String text, String imageUrl) {
        shareTextImage(context, text, 0, null, imageUrl);
    }

    private void shareTextImage(Context context,
                                String text,
                                int imageRes,
                                File imageFile,
                                String imageUrl) {
        InternalShareParams params = new InternalShareParams();
        params.text(text);
        fillImageParams(params, imageRes, imageFile, imageUrl);
        share(context, params);
    }

    public void shareImage(Context context, int imageRes) {
        shareImage(context, imageRes, null, null);
    }

    public void shareImage(Context context, File imageFile) {
        shareImage(context, 0, imageFile, null);
    }

    public void shareImage(Context context, String imageUrl) {
        shareImage(context, 0, null, imageUrl);
    }

    private void shareImage(Context context, int imageRes, File imageFile, String imageUrl) {
        InternalShareParams params = new InternalShareParams();
        fillImageParams(params, imageRes, imageFile, imageUrl);
        share(context, params);
    }

    public void shareWebpage(Context context, String title, String text, String url, int imageRes) {
        shareWebpage(context, title, text, url, imageRes, null, null);
    }

    public void shareWebpage(Context context, String title, String text, String url, File imageFile) {
        shareWebpage(context, title, text, url, 0, imageFile, null);
    }

    public void shareWebpage(Context context, String title, String text, String url, String imageUrl) {
        shareWebpage(context, title, text, url, 0, null, imageUrl);
    }

    private void shareWebpage(Context context,
                              String title,
                              String text,
                              String url,
                              int imageRes,
                              File imageFile,
                              String imageUrl) {
        InternalShareParams params = new InternalShareParams();
        params.type(ShareParams.Type.WEBPAGE)
              .title(title)
              .text(text)
              .url(url);
        fillImageParams(params, imageRes, imageFile, imageUrl);
        share(context, params);
    }

    public void shareEmoji(Context context, File emojiFile) {
        shareEmoji(context, emojiFile, null);
    }

    public void shareEmoji(Context context, String emojiUrl) {
        shareEmoji(context, null, emojiUrl);
    }

    private void shareEmoji(Context context, File emojiFile, String emojiUrl) {
        InternalShareParams params = new InternalShareParams();
        params.type(ShareParams.Type.EMOJ);
        if (emojiFile != null) {
            params.imagePath(emojiFile.getAbsolutePath());
        } else if (!TextUtils.isEmpty(emojiUrl)) {
            params.imageUrl(emojiUrl);
        }

        share(context, params);
    }

    private void fillImageParams(InternalShareParams params, int imageRes, File imageFile, String imageUrl) {
        if (0 != imageRes) {
            params.imageRes(imageRes);
        } else if (null != imageFile) {
            params.imagePath(imageFile.getAbsolutePath());
        } else if (!TextUtils.isEmpty(imageUrl)) {
            params.imageUrl(imageUrl);
        }
    }

    private void share(Context context, InternalShareParams params) {
        SocialDialog dialog = SocialDialog.newInstance((AppCompatActivity) context, params);
        dialog.setShareActionListener(mShareActionListener);
        dialog.setOnDialogListener(mDialogListener);
        dialog.setPlatformProxy(mPlatformProxy);
        dialog.share();
    }
}
