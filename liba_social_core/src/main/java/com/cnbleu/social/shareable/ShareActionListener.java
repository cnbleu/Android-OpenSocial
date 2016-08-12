package com.cnbleu.social.shareable;

import com.cnbleu.social.platform.IPlatform;

/**
 * <b>Project:</b> android<br>
 * <b>Create Date:</b> 16/3/29<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public interface ShareActionListener {
    /**
     * Media share success
     *
     * @param platform
     */
    void onSuccess(IPlatform platform);

    /**
     * Media share error
     *
     * @param platform
     * @param e
     */
    void onError(IPlatform platform, Throwable e);

    /**
     * User cancel
     *
     * @param platform
     */
    void onCancel(IPlatform platform);
}
