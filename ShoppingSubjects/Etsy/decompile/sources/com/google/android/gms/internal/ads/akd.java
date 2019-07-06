package com.google.android.gms.internal.ads;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import org.json.JSONObject;

final class akd extends akb<Integer> {
    akd(int i, String str, Integer num) {
        super(i, str, num, null);
    }

    public final /* synthetic */ Object a(SharedPreferences sharedPreferences) {
        return Integer.valueOf(sharedPreferences.getInt(a(), ((Integer) b()).intValue()));
    }

    public final /* synthetic */ Object a(JSONObject jSONObject) {
        return Integer.valueOf(jSONObject.optInt(a(), ((Integer) b()).intValue()));
    }

    public final /* synthetic */ void a(Editor editor, Object obj) {
        editor.putInt(a(), ((Integer) obj).intValue());
    }
}
