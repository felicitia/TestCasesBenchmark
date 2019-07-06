package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.GradientDrawable;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import com.google.android.gms.ads.a.a.C0131a;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.ads.internal.bg;
import com.google.android.gms.ads.internal.gmsg.ae;
import com.google.android.gms.ads.internal.overlay.zzc;
import com.google.android.gms.ads.internal.overlay.zzd;
import com.google.android.gms.common.util.Predicate;
import java.util.Map;
import org.json.JSONObject;

@bu
public final class zzarh extends FrameLayout implements nn {
    private final nn zzdcy;
    private final mg zzdcz;

    public zzarh(nn nnVar) {
        super(nnVar.getContext());
        this.zzdcy = nnVar;
        this.zzdcz = new mg(nnVar.zzua(), this, this);
        addView(this.zzdcy.getView());
    }

    public final void destroy() {
        this.zzdcy.destroy();
    }

    public final OnClickListener getOnClickListener() {
        return this.zzdcy.getOnClickListener();
    }

    public final int getRequestedOrientation() {
        return this.zzdcy.getRequestedOrientation();
    }

    public final View getView() {
        return this;
    }

    public final WebView getWebView() {
        return this.zzdcy.getWebView();
    }

    public final boolean isDestroyed() {
        return this.zzdcy.isDestroyed();
    }

    public final void loadData(String str, String str2, String str3) {
        this.zzdcy.loadData(str, str2, str3);
    }

    public final void loadDataWithBaseURL(String str, String str2, String str3, String str4, String str5) {
        this.zzdcy.loadDataWithBaseURL(str, str2, str3, str4, str5);
    }

    public final void loadUrl(String str) {
        this.zzdcy.loadUrl(str);
    }

    public final void onPause() {
        this.zzdcz.b();
        this.zzdcy.onPause();
    }

    public final void onResume() {
        this.zzdcy.onResume();
    }

    public final void setOnClickListener(OnClickListener onClickListener) {
        this.zzdcy.setOnClickListener(onClickListener);
    }

    public final void setOnTouchListener(OnTouchListener onTouchListener) {
        this.zzdcy.setOnTouchListener(onTouchListener);
    }

    public final void setRequestedOrientation(int i) {
        this.zzdcy.setRequestedOrientation(i);
    }

    public final void setWebChromeClient(WebChromeClient webChromeClient) {
        this.zzdcy.setWebChromeClient(webChromeClient);
    }

    public final void setWebViewClient(WebViewClient webViewClient) {
        this.zzdcy.setWebViewClient(webViewClient);
    }

    public final void stopLoading() {
        this.zzdcy.stopLoading();
    }

    public final void zza(zzc zzc) {
        this.zzdcy.zza(zzc);
    }

    public final void zza(zzd zzd) {
        this.zzdcy.zza(zzd);
    }

    public final void zza(afm afm) {
        this.zzdcy.zza(afm);
    }

    public final void zza(ot otVar) {
        this.zzdcy.zza(otVar);
    }

    public final void zza(zzarl zzarl) {
        this.zzdcy.zza(zzarl);
    }

    public final void zza(String str, ae<? super nn> aeVar) {
        this.zzdcy.zza(str, aeVar);
    }

    public final void zza(String str, Predicate<ae<? super nn>> predicate) {
        this.zzdcy.zza(str, predicate);
    }

    public final void zza(String str, Map<String, ?> map) {
        this.zzdcy.zza(str, map);
    }

    public final void zza(String str, JSONObject jSONObject) {
        this.zzdcy.zza(str, jSONObject);
    }

    public final void zza(boolean z, int i) {
        this.zzdcy.zza(z, i);
    }

    public final void zza(boolean z, int i, String str) {
        this.zzdcy.zza(z, i, str);
    }

    public final void zza(boolean z, int i, String str, String str2) {
        this.zzdcy.zza(z, i, str, str2);
    }

    public final void zzah(boolean z) {
        this.zzdcy.zzah(z);
    }

    public final void zzai(int i) {
        this.zzdcy.zzai(i);
    }

    public final void zzai(boolean z) {
        this.zzdcy.zzai(z);
    }

    public final void zzaj(boolean z) {
        this.zzdcy.zzaj(z);
    }

    public final void zzak(boolean z) {
        this.zzdcy.zzak(z);
    }

    public final void zzb(zzd zzd) {
        this.zzdcy.zzb(zzd);
    }

    public final void zzb(@Nullable ali ali) {
        this.zzdcy.zzb(ali);
    }

    public final void zzb(String str, ae<? super nn> aeVar) {
        this.zzdcy.zzb(str, aeVar);
    }

    public final void zzb(String str, JSONObject jSONObject) {
        this.zzdcy.zzb(str, jSONObject);
    }

    public final void zzbe(String str) {
        this.zzdcy.zzbe(str);
    }

    public final bg zzbi() {
        return this.zzdcy.zzbi();
    }

    public final void zzbm(Context context) {
        this.zzdcy.zzbm(context);
    }

    public final void zzc(String str, String str2, @Nullable String str3) {
        this.zzdcy.zzc(str, str2, str3);
    }

    public final void zzcl() {
        this.zzdcy.zzcl();
    }

    public final void zzcm() {
        this.zzdcy.zzcm();
    }

    public final void zzdr(String str) {
        this.zzdcy.zzdr(str);
    }

    public final void zzno() {
        this.zzdcy.zzno();
    }

    public final void zznp() {
        this.zzdcy.zznp();
    }

    public final String zzol() {
        return this.zzdcy.zzol();
    }

    public final mg zztl() {
        return this.zzdcz;
    }

    public final zzarl zztm() {
        return this.zzdcy.zztm();
    }

    public final akw zztn() {
        return this.zzdcy.zztn();
    }

    public final Activity zzto() {
        return this.zzdcy.zzto();
    }

    public final akx zztp() {
        return this.zzdcy.zztp();
    }

    public final zzang zztq() {
        return this.zzdcy.zztq();
    }

    public final int zztr() {
        return getMeasuredHeight();
    }

    public final int zzts() {
        return getMeasuredWidth();
    }

    public final void zzty() {
        this.zzdcy.zzty();
    }

    public final void zztz() {
        this.zzdcy.zztz();
    }

    public final void zzu(boolean z) {
        this.zzdcy.zzu(z);
    }

    public final Context zzua() {
        return this.zzdcy.zzua();
    }

    public final zzd zzub() {
        return this.zzdcy.zzub();
    }

    public final zzd zzuc() {
        return this.zzdcy.zzuc();
    }

    public final ot zzud() {
        return this.zzdcy.zzud();
    }

    public final String zzue() {
        return this.zzdcy.zzue();
    }

    public final oo zzuf() {
        return this.zzdcy.zzuf();
    }

    public final WebViewClient zzug() {
        return this.zzdcy.zzug();
    }

    public final boolean zzuh() {
        return this.zzdcy.zzuh();
    }

    public final ack zzui() {
        return this.zzdcy.zzui();
    }

    public final boolean zzuj() {
        return this.zzdcy.zzuj();
    }

    public final void zzuk() {
        this.zzdcz.c();
        this.zzdcy.zzuk();
    }

    public final boolean zzul() {
        return this.zzdcy.zzul();
    }

    public final boolean zzum() {
        return this.zzdcy.zzum();
    }

    public final boolean zzun() {
        return this.zzdcy.zzun();
    }

    public final void zzuo() {
        this.zzdcy.zzuo();
    }

    public final void zzup() {
        this.zzdcy.zzup();
    }

    @Nullable
    public final ali zzuq() {
        return this.zzdcy.zzuq();
    }

    public final void zzur() {
        setBackgroundColor(0);
        this.zzdcy.setBackgroundColor(0);
    }

    public final void zzus() {
        TextView textView = new TextView(getContext());
        Resources h = ao.i().h();
        textView.setText(h != null ? h.getString(C0131a.s7) : "Test Ad");
        textView.setTextSize(15.0f);
        textView.setTextColor(-1);
        textView.setPadding(5, 0, 5, 0);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setColor(-12303292);
        gradientDrawable.setCornerRadius(8.0f);
        if (VERSION.SDK_INT >= 16) {
            textView.setBackground(gradientDrawable);
        } else {
            textView.setBackgroundDrawable(gradientDrawable);
        }
        addView(textView, new LayoutParams(-2, -2, 49));
        bringChildToFront(textView);
    }
}
