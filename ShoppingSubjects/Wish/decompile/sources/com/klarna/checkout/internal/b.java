package com.klarna.checkout.internal;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public final class b extends Dialog {
    private b(Context context) {
        super(context, 16973836);
    }

    static b a(Activity activity, WebView webView) {
        b bVar = new b(activity);
        webView.setLayoutParams(new LayoutParams(-1, -2));
        bVar.setCancelable(false);
        bVar.getWindow().setLayout(-1, -1);
        bVar.getWindow().addFlags(8192);
        bVar.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        bVar.requestWindowFeature(1);
        RelativeLayout relativeLayout = new RelativeLayout(activity);
        relativeLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        relativeLayout.addView(webView);
        relativeLayout.setBackgroundColor(0);
        webView.setLayoutParams(new LayoutParams(-1, -1));
        webView.setBackgroundColor(0);
        bVar.addContentView(relativeLayout, new ViewGroup.LayoutParams(-1, -1));
        return bVar;
    }
}
