package dagger.android;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

public abstract class DaggerActivity extends Activity implements g {
    DispatchingAndroidInjector<Fragment> fragmentInjector;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        a.a((Activity) this);
        super.onCreate(bundle);
    }

    public b<Fragment> fragmentInjector() {
        return this.fragmentInjector;
    }
}
