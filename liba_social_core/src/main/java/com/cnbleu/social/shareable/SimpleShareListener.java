package com.cnbleu.social.shareable;

import android.content.Context;
import android.widget.Toast;

import com.cnbleu.social.R;
import com.cnbleu.social.platform.IPlatform;

/**
 * <b>Project:</b> project_hotclub<br>
 * <b>Create Date:</b> 7/21/16<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class SimpleShareListener implements ShareActionListener {


    private Context mContext;

    public SimpleShareListener(Context context) {
        this.mContext = context;
    }

    protected void onNotifySuccess(IPlatform platform, String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    protected void onNotifyCancel(IPlatform platform, String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    protected void onNotifyError(IPlatform platform, String msg, Throwable e) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(IPlatform platform) {
        onNotifySuccess(platform, mContext.getString(R.string.share_result_success));
    }

    @Override
    public void onError(IPlatform platform, Throwable e) {
        onNotifyError(platform, mContext.getString(R.string.share_result_error), e);
    }

    @Override
    public void onCancel(IPlatform platform) {
        onNotifyCancel(platform, mContext.getString(R.string.share_result_cancel));
    }
}
