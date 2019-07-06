package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.VisibleForTesting;
import java.util.PriorityQueue;

@bu
public final class agr {
    @VisibleForTesting
    private static long a(long j, int i) {
        if (i == 0) {
            return 1;
        }
        if (i == 1) {
            return j;
        }
        return (i % 2 == 0 ? a((j * j) % 1073807359, i / 2) : j * (a((j * j) % 1073807359, i / 2) % 1073807359)) % 1073807359;
    }

    @VisibleForTesting
    private static String a(String[] strArr, int i, int i2) {
        int i3 = i2 + i;
        if (strArr.length < i3) {
            gv.c("Unable to construct shingle");
            return "";
        }
        StringBuilder sb = new StringBuilder();
        while (true) {
            int i4 = i3 - 1;
            if (i < i4) {
                sb.append(strArr[i]);
                sb.append(' ');
                i++;
            } else {
                sb.append(strArr[i4]);
                return sb.toString();
            }
        }
    }

    @VisibleForTesting
    private static void a(int i, long j, String str, int i2, PriorityQueue<ags> priorityQueue) {
        ags ags = new ags(j, str, i2);
        if ((priorityQueue.size() != i || (((ags) priorityQueue.peek()).c <= ags.c && ((ags) priorityQueue.peek()).a <= ags.a)) && !priorityQueue.contains(ags)) {
            priorityQueue.add(ags);
            if (priorityQueue.size() > i) {
                priorityQueue.poll();
            }
        }
    }

    public static void a(String[] strArr, int i, int i2, PriorityQueue<ags> priorityQueue) {
        String[] strArr2 = strArr;
        int i3 = i2;
        if (strArr2.length < i3) {
            a(i, b(strArr2, 0, strArr2.length), a(strArr2, 0, strArr2.length), strArr2.length, priorityQueue);
            return;
        }
        long b = b(strArr2, 0, i3);
        a(i, b, a(strArr2, 0, i3), i3, priorityQueue);
        long a = a(16785407, i3 - 1);
        for (int i4 = 1; i4 < (strArr2.length - i3) + 1; i4++) {
            long j = b + 1073807359;
            b = (((((j - ((((((long) ago.a(strArr2[i4 - 1])) + 2147483647L) % 1073807359) * a) % 1073807359)) % 1073807359) * 16785407) % 1073807359) + ((((long) ago.a(strArr2[(i4 + i3) - 1])) + 2147483647L) % 1073807359)) % 1073807359;
            a(i, b, a(strArr2, i4, i3), strArr2.length, priorityQueue);
        }
    }

    private static long b(String[] strArr, int i, int i2) {
        long a = (((long) ago.a(strArr[0])) + 2147483647L) % 1073807359;
        for (int i3 = 1; i3 < i2; i3++) {
            a = (((a * 16785407) % 1073807359) + ((((long) ago.a(strArr[i3])) + 2147483647L) % 1073807359)) % 1073807359;
        }
        return a;
    }
}
