package com.bumptech.glide.a;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/* compiled from: Util */
final class c {
    static final Charset a = Charset.forName("US-ASCII");
    static final Charset b = Charset.forName("UTF-8");

    static void a(File file) throws IOException {
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("not a readable directory: ");
            sb.append(file);
            throw new IOException(sb.toString());
        }
        for (File file2 : listFiles) {
            if (file2.isDirectory()) {
                a(file2);
            }
            if (!file2.delete()) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("failed to delete file: ");
                sb2.append(file2);
                throw new IOException(sb2.toString());
            }
        }
    }

    static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception unused) {
            }
        }
    }
}
