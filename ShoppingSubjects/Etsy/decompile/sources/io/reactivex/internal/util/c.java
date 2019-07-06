package io.reactivex.internal.util;

import io.reactivex.d.a;
import io.reactivex.internal.schedulers.g;

/* compiled from: BlockingHelper */
public final class c {
    public static void a() {
        if (!a.a()) {
            return;
        }
        if ((Thread.currentThread() instanceof g) || a.b()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Attempt to block on a Scheduler ");
            sb.append(Thread.currentThread().getName());
            sb.append(" that doesn't support blocking operators as they may lead to deadlock");
            throw new IllegalStateException(sb.toString());
        }
    }
}
