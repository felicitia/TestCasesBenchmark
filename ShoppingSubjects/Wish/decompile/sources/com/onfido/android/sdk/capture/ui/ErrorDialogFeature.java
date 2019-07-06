package com.onfido.android.sdk.capture.ui;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.support.v7.app.AlertDialog.Builder;
import com.onfido.android.sdk.capture.R;
import java.lang.ref.WeakReference;

public class ErrorDialogFeature {
    private WeakReference<Activity> a;

    public interface Listener {
        void onErrorDialogClose();
    }

    public void attach(Activity activity) {
        this.a = new WeakReference<>(activity);
    }

    public void release() {
        this.a.clear();
    }

    public void show(int i, String str, final Listener listener) {
        Activity activity = (Activity) this.a.get();
        if (activity != null) {
            new Builder(activity).setTitle(i).setMessage((CharSequence) str).setPositiveButton(17039370, new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (listener != null) {
                        listener.onErrorDialogClose();
                    }
                }
            }).setCancelable(false).setOnDismissListener(new OnDismissListener() {
                public void onDismiss(DialogInterface dialogInterface) {
                    if (listener != null) {
                        listener.onErrorDialogClose();
                    }
                }
            }).show();
        }
    }

    public void show(String str, Listener listener) {
        show(R.string.onfido_error_dialog_title, str, listener);
    }
}
