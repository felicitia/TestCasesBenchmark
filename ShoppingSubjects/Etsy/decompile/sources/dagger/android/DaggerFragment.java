package dagger.android;

import android.app.Fragment;
import android.content.Context;

public abstract class DaggerFragment extends Fragment implements g {
    DispatchingAndroidInjector<Fragment> childFragmentInjector;

    public void onAttach(Context context) {
        a.a((Fragment) this);
        super.onAttach(context);
    }

    public b<Fragment> fragmentInjector() {
        return this.childFragmentInjector;
    }
}
