package androidx.work;

import android.content.Context;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.WorkerThread;
import androidx.work.impl.Extras;
import java.util.UUID;

public abstract class Worker {
    @NonNull
    private Context a;
    @NonNull
    private UUID b;
    @NonNull
    private Extras c;
    @NonNull
    private Data d = Data.a;
    private volatile boolean e;
    private volatile boolean f;

    public enum Result {
        SUCCESS,
        FAILURE,
        RETRY
    }

    public void b(boolean z) {
    }

    @WorkerThread
    @NonNull
    public abstract Result d();

    @NonNull
    public final Context a() {
        return this.a;
    }

    @NonNull
    public final UUID b() {
        return this.b;
    }

    @NonNull
    public final Data c() {
        return this.c.a();
    }

    public final void a(@NonNull Data data) {
        this.d = data;
    }

    @NonNull
    public final Data e() {
        return this.d;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public final void a(boolean z) {
        this.e = true;
        this.f = z;
        b(z);
    }

    @Keep
    private void internalInit(@NonNull Context context, @NonNull UUID uuid, @NonNull Extras extras) {
        this.a = context;
        this.b = uuid;
        this.c = extras;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @NonNull
    public Extras f() {
        return this.c;
    }
}
