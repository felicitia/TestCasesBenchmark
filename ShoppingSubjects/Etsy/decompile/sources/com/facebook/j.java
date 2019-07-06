package com.facebook;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import com.facebook.internal.aa;
import com.facebook.internal.z;

/* compiled from: ProfileManager */
public final class j {
    private static volatile j a;
    private final LocalBroadcastManager b;
    private final i c;
    private Profile d;

    j(LocalBroadcastManager localBroadcastManager, i iVar) {
        aa.a((Object) localBroadcastManager, "localBroadcastManager");
        aa.a((Object) iVar, "profileCache");
        this.b = localBroadcastManager;
        this.c = iVar;
    }

    static j a() {
        if (a == null) {
            synchronized (j.class) {
                if (a == null) {
                    a = new j(LocalBroadcastManager.getInstance(f.f()), new i());
                }
            }
        }
        return a;
    }

    /* access modifiers changed from: 0000 */
    public Profile b() {
        return this.d;
    }

    /* access modifiers changed from: 0000 */
    public boolean c() {
        Profile a2 = this.c.a();
        if (a2 == null) {
            return false;
        }
        a(a2, false);
        return true;
    }

    /* access modifiers changed from: 0000 */
    public void a(@Nullable Profile profile) {
        a(profile, true);
    }

    private void a(@Nullable Profile profile, boolean z) {
        Profile profile2 = this.d;
        this.d = profile;
        if (z) {
            if (profile != null) {
                this.c.a(profile);
            } else {
                this.c.b();
            }
        }
        if (!z.a(profile2, profile)) {
            a(profile2, profile);
        }
    }

    private void a(Profile profile, Profile profile2) {
        Intent intent = new Intent("com.facebook.sdk.ACTION_CURRENT_PROFILE_CHANGED");
        intent.putExtra("com.facebook.sdk.EXTRA_OLD_PROFILE", profile);
        intent.putExtra("com.facebook.sdk.EXTRA_NEW_PROFILE", profile2);
        this.b.sendBroadcast(intent);
    }
}
