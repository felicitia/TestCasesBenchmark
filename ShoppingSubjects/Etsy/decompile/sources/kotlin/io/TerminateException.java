package kotlin.io;

import com.etsy.android.lib.models.ResponseConstants;
import java.io.File;
import kotlin.jvm.internal.p;

/* compiled from: Utils.kt */
final class TerminateException extends FileSystemException {
    public TerminateException(File file) {
        p.b(file, ResponseConstants.FILE);
        super(file, null, null, 6, null);
    }
}
