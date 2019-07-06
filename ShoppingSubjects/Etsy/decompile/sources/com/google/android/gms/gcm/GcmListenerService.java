package com.google.android.gms.gcm;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.util.Log;
import com.etsy.android.lib.models.ResponseConstants;
import com.google.android.gms.iid.zze;
import java.util.Iterator;
import java.util.List;

@Deprecated
public class GcmListenerService extends zze {
    static void zzd(Bundle bundle) {
        Iterator it = bundle.keySet().iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (str != null && str.startsWith("google.c.")) {
                it.remove();
            }
        }
    }

    public void handleIntent(Intent intent) {
        if (!"com.google.android.c2dm.intent.RECEIVE".equals(intent.getAction())) {
            String str = "GcmListenerService";
            String str2 = "Unknown intent action: ";
            String valueOf = String.valueOf(intent.getAction());
            Log.w(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            return;
        }
        String stringExtra = intent.getStringExtra("message_type");
        if (stringExtra == null) {
            stringExtra = "gcm";
        }
        char c = 65535;
        int hashCode = stringExtra.hashCode();
        boolean z = false;
        if (hashCode != -2062414158) {
            if (hashCode != 102161) {
                if (hashCode != 814694033) {
                    if (hashCode == 814800675 && stringExtra.equals("send_event")) {
                        c = 2;
                    }
                } else if (stringExtra.equals("send_error")) {
                    c = 3;
                }
            } else if (stringExtra.equals("gcm")) {
                c = 0;
            }
        } else if (stringExtra.equals("deleted_messages")) {
            c = 1;
        }
        switch (c) {
            case 0:
                Bundle extras = intent.getExtras();
                extras.remove("message_type");
                extras.remove("android.support.content.wakelockid");
                if ("1".equals(d.a(extras, "gcm.n.e")) || d.a(extras, "gcm.n.icon") != null) {
                    if (!((KeyguardManager) getSystemService("keyguard")).inKeyguardRestrictedInputMode()) {
                        int myPid = Process.myPid();
                        List runningAppProcesses = ((ActivityManager) getSystemService("activity")).getRunningAppProcesses();
                        if (runningAppProcesses != null) {
                            Iterator it = runningAppProcesses.iterator();
                            while (true) {
                                if (it.hasNext()) {
                                    RunningAppProcessInfo runningAppProcessInfo = (RunningAppProcessInfo) it.next();
                                    if (runningAppProcessInfo.pid == myPid) {
                                        if (runningAppProcessInfo.importance == 100) {
                                            z = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!z) {
                        d.a((Context) this).a(extras);
                        return;
                    }
                    Bundle bundle = new Bundle();
                    Iterator it2 = extras.keySet().iterator();
                    while (it2.hasNext()) {
                        String str3 = (String) it2.next();
                        String string = extras.getString(str3);
                        if (str3.startsWith("gcm.notification.")) {
                            str3 = str3.replace("gcm.notification.", "gcm.n.");
                        }
                        if (str3.startsWith("gcm.n.")) {
                            if (!"gcm.n.e".equals(str3)) {
                                bundle.putString(str3.substring(6), string);
                            }
                            it2.remove();
                        }
                    }
                    String string2 = bundle.getString("sound2");
                    if (string2 != null) {
                        bundle.remove("sound2");
                        bundle.putString("sound", string2);
                    }
                    if (!bundle.isEmpty()) {
                        extras.putBundle("notification", bundle);
                    }
                }
                String string3 = extras.getString(ResponseConstants.FROM);
                extras.remove(ResponseConstants.FROM);
                zzd(extras);
                onMessageReceived(string3, extras);
                return;
            case 1:
                onDeletedMessages();
                return;
            case 2:
                onMessageSent(intent.getStringExtra("google.message_id"));
                return;
            case 3:
                String stringExtra2 = intent.getStringExtra("google.message_id");
                if (stringExtra2 == null) {
                    stringExtra2 = intent.getStringExtra("message_id");
                }
                onSendError(stringExtra2, intent.getStringExtra("error"));
                return;
            default:
                String str4 = "GcmListenerService";
                String str5 = "Received message with unknown type: ";
                String valueOf2 = String.valueOf(stringExtra);
                Log.w(str4, valueOf2.length() != 0 ? str5.concat(valueOf2) : new String(str5));
                return;
        }
    }

    public void onDeletedMessages() {
    }

    public void onMessageReceived(String str, Bundle bundle) {
    }

    public void onMessageSent(String str) {
    }

    public void onSendError(String str, String str2) {
    }
}
