package com.onfido.android.sdk.capture.ui;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import com.onfido.android.sdk.capture.R;

public abstract class BaseActivity extends AppCompatActivity {
    private ProgressDialog a;

    private void a() {
        if (this.a == null) {
            this.a = new ProgressDialog(this);
            this.a.setMessage(getString(R.string.onfido_message_loading));
            this.a.setCancelable(false);
        }
        this.a.show();
    }

    private void b() {
        if (this.a != null && this.a.isShowing()) {
            this.a.dismiss();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        if (this.a != null) {
            this.a.dismiss();
            this.a = null;
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        finish();
        return true;
    }

    public void setLoading(boolean z) {
        if (z) {
            a();
        } else {
            b();
        }
    }
}
