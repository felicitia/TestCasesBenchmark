package com.google.android.gms.internal.icing;

import com.google.android.gms.internal.icing.zzck;
import com.google.android.gms.internal.icing.zzck.zza;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class zzck<MessageType extends zzck<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzbb<MessageType, BuilderType> {
    private static Map<Object, zzck<?, ?>> zzho = new ConcurrentHashMap();
    protected zzey zzhm = zzey.zzcq();
    private int zzhn = -1;

    public static abstract class zza<MessageType extends zzck<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzbc<MessageType, BuilderType> {
        private final MessageType zzhp;
        private MessageType zzhq;
        private boolean zzhr = false;

        protected zza(MessageType messagetype) {
            this.zzhp = messagetype;
            this.zzhq = (zzck) messagetype.zza(zzd.zzhw, (Object) null, (Object) null);
        }

        private static void zza(MessageType messagetype, MessageType messagetype2) {
            zzec.zzbz().zzl(messagetype).zzc(messagetype, messagetype2);
        }

        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            zza zza = (zza) ((zzck) this.zzhp).zza(zzd.zzhx, (Object) null, (Object) null);
            if (!this.zzhr) {
                MessageType messagetype = this.zzhq;
                zzec.zzbz().zzl(messagetype).zzc(messagetype);
                this.zzhr = true;
            }
            zza.zza((MessageType) (zzck) this.zzhq);
            return zza;
        }

        public final boolean isInitialized() {
            return zzck.zza(this.zzhq, false);
        }

        public final BuilderType zza(MessageType messagetype) {
            if (this.zzhr) {
                MessageType messagetype2 = (zzck) this.zzhq.zza(zzd.zzhw, (Object) null, (Object) null);
                zza(messagetype2, this.zzhq);
                this.zzhq = messagetype2;
                this.zzhr = false;
            }
            zza(this.zzhq, messagetype);
            return this;
        }

        public final /* synthetic */ zzdr zzba() {
            return this.zzhp;
        }

        public final /* synthetic */ zzdr zzbb() {
            if (this.zzhr) {
                return this.zzhq;
            }
            MessageType messagetype = this.zzhq;
            zzec.zzbz().zzl(messagetype).zzc(messagetype);
            this.zzhr = true;
            return this.zzhq;
        }

        public final /* synthetic */ zzdr zzbc() {
            boolean z = true;
            if (!this.zzhr) {
                MessageType messagetype = this.zzhq;
                zzec.zzbz().zzl(messagetype).zzc(messagetype);
                this.zzhr = true;
            }
            zzck zzck = (zzck) this.zzhq;
            boolean booleanValue = Boolean.TRUE.booleanValue();
            byte byteValue = ((Byte) zzck.zza(zzd.zzht, (Object) null, (Object) null)).byteValue();
            if (byteValue != 1) {
                if (byteValue == 0) {
                    z = false;
                } else {
                    z = zzec.zzbz().zzl(zzck).zzk(zzck);
                    if (booleanValue) {
                        zzck.zza(zzd.zzhu, (Object) z ? zzck : null, (Object) null);
                    }
                }
            }
            if (z) {
                return zzck;
            }
            throw new zzew(zzck);
        }

        public final /* synthetic */ zzbc zzn() {
            return (zza) clone();
        }
    }

    public static class zzb<T extends zzck<T, ?>> extends zzbd<T> {
        private T zzhp;

        public zzb(T t) {
            this.zzhp = t;
        }
    }

    public static abstract class zzc<MessageType extends zzc<MessageType, BuilderType>, BuilderType> extends zzck<MessageType, BuilderType> implements zzdt {
        protected zzcd<Object> zzhs = zzcd.zzam();
    }

    /* 'enum' access flag removed */
    public static final class zzd {
        public static final int zzht = 1;
        public static final int zzhu = 2;
        public static final int zzhv = 3;
        public static final int zzhw = 4;
        public static final int zzhx = 5;
        public static final int zzhy = 6;
        public static final int zzhz = 7;
        private static final /* synthetic */ int[] zzia = {zzht, zzhu, zzhv, zzhw, zzhx, zzhy, zzhz};
        public static final int zzib = 1;
        public static final int zzic = 2;
        private static final /* synthetic */ int[] zzid = {zzib, zzic};
        public static final int zzie = 1;
        public static final int zzif = 2;
        private static final /* synthetic */ int[] zzig = {zzie, zzif};

        public static int[] values$50KLMJ33DTMIUPRFDTJMOP9FE1P6UT3FC9QMCBQ7CLN6ASJ1EHIM8JB5EDPM2PR59HKN8P949LIN8Q3FCHA6UIBEEPNMMP9R0() {
            return (int[]) zzia.clone();
        }
    }

    protected static Object zza(zzdr zzdr, String str, Object[] objArr) {
        return new zzee(zzdr, str, objArr);
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

    protected static <T extends zzck<?, ?>> void zza(Class<T> cls, T t) {
        zzho.put(cls, t);
    }

    protected static final <T extends zzck<T, ?>> boolean zza(T t, boolean z) {
        byte byteValue = ((Byte) t.zza(zzd.zzht, (Object) null, (Object) null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        return zzec.zzbz().zzl(t).zzk(t);
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.google.android.gms.internal.icing.zzcq, com.google.android.gms.internal.icing.zzdg] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v0, types: [com.google.android.gms.internal.icing.zzcq, com.google.android.gms.internal.icing.zzdg]
      assigns: [com.google.android.gms.internal.icing.zzdg]
      uses: [com.google.android.gms.internal.icing.zzcq]
      mth insns count: 2
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at jadx.core.dex.visitors.typeinference.TypeSearch$$Lambda$100/871566395.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at jadx.core.dex.visitors.DepthTraversal$$Lambda$34/1534130292.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at jadx.core.ProcessClass$$Lambda$69/1017384824.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
    	at jadx.api.JadxDecompiler$$Lambda$28/1037163664.run(Unknown Source)
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static com.google.android.gms.internal.icing.zzcq zzav() {
        /*
            com.google.android.gms.internal.icing.zzdg r0 = com.google.android.gms.internal.icing.zzdg.zzbl()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.zzck.zzav():com.google.android.gms.internal.icing.zzcq");
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.google.android.gms.internal.icing.zzco, com.google.android.gms.internal.icing.zzbx] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v0, types: [com.google.android.gms.internal.icing.zzco, com.google.android.gms.internal.icing.zzbx]
      assigns: [com.google.android.gms.internal.icing.zzbx]
      uses: [com.google.android.gms.internal.icing.zzco]
      mth insns count: 2
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at jadx.core.dex.visitors.typeinference.TypeSearch$$Lambda$100/871566395.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at jadx.core.dex.visitors.DepthTraversal$$Lambda$34/1534130292.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at jadx.core.ProcessClass$$Lambda$69/1017384824.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
    	at jadx.api.JadxDecompiler$$Lambda$28/1037163664.run(Unknown Source)
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static com.google.android.gms.internal.icing.zzco zzaw() {
        /*
            com.google.android.gms.internal.icing.zzbx r0 = com.google.android.gms.internal.icing.zzbx.zzae()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.zzck.zzaw():com.google.android.gms.internal.icing.zzco");
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.google.android.gms.internal.icing.zzcn, com.google.android.gms.internal.icing.zzbg] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v0, types: [com.google.android.gms.internal.icing.zzcn, com.google.android.gms.internal.icing.zzbg]
      assigns: [com.google.android.gms.internal.icing.zzbg]
      uses: [com.google.android.gms.internal.icing.zzcn]
      mth insns count: 2
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at jadx.core.dex.visitors.typeinference.TypeSearch$$Lambda$100/871566395.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at jadx.core.dex.visitors.DepthTraversal$$Lambda$34/1534130292.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at jadx.core.ProcessClass$$Lambda$69/1017384824.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
    	at jadx.api.JadxDecompiler$$Lambda$28/1037163664.run(Unknown Source)
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static com.google.android.gms.internal.icing.zzcn zzax() {
        /*
            com.google.android.gms.internal.icing.zzbg r0 = com.google.android.gms.internal.icing.zzbg.zzt()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.zzck.zzax():com.google.android.gms.internal.icing.zzcn");
    }

    protected static <E> zzcr<E> zzay() {
        return zzed.zzca();
    }

    static <T extends zzck<?, ?>> T zzc(Class<T> cls) {
        T t = (zzck) zzho.get(cls);
        if (t == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                t = (zzck) zzho.get(cls);
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

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!((zzck) zza(zzd.zzhy, (Object) null, (Object) null)).getClass().isInstance(obj)) {
            return false;
        }
        return zzec.zzbz().zzl(this).equals(this, (zzck) obj);
    }

    public int hashCode() {
        if (this.zzdi != 0) {
            return this.zzdi;
        }
        this.zzdi = zzec.zzbz().zzl(this).hashCode(this);
        return this.zzdi;
    }

    public final boolean isInitialized() {
        boolean booleanValue = Boolean.TRUE.booleanValue();
        byte byteValue = ((Byte) zza(zzd.zzht, (Object) null, (Object) null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean zzk = zzec.zzbz().zzl(this).zzk(this);
        if (booleanValue) {
            zza(zzd.zzhu, (Object) zzk ? this : null, (Object) null);
        }
        return zzk;
    }

    public String toString() {
        return zzdu.zza(this, super.toString());
    }

    /* access modifiers changed from: protected */
    public abstract Object zza(int i, Object obj, Object obj2);

    public final int zzan() {
        if (this.zzhn == -1) {
            this.zzhn = zzec.zzbz().zzl(this).zzj(this);
        }
        return this.zzhn;
    }

    public final /* synthetic */ zzds zzaz() {
        zza zza2 = (zza) zza(zzd.zzhx, (Object) null, (Object) null);
        zza2.zza(this);
        return zza2;
    }

    public final void zzb(zzbu zzbu) throws IOException {
        zzec.zzbz().zze(getClass()).zza(this, zzbw.zza(zzbu));
    }

    public final /* synthetic */ zzdr zzba() {
        return (zzck) zza(zzd.zzhy, (Object) null, (Object) null);
    }

    /* access modifiers changed from: 0000 */
    public final void zze(int i) {
        this.zzhn = i;
    }

    /* access modifiers changed from: 0000 */
    public final int zzm() {
        return this.zzhn;
    }
}
