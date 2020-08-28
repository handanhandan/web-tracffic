package com.hjd.parser.dataobject;

import eu.bitwalker.useragentutils.UserAgent;

/**
 * 存放公共字段信息
 */
public class BaseDataObject {
    private int profileId;
    private String trackerVersion;
    private String command;
    private String userId;
    private String pvId;
    private String serverTimeString;
    private String userAgent;
    private String clientIp;
    private UserAgent userAgentInfo;

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public String getTrackerVersion() {
        return trackerVersion;
    }

    public void setTrackerVersion(String trackerVersion) {
        this.trackerVersion = trackerVersion;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPvId() {
        return pvId;
    }

    public void setPvId(String pvId) {
        this.pvId = pvId;
    }

    public String getServerTimeString() {
        return serverTimeString;
    }

    public void setServerTimeString(String serverTimeString) {
        this.serverTimeString = serverTimeString;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public UserAgent getUserAgentInfo() {
        return userAgentInfo;
    }

    public void setUserAgentInfo(UserAgent userAgentInfo) {
        this.userAgentInfo = userAgentInfo;
    }
}
