package com.cnbleu.social;

/**
 * <b>Project:</b> project_hotclub<br>
 * <b>Create Date:</b> 7/20/16<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class ParamsException extends SocialException {

    public ParamsException() {
        this("ShareParams contains error, please look at the logcat");
    }

    public ParamsException(String detailMessage) {
        super(detailMessage);
    }
}
