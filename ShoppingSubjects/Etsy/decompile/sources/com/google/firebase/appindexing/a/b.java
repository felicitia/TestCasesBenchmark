package com.google.firebase.appindexing.a;

import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.appindexing.d;
import com.google.firebase.appindexing.d.a;

public final class b {
    public static d a(@NonNull String str, @NonNull String str2) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(str2);
        return ((a) ((a) new a().b(str2)).a(str)).a();
    }
}
