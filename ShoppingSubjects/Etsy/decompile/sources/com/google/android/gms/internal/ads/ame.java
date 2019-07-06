package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.ads.formats.MediaView;
import com.google.android.gms.ads.formats.f;
import com.google.android.gms.ads.i;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.WeakHashMap;

@bu
public final class ame implements f {
    private static WeakHashMap<IBinder, ame> a = new WeakHashMap<>();
    private final zzqs b;
    private final MediaView c;
    private final i d = new i();

    @VisibleForTesting
    private ame(zzqs zzqs) {
        Context context;
        this.b = zzqs;
        MediaView mediaView = null;
        try {
            context = (Context) ObjectWrapper.unwrap(zzqs.zzkh());
        } catch (RemoteException | NullPointerException e) {
            ka.b("", e);
            context = null;
        }
        if (context != null) {
            MediaView mediaView2 = new MediaView(context);
            try {
                if (this.b.zzh(ObjectWrapper.wrap(mediaView2))) {
                    mediaView = mediaView2;
                }
            } catch (RemoteException e2) {
                ka.b("", e2);
            }
        }
        this.c = mediaView;
    }

    public static ame a(zzqs zzqs) {
        synchronized (a) {
            ame ame = (ame) a.get(zzqs.asBinder());
            if (ame != null) {
                return ame;
            }
            ame ame2 = new ame(zzqs);
            a.put(zzqs.asBinder(), ame2);
            return ame2;
        }
    }

    public final String a() {
        try {
            return this.b.getCustomTemplateId();
        } catch (RemoteException e) {
            ka.b("", e);
            return null;
        }
    }

    public final zzqs b() {
        return this.b;
    }
}
