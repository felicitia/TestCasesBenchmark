package com.etsy.android.vespa;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

/* compiled from: BaseViewHolderClickHandler */
public abstract class b<Element> {
    private final FragmentActivity a;
    @NonNull
    private final com.etsy.android.lib.logger.b b;

    public abstract void a(Element element);

    public b(@NonNull FragmentActivity fragmentActivity, @NonNull com.etsy.android.lib.logger.b bVar) {
        this.a = fragmentActivity;
        this.b = bVar;
    }

    @NonNull
    public String b() {
        return this.b.b();
    }

    @NonNull
    public com.etsy.android.lib.logger.b c() {
        return this.b;
    }

    @NonNull
    public FragmentActivity d() {
        return this.a;
    }
}
