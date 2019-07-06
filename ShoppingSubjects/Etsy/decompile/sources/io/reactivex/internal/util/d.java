package io.reactivex.internal.util;

import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.ProtocolViolationException;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.a;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;

/* compiled from: EndConsumerHelper */
public final class d {
    public static boolean a(AtomicReference<Disposable> atomicReference, Disposable disposable, Class<?> cls) {
        a.a(disposable, "next is null");
        if (atomicReference.compareAndSet(null, disposable)) {
            return true;
        }
        disposable.dispose();
        if (atomicReference.get() != DisposableHelper.DISPOSED) {
            a(cls);
        }
        return false;
    }

    public static boolean a(AtomicReference<Subscription> atomicReference, Subscription subscription, Class<?> cls) {
        a.a(subscription, "next is null");
        if (atomicReference.compareAndSet(null, subscription)) {
            return true;
        }
        subscription.cancel();
        if (atomicReference.get() != SubscriptionHelper.CANCELLED) {
            a(cls);
        }
        return false;
    }

    public static String a(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("It is not allowed to subscribe with a(n) ");
        sb.append(str);
        sb.append(" multiple times. Please create a fresh instance of ");
        sb.append(str);
        sb.append(" and subscribe that to the target source instead.");
        return sb.toString();
    }

    public static void a(Class<?> cls) {
        io.reactivex.d.a.a((Throwable) new ProtocolViolationException(a(cls.getName())));
    }
}
