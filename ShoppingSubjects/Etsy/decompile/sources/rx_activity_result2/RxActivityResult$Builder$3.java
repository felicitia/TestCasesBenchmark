package rx_activity_result2;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import rx_activity_result2.f.a;

class RxActivityResult$Builder$3 implements OnResult {
    final /* synthetic */ a this$0;

    RxActivityResult$Builder$3(a aVar) {
        this.this$0 = aVar;
    }

    public void response(int i, int i2, Intent intent) {
        if (f.a.a() != null) {
            Fragment a = this.this$0.a(((FragmentActivity) f.a.a()).getSupportFragmentManager().getFragments());
            if (a != null) {
                this.this$0.b.onNext(new e(a, i, i2, intent));
                this.this$0.b.onComplete();
            }
        }
    }

    public void error(Throwable th) {
        this.this$0.b.onError(th);
    }
}
