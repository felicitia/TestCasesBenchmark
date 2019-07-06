package kotlin.io;

import com.etsy.android.lib.models.ResponseConstants;
import java.io.File;
import java.io.IOException;
import kotlin.jvm.internal.p;

/* compiled from: Exceptions.kt */
public class FileSystemException extends IOException {
    private final File file;
    private final File other;
    private final String reason;

    public final File getFile() {
        return this.file;
    }

    public /* synthetic */ FileSystemException(File file2, File file3, String str, int i, o oVar) {
        if ((i & 2) != 0) {
            file3 = null;
        }
        if ((i & 4) != 0) {
            str = null;
        }
        this(file2, file3, str);
    }

    public final File getOther() {
        return this.other;
    }

    public final String getReason() {
        return this.reason;
    }

    public FileSystemException(File file2, File file3, String str) {
        p.b(file2, ResponseConstants.FILE);
        super(b.b(file2, file3, str));
        this.file = file2;
        this.other = file3;
        this.reason = str;
    }
}
