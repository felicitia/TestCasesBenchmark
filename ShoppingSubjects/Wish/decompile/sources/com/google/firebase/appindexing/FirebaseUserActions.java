package com.google.firebase.appindexing;

import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appindexing.internal.zzo;
import java.lang.ref.WeakReference;

public abstract class FirebaseUserActions {
    private static WeakReference<FirebaseUserActions> zzcc;

    public static synchronized FirebaseUserActions getInstance() {
        FirebaseUserActions firebaseUserActions;
        synchronized (FirebaseUserActions.class) {
            firebaseUserActions = zzcc == null ? null : (FirebaseUserActions) zzcc.get();
            if (firebaseUserActions == null) {
                zzo zzo = new zzo(FirebaseApp.getInstance().getApplicationContext());
                zzcc = new WeakReference<>(zzo);
                firebaseUserActions = zzo;
            }
        }
        return firebaseUserActions;
    }

    public abstract Task<Void> end(Action action);

    public abstract Task<Void> start(Action action);
}
