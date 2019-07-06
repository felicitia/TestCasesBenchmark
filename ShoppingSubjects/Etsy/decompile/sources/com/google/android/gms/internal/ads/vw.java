package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.vv;
import com.google.android.gms.internal.ads.vw;

public abstract class vw<MessageType extends vv<MessageType, BuilderType>, BuilderType extends vw<MessageType, BuilderType>> implements yl {
    /* renamed from: a */
    public abstract BuilderType clone();

    /* access modifiers changed from: protected */
    public abstract BuilderType a(MessageType messagetype);

    public final /* synthetic */ yl a(yk ykVar) {
        if (p().getClass().isInstance(ykVar)) {
            return a((MessageType) (vv) ykVar);
        }
        throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
    }
}
