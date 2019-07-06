package com.firebase.jobdispatcher;

import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.NonNull;

/* compiled from: GooglePlayMessengerCallback */
class j implements l {
    private final Messenger a;
    private final String b;

    j(Messenger messenger, String str) {
        this.a = messenger;
        this.b = str;
    }

    public void a(int i) {
        try {
            this.a.send(b(i));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @NonNull
    private Message b(int i) {
        Message obtain = Message.obtain();
        obtain.what = 3;
        obtain.arg1 = i;
        Bundle bundle = new Bundle();
        bundle.putString("tag", this.b);
        obtain.setData(bundle);
        return obtain;
    }
}
