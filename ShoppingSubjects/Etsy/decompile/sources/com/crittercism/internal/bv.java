package com.crittercism.internal;

import android.app.Activity;
import android.app.Application;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import java.util.ArrayDeque;
import java.util.Deque;

public final class bv extends br {
    public av b;
    public Deque<String> c = new ArrayDeque();

    public final void e() {
    }

    public bv(@NonNull Application application, @NonNull av avVar) {
        super(application);
        if (VERSION.SDK_INT < 9) {
            throw new IllegalStateException("Activity monitoring is only supported on API 9 and later");
        }
        this.b = avVar;
        a();
    }

    private static String d(Activity activity) {
        if (activity == null) {
            return null;
        }
        return activity.getComponentName().flattenToShortString().replace("/", "");
    }

    private void g() {
        this.b.g = (String) this.c.peekFirst();
    }

    public final void b(Activity activity) {
        if (this.c.size() >= 10000) {
            this.c.removeLast();
        }
        String d = d(activity);
        if (d != null) {
            this.c.addFirst(d);
            g();
        }
    }

    public final void c(Activity activity) {
        String d = d(activity);
        if (d != null) {
            this.c.removeFirstOccurrence(d);
            g();
        }
    }
}
