package com.google.android.gms.internal.ads;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import org.json.JSONObject;

final class akc extends akb<Boolean> {
    akc(int i, String str, Boolean bool) {
        super(i, str, bool, null);
    }

    public final /* synthetic */ Object a(SharedPreferences sharedPreferences) {
        return Boolean.valueOf(sharedPreferences.getBoolean(a(), ((Boolean) b()).booleanValue()));
    }

    public final /* synthetic */ Object a(JSONObject jSONObject) {
        return Boolean.valueOf(jSONObject.optBoolean(a(), ((Boolean) b()).booleanValue()));
    }

    public final /* synthetic */ void a(Editor editor, Object obj) {
        editor.putBoolean(a(), ((Boolean) obj).booleanValue());
    }
}
