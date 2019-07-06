package com.salesforce.marketingcloud.messages;

import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.text.TextUtils;
import com.salesforce.marketingcloud.d.h;
import com.salesforce.marketingcloud.d.i;
import com.salesforce.marketingcloud.e.a;
import com.salesforce.marketingcloud.j;
import java.util.Date;

public final class k {
    private static final String a = n.a;

    private static int a(c cVar) {
        int k = cVar.k();
        if (k > 0 || cVar.l() <= 0 || cVar.m() == 0) {
            return k;
        }
        return 1;
    }

    @RestrictTo({Scope.LIBRARY})
    public static void a(c cVar, i iVar, a aVar) {
        c a2 = iVar.a(cVar.a(), aVar);
        if (a2 != null) {
            cVar.b(a2.y());
            cVar.b(a2.w());
            if (cVar.m() == a2.m()) {
                cVar.c(a2.x());
                cVar.a(a2.v());
            }
        }
    }

    static boolean a(c cVar, h hVar) {
        try {
            if (TextUtils.isEmpty(cVar.c().trim())) {
                j.b(a, "Message (%s) was tripped, but does not have an alert message", cVar.a());
                return false;
            }
            Date date = new Date();
            i i = hVar.i();
            if (cVar.g() != null && cVar.g().before(date)) {
                j.b(a, "Message (%s) was tripped, but has expired.", cVar.a());
                i.b(cVar.a());
                hVar.k().c(cVar.a());
                return false;
            } else if (cVar.f() != null && cVar.f().after(date)) {
                j.b(a, "Message (%s) was tripped, but has not started", cVar.a());
                return false;
            } else if (cVar.o() <= -1 || cVar.w() < cVar.o()) {
                int a2 = a(cVar);
                if (a2 > -1 && cVar.x() >= a2 && cVar.v() != null && date.before(cVar.v())) {
                    j.b(a, "Message (%s) was tripped, but has met its message per period limit", cVar.a());
                    return false;
                } else if (cVar.v() == null || !date.before(cVar.v())) {
                    return true;
                } else {
                    j.b(a, "Message (%s) was tripped, but was before its next allowed show time.", cVar.a());
                    return false;
                }
            } else {
                j.b(a, "Message (%s) was tripped, but has met its message limit.", cVar.a());
                return false;
            }
        } catch (Exception e) {
            j.c(a, e, "Failed to determine is message should be shown.", new Object[0]);
            return false;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0049, code lost:
        r5 = r5.toMillis(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0069, code lost:
        r6 = (long) r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x006a, code lost:
        r5 = r5.toMillis(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00b6, code lost:
        r0.set(5, 1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00b9, code lost:
        r0.set(10, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00bc, code lost:
        r0.set(12, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00bf, code lost:
        r13.a(r0.getTime());
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void b(com.salesforce.marketingcloud.messages.c r13, com.salesforce.marketingcloud.d.h r14) {
        /*
            java.util.Date r0 = new java.util.Date
            r0.<init>()
            r13.b(r0)
            int r1 = r13.w()
            r2 = 1
            int r1 = r1 + r2
            r13.b(r1)
            int r1 = a(r13)
            r3 = -1
            r4 = 0
            if (r1 <= r3) goto L_0x00c6
            int r5 = r13.l()
            if (r5 <= r3) goto L_0x00c6
            int r5 = r13.m()
            if (r5 == 0) goto L_0x00c6
            int r5 = r13.x()
            int r5 = r5 + r2
            r13.c(r5)
            int r5 = r13.x()
            int r6 = r13.k()
            if (r5 < r6) goto L_0x00c6
            r5 = 0
            int r7 = r13.m()
            r8 = 1
            r10 = 5
            switch(r7) {
                case 1: goto L_0x005e;
                case 2: goto L_0x0053;
                case 3: goto L_0x004e;
                case 4: goto L_0x0047;
                case 5: goto L_0x0044;
                default: goto L_0x0043;
            }
        L_0x0043:
            goto L_0x006e
        L_0x0044:
            java.util.concurrent.TimeUnit r5 = java.util.concurrent.TimeUnit.HOURS
            goto L_0x0049
        L_0x0047:
            java.util.concurrent.TimeUnit r5 = java.util.concurrent.TimeUnit.DAYS
        L_0x0049:
            long r5 = r5.toMillis(r8)
            goto L_0x006e
        L_0x004e:
            java.util.concurrent.TimeUnit r5 = java.util.concurrent.TimeUnit.DAYS
            r6 = 7
            goto L_0x006a
        L_0x0053:
            java.util.concurrent.TimeUnit r5 = java.util.concurrent.TimeUnit.DAYS
            java.util.Calendar r6 = java.util.Calendar.getInstance()
            int r6 = r6.getActualMaximum(r10)
            goto L_0x0069
        L_0x005e:
            java.util.concurrent.TimeUnit r5 = java.util.concurrent.TimeUnit.DAYS
            java.util.Calendar r6 = java.util.Calendar.getInstance()
            r7 = 6
            int r6 = r6.getActualMaximum(r7)
        L_0x0069:
            long r6 = (long) r6
        L_0x006a:
            long r5 = r5.toMillis(r6)
        L_0x006e:
            java.util.Date r7 = new java.util.Date
            long r8 = r0.getTime()
            int r0 = r13.l()
            long r11 = (long) r0
            long r11 = r11 * r5
            long r5 = r8 + r11
            r7.<init>(r5)
            r13.a(r7)
            boolean r0 = r13.n()
            if (r0 != 0) goto L_0x00c6
            java.util.Calendar r0 = java.util.Calendar.getInstance()
            java.util.Date r5 = r13.v()
            long r5 = r5.getTime()
            r0.setTimeInMillis(r5)
            r5 = 14
            r0.set(r5, r4)
            r5 = 13
            r0.set(r5, r4)
            int r5 = r13.m()
            r6 = 10
            r7 = 12
            switch(r5) {
                case 1: goto L_0x00b2;
                case 2: goto L_0x00b6;
                case 3: goto L_0x00ad;
                case 4: goto L_0x00b9;
                case 5: goto L_0x00bc;
                default: goto L_0x00ac;
            }
        L_0x00ac:
            goto L_0x00bf
        L_0x00ad:
            r5 = 7
            r0.set(r5, r2)
            goto L_0x00b9
        L_0x00b2:
            r5 = 2
            r0.set(r5, r4)
        L_0x00b6:
            r0.set(r10, r2)
        L_0x00b9:
            r0.set(r6, r4)
        L_0x00bc:
            r0.set(r7, r4)
        L_0x00bf:
            java.util.Date r0 = r0.getTime()
            r13.a(r0)
        L_0x00c6:
            int r0 = r13.x()
            if (r0 <= r3) goto L_0x00d7
            if (r1 <= r3) goto L_0x00d7
            int r0 = r13.x()
            if (r0 <= r1) goto L_0x00d7
            r13.c(r4)
        L_0x00d7:
            com.salesforce.marketingcloud.d.i r0 = r14.i()
            com.salesforce.marketingcloud.e.a r14 = r14.a()
            r0.a(r13, r14)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.messages.k.b(com.salesforce.marketingcloud.messages.c, com.salesforce.marketingcloud.d.h):void");
    }
}
