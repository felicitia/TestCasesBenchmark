package android.arch.lifecycle;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.arch.lifecycle.Lifecycle.Event;
import android.os.Bundle;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;

@RestrictTo({Scope.LIBRARY_GROUP})
public class ReportFragment extends Fragment {
    private static final String REPORT_FRAGMENT_TAG = "android.arch.lifecycle.LifecycleDispatcher.report_fragment_tag";
    private a mProcessListener;

    interface a {
        void a();

        void b();

        void c();
    }

    public static void injectIfNeededIn(Activity activity) {
        FragmentManager fragmentManager = activity.getFragmentManager();
        if (fragmentManager.findFragmentByTag(REPORT_FRAGMENT_TAG) == null) {
            fragmentManager.beginTransaction().add(new ReportFragment(), REPORT_FRAGMENT_TAG).commit();
            fragmentManager.executePendingTransactions();
        }
    }

    static ReportFragment get(Activity activity) {
        return (ReportFragment) activity.getFragmentManager().findFragmentByTag(REPORT_FRAGMENT_TAG);
    }

    private void dispatchCreate(a aVar) {
        if (aVar != null) {
            aVar.a();
        }
    }

    private void dispatchStart(a aVar) {
        if (aVar != null) {
            aVar.b();
        }
    }

    private void dispatchResume(a aVar) {
        if (aVar != null) {
            aVar.c();
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        dispatchCreate(this.mProcessListener);
        dispatch(Event.ON_CREATE);
    }

    public void onStart() {
        super.onStart();
        dispatchStart(this.mProcessListener);
        dispatch(Event.ON_START);
    }

    public void onResume() {
        super.onResume();
        dispatchResume(this.mProcessListener);
        dispatch(Event.ON_RESUME);
    }

    public void onPause() {
        super.onPause();
        dispatch(Event.ON_PAUSE);
    }

    public void onStop() {
        super.onStop();
        dispatch(Event.ON_STOP);
    }

    public void onDestroy() {
        super.onDestroy();
        dispatch(Event.ON_DESTROY);
        this.mProcessListener = null;
    }

    private void dispatch(Event event) {
        Activity activity = getActivity();
        if (activity instanceof LifecycleRegistryOwner) {
            ((LifecycleRegistryOwner) activity).getLifecycle().handleLifecycleEvent(event);
            return;
        }
        if (activity instanceof e) {
            Lifecycle lifecycle = ((e) activity).getLifecycle();
            if (lifecycle instanceof LifecycleRegistry) {
                ((LifecycleRegistry) lifecycle).handleLifecycleEvent(event);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void setProcessListener(a aVar) {
        this.mProcessListener = aVar;
    }
}
