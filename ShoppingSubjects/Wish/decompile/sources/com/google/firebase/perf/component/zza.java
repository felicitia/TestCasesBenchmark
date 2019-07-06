package com.google.firebase.perf.component;

import com.google.firebase.FirebaseApp;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.firebase.perf.FirebasePerformance;

final /* synthetic */ class zza implements ComponentFactory {
    static final ComponentFactory zzp = new zza();

    private zza() {
    }

    public final Object create(ComponentContainer componentContainer) {
        return new FirebasePerformance((FirebaseApp) componentContainer.get(FirebaseApp.class));
    }
}
