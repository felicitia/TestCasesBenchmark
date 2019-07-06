package kotlin.text;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.b.d;
import kotlin.jvm.a.m;
import kotlin.jvm.internal.p;
import kotlin.sequences.c;

/* compiled from: Strings.kt */
final class e implements c<kotlin.b.c> {
    /* access modifiers changed from: private */
    public final CharSequence a;
    /* access modifiers changed from: private */
    public final int b;
    /* access modifiers changed from: private */
    public final int c;
    /* access modifiers changed from: private */
    public final m<CharSequence, Integer, Pair<Integer, Integer>> d;

    /* compiled from: Strings.kt */
    public static final class a implements Iterator<kotlin.b.c> {
        final /* synthetic */ e a;
        private int b = -1;
        private int c;
        private int d;
        private kotlin.b.c e;
        private int f;

        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        a(e eVar) {
            this.a = eVar;
            this.c = d.a(eVar.b, 0, eVar.a.length());
            this.d = this.c;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:6:0x0025, code lost:
            if (r6.f < kotlin.text.e.a(r6.a)) goto L_0x0027;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private final void b() {
            /*
                r6 = this;
                int r0 = r6.d
                r1 = 0
                if (r0 >= 0) goto L_0x000e
                r6.b = r1
                r0 = 0
                kotlin.b.c r0 = (kotlin.b.c) r0
                r6.e = r0
                goto L_0x00a4
            L_0x000e:
                kotlin.text.e r0 = r6.a
                int r0 = r0.c
                r2 = -1
                r3 = 1
                if (r0 <= 0) goto L_0x0027
                int r0 = r6.f
                int r0 = r0 + r3
                r6.f = r0
                int r0 = r6.f
                kotlin.text.e r4 = r6.a
                int r4 = r4.c
                if (r0 >= r4) goto L_0x0035
            L_0x0027:
                int r0 = r6.d
                kotlin.text.e r4 = r6.a
                java.lang.CharSequence r4 = r4.a
                int r4 = r4.length()
                if (r0 <= r4) goto L_0x004b
            L_0x0035:
                int r0 = r6.c
                kotlin.b.c r1 = new kotlin.b.c
                kotlin.text.e r4 = r6.a
                java.lang.CharSequence r4 = r4.a
                int r4 = kotlin.text.m.e(r4)
                r1.<init>(r0, r4)
                r6.e = r1
                r6.d = r2
                goto L_0x00a2
            L_0x004b:
                kotlin.text.e r0 = r6.a
                kotlin.jvm.a.m r0 = r0.d
                kotlin.text.e r4 = r6.a
                java.lang.CharSequence r4 = r4.a
                int r5 = r6.d
                java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
                java.lang.Object r0 = r0.invoke(r4, r5)
                kotlin.Pair r0 = (kotlin.Pair) r0
                if (r0 != 0) goto L_0x007b
                int r0 = r6.c
                kotlin.b.c r1 = new kotlin.b.c
                kotlin.text.e r4 = r6.a
                java.lang.CharSequence r4 = r4.a
                int r4 = kotlin.text.m.e(r4)
                r1.<init>(r0, r4)
                r6.e = r1
                r6.d = r2
                goto L_0x00a2
            L_0x007b:
                java.lang.Object r2 = r0.component1()
                java.lang.Number r2 = (java.lang.Number) r2
                int r2 = r2.intValue()
                java.lang.Object r0 = r0.component2()
                java.lang.Number r0 = (java.lang.Number) r0
                int r0 = r0.intValue()
                int r4 = r6.c
                kotlin.b.c r4 = kotlin.b.d.b(r4, r2)
                r6.e = r4
                int r2 = r2 + r0
                r6.c = r2
                int r2 = r6.c
                if (r0 != 0) goto L_0x009f
                r1 = r3
            L_0x009f:
                int r2 = r2 + r1
                r6.d = r2
            L_0x00a2:
                r6.b = r3
            L_0x00a4:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.text.e.a.b():void");
        }

        /* renamed from: a */
        public kotlin.b.c next() {
            if (this.b == -1) {
                b();
            }
            if (this.b == 0) {
                throw new NoSuchElementException();
            }
            kotlin.b.c cVar = this.e;
            if (cVar == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.ranges.IntRange");
            }
            this.e = null;
            this.b = -1;
            return cVar;
        }

        public boolean hasNext() {
            if (this.b == -1) {
                b();
            }
            return this.b == 1;
        }
    }

    public e(CharSequence charSequence, int i, int i2, m<? super CharSequence, ? super Integer, Pair<Integer, Integer>> mVar) {
        p.b(charSequence, "input");
        p.b(mVar, "getNextMatch");
        this.a = charSequence;
        this.b = i;
        this.c = i2;
        this.d = mVar;
    }

    public Iterator<kotlin.b.c> a() {
        return new a<>(this);
    }
}
