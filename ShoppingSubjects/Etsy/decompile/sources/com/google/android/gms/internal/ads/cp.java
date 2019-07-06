package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Binder;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.support.annotation.NonNull;
import com.etsy.android.lib.models.ResponseConstants;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks;
import com.google.android.gms.common.internal.BaseGmsClient.BaseOnConnectionFailedListener;
import com.google.android.gms.common.util.VisibleForTesting;

@bu
public final class cp extends cl implements BaseConnectionCallbacks, BaseOnConnectionFailedListener {
    private Context a;
    private zzang b;
    private lg<zzaef> c;
    private final cj d;
    private final Object e = new Object();
    @VisibleForTesting
    private cq f;

    public cp(Context context, zzang zzang, lg<zzaef> lgVar, cj cjVar) {
        super(lgVar, cjVar);
        this.a = context;
        this.b = zzang;
        this.c = lgVar;
        this.d = cjVar;
        this.f = new cq(context, ((Boolean) ajh.f().a(akl.G)).booleanValue() ? ao.t().a() : context.getMainLooper(), this, this);
        this.f.checkAvailabilityAndConnect();
    }

    public final void a() {
        synchronized (this.e) {
            if (this.f.isConnected() || this.f.isConnecting()) {
                this.f.disconnect();
            }
            Binder.flushPendingCommands();
        }
    }

    public final zzaen d() {
        zzaen a2;
        synchronized (this.e) {
            try {
                a2 = this.f.a();
            } catch (DeadObjectException | IllegalStateException unused) {
                return null;
            } catch (Throwable th) {
                throw th;
            }
        }
        return a2;
    }

    public final void onConnected(Bundle bundle) {
        c();
    }

    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        gv.b("Cannot connect to remote service, fallback to local instance.");
        new co(this.a, this.c, this.d).c();
        Bundle bundle = new Bundle();
        bundle.putString(ResponseConstants.ACTION, "gms_connection_failed_fallback_to_local");
        ao.e().b(this.a, this.b.zzcw, "gmob-apps", bundle, true);
    }

    public final void onConnectionSuspended(int i) {
        gv.b("Disconnected from remote ad request service.");
    }
}
