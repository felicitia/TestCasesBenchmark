package com.klarna.checkout.browser;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.klarna.checkout.browser.BrowserActivity;
import com.klarna.checkout.browser.BrowserActivityListener;
import com.klarna.checkout.internal.e;
import com.klarna.checkout.sdk.R;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BrowserActivity extends Activity {
    private static BrowserActivity h;
    public static BrowserActivityListener tempListenerInstance = new e();
    /* access modifiers changed from: private */
    public WebView a;
    private ImageView b;
    private ImageView c;
    private ImageView d;
    private TextView e;
    private RelativeLayout f;
    /* access modifiers changed from: private */
    public ProgressBar g;

    public static void closeRemotely() {
        if (h != null) {
            h.close();
        }
    }

    public void alterNavigationBar() {
        RelativeLayout relativeLayout;
        int i;
        int color = getResources().getColor(R.color.nav_button_tint);
        int color2 = getResources().getColor(R.color.nav_button_tint_disabled);
        this.c.setColorFilter(this.a.canGoBack() ? color : color2);
        ImageView imageView = this.b;
        if (!this.a.canGoForward()) {
            color = color2;
        }
        imageView.setColorFilter(color);
        if (this.a.canGoBack() || this.a.canGoForward()) {
            relativeLayout = this.f;
            i = 0;
        } else {
            relativeLayout = this.f;
            i = 8;
        }
        relativeLayout.setVisibility(i);
    }

    public void close() {
        h = null;
        ViewGroup viewGroup = (ViewGroup) this.a.getParent();
        if (viewGroup != null) {
            viewGroup.removeAllViews();
        }
        this.a.destroy();
        tempListenerInstance.onActivityClosed();
        tempListenerInstance = new e();
        finish();
    }

    public ImageView getLockIcon() {
        return this.d;
    }

    public ProgressBar getProgressBar() {
        return this.g;
    }

    public TextView getTextTitle() {
        return this.e;
    }

    public void onBackPressed() {
        super.onBackPressed();
        close();
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"SetJavaScriptEnabled"})
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        h = this;
        requestWindowFeature(1);
        setContentView(R.layout.browser_activity);
        getWindow().setFlags(8192, 8192);
        this.a = (WebView) findViewById(R.id.webView);
        this.a.setWebChromeClient(new WebChromeClient() {
            public final void onProgressChanged(WebView webView, int i) {
                super.onProgressChanged(webView, i);
                BrowserActivity.this.g.setProgress(i);
            }
        });
        this.a.getSettings().setJavaScriptEnabled(true);
        this.a.getSettings().setAppCacheEnabled(true);
        this.a.getSettings().setAppCachePath(getApplicationContext().getCacheDir().getPath());
        this.a.addJavascriptInterface(new KcoPrintInterface(this.a), "KCO_PRINT");
        try {
            WebView webView = this.a;
            JSONObject jSONObject = new JSONObject(getIntent().getStringExtra("url_data"));
            new ArrayList();
            ArrayList arrayList = new ArrayList();
            if (jSONObject.has("blacklist")) {
                JSONArray jSONArray = jSONObject.getJSONArray("blacklist");
                for (int i = 0; i < jSONArray.length(); i++) {
                    arrayList.add(jSONArray.getString(i));
                }
            }
            boolean z = jSONObject.has("external") ? jSONObject.getBoolean("external") : false;
            String a2 = com.klarna.checkout.internal.c.e.a(jSONObject.getString("uri"));
            if (!a2.startsWith("http")) {
                com.klarna.checkout.internal.c.e.a(a2, webView, this, new e());
            } else {
                webView.loadUrl(a2);
            }
            jSONObject.getString("uri");
            this.a.setWebViewClient(new WebViewClient(this, tempListenerInstance, arrayList, z) {
                final /* synthetic */ BrowserActivity a;
                final /* synthetic */ BrowserActivityListener b;
                final /* synthetic */ List c;
                final /* synthetic */ boolean d;

                {
                    this.a = r1;
                    this.b = r2;
                    this.c = r3;
                    this.d = r4;
                }

                public final void onPageFinished(WebView webView, String str) {
                    TextView textTitle;
                    Resources resources;
                    int i;
                    super.onPageFinished(webView, str);
                    if (str != null) {
                        if (str.startsWith("https://")) {
                            this.a.getLockIcon().setVisibility(0);
                            textTitle = this.a.getTextTitle();
                            resources = this.a.getResources();
                            i = R.color.address_text_tint_green;
                        } else {
                            this.a.getLockIcon().setVisibility(8);
                            textTitle = this.a.getTextTitle();
                            resources = this.a.getResources();
                            i = R.color.address_text_tint_gray;
                        }
                        textTitle.setTextColor(resources.getColor(i));
                        this.a.getTextTitle().setText(Uri.parse(str).getHost());
                    }
                    this.a.alterNavigationBar();
                    this.a.getProgressBar().setVisibility(8);
                    this.b.onPageOpened(webView, str);
                    webView.loadUrl("javascript:(function(){ window.print = function(){ window.KCO_PRINT.print();}})();");
                }

                public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                    super.onPageStarted(webView, str, bitmap);
                    this.a.getProgressBar().setProgress(0);
                    this.a.getProgressBar().setVisibility(0);
                }

                public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
                    if (this.c.contains(str)) {
                        webView.stopLoading();
                        this.b.onLinkBlocked(str);
                        return true;
                    } else if (str.startsWith("intent:")) {
                        e.a(str, webView, this.a, this.b);
                        return true;
                    } else if (this.d) {
                        this.a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                        this.b.onExternalBrowserOpened();
                        return true;
                    } else {
                        if (str.startsWith("//")) {
                            StringBuilder sb = new StringBuilder("https:");
                            sb.append(str);
                            str = sb.toString();
                        }
                        String a2 = e.a(str);
                        if (a2.startsWith("tel:") || a2.startsWith("sms:") || a2.startsWith("smsto:") || a2.startsWith("mms:") || a2.startsWith("mmsto:")) {
                            this.a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(a2)));
                            return true;
                        } else if (a2.toLowerCase().endsWith(".pdf")) {
                            StringBuilder sb2 = new StringBuilder("javascript:(function(){ this.document.location.href = '");
                            sb2.append("https://docs.google.com/viewer?url=");
                            sb2.append(a2);
                            sb2.append("&noreload=true';})();");
                            webView.loadUrl(sb2.toString());
                            return true;
                        } else if (a2.startsWith("mailto:")) {
                            a.a((Context) this.a, a.a(a2));
                            return true;
                        } else if (a2.startsWith("http")) {
                            return false;
                        } else {
                            e.a(a2, webView, this.a, this.b);
                            return true;
                        }
                    }
                }
            });
        } catch (JSONException e2) {
            e2.getMessage();
        }
        this.d = (ImageView) findViewById(R.id.lockIcon);
        this.d.setVisibility(8);
        this.e = (TextView) findViewById(R.id.addressText);
        this.f = (RelativeLayout) findViewById(R.id.baseBar);
        this.g = (ProgressBar) findViewById(R.id.progressBar);
        this.g.setProgress(0);
        this.g.setLayoutParams((MarginLayoutParams) this.g.getLayoutParams());
        ImageView imageView = (ImageView) findViewById(R.id.closeIcon);
        imageView.setColorFilter(R.color.close_button_tint);
        imageView.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                BrowserActivity.this.close();
            }
        });
        this.c = (ImageView) findViewById(R.id.backIcon);
        this.c.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                if (BrowserActivity.this.a.canGoBack()) {
                    BrowserActivity.this.a.goBack();
                }
            }
        });
        this.b = (ImageView) findViewById(R.id.forwardIcon);
        this.b.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                if (BrowserActivity.this.a.canGoForward()) {
                    BrowserActivity.this.a.goForward();
                }
            }
        });
        alterNavigationBar();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        h = null;
    }
}
