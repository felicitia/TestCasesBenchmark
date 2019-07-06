package androidx.work.impl.workers;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;
import androidx.work.Worker;
import androidx.work.Worker.Result;
import androidx.work.e;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.a.c;
import androidx.work.impl.a.d;
import androidx.work.impl.b.j;
import androidx.work.impl.f;
import androidx.work.impl.g;
import java.util.Collections;
import java.util.List;

@RestrictTo({Scope.LIBRARY_GROUP})
public class ConstraintTrackingWorker extends Worker implements c {
    @Nullable
    private Worker a;
    private final Object b = new Object();
    private boolean c = false;

    public void a(@NonNull List<String> list) {
    }

    @NonNull
    public Result d() {
        String a2 = c().a("androidx.work.impl.workers.ConstraintTrackingWorker.ARGUMENT_CLASS_NAME");
        if (TextUtils.isEmpty(a2)) {
            e.b("ConstraintTrkngWrkr", "No worker to delegate to.", new Throwable[0]);
            return Result.FAILURE;
        }
        this.a = g.a(a(), a2, b(), f());
        if (this.a == null) {
            e.b("ConstraintTrkngWrkr", "No worker to delegate to.", new Throwable[0]);
            return Result.FAILURE;
        }
        j b2 = g().workSpecDao().b(b().toString());
        if (b2 == null) {
            return Result.FAILURE;
        }
        d dVar = new d(a(), this);
        dVar.a(Collections.singletonList(b2));
        if (dVar.a(b().toString())) {
            e.b("ConstraintTrkngWrkr", String.format("Constraints met for delegate %s", new Object[]{a2}), new Throwable[0]);
            try {
                Result d = this.a.d();
                synchronized (this.b) {
                    if (this.c) {
                        Result result = Result.RETRY;
                        return result;
                    }
                    a(this.a.e());
                    return d;
                }
            } catch (Throwable th) {
                e.b("ConstraintTrkngWrkr", String.format("Delegated worker %s threw a runtime exception.", new Object[]{a2}), th);
                synchronized (this.b) {
                    if (!this.c) {
                        return Result.FAILURE;
                    }
                    e.b("ConstraintTrkngWrkr", "Constraints were unmet, Retrying.", new Throwable[0]);
                    return Result.RETRY;
                }
            }
        } else {
            e.b("ConstraintTrkngWrkr", String.format("Constraints not met for delegate %s. Requesting retry.", new Object[]{a2}), new Throwable[0]);
            return Result.RETRY;
        }
    }

    @VisibleForTesting
    public WorkDatabase g() {
        return f.b().d();
    }

    public void b(@NonNull List<String> list) {
        e.b("ConstraintTrkngWrkr", String.format("Constraints changed for %s", new Object[]{list}), new Throwable[0]);
        synchronized (this.b) {
            this.c = true;
        }
    }
}
