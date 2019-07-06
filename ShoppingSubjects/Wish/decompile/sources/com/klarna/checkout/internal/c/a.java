package com.klarna.checkout.internal.c;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.MailTo;
import android.net.ParseException;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView;
import android.widget.Toast;

public final class a {
    public static Intent a(String str) {
        try {
            MailTo parse = MailTo.parse(str);
            Intent intent = new Intent("android.intent.action.SEND");
            intent.putExtra("android.intent.extra.EMAIL", new String[]{parse.getTo()});
            intent.putExtra("android.intent.extra.SUBJECT", parse.getSubject());
            intent.putExtra("android.intent.extra.TEXT", parse.getBody());
            intent.setType("message/rfc822");
            return Intent.createChooser(intent, "");
        } catch (ParseException e) {
            e.getMessage();
            return null;
        }
    }

    public static void a(Context context, Intent intent) {
        if (intent != null) {
            try {
                context.startActivity(intent);
            } catch (ActivityNotFoundException unused) {
                Toast.makeText(context, "No email clients installed.", 0).show();
            }
        }
    }

    public static void a(WebView webView, String str) {
        int i;
        try {
            i = Integer.parseInt(str);
        } catch (Exception unused) {
            i = -1;
        }
        if (i > 0 && i < 41) {
            i = -1;
        }
        LayoutParams layoutParams = webView.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new LayoutParams(-1, -1);
        }
        if (i != -1) {
            i = (int) (webView.getContext().getResources().getDisplayMetrics().density * ((float) i));
        }
        layoutParams.height = i;
        webView.setLayoutParams(layoutParams);
        webView.invalidate();
    }
}
