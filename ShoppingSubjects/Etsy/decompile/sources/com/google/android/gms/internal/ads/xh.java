package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.xh;
import com.google.android.gms.internal.ads.xh.a;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class xh<MessageType extends xh<MessageType, BuilderType>, BuilderType extends a<MessageType, BuilderType>> extends vv<MessageType, BuilderType> {
    private static Map<Object, xh<?, ?>> zzdtv = new ConcurrentHashMap();
    protected zw zzdtt = zw.a();
    private int zzdtu = -1;

    public static abstract class a<MessageType extends xh<MessageType, BuilderType>, BuilderType extends a<MessageType, BuilderType>> extends vw<MessageType, BuilderType> {
        protected MessageType a;
        private final MessageType b;
        private boolean c = false;

        protected a(MessageType messagetype) {
            this.b = messagetype;
            this.a = (xh) messagetype.a(e.d, (Object) null, (Object) null);
        }

        private static void a(MessageType messagetype, MessageType messagetype2) {
            yx.a().a(messagetype).b(messagetype, messagetype2);
        }

        public final /* synthetic */ vw a() {
            return (a) clone();
        }

        public final BuilderType a(MessageType messagetype) {
            b();
            a(this.a, messagetype);
            return this;
        }

        /* access modifiers changed from: protected */
        public final void b() {
            if (this.c) {
                MessageType messagetype = (xh) this.a.a(e.d, (Object) null, (Object) null);
                a(messagetype, this.a);
                this.a = messagetype;
                this.c = false;
            }
        }

        public final MessageType c() {
            boolean z = true;
            if (!this.c) {
                MessageType messagetype = this.a;
                yx.a().a(messagetype).c(messagetype);
                this.c = true;
            }
            MessageType messagetype2 = (xh) this.a;
            boolean booleanValue = Boolean.TRUE.booleanValue();
            byte byteValue = ((Byte) messagetype2.a(e.a, (Object) null, (Object) null)).byteValue();
            if (byteValue != 1) {
                if (byteValue == 0) {
                    z = false;
                } else {
                    z = yx.a().a(messagetype2).d(messagetype2);
                    if (booleanValue) {
                        messagetype2.a(e.b, (Object) z ? messagetype2 : null, (Object) null);
                    }
                }
            }
            if (z) {
                return messagetype2;
            }
            throw new zzbed(messagetype2);
        }

        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            a aVar = (a) ((xh) this.b).a(e.e, (Object) null, (Object) null);
            if (!this.c) {
                MessageType messagetype = this.a;
                yx.a().a(messagetype).c(messagetype);
                this.c = true;
            }
            aVar.a((MessageType) (xh) this.a);
            return aVar;
        }

        public final /* synthetic */ yk d() {
            if (this.c) {
                return this.a;
            }
            MessageType messagetype = this.a;
            yx.a().a(messagetype).c(messagetype);
            this.c = true;
            return this.a;
        }

        public final /* synthetic */ yk e() {
            boolean z = true;
            if (!this.c) {
                MessageType messagetype = this.a;
                yx.a().a(messagetype).c(messagetype);
                this.c = true;
            }
            xh xhVar = (xh) this.a;
            boolean booleanValue = Boolean.TRUE.booleanValue();
            byte byteValue = ((Byte) xhVar.a(e.a, (Object) null, (Object) null)).byteValue();
            if (byteValue != 1) {
                if (byteValue == 0) {
                    z = false;
                } else {
                    z = yx.a().a(xhVar).d(xhVar);
                    if (booleanValue) {
                        xhVar.a(e.b, (Object) z ? xhVar : null, (Object) null);
                    }
                }
            }
            if (z) {
                return xhVar;
            }
            throw new zzbed(xhVar);
        }

        public final boolean k() {
            return xh.a(this.a, false);
        }

        public final /* synthetic */ yk p() {
            return this.b;
        }
    }

    public static class b<T extends xh<T, ?>> extends vz<T> {
        private T a;

        public b(T t) {
            this.a = t;
        }
    }

    public static abstract class c<MessageType extends c<MessageType, BuilderType>, BuilderType> extends xh<MessageType, BuilderType> implements ym {
        protected xb<Object> zzdtz = xb.a();
    }

    public static class d<ContainingType extends yk, Type> extends wt<ContainingType, Type> {
    }

    /* 'enum' access flag removed */
    public static final class e {
        public static final int a = 1;
        public static final int b = 2;
        public static final int c = 3;
        public static final int d = 4;
        public static final int e = 5;
        public static final int f = 6;
        public static final int g = 7;
        public static final int h = 1;
        public static final int i = 2;
        public static final int j = 1;
        public static final int k = 2;
        private static final /* synthetic */ int[] l = {a, b, c, d, e, f, g};
        private static final /* synthetic */ int[] m = {h, i};
        private static final /* synthetic */ int[] n = {j, k};

        public static int[] a() {
            return (int[]) l.clone();
        }
    }

    private static <T extends xh<T, ?>> T a(T t, wl wlVar, ww wwVar) throws zzbbu {
        T t2 = (xh) t.a(e.d, (Object) null, (Object) null);
        try {
            yx.a().a(t2).a(t2, wo.a(wlVar), wwVar);
            yx.a().a(t2).c(t2);
            return t2;
        } catch (IOException e2) {
            if (e2.getCause() instanceof zzbbu) {
                throw ((zzbbu) e2.getCause());
            }
            throw new zzbbu(e2.getMessage()).zzj(t2);
        } catch (RuntimeException e3) {
            if (e3.getCause() instanceof zzbbu) {
                throw ((zzbbu) e3.getCause());
            }
            throw e3;
        }
    }

    protected static <T extends xh<T, ?>> T a(T t, zzbah zzbah) throws zzbbu {
        boolean z;
        T a2 = a(t, zzbah, ww.a());
        boolean z2 = false;
        if (a2 != null) {
            boolean booleanValue = Boolean.TRUE.booleanValue();
            byte byteValue = ((Byte) a2.a(e.a, (Object) null, (Object) null)).byteValue();
            if (byteValue == 1) {
                z = true;
            } else if (byteValue == 0) {
                z = false;
            } else {
                z = yx.a().a(a2).d(a2);
                if (booleanValue) {
                    a2.a(e.b, (Object) z ? a2 : null, (Object) null);
                }
            }
            if (!z) {
                throw new zzbed(a2).zzaga().zzj(a2);
            }
        }
        if (a2 != null) {
            boolean booleanValue2 = Boolean.TRUE.booleanValue();
            byte byteValue2 = ((Byte) a2.a(e.a, (Object) null, (Object) null)).byteValue();
            if (byteValue2 == 1) {
                z2 = true;
            } else if (byteValue2 != 0) {
                z2 = yx.a().a(a2).d(a2);
                if (booleanValue2) {
                    a2.a(e.b, (Object) z2 ? a2 : null, (Object) null);
                }
            }
            if (!z2) {
                throw new zzbed(a2).zzaga().zzj(a2);
            }
        }
        return a2;
    }

    private static <T extends xh<T, ?>> T a(T t, zzbah zzbah, ww wwVar) throws zzbbu {
        T a2;
        try {
            wl zzabf = zzbah.zzabf();
            a2 = a(t, zzabf, wwVar);
            zzabf.a(0);
            return a2;
        } catch (zzbbu e2) {
            throw e2.zzj(a2);
        } catch (zzbbu e3) {
            throw e3;
        }
    }

    protected static <T extends xh<T, ?>> T a(T t, byte[] bArr) throws zzbbu {
        T b2 = b(t, bArr);
        if (b2 != null) {
            boolean booleanValue = Boolean.TRUE.booleanValue();
            byte byteValue = ((Byte) b2.a(e.a, (Object) null, (Object) null)).byteValue();
            boolean z = true;
            if (byteValue != 1) {
                if (byteValue == 0) {
                    z = false;
                } else {
                    z = yx.a().a(b2).d(b2);
                    if (booleanValue) {
                        b2.a(e.b, (Object) z ? b2 : null, (Object) null);
                    }
                }
            }
            if (!z) {
                throw new zzbed(b2).zzaga().zzj(b2);
            }
        }
        return b2;
    }

    static <T extends xh<?, ?>> T a(Class<T> cls) {
        T t = (xh) zzdtv.get(cls);
        if (t == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                t = (xh) zzdtv.get(cls);
            } catch (ClassNotFoundException e2) {
                throw new IllegalStateException("Class initialization cannot fail.", e2);
            }
        }
        if (t != null) {
            return t;
        }
        String str = "Unable to get default instance for: ";
        String valueOf = String.valueOf(cls.getName());
        throw new IllegalStateException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
    }

    protected static Object a(yk ykVar, String str, Object[] objArr) {
        return new yz(ykVar, str, objArr);
    }

    static Object a(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e2);
        } catch (InvocationTargetException e3) {
            Throwable cause = e3.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            } else if (cause instanceof Error) {
                throw ((Error) cause);
            } else {
                throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
            }
        }
    }

    protected static <T extends xh<?, ?>> void a(Class<T> cls, T t) {
        zzdtv.put(cls, t);
    }

    protected static final <T extends xh<T, ?>> boolean a(T t, boolean z) {
        byte byteValue = ((Byte) t.a(e.a, (Object) null, (Object) null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        return yx.a().a(t).d(t);
    }

    private static <T extends xh<T, ?>> T b(T t, byte[] bArr) throws zzbbu {
        T t2 = (xh) t.a(e.d, (Object) null, (Object) null);
        try {
            yx.a().a(t2).a(t2, bArr, 0, bArr.length, new wd());
            yx.a().a(t2).c(t2);
            if (t2.zzdpf == 0) {
                return t2;
            }
            throw new RuntimeException();
        } catch (IOException e2) {
            if (e2.getCause() instanceof zzbbu) {
                throw ((zzbbu) e2.getCause());
            }
            throw new zzbbu(e2.getMessage()).zzj(t2);
        } catch (IndexOutOfBoundsException unused) {
            throw zzbbu.zzadl().zzj(t2);
        }
    }

    protected static <E> xm<E> m() {
        return yy.d();
    }

    /* access modifiers changed from: protected */
    public abstract Object a(int i, Object obj, Object obj2);

    /* access modifiers changed from: 0000 */
    public final void a(int i) {
        this.zzdtu = i;
    }

    public final void a(zzbav zzbav) throws IOException {
        yx.a().a(getClass()).a(this, (aai) wr.a(zzbav));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!((xh) a(e.f, (Object) null, (Object) null)).getClass().isInstance(obj)) {
            return false;
        }
        return yx.a().a(this).a(this, (xh) obj);
    }

    public int hashCode() {
        if (this.zzdpf != 0) {
            return this.zzdpf;
        }
        this.zzdpf = yx.a().a(this).a(this);
        return this.zzdpf;
    }

    /* access modifiers changed from: 0000 */
    public final int j() {
        return this.zzdtu;
    }

    public final boolean k() {
        boolean booleanValue = Boolean.TRUE.booleanValue();
        byte byteValue = ((Byte) a(e.a, (Object) null, (Object) null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean d2 = yx.a().a(this).d(this);
        if (booleanValue) {
            a(e.b, (Object) d2 ? this : null, (Object) null);
        }
        return d2;
    }

    public final int l() {
        if (this.zzdtu == -1) {
            this.zzdtu = yx.a().a(this).b(this);
        }
        return this.zzdtu;
    }

    public final /* synthetic */ yl n() {
        a aVar = (a) a(e.e, (Object) null, (Object) null);
        aVar.a(this);
        return aVar;
    }

    public final /* synthetic */ yl o() {
        return (a) a(e.e, (Object) null, (Object) null);
    }

    public final /* synthetic */ yk p() {
        return (xh) a(e.f, (Object) null, (Object) null);
    }

    public String toString() {
        return yn.a(this, super.toString());
    }
}
