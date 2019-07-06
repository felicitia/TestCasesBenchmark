package okhttp3;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import okhttp3.internal.c;
import okio.d;

/* compiled from: FormBody */
public final class q extends z {
    private static final u a = u.a("application/x-www-form-urlencoded");
    private final List<String> b;
    private final List<String> c;

    /* compiled from: FormBody */
    public static final class a {
        private final List<String> a;
        private final List<String> b;
        private final Charset c;

        public a() {
            this(null);
        }

        public a(Charset charset) {
            this.a = new ArrayList();
            this.b = new ArrayList();
            this.c = charset;
        }

        public a a(String str, String str2) {
            if (str == null) {
                throw new NullPointerException("name == null");
            } else if (str2 == null) {
                throw new NullPointerException("value == null");
            } else {
                this.a.add(HttpUrl.a(str, " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, true, true, this.c));
                this.b.add(HttpUrl.a(str2, " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, true, true, this.c));
                return this;
            }
        }

        public a b(String str, String str2) {
            if (str == null) {
                throw new NullPointerException("name == null");
            } else if (str2 == null) {
                throw new NullPointerException("value == null");
            } else {
                this.a.add(HttpUrl.a(str, " \"':;<=>@[]^`{}|/\\?#&!$(),~", true, false, true, true, this.c));
                this.b.add(HttpUrl.a(str2, " \"':;<=>@[]^`{}|/\\?#&!$(),~", true, false, true, true, this.c));
                return this;
            }
        }

        public q a() {
            return new q(this.a, this.b);
        }
    }

    q(List<String> list, List<String> list2) {
        this.b = c.a(list);
        this.c = c.a(list2);
    }

    public u a() {
        return a;
    }

    public long b() {
        return a(null, true);
    }

    public void a(d dVar) throws IOException {
        a(dVar, false);
    }

    private long a(d dVar, boolean z) {
        okio.c cVar;
        if (z) {
            cVar = new okio.c();
        } else {
            cVar = dVar.c();
        }
        int size = this.b.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                cVar.k(38);
            }
            cVar.b((String) this.b.get(i));
            cVar.k(61);
            cVar.b((String) this.c.get(i));
        }
        if (!z) {
            return 0;
        }
        long b2 = cVar.b();
        cVar.t();
        return b2;
    }
}
