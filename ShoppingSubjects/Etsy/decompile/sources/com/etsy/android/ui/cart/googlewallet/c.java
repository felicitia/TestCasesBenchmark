package com.etsy.android.ui.cart.googlewallet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PointerIconCompat;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import com.etsy.android.lib.models.apiv3.cart.AndroidPayDataContract;
import com.google.android.gms.wallet.FullWallet;
import com.google.android.gms.wallet.FullWalletRequest;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.d;

/* compiled from: GoogleWalletWebViewHelper */
public class c extends GoogleWalletHelperBase {
    private a e = new a();
    /* access modifiers changed from: private */
    public FullWallet f;
    /* access modifiers changed from: private */
    public MaskedWallet g;
    /* access modifiers changed from: private */
    public AndroidPayDataContract h;
    /* access modifiers changed from: private */
    public boolean i;

    /* compiled from: GoogleWalletWebViewHelper */
    private class a {
        private WebView b;
        /* access modifiers changed from: private */
        public View c;

        private a() {
        }

        /* access modifiers changed from: 0000 */
        @SuppressLint({"AddJavascriptInterface"})
        public void a(WebView webView, View view) {
            this.b = webView;
            this.b.addJavascriptInterface(this, "AppAndroidPay");
            this.c = view;
        }

        @JavascriptInterface
        public void initInterface() {
            if (c.this.g != null && c.this.g.getPaymentDescriptions().length > 0 && c.this.g.getInstrumentInfos().length > 0) {
                final String str = c.this.g.getPaymentDescriptions()[0];
                final String instrumentType = c.this.g.getInstrumentInfos()[0].getInstrumentType();
                final String instrumentDetails = c.this.g.getInstrumentInfos()[0].getInstrumentDetails();
                this.b.post(new Runnable() {
                    public void run() {
                        a.this.a(String.format("Etsy.AndroidPay.bindAndroidPayData('%s', '%s', '%s', %s);", new Object[]{str, instrumentType, instrumentDetails, "true"}));
                        a.this.c.setVisibility(8);
                    }
                });
                c.this.i = true;
            }
        }

        @JavascriptInterface
        public void requestCardChange() {
            this.b.post(new Runnable() {
                public void run() {
                    c.this.b(c.this.h);
                }
            });
        }

        @JavascriptInterface
        public void requestFullWallet() {
            this.b.post(new Runnable() {
                public void run() {
                    c.this.e();
                }
            });
        }

        /* access modifiers changed from: 0000 */
        /* JADX WARNING: Code restructure failed: missing block: B:10:0x0087, code lost:
            if (com.etsy.android.ui.cart.googlewallet.c.b(r6.a) != null) goto L_0x0089;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0089, code lost:
            r2 = com.etsy.android.ui.cart.googlewallet.c.b(r6.a).getCartId();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0093, code lost:
            r0.a(413, r3, r2);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x009a, code lost:
            c();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x009d, code lost:
            throw r0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:7:0x0075, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
            r0 = r6.a;
            r3 = r6.b.getContext();
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0077 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a() {
            /*
                r6 = this;
                org.json.JSONObject r0 = new org.json.JSONObject
                r0.<init>()
                java.lang.String r1 = "billing_address"
                com.etsy.android.ui.cart.googlewallet.c r2 = com.etsy.android.ui.cart.googlewallet.c.this
                com.google.android.gms.wallet.FullWallet r2 = r2.f
                com.google.android.gms.identity.intents.model.UserAddress r2 = r2.getBuyerBillingAddress()
                java.util.HashMap r1 = com.etsy.android.ui.cart.googlewallet.GoogleWalletHelperBase.a(r1, r2)
                r2 = 0
                org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0077 }
                com.etsy.android.ui.cart.googlewallet.c r4 = com.etsy.android.ui.cart.googlewallet.c.this     // Catch:{ JSONException -> 0x0077 }
                com.google.android.gms.wallet.FullWallet r4 = r4.f     // Catch:{ JSONException -> 0x0077 }
                com.google.android.gms.wallet.PaymentMethodToken r4 = r4.getPaymentMethodToken()     // Catch:{ JSONException -> 0x0077 }
                java.lang.String r4 = r4.getToken()     // Catch:{ JSONException -> 0x0077 }
                r3.<init>(r4)     // Catch:{ JSONException -> 0x0077 }
                java.lang.String r4 = "cryptogram_message"
                java.lang.String r5 = "encryptedMessage"
                java.lang.String r5 = r3.getString(r5)     // Catch:{ JSONException -> 0x0077 }
                r0.put(r4, r5)     // Catch:{ JSONException -> 0x0077 }
                java.lang.String r4 = "cryptogram_epk"
                java.lang.String r5 = "ephemeralPublicKey"
                java.lang.String r5 = r3.getString(r5)     // Catch:{ JSONException -> 0x0077 }
                r0.put(r4, r5)     // Catch:{ JSONException -> 0x0077 }
                java.lang.String r4 = "cryptogram_tag"
                java.lang.String r5 = "tag"
                java.lang.String r3 = r3.getString(r5)     // Catch:{ JSONException -> 0x0077 }
                r0.put(r4, r3)     // Catch:{ JSONException -> 0x0077 }
                java.util.Set r3 = r1.keySet()     // Catch:{ JSONException -> 0x0077 }
                java.util.Iterator r3 = r3.iterator()     // Catch:{ JSONException -> 0x0077 }
            L_0x0052:
                boolean r4 = r3.hasNext()     // Catch:{ JSONException -> 0x0077 }
                if (r4 == 0) goto L_0x0066
                java.lang.Object r4 = r3.next()     // Catch:{ JSONException -> 0x0077 }
                java.lang.String r4 = (java.lang.String) r4     // Catch:{ JSONException -> 0x0077 }
                java.lang.Object r5 = r1.get(r4)     // Catch:{ JSONException -> 0x0077 }
                r0.put(r4, r5)     // Catch:{ JSONException -> 0x0077 }
                goto L_0x0052
            L_0x0066:
                java.lang.String r1 = "Etsy.AndroidPay.submitOrder(%s);"
                r3 = 1
                java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ JSONException -> 0x0077 }
                r3[r2] = r0     // Catch:{ JSONException -> 0x0077 }
                java.lang.String r0 = java.lang.String.format(r1, r3)     // Catch:{ JSONException -> 0x0077 }
                r6.a(r0)     // Catch:{ JSONException -> 0x0077 }
                goto L_0x0096
            L_0x0075:
                r0 = move-exception
                goto L_0x009a
            L_0x0077:
                com.etsy.android.ui.cart.googlewallet.c r0 = com.etsy.android.ui.cart.googlewallet.c.this     // Catch:{ all -> 0x0075 }
                r1 = 413(0x19d, float:5.79E-43)
                android.webkit.WebView r3 = r6.b     // Catch:{ all -> 0x0075 }
                android.content.Context r3 = r3.getContext()     // Catch:{ all -> 0x0075 }
                com.etsy.android.ui.cart.googlewallet.c r4 = com.etsy.android.ui.cart.googlewallet.c.this     // Catch:{ all -> 0x0075 }
                com.etsy.android.lib.models.apiv3.cart.AndroidPayDataContract r4 = r4.h     // Catch:{ all -> 0x0075 }
                if (r4 == 0) goto L_0x0093
                com.etsy.android.ui.cart.googlewallet.c r2 = com.etsy.android.ui.cart.googlewallet.c.this     // Catch:{ all -> 0x0075 }
                com.etsy.android.lib.models.apiv3.cart.AndroidPayDataContract r2 = r2.h     // Catch:{ all -> 0x0075 }
                int r2 = r2.getCartId()     // Catch:{ all -> 0x0075 }
            L_0x0093:
                r0.a(r1, r3, r2)     // Catch:{ all -> 0x0075 }
            L_0x0096:
                r6.c()
                return
            L_0x009a:
                r6.c()
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.etsy.android.ui.cart.googlewallet.c.a.a():void");
        }

        /* access modifiers changed from: 0000 */
        public void b() {
            this.c.setVisibility(0);
        }

        /* access modifiers changed from: 0000 */
        public void c() {
            this.c.setVisibility(8);
        }

        /* access modifiers changed from: private */
        public void a(String str) {
            WebView webView = this.b;
            StringBuilder sb = new StringBuilder();
            sb.append("javascript:");
            sb.append(str);
            webView.loadUrl(sb.toString());
        }
    }

    public c(@NonNull Context context, @NonNull MaskedWallet maskedWallet, @NonNull AndroidPayDataContract androidPayDataContract) {
        super(context);
        this.g = maskedWallet;
        this.h = androidPayDataContract;
    }

    public void e() {
        com.etsy.android.ui.cart.a.a(this.h);
        FullWalletRequest a2 = FullWalletRequest.newBuilder().a(this.g.getGoogleTransactionId()).a(a(this.h)).a();
        this.e.b();
        d.b.a(this.b, a2, PointerIconCompat.TYPE_HELP);
    }

    public void b(AndroidPayDataContract androidPayDataContract) {
        this.h = androidPayDataContract;
        com.etsy.android.ui.cart.a.b(androidPayDataContract);
        this.e.b();
        d.b.a(this.b, this.g.getGoogleTransactionId(), null, PointerIconCompat.TYPE_HAND);
    }

    public void b(@NonNull MaskedWallet maskedWallet) {
        this.g = maskedWallet;
    }

    public void f() {
        this.e.c();
    }

    public void c(Intent intent) {
        this.e.b();
        if (intent != null) {
            this.f = (FullWallet) intent.getParcelableExtra("com.google.android.gms.wallet.EXTRA_FULL_WALLET");
            this.e.a();
        }
    }

    public void a(WebView webView, View view) {
        this.e.a(webView, view);
    }

    public void g() {
        if (this.i) {
            this.e.initInterface();
        }
    }
}
