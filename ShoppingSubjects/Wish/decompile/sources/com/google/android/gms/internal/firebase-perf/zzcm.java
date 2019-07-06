package com.google.android.gms.internal.firebase-perf;

import com.google.android.gms.internal.firebase-perf.zzcm;
import com.google.android.gms.internal.firebase-perf.zzcm.zza;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class zzcm<MessageType extends zzcm<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzaw<MessageType, BuilderType> {
    private static Map<Object, zzcm<?, ?>> zzme = new ConcurrentHashMap();
    protected zzfc zzmc = zzfc.zzfs();
    private int zzmd = -1;

    public static abstract class zza<MessageType extends zzcm<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzax<MessageType, BuilderType> {
        private final MessageType zzmf;
        private MessageType zzmg;
        private boolean zzmh = false;

        protected zza(MessageType messagetype) {
            this.zzmf = messagetype;
            this.zzmg = (zzcm) messagetype.zza(zze.zzmm, (Object) null, (Object) null);
        }

        public final boolean isInitialized() {
            return zzcm.zza(this.zzmg, false);
        }

        /* renamed from: zzdt */
        public MessageType zzdv() {
            if (this.zzmh) {
                return this.zzmg;
            }
            MessageType messagetype = this.zzmg;
            zzef.zzfa().zzo(messagetype).zzc(messagetype);
            this.zzmh = true;
            return this.zzmg;
        }

        /* renamed from: zzdu */
        public final MessageType zzdw() {
            MessageType messagetype = (zzcm) zzdv();
            boolean booleanValue = Boolean.TRUE.booleanValue();
            byte byteValue = ((Byte) messagetype.zza(zze.zzmj, (Object) null, (Object) null)).byteValue();
            boolean z = true;
            if (byteValue != 1) {
                if (byteValue == 0) {
                    z = false;
                } else {
                    z = zzef.zzfa().zzo(messagetype).zzn(messagetype);
                    if (booleanValue) {
                        messagetype.zza(zze.zzmk, (Object) z ? messagetype : null, (Object) null);
                    }
                }
            }
            if (z) {
                return messagetype;
            }
            throw new zzfa(messagetype);
        }

        public final BuilderType zza(MessageType messagetype) {
            if (this.zzmh) {
                MessageType messagetype2 = (zzcm) this.zzmg.zza(zze.zzmm, (Object) null, (Object) null);
                zza(messagetype2, this.zzmg);
                this.zzmg = messagetype2;
                this.zzmh = false;
            }
            zza(this.zzmg, messagetype);
            return this;
        }

        private static void zza(MessageType messagetype, MessageType messagetype2) {
            zzef.zzfa().zzo(messagetype).zzc(messagetype, messagetype2);
        }

        public final /* synthetic */ zzax zzbg() {
            return (zza) clone();
        }

        public final /* synthetic */ zzdt zzds() {
            return this.zzmf;
        }

        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            zza zza = (zza) ((zzcm) this.zzmf).zza(zze.zzmn, (Object) null, (Object) null);
            zza.zza((MessageType) (zzcm) zzdv());
            return zza;
        }
    }

    public static class zzb<T extends zzcm<T, ?>> extends zzay<T> {
        private final T zzmf;

        public zzb(T t) {
            this.zzmf = t;
        }

        public final /* synthetic */ Object zza(zzbo zzbo, zzbz zzbz) throws zzct {
            return zzcm.zza(this.zzmf, zzbo, zzbz);
        }
    }

    public static abstract class zzc<MessageType extends zzc<MessageType, BuilderType>, BuilderType> extends zzcm<MessageType, BuilderType> implements zzdv {
        protected zzcd<Object> zzmi = zzcd.zzdf();
    }

    public static class zzd<ContainingType extends zzdt, Type> extends zzbx<ContainingType, Type> {
    }

    /* 'enum' access flag removed */
    public static final class zze {
        public static final int zzmj = 1;
        public static final int zzmk = 2;
        public static final int zzml = 3;
        public static final int zzmm = 4;
        public static final int zzmn = 5;
        public static final int zzmo = 6;
        public static final int zzmp = 7;
        private static final /* synthetic */ int[] zzmq = {zzmj, zzmk, zzml, zzmm, zzmn, zzmo, zzmp};
        public static final int zzmr = 1;
        public static final int zzms = 2;
        private static final /* synthetic */ int[] zzmt = {zzmr, zzms};
        public static final int zzmu = 1;
        public static final int zzmv = 2;
        private static final /* synthetic */ int[] zzmw = {zzmu, zzmv};

        public static int[] values$50KLMJ33DTMIUPRFDTJMOP9FE1P6UT3FC9QMCBQ7CLN6ASJ1EHIM8JB5EDPM2PR59HKN8P949LIN8Q3FCHA6UIBEEPNMMP9R0() {
            return (int[]) zzmq.clone();
        }
    }

    /* access modifiers changed from: protected */
    public abstract Object zza(int i, Object obj, Object obj2);

    public String toString() {
        return zzdw.zza(this, super.toString());
    }

    public int hashCode() {
        if (this.zzhg != 0) {
            return this.zzhg;
        }
        this.zzhg = zzef.zzfa().zzo(this).hashCode(this);
        return this.zzhg;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!((zzcm) zza(zze.zzmo, (Object) null, (Object) null)).getClass().isInstance(obj)) {
            return false;
        }
        return zzef.zzfa().zzo(this).equals(this, (zzcm) obj);
    }

    public final boolean isInitialized() {
        boolean booleanValue = Boolean.TRUE.booleanValue();
        byte byteValue = ((Byte) zza(zze.zzmj, (Object) null, (Object) null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean zzn = zzef.zzfa().zzo(this).zzn(this);
        if (booleanValue) {
            zza(zze.zzmk, (Object) zzn ? this : null, (Object) null);
        }
        return zzn;
    }

    /* access modifiers changed from: 0000 */
    public final int zzbf() {
        return this.zzmd;
    }

    /* access modifiers changed from: 0000 */
    public final void zzf(int i) {
        this.zzmd = i;
    }

    public final void zzb(zzbt zzbt) throws IOException {
        zzef.zzfa().zzf(getClass()).zza(this, zzbv.zza(zzbt));
    }

    public final int zzdg() {
        if (this.zzmd == -1) {
            this.zzmd = zzef.zzfa().zzo(this).zzm(this);
        }
        return this.zzmd;
    }

    static <T extends zzcm<?, ?>> T zzd(Class<T> cls) {
        T t = (zzcm) zzme.get(cls);
        if (t == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                t = (zzcm) zzme.get(cls);
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

    protected static <T extends zzcm<?, ?>> void zza(Class<T> cls, T t) {
        zzme.put(cls, t);
    }

    protected static Object zza(zzdt zzdt, String str, Object[] objArr) {
        return new zzeh(zzdt, str, objArr);
    }

    static Object zza(Method method, Object obj, Object... objArr) {
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

    protected static final <T extends zzcm<T, ?>> boolean zza(T t, boolean z) {
        byte byteValue = ((Byte) t.zza(zze.zzmj, (Object) null, (Object) null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        return zzef.zzfa().zzo(t).zzn(t);
    }

    protected static <E> zzcs<E> zzdp() {
        return zzeg.zzfb();
    }

    static <T extends zzcm<T, ?>> T zza(T t, zzbo zzbo, zzbz zzbz) throws zzct {
        T t2 = (zzcm) t.zza(zze.zzmm, (Object) null, (Object) null);
        try {
            zzef.zzfa().zzo(t2).zza(t2, zzbr.zza(zzbo), zzbz);
            zzef.zzfa().zzo(t2).zzc(t2);
            return t2;
        } catch (IOException e) {
            if (e.getCause() instanceof zzct) {
                throw ((zzct) e.getCause());
            }
            throw new zzct(e.getMessage()).zzg(t2);
        } catch (RuntimeException e2) {
            if (e2.getCause() instanceof zzct) {
                throw ((zzct) e2.getCause());
            }
            throw e2;
        }
    }

    public final /* synthetic */ zzdu zzdq() {
        zza zza2 = (zza) zza(zze.zzmn, (Object) null, (Object) null);
        zza2.zza(this);
        return zza2;
    }

    public final /* synthetic */ zzdu zzdr() {
        return (zza) zza(zze.zzmn, (Object) null, (Object) null);
    }

    public final /* synthetic */ zzdt zzds() {
        return (zzcm) zza(zze.zzmo, (Object) null, (Object) null);
    }
}
