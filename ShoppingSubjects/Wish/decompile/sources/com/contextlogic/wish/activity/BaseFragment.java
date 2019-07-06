package com.contextlogic.wish.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.dialog.BaseDialogFragment;

public abstract class BaseFragment<A extends BaseActivity> extends Fragment {
    private Handler mHandler;
    private boolean mInitialized;
    private Bundle mSavedInstanceState;

    public interface ActivityTask<A> {
        void performTask(A a);
    }

    public interface DialogTask<A extends BaseActivity, D extends BaseDialogFragment> {
        void performTask(A a, D d);
    }

    public interface ServiceTask<A extends BaseActivity, S extends ServiceFragment> {
        void performTask(A a, S s);
    }

    public interface UiTask<A extends BaseActivity, U extends UiFragment> {
        void performTask(A a, U u);
    }

    public void handleSaveInstanceState(Bundle bundle) {
    }

    /* access modifiers changed from: protected */
    public abstract void initialize();

    public abstract void onAuthenticationVerifiedResume();

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            if (bundle.getBoolean(getClassName())) {
                this.mSavedInstanceState = bundle;
            } else {
                this.mSavedInstanceState = null;
            }
        }
        this.mHandler = new Handler(Looper.getMainLooper());
    }

    public void withActivity(ActivityTask<A> activityTask) {
        BaseActivity baseActivity = getBaseActivity();
        if (baseActivity != null) {
            activityTask.performTask(baseActivity);
        }
    }

    public void withVerifiedAuthenticationActivity(final ActivityTask<A> activityTask) {
        withActivity(new ActivityTask<A>() {
            public void performTask(A a) {
                a.withVerifiedAuthentication(new Runnable() {
                    public void run() {
                        BaseFragment.this.withActivity(activityTask);
                    }
                });
            }
        });
    }

    public <A extends BaseActivity, S extends ServiceFragment> void withServiceFragment(ServiceTask<A, S> serviceTask) {
        BaseActivity baseActivity = getBaseActivity();
        if (baseActivity != null) {
            ServiceFragment serviceFragment = baseActivity.getServiceFragment();
            if (serviceFragment != null) {
                serviceTask.performTask(baseActivity, serviceFragment);
            }
        }
    }

    /* access modifiers changed from: protected */
    public <A extends BaseActivity, U extends UiFragment> void withUiFragment(UiTask<A, U> uiTask) {
        withUiFragment(uiTask, "FragmentTagMainContent");
    }

    /* access modifiers changed from: protected */
    public <A extends BaseActivity, U extends UiFragment> void withUiFragment(final UiTask<A, U> uiTask, final String str) {
        BaseActivity baseActivity = getBaseActivity();
        if (baseActivity != null) {
            baseActivity.withVerifiedAuthentication(new Runnable() {
                public void run() {
                    BaseActivity baseActivity = BaseFragment.this.getBaseActivity();
                    if (baseActivity != null) {
                        UiFragment uiFragment = baseActivity.getUiFragment(str);
                        if (uiFragment != null) {
                            uiTask.performTask(baseActivity, uiFragment);
                        }
                    }
                }
            });
        }
    }

    public <A extends BaseActivity, D extends BaseDialogFragment> void withDialogFragment(DialogTask<A, D> dialogTask) {
        BaseActivity baseActivity = getBaseActivity();
        if (baseActivity != null) {
            try {
                BaseDialogFragment currentBaseDialog = baseActivity.getCurrentBaseDialog();
                if (currentBaseDialog != null) {
                    dialogTask.performTask(baseActivity, currentBaseDialog);
                }
            } catch (ClassCastException unused) {
            }
        }
    }

    public A getBaseActivity() {
        return (BaseActivity) getActivity();
    }

    public void initializeIfNeccessary() {
        if (!this.mInitialized) {
            this.mInitialized = true;
            initialize();
        }
        onAuthenticationVerifiedResume();
    }

    /* access modifiers changed from: protected */
    public Handler getHandler() {
        return this.mHandler;
    }

    public final void onSaveInstanceState(Bundle bundle) {
        if (this.mInitialized) {
            super.onSaveInstanceState(bundle);
            bundle.putBoolean(getClassName(), true);
            handleSaveInstanceState(bundle);
        }
    }

    /* access modifiers changed from: protected */
    public String getClassName() {
        return getClass().getSimpleName();
    }

    public Bundle getSavedInstanceState() {
        return this.mSavedInstanceState;
    }

    public void onDestroy() {
        super.onDestroy();
        WishApplication.getInstance().getRefWatcher().watch(this);
    }
}
