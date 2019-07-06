package com.threatmetrix.TrustDefender.internal;

import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;
import com.threatmetrix.TrustDefender.internal.D2.E;
import com.threatmetrix.TrustDefender.internal.P.O;
import java.lang.reflect.Method;

class Z extends D2 {

    /* renamed from: byte reason: not valid java name */
    private static final Method f710byte;

    /* renamed from: case reason: not valid java name */
    private static final Method f711case;

    /* renamed from: char reason: not valid java name */
    private static final Method f712char;

    /* renamed from: do reason: not valid java name */
    private static final Class<?> f713do = m38do(E.WINDOW_MANAGER);

    /* renamed from: else reason: not valid java name */
    private static final Method f714else;

    /* renamed from: for reason: not valid java name */
    private static final String f715for = TL.m331if(Z.class);

    /* renamed from: if reason: not valid java name */
    private static final Method f716if = m44for(Display.class, "getHeight", new Class[0]);

    /* renamed from: int reason: not valid java name */
    private static final Method f717int = m44for(Display.class, "getWidth", new Class[0]);

    /* renamed from: new reason: not valid java name */
    private static final Class<?> f718new = m38do(E.POINT);

    /* renamed from: try reason: not valid java name */
    private static final Method f719try = m44for(f713do, "getDefaultDisplay", new Class[0]);

    /* renamed from: goto reason: not valid java name */
    private Point f720goto;

    /* renamed from: long reason: not valid java name */
    private Display f721long;

    static {
        if (f718new != null) {
            f714else = m44for(Display.class, "getSize", Point.class);
            f710byte = m44for(Display.class, "getRealSize", Point.class);
            f711case = m44for(Display.class, "getRawWidth", Point.class);
            f712char = m44for(Display.class, "getRawHeight", Point.class);
            return;
        }
        f712char = null;
        f711case = null;
        f710byte = null;
        f714else = null;
    }

    Z(O o) {
        if (f719try != null) {
            try {
                Object systemService = o.f487for.getSystemService("window");
                if (systemService != null && (systemService instanceof WindowManager)) {
                    this.f721long = ((WindowManager) systemService).getDefaultDisplay();
                }
            } catch (SecurityException unused) {
            } catch (Exception e) {
                TL.m338new(f715for, e.toString());
            }
        }
        Point point = null;
        if (!(f718new == null || f710byte == null)) {
            Point point2 = new Point();
            m39do((Object) this.f721long, f710byte, point2);
            if (!(point2.x == 0 || point2.y == 0)) {
                point = point2;
            }
        }
        this.f720goto = point;
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0029 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x002a  */
    /* renamed from: new reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int m455new() {
        /*
            r5 = this;
            android.view.Display r0 = r5.f721long
            r1 = 0
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            android.graphics.Point r0 = r5.f720goto
            if (r0 == 0) goto L_0x000f
            android.graphics.Point r0 = r5.f720goto
            int r0 = r0.x
            return r0
        L_0x000f:
            java.lang.reflect.Method r0 = f711case
            if (r0 == 0) goto L_0x0026
            android.view.Display r0 = r5.f721long
            java.lang.reflect.Method r2 = f711case
            java.lang.Object[] r3 = new java.lang.Object[r1]
            java.lang.Object r0 = m39do(r0, r2, r3)
            java.lang.Integer r0 = (java.lang.Integer) r0
            if (r0 == 0) goto L_0x0026
            int r0 = r0.intValue()
            goto L_0x0027
        L_0x0026:
            r0 = 0
        L_0x0027:
            if (r0 == 0) goto L_0x002a
            return r0
        L_0x002a:
            java.lang.reflect.Method r0 = f714else
            if (r0 == 0) goto L_0x0042
            android.graphics.Point r0 = new android.graphics.Point
            r0.<init>()
            android.view.Display r2 = r5.f721long
            java.lang.reflect.Method r3 = f714else
            r4 = 1
            java.lang.Object[] r4 = new java.lang.Object[r4]
            r4[r1] = r0
            m39do(r2, r3, r4)
            int r0 = r0.x
            return r0
        L_0x0042:
            java.lang.reflect.Method r0 = f717int
            if (r0 == 0) goto L_0x0059
            android.view.Display r0 = r5.f721long
            java.lang.reflect.Method r2 = f717int
            java.lang.Object[] r3 = new java.lang.Object[r1]
            java.lang.Object r0 = m39do(r0, r2, r3)
            java.lang.Integer r0 = (java.lang.Integer) r0
            if (r0 == 0) goto L_0x0059
            int r0 = r0.intValue()
            return r0
        L_0x0059:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.internal.Z.m455new():int");
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0029 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x002a  */
    /* renamed from: do reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int m454do() {
        /*
            r5 = this;
            android.view.Display r0 = r5.f721long
            r1 = 0
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            android.graphics.Point r0 = r5.f720goto
            if (r0 == 0) goto L_0x000f
            android.graphics.Point r0 = r5.f720goto
            int r0 = r0.y
            return r0
        L_0x000f:
            java.lang.reflect.Method r0 = f712char
            if (r0 == 0) goto L_0x0026
            android.view.Display r0 = r5.f721long
            java.lang.reflect.Method r2 = f712char
            java.lang.Object[] r3 = new java.lang.Object[r1]
            java.lang.Object r0 = m39do(r0, r2, r3)
            java.lang.Integer r0 = (java.lang.Integer) r0
            if (r0 == 0) goto L_0x0026
            int r0 = r0.intValue()
            goto L_0x0027
        L_0x0026:
            r0 = 0
        L_0x0027:
            if (r0 == 0) goto L_0x002a
            return r0
        L_0x002a:
            java.lang.reflect.Method r0 = f714else
            if (r0 == 0) goto L_0x0042
            android.graphics.Point r0 = new android.graphics.Point
            r0.<init>()
            android.view.Display r2 = r5.f721long
            java.lang.reflect.Method r3 = f714else
            r4 = 1
            java.lang.Object[] r4 = new java.lang.Object[r4]
            r4[r1] = r0
            m39do(r2, r3, r4)
            int r0 = r0.y
            return r0
        L_0x0042:
            java.lang.reflect.Method r0 = f716if
            if (r0 == 0) goto L_0x0059
            android.view.Display r0 = r5.f721long
            java.lang.reflect.Method r2 = f716if
            java.lang.Object[] r3 = new java.lang.Object[r1]
            java.lang.Object r0 = m39do(r0, r2, r3)
            java.lang.Integer r0 = (java.lang.Integer) r0
            if (r0 == 0) goto L_0x0059
            int r0 = r0.intValue()
            return r0
        L_0x0059:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.internal.Z.m454do():int");
    }
}
