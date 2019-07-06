package com.google.android.gms.internal.firebase-perf;

import com.google.android.gms.internal.firebase-perf.zzaw;
import com.google.android.gms.internal.firebase-perf.zzax;
import java.io.IOException;

public abstract class zzaw<MessageType extends zzaw<MessageType, BuilderType>, BuilderType extends zzax<MessageType, BuilderType>> implements zzdt {
    private static boolean zzhh = false;
    protected int zzhg = 0;

    public final zzbd zzbe() {
        try {
            zzbk zzk = zzbd.zzk(zzdg());
            zzb(zzk.zzbr());
            return zzk.zzbq();
        } catch (IOException e) {
            String str = "ByteString";
            String name = getClass().getName();
            StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 62 + String.valueOf(str).length());
            sb.append("Serializing ");
            sb.append(name);
            sb.append(" to a ");
            sb.append(str);
            sb.append(" threw an IOException (should never happen).");
            throw new RuntimeException(sb.toString(), e);
        }
    }

    /* access modifiers changed from: 0000 */
    public int zzbf() {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: 0000 */
    public void zzf(int i) {
        throw new UnsupportedOperationException();
    }
}
