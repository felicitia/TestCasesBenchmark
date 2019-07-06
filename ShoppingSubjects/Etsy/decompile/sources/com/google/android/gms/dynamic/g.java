package com.google.android.gms.dynamic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;

final class g implements C0137a {
    private final /* synthetic */ FrameLayout a;
    private final /* synthetic */ LayoutInflater b;
    private final /* synthetic */ ViewGroup c;
    private final /* synthetic */ Bundle d;
    private final /* synthetic */ a e;

    g(a aVar, FrameLayout frameLayout, LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.e = aVar;
        this.a = frameLayout;
        this.b = layoutInflater;
        this.c = viewGroup;
        this.d = bundle;
    }

    public final int a() {
        return 2;
    }

    public final void a(b bVar) {
        this.a.removeAllViews();
        this.a.addView(this.e.a.a(this.b, this.c, this.d));
    }
}
