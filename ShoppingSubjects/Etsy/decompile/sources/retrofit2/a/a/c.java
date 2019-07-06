package retrofit2.a.a;

import com.google.gson.JsonIOException;
import com.google.gson.q;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.a;
import java.io.IOException;
import okhttp3.ab;
import retrofit2.e;

/* compiled from: GsonResponseBodyConverter */
final class c<T> implements e<ab, T> {
    private final com.google.gson.e a;
    private final q<T> b;

    c(com.google.gson.e eVar, q<T> qVar) {
        this.a = eVar;
        this.b = qVar;
    }

    public T a(ab abVar) throws IOException {
        a a2 = this.a.a(abVar.e());
        try {
            T b2 = this.b.b(a2);
            if (a2.f() == JsonToken.END_DOCUMENT) {
                return b2;
            }
            throw new JsonIOException("JSON document was not fully consumed.");
        } finally {
            abVar.close();
        }
    }
}
