package kotlin.io;

import java.io.File;
import java.io.IOException;
import kotlin.h;
import kotlin.jvm.a.m;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.p;

/* compiled from: Utils.kt */
final class FilesKt__UtilsKt$copyRecursively$2 extends Lambda implements m<File, IOException, h> {
    final /* synthetic */ m $onError;

    FilesKt__UtilsKt$copyRecursively$2(m mVar) {
        this.$onError = mVar;
        super(2);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((File) obj, (IOException) obj2);
        return h.a;
    }

    public final void invoke(File file, IOException iOException) {
        p.b(file, "f");
        p.b(iOException, "e");
        if (((OnErrorAction) this.$onError.invoke(file, iOException)) == OnErrorAction.TERMINATE) {
            throw new TerminateException(file);
        }
    }
}
