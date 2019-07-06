package kotlin;

import kotlin.jvm.internal.p;

/* compiled from: Standard.kt */
public final class NotImplementedError extends Error {
    public NotImplementedError() {
        this(null, 1, null);
    }

    public NotImplementedError(String str) {
        p.b(str, "message");
        super(str);
    }

    public /* synthetic */ NotImplementedError(String str, int i, o oVar) {
        if ((i & 1) != 0) {
            str = "An operation is not implemented.";
        }
        this(str);
    }
}
