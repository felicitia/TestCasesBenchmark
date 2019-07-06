package org.scribe.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.scribe.e.b;
import org.scribe.e.c;

/* compiled from: ParameterList */
public class e {
    private final List<d> a;

    public e() {
        this.a = new ArrayList();
    }

    e(List<d> list) {
        this.a = new ArrayList(list);
    }

    public e(Map<String, String> map) {
        this();
        for (Entry entry : map.entrySet()) {
            this.a.add(new d((String) entry.getKey(), (String) entry.getValue()));
        }
    }

    public void a(String str, String str2) {
        this.a.add(new d(str, str2));
    }

    public String a(String str) {
        c.a((Object) str, "Cannot append to null URL");
        String b = b();
        if (b.equals("")) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str.indexOf(63) != -1 ? "&" : Character.valueOf('?'));
        String sb2 = sb.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(sb2);
        sb3.append(b);
        return sb3.toString();
    }

    public String a() {
        return b.a(b());
    }

    public String b() {
        if (this.a.size() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (d dVar : this.a) {
            sb.append('&');
            sb.append(dVar.a());
        }
        return sb.toString().substring(1);
    }

    public void a(e eVar) {
        this.a.addAll(eVar.a);
    }

    public void b(String str) {
        if (str != null && str.length() > 0) {
            for (String split : str.split("&")) {
                String[] split2 = split.split("=");
                this.a.add(new d(b.b(split2[0]), split2.length > 1 ? b.b(split2[1]) : ""));
            }
        }
    }

    public e c() {
        e eVar = new e(this.a);
        Collections.sort(eVar.a);
        return eVar;
    }
}
