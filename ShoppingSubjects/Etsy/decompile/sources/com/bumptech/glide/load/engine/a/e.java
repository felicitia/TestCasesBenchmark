package com.bumptech.glide.load.engine.a;

import android.util.Log;
import com.bumptech.glide.a.a;
import com.bumptech.glide.a.a.C0007a;
import com.bumptech.glide.a.a.c;
import com.bumptech.glide.load.b;
import java.io.File;
import java.io.IOException;

/* compiled from: DiskLruCacheWrapper */
public class e implements a {
    private static e a;
    private final c b = new c();
    private final j c;
    private final File d;
    private final int e;
    private a f;

    public static synchronized a a(File file, int i) {
        e eVar;
        synchronized (e.class) {
            if (a == null) {
                a = new e(file, i);
            }
            eVar = a;
        }
        return eVar;
    }

    protected e(File file, int i) {
        this.d = file;
        this.e = i;
        this.c = new j();
    }

    private synchronized a a() throws IOException {
        if (this.f == null) {
            this.f = a.a(this.d, 1, 1, (long) this.e);
        }
        return this.f;
    }

    public File a(b bVar) {
        try {
            c a2 = a().a(this.c.a(bVar));
            if (a2 != null) {
                return a2.a(0);
            }
            return null;
        } catch (IOException e2) {
            if (!Log.isLoggable("DiskLruCacheWrapper", 5)) {
                return null;
            }
            Log.w("DiskLruCacheWrapper", "Unable to get from disk cache", e2);
            return null;
        }
    }

    public void a(b bVar, a.b bVar2) {
        C0007a b2;
        String a2 = this.c.a(bVar);
        this.b.a(bVar);
        try {
            b2 = a().b(a2);
            if (b2 != null) {
                if (bVar2.a(b2.a(0))) {
                    b2.a();
                }
                b2.c();
            }
        } catch (IOException e2) {
            try {
                if (Log.isLoggable("DiskLruCacheWrapper", 5)) {
                    Log.w("DiskLruCacheWrapper", "Unable to put to disk cache", e2);
                }
            } catch (Throwable th) {
                this.b.b(bVar);
                throw th;
            }
        } catch (Throwable th2) {
            b2.c();
            throw th2;
        }
        this.b.b(bVar);
    }

    public void b(b bVar) {
        try {
            a().c(this.c.a(bVar));
        } catch (IOException e2) {
            if (Log.isLoggable("DiskLruCacheWrapper", 5)) {
                Log.w("DiskLruCacheWrapper", "Unable to delete from disk cache", e2);
            }
        }
    }
}
