package androidx.work.impl.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;

@RestrictTo({Scope.LIBRARY_GROUP})
/* compiled from: IdGenerator */
public class c {
    private SharedPreferences a;

    public c(Context context) {
        this.a = context.getSharedPreferences("androidx.work.util.id", 0);
    }

    public int a(int i, int i2) {
        synchronized (c.class) {
            int a2 = a("next_job_scheduler_id");
            if (a2 >= i) {
                if (a2 <= i2) {
                    i = a2;
                }
            }
            a("next_job_scheduler_id", i + 1);
        }
        return i;
    }

    public int a() {
        int a2;
        synchronized (c.class) {
            a2 = a("next_alarm_manager_id");
        }
        return a2;
    }

    private int a(String str) {
        int i = 0;
        int i2 = this.a.getInt(str, 0);
        if (i2 != Integer.MAX_VALUE) {
            i = i2 + 1;
        }
        a(str, i);
        return i2;
    }

    private void a(String str, int i) {
        this.a.edit().putInt(str, i).apply();
    }
}
