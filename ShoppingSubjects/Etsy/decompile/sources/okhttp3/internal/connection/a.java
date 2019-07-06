package okhttp3.internal.connection;

import com.etsy.android.lib.core.http.request.BaseHttpRequest;
import java.io.IOException;
import okhttp3.aa;
import okhttp3.internal.b.g;
import okhttp3.t;
import okhttp3.w;
import okhttp3.y;

/* compiled from: ConnectInterceptor */
public final class a implements t {
    public final w a;

    public a(w wVar) {
        this.a = wVar;
    }

    public aa a(okhttp3.t.a aVar) throws IOException {
        g gVar = (g) aVar;
        y a2 = gVar.a();
        f f = gVar.f();
        return gVar.a(a2, f, f.a(this.a, aVar, !a2.b().equals(BaseHttpRequest.GET)), f.c());
    }
}
