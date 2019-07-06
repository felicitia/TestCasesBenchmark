package defpackage;

import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: n reason: default package */
/* compiled from: GA */
public final class n {
    private volatile AtomicReference<String> a = new AtomicReference<>();
    private volatile AtomicReference<String> b = new AtomicReference<>();

    public n() {
        b("00");
        a("00");
    }

    public final void a(String str) {
        this.a.set(str);
    }

    public final void b(String str) {
        this.b.set(str);
    }

    public final byte[] a() {
        ByteBuffer allocate = ByteBuffer.allocate(4);
        synchronized (this) {
            allocate.put(((String) this.b.get()).getBytes());
            allocate.put(((String) this.a.get()).getBytes());
        }
        allocate.flip();
        return allocate.array();
    }
}
