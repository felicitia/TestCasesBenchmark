package okhttp3.internal.a;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import okhttp3.aa;
import okhttp3.internal.b.d;
import okhttp3.internal.b.e;
import okhttp3.s;
import okhttp3.y;
import org.apache.commons.lang3.time.DateUtils;

/* compiled from: CacheStrategy */
public final class c {
    public final y a;
    public final aa b;

    /* compiled from: CacheStrategy */
    public static class a {
        final long a;
        final y b;
        final aa c;
        private Date d;
        private String e;
        private Date f;
        private String g;
        private Date h;
        private long i;
        private long j;
        private String k;
        private int l = -1;

        public a(long j2, y yVar, aa aaVar) {
            this.a = j2;
            this.b = yVar;
            this.c = aaVar;
            if (aaVar != null) {
                this.i = aaVar.k();
                this.j = aaVar.l();
                s f2 = aaVar.f();
                int a2 = f2.a();
                for (int i2 = 0; i2 < a2; i2++) {
                    String a3 = f2.a(i2);
                    String b2 = f2.b(i2);
                    if ("Date".equalsIgnoreCase(a3)) {
                        this.d = d.a(b2);
                        this.e = b2;
                    } else if ("Expires".equalsIgnoreCase(a3)) {
                        this.h = d.a(b2);
                    } else if ("Last-Modified".equalsIgnoreCase(a3)) {
                        this.f = d.a(b2);
                        this.g = b2;
                    } else if ("ETag".equalsIgnoreCase(a3)) {
                        this.k = b2;
                    } else if ("Age".equalsIgnoreCase(a3)) {
                        this.l = e.b(b2, -1);
                    }
                }
            }
        }

        public c a() {
            c b2 = b();
            return (b2.a == null || !this.b.f().i()) ? b2 : new c(null, null);
        }

        private c b() {
            String str;
            String str2;
            if (this.c == null) {
                return new c(this.b, null);
            }
            if (this.b.g() && this.c.e() == null) {
                return new c(this.b, null);
            }
            if (!c.a(this.c, this.b)) {
                return new c(this.b, null);
            }
            okhttp3.d f2 = this.b.f();
            if (f2.a() || a(this.b)) {
                return new c(this.b, null);
            }
            okhttp3.d j2 = this.c.j();
            if (j2.j()) {
                return new c(null, this.c);
            }
            long d2 = d();
            long c2 = c();
            if (f2.c() != -1) {
                c2 = Math.min(c2, TimeUnit.SECONDS.toMillis((long) f2.c()));
            }
            long j3 = 0;
            long millis = f2.h() != -1 ? TimeUnit.SECONDS.toMillis((long) f2.h()) : 0;
            if (!j2.f() && f2.g() != -1) {
                j3 = TimeUnit.SECONDS.toMillis((long) f2.g());
            }
            if (!j2.a()) {
                long j4 = d2 + millis;
                if (j4 < c2 + j3) {
                    okhttp3.aa.a h2 = this.c.h();
                    if (j4 >= c2) {
                        h2.a("Warning", "110 HttpURLConnection \"Response is stale\"");
                    }
                    if (d2 > DateUtils.MILLIS_PER_DAY && e()) {
                        h2.a("Warning", "113 HttpURLConnection \"Heuristic expiration\"");
                    }
                    return new c(null, h2.a());
                }
            }
            if (this.k != null) {
                str2 = "If-None-Match";
                str = this.k;
            } else if (this.f != null) {
                str2 = "If-Modified-Since";
                str = this.g;
            } else if (this.d == null) {
                return new c(this.b, null);
            } else {
                str2 = "If-Modified-Since";
                str = this.e;
            }
            okhttp3.s.a c3 = this.b.c().c();
            okhttp3.internal.a.a.a(c3, str2, str);
            return new c(this.b.e().a(c3.a()).a(), this.c);
        }

        private long c() {
            long j2;
            long j3;
            okhttp3.d j4 = this.c.j();
            if (j4.c() != -1) {
                return TimeUnit.SECONDS.toMillis((long) j4.c());
            }
            long j5 = 0;
            if (this.h != null) {
                if (this.d != null) {
                    j3 = this.d.getTime();
                } else {
                    j3 = this.j;
                }
                long time = this.h.getTime() - j3;
                if (time > 0) {
                    j5 = time;
                }
                return j5;
            } else if (this.f == null || this.c.a().a().l() != null) {
                return 0;
            } else {
                if (this.d != null) {
                    j2 = this.d.getTime();
                } else {
                    j2 = this.i;
                }
                long time2 = j2 - this.f.getTime();
                if (time2 > 0) {
                    j5 = time2 / 10;
                }
                return j5;
            }
        }

        private long d() {
            long j2 = 0;
            if (this.d != null) {
                j2 = Math.max(0, this.j - this.d.getTime());
            }
            if (this.l != -1) {
                j2 = Math.max(j2, TimeUnit.SECONDS.toMillis((long) this.l));
            }
            return j2 + (this.j - this.i) + (this.a - this.j);
        }

        private boolean e() {
            return this.c.j().c() == -1 && this.h == null;
        }

        private static boolean a(y yVar) {
            return (yVar.a("If-Modified-Since") == null && yVar.a("If-None-Match") == null) ? false : true;
        }
    }

    c(y yVar, aa aaVar) {
        this.a = yVar;
        this.b = aaVar;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0038, code lost:
        if (r3.j().b() != false) goto L_0x0045;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0042, code lost:
        if (r4.f().b() != false) goto L_0x0045;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0044, code lost:
        r1 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0045, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0046, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002e, code lost:
        if (r3.j().d() == false) goto L_0x0046;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(okhttp3.aa r3, okhttp3.y r4) {
        /*
            int r0 = r3.b()
            r1 = 0
            switch(r0) {
                case 200: goto L_0x0030;
                case 203: goto L_0x0030;
                case 204: goto L_0x0030;
                case 300: goto L_0x0030;
                case 301: goto L_0x0030;
                case 302: goto L_0x0009;
                case 307: goto L_0x0009;
                case 308: goto L_0x0030;
                case 404: goto L_0x0030;
                case 405: goto L_0x0030;
                case 410: goto L_0x0030;
                case 414: goto L_0x0030;
                case 501: goto L_0x0030;
                default: goto L_0x0008;
            }
        L_0x0008:
            goto L_0x0046
        L_0x0009:
            java.lang.String r0 = "Expires"
            java.lang.String r0 = r3.a(r0)
            if (r0 != 0) goto L_0x0030
            okhttp3.d r0 = r3.j()
            int r0 = r0.c()
            r2 = -1
            if (r0 != r2) goto L_0x0030
            okhttp3.d r0 = r3.j()
            boolean r0 = r0.e()
            if (r0 != 0) goto L_0x0030
            okhttp3.d r0 = r3.j()
            boolean r0 = r0.d()
            if (r0 == 0) goto L_0x0046
        L_0x0030:
            okhttp3.d r3 = r3.j()
            boolean r3 = r3.b()
            if (r3 != 0) goto L_0x0045
            okhttp3.d r3 = r4.f()
            boolean r3 = r3.b()
            if (r3 != 0) goto L_0x0045
            r1 = 1
        L_0x0045:
            return r1
        L_0x0046:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.a.c.a(okhttp3.aa, okhttp3.y):boolean");
    }
}
