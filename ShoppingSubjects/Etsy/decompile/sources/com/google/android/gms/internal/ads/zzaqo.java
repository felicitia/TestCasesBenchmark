package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.os.Message;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.View;
import android.view.WindowManager.BadTokenException;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions.Callback;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebStorage.QuotaUpdater;
import android.webkit.WebView;
import android.webkit.WebView.WebViewTransport;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.etsy.android.lib.convos.Draft;
import com.etsy.android.lib.models.ResponseConstants;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.ads.internal.bh;
import com.google.android.gms.ads.internal.overlay.zzd;
import com.google.android.gms.common.util.PlatformVersion;

@bu
@TargetApi(11)
public final class zzaqo extends WebChromeClient {
    private final nn zzbnd;

    public zzaqo(nn nnVar) {
        this.zzbnd = nnVar;
    }

    private static Context zza(WebView webView) {
        if (!(webView instanceof nn)) {
            return webView.getContext();
        }
        nn nnVar = (nn) webView;
        Activity zzto = nnVar.zzto();
        return zzto != null ? zzto : nnVar.getContext();
    }

    private final boolean zza(Context context, String str, String str2, String str3, String str4, JsResult jsResult, JsPromptResult jsPromptResult, boolean z) {
        AlertDialog create;
        try {
            if (!(this.zzbnd == null || this.zzbnd.zzuf() == null || this.zzbnd.zzuf().zzut() == null)) {
                bh zzut = this.zzbnd.zzuf().zzut();
                if (zzut != null && !zzut.b()) {
                    StringBuilder sb = new StringBuilder(11 + String.valueOf(str).length() + String.valueOf(str3).length());
                    sb.append("window.");
                    sb.append(str);
                    sb.append("('");
                    sb.append(str3);
                    sb.append("')");
                    zzut.a(sb.toString());
                    return false;
                }
            }
            Builder builder = new Builder(context);
            builder.setTitle(str2);
            if (z) {
                LinearLayout linearLayout = new LinearLayout(context);
                linearLayout.setOrientation(1);
                TextView textView = new TextView(context);
                textView.setText(str3);
                EditText editText = new EditText(context);
                editText.setText(str4);
                linearLayout.addView(textView);
                linearLayout.addView(editText);
                create = builder.setView(linearLayout).setPositiveButton(17039370, new nl(jsPromptResult, editText)).setNegativeButton(17039360, new nk(jsPromptResult)).setOnCancelListener(new nj(jsPromptResult)).create();
            } else {
                create = builder.setMessage(str3).setPositiveButton(17039370, new ni(jsResult)).setNegativeButton(17039360, new nh(jsResult)).setOnCancelListener(new ng(jsResult)).create();
            }
            create.show();
            return true;
        } catch (BadTokenException e) {
            gv.c("Fail to display Dialog.", e);
            return true;
        }
    }

    public final void onCloseWindow(WebView webView) {
        String str;
        if (!(webView instanceof nn)) {
            str = "Tried to close a WebView that wasn't an AdWebView.";
        } else {
            zzd zzub = ((nn) webView).zzub();
            if (zzub == null) {
                str = "Tried to close an AdWebView not associated with an overlay.";
            } else {
                zzub.close();
                return;
            }
        }
        gv.e(str);
    }

    public final boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        String message = consoleMessage.message();
        String sourceId = consoleMessage.sourceId();
        int lineNumber = consoleMessage.lineNumber();
        StringBuilder sb = new StringBuilder(19 + String.valueOf(message).length() + String.valueOf(sourceId).length());
        sb.append("JS: ");
        sb.append(message);
        sb.append(" (");
        sb.append(sourceId);
        sb.append(Draft.IMAGE_DELIMITER);
        sb.append(lineNumber);
        sb.append(")");
        String sb2 = sb.toString();
        if (sb2.contains("Application Cache")) {
            return super.onConsoleMessage(consoleMessage);
        }
        switch (nm.a[consoleMessage.messageLevel().ordinal()]) {
            case 1:
                gv.c(sb2);
                break;
            case 2:
                gv.e(sb2);
                break;
            case 5:
                gv.b(sb2);
                break;
            default:
                gv.d(sb2);
                break;
        }
        return super.onConsoleMessage(consoleMessage);
    }

    public final boolean onCreateWindow(WebView webView, boolean z, boolean z2, Message message) {
        WebViewTransport webViewTransport = (WebViewTransport) message.obj;
        WebView webView2 = new WebView(webView.getContext());
        if (this.zzbnd.zzug() != null) {
            webView2.setWebViewClient(this.zzbnd.zzug());
        }
        webViewTransport.setWebView(webView2);
        message.sendToTarget();
        return true;
    }

    public final void onExceededDatabaseQuota(String str, String str2, long j, long j2, long j3, QuotaUpdater quotaUpdater) {
        long j4 = 5242880 - j3;
        long j5 = 0;
        if (j4 <= 0) {
            quotaUpdater.updateQuota(j);
            return;
        }
        if (j != 0) {
            if (j2 == 0) {
                j = Math.min(j + Math.min(PlaybackStateCompat.ACTION_PREPARE_FROM_URI, j4), PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED);
            } else if (j2 <= Math.min(PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED - j, j4)) {
                j5 = j + j2;
            }
            j5 = j;
        } else if (j2 <= j4 && j2 <= PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) {
            j5 = j2;
        }
        quotaUpdater.updateQuota(j5);
    }

    public final void onGeolocationPermissionsShowPrompt(String str, Callback callback) {
        boolean z;
        if (callback != null) {
            ao.e();
            if (!hd.a(this.zzbnd.getContext(), "android.permission.ACCESS_FINE_LOCATION")) {
                ao.e();
                if (!hd.a(this.zzbnd.getContext(), "android.permission.ACCESS_COARSE_LOCATION")) {
                    z = false;
                    callback.invoke(str, z, true);
                }
            }
            z = true;
            callback.invoke(str, z, true);
        }
    }

    public final void onHideCustomView() {
        zzd zzub = this.zzbnd.zzub();
        if (zzub == null) {
            gv.e("Could not get ad overlay when hiding custom view.");
        } else {
            zzub.zznh();
        }
    }

    public final boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
        return zza(zza(webView), "alert", str, str2, null, jsResult, null, false);
    }

    public final boolean onJsBeforeUnload(WebView webView, String str, String str2, JsResult jsResult) {
        return zza(zza(webView), "onBeforeUnload", str, str2, null, jsResult, null, false);
    }

    public final boolean onJsConfirm(WebView webView, String str, String str2, JsResult jsResult) {
        return zza(zza(webView), "confirm", str, str2, null, jsResult, null, false);
    }

    public final boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        return zza(zza(webView), ResponseConstants.PROMPT, str, str2, str3, null, jsPromptResult, true);
    }

    @TargetApi(21)
    public final void onPermissionRequest(PermissionRequest permissionRequest) {
        if (PlatformVersion.isAtLeastLollipop()) {
            if (!((Boolean) ajh.f().a(akl.aC)).booleanValue()) {
                if (this.zzbnd == null || this.zzbnd.zzuf() == null || this.zzbnd.zzuf().zzvf() == null) {
                    super.onPermissionRequest(permissionRequest);
                    return;
                }
                String[] a = this.zzbnd.zzuf().zzvf().a(permissionRequest.getResources());
                if (a.length > 0) {
                    permissionRequest.grant(a);
                    return;
                } else {
                    permissionRequest.deny();
                    return;
                }
            }
        }
        super.onPermissionRequest(permissionRequest);
    }

    public final void onReachedMaxAppCacheSize(long j, long j2, QuotaUpdater quotaUpdater) {
        long j3 = j + PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
        if (5242880 - j2 < j3) {
            quotaUpdater.updateQuota(0);
        } else {
            quotaUpdater.updateQuota(j3);
        }
    }

    @Deprecated
    public final void onShowCustomView(View view, int i, CustomViewCallback customViewCallback) {
        zzd zzub = this.zzbnd.zzub();
        if (zzub == null) {
            gv.e("Could not get ad overlay when showing custom view.");
            customViewCallback.onCustomViewHidden();
            return;
        }
        zzub.zza(view, customViewCallback);
        zzub.setRequestedOrientation(i);
    }

    public final void onShowCustomView(View view, CustomViewCallback customViewCallback) {
        onShowCustomView(view, -1, customViewCallback);
    }
}
