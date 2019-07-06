package com.google.android.gms.internal.icing;

import com.google.android.gms.internal.icing.zzbb;
import com.google.android.gms.internal.icing.zzbc;
import java.io.IOException;

public abstract class zzbb<MessageType extends zzbb<MessageType, BuilderType>, BuilderType extends zzbc<MessageType, BuilderType>> implements zzdr {
    private static boolean zzdj = false;
    protected int zzdi = 0;

    /* access modifiers changed from: 0000 */
    public void zze(int i) {
        throw new UnsupportedOperationException();
    }

    public final zzbi zzl() {
        try {
            zzbn zzj = zzbi.zzj(zzan());
            zzb(zzj.zzz());
            return zzj.zzy();
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
    public int zzm() {
        throw new UnsupportedOperationException();
    }
}
