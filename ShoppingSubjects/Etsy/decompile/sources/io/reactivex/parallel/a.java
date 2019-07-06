package io.reactivex.parallel;

import io.reactivex.internal.subscriptions.EmptySubscription;
import org.reactivestreams.c;

/* compiled from: ParallelFlowable */
public abstract class a<T> {
    public abstract int a();

    public abstract void a(c<? super T>[] cVarArr);

    /* access modifiers changed from: protected */
    public final boolean b(c<?>[] cVarArr) {
        int a = a();
        if (cVarArr.length == a) {
            return true;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("parallelism = ");
        sb.append(a);
        sb.append(", subscribers = ");
        sb.append(cVarArr.length);
        IllegalArgumentException illegalArgumentException = new IllegalArgumentException(sb.toString());
        for (c<?> error : cVarArr) {
            EmptySubscription.error(illegalArgumentException, error);
        }
        return false;
    }
}
