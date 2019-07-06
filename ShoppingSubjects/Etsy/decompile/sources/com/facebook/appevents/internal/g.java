package com.facebook.appevents.internal;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import com.facebook.f;
import java.util.UUID;

/* compiled from: SessionInfo */
class g {
    private Long a;
    private Long b;
    private int c;
    private Long d;
    private i e;
    private UUID f;

    public g(Long l, Long l2) {
        this(l, l2, UUID.randomUUID());
    }

    public g(Long l, Long l2, UUID uuid) {
        this.a = l;
        this.b = l2;
        this.f = uuid;
    }

    public static g a() {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(f.f());
        long j = defaultSharedPreferences.getLong("com.facebook.appevents.SessionInfo.sessionStartTime", 0);
        long j2 = defaultSharedPreferences.getLong("com.facebook.appevents.SessionInfo.sessionEndTime", 0);
        String string = defaultSharedPreferences.getString("com.facebook.appevents.SessionInfo.sessionId", null);
        if (j == 0 || j2 == 0 || string == null) {
            return null;
        }
        g gVar = new g(Long.valueOf(j), Long.valueOf(j2));
        gVar.c = defaultSharedPreferences.getInt("com.facebook.appevents.SessionInfo.interruptionCount", 0);
        gVar.e = i.a();
        gVar.d = Long.valueOf(System.currentTimeMillis());
        gVar.f = UUID.fromString(string);
        return gVar;
    }

    public static void b() {
        Editor edit = PreferenceManager.getDefaultSharedPreferences(f.f()).edit();
        edit.remove("com.facebook.appevents.SessionInfo.sessionStartTime");
        edit.remove("com.facebook.appevents.SessionInfo.sessionEndTime");
        edit.remove("com.facebook.appevents.SessionInfo.interruptionCount");
        edit.remove("com.facebook.appevents.SessionInfo.sessionId");
        edit.apply();
        i.b();
    }

    public Long c() {
        return this.b;
    }

    public void a(Long l) {
        this.b = l;
    }

    public int d() {
        return this.c;
    }

    public void e() {
        this.c++;
    }

    public long f() {
        if (this.d == null) {
            return 0;
        }
        return this.d.longValue();
    }

    public UUID g() {
        return this.f;
    }

    public long h() {
        if (this.a == null || this.b == null) {
            return 0;
        }
        return this.b.longValue() - this.a.longValue();
    }

    public i i() {
        return this.e;
    }

    public void a(i iVar) {
        this.e = iVar;
    }

    public void j() {
        Editor edit = PreferenceManager.getDefaultSharedPreferences(f.f()).edit();
        edit.putLong("com.facebook.appevents.SessionInfo.sessionStartTime", this.a.longValue());
        edit.putLong("com.facebook.appevents.SessionInfo.sessionEndTime", this.b.longValue());
        edit.putInt("com.facebook.appevents.SessionInfo.interruptionCount", this.c);
        edit.putString("com.facebook.appevents.SessionInfo.sessionId", this.f.toString());
        edit.apply();
        if (this.e != null) {
            this.e.c();
        }
    }
}
