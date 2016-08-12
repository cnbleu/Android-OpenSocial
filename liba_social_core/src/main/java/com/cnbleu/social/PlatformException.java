package com.cnbleu.social;

/**
 * <b>Project:</b> project_hotclub<br>
 * <b>Create Date:</b> 7/21/16<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class PlatformException extends SocialException {

    public PlatformException() {
    }

    public PlatformException(String detailMessage) {
        super(detailMessage);
    }

    public PlatformException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public PlatformException(Throwable throwable) {
        super(throwable);
    }
}
