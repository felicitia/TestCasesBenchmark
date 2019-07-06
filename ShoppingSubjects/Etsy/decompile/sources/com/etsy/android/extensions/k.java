package com.etsy.android.extensions;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import kotlin.jvm.internal.p;

/* compiled from: ViewGroupExtensions.kt */
public final class k {
    public static /* bridge */ /* synthetic */ View a(ViewGroup viewGroup, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        return a(viewGroup, i, z);
    }

    public static final View a(ViewGroup viewGroup, int i, boolean z) {
        p.b(viewGroup, "$receiver");
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(i, viewGroup, z);
        p.a((Object) inflate, "LayoutInflater.from(cont…tRes, this, attachToRoot)");
        return inflate;
    }
}
