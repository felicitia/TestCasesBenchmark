package androidx.work.impl;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver.PendingResult;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import androidx.work.a;
import androidx.work.h;
import androidx.work.impl.utils.ForceStopRunnable;
import androidx.work.impl.utils.a.b;
import androidx.work.impl.utils.a.c;
import androidx.work.impl.utils.e;
import androidx.work.impl.utils.g;
import androidx.work.j;
import androidx.work.k;
import java.util.Arrays;
import java.util.List;

@RestrictTo({Scope.LIBRARY_GROUP})
/* compiled from: WorkManagerImpl */
public class f extends j {
    private static f j;
    private static f k;
    private static final Object l = new Object();
    private Context a;
    private a b;
    private WorkDatabase c;
    private b d;
    private List<c> e;
    private b f;
    private e g;
    private boolean h;
    private PendingResult i;

    @Nullable
    @RestrictTo({Scope.LIBRARY_GROUP})
    public static f b() {
        synchronized (l) {
            if (j != null) {
                f fVar = j;
                return fVar;
            }
            f fVar2 = k;
            return fVar2;
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public static void b(@NonNull Context context, @NonNull a aVar) {
        synchronized (l) {
            if (j == null) {
                Context applicationContext = context.getApplicationContext();
                if (k == null) {
                    k = new f(applicationContext, aVar);
                }
                j = k;
            }
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public f(@NonNull Context context, @NonNull a aVar) {
        this(context, aVar, context.getResources().getBoolean(h.a.workmanager_test_configuration));
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public f(@NonNull Context context, @NonNull a aVar, boolean z) {
        Context applicationContext = context.getApplicationContext();
        this.a = applicationContext;
        this.b = aVar;
        this.c = WorkDatabase.create(applicationContext, z);
        this.d = c.a();
        b bVar = new b(applicationContext, this.b, this.c, f(), aVar.a());
        this.f = bVar;
        this.g = new e(this.a);
        this.h = false;
        androidx.work.e.a(this.b.b());
        this.d.b(new ForceStopRunnable(applicationContext, this));
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public Context c() {
        return this.a;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public WorkDatabase d() {
        return this.c;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @NonNull
    public a e() {
        return this.b;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @NonNull
    public List<c> f() {
        if (this.e == null) {
            this.e = Arrays.asList(new c[]{d.a(this.a, this), new androidx.work.impl.background.a.a(this.a, this)});
        }
        return this.e;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @NonNull
    public b g() {
        return this.f;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @NonNull
    public b h() {
        return this.d;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @NonNull
    public e i() {
        return this.g;
    }

    public void a(@NonNull List<? extends k> list) {
        new e(this, list).i();
    }

    public void a(@NonNull String str) {
        this.d.b(androidx.work.impl.utils.a.a(str, this));
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void b(String str) {
        a(str, null);
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void a(String str, Extras.a aVar) {
        this.d.b(new androidx.work.impl.utils.f(this, str, aVar));
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void c(String str) {
        this.d.b(new g(this, str));
    }

    @TargetApi(23)
    @RestrictTo({Scope.LIBRARY_GROUP})
    public void j() {
        if (VERSION.SDK_INT >= 23) {
            androidx.work.impl.background.systemjob.b.a(c());
        }
        d().workSpecDao().b();
        d.a(e(), d(), f());
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void k() {
        synchronized (l) {
            this.h = true;
            if (this.i != null) {
                this.i.finish();
                this.i = null;
            }
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void a(@NonNull PendingResult pendingResult) {
        synchronized (l) {
            this.i = pendingResult;
            if (this.h) {
                this.i.finish();
                this.i = null;
            }
        }
    }
}
