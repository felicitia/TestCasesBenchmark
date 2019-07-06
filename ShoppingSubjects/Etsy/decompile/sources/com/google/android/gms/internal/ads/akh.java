package com.google.android.gms.internal.ads;

import android.content.SharedPreferences.Editor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.json.JSONObject;

@bu
public final class akh {
    private final Collection<akb<?>> a = new ArrayList();
    private final Collection<akb<String>> b = new ArrayList();
    private final Collection<akb<String>> c = new ArrayList();

    public final List<String> a() {
        ArrayList arrayList = new ArrayList();
        for (akb a2 : this.b) {
            String str = (String) ajh.f().a(a2);
            if (str != null) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    public final void a(Editor editor, int i, JSONObject jSONObject) {
        for (akb akb : this.a) {
            if (akb.c() == 1) {
                akb.a(editor, akb.a(jSONObject));
            }
        }
    }

    public final void a(akb akb) {
        this.a.add(akb);
    }

    public final List<String> b() {
        List<String> a2 = a();
        for (akb a3 : this.c) {
            String str = (String) ajh.f().a(a3);
            if (str != null) {
                a2.add(str);
            }
        }
        return a2;
    }

    public final void b(akb<String> akb) {
        this.b.add(akb);
    }

    public final void c(akb<String> akb) {
        this.c.add(akb);
    }
}
