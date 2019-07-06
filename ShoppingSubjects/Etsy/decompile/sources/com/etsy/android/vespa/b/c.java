package com.etsy.android.vespa.b;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.etsy.android.lib.core.k;
import com.etsy.android.lib.logger.f;
import java.util.HashMap;

/* compiled from: PaginationForOffset */
public class c implements a {
    private int a = 0;
    private int b = 24;
    private int c = Integer.MAX_VALUE;
    private boolean d = false;

    @Nullable
    public String getApiNextLink() {
        return null;
    }

    public boolean canLoadContent() {
        return !this.d;
    }

    public void reset() {
        setContentExhausted(false);
        this.a = 0;
    }

    public void onViewStateRestored(@Nullable Bundle bundle) {
        if (bundle != null) {
            this.d = bundle.getBoolean("key_content_exhausted", false);
            this.a = bundle.getInt("key_offset", 0);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putBoolean("key_content_exhausted", this.d);
        bundle.putInt("key_offset", this.a);
    }

    public void a(int i, int i2) {
        this.a += i;
        if (this.a >= i2) {
            setContentExhausted(true);
        }
    }

    @Nullable
    public HashMap<String, String> getPaginationParams() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("offset", Integer.toString(this.a));
        hashMap.put("limit", Integer.toString(this.b));
        return hashMap;
    }

    public int a() {
        return this.a;
    }

    public void onSuccess(@Nullable Object obj, int i) {
        if (obj == null) {
            this.d = true;
            return;
        }
        if (obj instanceof k) {
            a(i, ((k) obj).f());
        } else if (obj instanceof Integer) {
            a(i, ((Integer) obj).intValue());
        } else {
            f.f("Unknown pagination indicator");
        }
        if (!this.d) {
            a(i);
        }
    }

    public int getLoadTriggerPosition() {
        return this.c;
    }

    public void recalculateLoadTriggerPosition(int i) {
        this.c = i / 2;
    }

    public void setContentExhausted(boolean z) {
        this.d = z;
    }

    private void a(int i) {
        if (this.c == Integer.MAX_VALUE) {
            recalculateLoadTriggerPosition(i);
        }
    }
}
