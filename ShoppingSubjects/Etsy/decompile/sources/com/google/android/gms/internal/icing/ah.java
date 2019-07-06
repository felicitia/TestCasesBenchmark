package com.google.android.gms.internal.icing;

import com.google.android.gms.internal.icing.ah;
import com.google.android.gms.internal.icing.ah.a;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class ah<MessageType extends ah<MessageType, BuilderType>, BuilderType extends a<MessageType, BuilderType>> extends e<MessageType, BuilderType> {
    private static Map<Object, ah<?, ?>> zzho = new ConcurrentHashMap();
    protected cs zzhm = cs.a();
    private int zzhn = -1;

    public static abstract class a<MessageType extends ah<MessageType, BuilderType>, BuilderType extends a<MessageType, BuilderType>> extends f<MessageType, BuilderType> {
        private final MessageType a;
        private MessageType b;
        private boolean c = false;

        protected a(MessageType messagetype) {
            this.a = messagetype;
            this.b = (ah) messagetype.a(d.d, (Object) null, (Object) null);
        }

        private static void a(MessageType messagetype, MessageType messagetype2) {
            bx.a().a(messagetype).b(messagetype, messagetype2);
        }

        public final BuilderType a(MessageType messagetype) {
            if (this.c) {
                MessageType messagetype2 = (ah) this.b.a(d.d, (Object) null, (Object) null);
                a(messagetype2, this.b);
                this.b = messagetype2;
                this.c = false;
            }
            a(this.b, messagetype);
            return this;
        }

        public final /* synthetic */ f a() {
            return (a) clone();
        }

        public final /* synthetic */ bl b() {
            if (this.c) {
                return this.b;
            }
            MessageType messagetype = this.b;
            bx.a().a(messagetype).c(messagetype);
            this.c = true;
            return this.b;
        }

        public final boolean c() {
            return ah.a(this.b, false);
        }

        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            a aVar = (a) ((ah) this.a).a(d.e, (Object) null, (Object) null);
            if (!this.c) {
                MessageType messagetype = this.b;
                bx.a().a(messagetype).c(messagetype);
                this.c = true;
            }
            aVar.a((MessageType) (ah) this.b);
            return aVar;
        }

        public final /* synthetic */ bl d() {
            boolean z = true;
            if (!this.c) {
                MessageType messagetype = this.b;
                bx.a().a(messagetype).c(messagetype);
                this.c = true;
            }
            ah ahVar = (ah) this.b;
            boolean booleanValue = Boolean.TRUE.booleanValue();
            byte byteValue = ((Byte) ahVar.a(d.a, (Object) null, (Object) null)).byteValue();
            if (byteValue != 1) {
                if (byteValue == 0) {
                    z = false;
                } else {
                    z = bx.a().a(ahVar).d(ahVar);
                    if (booleanValue) {
                        ahVar.a(d.b, (Object) z ? ahVar : null, (Object) null);
                    }
                }
            }
            if (z) {
                return ahVar;
            }
            throw new zzew(ahVar);
        }

        public final /* synthetic */ bl j() {
            return this.a;
        }
    }

    public static class b<T extends ah<T, ?>> extends g<T> {
        private T a;

        public b(T t) {
            this.a = t;
        }
    }

    public static abstract class c<MessageType extends c<MessageType, BuilderType>, BuilderType> extends ah<MessageType, BuilderType> implements bn {
        protected ac<Object> zzhs = ac.a();
    }

    /* 'enum' access flag removed */
    public static final class d {
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

    static <T extends ah<?, ?>> T a(Class<T> cls) {
        T t = (ah) zzho.get(cls);
        if (t == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                t = (ah) zzho.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (t != null) {
            return t;
        }
        String str = "Unable to get default instance for: ";
        String valueOf = String.valueOf(cls.getName());
        throw new IllegalStateException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
    }

    protected static Object a(bl blVar, String str, Object[] objArr) {
        return new bz(blVar, str, objArr);
    }

    static Object a(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            } else if (cause instanceof Error) {
                throw ((Error) cause);
            } else {
                throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
            }
        }
    }

    protected static <T extends ah<?, ?>> void a(Class<T> cls, T t) {
        zzho.put(cls, t);
    }

    protected static final <T extends ah<T, ?>> boolean a(T t, boolean z) {
        byte byteValue = ((Byte) t.a(d.a, (Object) null, (Object) null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        return bx.a().a(t).d(t);
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.google.android.gms.internal.icing.an, com.google.android.gms.internal.icing.bb] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v0, types: [com.google.android.gms.internal.icing.an, com.google.android.gms.internal.icing.bb]
      assigns: [com.google.android.gms.internal.icing.bb]
      uses: [com.google.android.gms.internal.icing.an]
      mth insns count: 2
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at jadx.core.dex.visitors.typeinference.TypeSearch$$Lambda$100/183835416.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at jadx.core.dex.visitors.DepthTraversal$$Lambda$33/170174037.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at jadx.core.ProcessClass$$Lambda$38/2083670723.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
    	at jadx.api.JadxDecompiler$$Lambda$28/1919834117.run(Unknown Source)
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static com.google.android.gms.internal.icing.an e() {
        /*
            com.google.android.gms.internal.icing.bb r0 = com.google.android.gms.internal.icing.bb.d()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.ah.e():com.google.android.gms.internal.icing.an");
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.google.android.gms.internal.icing.v, com.google.android.gms.internal.icing.al] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v0, types: [com.google.android.gms.internal.icing.v, com.google.android.gms.internal.icing.al]
      assigns: [com.google.android.gms.internal.icing.v]
      uses: [com.google.android.gms.internal.icing.al]
      mth insns count: 2
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at jadx.core.dex.visitors.typeinference.TypeSearch$$Lambda$100/183835416.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at jadx.core.dex.visitors.DepthTraversal$$Lambda$33/170174037.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at jadx.core.ProcessClass$$Lambda$38/2083670723.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
    	at jadx.api.JadxDecompiler$$Lambda$28/1919834117.run(Unknown Source)
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static com.google.android.gms.internal.icing.al f() {
        /*
            com.google.android.gms.internal.icing.v r0 = com.google.android.gms.internal.icing.v.d()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.ah.f():com.google.android.gms.internal.icing.al");
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.google.android.gms.internal.icing.ak, com.google.android.gms.internal.icing.j] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v0, types: [com.google.android.gms.internal.icing.ak, com.google.android.gms.internal.icing.j]
      assigns: [com.google.android.gms.internal.icing.j]
      uses: [com.google.android.gms.internal.icing.ak]
      mth insns count: 2
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at jadx.core.dex.visitors.typeinference.TypeSearch$$Lambda$100/183835416.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at jadx.core.dex.visitors.DepthTraversal$$Lambda$33/170174037.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at jadx.core.ProcessClass$$Lambda$38/2083670723.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
    	at jadx.api.JadxDecompiler$$Lambda$28/1919834117.run(Unknown Source)
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static com.google.android.gms.internal.icing.ak g() {
        /*
            com.google.android.gms.internal.icing.j r0 = com.google.android.gms.internal.icing.j.d()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.ah.g():com.google.android.gms.internal.icing.ak");
    }

    protected static <E> ao<E> h() {
        return by.d();
    }

    /* access modifiers changed from: protected */
    public abstract Object a(int i, Object obj, Object obj2);

    /* access modifiers changed from: 0000 */
    public final void a(int i) {
        this.zzhn = i;
    }

    public final void a(zzbu zzbu) throws IOException {
        bx.a().a(getClass()).a(this, (df) u.a(zzbu));
    }

    /* access modifiers changed from: 0000 */
    public final int b() {
        return this.zzhn;
    }

    public final boolean c() {
        boolean booleanValue = Boolean.TRUE.booleanValue();
        byte byteValue = ((Byte) a(d.a, (Object) null, (Object) null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean d2 = bx.a().a(this).d(this);
        if (booleanValue) {
            a(d.b, (Object) d2 ? this : null, (Object) null);
        }
        return d2;
    }

    public final int d() {
        if (this.zzhn == -1) {
            this.zzhn = bx.a().a(this).b(this);
        }
        return this.zzhn;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!((ah) a(d.f, (Object) null, (Object) null)).getClass().isInstance(obj)) {
            return false;
        }
        return bx.a().a(this).a(this, (ah) obj);
    }

    public int hashCode() {
        if (this.zzdi != 0) {
            return this.zzdi;
        }
        this.zzdi = bx.a().a(this).a(this);
        return this.zzdi;
    }

    public final /* synthetic */ bm i() {
        a aVar = (a) a(d.e, (Object) null, (Object) null);
        aVar.a(this);
        return aVar;
    }

    public final /* synthetic */ bl j() {
        return (ah) a(d.f, (Object) null, (Object) null);
    }

    public String toString() {
        return bo.a(this, super.toString());
    }
}
