package kotlin.io;

import java.io.File;
import kotlin.jvm.internal.p;

/* compiled from: Exceptions.kt */
public final class b {
    /* access modifiers changed from: private */
    public static final String b(File file, File file2, String str) {
        StringBuilder sb = new StringBuilder(file.toString());
        if (file2 != null) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(" -> ");
            sb2.append(file2);
            sb.append(sb2.toString());
        }
        if (str != null) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(": ");
            sb3.append(str);
            sb.append(sb3.toString());
        }
        String sb4 = sb.toString();
        p.a((Object) sb4, "sb.toString()");
        return sb4;
    }
}
