package com.cnbleu.social.agent.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.cnbleu.social.Configuration;
import com.cnbleu.social.agent.IPlatformProxy;
import com.cnbleu.social.agent.ShareHelper;
import com.cnbleu.social.agent.databinding.SocialDialogItemLayoutBinding;
import com.cnbleu.social.shareable.ShareActionListener;
import com.cnbleu.social.shareable.ShareParams;
import com.cnbleu.social.agent.databinding.SocialDialogLayoutBinding;

import java.util.Collections;
import java.util.List;

/**
 * <b>Project:</b> project_hotclub<br>
 * <b>Create Date:</b> 7/28/16<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class SocialDialog extends DialogFragment implements View.OnClickListener {
    public static final String DIALOG_TAG = "social_fragment";
    private static final String EXT_DATA = "ext_params";


    public interface OnDialogListener {
        void onDismiss(DialogInterface dialog);

        void onCancel(DialogInterface dialog);
    }

    private ShareParams mShareParams;
    private ShareActionListener mShareActionListener;
    private OnDialogListener mOnDialogListener;
    private IPlatformProxy mPlatformProxy;

    private AppCompatActivity mActivity;

    public static SocialDialog newInstance(AppCompatActivity activity, ShareParams params) {
        SocialDialog dialog = new SocialDialog();
        dialog.setActivity(activity);

        Bundle bundle = new Bundle();
        bundle.putSerializable(EXT_DATA, params);

        dialog.setArguments(bundle);
        return dialog;
    }


    public void share() {
        show(mActivity.getSupportFragmentManager(), DIALOG_TAG);
    }

    private void setActivity(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    public void setShareActionListener(ShareActionListener listener) {
        this.mShareActionListener = listener;
    }

    public void setOnDialogListener(OnDialogListener listener) {
        this.mOnDialogListener = listener;
    }

    public void setPlatformProxy(IPlatformProxy proxy) {
        this.mPlatformProxy = proxy;
    }

    protected View onCreateDialogContentView(Bundle savedInstanceState) {
        return DataBindingUtil.inflate(LayoutInflater.from(getActivity()),
                                       com.cnbleu.social.agent.R.layout.social_dialog_layout,
                                       null,
                                       false)
                              .getRoot();
    }

    protected void onConfigDialog(Dialog dialog) {
        dialog.setCanceledOnTouchOutside(true);
        dialog.setOnDismissListener(this);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), com.cnbleu.social.agent.R.style.Social_Gravity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // must be called before set content

        final View view = onCreateDialogContentView(savedInstanceState);
        dialog.setContentView(view);
        onConfigDialog(dialog);
        onViewCreated(view, savedInstanceState);

        return dialog;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        if (null != savedInstanceState) {
//            dismiss();
//        }

        mShareParams = (ShareParams) getArguments().getSerializable(EXT_DATA);

        final List<Configuration> configurations = ShareHelper.getAvailablePlatforms(getActivity());

        // add configurations from platform proxy
        if (null != mPlatformProxy) {
            final List<Configuration> configs = mPlatformProxy.onCreateConfiguration();
            if (null != configs) {
                configurations.addAll(configs);

                // sort
                Collections.sort(configurations);
            }
        }

        Adapter adapter = new Adapter();

        final SocialDialogLayoutBinding binding = DataBindingUtil.getBinding(view);
        binding.setAdapter(adapter);

        final GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 4);
        binding.setLayoutManager(layoutManager);
        binding.setClick(this);

        adapter.updateData(configurations);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (mOnDialogListener != null) {
            mOnDialogListener.onDismiss(dialog);
        }
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        if (mOnDialogListener != null) {
            mOnDialogListener.onCancel(dialog);
        }
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }

    private class Adapter extends RecyclerView.Adapter<ViewHolder> {

        private List<Configuration> mDatas;

        Adapter() {
        }

        public void updateData(List<Configuration> datas) {
            this.mDatas = datas;
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final LayoutInflater inflater =
                    (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final SocialDialogItemLayoutBinding binding =
                    DataBindingUtil.inflate(inflater, com.cnbleu.social.agent.R.layout.social_dialog_item_layout, null, true);
            return new ViewHolder(binding.getRoot());
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            final Configuration configuration = mDatas.get(position);
            final SocialDialogItemLayoutBinding binding = DataBindingUtil.getBinding(holder.itemView);
            binding.setViewmodel(new ViewModel(SocialDialog.this,
                                               configuration,
                                               mPlatformProxy,
                                               mShareParams,
                                               mShareActionListener));
        }

        @Override
        public int getItemCount() {
            return null == mDatas ? 0 : mDatas.size();
        }
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
