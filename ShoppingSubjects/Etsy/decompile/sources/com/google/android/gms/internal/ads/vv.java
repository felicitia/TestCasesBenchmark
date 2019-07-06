package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.vv;
import com.google.android.gms.internal.ads.vw;
import java.io.IOException;

public abstract class vv<MessageType extends vv<MessageType, BuilderType>, BuilderType extends vw<MessageType, BuilderType>> implements yk {
    private static boolean zzdpg = false;
    protected int zzdpf = 0;

    /* access modifiers changed from: 0000 */
    public void a(int i) {
        throw new UnsupportedOperationException();
    }

    public final zzbah h() {
        try {
            wj zzbo = zzbah.zzbo(l());
            a(zzbo.b());
            return zzbo.a();
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

    public final byte[] i() {
        try {
            byte[] bArr = new byte[l()];
            zzbav a = zzbav.a(bArr);
            a(a);
            a.b();
            return bArr;
        } catch (IOException e) {
            String str = "byte array";
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
    public int j() {
        throw new UnsupportedOperationException();
    }
}
