package androidx.work.impl.utils;

import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import androidx.work.State;
import androidx.work.e;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.b.k;
import androidx.work.impl.f;

@RestrictTo({Scope.LIBRARY_GROUP})
/* compiled from: StopWorkRunnable */
public class g implements Runnable {
    private f a;
    private String b;

    public g(f fVar, String str) {
        this.a = fVar;
        this.b = str;
    }

    public void run() {
        WorkDatabase d = this.a.d();
        k workSpecDao = d.workSpecDao();
        d.beginTransaction();
        try {
            if (workSpecDao.f(this.b) == State.RUNNING) {
                workSpecDao.a(State.ENQUEUED, this.b);
            }
            e.b("StopWorkRunnable", String.format("StopWorkRunnable for %s; Processor.stopWork = %s", new Object[]{this.b, Boolean.valueOf(this.a.g().b(this.b))}), new Throwable[0]);
            d.setTransactionSuccessful();
        } finally {
            d.endTransaction();
        }
    }
}
