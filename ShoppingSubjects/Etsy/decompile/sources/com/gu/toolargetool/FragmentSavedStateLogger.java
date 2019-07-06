package com.gu.toolargetool;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.FragmentLifecycleCallbacks;
import java.util.HashMap;
import java.util.Map;

public class FragmentSavedStateLogger extends FragmentLifecycleCallbacks {
    @NonNull
    private b formatter;
    @NonNull
    private c logger;
    @NonNull
    private final Map<Fragment, Bundle> savedStates = new HashMap();

    public FragmentSavedStateLogger(@NonNull b bVar, @NonNull c cVar) {
        this.formatter = bVar;
        this.logger = cVar;
    }

    public void onFragmentSaveInstanceState(FragmentManager fragmentManager, Fragment fragment, Bundle bundle) {
        this.savedStates.put(fragment, bundle);
    }

    public void onFragmentStopped(FragmentManager fragmentManager, Fragment fragment) {
        Bundle bundle = (Bundle) this.savedStates.remove(fragment);
        if (bundle != null) {
            try {
                this.logger.a(this.formatter.a(fragmentManager, fragment, bundle));
            } catch (RuntimeException e) {
                this.logger.a((Exception) e);
            }
        }
    }
}
