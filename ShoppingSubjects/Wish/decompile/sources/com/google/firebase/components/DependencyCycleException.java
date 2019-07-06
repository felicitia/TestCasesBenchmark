package com.google.firebase.components;

import java.util.Arrays;
import java.util.List;

/* compiled from: com.google.firebase:firebase-common@@16.0.1 */
public class DependencyCycleException extends DependencyException {
    private final List<Component<?>> zza;

    public DependencyCycleException(List<Component<?>> list) {
        StringBuilder sb = new StringBuilder("Dependency cycle detected: ");
        sb.append(Arrays.toString(list.toArray()));
        super(sb.toString());
        this.zza = list;
    }
}
