package com.facebook.internal;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.etsy.android.lib.models.ResponseConstants;
import com.facebook.AccessToken;
import com.facebook.FacebookDialogException;
import com.facebook.FacebookException;
import com.facebook.FacebookGraphResponseException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.FacebookRequestError;
import com.facebook.GraphResponse;
import com.facebook.common.a.g;
import com.facebook.f;
import com.facebook.share.internal.k;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import org.json.JSONArray;
import org.json.JSONObject;

public class WebDialog extends Dialog {
    private static final int API_EC_DIALOG_CANCEL = 4201;
    private static final int BACKGROUND_GRAY = -872415232;
    private static final int DEFAULT_THEME = g.com_facebook_activity_theme;
    static final boolean DISABLE_SSL_CHECK_FOR_TESTING = false;
    private static final String DISPLAY_TOUCH = "touch";
    private static final String LOG_TAG = "FacebookSDK.WebDialog";
    private static final int MAX_PADDING_SCREEN_HEIGHT = 1280;
    private static final int MAX_PADDING_SCREEN_WIDTH = 800;
    private static final double MIN_SCALE_FACTOR = 0.5d;
    private static final int NO_PADDING_SCREEN_HEIGHT = 800;
    private static final int NO_PADDING_SCREEN_WIDTH = 480;
    private static volatile int webDialogTheme;
    /* access modifiers changed from: private */
    public FrameLayout contentFrameLayout;
    /* access modifiers changed from: private */
    public ImageView crossImageView;
    /* access modifiers changed from: private */
    public String expectedRedirectUrl;
    /* access modifiers changed from: private */
    public boolean isDetached;
    /* access modifiers changed from: private */
    public boolean isPageFinished;
    private boolean listenerCalled;
    private c onCompleteListener;
    /* access modifiers changed from: private */
    public ProgressDialog spinner;
    private d uploadTask;
    /* access modifiers changed from: private */
    public String url;
    /* access modifiers changed from: private */
    public WebView webView;
    private LayoutParams windowParams;

    public static class a {
        private Context a;
        private String b;
        private String c;
        private int d;
        private c e;
        private Bundle f;
        private AccessToken g;

        public a(Context context, String str, Bundle bundle) {
            this.g = AccessToken.getCurrentAccessToken();
            if (!AccessToken.isCurrentAccessTokenActive()) {
                String a2 = z.a(context);
                if (a2 != null) {
                    this.b = a2;
                } else {
                    throw new FacebookException("Attempted to create a builder without a valid access token or a valid default Application ID.");
                }
            }
            a(context, str, bundle);
        }

        public a(Context context, String str, String str2, Bundle bundle) {
            if (str == null) {
                str = z.a(context);
            }
            aa.a(str, "applicationId");
            this.b = str;
            a(context, str2, bundle);
        }

        public a a(c cVar) {
            this.e = cVar;
            return this;
        }

        public WebDialog a() {
            if (this.g != null) {
                this.f.putString("app_id", this.g.getApplicationId());
                this.f.putString(AccessToken.ACCESS_TOKEN_KEY, this.g.getToken());
            } else {
                this.f.putString("app_id", this.b);
            }
            return WebDialog.newInstance(this.a, this.c, this.f, this.d, this.e);
        }

        public String b() {
            return this.b;
        }

        public Context c() {
            return this.a;
        }

        public int d() {
            return this.d;
        }

        public Bundle e() {
            return this.f;
        }

        public c f() {
            return this.e;
        }

        private void a(Context context, String str, Bundle bundle) {
            this.a = context;
            this.c = str;
            if (bundle != null) {
                this.f = bundle;
            } else {
                this.f = new Bundle();
            }
        }
    }

    private class b extends WebViewClient {
        private b() {
        }

        /* JADX WARNING: Removed duplicated region for block: B:30:0x0090  */
        /* JADX WARNING: Removed duplicated region for block: B:31:0x0096  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean shouldOverrideUrlLoading(android.webkit.WebView r6, java.lang.String r7) {
            /*
                r5 = this;
                java.lang.String r6 = "FacebookSDK.WebDialog"
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r1 = "Redirect URL: "
                r0.append(r1)
                r0.append(r7)
                java.lang.String r0 = r0.toString()
                com.facebook.internal.z.b(r6, r0)
                com.facebook.internal.WebDialog r6 = com.facebook.internal.WebDialog.this
                java.lang.String r6 = r6.expectedRedirectUrl
                boolean r6 = r7.startsWith(r6)
                r0 = 1
                if (r6 == 0) goto L_0x00a6
                com.facebook.internal.WebDialog r6 = com.facebook.internal.WebDialog.this
                android.os.Bundle r6 = r6.parseResponseUri(r7)
                java.lang.String r7 = "error"
                java.lang.String r7 = r6.getString(r7)
                if (r7 != 0) goto L_0x0037
                java.lang.String r7 = "error_type"
                java.lang.String r7 = r6.getString(r7)
            L_0x0037:
                java.lang.String r1 = "error_msg"
                java.lang.String r1 = r6.getString(r1)
                if (r1 != 0) goto L_0x0045
                java.lang.String r1 = "error_message"
                java.lang.String r1 = r6.getString(r1)
            L_0x0045:
                if (r1 != 0) goto L_0x004d
                java.lang.String r1 = "error_description"
                java.lang.String r1 = r6.getString(r1)
            L_0x004d:
                java.lang.String r2 = "error_code"
                java.lang.String r2 = r6.getString(r2)
                boolean r3 = com.facebook.internal.z.a(r2)
                r4 = -1
                if (r3 != 0) goto L_0x005f
                int r2 = java.lang.Integer.parseInt(r2)     // Catch:{ NumberFormatException -> 0x005f }
                goto L_0x0060
            L_0x005f:
                r2 = r4
            L_0x0060:
                boolean r3 = com.facebook.internal.z.a(r7)
                if (r3 == 0) goto L_0x0074
                boolean r3 = com.facebook.internal.z.a(r1)
                if (r3 == 0) goto L_0x0074
                if (r2 != r4) goto L_0x0074
                com.facebook.internal.WebDialog r7 = com.facebook.internal.WebDialog.this
                r7.sendSuccessToListener(r6)
                goto L_0x00a5
            L_0x0074:
                if (r7 == 0) goto L_0x008c
                java.lang.String r6 = "access_denied"
                boolean r6 = r7.equals(r6)
                if (r6 != 0) goto L_0x0086
                java.lang.String r6 = "OAuthAccessDeniedException"
                boolean r6 = r7.equals(r6)
                if (r6 == 0) goto L_0x008c
            L_0x0086:
                com.facebook.internal.WebDialog r6 = com.facebook.internal.WebDialog.this
                r6.cancel()
                goto L_0x00a5
            L_0x008c:
                r6 = 4201(0x1069, float:5.887E-42)
                if (r2 != r6) goto L_0x0096
                com.facebook.internal.WebDialog r6 = com.facebook.internal.WebDialog.this
                r6.cancel()
                goto L_0x00a5
            L_0x0096:
                com.facebook.FacebookRequestError r6 = new com.facebook.FacebookRequestError
                r6.<init>(r2, r7, r1)
                com.facebook.internal.WebDialog r7 = com.facebook.internal.WebDialog.this
                com.facebook.FacebookServiceException r2 = new com.facebook.FacebookServiceException
                r2.<init>(r6, r1)
                r7.sendErrorToListener(r2)
            L_0x00a5:
                return r0
            L_0x00a6:
                java.lang.String r6 = "fbconnect://cancel"
                boolean r6 = r7.startsWith(r6)
                if (r6 == 0) goto L_0x00b4
                com.facebook.internal.WebDialog r6 = com.facebook.internal.WebDialog.this
                r6.cancel()
                return r0
            L_0x00b4:
                java.lang.String r6 = "touch"
                boolean r6 = r7.contains(r6)
                r1 = 0
                if (r6 == 0) goto L_0x00be
                return r1
            L_0x00be:
                com.facebook.internal.WebDialog r6 = com.facebook.internal.WebDialog.this     // Catch:{ ActivityNotFoundException -> 0x00d3 }
                android.content.Context r6 = r6.getContext()     // Catch:{ ActivityNotFoundException -> 0x00d3 }
                android.content.Intent r2 = new android.content.Intent     // Catch:{ ActivityNotFoundException -> 0x00d3 }
                java.lang.String r3 = "android.intent.action.VIEW"
                android.net.Uri r7 = android.net.Uri.parse(r7)     // Catch:{ ActivityNotFoundException -> 0x00d3 }
                r2.<init>(r3, r7)     // Catch:{ ActivityNotFoundException -> 0x00d3 }
                r6.startActivity(r2)     // Catch:{ ActivityNotFoundException -> 0x00d3 }
                return r0
            L_0x00d3:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.WebDialog.b.shouldOverrideUrlLoading(android.webkit.WebView, java.lang.String):boolean");
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
            WebDialog.this.sendErrorToListener(new FacebookDialogException(str, i, str2));
        }

        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            super.onReceivedSslError(webView, sslErrorHandler, sslError);
            sslErrorHandler.cancel();
            WebDialog.this.sendErrorToListener(new FacebookDialogException(null, -11, null));
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            String str2 = WebDialog.LOG_TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("Webview loading URL: ");
            sb.append(str);
            z.b(str2, sb.toString());
            super.onPageStarted(webView, str, bitmap);
            if (!WebDialog.this.isDetached) {
                WebDialog.this.spinner.show();
            }
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            if (!WebDialog.this.isDetached) {
                WebDialog.this.spinner.dismiss();
            }
            WebDialog.this.contentFrameLayout.setBackgroundColor(0);
            WebDialog.this.webView.setVisibility(0);
            WebDialog.this.crossImageView.setVisibility(0);
            WebDialog.this.isPageFinished = true;
        }
    }

    public interface c {
        void a(Bundle bundle, FacebookException facebookException);
    }

    private class d extends AsyncTask<Void, Void, String[]> {
        private String b;
        private Bundle c;
        /* access modifiers changed from: private */
        public Exception[] d;

        d(String str, Bundle bundle) {
            this.b = str;
            this.c = bundle;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public String[] doInBackground(Void... voidArr) {
            String[] stringArray = this.c.getStringArray(ResponseConstants.MEDIA);
            final String[] strArr = new String[stringArray.length];
            this.d = new Exception[stringArray.length];
            final CountDownLatch countDownLatch = new CountDownLatch(stringArray.length);
            ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue();
            AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();
            final int i = 0;
            while (i < stringArray.length) {
                try {
                    if (isCancelled()) {
                        Iterator it = concurrentLinkedQueue.iterator();
                        while (it.hasNext()) {
                            ((AsyncTask) it.next()).cancel(true);
                        }
                        return null;
                    }
                    Uri parse = Uri.parse(stringArray[i]);
                    if (z.b(parse)) {
                        strArr[i] = parse.toString();
                        countDownLatch.countDown();
                    } else {
                        concurrentLinkedQueue.add(k.a(currentAccessToken, parse, (com.facebook.GraphRequest.b) new com.facebook.GraphRequest.b() {
                            public void a(GraphResponse graphResponse) {
                                try {
                                    FacebookRequestError a2 = graphResponse.a();
                                    if (a2 != null) {
                                        String errorMessage = a2.getErrorMessage();
                                        if (errorMessage == null) {
                                            errorMessage = "Error staging photo.";
                                        }
                                        throw new FacebookGraphResponseException(graphResponse, errorMessage);
                                    }
                                    JSONObject b2 = graphResponse.b();
                                    if (b2 == null) {
                                        throw new FacebookException("Error staging photo.");
                                    }
                                    String optString = b2.optString("uri");
                                    if (optString == null) {
                                        throw new FacebookException("Error staging photo.");
                                    }
                                    strArr[i] = optString;
                                    countDownLatch.countDown();
                                } catch (Exception e) {
                                    d.this.d[i] = e;
                                }
                            }
                        }).j());
                    }
                    i++;
                } catch (Exception unused) {
                    Iterator it2 = concurrentLinkedQueue.iterator();
                    while (it2.hasNext()) {
                        ((AsyncTask) it2.next()).cancel(true);
                    }
                    return null;
                }
            }
            countDownLatch.await();
            return strArr;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(String[] strArr) {
            Exception[] excArr;
            WebDialog.this.spinner.dismiss();
            for (Exception exc : this.d) {
                if (exc != null) {
                    WebDialog.this.sendErrorToListener(exc);
                    return;
                }
            }
            if (strArr == null) {
                WebDialog.this.sendErrorToListener(new FacebookException("Failed to stage photos for web dialog"));
                return;
            }
            List asList = Arrays.asList(strArr);
            if (asList.contains(null)) {
                WebDialog.this.sendErrorToListener(new FacebookException("Failed to stage photos for web dialog"));
                return;
            }
            z.a(this.c, ResponseConstants.MEDIA, (Object) new JSONArray(asList));
            String a2 = x.a();
            StringBuilder sb = new StringBuilder();
            sb.append(f.g());
            sb.append("/");
            sb.append("dialog/");
            sb.append(this.b);
            WebDialog.this.url = z.a(a2, sb.toString(), this.c).toString();
            WebDialog.this.setUpWebView((WebDialog.this.crossImageView.getDrawable().getIntrinsicWidth() / 2) + 1);
        }
    }

    private int getScaledSize(int i, float f, int i2, int i3) {
        int i4 = (int) (((float) i) / f);
        double d2 = MIN_SCALE_FACTOR;
        if (i4 <= i2) {
            d2 = 1.0d;
        } else if (i4 < i3) {
            d2 = MIN_SCALE_FACTOR + ((((double) (i3 - i4)) / ((double) (i3 - i2))) * MIN_SCALE_FACTOR);
        }
        return (int) (((double) i) * d2);
    }

    protected static void initDefaultTheme(Context context) {
        if (context != null) {
            try {
                ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
                if (!(applicationInfo == null || applicationInfo.metaData == null || webDialogTheme != 0)) {
                    setWebDialogTheme(applicationInfo.metaData.getInt("com.facebook.sdk.WebDialogTheme"));
                }
            } catch (NameNotFoundException unused) {
            }
        }
    }

    public static WebDialog newInstance(Context context, String str, Bundle bundle, int i, c cVar) {
        initDefaultTheme(context);
        WebDialog webDialog = new WebDialog(context, str, bundle, i, cVar);
        return webDialog;
    }

    public static int getWebDialogTheme() {
        aa.a();
        return webDialogTheme;
    }

    public static void setWebDialogTheme(int i) {
        if (i == 0) {
            i = DEFAULT_THEME;
        }
        webDialogTheme = i;
    }

    protected WebDialog(Context context, String str) {
        this(context, str, getWebDialogTheme());
    }

    private WebDialog(Context context, String str, int i) {
        if (i == 0) {
            i = getWebDialogTheme();
        }
        super(context, i);
        this.expectedRedirectUrl = "fbconnect://success";
        this.listenerCalled = false;
        this.isDetached = false;
        this.isPageFinished = false;
        this.url = str;
    }

    private WebDialog(Context context, String str, Bundle bundle, int i, c cVar) {
        if (i == 0) {
            i = getWebDialogTheme();
        }
        super(context, i);
        this.expectedRedirectUrl = "fbconnect://success";
        this.listenerCalled = false;
        this.isDetached = false;
        this.isPageFinished = false;
        if (bundle == null) {
            bundle = new Bundle();
        }
        this.expectedRedirectUrl = z.f(context) ? "fbconnect://chrome_os_success" : "fbconnect://success";
        bundle.putString("redirect_uri", this.expectedRedirectUrl);
        bundle.putString("display", DISPLAY_TOUCH);
        bundle.putString("client_id", f.j());
        bundle.putString("sdk", String.format(Locale.ROOT, "android-%s", new Object[]{f.h()}));
        this.onCompleteListener = cVar;
        if (!str.equals("share") || !bundle.containsKey(ResponseConstants.MEDIA)) {
            String a2 = x.a();
            StringBuilder sb = new StringBuilder();
            sb.append(f.g());
            sb.append("/");
            sb.append("dialog/");
            sb.append(str);
            this.url = z.a(a2, sb.toString(), bundle).toString();
            return;
        }
        this.uploadTask = new d(str, bundle);
    }

    public void setOnCompleteListener(c cVar) {
        this.onCompleteListener = cVar;
    }

    public c getOnCompleteListener() {
        return this.onCompleteListener;
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            cancel();
        }
        return super.onKeyDown(i, keyEvent);
    }

    public void dismiss() {
        if (this.webView != null) {
            this.webView.stopLoading();
        }
        if (!this.isDetached && this.spinner != null && this.spinner.isShowing()) {
            this.spinner.dismiss();
        }
        super.dismiss();
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        if (this.uploadTask == null || this.uploadTask.getStatus() != Status.PENDING) {
            resize();
            return;
        }
        this.uploadTask.execute(new Void[0]);
        this.spinner.show();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        if (this.uploadTask != null) {
            this.uploadTask.cancel(true);
            this.spinner.dismiss();
        }
        super.onStop();
    }

    public void onDetachedFromWindow() {
        this.isDetached = true;
        super.onDetachedFromWindow();
    }

    public void onAttachedToWindow() {
        this.isDetached = false;
        if (z.d(getContext()) && this.windowParams != null && this.windowParams.token == null) {
            this.windowParams.token = getOwnerActivity().getWindow().getAttributes().token;
            String str = LOG_TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("Set token on onAttachedToWindow(): ");
            sb.append(this.windowParams.token);
            z.b(str, sb.toString());
        }
        super.onAttachedToWindow();
    }

    public void onWindowAttributesChanged(LayoutParams layoutParams) {
        if (layoutParams.token == null) {
            this.windowParams = layoutParams;
        }
        super.onWindowAttributesChanged(layoutParams);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.spinner = new ProgressDialog(getContext());
        this.spinner.requestWindowFeature(1);
        this.spinner.setMessage(getContext().getString(com.facebook.common.a.f.com_facebook_loading));
        this.spinner.setCanceledOnTouchOutside(false);
        this.spinner.setOnCancelListener(new OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                WebDialog.this.cancel();
            }
        });
        requestWindowFeature(1);
        this.contentFrameLayout = new FrameLayout(getContext());
        resize();
        getWindow().setGravity(17);
        getWindow().setSoftInputMode(16);
        createCrossImage();
        if (this.url != null) {
            setUpWebView((this.crossImageView.getDrawable().getIntrinsicWidth() / 2) + 1);
        }
        this.contentFrameLayout.addView(this.crossImageView, new ViewGroup.LayoutParams(-2, -2));
        setContentView(this.contentFrameLayout);
    }

    /* access modifiers changed from: protected */
    public void setExpectedRedirectUrl(String str) {
        this.expectedRedirectUrl = str;
    }

    /* access modifiers changed from: protected */
    public Bundle parseResponseUri(String str) {
        Uri parse = Uri.parse(str);
        Bundle c2 = z.c(parse.getQuery());
        c2.putAll(z.c(parse.getFragment()));
        return c2;
    }

    /* access modifiers changed from: protected */
    public boolean isListenerCalled() {
        return this.listenerCalled;
    }

    /* access modifiers changed from: protected */
    public boolean isPageFinished() {
        return this.isPageFinished;
    }

    /* access modifiers changed from: protected */
    public WebView getWebView() {
        return this.webView;
    }

    public void resize() {
        Display defaultDisplay = ((WindowManager) getContext().getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        getWindow().setLayout(Math.min(getScaledSize(displayMetrics.widthPixels < displayMetrics.heightPixels ? displayMetrics.widthPixels : displayMetrics.heightPixels, displayMetrics.density, NO_PADDING_SCREEN_WIDTH, 800), displayMetrics.widthPixels), Math.min(getScaledSize(displayMetrics.widthPixels < displayMetrics.heightPixels ? displayMetrics.heightPixels : displayMetrics.widthPixels, displayMetrics.density, 800, MAX_PADDING_SCREEN_HEIGHT), displayMetrics.heightPixels));
    }

    /* access modifiers changed from: protected */
    public void sendSuccessToListener(Bundle bundle) {
        if (this.onCompleteListener != null && !this.listenerCalled) {
            this.listenerCalled = true;
            this.onCompleteListener.a(bundle, null);
            dismiss();
        }
    }

    /* access modifiers changed from: protected */
    public void sendErrorToListener(Throwable th) {
        FacebookException facebookException;
        if (this.onCompleteListener != null && !this.listenerCalled) {
            this.listenerCalled = true;
            if (th instanceof FacebookException) {
                facebookException = (FacebookException) th;
            } else {
                facebookException = new FacebookException(th);
            }
            this.onCompleteListener.a(null, facebookException);
            dismiss();
        }
    }

    public void cancel() {
        if (this.onCompleteListener != null && !this.listenerCalled) {
            sendErrorToListener(new FacebookOperationCanceledException());
        }
    }

    private void createCrossImage() {
        this.crossImageView = new ImageView(getContext());
        this.crossImageView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WebDialog.this.cancel();
            }
        });
        this.crossImageView.setImageDrawable(getContext().getResources().getDrawable(com.facebook.common.a.c.com_facebook_close));
        this.crossImageView.setVisibility(4);
    }

    /* access modifiers changed from: private */
    @SuppressLint({"SetJavaScriptEnabled"})
    public void setUpWebView(int i) {
        LinearLayout linearLayout = new LinearLayout(getContext());
        this.webView = new WebView(getContext()) {
            public void onWindowFocusChanged(boolean z) {
                try {
                    super.onWindowFocusChanged(z);
                } catch (NullPointerException unused) {
                }
            }
        };
        this.webView.setVerticalScrollBarEnabled(false);
        this.webView.setHorizontalScrollBarEnabled(false);
        this.webView.setWebViewClient(new b());
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.loadUrl(this.url);
        this.webView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        this.webView.setVisibility(4);
        this.webView.getSettings().setSavePassword(false);
        this.webView.getSettings().setSaveFormData(false);
        this.webView.setFocusable(true);
        this.webView.setFocusableInTouchMode(true);
        this.webView.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (!view.hasFocus()) {
                    view.requestFocus();
                }
                return false;
            }
        });
        linearLayout.setPadding(i, i, i, i);
        linearLayout.addView(this.webView);
        linearLayout.setBackgroundColor(BACKGROUND_GRAY);
        this.contentFrameLayout.addView(linearLayout);
    }
}
