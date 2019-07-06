package kotlin.collections;

import com.etsy.android.lib.models.ResponseConstants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.Pair;
import kotlin.f;
import kotlin.jvm.internal.p;

/* compiled from: _Arrays.kt */
class j extends i {
    public static final char a(char[] cArr) {
        p.b(cArr, "$receiver");
        switch (cArr.length) {
            case 0:
                throw new NoSuchElementException("Array is empty.");
            case 1:
                return cArr[0];
            default:
                throw new IllegalArgumentException("Array has more than one element.");
        }
    }

    public static final <T, C extends Collection<? super T>> C a(T[] tArr, C c) {
        p.b(tArr, "$receiver");
        p.b(c, "destination");
        for (T add : tArr) {
            c.add(add);
        }
        return c;
    }

    public static final <T> HashSet<T> b(T[] tArr) {
        p.b(tArr, "$receiver");
        return (HashSet) f.a(tArr, (C) new HashSet(af.a(tArr.length)));
    }

    public static final <T> Set<T> c(T[] tArr) {
        p.b(tArr, "$receiver");
        switch (tArr.length) {
            case 0:
                return al.a();
            case 1:
                return al.a(tArr[0]);
            default:
                return (Set) f.a(tArr, (C) new LinkedHashSet(af.a(tArr.length)));
        }
    }

    public static final List<Pair<Byte, Byte>> a(byte[] bArr, byte[] bArr2) {
        p.b(bArr, "$receiver");
        p.b(bArr2, ResponseConstants.OTHER);
        int min = Math.min(bArr.length, bArr2.length);
        ArrayList arrayList = new ArrayList(min);
        for (int i = 0; i < min; i++) {
            arrayList.add(f.a(Byte.valueOf(bArr[i]), Byte.valueOf(bArr2[i])));
        }
        return arrayList;
    }
}
