package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.ao;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@bu
public final class ki {
    public static <T> kr<T> a(Throwable th) {
        return new kr<>(th);
    }

    public static <T> ks<T> a(T t) {
        return new ks<>(t);
    }

    public static <V> kt<V> a(kt<V> ktVar, long j, TimeUnit timeUnit, ScheduledExecutorService scheduledExecutorService) {
        le leVar = new le();
        a((kt<A>) leVar, (Future<B>) ktVar);
        ScheduledFuture schedule = scheduledExecutorService.schedule(new km(leVar), j, timeUnit);
        a(ktVar, leVar);
        leVar.a(new kn(schedule), kz.b);
        return leVar;
    }

    public static <A, B> kt<B> a(kt<A> ktVar, kd<? super A, ? extends B> kdVar, Executor executor) {
        le leVar = new le();
        ktVar.a(new kl(leVar, kdVar, ktVar), executor);
        a((kt<A>) leVar, (Future<B>) ktVar);
        return leVar;
    }

    public static <A, B> kt<B> a(kt<A> ktVar, ke<A, B> keVar, Executor executor) {
        le leVar = new le();
        ktVar.a(new kk(leVar, keVar, ktVar), executor);
        a((kt<A>) leVar, (Future<B>) ktVar);
        return leVar;
    }

    public static <V, X extends Throwable> kt<V> a(kt<? extends V> ktVar, Class<X> cls, kd<? super X, ? extends V> kdVar, Executor executor) {
        le leVar = new le();
        a((kt<A>) leVar, (Future<B>) ktVar);
        ko koVar = new ko(leVar, ktVar, cls, kdVar, executor);
        ktVar.a(koVar, kz.b);
        return leVar;
    }

    public static <T> T a(Future<T> future, T t) {
        try {
            return future.get(((Long) ajh.f().a(akl.bz)).longValue(), TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e = e;
            future.cancel(true);
            gv.c("InterruptedException caught while resolving future.", e);
            Thread.currentThread().interrupt();
            ao.i().b(e, "Futures.resolveFuture");
            return t;
        } catch (Exception e2) {
            e = e2;
            future.cancel(true);
            gv.b("Error waiting for future.", e);
            ao.i().b(e, "Futures.resolveFuture");
            return t;
        }
    }

    public static <T> T a(Future<T> future, T t, long j, TimeUnit timeUnit) {
        try {
            return future.get(j, timeUnit);
        } catch (InterruptedException e) {
            e = e;
            future.cancel(true);
            gv.c("InterruptedException caught while resolving future.", e);
            Thread.currentThread().interrupt();
            ao.i().a(e, "Futures.resolveFuture");
            return t;
        } catch (Exception e2) {
            e = e2;
            future.cancel(true);
            gv.b("Error waiting for future.", e);
            ao.i().a(e, "Futures.resolveFuture");
            return t;
        }
    }

    public static <V> void a(kt<V> ktVar, kf<V> kfVar, Executor executor) {
        ktVar.a(new kj(kfVar, ktVar), executor);
    }

    private static <V> void a(kt<? extends V> ktVar, le<V> leVar) {
        a((kt<A>) leVar, (Future<B>) ktVar);
        ktVar.a(new kp(leVar, ktVar), kz.b);
    }

    private static <A, B> void a(kt<A> ktVar, Future<B> future) {
        ktVar.a(new kq(ktVar, future), kz.b);
    }

    static final /* synthetic */ void a(le leVar, kd kdVar, kt ktVar) {
        if (!leVar.isCancelled()) {
            try {
                a(kdVar.a(ktVar.get()), leVar);
            } catch (CancellationException unused) {
                leVar.cancel(true);
            } catch (ExecutionException e) {
                leVar.a(e.getCause());
            } catch (InterruptedException e2) {
                Thread.currentThread().interrupt();
                leVar.a(e2);
            } catch (Exception e3) {
                leVar.a(e3);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x001e  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x002a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static final /* synthetic */ void a(com.google.android.gms.internal.ads.le r1, com.google.android.gms.internal.ads.kt r2, java.lang.Class r3, com.google.android.gms.internal.ads.kd r4, java.util.concurrent.Executor r5) {
        /*
            java.lang.Object r2 = r2.get()     // Catch:{ ExecutionException -> 0x0013, InterruptedException -> 0x000a, Exception -> 0x0008 }
            r1.b(r2)     // Catch:{ ExecutionException -> 0x0013, InterruptedException -> 0x000a, Exception -> 0x0008 }
            return
        L_0x0008:
            r2 = move-exception
            goto L_0x0018
        L_0x000a:
            r2 = move-exception
            java.lang.Thread r0 = java.lang.Thread.currentThread()
            r0.interrupt()
            goto L_0x0018
        L_0x0013:
            r2 = move-exception
            java.lang.Throwable r2 = r2.getCause()
        L_0x0018:
            boolean r3 = r3.isInstance(r2)
            if (r3 == 0) goto L_0x002a
            com.google.android.gms.internal.ads.ks r2 = a((T) r2)
            com.google.android.gms.internal.ads.kt r2 = a(r2, r4, r5)
            a(r2, r1)
            return
        L_0x002a:
            r1.a(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.ki.a(com.google.android.gms.internal.ads.le, com.google.android.gms.internal.ads.kt, java.lang.Class, com.google.android.gms.internal.ads.kd, java.util.concurrent.Executor):void");
    }
}
