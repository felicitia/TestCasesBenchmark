package kotlin.text;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import kotlin.b.c;
import kotlin.collections.d;
import kotlin.collections.o;
import kotlin.jvm.internal.p;

/* compiled from: Regex.kt */
final class k implements j {
    /* access modifiers changed from: private */
    public final MatchResult a = this.d.toMatchResult();
    private final h b = new b(this);
    private List<String> c;
    private final Matcher d;
    private final CharSequence e;

    /* compiled from: Regex.kt */
    public static final class a extends d<String> {
        final /* synthetic */ k b;

        a(k kVar) {
            this.b = kVar;
        }

        public int a(String str) {
            return super.indexOf(str);
        }

        public int b(String str) {
            return super.lastIndexOf(str);
        }

        public boolean c(String str) {
            return super.contains(str);
        }

        public final boolean contains(Object obj) {
            if (obj instanceof String) {
                return c((String) obj);
            }
            return false;
        }

        public final int indexOf(Object obj) {
            if (obj instanceof String) {
                return a((String) obj);
            }
            return -1;
        }

        public final int lastIndexOf(Object obj) {
            if (obj instanceof String) {
                return b((String) obj);
            }
            return -1;
        }

        public int a() {
            return this.b.a.groupCount() + 1;
        }

        /* renamed from: a */
        public String get(int i) {
            String group = this.b.a.group(i);
            return group != null ? group : "";
        }
    }

    /* compiled from: Regex.kt */
    public static final class b extends kotlin.collections.a<g> implements i {
        final /* synthetic */ k a;

        public boolean isEmpty() {
            return false;
        }

        b(k kVar) {
            this.a = kVar;
        }

        public boolean a(g gVar) {
            return super.contains(gVar);
        }

        public final boolean contains(Object obj) {
            if (obj != null ? obj instanceof g : true) {
                return a((g) obj);
            }
            return false;
        }

        public int a() {
            return this.a.a.groupCount() + 1;
        }

        public Iterator<g> iterator() {
            return kotlin.sequences.d.c(o.i(o.a((Collection<?>) this)), new MatcherMatchResult$groups$1$iterator$1(this)).a();
        }

        public g a(int i) {
            MatchResult a2 = this.a.a;
            p.a((Object) a2, "matchResult");
            c a3 = l.b(a2, i);
            if (a3.f().intValue() < 0) {
                return null;
            }
            String group = this.a.a.group(i);
            p.a((Object) group, "matchResult.group(index)");
            return new g(group, a3);
        }
    }

    public k(Matcher matcher, CharSequence charSequence) {
        p.b(matcher, "matcher");
        p.b(charSequence, "input");
        this.d = matcher;
        this.e = charSequence;
    }

    public c a() {
        MatchResult matchResult = this.a;
        p.a((Object) matchResult, "matchResult");
        return l.b(matchResult);
    }

    public String b() {
        String group = this.a.group();
        p.a((Object) group, "matchResult.group()");
        return group;
    }

    public List<String> c() {
        if (this.c == null) {
            this.c = new a(this);
        }
        List<String> list = this.c;
        if (list == null) {
            p.a();
        }
        return list;
    }

    public j d() {
        int end = this.a.end() + (this.a.end() == this.a.start() ? 1 : 0);
        if (end <= this.e.length()) {
            return l.b(this.d, end, this.e);
        }
        return null;
    }
}
