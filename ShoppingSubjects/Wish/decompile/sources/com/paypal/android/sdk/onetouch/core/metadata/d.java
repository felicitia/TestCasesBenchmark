package com.paypal.android.sdk.onetouch.core.metadata;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public final class d {
    private boolean a = false;
    private boolean b = false;
    private File c;

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0034  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x003e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public d() {
        /*
            r5 = this;
            r5.<init>()
            r0 = 0
            r5.a = r0
            r5.b = r0
            java.lang.String r1 = android.os.Environment.getExternalStorageState()
            int r2 = r1.hashCode()
            r3 = 1242932856(0x4a15a678, float:2451870.0)
            r4 = 1
            if (r2 == r3) goto L_0x0026
            r3 = 1299749220(0x4d789964, float:2.60675136E8)
            if (r2 == r3) goto L_0x001c
            goto L_0x0030
        L_0x001c:
            java.lang.String r2 = "mounted_ro"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0030
            r1 = 1
            goto L_0x0031
        L_0x0026:
            java.lang.String r2 = "mounted"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0030
            r1 = 0
            goto L_0x0031
        L_0x0030:
            r1 = -1
        L_0x0031:
            switch(r1) {
                case 0: goto L_0x003e;
                case 1: goto L_0x0039;
                default: goto L_0x0034;
            }
        L_0x0034:
            r5.b = r0
            r5.a = r0
            goto L_0x0042
        L_0x0039:
            r5.a = r4
            r5.b = r0
            goto L_0x0042
        L_0x003e:
            r5.b = r4
            r5.a = r4
        L_0x0042:
            java.io.File r0 = android.os.Environment.getExternalStorageDirectory()
            r5.c = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.paypal.android.sdk.onetouch.core.metadata.d.<init>():void");
    }

    public final void a(String str) {
        this.c = new File(str);
    }

    public final void a(String str, byte[] bArr) {
        FileOutputStream fileOutputStream;
        if (this.a && this.b) {
            FileOutputStream fileOutputStream2 = null;
            try {
                if (!this.c.mkdirs()) {
                    if (!this.c.isDirectory()) {
                        fileOutputStream = null;
                        af.a(fileOutputStream);
                    }
                }
                fileOutputStream = new FileOutputStream(new File(this.c, str));
                try {
                    fileOutputStream.write(bArr);
                    af.a(fileOutputStream);
                } catch (Throwable th) {
                    fileOutputStream2 = fileOutputStream;
                    th = th;
                    af.a(fileOutputStream2);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                af.a(fileOutputStream2);
                throw th;
            }
        }
    }

    public final String b(String str) {
        FileInputStream fileInputStream;
        byte[] bArr = new byte[1024];
        if (this.b) {
            try {
                fileInputStream = new FileInputStream(new File(this.c, str));
                try {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    for (int read = fileInputStream.read(bArr, 0, 1024); read != -1; read = fileInputStream.read(bArr, 0, 1024)) {
                        byteArrayOutputStream.write(bArr, 0, read);
                    }
                    bArr = byteArrayOutputStream.toByteArray();
                    af.a(fileInputStream);
                } catch (IOException unused) {
                    String str2 = "";
                    af.a(fileInputStream);
                    return str2;
                } catch (Throwable th) {
                    th = th;
                    af.a(fileInputStream);
                    throw th;
                }
            } catch (IOException unused2) {
                fileInputStream = null;
                String str22 = "";
                af.a(fileInputStream);
                return str22;
            } catch (Throwable th2) {
                th = th2;
                fileInputStream = null;
                af.a(fileInputStream);
                throw th;
            }
        }
        return new String(bArr, "UTF-8");
    }
}
