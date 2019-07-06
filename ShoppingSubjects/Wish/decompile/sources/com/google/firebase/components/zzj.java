package com.google.firebase.components;

import com.google.firebase.events.Publisher;
import com.google.firebase.inject.Provider;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* compiled from: com.google.firebase:firebase-common@@16.0.1 */
final class zzj implements ComponentContainer {
    private final Set<Class<?>> zza;
    private final Set<Class<?>> zzb;
    private final Set<Class<?>> zzc;
    private final ComponentContainer zzd;

    /* compiled from: com.google.firebase:firebase-common@@16.0.1 */
    static class zza implements Publisher {
        private final Set<Class<?>> zza;
        private final Publisher zzb;

        public zza(Set<Class<?>> set, Publisher publisher) {
            this.zza = set;
            this.zzb = publisher;
        }
    }

    zzj(Component<?> component, ComponentContainer componentContainer) {
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        for (Dependency dependency : component.zzb()) {
            if (dependency.zzc()) {
                hashSet.add(dependency.zza());
            } else {
                hashSet2.add(dependency.zza());
            }
        }
        if (!component.zzd().isEmpty()) {
            hashSet.add(Publisher.class);
        }
        this.zza = Collections.unmodifiableSet(hashSet);
        this.zzb = Collections.unmodifiableSet(hashSet2);
        this.zzc = component.zzd();
        this.zzd = componentContainer;
    }

    public final <T> T get(Class<T> cls) {
        if (!this.zza.contains(cls)) {
            throw new IllegalArgumentException(String.format("Requesting %s is not allowed.", new Object[]{cls}));
        }
        T t = this.zzd.get(cls);
        if (!cls.equals(Publisher.class)) {
            return t;
        }
        return new zza(this.zzc, (Publisher) t);
    }

    public final <T> Provider<T> getProvider(Class<T> cls) {
        if (this.zzb.contains(cls)) {
            return this.zzd.getProvider(cls);
        }
        throw new IllegalArgumentException(String.format("Requesting Provider<%s> is not allowed.", new Object[]{cls}));
    }
}
