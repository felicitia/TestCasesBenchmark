package rx_activity_result2;

import android.content.Intent;

/* compiled from: Result */
public class e<T> {
    private final T a;
    private final int b;
    private final int c;
    private final Intent d;

    public e(T t, int i, int i2, Intent intent) {
        this.a = t;
        this.b = i2;
        this.c = i;
        this.d = intent;
    }

    public Intent a() {
        return this.d;
    }
}
