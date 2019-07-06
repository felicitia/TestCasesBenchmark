package com.etsy.android.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Pair;
import kotlin.collections.f;
import kotlin.collections.o;

/* compiled from: BOESecrets.kt */
public final class b {
    public static final String a = h.a(new byte[]{5, 82, 14, 1, 81, 12, 88, 8, 13, 72, 66, 5, 8, 67, 90, 90, 3, 82, 10, 9, 7, 76, 71, 67});
    public static final String b = h.a(new byte[]{21, 67, 66, 69, 85, 8, 7, 84, 13, 86});
    public static final String c = h.a(new byte[]{87, 7, 92, 14, 81, 81, 81, 14, 86, 15, 3, 83, 90, 82, 0, 2, 82, 85, 81, 8, 6, 6, 5, 8});
    public static final String d = h.a(new byte[]{90, 4, 12, 85, 81, 83, 86, 94, 75, 89, 80, 89, 85, 30, 2, 80, 2, 83, 76, 0, 7, 5, 4, 20, 86, 83, 1, 14, 84, 1, 6, 1, 80, 3, 6, 86});
    public static final String e = h.a(new byte[]{15, 82, 89, 78, 2, 7, 72, 64, 82, 74, 85, 6, 5, 89, 88, 69, 2, 66, 89, 90, 78, 67, 87, 15});
    public static final String f = h.a(new byte[]{80, 13, 14, 14, 81, 82, 5, 0, 85, 11, 8, 82});
    public static final String g = h.a(new byte[]{3, 68, 72, 26, 85, 5, 84, 89, 4, 90, 80, 7, 87, 6, 15, 15, 85, 5, 81, 92});
    public static final a h = new a(null);
    /* access modifiers changed from: private */
    public static final byte[] i = {98, 52, 56, 55, 100, 100, 50, 56, 102, 56, 49, 97, 99, 51, 54, 54, 100, 55, 97, 56, 54, 54, 53, 57, 53, 101, 55, 55, 98, 98, 49, 48, 49, 102, 51, 53, 100, 102, 54, 98, 97, 49, 48, 50, 51, 100, 48, 55, 100, 55, 56, 99, 49, 51, 57, 101, 56, 98, 99, 52, 49, 48, 102, 50, 57, 49, 97, 55, 56, 54, 97, 53};

    /* compiled from: BOESecrets.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(o oVar) {
            this();
        }

        /* access modifiers changed from: private */
        public final String a(byte[] bArr) {
            Iterable<Pair> a = f.a(b.i, bArr);
            Collection arrayList = new ArrayList(o.a(a, 10));
            for (Pair pair : a) {
                arrayList.add(Byte.valueOf((byte) (((Number) pair.getSecond()).byteValue() ^ ((Number) pair.getFirst()).byteValue())));
            }
            Iterable<Number> iterable = (List) arrayList;
            Collection arrayList2 = new ArrayList(o.a(iterable, 10));
            for (Number byteValue : iterable) {
                arrayList2.add(Character.valueOf((char) byteValue.byteValue()));
            }
            return o.a((List) arrayList2, "", null, null, 0, null, null, 62, null);
        }
    }
}
