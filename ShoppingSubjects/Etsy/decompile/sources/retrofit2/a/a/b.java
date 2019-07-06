package retrofit2.a.a;

import com.google.gson.q;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import okhttp3.u;
import okhttp3.z;
import okio.c;
import retrofit2.e;

/* compiled from: GsonRequestBodyConverter */
final class b<T> implements e<T, z> {
    private static final u a = u.a("application/json; charset=UTF-8");
    private static final Charset b = Charset.forName("UTF-8");
    private final com.google.gson.e c;
    private final q<T> d;

    b(com.google.gson.e eVar, q<T> qVar) {
        this.c = eVar;
        this.d = qVar;
    }

    /* renamed from: b */
    public z a(T t) throws IOException {
        c cVar = new c();
        com.google.gson.stream.b a2 = this.c.a((Writer) new OutputStreamWriter(cVar.d(), b));
        this.d.a(a2, t);
        a2.close();
        return z.a(a, cVar.o());
    }
}
