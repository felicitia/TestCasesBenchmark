package com.google.android.gms.internal.firebase-perf;

import com.google.android.gms.internal.firebase-perf.zzaw;
import com.google.android.gms.internal.firebase-perf.zzax;

public abstract class zzax<MessageType extends zzaw<MessageType, BuilderType>, BuilderType extends zzax<MessageType, BuilderType>> implements zzdu {
    /* access modifiers changed from: protected */
    public abstract BuilderType zza(MessageType messagetype);

    /* renamed from: zzbg */
    public abstract BuilderType clone();

    public final /* synthetic */ zzdu zza(zzdt zzdt) {
        if (zzds().getClass().isInstance(zzdt)) {
            return zza((MessageType) (zzaw) zzdt);
        }
        throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
    }
}
