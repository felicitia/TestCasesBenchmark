package androidx.work.impl.utils;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.WorkerThread;
import androidx.work.State;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.b.k;
import androidx.work.impl.c;
import androidx.work.impl.d;
import androidx.work.impl.f;

@RestrictTo({Scope.LIBRARY_GROUP})
/* compiled from: CancelWorkRunnable */
public abstract class a implements Runnable {
    /* access modifiers changed from: 0000 */
    public void a(f fVar, String str) {
        a(fVar.d(), str);
        fVar.g().c(str);
        for (c a : fVar.f()) {
            a.a(str);
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(f fVar) {
        d.a(fVar.e(), fVar.d(), fVar.f());
    }

    private void a(WorkDatabase workDatabase, String str) {
        k workSpecDao = workDatabase.workSpecDao();
        for (String a : workDatabase.dependencyDao().b(str)) {
            a(workDatabase, a);
        }
        State f = workSpecDao.f(str);
        if (f != State.SUCCEEDED && f != State.FAILED) {
            workSpecDao.a(State.CANCELLED, str);
        }
    }

    public static Runnable a(@NonNull final String str, @NonNull final f fVar) {
        return new a() {
            /* JADX INFO: finally extract failed */
            @WorkerThread
            public void run() {
                WorkDatabase d = fVar.d();
                d.beginTransaction();
                try {
                    for (String a2 : d.workSpecDao().h(str)) {
                        a(fVar, a2);
                    }
                    d.setTransactionSuccessful();
                    d.endTransaction();
                    a(fVar);
                } catch (Throwable th) {
                    d.endTransaction();
                    throw th;
                }
            }
        };
    }

    public static Runnable a(@NonNull final String str, @NonNull final f fVar, final boolean z) {
        return new a() {
            /* JADX INFO: finally extract failed */
            @WorkerThread
            public void run() {
                WorkDatabase d = fVar.d();
                d.beginTransaction();
                try {
                    for (String a2 : d.workSpecDao().i(str)) {
                        a(fVar, a2);
                    }
                    d.setTransactionSuccessful();
                    d.endTransaction();
                    if (z) {
                        a(fVar);
                    }
                } catch (Throwable th) {
                    d.endTransaction();
                    throw th;
                }
            }
        };
    }
}
