package okhttp3.internal.http2;

import com.etsy.android.lib.convos.Draft;
import okhttp3.internal.c;
import okio.ByteString;

/* compiled from: Header */
public final class a {
    public static final ByteString a = ByteString.encodeUtf8(Draft.IMAGE_DELIMITER);
    public static final ByteString b = ByteString.encodeUtf8(":status");
    public static final ByteString c = ByteString.encodeUtf8(":method");
    public static final ByteString d = ByteString.encodeUtf8(":path");
    public static final ByteString e = ByteString.encodeUtf8(":scheme");
    public static final ByteString f = ByteString.encodeUtf8(":authority");
    public final ByteString g;
    public final ByteString h;
    final int i;

    public a(String str, String str2) {
        this(ByteString.encodeUtf8(str), ByteString.encodeUtf8(str2));
    }

    public a(ByteString byteString, String str) {
        this(byteString, ByteString.encodeUtf8(str));
    }

    public a(ByteString byteString, ByteString byteString2) {
        this.g = byteString;
        this.h = byteString2;
        this.i = 32 + byteString.size() + byteString2.size();
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        if (this.g.equals(aVar.g) && this.h.equals(aVar.h)) {
            z = true;
        }
        return z;
    }

    public int hashCode() {
        return (31 * (527 + this.g.hashCode())) + this.h.hashCode();
    }

    public String toString() {
        return c.a("%s: %s", this.g.utf8(), this.h.utf8());
    }
}
