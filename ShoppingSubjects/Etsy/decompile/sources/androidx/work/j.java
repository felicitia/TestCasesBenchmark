package androidx.work;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import androidx.work.impl.f;
import java.util.Arrays;
import java.util.List;

/* compiled from: WorkManager */
public abstract class j {
    public abstract void a(@NonNull String str);

    public abstract void a(@NonNull List<? extends k> list);

    @NonNull
    public static j a() {
        f b = f.b();
        if (b != null) {
            return b;
        }
        throw new IllegalStateException("WorkManager is not initialized properly.  The most likely cause is that you disabled WorkManagerInitializer in your manifest but forgot to call WorkManager#initialize in your Application#onCreate or a ContentProvider.");
    }

    public static void a(@NonNull Context context, @NonNull a aVar) {
        f.b(context, aVar);
    }

    public final void a(@NonNull k... kVarArr) {
        a(Arrays.asList(kVarArr));
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    protected j() {
    }
}
