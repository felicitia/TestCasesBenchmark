package com.google.android.gms.internal.icing;

import com.google.android.gms.internal.icing.zzbb;
import com.google.android.gms.internal.icing.zzbc;

public abstract class zzbc<MessageType extends zzbb<MessageType, BuilderType>, BuilderType extends zzbc<MessageType, BuilderType>> implements zzds {
    /* access modifiers changed from: protected */
    public abstract BuilderType zza(MessageType messagetype);

    public final /* synthetic */ zzds zza(zzdr zzdr) {
        if (zzba().getClass().isInstance(zzdr)) {
            return zza((MessageType) (zzbb) zzdr);
        }
        throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
    }

    /* renamed from: zzn */
    public abstract BuilderType clone();
}
