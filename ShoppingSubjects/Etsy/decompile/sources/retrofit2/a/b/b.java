package retrofit2.a.b;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.l;
import java.io.IOException;
import okhttp3.u;
import okhttp3.z;
import okio.c;
import okio.d;
import retrofit2.e;

/* compiled from: MoshiRequestBodyConverter */
final class b<T> implements e<T, z> {
    private static final u a = u.a("application/json; charset=UTF-8");
    private final JsonAdapter<T> b;

    b(JsonAdapter<T> jsonAdapter) {
        this.b = jsonAdapter;
    }

    /* renamed from: b */
    public z a(T t) throws IOException {
        c cVar = new c();
        this.b.toJson(l.a((d) cVar), t);
        return z.a(a, cVar.o());
    }
}
