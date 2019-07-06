package com.google.android.gms.internal.icing;

import com.google.android.gms.internal.icing.e;
import com.google.android.gms.internal.icing.f;
import java.io.IOException;

public abstract class e<MessageType extends e<MessageType, BuilderType>, BuilderType extends f<MessageType, BuilderType>> implements bl {
    private static boolean zzdj = false;
    protected int zzdi = 0;

    public final zzbi a() {
        try {
            o zzj = zzbi.zzj(d());
            a(zzj.b());
            return zzj.a();
        } catch (IOException e) {
            String str = "ByteString";
            String name = getClass().getName();
            StringBuilder sb = new StringBuilder(62 + String.valueOf(name).length() + String.valueOf(str).length());
            sb.append("Serializing ");
            sb.append(name);
            sb.append(" to a ");
            sb.append(str);
            sb.append(" threw an IOException (should never happen).");
            throw new RuntimeException(sb.toString(), e);
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(int i) {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: 0000 */
    public int b() {
        throw new UnsupportedOperationException();
    }
}
