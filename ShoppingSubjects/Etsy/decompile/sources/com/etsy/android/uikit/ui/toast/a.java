package com.etsy.android.uikit.ui.toast;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.etsy.android.lib.a.i;
import com.etsy.android.uikit.util.TrackingOnClickListener;

/* compiled from: PersistentToastPopup */
public abstract class a {
    protected final Context b;
    protected final PersistentToastView c;
    protected C0113a d;
    protected int e;
    protected int f;

    /* renamed from: com.etsy.android.uikit.ui.toast.a$a reason: collision with other inner class name */
    /* compiled from: PersistentToastPopup */
    public interface C0113a {
        void a();

        void b();
    }

    @LayoutRes
    public abstract int a();

    public a(Activity activity) {
        this(activity.getWindow());
    }

    public a(Window window) {
        this.e = 300;
        this.b = window.getContext();
        this.f = a();
        this.c = a(window);
        this.c.setActionClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                a.this.e();
            }
        });
        this.c.setDismissClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                a.this.g();
            }
        });
        b(false);
    }

    public void a(C0113a aVar) {
        this.d = aVar;
    }

    public void b() {
        a(true);
    }

    public void a(boolean z) {
        this.c.setVisibility(0);
        if (z) {
            this.c.animateIn((long) this.e);
        } else {
            this.c.show();
        }
    }

    public void c() {
        b(true);
    }

    public void b(boolean z) {
        if (z) {
            this.c.animateOut((long) this.e);
            return;
        }
        this.c.hide();
        this.c.setVisibility(4);
    }

    public void d() {
        c();
    }

    /* access modifiers changed from: protected */
    public void e() {
        f();
        b(true);
    }

    /* access modifiers changed from: protected */
    public void f() {
        if (this.d != null) {
            this.d.a();
        }
    }

    /* access modifiers changed from: protected */
    public void g() {
        h();
        b(true);
    }

    /* access modifiers changed from: protected */
    public void h() {
        if (this.d != null) {
            this.d.b();
        }
    }

    /* access modifiers changed from: protected */
    public PersistentToastView a(Window window) {
        ViewGroup viewGroup = (ViewGroup) window.getDecorView();
        ViewGroup viewGroup2 = (ViewGroup) viewGroup.findViewById(16908290);
        if (viewGroup2 != null) {
            viewGroup = viewGroup2;
        }
        PersistentToastView persistentToastView = (PersistentToastView) viewGroup.findViewById(i.persistent_toast_root);
        if (persistentToastView != null) {
            return persistentToastView;
        }
        PersistentToastView persistentToastView2 = (PersistentToastView) LayoutInflater.from(viewGroup.getContext()).inflate(this.f, viewGroup, false);
        persistentToastView2.setPersistentToastPopup(this);
        viewGroup.addView(persistentToastView2);
        return persistentToastView2;
    }
}
