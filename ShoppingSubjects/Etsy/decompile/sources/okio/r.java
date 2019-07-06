package okio;

import android.support.v4.media.session.PlaybackStateCompat;

/* compiled from: SegmentPool */
final class r {
    static q a;
    static long b;

    private r() {
    }

    static q a() {
        synchronized (r.class) {
            if (a == null) {
                return new q();
            }
            q qVar = a;
            a = qVar.f;
            qVar.f = null;
            b -= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
            return qVar;
        }
    }

    static void a(q qVar) {
        if (qVar.f != null || qVar.g != null) {
            throw new IllegalArgumentException();
        } else if (!qVar.d) {
            synchronized (r.class) {
                if (b + PlaybackStateCompat.ACTION_PLAY_FROM_URI <= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH) {
                    b += PlaybackStateCompat.ACTION_PLAY_FROM_URI;
                    qVar.f = a;
                    qVar.c = 0;
                    qVar.b = 0;
                    a = qVar;
                }
            }
        }
    }
}
