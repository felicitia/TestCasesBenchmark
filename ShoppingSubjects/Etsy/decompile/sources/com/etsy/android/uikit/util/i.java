package com.etsy.android.uikit.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: UserInputDialogHelper */
public class i {
    private final Context a;
    private final ProgressDialog b;
    private final ArrayList<a> c = new ArrayList<>();

    /* compiled from: UserInputDialogHelper */
    public interface a {
        void a(boolean z);
    }

    public i(Context context) {
        this.a = context;
        this.b = new ProgressDialog(this.a);
        this.b.setProgressStyle(0);
        this.b.setCancelable(false);
    }

    public void a(int i) {
        if (!this.b.isShowing()) {
            this.b.setMessage(this.a.getString(i));
            if (this.b.isShowing()) {
                this.b.dismiss();
            }
            this.b.show();
        }
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            ((a) it.next()).a(true);
        }
    }

    public void a() {
        if (this.b.isShowing()) {
            this.b.dismiss();
        }
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            ((a) it.next()).a(false);
        }
    }

    public void a(TextView textView) {
        textView.setVisibility(8);
    }

    public void a(TextView textView, int i) {
        a(textView, this.a.getString(i));
    }

    public void a(TextView textView, String str) {
        textView.setVisibility(0);
        textView.setText(str);
    }
}
