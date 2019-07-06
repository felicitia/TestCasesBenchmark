package com.crittercism.internal;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Scanner;

public final class cn {
    public static void a(File file) {
        if (file.getAbsolutePath().contains("com.crittercism") && file.exists()) {
            if (file.isDirectory()) {
                for (File a : file.listFiles()) {
                    a(a);
                }
            }
            file.delete();
        }
    }

    public static String b(File file) {
        Scanner scanner = new Scanner(file);
        try {
            return scanner.useDelimiter("\\A").next();
        } finally {
            scanner.close();
        }
    }

    public static byte[] a(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[4096];
        while (true) {
            int read = inputStream.read(bArr);
            if (-1 == read) {
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x001d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(android.content.Context r1, java.lang.String r2) {
        /*
            android.content.res.AssetManager r1 = r1.getAssets()
            java.io.InputStream r1 = r1.open(r2)     // Catch:{ all -> 0x0019 }
            byte[] r2 = a(r1)     // Catch:{ all -> 0x0017 }
            java.lang.String r0 = new java.lang.String     // Catch:{ all -> 0x0017 }
            r0.<init>(r2)     // Catch:{ all -> 0x0017 }
            if (r1 == 0) goto L_0x0016
            r1.close()
        L_0x0016:
            return r0
        L_0x0017:
            r2 = move-exception
            goto L_0x001b
        L_0x0019:
            r2 = move-exception
            r1 = 0
        L_0x001b:
            if (r1 == 0) goto L_0x0020
            r1.close()
        L_0x0020:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.crittercism.internal.cn.a(android.content.Context, java.lang.String):java.lang.String");
    }
}
