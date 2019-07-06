package androidx.work.impl.background.systemalarm;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager.WakeLock;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;
import androidx.work.impl.f;
import androidx.work.impl.utils.h;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestrictTo({Scope.LIBRARY_GROUP})
/* compiled from: SystemAlarmDispatcher */
public class e implements androidx.work.impl.a {
    final Context a;
    final b b;
    final List<Intent> c;
    private final g d;
    private final androidx.work.impl.b e;
    private final f f;
    private final Handler g;
    private final ExecutorService h;
    @Nullable
    private c i;

    /* compiled from: SystemAlarmDispatcher */
    static class a implements Runnable {
        private final e a;
        private final Intent b;
        private final int c;

        a(@NonNull e eVar, @NonNull Intent intent, int i) {
            this.a = eVar;
            this.b = intent;
            this.c = i;
        }

        public void run() {
            this.a.a(this.b, this.c);
        }
    }

    /* compiled from: SystemAlarmDispatcher */
    static class b implements Runnable {
        private final e a;

        b(@NonNull e eVar) {
            this.a = eVar;
        }

        public void run() {
            this.a.e();
        }
    }

    /* compiled from: SystemAlarmDispatcher */
    interface c {
        void onAllCommandsCompleted();
    }

    e(@NonNull Context context) {
        this(context, null, null);
    }

    @VisibleForTesting
    e(@NonNull Context context, @Nullable androidx.work.impl.b bVar, @Nullable f fVar) {
        this.a = context.getApplicationContext();
        this.b = new b(this.a);
        this.d = new g();
        if (fVar == null) {
            fVar = f.b();
        }
        this.f = fVar;
        if (bVar == null) {
            bVar = this.f.g();
        }
        this.e = bVar;
        this.e.a((androidx.work.impl.a) this);
        this.c = new ArrayList();
        this.g = new Handler(Looper.getMainLooper());
        this.h = Executors.newSingleThreadExecutor();
    }

    /* access modifiers changed from: 0000 */
    public void a() {
        this.e.b((androidx.work.impl.a) this);
        this.i = null;
    }

    public void onExecuted(@NonNull String str, boolean z, boolean z2) {
        a((Runnable) new a(this, b.a(this.a, str, z, z2), 0));
    }

    @MainThread
    public boolean a(@NonNull Intent intent, int i2) {
        g();
        String action = intent.getAction();
        if (TextUtils.isEmpty(action)) {
            androidx.work.e.d("SystemAlarmDispatcher", "Unknown command. Ignoring", new Throwable[0]);
            return false;
        } else if ("ACTION_CONSTRAINTS_CHANGED".equals(action) && a("ACTION_CONSTRAINTS_CHANGED")) {
            return false;
        } else {
            intent.putExtra("KEY_START_ID", i2);
            synchronized (this.c) {
                this.c.add(intent);
            }
            f();
            return true;
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(@NonNull c cVar) {
        if (this.i != null) {
            androidx.work.e.e("SystemAlarmDispatcher", "A completion listener for SystemAlarmDispatcher already exists.", new Throwable[0]);
        } else {
            this.i = cVar;
        }
    }

    /* access modifiers changed from: 0000 */
    public androidx.work.impl.b b() {
        return this.e;
    }

    /* access modifiers changed from: 0000 */
    public g c() {
        return this.d;
    }

    /* access modifiers changed from: 0000 */
    public f d() {
        return this.f;
    }

    /* access modifiers changed from: 0000 */
    public void a(@NonNull Runnable runnable) {
        this.g.post(runnable);
    }

    /* access modifiers changed from: 0000 */
    @MainThread
    public void e() {
        g();
        synchronized (this.c) {
            if (!this.b.a() && this.c.isEmpty()) {
                androidx.work.e.b("SystemAlarmDispatcher", "No more commands & intents.", new Throwable[0]);
                if (this.i != null) {
                    this.i.onAllCommandsCompleted();
                }
            }
        }
    }

    @MainThread
    private void f() {
        g();
        WakeLock a2 = h.a(this.a, "ProcessCommand");
        try {
            a2.acquire();
            this.h.submit(new Runnable() {
                public void run() {
                    Intent intent;
                    synchronized (e.this.c) {
                        intent = (Intent) e.this.c.get(0);
                    }
                    if (intent != null) {
                        String action = intent.getAction();
                        int intExtra = intent.getIntExtra("KEY_START_ID", 0);
                        androidx.work.e.b("SystemAlarmDispatcher", String.format("Processing command %s, %s", new Object[]{intent, Integer.valueOf(intExtra)}), new Throwable[0]);
                        WakeLock a2 = h.a(e.this.a, String.format("%s (%s)", new Object[]{action, Integer.valueOf(intExtra)}));
                        try {
                            androidx.work.e.b("SystemAlarmDispatcher", String.format("Acquiring operation wake lock (%s) %s", new Object[]{action, a2}), new Throwable[0]);
                            a2.acquire();
                            e.this.b.a(intent, intExtra, e.this);
                            synchronized (e.this.c) {
                                e.this.c.remove(0);
                            }
                            androidx.work.e.b("SystemAlarmDispatcher", String.format("Releasing operation wake lock (%s) %s", new Object[]{action, a2}), new Throwable[0]);
                            a2.release();
                            e.this.a((Runnable) new b(e.this));
                        } catch (Throwable th) {
                            synchronized (e.this.c) {
                                e.this.c.remove(0);
                                androidx.work.e.b("SystemAlarmDispatcher", String.format("Releasing operation wake lock (%s) %s", new Object[]{action, a2}), new Throwable[0]);
                                a2.release();
                                e.this.a((Runnable) new b(e.this));
                                throw th;
                            }
                        }
                    }
                }
            });
        } finally {
            a2.release();
        }
    }

    @MainThread
    private boolean a(@NonNull String str) {
        g();
        synchronized (this.c) {
            for (Intent action : this.c) {
                if (str.equals(action.getAction())) {
                    return true;
                }
            }
            return false;
        }
    }

    private void g() {
        if (this.g.getLooper().getThread() != Thread.currentThread()) {
            throw new IllegalStateException("Needs to be invoked on the main thread.");
        }
    }
}
