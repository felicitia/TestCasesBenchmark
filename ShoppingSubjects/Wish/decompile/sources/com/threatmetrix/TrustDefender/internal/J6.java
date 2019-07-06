package com.threatmetrix.TrustDefender.internal;

import com.threatmetrix.TrustDefender.internal.D2.E;
import java.lang.reflect.Method;

class J6 extends D2 {

    /* renamed from: byte reason: not valid java name */
    private static final Method f239byte = m44for(f245new, "getBlockCountLong", new Class[0]);

    /* renamed from: case reason: not valid java name */
    private static final Method f240case = m44for(f245new, "getBlockCount", new Class[0]);

    /* renamed from: do reason: not valid java name */
    static final Method f241do = m44for(f245new, "getAvailableBlocks", new Class[0]);

    /* renamed from: else reason: not valid java name */
    private static final String f242else = TL.m331if(J6.class);

    /* renamed from: for reason: not valid java name */
    static final Method f243for = m44for(f245new, "getAvailableBlocksLong", new Class[0]);

    /* renamed from: if reason: not valid java name */
    private static final Method f244if;

    /* renamed from: new reason: not valid java name */
    private static final Class<?> f245new;

    /* renamed from: try reason: not valid java name */
    private static final Method f246try = m44for(f245new, "getBlockSizeLong", new Class[0]);

    /* renamed from: int reason: not valid java name */
    final Object f247int;

    static {
        Class<?> cls = m38do(E.STAT_FS);
        f245new = cls;
        f244if = m44for(cls, "getBlockSize", new Class[0]);
    }

    public J6(String str) {
        if (f245new == null) {
            this.f247int = null;
            return;
        }
        this.f247int = D2.m45if(f245new, new Class[]{String.class}, new Object[]{str});
    }

    /* renamed from: int reason: not valid java name */
    public final long m102int() {
        if (f239byte != null) {
            Long l = (Long) m39do(this.f247int, f239byte, new Object[0]);
            if (l != null) {
                return l.longValue();
            }
        }
        if (f240case != null) {
            Integer num = (Integer) m39do(this.f247int, f240case, new Object[0]);
            if (num != null) {
                return (long) num.intValue();
            }
        }
        return 0;
    }

    /* renamed from: if reason: not valid java name */
    public final long m101if() {
        if (f246try != null) {
            Long l = (Long) m39do(this.f247int, f246try, new Object[0]);
            if (l != null) {
                return l.longValue();
            }
        }
        if (f244if != null) {
            Integer num = (Integer) m39do(this.f247int, f244if, new Object[0]);
            if (num != null) {
                return (long) num.intValue();
            }
        }
        return 0;
    }
}
