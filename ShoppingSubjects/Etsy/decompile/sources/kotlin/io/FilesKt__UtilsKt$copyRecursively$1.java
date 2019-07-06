package kotlin.io;

import java.io.File;
import java.io.IOException;
import kotlin.jvm.a.m;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.p;

/* compiled from: Utils.kt */
final class FilesKt__UtilsKt$copyRecursively$1 extends Lambda implements m {
    public static final FilesKt__UtilsKt$copyRecursively$1 INSTANCE = new FilesKt__UtilsKt$copyRecursively$1();

    FilesKt__UtilsKt$copyRecursively$1() {
        super(2);
    }

    public final Void invoke(File file, IOException iOException) {
        p.b(file, "<anonymous parameter 0>");
        p.b(iOException, "exception");
        throw iOException;
    }
}
