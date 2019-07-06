package androidx.work;

import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import java.util.concurrent.TimeUnit;

/* compiled from: PeriodicWorkRequest */
public final class g extends k {

    /* compiled from: PeriodicWorkRequest */
    public static final class a extends androidx.work.k.a<a, g> {
        /* access modifiers changed from: 0000 */
        @NonNull
        /* renamed from: b */
        public a c() {
            return this;
        }

        public a(@NonNull Class<? extends Worker> cls, long j, @NonNull TimeUnit timeUnit) {
            super(cls);
            this.c.a(timeUnit.toMillis(j));
        }

        /* access modifiers changed from: 0000 */
        @NonNull
        /* renamed from: a */
        public g d() {
            if (!this.a || VERSION.SDK_INT < 23 || !this.c.j.c()) {
                return new g(this);
            }
            throw new IllegalArgumentException("Cannot set backoff criteria on an idle mode job");
        }
    }

    g(a aVar) {
        super(aVar.b, aVar.c, aVar.d);
    }
}
