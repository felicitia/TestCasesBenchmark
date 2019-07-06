package kotlin.io;

import com.etsy.android.lib.models.ResponseConstants;
import java.io.File;
import kotlin.jvm.internal.p;

/* compiled from: Exceptions.kt */
public final class NoSuchFileException extends FileSystemException {
    public /* synthetic */ NoSuchFileException(File file, File file2, String str, int i, o oVar) {
        if ((i & 2) != 0) {
            file2 = null;
        }
        if ((i & 4) != 0) {
            str = null;
        }
        this(file, file2, str);
    }

    public NoSuchFileException(File file, File file2, String str) {
        p.b(file, ResponseConstants.FILE);
        super(file, file2, str);
    }
}
