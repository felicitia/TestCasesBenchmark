package androidx.work;

import android.os.Build.VERSION;
import android.support.annotation.NonNull;

/* compiled from: OneTimeWorkRequest */
public final class f extends k {

    /* compiled from: OneTimeWorkRequest */
    public static final class a extends androidx.work.k.a<a, f> {
        /* access modifiers changed from: 0000 */
        @NonNull
        /* renamed from: b */
        public a c() {
            return this;
        }

        public a(@NonNull Class<? extends Worker> cls) {
            super(cls);
        }

        /* access modifiers changed from: 0000 */
        @NonNull
        /* renamed from: a */
        public f d() {
            if (!this.a || VERSION.SDK_INT < 23 || !this.c.j.c()) {
                return new f(this);
            }
            throw new IllegalArgumentException("Cannot set backoff criteria on an idle mode job");
        }
    }

    f(a aVar) {
        super(aVar.b, aVar.c, aVar.d);
    }
}
