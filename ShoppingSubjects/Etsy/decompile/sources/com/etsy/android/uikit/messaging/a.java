package com.etsy.android.uikit.messaging;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import com.etsy.android.lib.a.k;

/* compiled from: EasyOptOutPopup */
public class a extends com.etsy.android.uikit.ui.toast.a {
    protected CharSequence a;
    private CharSequence g;

    public a(Activity activity) {
        super(activity);
    }

    @LayoutRes
    public int a() {
        return k.popup_easy_opt_out;
    }

    public void a(CharSequence charSequence) {
        this.a = charSequence;
    }

    public void b(CharSequence charSequence) {
        this.g = charSequence;
    }

    public void a(boolean z) {
        ((EasyOptOutToastView) this.c).setTextContent(this.a, this.g);
        super.a(z);
    }
}
