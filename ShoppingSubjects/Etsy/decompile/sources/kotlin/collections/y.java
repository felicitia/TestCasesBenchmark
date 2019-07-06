package kotlin.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import kotlin.TypeCastException;
import kotlin.b.d;
import kotlin.jvm.a.b;
import kotlin.jvm.internal.p;
import kotlin.sequences.c;
import kotlin.text.m;

/* compiled from: _Collections.kt */
class y extends x {

    /* compiled from: Sequences.kt */
    public static final class a implements c<T> {
        final /* synthetic */ Iterable a;

        public a(Iterable iterable) {
            this.a = iterable;
        }

        public Iterator<T> a() {
            return this.a.iterator();
        }
    }

    public static final <T> T b(Iterable<? extends T> iterable) {
        p.b(iterable, "$receiver");
        if (iterable instanceof List) {
            return o.e((List) iterable);
        }
        Iterator it = iterable.iterator();
        if (it.hasNext()) {
            return it.next();
        }
        throw new NoSuchElementException("Collection is empty.");
    }

    public static final <T> T e(List<? extends T> list) {
        p.b(list, "$receiver");
        if (!list.isEmpty()) {
            return list.get(0);
        }
        throw new NoSuchElementException("List is empty.");
    }

    public static final <T> T f(List<? extends T> list) {
        p.b(list, "$receiver");
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public static final <T> T g(List<? extends T> list) {
        p.b(list, "$receiver");
        if (!list.isEmpty()) {
            return list.get(o.a(list));
        }
        throw new NoSuchElementException("List is empty.");
    }

    public static final <T> T c(Iterable<? extends T> iterable) {
        p.b(iterable, "$receiver");
        if (iterable instanceof List) {
            return o.h((List) iterable);
        }
        Iterator it = iterable.iterator();
        if (!it.hasNext()) {
            throw new NoSuchElementException("Collection is empty.");
        }
        T next = it.next();
        if (!it.hasNext()) {
            return next;
        }
        throw new IllegalArgumentException("Collection has more than one element.");
    }

    public static final <T> T h(List<? extends T> list) {
        p.b(list, "$receiver");
        switch (list.size()) {
            case 0:
                throw new NoSuchElementException("List is empty.");
            case 1:
                return list.get(0);
            default:
                throw new IllegalArgumentException("List has more than one element.");
        }
    }

    public static final <T> List<T> a(List<? extends T> list, int i) {
        p.b(list, "$receiver");
        if (i >= 0) {
            return o.b(list, d.c(list.size() - i, 0));
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Requested element count ");
        sb.append(i);
        sb.append(" is less than zero.");
        throw new IllegalArgumentException(sb.toString().toString());
    }

    public static final <T> List<T> b(Iterable<? extends T> iterable, int i) {
        p.b(iterable, "$receiver");
        int i2 = 0;
        if (!(i >= 0)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Requested element count ");
            sb.append(i);
            sb.append(" is less than zero.");
            throw new IllegalArgumentException(sb.toString().toString());
        } else if (i == 0) {
            return o.a();
        } else {
            if (iterable instanceof Collection) {
                if (i >= ((Collection) iterable).size()) {
                    return o.f(iterable);
                }
                if (i == 1) {
                    return o.a(o.b(iterable));
                }
            }
            ArrayList arrayList = new ArrayList(i);
            for (Object next : iterable) {
                int i3 = i2 + 1;
                if (i2 == i) {
                    break;
                }
                arrayList.add(next);
                i2 = i3;
            }
            return o.b(arrayList);
        }
    }

    public static final <T> List<T> d(Iterable<? extends T> iterable) {
        p.b(iterable, "$receiver");
        if ((iterable instanceof Collection) && ((Collection) iterable).size() <= 1) {
            return o.f(iterable);
        }
        List<T> g = o.g(iterable);
        o.d(g);
        return g;
    }

    public static final <T> List<T> a(Iterable<? extends T> iterable, Comparator<? super T> comparator) {
        p.b(iterable, "$receiver");
        p.b(comparator, "comparator");
        if (iterable instanceof Collection) {
            Collection collection = (Collection) iterable;
            if (collection.size() <= 1) {
                return o.f(iterable);
            }
            Object[] array = collection.toArray(new Object[0]);
            if (array == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
            } else if (array == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
            } else {
                f.a(array, comparator);
                return f.a(array);
            }
        } else {
            List<T> g = o.g(iterable);
            o.a(g, comparator);
            return g;
        }
    }

    public static final <T, C extends Collection<? super T>> C a(Iterable<? extends T> iterable, C c) {
        p.b(iterable, "$receiver");
        p.b(c, "destination");
        for (Object add : iterable) {
            c.add(add);
        }
        return c;
    }

    public static final <T> HashSet<T> e(Iterable<? extends T> iterable) {
        p.b(iterable, "$receiver");
        return (HashSet) o.a(iterable, (C) new HashSet(af.a(o.a(iterable, 12))));
    }

    public static final <T> List<T> f(Iterable<? extends T> iterable) {
        List<T> list;
        p.b(iterable, "$receiver");
        if (!(iterable instanceof Collection)) {
            return o.b(o.g(iterable));
        }
        Collection collection = (Collection) iterable;
        switch (collection.size()) {
            case 0:
                list = o.a();
                break;
            case 1:
                list = o.a(iterable instanceof List ? ((List) iterable).get(0) : iterable.iterator().next());
                break;
            default:
                list = o.b(collection);
                break;
        }
        return list;
    }

    public static final <T> List<T> g(Iterable<? extends T> iterable) {
        p.b(iterable, "$receiver");
        if (iterable instanceof Collection) {
            return o.b((Collection) iterable);
        }
        return (List) o.a(iterable, (C) new ArrayList());
    }

    public static final <T> List<T> b(Collection<? extends T> collection) {
        p.b(collection, "$receiver");
        return new ArrayList<>(collection);
    }

    public static final <T extends Comparable<? super T>> T h(Iterable<? extends T> iterable) {
        p.b(iterable, "$receiver");
        Iterator it = iterable.iterator();
        if (!it.hasNext()) {
            return null;
        }
        T t = (Comparable) it.next();
        while (it.hasNext()) {
            T t2 = (Comparable) it.next();
            if (t.compareTo(t2) > 0) {
                t = t2;
            }
        }
        return t;
    }

    public static final <T> List<T> a(Collection<? extends T> collection, T t) {
        p.b(collection, "$receiver");
        ArrayList arrayList = new ArrayList(collection.size() + 1);
        arrayList.addAll(collection);
        arrayList.add(t);
        return arrayList;
    }

    public static final <T> List<List<T>> a(Iterable<? extends T> iterable, int i, int i2, boolean z) {
        p.b(iterable, "$receiver");
        aq.a(i, i2);
        if (!(iterable instanceof RandomAccess) || !(iterable instanceof List)) {
            ArrayList arrayList = new ArrayList();
            Iterator a2 = aq.a(iterable.iterator(), i, i2, z, false);
            while (a2.hasNext()) {
                arrayList.add((List) a2.next());
            }
            return arrayList;
        }
        List list = (List) iterable;
        int size = list.size();
        ArrayList arrayList2 = new ArrayList(((size + i2) - 1) / i2);
        int i3 = 0;
        while (i3 < size) {
            int d = d.d(i, size - i3);
            if (d < i && !z) {
                break;
            }
            ArrayList arrayList3 = new ArrayList(d);
            for (int i4 = 0; i4 < d; i4++) {
                arrayList3.add(list.get(i4 + i3));
            }
            arrayList2.add(arrayList3);
            i3 += i2;
        }
        return arrayList2;
    }

    public static /* bridge */ /* synthetic */ Appendable a(Iterable iterable, Appendable appendable, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, b bVar, int i2, Object obj) {
        return o.a(iterable, appendable, (i2 & 2) != 0 ? ", " : charSequence, (i2 & 4) != 0 ? "" : charSequence2, (i2 & 8) != 0 ? "" : charSequence3, (i2 & 16) != 0 ? -1 : i, (i2 & 32) != 0 ? "..." : charSequence4, (i2 & 64) != 0 ? null : bVar);
    }

    public static final <T, A extends Appendable> A a(Iterable<? extends T> iterable, A a2, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, b<? super T, ? extends CharSequence> bVar) {
        p.b(iterable, "$receiver");
        p.b(a2, "buffer");
        p.b(charSequence, "separator");
        p.b(charSequence2, "prefix");
        p.b(charSequence3, "postfix");
        p.b(charSequence4, "truncated");
        a2.append(charSequence2);
        int i2 = 0;
        for (Object next : iterable) {
            i2++;
            if (i2 > 1) {
                a2.append(charSequence);
            }
            if (i >= 0 && i2 > i) {
                break;
            }
            m.a(a2, next, bVar);
        }
        if (i >= 0 && i2 > i) {
            a2.append(charSequence4);
        }
        a2.append(charSequence3);
        return a2;
    }

    public static /* bridge */ /* synthetic */ String a(Iterable iterable, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, b bVar, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            charSequence = ", ";
        }
        if ((i2 & 2) != 0) {
            charSequence2 = "";
        }
        CharSequence charSequence5 = charSequence2;
        if ((i2 & 4) != 0) {
            charSequence3 = "";
        }
        CharSequence charSequence6 = charSequence3;
        if ((i2 & 8) != 0) {
            i = -1;
        }
        int i3 = i;
        if ((i2 & 16) != 0) {
            charSequence4 = "...";
        }
        CharSequence charSequence7 = charSequence4;
        if ((i2 & 32) != 0) {
            bVar = null;
        }
        return o.a(iterable, charSequence, charSequence5, charSequence6, i3, charSequence7, bVar);
    }

    public static final <T> String a(Iterable<? extends T> iterable, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, b<? super T, ? extends CharSequence> bVar) {
        p.b(iterable, "$receiver");
        p.b(charSequence, "separator");
        p.b(charSequence2, "prefix");
        p.b(charSequence3, "postfix");
        p.b(charSequence4, "truncated");
        String sb = ((StringBuilder) o.a(iterable, new StringBuilder(), charSequence, charSequence2, charSequence3, i, charSequence4, bVar)).toString();
        p.a((Object) sb, "joinTo(StringBuilder(), …ed, transform).toString()");
        return sb;
    }

    public static final <T> c<T> i(Iterable<? extends T> iterable) {
        p.b(iterable, "$receiver");
        return new a<>(iterable);
    }
}
