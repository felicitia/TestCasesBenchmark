package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.view.Window;
import com.google.android.gms.ads.internal.ao;

@bu
public final class jo {
    private final View a;
    private Activity b;
    private boolean c;
    private boolean d;
    private boolean e;
    private OnGlobalLayoutListener f;
    private OnScrollChangedListener g;

    public jo(Activity activity, View view, OnGlobalLayoutListener onGlobalLayoutListener, OnScrollChangedListener onScrollChangedListener) {
        this.b = activity;
        this.a = view;
        this.f = onGlobalLayoutListener;
        this.g = onScrollChangedListener;
    }

    private static ViewTreeObserver b(Activity activity) {
        if (activity == null) {
            return null;
        }
        Window window = activity.getWindow();
        if (window == null) {
            return null;
        }
        View decorView = window.getDecorView();
        if (decorView == null) {
            return null;
        }
        return decorView.getViewTreeObserver();
    }

    private final void e() {
        if (!this.c) {
            if (this.f != null) {
                if (this.b != null) {
                    Activity activity = this.b;
                    OnGlobalLayoutListener onGlobalLayoutListener = this.f;
                    ViewTreeObserver b2 = b(activity);
                    if (b2 != null) {
                        b2.addOnGlobalLayoutListener(onGlobalLayoutListener);
                    }
                }
                ao.A();
                lm.a(this.a, this.f);
            }
            if (this.g != null) {
                if (this.b != null) {
                    Activity activity2 = this.b;
                    OnScrollChangedListener onScrollChangedListener = this.g;
                    ViewTreeObserver b3 = b(activity2);
                    if (b3 != null) {
                        b3.addOnScrollChangedListener(onScrollChangedListener);
                    }
                }
                ao.A();
                lm.a(this.a, this.g);
            }
            this.c = true;
        }
    }

    private final void f() {
        if (this.b != null && this.c) {
            if (this.f != null) {
                Activity activity = this.b;
                OnGlobalLayoutListener onGlobalLayoutListener = this.f;
                ViewTreeObserver b2 = b(activity);
                if (b2 != null) {
                    ao.g().a(b2, onGlobalLayoutListener);
                }
            }
            if (this.g != null) {
                Activity activity2 = this.b;
                OnScrollChangedListener onScrollChangedListener = this.g;
                ViewTreeObserver b3 = b(activity2);
                if (b3 != null) {
                    b3.removeOnScrollChangedListener(onScrollChangedListener);
                }
            }
            this.c = false;
        }
    }

    public final void a() {
        this.e = true;
        if (this.d) {
            e();
        }
    }

    public final void a(Activity activity) {
        this.b = activity;
    }

    public final void b() {
        this.e = false;
        f();
    }

    public final void c() {
        this.d = true;
        if (this.e) {
            e();
        }
    }

    public final void d() {
        this.d = false;
        f();
    }
}
