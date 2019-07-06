package com.salesforce.marketingcloud.registration;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Size;

public interface d {

    public interface a {
        @NonNull
        a a(@Size(min = 1) @NonNull String str);

        boolean a();
    }

    public interface b {
        void a(@NonNull c cVar);
    }

    @Nullable
    String a();

    @NonNull
    String c();

    @NonNull
    a d();
}
