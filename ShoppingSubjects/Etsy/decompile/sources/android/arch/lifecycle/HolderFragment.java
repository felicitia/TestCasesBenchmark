package android.arch.lifecycle;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.FragmentLifecycleCallbacks;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;

@RestrictTo({Scope.LIBRARY_GROUP})
public class HolderFragment extends Fragment {
    @RestrictTo({Scope.LIBRARY_GROUP})
    public static final String HOLDER_TAG = "android.arch.lifecycle.state.StateProviderHolderFragment";
    private static final String LOG_TAG = "ViewModelStores";
    private static final a sHolderFragmentManager = new a();
    private l mViewModelStore = new l();

    static class a {
        /* access modifiers changed from: private */
        public Map<Activity, HolderFragment> a = new HashMap();
        /* access modifiers changed from: private */
        public Map<Fragment, HolderFragment> b = new HashMap();
        private ActivityLifecycleCallbacks c = new b() {
            public void onActivityDestroyed(Activity activity) {
                if (((HolderFragment) a.this.a.remove(activity)) != null) {
                    String str = HolderFragment.LOG_TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Failed to save a ViewModel for ");
                    sb.append(activity);
                    Log.e(str, sb.toString());
                }
            }
        };
        private boolean d = false;
        private FragmentLifecycleCallbacks e = new FragmentLifecycleCallbacks() {
            public void onFragmentDestroyed(FragmentManager fragmentManager, Fragment fragment) {
                super.onFragmentDestroyed(fragmentManager, fragment);
                if (((HolderFragment) a.this.b.remove(fragment)) != null) {
                    String str = HolderFragment.LOG_TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Failed to save a ViewModel for ");
                    sb.append(fragment);
                    Log.e(str, sb.toString());
                }
            }
        };

        a() {
        }

        /* access modifiers changed from: 0000 */
        public void a(Fragment fragment) {
            Fragment parentFragment = fragment.getParentFragment();
            if (parentFragment != null) {
                this.b.remove(parentFragment);
                parentFragment.getFragmentManager().unregisterFragmentLifecycleCallbacks(this.e);
                return;
            }
            this.a.remove(fragment.getActivity());
        }

        private static HolderFragment a(FragmentManager fragmentManager) {
            if (fragmentManager.isDestroyed()) {
                throw new IllegalStateException("Can't access ViewModels from onDestroy");
            }
            Fragment findFragmentByTag = fragmentManager.findFragmentByTag(HolderFragment.HOLDER_TAG);
            if (findFragmentByTag == null || (findFragmentByTag instanceof HolderFragment)) {
                return (HolderFragment) findFragmentByTag;
            }
            throw new IllegalStateException("Unexpected fragment instance was returned by HOLDER_TAG");
        }

        private static HolderFragment b(FragmentManager fragmentManager) {
            HolderFragment holderFragment = new HolderFragment();
            fragmentManager.beginTransaction().add((Fragment) holderFragment, HolderFragment.HOLDER_TAG).commitAllowingStateLoss();
            return holderFragment;
        }

        /* access modifiers changed from: 0000 */
        public HolderFragment a(FragmentActivity fragmentActivity) {
            FragmentManager supportFragmentManager = fragmentActivity.getSupportFragmentManager();
            HolderFragment a2 = a(supportFragmentManager);
            if (a2 != null) {
                return a2;
            }
            HolderFragment holderFragment = (HolderFragment) this.a.get(fragmentActivity);
            if (holderFragment != null) {
                return holderFragment;
            }
            if (!this.d) {
                this.d = true;
                fragmentActivity.getApplication().registerActivityLifecycleCallbacks(this.c);
            }
            HolderFragment b2 = b(supportFragmentManager);
            this.a.put(fragmentActivity, b2);
            return b2;
        }

        /* access modifiers changed from: 0000 */
        public HolderFragment b(Fragment fragment) {
            FragmentManager childFragmentManager = fragment.getChildFragmentManager();
            HolderFragment a2 = a(childFragmentManager);
            if (a2 != null) {
                return a2;
            }
            HolderFragment holderFragment = (HolderFragment) this.b.get(fragment);
            if (holderFragment != null) {
                return holderFragment;
            }
            fragment.getFragmentManager().registerFragmentLifecycleCallbacks(this.e, false);
            HolderFragment b2 = b(childFragmentManager);
            this.b.put(fragment, b2);
            return b2;
        }
    }

    public HolderFragment() {
        setRetainInstance(true);
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        sHolderFragmentManager.a((Fragment) this);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    public void onDestroy() {
        super.onDestroy();
        this.mViewModelStore.a();
    }

    @NonNull
    public l getViewModelStore() {
        return this.mViewModelStore;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public static HolderFragment holderFragmentFor(FragmentActivity fragmentActivity) {
        return sHolderFragmentManager.a(fragmentActivity);
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public static HolderFragment holderFragmentFor(Fragment fragment) {
        return sHolderFragmentManager.b(fragment);
    }
}
