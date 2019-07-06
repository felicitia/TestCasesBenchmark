package com.a;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsService;
import android.support.customtabs.CustomTabsServiceConnection;
import android.support.customtabs.CustomTabsSession;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.util.ArrayList;
import java.util.List;

/* compiled from: WebHelper */
class g {
    /* access modifiers changed from: private */
    public static CustomTabsServiceConnection a;
    /* access modifiers changed from: private */
    public static WebView b;

    /* compiled from: WebHelper */
    interface a {
        void a();

        void b();
    }

    static void a(Context context, Uri uri, final a aVar) {
        if (a(context) != null) {
            b(context, uri, aVar);
            return;
        }
        b = new WebView(context);
        b.setWebViewClient(new WebViewClient() {
            public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                super.onPageStarted(webView, str, bitmap);
                aVar.a();
            }

            public final void onPageFinished(WebView webView, String str) {
                super.onPageFinished(webView, str);
                g.b = null;
                aVar.b();
            }
        });
        b.loadUrl(uri.toString());
    }

    private static String a(Context context) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.example.com"));
        ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 0);
        String str = resolveActivity != null ? resolveActivity.activityInfo.packageName : null;
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 0);
        ArrayList arrayList = new ArrayList();
        for (ResolveInfo resolveInfo : queryIntentActivities) {
            Intent intent2 = new Intent();
            intent2.setAction(CustomTabsService.ACTION_CUSTOM_TABS_CONNECTION);
            intent2.setPackage(resolveInfo.activityInfo.packageName);
            if (packageManager.resolveService(intent2, 0) != null) {
                arrayList.add(resolveInfo.activityInfo.packageName);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        if (arrayList.size() == 1) {
            return (String) arrayList.get(0);
        }
        if (!TextUtils.isEmpty(str) && !a(context, intent) && arrayList.contains(str)) {
            return str;
        }
        if (arrayList.contains("com.android.chrome")) {
            return "com.android.chrome";
        }
        if (arrayList.contains("com.chrome.beta")) {
            return "com.chrome.beta";
        }
        if (arrayList.contains("com.chrome.dev")) {
            return "com.chrome.dev";
        }
        if (arrayList.contains("com.google.android.apps.chrome")) {
            return "com.google.android.apps.chrome";
        }
        return null;
    }

    private static boolean a(Context context, Intent intent) {
        try {
            List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 64);
            if (queryIntentActivities != null) {
                if (queryIntentActivities.size() != 0) {
                    for (ResolveInfo resolveInfo : queryIntentActivities) {
                        IntentFilter intentFilter = resolveInfo.filter;
                        if (intentFilter != null) {
                            if (intentFilter.countDataAuthorities() == 0) {
                                continue;
                            } else if (intentFilter.countDataPaths() != 0) {
                                if (resolveInfo.activityInfo != null) {
                                    return true;
                                }
                            }
                        }
                    }
                    return false;
                }
            }
            return false;
        } catch (RuntimeException e) {
            Log.d("BitlySDK", "Bitly SDK Failed to determine specialized handler intents", e);
        }
    }

    private static void b(Context context, final Uri uri, final a aVar) {
        String a2 = a(context);
        if (a2 != null) {
            a = new CustomTabsServiceConnection() {
                private CustomTabsClient c;
                private CustomTabsSession d;

                public void onCustomTabsServiceConnected(ComponentName componentName, CustomTabsClient customTabsClient) {
                    this.c = customTabsClient;
                    this.c.warmup(0);
                    this.d = this.c.newSession(null);
                    if (this.d != null) {
                        this.d.mayLaunchUrl(uri, null, null);
                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                aVar.b();
                                AnonymousClass2.this.onServiceDisconnected(null);
                            }
                        }, 1000);
                    }
                }

                public void onServiceDisconnected(ComponentName componentName) {
                    this.c = null;
                    this.d = null;
                    g.a = null;
                }
            };
            CustomTabsClient.bindCustomTabsService(context, a2, a);
        }
    }
}
