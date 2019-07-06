package okhttp3.internal.http2;

import java.io.IOException;
import java.util.List;
import okio.e;

/* compiled from: PushObserver */
public interface j {
    public static final j a = new j() {
        public void a(int i, ErrorCode errorCode) {
        }

        public boolean a(int i, List<a> list) {
            return true;
        }

        public boolean a(int i, List<a> list, boolean z) {
            return true;
        }

        public boolean a(int i, e eVar, int i2, boolean z) throws IOException {
            eVar.i((long) i2);
            return true;
        }
    };

    void a(int i, ErrorCode errorCode);

    boolean a(int i, List<a> list);

    boolean a(int i, List<a> list, boolean z);

    boolean a(int i, e eVar, int i2, boolean z) throws IOException;
}
