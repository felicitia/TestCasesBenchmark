package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.content.ContentProviderClient;
import android.content.Context;
import android.location.Location;
import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolder.ListenerKey;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.f;
import com.google.android.gms.location.zzu;
import com.google.android.gms.location.zzx;
import java.util.HashMap;
import java.util.Map;

public final class g {
    private final q<zzao> a;
    private final Context b;
    private ContentProviderClient c = null;
    private boolean d = false;
    private final Map<ListenerKey<com.google.android.gms.location.g>, l> e = new HashMap();
    private final Map<ListenerKey<Object>, k> f = new HashMap();
    private final Map<ListenerKey<f>, h> g = new HashMap();

    public g(Context context, q<zzao> qVar) {
        this.b = context;
        this.a = qVar;
    }

    private final l a(ListenerHolder<com.google.android.gms.location.g> listenerHolder) {
        l lVar;
        synchronized (this.e) {
            lVar = (l) this.e.get(listenerHolder.getListenerKey());
            if (lVar == null) {
                lVar = new l(listenerHolder);
            }
            this.e.put(listenerHolder.getListenerKey(), lVar);
        }
        return lVar;
    }

    public final Location a() throws RemoteException {
        this.a.b();
        return ((zzao) this.a.a()).zza(this.b.getPackageName());
    }

    public final void a(ListenerKey<com.google.android.gms.location.g> listenerKey, zzaj zzaj) throws RemoteException {
        this.a.b();
        Preconditions.checkNotNull(listenerKey, "Invalid null listener key");
        synchronized (this.e) {
            l lVar = (l) this.e.remove(listenerKey);
            if (lVar != null) {
                lVar.a();
                ((zzao) this.a.a()).zza(zzbf.zza((zzx) lVar, zzaj));
            }
        }
    }

    public final void a(LocationRequest locationRequest, PendingIntent pendingIntent, zzaj zzaj) throws RemoteException {
        this.a.b();
        zzao zzao = (zzao) this.a.a();
        zzbf zzbf = new zzbf(1, zzbd.zza(locationRequest), null, pendingIntent, null, zzaj != null ? zzaj.asBinder() : null);
        zzao.zza(zzbf);
    }

    public final void a(LocationRequest locationRequest, ListenerHolder<com.google.android.gms.location.g> listenerHolder, zzaj zzaj) throws RemoteException {
        this.a.b();
        zzao zzao = (zzao) this.a.a();
        zzbf zzbf = new zzbf(1, zzbd.zza(locationRequest), a(listenerHolder).asBinder(), null, null, zzaj != null ? zzaj.asBinder() : null);
        zzao.zza(zzbf);
    }

    public final void a(boolean z) throws RemoteException {
        this.a.b();
        ((zzao) this.a.a()).zza(z);
        this.d = z;
    }

    public final void b() throws RemoteException {
        synchronized (this.e) {
            for (l lVar : this.e.values()) {
                if (lVar != null) {
                    ((zzao) this.a.a()).zza(zzbf.zza((zzx) lVar, (zzaj) null));
                }
            }
            this.e.clear();
        }
        synchronized (this.g) {
            for (h hVar : this.g.values()) {
                if (hVar != null) {
                    ((zzao) this.a.a()).zza(zzbf.zza((zzu) hVar, (zzaj) null));
                }
            }
            this.g.clear();
        }
        synchronized (this.f) {
            for (k kVar : this.f.values()) {
                if (kVar != null) {
                    ((zzao) this.a.a()).zza(new zzo(2, null, kVar.asBinder(), null));
                }
            }
            this.f.clear();
        }
    }

    public final void c() throws RemoteException {
        if (this.d) {
            a(false);
        }
    }
}
