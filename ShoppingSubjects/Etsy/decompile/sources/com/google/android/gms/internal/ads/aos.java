package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.support.annotation.GuardedBy;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.ads.internal.gmsg.ae;
import com.google.android.gms.common.util.Predicate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@bu
public abstract class aos<ReferenceT> {
    @GuardedBy("this")
    private final Map<String, CopyOnWriteArrayList<ae<? super ReferenceT>>> a = new HashMap();

    private final synchronized void a(String str, Map<String, String> map) {
        if (gv.a(2)) {
            String str2 = "Received GMSG: ";
            String valueOf = String.valueOf(str);
            gv.a(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            for (String str3 : map.keySet()) {
                String str4 = (String) map.get(str3);
                StringBuilder sb = new StringBuilder(4 + String.valueOf(str3).length() + String.valueOf(str4).length());
                sb.append("  ");
                sb.append(str3);
                sb.append(": ");
                sb.append(str4);
                gv.a(sb.toString());
            }
        }
        CopyOnWriteArrayList copyOnWriteArrayList = (CopyOnWriteArrayList) this.a.get(str);
        if (copyOnWriteArrayList != null) {
            Iterator it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                kz.a.execute(new aot(this, (ae) it.next(), map));
            }
        }
    }

    public final synchronized void a(String str, ae<? super ReferenceT> aeVar) {
        CopyOnWriteArrayList copyOnWriteArrayList = (CopyOnWriteArrayList) this.a.get(str);
        if (copyOnWriteArrayList == null) {
            copyOnWriteArrayList = new CopyOnWriteArrayList();
            this.a.put(str, copyOnWriteArrayList);
        }
        copyOnWriteArrayList.add(aeVar);
    }

    public final synchronized void a(String str, Predicate<ae<? super ReferenceT>> predicate) {
        CopyOnWriteArrayList copyOnWriteArrayList = (CopyOnWriteArrayList) this.a.get(str);
        if (copyOnWriteArrayList != null) {
            ArrayList arrayList = new ArrayList();
            Iterator it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                ae aeVar = (ae) it.next();
                if (predicate.apply(aeVar)) {
                    arrayList.add(aeVar);
                }
            }
            copyOnWriteArrayList.removeAll(arrayList);
        }
    }

    public final boolean a(Uri uri) {
        if (!"gmsg".equalsIgnoreCase(uri.getScheme()) || !"mobileads.google.com".equalsIgnoreCase(uri.getHost())) {
            return false;
        }
        String path = uri.getPath();
        ao.e();
        a(path, hd.a(uri));
        return true;
    }

    public final synchronized void b(String str, ae<? super ReferenceT> aeVar) {
        CopyOnWriteArrayList copyOnWriteArrayList = (CopyOnWriteArrayList) this.a.get(str);
        if (copyOnWriteArrayList != null) {
            copyOnWriteArrayList.remove(aeVar);
        }
    }

    public synchronized void d() {
        this.a.clear();
    }

    public abstract ReferenceT f();
}
