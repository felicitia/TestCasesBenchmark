package com.google.android.gms.plus.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.view.View;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.RemoteCreator;
import com.google.android.gms.plus.PlusOneDummyView;

public final class a extends RemoteCreator<zzd> {
    private static final a a = new a();

    private a() {
        super("com.google.android.gms.plus.plusone.PlusOneButtonCreatorImpl");
    }

    public static View a(Context context, int i, int i2, String str, int i3) {
        if (str != null) {
            return (View) ObjectWrapper.unwrap(((zzd) a.getRemoteCreatorInstance(context)).zza(ObjectWrapper.wrap(context), i, i2, str, i3));
        }
        try {
            throw new NullPointerException();
        } catch (Exception unused) {
            return new PlusOneDummyView(context, i);
        }
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object getRemoteCreator(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.plus.internal.IPlusOneButtonCreator");
        return queryLocalInterface instanceof zzd ? (zzd) queryLocalInterface : new zze(iBinder);
    }
}
