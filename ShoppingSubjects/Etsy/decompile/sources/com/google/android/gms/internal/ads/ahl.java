package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.DynamiteModule.LoadingException;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;

@bu
public final class ahl {
    @VisibleForTesting
    zzen a;
    @VisibleForTesting
    boolean b;

    public ahl() {
    }

    public ahl(Context context) {
        akl.a(context);
        if (((Boolean) ajh.f().a(akl.db)).booleanValue()) {
            try {
                this.a = zzeo.zza(DynamiteModule.a(context, DynamiteModule.a, ModuleDescriptor.MODULE_ID).a("com.google.android.gms.ads.clearcut.DynamiteClearcutLogger"));
                ObjectWrapper.wrap(context);
                this.a.zza(ObjectWrapper.wrap(context), "GMA_SDK");
                this.b = true;
            } catch (RemoteException | LoadingException | NullPointerException unused) {
                ka.b("Cannot dynamite load clearcut");
            }
        }
    }

    public ahl(Context context, String str, String str2) {
        akl.a(context);
        try {
            this.a = zzeo.zza(DynamiteModule.a(context, DynamiteModule.a, ModuleDescriptor.MODULE_ID).a("com.google.android.gms.ads.clearcut.DynamiteClearcutLogger"));
            ObjectWrapper.wrap(context);
            this.a.zza(ObjectWrapper.wrap(context), str, null);
            this.b = true;
        } catch (RemoteException | LoadingException | NullPointerException unused) {
            ka.b("Cannot dynamite load clearcut");
        }
    }

    public final ahn a(byte[] bArr) {
        return new ahn(this, bArr);
    }
}
