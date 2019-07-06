package defpackage;

import java.security.Provider;

/* renamed from: d reason: default package */
/* compiled from: GA */
public final class d extends Provider {
    public d() {
        super("AGCryptoProvider", 1.0d, "AGCryptoProvider v1.0, implementing SHA-224");
        put("Mac.HmacSHA224", "io.ag.crypto.HmacSHA224");
        put("MessageDigest.SHA-224", "io.ag.crypto.SHA224");
    }
}
