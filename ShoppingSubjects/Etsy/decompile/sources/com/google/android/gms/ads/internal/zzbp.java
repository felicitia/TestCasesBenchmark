package com.google.android.gms.ads.internal;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView;
import com.etsy.android.lib.models.ResponseConstants;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.ack;
import com.google.android.gms.internal.ads.ajh;
import com.google.android.gms.internal.ads.akl;
import com.google.android.gms.internal.ads.bu;
import com.google.android.gms.internal.ads.gv;
import com.google.android.gms.internal.ads.hb;
import com.google.android.gms.internal.ads.jp;
import com.google.android.gms.internal.ads.zzaaw;
import com.google.android.gms.internal.ads.zzabc;
import com.google.android.gms.internal.ads.zzahe;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzcj;
import com.google.android.gms.internal.ads.zzjj;
import com.google.android.gms.internal.ads.zzjn;
import com.google.android.gms.internal.ads.zzke;
import com.google.android.gms.internal.ads.zzkh;
import com.google.android.gms.internal.ads.zzkt;
import com.google.android.gms.internal.ads.zzkx;
import com.google.android.gms.internal.ads.zzla;
import com.google.android.gms.internal.ads.zzlg;
import com.google.android.gms.internal.ads.zzlo;
import com.google.android.gms.internal.ads.zzlu;
import com.google.android.gms.internal.ads.zzmu;
import com.google.android.gms.internal.ads.zzod;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

@bu
public final class zzbp extends zzkt {
    /* access modifiers changed from: private */
    public final Context mContext;
    private final zzjn zzaau;
    /* access modifiers changed from: private */
    public final Future<ack> zzaav = hb.a((Callable<T>) new al<T>(this));
    private final an zzaaw;
    /* access modifiers changed from: private */
    @Nullable
    public WebView zzaax = new WebView(this.mContext);
    /* access modifiers changed from: private */
    @Nullable
    public ack zzaay;
    private AsyncTask<Void, Void, String> zzaaz;
    /* access modifiers changed from: private */
    @Nullable
    public zzkh zzxs;
    /* access modifiers changed from: private */
    public final zzang zzyf;

    public zzbp(Context context, zzjn zzjn, String str, zzang zzang) {
        this.mContext = context;
        this.zzyf = zzang;
        this.zzaau = zzjn;
        this.zzaaw = new an(str);
        zzk(0);
        this.zzaax.setVerticalScrollBarEnabled(false);
        this.zzaax.getSettings().setJavaScriptEnabled(true);
        this.zzaax.setWebViewClient(new aj(this));
        this.zzaax.setOnTouchListener(new ak(this));
    }

    /* access modifiers changed from: private */
    public final String zzv(String str) {
        if (this.zzaay == null) {
            return str;
        }
        Uri parse = Uri.parse(str);
        try {
            parse = this.zzaay.a(parse, this.mContext, null, null);
        } catch (zzcj e) {
            gv.c("Unable to process ad data", e);
        }
        return parse.toString();
    }

    /* access modifiers changed from: private */
    public final void zzw(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        this.mContext.startActivity(intent);
    }

    public final void destroy() throws RemoteException {
        Preconditions.checkMainThread("destroy must be called on the main UI thread.");
        this.zzaaz.cancel(true);
        this.zzaav.cancel(true);
        this.zzaax.destroy();
        this.zzaax = null;
    }

    public final String getAdUnitId() {
        throw new IllegalStateException("getAdUnitId not implemented");
    }

    @Nullable
    public final String getMediationAdapterClassName() throws RemoteException {
        return null;
    }

    @Nullable
    public final zzlo getVideoController() {
        return null;
    }

    public final boolean isLoading() throws RemoteException {
        return false;
    }

    public final boolean isReady() throws RemoteException {
        return false;
    }

    public final void pause() throws RemoteException {
        Preconditions.checkMainThread("pause must be called on the main UI thread.");
    }

    public final void resume() throws RemoteException {
        Preconditions.checkMainThread("resume must be called on the main UI thread.");
    }

    public final void setImmersiveMode(boolean z) {
        throw new IllegalStateException("Unused method");
    }

    public final void setManualImpressionsEnabled(boolean z) throws RemoteException {
    }

    public final void setUserId(String str) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public final void showInterstitial() throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public final void stopLoading() throws RemoteException {
    }

    public final void zza(zzaaw zzaaw2) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public final void zza(zzabc zzabc, String str) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public final void zza(zzahe zzahe) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public final void zza(zzjn zzjn) throws RemoteException {
        throw new IllegalStateException("AdSize must be set before initialization");
    }

    public final void zza(zzke zzke) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public final void zza(zzkh zzkh) throws RemoteException {
        this.zzxs = zzkh;
    }

    public final void zza(zzkx zzkx) {
        throw new IllegalStateException("Unused method");
    }

    public final void zza(zzla zzla) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public final void zza(zzlg zzlg) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public final void zza(zzlu zzlu) {
        throw new IllegalStateException("Unused method");
    }

    public final void zza(zzmu zzmu) {
        throw new IllegalStateException("Unused method");
    }

    public final void zza(zzod zzod) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public final boolean zzb(zzjj zzjj) throws RemoteException {
        Preconditions.checkNotNull(this.zzaax, "This Search Ad has already been torn down");
        this.zzaaw.a(zzjj, this.zzyf);
        this.zzaaz = new am(this, null).execute(new Void[0]);
        return true;
    }

    public final Bundle zzba() {
        throw new IllegalStateException("Unused method");
    }

    public final IObjectWrapper zzbj() throws RemoteException {
        Preconditions.checkMainThread("getAdFrame must be called on the main UI thread.");
        return ObjectWrapper.wrap(this.zzaax);
    }

    public final zzjn zzbk() throws RemoteException {
        return this.zzaau;
    }

    public final void zzbm() throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public final zzla zzbw() {
        throw new IllegalStateException("getIAppEventListener not implemented");
    }

    public final zzkh zzbx() {
        throw new IllegalStateException("getIAdListener not implemented");
    }

    @Nullable
    public final String zzck() throws RemoteException {
        return null;
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public final String zzea() {
        Builder builder = new Builder();
        builder.scheme("https://").appendEncodedPath((String) ajh.f().a(akl.cx));
        builder.appendQueryParameter(ResponseConstants.QUERY, this.zzaaw.b());
        builder.appendQueryParameter("pubId", this.zzaaw.c());
        Map d = this.zzaaw.d();
        for (String str : d.keySet()) {
            builder.appendQueryParameter(str, (String) d.get(str));
        }
        Uri build = builder.build();
        if (this.zzaay != null) {
            try {
                build = this.zzaay.a(build, this.mContext);
            } catch (zzcj e) {
                gv.c("Unable to process ad data", e);
            }
        }
        String zzeb = zzeb();
        String encodedQuery = build.getEncodedQuery();
        StringBuilder sb = new StringBuilder(1 + String.valueOf(zzeb).length() + String.valueOf(encodedQuery).length());
        sb.append(zzeb);
        sb.append("#");
        sb.append(encodedQuery);
        return sb.toString();
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public final String zzeb() {
        String a = this.zzaaw.a();
        if (TextUtils.isEmpty(a)) {
            a = "www.google.com";
        }
        String str = (String) ajh.f().a(akl.cx);
        StringBuilder sb = new StringBuilder(8 + String.valueOf(a).length() + String.valueOf(str).length());
        sb.append("https://");
        sb.append(a);
        sb.append(str);
        return sb.toString();
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public final void zzk(int i) {
        if (this.zzaax != null) {
            this.zzaax.setLayoutParams(new LayoutParams(-1, i));
        }
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public final int zzu(String str) {
        String queryParameter = Uri.parse(str).getQueryParameter(ResponseConstants.HEIGHT);
        if (TextUtils.isEmpty(queryParameter)) {
            return 0;
        }
        try {
            ajh.a();
            return jp.a(this.mContext, Integer.parseInt(queryParameter));
        } catch (NumberFormatException unused) {
            return 0;
        }
    }
}
