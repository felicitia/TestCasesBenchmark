package com.google.android.gms.internal.ads;

import android.util.Base64OutputStream;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@VisibleForTesting
final class agn {
    @VisibleForTesting
    private ByteArrayOutputStream a = new ByteArrayOutputStream(4096);
    @VisibleForTesting
    private Base64OutputStream b = new Base64OutputStream(this.a, 10);

    public final void a(byte[] bArr) throws IOException {
        this.b.write(bArr);
    }

    public final String toString() {
        String str;
        try {
            this.b.close();
        } catch (IOException e) {
            gv.b("HashManager: Unable to convert to Base64.", e);
        }
        try {
            this.a.close();
            str = this.a.toString();
        } catch (IOException e2) {
            gv.b("HashManager: Unable to convert to Base64.", e2);
            str = "";
        } catch (Throwable th) {
            this.a = null;
            this.b = null;
            throw th;
        }
        this.a = null;
        this.b = null;
        return str;
    }
}
