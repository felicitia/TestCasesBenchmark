package io.reactivex;

import io.reactivex.internal.functions.a;
import io.reactivex.internal.util.NotificationLite;

/* compiled from: Notification */
public final class p<T> {
    static final p<Object> b = new p<>(null);
    final Object a;

    private p(Object obj) {
        this.a = obj;
    }

    public boolean a() {
        return NotificationLite.isError(this.a);
    }

    public Throwable b() {
        Object obj = this.a;
        if (NotificationLite.isError(obj)) {
            return NotificationLite.getError(obj);
        }
        return null;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof p)) {
            return false;
        }
        return a.a(this.a, ((p) obj).a);
    }

    public int hashCode() {
        Object obj = this.a;
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }

    public String toString() {
        Object obj = this.a;
        if (obj == null) {
            return "OnCompleteNotification";
        }
        if (NotificationLite.isError(obj)) {
            StringBuilder sb = new StringBuilder();
            sb.append("OnErrorNotification[");
            sb.append(NotificationLite.getError(obj));
            sb.append("]");
            return sb.toString();
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("OnNextNotification[");
        sb2.append(this.a);
        sb2.append("]");
        return sb2.toString();
    }

    public static <T> p<T> a(T t) {
        a.a(t, "value is null");
        return new p<>(t);
    }

    public static <T> p<T> a(Throwable th) {
        a.a(th, "error is null");
        return new p<>(NotificationLite.error(th));
    }

    public static <T> p<T> c() {
        return b;
    }
}
