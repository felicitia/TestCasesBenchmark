package com.facebook.share.internal;

import com.facebook.internal.e;

@Deprecated
public enum LikeDialogFeature implements e {
    LIKE_DIALOG(20140701);
    
    private int minVersion;

    public String getAction() {
        return "com.facebook.platform.action.request.LIKE_DIALOG";
    }

    private LikeDialogFeature(int i) {
        this.minVersion = i;
    }

    public int getMinVersion() {
        return this.minVersion;
    }
}
