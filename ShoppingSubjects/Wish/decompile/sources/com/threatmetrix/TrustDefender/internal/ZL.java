package com.threatmetrix.TrustDefender.internal;

import com.threatmetrix.TrustDefender.internal.P.O;
import com.threatmetrix.TrustDefender.internal.XU.K;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ZL implements K {
    /* access modifiers changed from: private */

    /* renamed from: new reason: not valid java name */
    public static final String f741new = TL.m331if(ZL.class);
    /* access modifiers changed from: private */

    /* renamed from: do reason: not valid java name */
    public volatile boolean f742do = false;
    /* access modifiers changed from: private */

    /* renamed from: if reason: not valid java name */
    public volatile String f743if = null;

    /* renamed from: int reason: not valid java name */
    private volatile String f744int = null;

    class L implements InvocationHandler {
        private L() {
        }

        /* synthetic */ L(ZL zl, byte b) {
            this();
        }

        public final Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            if ("onResult".equals(method.getName())) {
                W w = J.f184do;
                if (!(w == null || objArr == null || objArr.length <= 0)) {
                    if (J.m90for(objArr[0])) {
                        Object obj2 = D2.m39do(objArr[0], w.f237volatile, new Object[0]);
                        if (obj2 != null && (obj2 instanceof String)) {
                            ZL.this.f743if = (String) obj2;
                        }
                    } else {
                        ZL.f741new;
                        ZL.this.f742do = false;
                    }
                }
            }
            return null;
        }
    }

    /* renamed from: new reason: not valid java name */
    public final void m463new(O o, String str) {
        if (!this.f742do) {
            try {
                byte[] bytes = str.getBytes("UTF-8");
                if (bytes.length >= 16) {
                    J.m89for(o.f487for, bytes, new L(this, 0));
                    this.f742do = true;
                    this.f744int = str;
                    return;
                }
                TL.m338new(f741new, "SafetyNet failure: Invalid nonce format");
            } catch (UnsupportedEncodingException unused) {
            }
        }
    }

    /* renamed from: do reason: not valid java name */
    public final String m461do() {
        return this.f743if;
    }

    /* renamed from: new reason: not valid java name */
    public final String m462new() {
        return this.f744int;
    }
}
