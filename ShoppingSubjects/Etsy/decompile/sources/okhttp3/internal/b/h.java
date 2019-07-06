package okhttp3.internal.b;

import okhttp3.ab;
import okhttp3.u;
import okio.e;

/* compiled from: RealResponseBody */
public final class h extends ab {
    private final String a;
    private final long b;
    private final e c;

    public h(String str, long j, e eVar) {
        this.a = str;
        this.b = j;
        this.c = eVar;
    }

    public u a() {
        if (this.a != null) {
            return u.a(this.a);
        }
        return null;
    }

    public long b() {
        return this.b;
    }

    public e c() {
        return this.c;
    }
}
