package com.cnbleu.social;

/**
 * <b>Project:</b> project_hotclub<br>
 * <b>Create Date:</b> 7/20/16<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class SocialException extends Exception {
    public SocialException() {
    }

    public SocialException(String detailMessage) {
        super(detailMessage);
    }

    public SocialException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public SocialException(Throwable throwable) {
        super(throwable);
    }
}
