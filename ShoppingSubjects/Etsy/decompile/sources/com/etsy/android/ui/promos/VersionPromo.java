package com.etsy.android.ui.promos;

import android.support.v4.app.Fragment;
import java.io.Serializable;

public abstract class VersionPromo implements Serializable {
    public abstract Fragment getFragment();

    public abstract int getMinVersion();

    public abstract String getUniqueName();

    public boolean requiresUserSync() {
        return false;
    }
}
