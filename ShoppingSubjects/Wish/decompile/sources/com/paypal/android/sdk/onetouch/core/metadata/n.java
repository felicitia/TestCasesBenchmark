package com.paypal.android.sdk.onetouch.core.metadata;

import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;

public final class n {
    public static String a(File file) {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        byte[] bArr = new byte[((int) randomAccessFile.length())];
        randomAccessFile.readFully(bArr);
        randomAccessFile.close();
        return new String(bArr, "UTF-8");
    }

    public static void a(File file, String str) {
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(file);
            try {
                fileOutputStream.write(str.getBytes("UTF-8"));
                af.a(fileOutputStream);
            } catch (Throwable th) {
                th = th;
                af.a(fileOutputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream = null;
            af.a(fileOutputStream);
            throw th;
        }
    }
}
