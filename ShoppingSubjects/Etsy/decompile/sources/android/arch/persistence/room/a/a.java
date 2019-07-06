package android.arch.persistence.room.a;

import android.support.annotation.NonNull;

/* compiled from: Migration */
public abstract class a {
    public final int endVersion;
    public final int startVersion;

    public abstract void migrate(@NonNull android.arch.persistence.db.a aVar);

    public a(int i, int i2) {
        this.startVersion = i;
        this.endVersion = i2;
    }
}
