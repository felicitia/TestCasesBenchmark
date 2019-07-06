package dagger.android.support;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.a;
import dagger.android.b;
import dagger.android.g;

public abstract class DaggerAppCompatActivity extends AppCompatActivity implements g, b {
    DispatchingAndroidInjector<Fragment> frameworkFragmentInjector;
    DispatchingAndroidInjector<android.support.v4.app.Fragment> supportFragmentInjector;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        a.a((Activity) this);
        super.onCreate(bundle);
    }

    public b<android.support.v4.app.Fragment> supportFragmentInjector() {
        return this.supportFragmentInjector;
    }

    public b<Fragment> fragmentInjector() {
        return this.frameworkFragmentInjector;
    }
}
