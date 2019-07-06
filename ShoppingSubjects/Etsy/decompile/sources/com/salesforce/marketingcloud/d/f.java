package com.salesforce.marketingcloud.d;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.List;

public interface f {

    public enum a {
        UNREAD,
        READ,
        DELETED,
        NOT_DELETED
    }

    int a();

    @Nullable
    com.salesforce.marketingcloud.messages.c.a a(@NonNull String str, @NonNull com.salesforce.marketingcloud.e.a aVar);

    List<com.salesforce.marketingcloud.messages.c.a> a(@NonNull com.salesforce.marketingcloud.e.a aVar);

    void a(@NonNull com.salesforce.marketingcloud.messages.c.a aVar, @NonNull com.salesforce.marketingcloud.e.a aVar2);

    int b(@NonNull com.salesforce.marketingcloud.messages.c.a aVar, @NonNull com.salesforce.marketingcloud.e.a aVar2);
}
