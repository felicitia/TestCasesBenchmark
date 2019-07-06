package com.facebook.share.internal;

import com.facebook.internal.e;

public enum OpenGraphMessageDialogFeature implements e {
    OG_MESSAGE_DIALOG(20140204);
    
    private int minVersion;

    public String getAction() {
        return "com.facebook.platform.action.request.OGMESSAGEPUBLISH_DIALOG";
    }

    private OpenGraphMessageDialogFeature(int i) {
        this.minVersion = i;
    }

    public int getMinVersion() {
        return this.minVersion;
    }
}
