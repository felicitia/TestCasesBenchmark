package androidx.work.impl.background.systemalarm;

import android.content.Context;
import android.os.PowerManager.WakeLock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.WorkerThread;
import androidx.work.e;
import androidx.work.impl.a;
import androidx.work.impl.a.c;
import androidx.work.impl.b.j;
import androidx.work.impl.utils.h;
import java.util.Collections;
import java.util.List;

@RestrictTo({Scope.LIBRARY_GROUP})
/* compiled from: DelayMetCommandHandler */
public class d implements a, c, a {
    private final Context a;
    private final int b;
    private final String c;
    private final e d;
    private final androidx.work.impl.a.d e = new androidx.work.impl.a.d(this.a, this);
    private final Object f = new Object();
    private boolean g = false;
    @Nullable
    private WakeLock h;
    private boolean i = false;

    d(@NonNull Context context, int i2, @NonNull String str, @NonNull e eVar) {
        this.a = context;
        this.b = i2;
        this.d = eVar;
        this.c = str;
    }

    public void a(@NonNull List<String> list) {
        e.b("DelayMetCommandHandler", String.format("onAllConstraintsMet for %s", new Object[]{this.c}), new Throwable[0]);
        if (this.d.b().a(this.c)) {
            this.d.c().a(this.c, 600000, this);
        } else {
            c();
        }
    }

    public void onExecuted(@NonNull String str, boolean z, boolean z2) {
        e.b("DelayMetCommandHandler", String.format("onExecuted %s, %s, %s", new Object[]{str, Boolean.valueOf(z), Boolean.valueOf(z2)}), new Throwable[0]);
        c();
        if (this.i) {
            this.d.a((Runnable) new a(this.d, b.a(this.a), this.b));
        }
    }

    public void a(@NonNull String str) {
        e.b("DelayMetCommandHandler", String.format("Exceeded time limits on execution for %s", new Object[]{str}), new Throwable[0]);
        b();
    }

    public void b(@NonNull List<String> list) {
        b();
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public void a() {
        this.h = h.a(this.a, String.format("%s (%s)", new Object[]{this.c, Integer.valueOf(this.b)}));
        e.b("DelayMetCommandHandler", String.format("Acquiring wakelock %s for WorkSpec %s", new Object[]{this.h, this.c}), new Throwable[0]);
        this.h.acquire();
        j b2 = this.d.d().d().workSpecDao().b(this.c);
        this.i = b2.d();
        if (!this.i) {
            e.b("DelayMetCommandHandler", String.format("No constraints for %s", new Object[]{this.c}), new Throwable[0]);
            a(Collections.singletonList(this.c));
            return;
        }
        this.e.a(Collections.singletonList(b2));
    }

    private void b() {
        synchronized (this.f) {
            if (!this.g) {
                e.b("DelayMetCommandHandler", String.format("Stopping work for workspec %s", new Object[]{this.c}), new Throwable[0]);
                this.d.a((Runnable) new a(this.d, b.c(this.a, this.c), this.b));
                if (this.d.b().e(this.c)) {
                    e.b("DelayMetCommandHandler", String.format("WorkSpec %s needs to be rescheduled", new Object[]{this.c}), new Throwable[0]);
                    this.d.a((Runnable) new a(this.d, b.a(this.a, this.c), this.b));
                } else {
                    e.b("DelayMetCommandHandler", String.format("Processor does not have WorkSpec %s. No need to reschedule ", new Object[]{this.c}), new Throwable[0]);
                }
                this.g = true;
            } else {
                e.b("DelayMetCommandHandler", String.format("Already stopped work for %s", new Object[]{this.c}), new Throwable[0]);
            }
        }
    }

    private void c() {
        synchronized (this.f) {
            this.d.c().a(this.c);
            if (this.h != null && this.h.isHeld()) {
                e.b("DelayMetCommandHandler", String.format("Releasing wakelock %s for WorkSpec %s", new Object[]{this.h, this.c}), new Throwable[0]);
                this.h.release();
            }
        }
    }
}
