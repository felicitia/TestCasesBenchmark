package kotlin.sequences;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.f;
import kotlin.collections.o;
import kotlin.jvm.internal.Ref.BooleanRef;
import kotlin.jvm.internal.p;
import kotlin.text.m;

/* compiled from: _Sequences.kt */
class h extends g {

    /* compiled from: _Sequences.kt */
    public static final class a implements c<T> {
        final /* synthetic */ c a;
        final /* synthetic */ Object b;

        public Iterator<T> a() {
            BooleanRef booleanRef = new BooleanRef();
            booleanRef.element = false;
            return d.a(this.a, (kotlin.jvm.a.b<? super T, Boolean>) new SequencesKt___SequencesKt$minus$1$iterator$1<Object,Boolean>(this, booleanRef)).a();
        }
    }

    /* compiled from: _Sequences.kt */
    public static final class b implements c<T> {
        final /* synthetic */ c a;
        final /* synthetic */ Object[] b;

        public Iterator<T> a() {
            return d.b(this.a, new SequencesKt___SequencesKt$minus$2$iterator$1(f.b(this.b))).a();
        }
    }

    /* compiled from: _Sequences.kt */
    public static final class c implements c<T> {
        final /* synthetic */ c a;
        final /* synthetic */ Iterable b;

        public Iterator<T> a() {
            Collection a2 = o.a(this.b);
            if (a2.isEmpty()) {
                return this.a.a();
            }
            return d.b(this.a, new SequencesKt___SequencesKt$minus$3$iterator$1(a2)).a();
        }
    }

    /* compiled from: _Sequences.kt */
    public static final class d implements c<T> {
        final /* synthetic */ c a;
        final /* synthetic */ c b;

        public Iterator<T> a() {
            HashSet a2 = d.a(this.b);
            if (a2.isEmpty()) {
                return this.a.a();
            }
            return d.b(this.a, new SequencesKt___SequencesKt$minus$4$iterator$1(a2)).a();
        }
    }

    public static final <T> c<T> a(c<? extends T> cVar, kotlin.jvm.a.b<? super T, Boolean> bVar) {
        p.b(cVar, "$receiver");
        p.b(bVar, "predicate");
        return new a<>(cVar, true, bVar);
    }

    public static final <T> c<T> b(c<? extends T> cVar, kotlin.jvm.a.b<? super T, Boolean> bVar) {
        p.b(cVar, "$receiver");
        p.b(bVar, "predicate");
        return new a<>(cVar, false, bVar);
    }

    public static final <T, C extends Collection<? super T>> C a(c<? extends T> cVar, C c2) {
        p.b(cVar, "$receiver");
        p.b(c2, "destination");
        Iterator a2 = cVar.a();
        while (a2.hasNext()) {
            c2.add(a2.next());
        }
        return c2;
    }

    public static final <T> HashSet<T> a(c<? extends T> cVar) {
        p.b(cVar, "$receiver");
        return (HashSet) d.a(cVar, (C) new HashSet());
    }

    public static final <T> List<T> b(c<? extends T> cVar) {
        p.b(cVar, "$receiver");
        return o.b(d.c(cVar));
    }

    public static final <T> List<T> c(c<? extends T> cVar) {
        p.b(cVar, "$receiver");
        return (List) d.a(cVar, (C) new ArrayList());
    }

    public static final <T, R> c<R> c(c<? extends T> cVar, kotlin.jvm.a.b<? super T, ? extends R> bVar) {
        p.b(cVar, "$receiver");
        p.b(bVar, "transform");
        return new i<>(cVar, bVar);
    }

    public static final <T, A extends Appendable> A a(c<? extends T> cVar, A a2, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, kotlin.jvm.a.b<? super T, ? extends CharSequence> bVar) {
        p.b(cVar, "$receiver");
        p.b(a2, "buffer");
        p.b(charSequence, "separator");
        p.b(charSequence2, "prefix");
        p.b(charSequence3, "postfix");
        p.b(charSequence4, "truncated");
        a2.append(charSequence2);
        Iterator a3 = cVar.a();
        int i2 = 0;
        while (a3.hasNext()) {
            Object next = a3.next();
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

    public static /* bridge */ /* synthetic */ String a(c cVar, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, kotlin.jvm.a.b bVar, int i2, Object obj) {
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
        return d.a(cVar, charSequence, charSequence5, charSequence6, i3, charSequence7, bVar);
    }

    public static final <T> String a(c<? extends T> cVar, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, kotlin.jvm.a.b<? super T, ? extends CharSequence> bVar) {
        p.b(cVar, "$receiver");
        p.b(charSequence, "separator");
        p.b(charSequence2, "prefix");
        p.b(charSequence3, "postfix");
        p.b(charSequence4, "truncated");
        String sb = ((StringBuilder) d.a(cVar, new StringBuilder(), charSequence, charSequence2, charSequence3, i, charSequence4, bVar)).toString();
        p.a((Object) sb, "joinTo(StringBuilder(), …ed, transform).toString()");
        return sb;
    }
}
