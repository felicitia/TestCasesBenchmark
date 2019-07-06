package kotlin.io;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FileReadWrite.kt */
class FilesKt__FileReadWriteKt extends FilesKt__FilePathComponentsKt {
    public static final byte[] readBytes(File file) {
        Throwable th;
        Intrinsics.checkParameterIsNotNull(file, "$receiver");
        Closeable fileInputStream = new FileInputStream(file);
        Throwable th2 = null;
        try {
            FileInputStream fileInputStream2 = (FileInputStream) fileInputStream;
            int i = 0;
            long length = file.length();
            if (length > ((long) Integer.MAX_VALUE)) {
                StringBuilder sb = new StringBuilder();
                sb.append("File ");
                sb.append(file);
                sb.append(" is too big (");
                sb.append(length);
                sb.append(" bytes) to fit in memory.");
                throw new OutOfMemoryError(sb.toString());
            }
            int i2 = (int) length;
            byte[] bArr = new byte[i2];
            while (true) {
                if (i2 <= 0) {
                    break;
                }
                int read = fileInputStream2.read(bArr, i, i2);
                if (read < 0) {
                    break;
                }
                i2 -= read;
                i += read;
            }
            if (i2 != 0) {
                bArr = Arrays.copyOf(bArr, i);
                Intrinsics.checkExpressionValueIsNotNull(bArr, "java.util.Arrays.copyOf(this, newSize)");
            }
            CloseableKt.closeFinally(fileInputStream, th2);
            return bArr;
        } catch (Throwable th3) {
            CloseableKt.closeFinally(fileInputStream, th);
            throw th3;
        }
    }
}
