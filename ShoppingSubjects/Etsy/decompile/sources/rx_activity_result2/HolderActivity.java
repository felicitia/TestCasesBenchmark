package rx_activity_result2;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import com.google.a.a.a.a.a.a;

public class HolderActivity extends Activity {
    private static int FAILED_REQUEST_CODE = -909;
    private static c request;
    private Intent data;
    private b onPreResult;
    private OnResult onResult;
    private int requestCode;
    private int resultCode;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (request == null) {
            finish();
            return;
        }
        this.onPreResult = request.a();
        this.onResult = request.b();
        if (bundle == null) {
            if (request instanceof d) {
                d dVar = (d) request;
                if (dVar.i() == null) {
                    startIntentSender(dVar);
                } else {
                    startIntentSenderWithOptions(dVar);
                }
            } else {
                try {
                    startActivityForResult(request.c(), 0);
                } catch (ActivityNotFoundException e) {
                    if (this.onResult != null) {
                        this.onResult.error(e);
                    }
                }
            }
        }
    }

    private void startIntentSender(d dVar) {
        try {
            startIntentSenderForResult(dVar.d(), 0, dVar.e(), dVar.f(), dVar.g(), dVar.h());
        } catch (SendIntentException e) {
            a.a(e);
            this.onResult.response(FAILED_REQUEST_CODE, 0, null);
        }
    }

    private void startIntentSenderWithOptions(d dVar) {
        try {
            startIntentSenderForResult(dVar.d(), 0, dVar.e(), dVar.f(), dVar.g(), dVar.h(), dVar.i());
        } catch (SendIntentException e) {
            a.a(e);
            this.onResult.response(FAILED_REQUEST_CODE, 0, null);
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        this.resultCode = i2;
        this.requestCode = i;
        this.data = intent;
        if (this.onPreResult != null) {
            this.onPreResult.a(i, i2, intent).a((io.reactivex.functions.a) new io.reactivex.functions.a() {
                public void a() throws Exception {
                    HolderActivity.this.finish();
                }
            }).k();
        } else {
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        if (this.onResult != null) {
            this.onResult.response(this.requestCode, this.resultCode, this.data);
        }
    }

    static void setRequest(c cVar) {
        request = cVar;
    }
}
