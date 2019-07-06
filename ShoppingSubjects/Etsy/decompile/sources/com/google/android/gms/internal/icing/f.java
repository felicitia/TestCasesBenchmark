package com.google.android.gms.internal.icing;

import com.google.android.gms.internal.icing.e;
import com.google.android.gms.internal.icing.f;

public abstract class f<MessageType extends e<MessageType, BuilderType>, BuilderType extends f<MessageType, BuilderType>> implements bm {
    public final /* synthetic */ bm a(bl blVar) {
        if (j().getClass().isInstance(blVar)) {
            return a((MessageType) (e) blVar);
        }
        throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
    }

    /* renamed from: a */
    public abstract BuilderType clone();

    /* access modifiers changed from: protected */
    public abstract BuilderType a(MessageType messagetype);
}
