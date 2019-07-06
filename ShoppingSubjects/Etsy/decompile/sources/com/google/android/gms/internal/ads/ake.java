package com.google.android.gms.internal.ads;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import org.json.JSONObject;

final class ake extends akb<Long> {
    ake(int i, String str, Long l) {
        super(i, str, l, null);
    }

    public final /* synthetic */ Object a(SharedPreferences sharedPreferences) {
        return Long.valueOf(sharedPreferences.getLong(a(), ((Long) b()).longValue()));
    }

    public final /* synthetic */ Object a(JSONObject jSONObject) {
        return Long.valueOf(jSONObject.optLong(a(), ((Long) b()).longValue()));
    }

    public final /* synthetic */ void a(Editor editor, Object obj) {
        editor.putLong(a(), ((Long) obj).longValue());
    }
}
