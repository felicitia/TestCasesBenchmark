package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.ae;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;

@bu
public final class aqf implements aoo, aqe {
    private final aqd a;
    private final HashSet<SimpleEntry<String, ae<? super aqd>>> b = new HashSet<>();

    public aqf(aqd aqd) {
        this.a = aqd;
    }

    public final void a() {
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            SimpleEntry simpleEntry = (SimpleEntry) it.next();
            String str = "Unregistering eventhandler: ";
            String valueOf = String.valueOf(((ae) simpleEntry.getValue()).toString());
            gv.a(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            this.a.b((String) simpleEntry.getKey(), (ae) simpleEntry.getValue());
        }
        this.b.clear();
    }

    public final void a(String str, ae<? super aqd> aeVar) {
        this.a.a(str, aeVar);
        this.b.add(new SimpleEntry(str, aeVar));
    }

    public final void a(String str, String str2) {
        aop.a((aoo) this, str, str2);
    }

    public final void b(String str, ae<? super aqd> aeVar) {
        this.a.b(str, aeVar);
        this.b.remove(new SimpleEntry(str, aeVar));
    }

    public final void zza(String str, Map map) {
        aop.a((aoo) this, str, map);
    }

    public final void zza(String str, JSONObject jSONObject) {
        aop.b(this, str, jSONObject);
    }

    public final void zzb(String str, JSONObject jSONObject) {
        aop.a((aoo) this, str, jSONObject);
    }

    public final void zzbe(String str) {
        this.a.zzbe(str);
    }
}
