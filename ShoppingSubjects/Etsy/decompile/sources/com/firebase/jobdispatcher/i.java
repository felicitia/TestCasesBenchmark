package com.firebase.jobdispatcher;

import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

@TargetApi(21)
/* compiled from: GooglePlayMessageHandler */
class i extends Handler {
    private final GooglePlayReceiver a;

    public i(Looper looper, GooglePlayReceiver googlePlayReceiver) {
        super(looper);
        this.a = googlePlayReceiver;
    }

    public void handleMessage(Message message) {
        if (message != null) {
            try {
                ((AppOpsManager) this.a.getApplicationContext().getSystemService("appops")).checkPackage(message.sendingUid, "com.google.android.gms");
                int i = message.what;
                if (i != 4) {
                    switch (i) {
                        case 1:
                            a(message);
                            break;
                        case 2:
                            b(message);
                            break;
                        default:
                            StringBuilder sb = new StringBuilder();
                            sb.append("Unrecognized message received: ");
                            sb.append(message);
                            Log.e("FJD.GooglePlayReceiver", sb.toString());
                            break;
                    }
                }
            } catch (SecurityException unused) {
                Log.e("FJD.GooglePlayReceiver", "Message was not sent from GCM.");
            }
        }
    }

    private void a(Message message) {
        Bundle data = message.getData();
        Messenger messenger = message.replyTo;
        String string = data.getString("tag");
        if (messenger == null || string == null) {
            if (Log.isLoggable("FJD.GooglePlayReceiver", 3)) {
                Log.d("FJD.GooglePlayReceiver", "Invalid start execution message.");
            }
            return;
        }
        this.a.getExecutionDelegator().a(this.a.prepareJob(new j(messenger, string), data));
    }

    private void b(Message message) {
        a b = GooglePlayReceiver.getJobCoder().b(message.getData());
        if (b == null) {
            if (Log.isLoggable("FJD.GooglePlayReceiver", 3)) {
                Log.d("FJD.GooglePlayReceiver", "Invalid stop execution message.");
            }
            return;
        }
        d.a(b.a(), true);
    }
}
