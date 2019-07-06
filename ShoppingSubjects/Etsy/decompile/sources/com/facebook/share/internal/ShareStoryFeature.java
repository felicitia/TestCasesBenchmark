package com.facebook.share.internal;

import com.facebook.internal.e;

public enum ShareStoryFeature implements e {
    SHARE_STORY_ASSET(20170417);
    
    private int minVersion;

    public String getAction() {
        return "com.facebook.platform.action.request.SHARE_STORY";
    }

    private ShareStoryFeature(int i) {
        this.minVersion = i;
    }

    public int getMinVersion() {
        return this.minVersion;
    }
}
