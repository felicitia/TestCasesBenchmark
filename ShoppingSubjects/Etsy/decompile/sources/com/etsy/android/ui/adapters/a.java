package com.etsy.android.ui.adapters;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AbsListView.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.util.l;
import java.util.ArrayList;

/* compiled from: ListingRowBaseGenerator */
public abstract class a<T> extends com.etsy.android.uikit.adapter.a {
    protected int a;
    private int m;
    private int n;
    private int o;
    private final int p;
    private boolean q;
    private int r;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;
    private int x;

    /* renamed from: com.etsy.android.ui.adapters.a$a reason: collision with other inner class name */
    /* compiled from: ListingRowBaseGenerator */
    public static class C0086a<T> {
        public T a;
        public View b;
        public ImageView c;
        public ImageView d;
        public ImageView e;
        public TextView f;
        public TextView g;
        public TextView h;
    }

    /* compiled from: ListingRowBaseGenerator */
    public static class b {
        ArrayList<C0086a> a;
        public int b;
    }

    /* access modifiers changed from: protected */
    public abstract void a(int i, int i2, C0086a<T> aVar, T t2);

    public a(FragmentActivity fragmentActivity, c cVar, int i) {
        this(fragmentActivity, cVar, i, R.integer.listing_shop_card_item_columns);
    }

    public a(FragmentActivity fragmentActivity, c cVar, int i, int i2) {
        super(fragmentActivity, cVar, i);
        this.n = -1;
        this.o = -1;
        this.p = i2;
        this.r = 0;
        this.s = 0;
        this.a = c;
        f();
    }

    public int a() {
        return g().getInteger(this.p);
    }

    public void a(int i) {
        this.r = i;
    }

    public void a(boolean z) {
        this.q = z;
        f();
        if (this.s > 0) {
            l();
        }
    }

    public void b(int i) {
        this.a = i;
    }

    public void c(int i) {
        this.m = i;
    }

    public void d(int i) {
        this.d = i;
    }

    public void e(int i) {
        this.n = i;
    }

    public void f(int i) {
        this.o = i;
    }

    public void b() {
        super.f();
        if (this.s > 0) {
            l();
        }
    }

    public View a(View view) {
        return a(view, a(), this.j, this.l, this.k);
    }

    public View a(View view, int i) {
        return a(view, i, this.j, this.l, this.k);
    }

    /* access modifiers changed from: protected */
    public View a(View view, int i, int i2, int i3, int i4) {
        if (view != null && view.getTag() != null && ((b) view.getTag()).b == i) {
            return view;
        }
        b bVar = new b();
        bVar.a = new ArrayList<>(i);
        bVar.b = i;
        LinearLayout linearLayout = new LinearLayout(h());
        linearLayout.setLayoutParams(new LayoutParams(-1, -2));
        linearLayout.setPadding(this.h, this.i, this.h, this.i);
        linearLayout.setOrientation(0);
        for (int i5 = 0; i5 < i; i5++) {
            View inflate = i().inflate(this.f, null);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i2, -2);
            layoutParams.setMargins(this.i, 0, this.i, 0);
            inflate.setLayoutParams(layoutParams);
            linearLayout.addView(inflate);
            C0086a aVar = new C0086a();
            aVar.b = inflate.findViewById(R.id.listing_matte);
            aVar.c = (ImageView) inflate.findViewById(R.id.listing_image);
            aVar.c.getLayoutParams().height = i4;
            aVar.c.getLayoutParams().width = i3;
            aVar.d = (ImageView) inflate.findViewById(R.id.btn_fav);
            aVar.e = (ImageView) inflate.findViewById(R.id.btn_lists);
            aVar.f = (TextView) inflate.findViewById(R.id.listing_title);
            aVar.g = (TextView) inflate.findViewById(R.id.listing_price);
            aVar.h = (TextView) inflate.findViewById(R.id.shopname);
            bVar.a.add(aVar);
        }
        linearLayout.setTag(bVar);
        return linearLayout;
    }

    public void b(View view, int i) {
        if (i == 0 && l.d((Activity) h()) && l.e((Activity) h())) {
            view.setPadding(this.h, 2 * this.i, this.h, this.i);
        } else if (i == 0) {
            view.setPadding(this.h, this.i + this.h, this.h, this.i);
        } else {
            view.setPadding(this.h, this.i, this.h, this.i);
        }
    }

    public C0086a<T> a(Object obj, T t2, int i) {
        b bVar = (b) obj;
        if (bVar.a.size() <= i) {
            return null;
        }
        C0086a<T> aVar = (C0086a) bVar.a.get(i);
        aVar.a = t2;
        if (t2 != null) {
            a(this.l, this.k, aVar, t2);
        } else {
            aVar.b.setVisibility(8);
        }
        return aVar;
    }

    private void l() {
        int c = c();
        this.u = g().getInteger(R.integer.shop_featured_listing_columns_count);
        this.t = this.s / this.u;
        if (this.t * this.u < this.s) {
            this.t++;
        }
        this.v = ((c - (this.h * 2)) / this.u) - (this.i * 2);
        this.w = this.v - (g().getDimensionPixelSize(R.dimen.listing_card_shadow_padding) * 2);
        this.x = (int) (((float) this.w) * 0.5625f);
    }

    /* access modifiers changed from: protected */
    public int c() {
        if (this.m > 0) {
            return this.m;
        }
        if (l.c((Activity) h())) {
            return this.g.d() - this.r;
        }
        return this.g.d();
    }

    public int d() {
        if (this.n != -1) {
            return this.n;
        }
        int dimensionPixelSize = g().getDimensionPixelSize(R.dimen.padding_large) - (e() * 2);
        if (l.d((Activity) h())) {
            dimensionPixelSize += g().getDimensionPixelOffset(R.dimen.listing_card_shadow_padding);
            if (this.q && l.e((Activity) h())) {
                dimensionPixelSize += g().getDimensionPixelOffset(R.dimen.listview_extra_tablet_padding);
            }
        }
        return dimensionPixelSize;
    }

    /* access modifiers changed from: protected */
    public int e() {
        if (this.o != -1) {
            return this.o;
        }
        return super.e();
    }
}
