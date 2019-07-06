package com.onfido.android.sdk.capture.ui;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import com.onfido.android.sdk.capture.R;

public abstract class BaseFragment extends Fragment {
    private ProgressDialog a;

    public void dismissLoadingDialog() {
        if (this.a != null && this.a.isShowing()) {
            this.a.dismiss();
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        NavUtils.navigateUpFromSameTask(getActivity());
        return true;
    }

    public void showLoadingDialog() {
        if (this.a == null) {
            this.a = new ProgressDialog(getContext());
            this.a.setMessage(getString(R.string.onfido_message_loading));
            this.a.setCancelable(false);
        }
        this.a.show();
    }
}
