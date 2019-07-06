package android.arch.lifecycle;

import android.support.annotation.NonNull;

@Deprecated
public interface LifecycleRegistryOwner extends e {
    @NonNull
    LifecycleRegistry getLifecycle();
}
