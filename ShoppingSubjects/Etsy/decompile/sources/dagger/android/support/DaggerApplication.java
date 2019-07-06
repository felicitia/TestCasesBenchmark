package dagger.android.support;

import android.support.v4.app.Fragment;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.b;

public abstract class DaggerApplication extends dagger.android.DaggerApplication implements b {
    DispatchingAndroidInjector<Fragment> supportFragmentInjector;

    /* access modifiers changed from: protected */
    public abstract b<? extends DaggerApplication> applicationInjector();

    public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
        return this.supportFragmentInjector;
    }
}
