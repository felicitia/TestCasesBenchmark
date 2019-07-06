package com.salesforce.marketingcloud.notifications;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public final class DefaultUrlPresenter extends Activity {
    @VisibleForTesting
    WebView a;

    @NonNull
    public static Intent intentForPresenter(Context context, NotificationMessage notificationMessage) {
        return new Intent(context, DefaultUrlPresenter.class).putExtra("com.salesforce.marketingcloud.notifications.EXTRA_MESSAGE", notificationMessage);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle("Loading...");
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new LayoutParams(-1, -1));
        linearLayout.setGravity(17);
        final NotificationMessage a2 = c.a(getIntent());
        if (!(a2 == null || a2.url() == null)) {
            this.a = new WebView(this);
            LayoutParams layoutParams = new LayoutParams(-1, -1);
            layoutParams.weight = 0.9f;
            this.a.setLayoutParams(layoutParams);
            this.a.loadUrl(a2.url());
            this.a.getSettings().setJavaScriptEnabled(true);
            linearLayout.addView(this.a);
            this.a.setWebViewClient(new WebViewClient() {
                public void onPageFinished(WebView webView, String str) {
                    String title = webView.getTitle();
                    if (TextUtils.isEmpty(title)) {
                        title = "";
                        Intent intent = DefaultUrlPresenter.this.getIntent();
                        if (intent != null) {
                            Bundle extras = intent.getExtras();
                            if (extras != null) {
                                title = extras.getString(a2.alert());
                                if (title == null) {
                                    title = "";
                                }
                            }
                        }
                    }
                    DefaultUrlPresenter.this.setTitle(title);
                }
            });
        }
        setContentView(linearLayout);
    }
}
