package androidx.work.impl.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.VisibleForTesting;

@RestrictTo({Scope.LIBRARY_GROUP})
/* compiled from: Preferences */
public class e {
    private SharedPreferences a;

    public e(@NonNull Context context) {
        this(context.getSharedPreferences("androidx.work.util.preferences", 0));
    }

    @VisibleForTesting
    public e(@NonNull SharedPreferences sharedPreferences) {
        this.a = sharedPreferences;
    }

    public boolean a() {
        return this.a.getBoolean("reschedule_needed", false);
    }

    public void a(boolean z) {
        this.a.edit().putBoolean("reschedule_needed", z).apply();
    }
}
