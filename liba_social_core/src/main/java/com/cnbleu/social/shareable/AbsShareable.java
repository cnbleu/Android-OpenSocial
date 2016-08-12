package com.cnbleu.social.shareable;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Process;
import android.text.TextUtils;

import com.cnbleu.social.platform.IPlatform;
import com.cnbleu.social.ParamsException;

/**
 * <b>Project:</b> android<br>
 * <b>Create Date:</b> 16/3/29<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public abstract class AbsShareable implements IShareable {

    private static final String RESULT_ACTION = "com.want.social.ACTION_SHARE";
    private static final String RESULT_WHAT = "com.want.social.WHAT";
    private static final String RESULT_OBJ = "com.want.social.OBJ";

    private static final int RESULT_SUCCESS = 0;
    private static final int RESULT_CANCEL = 1;
    private static final int RESULT_ERROR = 2;

    protected Context mContext;
    private ShareParams mShareParams;
    private IPlatform mPlatform;
    protected ShareActionListener actionListener;

    private IntentFilter mIntentFilter = new IntentFilter(RESULT_ACTION);
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (null != actionListener && !TextUtils.isEmpty(action)) {
                final int what = intent.getIntExtra(RESULT_WHAT, -1);
                final IPlatform platform = getPlatform();
                switch (what) {
                    case RESULT_SUCCESS: {
                        actionListener.onSuccess(platform);
                        break;
                    }
                    case RESULT_CANCEL: {
                        actionListener.onCancel(platform);
                        break;
                    }
                    case RESULT_ERROR: {
                        final Throwable e = (Throwable) intent.getSerializableExtra(RESULT_OBJ);
                        actionListener.onError(platform, e);
                        break;
                    }
                    case -1:
                    default: {
                        break;
                    }
                }

                // we should unregister this receiver after any 'what' processed when
                // the 'has leaked IntentReceiver' warnings disappeared
                unregister(context);
            }
        }

        private void unregister(Context context) {
            context.unregisterReceiver(this);
        }
    };

    public AbsShareable(Context context) {
        this.mContext = context;
    }

    public void setPlatform(IPlatform platform) {
        this.mPlatform = platform;
    }

    protected IPlatform getPlatform() {
        return mPlatform;
    }

    public static void notifyResult(Context context, Intent intent) {
        intent.setAction(RESULT_ACTION);
        context.sendBroadcast(intent);
    }

    public static void notifySuccess(Context context) {
        Intent intent = new Intent();
        intent.putExtra(RESULT_WHAT, RESULT_SUCCESS);
        notifyResult(context, intent);
    }

    public static void notifyCancel(Context context) {
        Intent intent = new Intent();
        intent.putExtra(RESULT_WHAT, RESULT_CANCEL);
        notifyResult(context, intent);
    }

    public static void notifyError(Context context, Throwable e) {
        Intent intent = new Intent();
        intent.putExtra(RESULT_WHAT, RESULT_CANCEL);
        intent.putExtra(RESULT_OBJ, e);
        notifyResult(context, intent);
    }

    /**
     * 分享调用用口函数
     *
     * @param params
     */
    @Override
    public void share(ShareParams params) {
        this.mShareParams = params;
        mContext.registerReceiver(mReceiver, mIntentFilter);
        if (mShareParams.validParams()) {
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    setPriority(Process.THREAD_PRIORITY_BACKGROUND);
                    // 1) process share action
                    doShare();
                }
            }.start();
        } else {
            notifyError(mContext, new ParamsException());
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getShareParams() {
        return (T) mShareParams;
    }

    @Override
    public void setShareActionListener(ShareActionListener listener) {
        this.actionListener = listener;
    }
}
