package com.facebook.share.internal;

import com.facebook.internal.e;

public enum AppInviteDialogFeature implements e {
    APP_INVITES_DIALOG(20140701);
    
    private int minVersion;

    public String getAction() {
        return "com.facebook.platform.action.request.APPINVITES_DIALOG";
    }

    private AppInviteDialogFeature(int i) {
        this.minVersion = i;
    }

    public int getMinVersion() {
        return this.minVersion;
    }
}
