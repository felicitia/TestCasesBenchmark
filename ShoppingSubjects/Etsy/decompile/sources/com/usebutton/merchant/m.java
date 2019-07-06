package com.usebutton.merchant;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

/* compiled from: PersistenceManagerImpl */
final class m implements l {
    private static l a;
    private final SharedPreferences b;

    static l a(Context context) {
        if (a == null) {
            a = new m(context);
        }
        return a;
    }

    @VisibleForTesting
    m(Context context) {
        this.b = context.getSharedPreferences("button_shared_preferences", 0);
    }

    public void a(String str) {
        this.b.edit().putString("btn_source_token", str).apply();
    }

    @Nullable
    public String a() {
        return this.b.getString("btn_source_token", null);
    }

    public boolean b() {
        return this.b.getBoolean("btn_checked_deferred_deep_link", false);
    }

    public void a(boolean z) {
        this.b.edit().putBoolean("btn_checked_deferred_deep_link", z).apply();
    }
}
