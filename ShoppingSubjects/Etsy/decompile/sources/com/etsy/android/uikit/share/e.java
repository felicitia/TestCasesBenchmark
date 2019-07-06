package com.etsy.android.uikit.share;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.logger.w;
import com.etsy.android.uikit.util.TrackingOnClickListener;

/* compiled from: SocialSharePopup */
public class e {
    protected final Context a;
    protected final SocialShareView b;
    protected a c;
    protected CharSequence d;
    protected int e;
    protected int f;

    /* compiled from: SocialSharePopup */
    public interface a {
        void a();
    }

    public e(Activity activity) {
        this(activity.getWindow());
    }

    public e(Window window) {
        this(window, -1);
    }

    public e(Window window, @LayoutRes int i) {
        this.e = 300;
        if (i == -1) {
            i = k.popup_social_share;
        }
        this.a = window.getContext();
        this.f = i;
        this.b = a(window);
        this.b.setOnShareListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                e.this.d();
            }
        });
        b(false);
    }

    public void a(int i) {
        this.d = this.a.getString(i);
    }

    public void a(a aVar) {
        this.c = aVar;
    }

    public void a() {
        a(true);
    }

    public void a(boolean z) {
        this.b.setMessage(this.d);
        this.b.setVisibility(0);
        if (z) {
            this.b.animateIn((long) this.e);
        } else {
            this.b.show();
        }
        w a2 = com.etsy.android.uikit.util.a.a(this.b);
        if (a2 != null) {
            a2.a("toast_shown", null);
        }
    }

    public void b() {
        b(true);
    }

    public void b(boolean z) {
        if (z) {
            this.b.animateOut((long) this.e);
            return;
        }
        this.b.hide();
        this.b.setVisibility(4);
        this.d = null;
    }

    public void c() {
        b();
        w a2 = com.etsy.android.uikit.util.a.a(this.b);
        if (a2 != null) {
            a2.a("toast_ignored", null);
        }
    }

    /* access modifiers changed from: protected */
    public void d() {
        e();
        b(true);
    }

    /* access modifiers changed from: protected */
    public void e() {
        if (this.c != null) {
            this.c.a();
        }
    }

    /* access modifiers changed from: protected */
    public SocialShareView a(Window window) {
        ViewGroup viewGroup = (ViewGroup) window.getDecorView();
        ViewGroup viewGroup2 = (ViewGroup) viewGroup.findViewById(16908290);
        if (viewGroup2 != null) {
            viewGroup = viewGroup2;
        }
        SocialShareView socialShareView = (SocialShareView) viewGroup.findViewById(i.toast_layout_root);
        if (socialShareView == null) {
            socialShareView = (SocialShareView) LayoutInflater.from(viewGroup.getContext()).inflate(this.f, viewGroup, false);
            viewGroup.addView(socialShareView);
        }
        socialShareView.setSocialShareToast(this);
        return socialShareView;
    }
}
