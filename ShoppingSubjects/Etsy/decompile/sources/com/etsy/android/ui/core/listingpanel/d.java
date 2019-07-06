package com.etsy.android.ui.core.listingpanel;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.support.annotation.CallSuper;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.config.h;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.logger.w;
import com.etsy.android.lib.models.Listing;
import com.etsy.android.lib.util.l;
import com.etsy.android.uikit.BaseActivity;
import com.etsy.android.uikit.util.AnimationUtil;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.util.j;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;

/* compiled from: ListingPanelBase */
public abstract class d {
    private static final String j = f.a(d.class);
    protected BaseActivity a;
    protected Listing b;
    protected View c;
    protected LinearLayout d;
    protected View e;
    protected View f;
    protected ImageView g;
    protected boolean h = true;
    protected boolean i = false;
    private TextView k;
    private int l;
    private int m;
    private int n;
    private int o;
    /* access modifiers changed from: private */
    public int p;
    private int q;
    @NonNull
    private w r;
    private a s;
    private OnGlobalLayoutListener t;

    /* compiled from: ListingPanelBase */
    public interface a {
        void a(d dVar);
    }

    /* access modifiers changed from: protected */
    public abstract void c();

    /* access modifiers changed from: protected */
    public void j() {
    }

    /* access modifiers changed from: protected */
    public void k() {
    }

    /* access modifiers changed from: protected */
    public void l() {
    }

    /* access modifiers changed from: protected */
    public void m() {
    }

    protected d(Listing listing, BaseActivity baseActivity, @NonNull w wVar) {
        this.b = listing;
        this.a = baseActivity;
        this.r = wVar;
        this.q = new l(this.a).e();
    }

    /* access modifiers changed from: protected */
    public void a(@IdRes int i2, @IdRes int i3, @IdRes int i4, @IdRes int i5) {
        this.l = i2;
        this.m = i3;
        this.o = i4;
        this.n = i5;
    }

    public void a(Listing listing) {
        this.b = listing;
    }

    public void a(BaseActivity baseActivity) {
        this.a = baseActivity;
    }

    public void a(int i2) {
        this.p = i2;
    }

    public boolean a() {
        return this.i;
    }

    public void a(a aVar) {
        this.s = aVar;
    }

    public void a(View view, boolean z) {
        this.c = view;
        this.d = (LinearLayout) this.c.findViewById(this.l);
        this.e = this.c.findViewById(this.m);
        this.g = (ImageView) this.c.findViewById(this.o);
        this.f = this.c.findViewById(this.n);
        this.k = (TextView) this.c.findViewById(R.id.txt_hidden_content);
        c();
        if (l.c((Activity) this.a)) {
            if (!z) {
                this.d.setVisibility(8);
            }
            a(this.e);
        } else {
            a(!z);
            a(this.e);
            a((View) this.g);
        }
        if (z && this.h) {
            if (!l.c((Activity) this.a)) {
                this.g.setRotation(180.0f);
            }
            t();
        } else if (this.h && !l.c((Activity) this.a)) {
            this.g.setRotation(0.0f);
        }
        this.i = true;
        s();
    }

    @CallSuper
    public void b() {
        n().a((Object) this);
        w();
        this.i = false;
    }

    public void d() {
        if (i()) {
            e();
        } else {
            f();
        }
    }

    public void e() {
        if (i()) {
            l();
            if (l.c((Activity) this.a)) {
                this.d.setVisibility(8);
                this.e.setSelected(false);
                m();
            } else {
                u();
            }
        }
        s();
    }

    public void f() {
        b(true);
    }

    public void g() {
        b(false);
    }

    private void b(boolean z) {
        if (!i()) {
            j();
            if (!z || l.c((Activity) this.a)) {
                t();
                k();
            } else {
                u();
            }
        }
        s();
    }

    /* access modifiers changed from: private */
    public void s() {
        if (this.g != null) {
            if (this.g.getRotation() == 0.0f) {
                this.g.setContentDescription(this.a.getResources().getString(R.string.expand));
                if (this.f instanceof TextView) {
                    View view = this.f;
                    StringBuilder sb = new StringBuilder();
                    sb.append(((TextView) this.f).getText());
                    sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                    sb.append(this.a.getResources().getString(R.string.expand));
                    view.setContentDescription(sb.toString());
                } else if ((this.f instanceof RelativeLayout) && this.k != null) {
                    this.k.setContentDescription(this.a.getResources().getString(R.string.expand));
                }
            } else {
                this.g.setContentDescription(this.a.getResources().getString(R.string.collapse));
                if (this.f instanceof TextView) {
                    View view2 = this.f;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(((TextView) this.f).getText());
                    sb2.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                    sb2.append(this.a.getResources().getString(R.string.collapse));
                    view2.setContentDescription(sb2.toString());
                } else if ((this.f instanceof RelativeLayout) && this.k != null) {
                    this.k.setContentDescription(this.a.getResources().getString(R.string.collapse));
                }
            }
        }
    }

    public void h() {
        this.h = false;
        this.d.setVisibility(8);
        this.e.setVisibility(8);
    }

    public boolean i() {
        return this.d.getVisibility() == 0;
    }

    private void t() {
        this.d.setVisibility(0);
        if (l.c((Activity) this.a)) {
            this.e.setSelected(true);
        }
    }

    private void u() {
        if (i()) {
            if (this.d.getHeight() > this.q) {
                f.c(j, "Not animating panel since the panel is larger than the window. In some cases this will cause a crash on texture size");
                this.d.setVisibility(8);
                m();
            } else {
                AnimationUtil.a((View) this.d, (AnimatorListenerAdapter) new AnimatorListenerAdapter() {
                    public void onAnimationCancel(Animator animator) {
                    }

                    public void onAnimationEnd(Animator animator) {
                        d.this.m();
                        d.this.s();
                    }
                });
            }
            this.g.setRotation(0.0f);
            return;
        }
        AnimationUtil.c(this.d, this.p, new AnimatorListenerAdapter() {
            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
                d.this.k();
                d.this.s();
            }
        });
        t();
        this.g.setRotation(180.0f);
    }

    /* access modifiers changed from: private */
    public void v() {
        if (this.s != null) {
            this.s.a(this);
        }
    }

    /* access modifiers changed from: protected */
    public void a(final boolean z) {
        w();
        this.t = new OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                d.this.w();
                d.this.p = d.this.d.getMeasuredHeight();
                if (z) {
                    d.this.g.setRotation(0.0f);
                    d.this.d.setVisibility(8);
                }
            }
        };
        j.a(this.d.getViewTreeObserver(), this.t);
    }

    /* access modifiers changed from: protected */
    public void a(View view) {
        if (view == null) {
            return;
        }
        if (l.c((Activity) this.a)) {
            view.setOnClickListener(new TrackingOnClickListener(this.b) {
                public void onViewClick(View view) {
                    d.this.f();
                    d.this.v();
                }
            });
            return;
        }
        view.setOnClickListener(new TrackingOnClickListener(this.b) {
            public void onViewClick(View view) {
                d.this.d();
            }
        });
    }

    /* access modifiers changed from: protected */
    public com.etsy.android.lib.core.j n() {
        return v.a().j();
    }

    /* access modifiers changed from: protected */
    @NonNull
    public w o() {
        return this.r;
    }

    /* access modifiers changed from: protected */
    public h p() {
        return this.r.c();
    }

    /* access modifiers changed from: private */
    public void w() {
        if (this.d != null && this.t != null) {
            j.b(this.d.getViewTreeObserver(), this.t);
            this.t = null;
        }
    }

    public LinearLayout q() {
        return this.d;
    }

    public boolean r() {
        return this.h;
    }
}
