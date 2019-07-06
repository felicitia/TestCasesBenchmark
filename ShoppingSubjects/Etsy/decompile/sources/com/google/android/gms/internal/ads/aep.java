package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import android.view.View;
import java.lang.ref.WeakReference;

public final class aep implements afx {
    private WeakReference<alk> a;

    public aep(alk alk) {
        this.a = new WeakReference<>(alk);
    }

    @Nullable
    public final View a() {
        alk alk = (alk) this.a.get();
        if (alk != null) {
            return alk.l();
        }
        return null;
    }

    public final boolean b() {
        return this.a.get() == null;
    }

    public final afx c() {
        return new aer((alk) this.a.get());
    }
}
