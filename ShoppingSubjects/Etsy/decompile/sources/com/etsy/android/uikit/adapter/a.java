package com.etsy.android.uikit.adapter;

import android.app.Activity;
import android.content.res.Resources;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import com.etsy.android.lib.a.f;
import com.etsy.android.lib.a.g;
import com.etsy.android.lib.a.j;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.util.l;

/* compiled from: BaseCardRowGenerator */
public abstract class a {
    protected static final int b = j.multi_listing_columns_count;
    protected static final int c = g.bg_card;
    private FragmentActivity a;
    protected int d;
    protected int e;
    protected int f;
    protected l g;
    protected int h;
    protected int i;
    protected int j;
    protected int k;
    protected int l;
    private Resources m;
    private LayoutInflater n;
    private c o;

    public abstract View a(View view);

    /* access modifiers changed from: protected */
    public Resources g() {
        return this.m;
    }

    /* access modifiers changed from: protected */
    public FragmentActivity h() {
        return this.a;
    }

    /* access modifiers changed from: protected */
    public LayoutInflater i() {
        return this.n;
    }

    /* access modifiers changed from: protected */
    public c j() {
        return this.o;
    }

    public a(FragmentActivity fragmentActivity, c cVar, int i2) {
        this.f = i2;
        this.a = fragmentActivity;
        this.m = fragmentActivity.getResources();
        this.n = fragmentActivity.getLayoutInflater();
        this.o = cVar;
        this.d = fragmentActivity.getResources().getDimensionPixelSize(f.listing_card_shadow_padding);
        this.g = new l(fragmentActivity);
    }

    public void a(FragmentActivity fragmentActivity) {
        this.a = fragmentActivity;
        this.m = fragmentActivity.getResources();
        this.n = fragmentActivity.getLayoutInflater();
        this.g = new l(fragmentActivity);
    }

    public void b() {
        f();
    }

    public int a() {
        return g().getInteger(b);
    }

    public int k() {
        return this.e;
    }

    /* access modifiers changed from: protected */
    public void f() {
        int c2 = c();
        this.e = this.g.f() ? 1 : 0;
        this.h = d();
        this.i = e();
        this.j = ((c2 - (this.h * 2)) / a()) - (this.i * 2);
        this.l = this.j - (this.d * 2);
        this.k = (int) (((float) this.l) * 0.75f);
    }

    /* access modifiers changed from: protected */
    public int c() {
        return this.g.d();
    }

    public int d() {
        int dimensionPixelSize = g().getDimensionPixelSize(f.padding_large) - (e() * 2);
        return l.d((Activity) h()) ? dimensionPixelSize + g().getDimensionPixelOffset(f.listing_card_shadow_padding) : dimensionPixelSize;
    }

    /* access modifiers changed from: protected */
    public int e() {
        return g().getDimensionPixelSize(f.padding_medium) - g().getDimensionPixelOffset(f.listing_card_shadow_padding);
    }
}
