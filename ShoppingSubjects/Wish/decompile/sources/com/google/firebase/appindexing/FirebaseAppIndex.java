package com.google.firebase.appindexing;

import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appindexing.internal.zzh;
import java.lang.ref.WeakReference;

public abstract class FirebaseAppIndex {
    private static WeakReference<FirebaseAppIndex> zzcc;

    public static synchronized FirebaseAppIndex getInstance() {
        FirebaseAppIndex firebaseAppIndex;
        synchronized (FirebaseAppIndex.class) {
            firebaseAppIndex = zzcc == null ? null : (FirebaseAppIndex) zzcc.get();
            if (firebaseAppIndex == null) {
                zzh zzh = new zzh(FirebaseApp.getInstance().getApplicationContext());
                zzcc = new WeakReference<>(zzh);
                firebaseAppIndex = zzh;
            }
        }
        return firebaseAppIndex;
    }

    public abstract Task<Void> removeAll();

    public abstract Task<Void> update(Indexable... indexableArr);
}
