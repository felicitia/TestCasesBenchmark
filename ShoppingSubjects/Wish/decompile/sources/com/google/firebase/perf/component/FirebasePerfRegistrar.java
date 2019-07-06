package com.google.firebase.perf.component;

import android.support.annotation.Keep;
import com.google.firebase.FirebaseApp;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.firebase.perf.FirebasePerformance;
import java.util.Arrays;
import java.util.List;

@Keep
public class FirebasePerfRegistrar implements ComponentRegistrar {
    @Keep
    public List<Component<?>> getComponents() {
        return Arrays.asList(new Component[]{Component.builder(FirebasePerformance.class).add(Dependency.required(FirebaseApp.class)).factory(zza.zzp).build()});
    }
}
