package com.google.android.gms.internal.ads;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import org.json.JSONObject;

final class akg extends akb<String> {
    akg(int i, String str, String str2) {
        super(i, str, str2, null);
    }

    public final /* synthetic */ Object a(SharedPreferences sharedPreferences) {
        return sharedPreferences.getString(a(), (String) b());
    }

    public final /* synthetic */ Object a(JSONObject jSONObject) {
        return jSONObject.optString(a(), (String) b());
    }

    public final /* synthetic */ void a(Editor editor, Object obj) {
        editor.putString(a(), (String) obj);
    }
}
