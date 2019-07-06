package defpackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: r reason: default package */
/* compiled from: GA */
public abstract class r {
    public List<String> a = Collections.synchronizedList(new ArrayList());

    public abstract void a();

    public abstract boolean b();

    public abstract void c();

    /* access modifiers changed from: protected */
    public final void a(String str) {
        if (str != null && !str.isEmpty()) {
            this.a.add(str);
        }
    }
}
