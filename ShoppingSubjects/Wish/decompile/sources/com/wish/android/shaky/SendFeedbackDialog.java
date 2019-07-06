package com.wish.android.shaky;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.view.View;
import java.util.concurrent.TimeUnit;

public class SendFeedbackDialog extends DialogFragment {
    private static final long DISMISS_TIMEOUT_MS = TimeUnit.SECONDS.toMillis(5);
    private Handler handler;
    private Runnable runnable;

    public Dialog onCreateDialog(Bundle bundle) {
        super.onCreate(bundle);
        Builder builder = new Builder(getActivity(), R.style.AppCompatAlertDialog);
        builder.setView(View.inflate(getActivity().getApplicationContext(), R.layout.shaky_popup, null));
        builder.setPositiveButton(R.string.shaky_dialog_positive, new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                LocalBroadcastManager.getInstance(SendFeedbackDialog.this.getActivity()).sendBroadcast(new Intent("StartFeedbackFlow"));
            }
        });
        builder.setNegativeButton(R.string.shaky_dialog_negative, null);
        final AlertDialog create = builder.create();
        this.handler = new Handler();
        this.runnable = new Runnable() {
            public void run() {
                if (create.isShowing()) {
                    create.dismiss();
                }
            }
        };
        this.handler.postDelayed(this.runnable, DISMISS_TIMEOUT_MS);
        return create;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        if (this.handler != null) {
            this.handler.removeCallbacks(this.runnable);
        }
    }
}
