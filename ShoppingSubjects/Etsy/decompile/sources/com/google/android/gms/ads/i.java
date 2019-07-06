package com.google.android.gms.ads;

import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.ads.bu;
import com.google.android.gms.internal.ads.ka;
import com.google.android.gms.internal.ads.zzlo;
import com.google.android.gms.internal.ads.zzmt;

@bu
public final class i {
    private final Object a = new Object();
    @Nullable
    private zzlo b;
    @Nullable
    private a c;

    public static abstract class a {
        public void a() {
        }

        public void a(boolean z) {
        }

        public void b() {
        }

        public void c() {
        }

        public void d() {
        }
    }

    public final zzlo a() {
        zzlo zzlo;
        synchronized (this.a) {
            zzlo = this.b;
        }
        return zzlo;
    }

    public final void a(a aVar) {
        Preconditions.checkNotNull(aVar, "VideoLifecycleCallbacks may not be null.");
        synchronized (this.a) {
            this.c = aVar;
            if (this.b != null) {
                try {
                    this.b.zza(new zzmt(aVar));
                } catch (RemoteException e) {
                    ka.b("Unable to call setVideoLifecycleCallbacks on video controller.", e);
                }
            }
        }
    }

    public final void a(zzlo zzlo) {
        synchronized (this.a) {
            this.b = zzlo;
            if (this.c != null) {
                a(this.c);
            }
        }
    }
}
