package com.cnbleu.social.impl.weixin;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;

import com.cnbleu.social.Utils;
import com.cnbleu.social.shareable.InternalShareParams;
import com.cnbleu.social.shareable.ShareParams;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXEmojiObject;
import com.tencent.mm.sdk.modelmsg.WXImageObject;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXTextObject;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.cnbleu.social.ParamsException;
import com.cnbleu.social.shareable.AbsShareable;
import com.cnbleu.social.utils.NetUtils;

import java.io.File;
import java.lang.ref.WeakReference;

/**
 * <b>Project:</b> android<br>
 * <b>Create Date:</b> 16/3/30<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class WXShareable extends AbsShareable {


    private WeakReference<Context> mContext;
    private IWXAPI mIWXAPI;
    private int scene;

    public WXShareable(Context context, int scene, String appid) {
        super(context);
        this.scene = scene;
        this.mContext = new WeakReference<Context>(context);
        mIWXAPI = WXAPIFactory.createWXAPI(context, appid);
    }

    /**
     * 分享平台执行分享过程
     */
    @Override
    public void doShare() {
        final InternalShareParams params = new InternalShareParams((ShareParams) getShareParams());
        final ShareParams.Type type = params.type;

        WXMediaMessage mediaMessage = null;
        String transaction = null;
        switch (type) {
            case TEXT: {
                transaction = "text";
                mediaMessage = prepareShareText(params);
                break;
            }
            case IMAGE: {
                transaction = "image";
                mediaMessage = prepareShareImage(params);
                break;
            }
            case TEXT_IMAGE: {
                transaction = "text_image";
                mediaMessage = prepareShareTextImage(params);
                break;
            }
            case EMOJ: {
                transaction = "emoji";
                mediaMessage = prepareShareEmoji(params);
                break;
            }
            case WEBPAGE: {
                transaction = "webpage";
                mediaMessage = prepareShareWebpage(params);
                break;
            }
        }

        // send message to wx
        if (null != mediaMessage) {
            SendMessageToWX.Req req = new SendMessageToWX.Req();
            req.transaction = buildTransaction(transaction);
            req.message = mediaMessage;
            req.scene = scene;

            mIWXAPI.sendReq(req);
        }
    }

    protected String buildTransaction(String type) {
        return TextUtils.isEmpty(type)
               ? String.valueOf(System.currentTimeMillis())
               : type + System.currentTimeMillis();
    }

    protected WXMediaMessage prepareShareText(InternalShareParams params) {
        WXMediaMessage mediaMessage = new WXMediaMessage();

        WXTextObject object = new WXTextObject();
        object.text = params.text;

        mediaMessage.mediaObject = object;
        mediaMessage.description = params.text;

        return mediaMessage;
    }

    protected WXMediaMessage prepareShareImage(InternalShareParams params) {
        final Context context = mContext.get();
        WXImageObject object = new WXImageObject();
        if (0 != params.imageRes) {
            object.imageData = Utils.bmpToByte(Utils.resToBmp(context.getResources(), params.imageRes), true);
        } else if (!TextUtils.isEmpty(params.imagePath)) {
            object.imagePath = params.imagePath;
        } else if (!TextUtils.isEmpty(params.imageUrl)) {
            final File file = NetUtils.downloadImage(params.imageUrl);
            if (file != null) {
                object.imagePath = file.getAbsolutePath();
            } else {
                notifyError(context, new ParamsException("download image error."));
                return null;
            }
        }

        WXMediaMessage mediaMessage = new WXMediaMessage();
        mediaMessage.mediaObject = object;
        mediaMessage.thumbData = null;

        return mediaMessage;
    }

    protected WXMediaMessage prepareShareTextImage(InternalShareParams params) {
        WXMediaMessage mediaMessage = prepareShareImage(params);
        mediaMessage.title = params.title;
        mediaMessage.description = params.text;
        return mediaMessage;
    }

    protected WXMediaMessage prepareShareEmoji(InternalShareParams params) {
        WXEmojiObject object = new WXEmojiObject();
        final Context context = mContext.get();
        if (0 != params.imageRes) {
            object.emojiData = Utils.bmpToByte(Utils.resToBmp(context.getResources(), params.imageRes), true);
        } else if (!TextUtils.isEmpty(params.imagePath)) {
            object.emojiPath = params.imagePath;
        } else if (!TextUtils.isEmpty(params.imageUrl)) {
            final File file = NetUtils.downloadImage(params.imageUrl);
            if (null != file) {
                object.emojiPath = file.getAbsolutePath();
            } else {
                notifyError(context, new ParamsException("download image error."));
                return null;
            }
        }

        final byte[] thumb;
        if (!TextUtils.isEmpty(params.thumb)) {
            thumb = Utils.readFromFile(params.thumb, 0, (int) new File(params.thumb).length());
        } else {
            Bitmap bitmap;
            if (null != object.emojiData) {
                bitmap = Utils.createThumb(Utils.resToBmp(context.getResources(), params.imageRes));
            } else {
                bitmap = Utils.createThumb(new File(object.emojiPath));
            }
            thumb = Utils.bmpToByte(bitmap, true);
        }


        WXMediaMessage mediaMessage = new WXMediaMessage();
        mediaMessage.title = params.title;
        mediaMessage.description = params.text;
        mediaMessage.thumbData = thumb;
        mediaMessage.mediaObject = object;
        return mediaMessage;
    }

    private WXMediaMessage prepareShareWebpage(InternalShareParams params) {
        final Context context = mContext.get();
        WXWebpageObject object = new WXWebpageObject();
        object.webpageUrl = params.url;

        Bitmap bitmap = null;
        if (0 != params.imageRes) {
            bitmap = Utils.resToBmp(context.getResources(), params.imageRes);
        } else if (!TextUtils.isEmpty(params.imagePath)) {
            bitmap = Utils.fileToBmp(params.imagePath);
        } else if (!TextUtils.isEmpty(params.imageUrl)) {
            final File file = NetUtils.downloadImage(params.imageUrl);
            if (null != file) {
                bitmap = Utils.fileToBmp(file.getAbsolutePath());
            } else {
                notifyError(context, new ParamsException("download image error."));
                return null;
            }
        }

        WXMediaMessage mediaMessage = new WXMediaMessage();
        mediaMessage.title = params.title;
        mediaMessage.description = params.text;
        mediaMessage.mediaObject = object;

        if (null != bitmap) {
            bitmap = Utils.compressBitmap(bitmap, Utils.WX_THUMB_MAX);
            mediaMessage.thumbData = Utils.bmpToByte(bitmap, true);
        }
        return mediaMessage;
    }
}
