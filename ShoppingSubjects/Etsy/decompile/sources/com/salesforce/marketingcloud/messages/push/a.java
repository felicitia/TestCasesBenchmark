package com.salesforce.marketingcloud.messages.push;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import com.salesforce.marketingcloud.notifications.NotificationMessage;

public abstract class a {
    @RestrictTo({Scope.LIBRARY})
    public static String a = "com.salesforce.marketingcloud.notifications.PUSH_ENABLED";
    @RestrictTo({Scope.LIBRARY})
    public static String b = "com.salesforce.marketingcloud.push.TOKEN";

    /* renamed from: com.salesforce.marketingcloud.messages.push.a$a reason: collision with other inner class name */
    public interface C0172a {
        void a(@Nullable String str);
    }

    public interface b {
        void a(@NonNull Bundle bundle);
    }

    public static boolean a(@NonNull Bundle bundle) {
        return bundle != null && (bundle.containsKey(NotificationMessage.a) || bundle.containsKey("_nodes"));
    }

    @Nullable
    public abstract String a();

    public abstract boolean b(@NonNull Bundle bundle);

    public abstract boolean c();
}
