package androidx.work.impl.background.systemalarm;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import androidx.work.e;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RestrictTo({Scope.LIBRARY_GROUP})
/* compiled from: WorkTimer */
class g {
    final Map<String, b> a = new HashMap();
    final Map<String, a> b = new HashMap();
    final Object c = new Object();
    private final ScheduledExecutorService d = Executors.newSingleThreadScheduledExecutor();

    /* compiled from: WorkTimer */
    interface a {
        void a(@NonNull String str);
    }

    /* compiled from: WorkTimer */
    static class b implements Runnable {
        private final g a;
        private final String b;

        b(@NonNull g gVar, @NonNull String str) {
            this.a = gVar;
            this.b = str;
        }

        public void run() {
            synchronized (this.a.c) {
                if (this.a.a.containsKey(this.b)) {
                    this.a.a.remove(this.b);
                    a aVar = (a) this.a.b.remove(this.b);
                    if (aVar != null) {
                        aVar.a(this.b);
                    }
                } else {
                    e.b("WrkTimerRunnable", String.format("Timer with %s is already marked as complete.", new Object[]{this.b}), new Throwable[0]);
                }
            }
        }
    }

    g() {
    }

    /* access modifiers changed from: 0000 */
    public void a(@NonNull String str, long j, @NonNull a aVar) {
        synchronized (this.c) {
            e.b("WorkTimer", String.format("Starting timer for %s", new Object[]{str}), new Throwable[0]);
            a(str);
            b bVar = new b(this, str);
            this.a.put(str, bVar);
            this.b.put(str, aVar);
            this.d.schedule(bVar, j, TimeUnit.MILLISECONDS);
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(@NonNull String str) {
        synchronized (this.c) {
            if (this.a.containsKey(str)) {
                e.b("WorkTimer", String.format("Stopping timer for %s", new Object[]{str}), new Throwable[0]);
                this.a.remove(str);
                this.b.remove(str);
            }
        }
    }
}
