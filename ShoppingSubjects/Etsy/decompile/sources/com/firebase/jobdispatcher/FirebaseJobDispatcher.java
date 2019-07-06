package com.firebase.jobdispatcher;

import android.support.annotation.NonNull;
import com.firebase.jobdispatcher.k.a;

public final class FirebaseJobDispatcher {
    private final c a;
    private final ValidationEnforcer b;
    private final a c = new a(this.b);

    public static final class ScheduleFailedException extends RuntimeException {
    }

    public FirebaseJobDispatcher(c cVar) {
        this.a = cVar;
        this.b = new ValidationEnforcer(cVar.a());
    }

    public int a(@NonNull k kVar) {
        if (!this.a.b()) {
            return 2;
        }
        return this.a.a(kVar);
    }

    public int a(@NonNull String str) {
        if (!this.a.b()) {
            return 2;
        }
        return this.a.a(str);
    }

    public void b(k kVar) {
        if (a(kVar) != 0) {
            throw new ScheduleFailedException();
        }
    }

    @NonNull
    public a a() {
        return new a(this.b);
    }
}
