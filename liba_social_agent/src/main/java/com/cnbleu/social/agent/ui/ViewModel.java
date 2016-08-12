package com.cnbleu.social.agent.ui;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;
import android.widget.TextView;

import com.cnbleu.social.PlatformFactory;
import com.cnbleu.social.Configuration;
import com.cnbleu.social.SocialAgent;
import com.cnbleu.social.agent.IPlatformProxy;
import com.cnbleu.social.platform.IPlatform;
import com.cnbleu.social.shareable.ShareActionListener;
import com.cnbleu.social.shareable.ShareParams;
import com.cnbleu.social.shareable.SimpleShareListener;

/**
 * <b>Project:</b> project_hotclub<br>
 * <b>Create Date:</b> 7/28/16<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class ViewModel extends BaseObservable {

    private DialogFragment mFragment;
    private Context mContext;
    private Configuration mConfiguration;
    private IPlatformProxy mPlatformProxy;
    private ShareParams mShareParams;
    private ShareActionListener mShareActionListener;

    public ViewModel(DialogFragment fragment,
                     Configuration configuration,
                     IPlatformProxy proxy,
                     ShareParams params,
                     ShareActionListener listener) {
        this.mFragment = fragment;
        this.mContext = mFragment.getActivity();
        this.mConfiguration = configuration;
        this.mPlatformProxy = proxy;
        this.mShareParams = params;
        this.mShareActionListener = listener;
    }

    public void onClick(View view) {
        final String name = mConfiguration.getPlatform();
        IPlatform platform = PlatformFactory.create(mContext, IPlatform.Name.nameOf(name));

        if (null == platform && null != mPlatformProxy) {
            platform = mPlatformProxy.onCreatePlatform(mConfiguration);
        }

        final ShareActionListener listener =
                null != mShareActionListener ? mShareActionListener
                                             : new SimpleShareListener(mContext);
        SocialAgent.newInstance()
                   .platform(platform)
                   .shareParams(mShareParams)
                   .shareActionListener(listener)
                   .share();
        mFragment.dismiss();
    }

    @Bindable
    public int getItemImage() {
        return mContext.getResources()
                       .getIdentifier("social_platform_" + mConfiguration.getPlatform(),
                                      "drawable",
                                      mContext.getPackageName());
    }

    @Bindable
    public int getItemName() {
        return mContext.getResources()
                       .getIdentifier(mConfiguration.getPlatform(),
                                      "string",
                                      mContext.getPackageName());
    }


    @BindingAdapter(value = {"itemText"}, requireAll = false)
    public static void itemName(TextView view, int res) {
        view.setText(res);
    }

    @BindingAdapter(value = {"itemImage"}, requireAll = false)
    public static void itemImage(final TextView view, int res) {
        final Context context = view.getContext();
        final Drawable drawable = ResourcesCompat.getDrawable(context.getResources(),
                                                              res,
                                                              context.getTheme());
        view.post(new Runnable() {
            @Override
            public void run() {
                final int width = (int) (view.getWidth() * 0.6f + 0.5f);
                drawable.setBounds(0, 0, width, width);
                view.setCompoundDrawables(null, drawable, null, null);
            }
        });
    }
}
