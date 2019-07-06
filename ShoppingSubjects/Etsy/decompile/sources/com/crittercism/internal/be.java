package com.crittercism.internal;

import android.content.Context;
import android.content.SharedPreferences;
import com.crittercism.internal.ay.a;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class be implements ay<bd> {
    private SharedPreferences a;
    private final List<a> b = new LinkedList();

    public be(Context context, String str) {
        StringBuilder sb = new StringBuilder("com.crittercism.");
        sb.append(str);
        sb.append(".usermetadata.v2");
        this.a = context.getSharedPreferences(sb.toString(), 0);
    }

    private boolean e() {
        return this.a.getBoolean("dirty", false);
    }

    private boolean f() {
        String str = null;
        long j = Long.MAX_VALUE;
        for (String str2 : this.a.getAll().keySet()) {
            if (str2.startsWith("__timestamp_") && (str == null || j > this.a.getLong(str2, j))) {
                j = this.a.getLong(str2, j);
                str = str2;
            }
        }
        if (str == null) {
            return false;
        }
        return this.a.edit().remove(str).remove(str.substring(12)).commit();
    }

    /* access modifiers changed from: private */
    public boolean a(bd bdVar) {
        String string = this.a.getString(bdVar.a, null);
        String str = bdVar.a;
        String str2 = bdVar.a;
        StringBuilder sb = new StringBuilder("__timestamp_");
        sb.append(str2);
        String sb2 = sb.toString();
        if (bdVar.b.equals(string)) {
            this.a.edit().putLong(sb2, System.nanoTime()).commit();
            return true;
        } else if ((this.a.getAll().size() - 1) / 2 >= 25 && !this.a.contains(bdVar.a) && !f()) {
            return false;
        } else {
            boolean commit = this.a.edit().putString(str, bdVar.b).putLong(sb2, System.nanoTime()).putBoolean("dirty", true).commit();
            if (commit) {
                for (a a2 : this.b) {
                    a2.a();
                }
            }
            return commit;
        }
    }

    public final boolean a(JSONObject jSONObject) {
        Iterator keys = jSONObject.keys();
        boolean z = true;
        while (keys.hasNext()) {
            try {
                String str = (String) keys.next();
                z &= a(new bd(str, jSONObject.get(str).toString()));
            } catch (JSONException unused) {
            }
        }
        return z;
    }

    public final void a(String str) {
        throw new UnsupportedOperationException();
    }

    public final JSONArray a() {
        JSONArray jSONArray = new JSONArray();
        Iterator it = b().iterator();
        if (!it.hasNext()) {
            return jSONArray;
        }
        it.next();
        throw new UnsupportedOperationException();
    }

    public final List<bd> b() {
        Map all = this.a.getAll();
        LinkedList linkedList = new LinkedList();
        for (String str : all.keySet()) {
            if (!str.startsWith("__timestamp_") && !str.equals("dirty")) {
                linkedList.add(new bd(str, (String) all.get(str)));
            }
        }
        return linkedList;
    }

    public final List<bd> d() {
        if (e()) {
            return b();
        }
        return new LinkedList();
    }

    public final void a(a aVar) {
        synchronized (this.b) {
            this.b.add(aVar);
        }
        if (e()) {
            aVar.a();
        }
    }

    public final boolean c() {
        return e();
    }

    public final void a(List<bd> list) {
        this.a.edit().putBoolean("dirty", false).commit();
    }
}
