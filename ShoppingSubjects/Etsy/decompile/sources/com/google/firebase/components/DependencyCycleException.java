package com.google.firebase.components;

import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.Arrays;
import java.util.List;

@KeepForSdk
public class DependencyCycleException extends DependencyException {
    private final List<a<?>> zza;

    @KeepForSdk
    public DependencyCycleException(List<a<?>> list) {
        StringBuilder sb = new StringBuilder("Dependency cycle detected: ");
        sb.append(Arrays.toString(list.toArray()));
        super(sb.toString());
        this.zza = list;
    }

    @KeepForSdk
    public List<a<?>> getComponentsInCycle() {
        return this.zza;
    }
}
