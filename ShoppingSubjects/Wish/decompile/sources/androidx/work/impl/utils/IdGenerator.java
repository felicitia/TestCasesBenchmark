package androidx.work.impl.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class IdGenerator {
    private SharedPreferences mSharedPrefs;

    public IdGenerator(Context context) {
        this.mSharedPrefs = context.getSharedPreferences("androidx.work.util.id", 0);
    }

    public int nextJobSchedulerId() {
        int nextId;
        synchronized (IdGenerator.class) {
            nextId = nextId("next_job_scheduler_id");
        }
        return nextId;
    }

    public int nextAlarmManagerId() {
        int nextId;
        synchronized (IdGenerator.class) {
            nextId = nextId("next_alarm_manager_id");
        }
        return nextId;
    }

    private int nextId(String str) {
        int i = 0;
        int i2 = this.mSharedPrefs.getInt(str, 0);
        if (i2 != Integer.MAX_VALUE) {
            i = i2 + 1;
        }
        this.mSharedPrefs.edit().putInt(str, i).apply();
        return i2;
    }
}
