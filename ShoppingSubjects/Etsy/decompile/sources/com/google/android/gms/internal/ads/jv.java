package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

final class jv {
    long a;
    final String b;
    final String c;
    final long d;
    final long e;
    final long f;
    final long g;
    final List<ajj> h;

    jv(String str, acb acb) {
        List list;
        acb acb2 = acb;
        String str2 = acb2.b;
        long j = acb2.c;
        long j2 = acb2.d;
        long j3 = acb2.e;
        long j4 = acb2.f;
        if (acb2.h != null) {
            list = acb2.h;
        } else {
            Map<String, String> map = acb2.g;
            ArrayList arrayList = new ArrayList(map.size());
            for (Entry entry : map.entrySet()) {
                arrayList.add(new ajj((String) entry.getKey(), (String) entry.getValue()));
            }
            list = arrayList;
        }
        this(str, str2, j, j2, j3, j4, list);
        this.a = (long) acb2.a.length;
    }

    private jv(String str, String str2, long j, long j2, long j3, long j4, List<ajj> list) {
        this.b = str;
        if ("".equals(str2)) {
            str2 = null;
        }
        this.c = str2;
        this.d = j;
        this.e = j2;
        this.f = j3;
        this.g = j4;
        this.h = list;
    }

    static jv a(ku kuVar) throws IOException {
        if (iu.a((InputStream) kuVar) != 538247942) {
            throw new IOException();
        }
        jv jvVar = new jv(iu.a(kuVar), iu.a(kuVar), iu.b((InputStream) kuVar), iu.b((InputStream) kuVar), iu.b((InputStream) kuVar), iu.b((InputStream) kuVar), iu.b(kuVar));
        return jvVar;
    }

    /* access modifiers changed from: 0000 */
    public final boolean a(OutputStream outputStream) {
        try {
            iu.a(outputStream, 538247942);
            iu.a(outputStream, this.b);
            iu.a(outputStream, this.c == null ? "" : this.c);
            iu.a(outputStream, this.d);
            iu.a(outputStream, this.e);
            iu.a(outputStream, this.f);
            iu.a(outputStream, this.g);
            List<ajj> list = this.h;
            if (list != null) {
                iu.a(outputStream, list.size());
                for (ajj ajj : list) {
                    iu.a(outputStream, ajj.a());
                    iu.a(outputStream, ajj.b());
                }
            } else {
                iu.a(outputStream, 0);
            }
            outputStream.flush();
            return true;
        } catch (IOException e2) {
            ct.b("%s", e2.toString());
            return false;
        }
    }
}
