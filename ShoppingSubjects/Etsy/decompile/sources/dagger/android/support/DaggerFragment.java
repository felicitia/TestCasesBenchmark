package dagger.android.support;

import android.content.Context;
import android.support.v4.app.Fragment;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.b;

public abstract class DaggerFragment extends Fragment implements b {
    DispatchingAndroidInjector<Fragment> childFragmentInjector;

    public void onAttach(Context context) {
        a.a(this);
        super.onAttach(context);
    }

    public b<Fragment> supportFragmentInjector() {
        return this.childFragmentInjector;
    }
}
