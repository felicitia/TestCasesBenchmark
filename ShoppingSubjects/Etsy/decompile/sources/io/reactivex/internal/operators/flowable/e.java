package io.reactivex.internal.operators.flowable;

import io.reactivex.functions.g;
import io.reactivex.internal.functions.a;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.ScalarSubscription;
import java.util.concurrent.Callable;
import org.reactivestreams.b;
import org.reactivestreams.c;

/* compiled from: FlowableScalarXMap */
public final class e {
    public static <T, R> boolean a(b<T> bVar, c<? super R> cVar, g<? super T, ? extends b<? extends R>> gVar) {
        if (!(bVar instanceof Callable)) {
            return false;
        }
        try {
            Object call = ((Callable) bVar).call();
            if (call == null) {
                EmptySubscription.complete(cVar);
                return true;
            }
            try {
                b bVar2 = (b) a.a(gVar.apply(call), "The mapper returned a null Publisher");
                if (bVar2 instanceof Callable) {
                    try {
                        Object call2 = ((Callable) bVar2).call();
                        if (call2 == null) {
                            EmptySubscription.complete(cVar);
                            return true;
                        }
                        cVar.onSubscribe(new ScalarSubscription(cVar, call2));
                    } catch (Throwable th) {
                        io.reactivex.exceptions.a.b(th);
                        EmptySubscription.error(th, cVar);
                        return true;
                    }
                } else {
                    bVar2.subscribe(cVar);
                }
                return true;
            } catch (Throwable th2) {
                io.reactivex.exceptions.a.b(th2);
                EmptySubscription.error(th2, cVar);
                return true;
            }
        } catch (Throwable th3) {
            io.reactivex.exceptions.a.b(th3);
            EmptySubscription.error(th3, cVar);
            return true;
        }
    }
}
