package defpackage;

import java.io.IOException;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.Buffer;

/* renamed from: f reason: default package */
/* compiled from: GA */
public final class f extends i<Request> {
    public f(Request request, o oVar) {
        super(request, request.url().toString(), request.method(), oVar);
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public Request a() {
        if (!this.b || this.d == null) {
            return (Request) this.a;
        }
        byte[] bArr = null;
        RequestBody body = ((Request) this.a).body();
        if (body != null) {
            Buffer buffer = new Buffer();
            try {
                body.writeTo(buffer);
            } catch (IOException unused) {
                buffer.clear();
            }
            byte[] readByteArray = buffer.readByteArray();
            buffer.close();
            bArr = readByteArray;
        }
        String a = this.c.a(this.d, ((Request) this.a).url().toString(), bArr);
        if (a.isEmpty()) {
            return (Request) this.a;
        }
        return ((Request) this.a).newBuilder().header("X-Cbt", a).build();
    }
}
