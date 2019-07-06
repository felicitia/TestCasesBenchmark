package com.etsy.android.vespa.b;

import android.os.Bundle;
import android.support.annotation.Nullable;
import java.util.HashMap;

/* compiled from: PaginationNone */
public class d implements a {
    private boolean a = false;

    @Nullable
    public String getApiNextLink() {
        return null;
    }

    public int getLoadTriggerPosition() {
        return Integer.MAX_VALUE;
    }

    @Nullable
    public HashMap<String, String> getPaginationParams() {
        return null;
    }

    public void recalculateLoadTriggerPosition(int i) {
    }

    public void reset() {
        this.a = false;
    }

    public boolean canLoadContent() {
        return !this.a;
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putBoolean("key_content_exhausted", this.a);
    }

    public void onViewStateRestored(@Nullable Bundle bundle) {
        if (bundle != null) {
            this.a = bundle.getBoolean("key_content_exhausted", false);
        }
    }

    public void setContentExhausted(boolean z) {
        this.a = z;
    }

    public void onSuccess(Object obj, int i) {
        this.a = true;
    }
}
