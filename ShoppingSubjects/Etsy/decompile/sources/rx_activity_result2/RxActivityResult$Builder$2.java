package rx_activity_result2;

import android.content.Intent;
import rx_activity_result2.f.a;

class RxActivityResult$Builder$2 implements OnResult {
    final /* synthetic */ a this$0;

    RxActivityResult$Builder$2(a aVar) {
        this.this$0 = aVar;
    }

    public void response(int i, int i2, Intent intent) {
        if (f.a.a() != null && f.a.a().getClass() == this.this$0.a) {
            this.this$0.b.onNext(new e(f.a.a(), i, i2, intent));
            this.this$0.b.onComplete();
        }
    }

    public void error(Throwable th) {
        this.this$0.b.onError(th);
    }
}
