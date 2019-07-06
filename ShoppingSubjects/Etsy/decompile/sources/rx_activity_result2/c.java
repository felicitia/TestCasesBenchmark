package rx_activity_result2;

import android.content.Intent;
import android.support.annotation.Nullable;

/* compiled from: Request */
class c {
    private final Intent a;
    private b b;
    private OnResult c;

    public c(@Nullable Intent intent) {
        this.a = intent;
    }

    /* access modifiers changed from: 0000 */
    public void a(@Nullable b bVar) {
        this.b = bVar;
    }

    /* access modifiers changed from: 0000 */
    public b a() {
        return this.b;
    }

    public void a(OnResult onResult) {
        this.c = onResult;
    }

    public OnResult b() {
        return this.c;
    }

    @Nullable
    public Intent c() {
        return this.a;
    }
}
