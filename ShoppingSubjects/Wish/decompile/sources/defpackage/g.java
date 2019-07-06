package defpackage;

import okhttp3.Response;

/* renamed from: g reason: default package */
/* compiled from: GA */
public final class g extends j<Response> {
    private int d;

    public g(Response response, o oVar) {
        super(response, response.request().url().toString(), response.request().method(), oVar);
        this.d = response.code();
    }

    public final /* synthetic */ Object a() {
        if (!this.b) {
            return (Response) this.a;
        }
        String header = ((Response) this.a).header("X-Cbt");
        this.c.a(this.d, header != null && header.length() > 0);
        return ((Response) this.a).newBuilder().removeHeader("X-Cbt").build();
    }
}
