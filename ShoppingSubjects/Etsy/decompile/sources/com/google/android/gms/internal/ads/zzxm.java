package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.ads.mediation.AdUrlAdapter;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.ads.mediation.b;
import com.google.ads.mediation.e;
import com.google.android.gms.ads.mediation.customevent.CustomEventAdapter;
import com.google.android.gms.ads.mediation.customevent.a;
import com.google.android.gms.ads.mediation.customevent.c;
import java.util.Map;

@bu
public final class zzxm extends zzxo {
    private static final arx zzbup = new arx();
    private Map<Class<? extends Object>, Object> zzbuo;

    private final <NetworkExtrasT extends e, ServerParametersT extends MediationServerParameters> zzxq zzbo(String str) throws RemoteException {
        try {
            Class cls = Class.forName(str, false, zzxm.class.getClassLoader());
            if (b.class.isAssignableFrom(cls)) {
                b bVar = (b) cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                return new zzyp(bVar, (e) this.zzbuo.get(bVar.getAdditionalParametersType()));
            } else if (com.google.android.gms.ads.mediation.b.class.isAssignableFrom(cls)) {
                return new zzyk((com.google.android.gms.ads.mediation.b) cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]));
            } else {
                StringBuilder sb = new StringBuilder(64 + String.valueOf(str).length());
                sb.append("Could not instantiate mediation adapter: ");
                sb.append(str);
                sb.append(" (not a valid adapter).");
                ka.e(sb.toString());
                throw new RemoteException();
            }
        } catch (Throwable unused) {
            return zzbp(str);
        }
    }

    private final zzxq zzbp(String str) throws RemoteException {
        try {
            ka.b("Reflection failed, retrying using direct instantiation");
            if ("com.google.ads.mediation.admob.AdMobAdapter".equals(str)) {
                return new zzyk(new AdMobAdapter());
            }
            if ("com.google.ads.mediation.AdUrlAdapter".equals(str)) {
                return new zzyk(new AdUrlAdapter());
            }
            if ("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter".equals(str)) {
                return new zzyk(new CustomEventAdapter());
            }
            if ("com.google.ads.mediation.customevent.CustomEventAdapter".equals(str)) {
                com.google.ads.mediation.customevent.CustomEventAdapter customEventAdapter = new com.google.ads.mediation.customevent.CustomEventAdapter();
                return new zzyp(customEventAdapter, (c) this.zzbuo.get(customEventAdapter.getAdditionalParametersType()));
            }
            throw new RemoteException();
        } catch (Throwable th) {
            StringBuilder sb = new StringBuilder(43 + String.valueOf(str).length());
            sb.append("Could not instantiate mediation adapter: ");
            sb.append(str);
            sb.append(". ");
            ka.c(sb.toString(), th);
        }
    }

    public final zzxq zzbm(String str) throws RemoteException {
        return zzbo(str);
    }

    public final boolean zzbn(String str) throws RemoteException {
        try {
            return a.class.isAssignableFrom(Class.forName(str, false, zzxm.class.getClassLoader()));
        } catch (Throwable unused) {
            StringBuilder sb = new StringBuilder(80 + String.valueOf(str).length());
            sb.append("Could not load custom event implementation class: ");
            sb.append(str);
            sb.append(", assuming old implementation.");
            ka.e(sb.toString());
            return false;
        }
    }

    public final zzzj zzbq(String str) throws RemoteException {
        return arx.a(str);
    }

    public final void zzj(Map<Class<? extends Object>, Object> map) {
        this.zzbuo = map;
    }
}
