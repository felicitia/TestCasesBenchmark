package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.VisibleForTesting;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.PriorityQueue;

@bu
public final class agl {
    private final int a;
    private final int b;
    private final int c;
    private final agk d = new agp();

    public agl(int i) {
        this.b = i;
        this.a = 6;
        this.c = 0;
    }

    @VisibleForTesting
    private final String a(String str) {
        String[] split = str.split("\n");
        if (split.length == 0) {
            return "";
        }
        agn agn = new agn();
        PriorityQueue priorityQueue = new PriorityQueue(this.b, new agm(this));
        for (String a2 : split) {
            String[] a3 = ago.a(a2, false);
            if (a3.length != 0) {
                agr.a(a3, this.b, this.a, priorityQueue);
            }
        }
        Iterator it = priorityQueue.iterator();
        while (it.hasNext()) {
            try {
                agn.a(this.d.a(((ags) it.next()).b));
            } catch (IOException e) {
                gv.b("Error while writing hash to byteStream", e);
            }
        }
        return agn.toString();
    }

    public final String a(ArrayList<String> arrayList) {
        StringBuilder sb = new StringBuilder();
        ArrayList arrayList2 = arrayList;
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            sb.append(((String) obj).toLowerCase(Locale.US));
            sb.append(10);
        }
        return a(sb.toString());
    }
}
