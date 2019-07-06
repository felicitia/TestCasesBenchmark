package com.google.android.gms.internal.ads;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import org.json.JSONObject;

final class akf extends akb<Float> {
    akf(int i, String str, Float f) {
        super(i, str, f, null);
    }

    public final /* synthetic */ Object a(SharedPreferences sharedPreferences) {
        return Float.valueOf(sharedPreferences.getFloat(a(), ((Float) b()).floatValue()));
    }

    public final /* synthetic */ Object a(JSONObject jSONObject) {
        return Float.valueOf((float) jSONObject.optDouble(a(), (double) ((Float) b()).floatValue()));
    }

    public final /* synthetic */ void a(Editor editor, Object obj) {
        editor.putFloat(a(), ((Float) obj).floatValue());
    }
}
