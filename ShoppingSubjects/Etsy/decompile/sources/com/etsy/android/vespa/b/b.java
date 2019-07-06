package com.etsy.android.vespa.b;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.etsy.android.lib.core.a.a;
import java.util.HashMap;

/* compiled from: PaginationForNextLink */
public class b implements a {
    private String a = null;
    private int b = Integer.MAX_VALUE;
    private boolean c = false;

    @Nullable
    public HashMap<String, String> getPaginationParams() {
        return null;
    }

    public boolean a() {
        return this.c;
    }

    public boolean canLoadContent() {
        return !this.c;
    }

    public void reset() {
        setContentExhausted(false);
        this.a = null;
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putBoolean("key_content_exhausted", this.c);
        bundle.putString("saved_api_next_link", this.a);
    }

    public void onViewStateRestored(@Nullable Bundle bundle) {
        if (bundle != null) {
            this.c = bundle.getBoolean("key_content_exhausted", false);
            this.a = bundle.getString("saved_api_next_link", null);
        }
    }

    public void a(String str) {
        this.a = str;
        if (TextUtils.isEmpty(this.a)) {
            setContentExhausted(true);
        }
    }

    public String getApiNextLink() {
        return this.a;
    }

    public void onSuccess(Object obj, int i) {
        if (obj instanceof a) {
            a(((a) obj).p());
        } else if (obj instanceof String) {
            a((String) obj);
        }
        if (!a()) {
            a(i);
        }
    }

    public int getLoadTriggerPosition() {
        return this.b;
    }

    public void recalculateLoadTriggerPosition(int i) {
        this.b = i / 2;
    }

    public void setContentExhausted(boolean z) {
        this.c = z;
    }

    private void a(int i) {
        if (this.b == Integer.MAX_VALUE) {
            recalculateLoadTriggerPosition(i);
        }
    }
}
