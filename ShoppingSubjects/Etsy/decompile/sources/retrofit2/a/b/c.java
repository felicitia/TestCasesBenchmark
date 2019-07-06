package retrofit2.a.b;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonReader.Token;
import java.io.IOException;
import okhttp3.ab;
import okio.ByteString;
import retrofit2.e;

/* compiled from: MoshiResponseBodyConverter */
final class c<T> implements e<ab, T> {
    private static final ByteString a = ByteString.decodeHex("EFBBBF");
    private final JsonAdapter<T> b;

    c(JsonAdapter<T> jsonAdapter) {
        this.b = jsonAdapter;
    }

    public T a(ab abVar) throws IOException {
        okio.e c = abVar.c();
        try {
            if (c.a(0, a)) {
                c.i((long) a.size());
            }
            JsonReader a2 = JsonReader.a(c);
            T fromJson = this.b.fromJson(a2);
            if (a2.h() == Token.END_DOCUMENT) {
                return fromJson;
            }
            throw new JsonDataException("JSON document was not fully consumed.");
        } finally {
            abVar.close();
        }
    }
}
