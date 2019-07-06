package androidx.work.impl;

import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.WorkerThread;
import androidx.work.Data;
import androidx.work.State;
import androidx.work.Worker;
import androidx.work.Worker.Result;
import androidx.work.d;
import androidx.work.e;
import androidx.work.impl.b.b;
import androidx.work.impl.b.j;
import androidx.work.impl.b.k;
import androidx.work.impl.b.n;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestrictTo({Scope.LIBRARY_GROUP})
/* compiled from: WorkerWrapper */
public class g implements Runnable {
    String a;
    a b;
    Worker c;
    private Context d;
    private List<c> e;
    private androidx.work.impl.Extras.a f;
    private j g;
    private androidx.work.a h;
    private WorkDatabase i;
    private k j = this.i.workSpecDao();
    private b k = this.i.dependencyDao();
    private n l = this.i.workTagDao();
    private List<String> m;
    private String n;
    private volatile boolean o;

    @RestrictTo({Scope.LIBRARY_GROUP})
    /* compiled from: WorkerWrapper */
    public static class a {
        Context a;
        @Nullable
        Worker b;
        androidx.work.a c;
        WorkDatabase d;
        String e;
        a f;
        List<c> g;
        androidx.work.impl.Extras.a h;

        public a(@NonNull Context context, @NonNull androidx.work.a aVar, @NonNull WorkDatabase workDatabase, @NonNull String str) {
            this.a = context.getApplicationContext();
            this.c = aVar;
            this.d = workDatabase;
            this.e = str;
        }

        public a a(a aVar) {
            this.f = aVar;
            return this;
        }

        public a a(List<c> list) {
            this.g = list;
            return this;
        }

        public a a(androidx.work.impl.Extras.a aVar) {
            this.h = aVar;
            return this;
        }

        public g a() {
            return new g(this);
        }
    }

    g(a aVar) {
        this.d = aVar.a;
        this.a = aVar.e;
        this.b = aVar.f;
        this.e = aVar.g;
        this.f = aVar.h;
        this.c = aVar.b;
        this.h = aVar.c;
        this.i = aVar.d;
    }

    @WorkerThread
    public void run() {
        this.m = this.l.a(this.a);
        this.n = a(this.m);
        a();
        d.a(this.h, this.i, this.e);
    }

    private void a() {
        Data data;
        Result result;
        if (!c()) {
            this.i.beginTransaction();
            try {
                this.g = this.j.b(this.a);
                if (this.g == null) {
                    e.e("WorkerWrapper", String.format("Didn't find WorkSpec for id %s", new Object[]{this.a}), new Throwable[0]);
                    a(false, false);
                } else if (this.g.b != State.ENQUEUED) {
                    b();
                    this.i.setTransactionSuccessful();
                    this.i.endTransaction();
                } else {
                    this.i.setTransactionSuccessful();
                    this.i.endTransaction();
                    if (this.g.a()) {
                        data = this.g.e;
                    } else {
                        d a2 = d.a(this.g.d);
                        if (a2 == null) {
                            e.e("WorkerWrapper", String.format("Could not create Input Merger %s", new Object[]{this.g.d}), new Throwable[0]);
                            e();
                            return;
                        }
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(this.g.e);
                        arrayList.addAll(this.j.g(this.a));
                        data = a2.a((List<Data>) arrayList);
                    }
                    Extras extras = new Extras(data, this.m, this.f, this.g.k);
                    if (this.c == null) {
                        this.c = a(this.d, this.g, extras);
                    }
                    if (this.c == null) {
                        e.e("WorkerWrapper", String.format("Could for create Worker %s", new Object[]{this.g.c}), new Throwable[0]);
                        e();
                        return;
                    }
                    if (!d()) {
                        b();
                    } else if (!c()) {
                        try {
                            result = this.c.d();
                        } catch (Error | Exception e2) {
                            Result result2 = Result.FAILURE;
                            e.e("WorkerWrapper", String.format("%s failed because it threw an exception/error", new Object[]{this.n}), e2);
                            result = result2;
                        }
                        try {
                            this.i.beginTransaction();
                            if (!c()) {
                                State f2 = this.j.f(this.a);
                                if (f2 == null) {
                                    a(false, false);
                                } else if (f2 == State.RUNNING) {
                                    a(result);
                                } else if (!f2.isFinished()) {
                                    f();
                                }
                                this.i.setTransactionSuccessful();
                            }
                        } finally {
                            this.i.endTransaction();
                        }
                    }
                }
            } finally {
                this.i.endTransaction();
            }
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void a(boolean z) {
        this.o = true;
        if (this.c != null) {
            this.c.a(z);
        }
    }

    private void b() {
        State f2 = this.j.f(this.a);
        if (f2 == State.RUNNING) {
            e.b("WorkerWrapper", String.format("Status for %s is RUNNING;not doing any work and rescheduling for later execution", new Object[]{this.a}), new Throwable[0]);
            a(false, true);
            return;
        }
        e.b("WorkerWrapper", String.format("Status for %s is %s; not doing any work", new Object[]{this.a, f2}), new Throwable[0]);
        a(false, false);
    }

    private boolean c() {
        boolean z = false;
        if (!this.o) {
            return false;
        }
        e.c("WorkerWrapper", String.format("Work interrupted for %s", new Object[]{this.n}), new Throwable[0]);
        State f2 = this.j.f(this.a);
        if (f2 == null) {
            a(false, false);
        } else {
            if (f2 == State.SUCCEEDED) {
                z = true;
            }
            a(z, !f2.isFinished());
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0023 A[Catch:{ all -> 0x0041 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(final boolean r4, final boolean r5) {
        /*
            r3 = this;
            androidx.work.impl.a r0 = r3.b
            if (r0 != 0) goto L_0x0005
            return
        L_0x0005:
            androidx.work.impl.WorkDatabase r0 = r3.i     // Catch:{ all -> 0x0041 }
            r0.beginTransaction()     // Catch:{ all -> 0x0041 }
            androidx.work.impl.WorkDatabase r0 = r3.i     // Catch:{ all -> 0x0041 }
            androidx.work.impl.b.k r0 = r0.workSpecDao()     // Catch:{ all -> 0x0041 }
            java.util.List r0 = r0.a()     // Catch:{ all -> 0x0041 }
            r1 = 0
            if (r0 == 0) goto L_0x0020
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x0041 }
            if (r0 == 0) goto L_0x001e
            goto L_0x0020
        L_0x001e:
            r0 = r1
            goto L_0x0021
        L_0x0020:
            r0 = 1
        L_0x0021:
            if (r0 == 0) goto L_0x002a
            android.content.Context r0 = r3.d     // Catch:{ all -> 0x0041 }
            java.lang.Class<androidx.work.impl.background.systemalarm.RescheduleReceiver> r2 = androidx.work.impl.background.systemalarm.RescheduleReceiver.class
            androidx.work.impl.utils.d.a(r0, r2, r1)     // Catch:{ all -> 0x0041 }
        L_0x002a:
            androidx.work.impl.utils.a.c r0 = androidx.work.impl.utils.a.c.a()     // Catch:{ all -> 0x0041 }
            androidx.work.impl.g$1 r1 = new androidx.work.impl.g$1     // Catch:{ all -> 0x0041 }
            r1.<init>(r4, r5)     // Catch:{ all -> 0x0041 }
            r0.a(r1)     // Catch:{ all -> 0x0041 }
            androidx.work.impl.WorkDatabase r4 = r3.i     // Catch:{ all -> 0x0041 }
            r4.setTransactionSuccessful()     // Catch:{ all -> 0x0041 }
            androidx.work.impl.WorkDatabase r4 = r3.i
            r4.endTransaction()
            return
        L_0x0041:
            r4 = move-exception
            androidx.work.impl.WorkDatabase r5 = r3.i
            r5.endTransaction()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.g.a(boolean, boolean):void");
    }

    private void a(Result result) {
        switch (result) {
            case SUCCESS:
                e.c("WorkerWrapper", String.format("Worker result SUCCESS for %s", new Object[]{this.n}), new Throwable[0]);
                if (this.g.a()) {
                    b(true);
                    return;
                } else {
                    g();
                    return;
                }
            case RETRY:
                e.c("WorkerWrapper", String.format("Worker result RETRY for %s", new Object[]{this.n}), new Throwable[0]);
                f();
                return;
            default:
                e.c("WorkerWrapper", String.format("Worker result FAILURE for %s", new Object[]{this.n}), new Throwable[0]);
                if (this.g.a()) {
                    b(false);
                    return;
                } else {
                    e();
                    return;
                }
        }
    }

    private boolean d() {
        this.i.beginTransaction();
        try {
            boolean z = true;
            if (this.j.f(this.a) == State.ENQUEUED) {
                this.j.a(State.RUNNING, this.a);
                this.j.d(this.a);
            } else {
                z = false;
            }
            this.i.setTransactionSuccessful();
            return z;
        } finally {
            this.i.endTransaction();
        }
    }

    private void e() {
        this.i.beginTransaction();
        try {
            a(this.a);
            if (this.c != null) {
                this.j.a(this.a, this.c.e());
            }
            this.i.setTransactionSuccessful();
        } finally {
            this.i.endTransaction();
            a(false, false);
        }
    }

    private void a(String str) {
        for (String a2 : this.k.b(str)) {
            a(a2);
        }
        if (this.j.f(str) != State.CANCELLED) {
            this.j.a(State.FAILED, str);
        }
    }

    private void f() {
        this.i.beginTransaction();
        try {
            this.j.a(State.ENQUEUED, this.a);
            this.j.a(this.a, System.currentTimeMillis());
            this.i.setTransactionSuccessful();
        } finally {
            this.i.endTransaction();
            a(false, true);
        }
    }

    private void b(boolean z) {
        this.i.beginTransaction();
        try {
            this.j.a(this.a, this.g.n + this.g.h);
            this.j.a(State.ENQUEUED, this.a);
            this.j.e(this.a);
            if (VERSION.SDK_INT < 23) {
                this.j.b(this.a, -1);
            }
            this.i.setTransactionSuccessful();
        } finally {
            this.i.endTransaction();
            a(z, false);
        }
    }

    private void g() {
        this.i.beginTransaction();
        try {
            this.j.a(State.SUCCEEDED, this.a);
            this.j.a(this.a, this.c.e());
            long currentTimeMillis = System.currentTimeMillis();
            for (String str : this.k.b(this.a)) {
                if (this.k.a(str)) {
                    e.c("WorkerWrapper", String.format("Setting status to enqueued for %s", new Object[]{str}), new Throwable[0]);
                    this.j.a(State.ENQUEUED, str);
                    this.j.a(str, currentTimeMillis);
                }
            }
            this.i.setTransactionSuccessful();
        } finally {
            this.i.endTransaction();
            a(true, false);
        }
    }

    private String a(List<String> list) {
        StringBuilder sb = new StringBuilder("Work [ id=");
        sb.append(this.a);
        sb.append(", tags={ ");
        boolean z = true;
        for (String str : list) {
            if (z) {
                z = false;
            } else {
                sb.append(", ");
            }
            sb.append(str);
        }
        sb.append(" } ]");
        return sb.toString();
    }

    static Worker a(@NonNull Context context, @NonNull j jVar, @NonNull Extras extras) {
        return a(context, jVar.c, UUID.fromString(jVar.a), extras);
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public static Worker a(@NonNull Context context, @NonNull String str, @NonNull UUID uuid, @NonNull Extras extras) {
        Context applicationContext = context.getApplicationContext();
        try {
            Worker worker = (Worker) Class.forName(str).newInstance();
            Method declaredMethod = Worker.class.getDeclaredMethod("internalInit", new Class[]{Context.class, UUID.class, Extras.class});
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(worker, new Object[]{applicationContext, uuid, extras});
            return worker;
        } catch (Exception e2) {
            StringBuilder sb = new StringBuilder();
            sb.append("Trouble instantiating ");
            sb.append(str);
            e.e("WorkerWrapper", sb.toString(), e2);
            return null;
        }
    }
}
