package com.google.android.gms.internal.ads;

import android.view.View;
import java.lang.ref.WeakReference;

public final class aet implements afx {
    private final WeakReference<View> a;
    private final WeakReference<ga> b;

    public aet(View view, ga gaVar) {
        this.a = new WeakReference<>(view);
        this.b = new WeakReference<>(gaVar);
    }

    public final View a() {
        return (View) this.a.get();
    }

    public final boolean b() {
        return this.a.get() == null || this.b.get() == null;
    }

    public final afx c() {
        return new aes((View) this.a.get(), (ga) this.b.get());
    }
}
