package androidx.work.impl;

import android.net.Network;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import androidx.work.Data;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestrictTo({Scope.LIBRARY_GROUP})
public class Extras {
    @NonNull
    private Data a;
    @NonNull
    private Set<String> b;
    @Nullable
    private a c;
    private int d;

    @RestrictTo({Scope.LIBRARY_GROUP})
    public static class a {
        public String[] a;
        public Uri[] b;
        @RequiresApi(28)
        public Network c;
    }

    public Extras(@NonNull Data data, @NonNull List<String> list, @Nullable a aVar, int i) {
        this.a = data;
        this.b = new HashSet(list);
        this.c = aVar;
        this.d = i;
    }

    @NonNull
    public Data a() {
        return this.a;
    }
}
