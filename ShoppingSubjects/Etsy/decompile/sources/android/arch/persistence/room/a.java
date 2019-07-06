package android.arch.persistence.room;

import android.arch.persistence.db.b.c;
import android.arch.persistence.room.RoomDatabase.JournalMode;
import android.arch.persistence.room.RoomDatabase.b;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import java.util.List;
import java.util.Set;

/* compiled from: DatabaseConfiguration */
public class a {
    @NonNull
    public final c a;
    @NonNull
    public final Context b;
    @Nullable
    public final String c;
    @NonNull
    public final RoomDatabase.c d;
    @Nullable
    public final List<b> e;
    public final boolean f;
    public final JournalMode g;
    public final boolean h;
    private final Set<Integer> i;

    @RestrictTo({Scope.LIBRARY_GROUP})
    public a(@NonNull Context context, @Nullable String str, @NonNull c cVar, @NonNull RoomDatabase.c cVar2, @Nullable List<b> list, boolean z, JournalMode journalMode, boolean z2, @Nullable Set<Integer> set) {
        this.a = cVar;
        this.b = context;
        this.c = str;
        this.d = cVar2;
        this.e = list;
        this.f = z;
        this.g = journalMode;
        this.h = z2;
        this.i = set;
    }

    public boolean a(int i2) {
        return this.h && (this.i == null || !this.i.contains(Integer.valueOf(i2)));
    }
}
