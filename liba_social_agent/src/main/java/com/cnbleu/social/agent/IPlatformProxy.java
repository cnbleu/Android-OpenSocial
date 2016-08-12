package com.cnbleu.social.agent;

import com.cnbleu.social.Configuration;
import com.cnbleu.social.platform.IPlatform;

import java.util.List;

/**
 * <b>Project:</b> project_hotclub<br>
 * <b>Create Date:</b> 7/29/16<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public interface IPlatformProxy {

    List<Configuration> onCreateConfiguration();

    IPlatform onCreatePlatform(Configuration configuration);

}