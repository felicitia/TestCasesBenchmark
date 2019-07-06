package android.arch.persistence.db.framework;

import android.arch.persistence.db.b;
import android.arch.persistence.db.b.C0002b;
import android.arch.persistence.db.b.c;

public final class FrameworkSQLiteOpenHelperFactory implements c {
    public b create(C0002b bVar) {
        return new b(bVar.a, bVar.b, bVar.c);
    }
}
