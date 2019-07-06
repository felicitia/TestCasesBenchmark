package com.contextlogic.wish.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.dialog.popupanimation.PopupAnimationDialogFragment;
import com.contextlogic.wish.util.DisplayUtil;
import com.crashlytics.android.Crashlytics;
import java.util.Locale;

public abstract class BaseDialogFragment<A extends BaseActivity> extends DialogFragment {
    protected boolean mClosed;
    private View mContent;
    private LinearLayout mDialog;
    private Runnable mDismissAnimation = new Runnable() {
        public void run() {
            BaseDialogFragment.this.onPopOutAnimationEnded();
        }
    };
    private Handler mHandler;
    private FrameLayout mProgressView;

    public interface BaseDialogCallback {
        void onCancel(BaseDialogFragment baseDialogFragment);

        void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle);
    }

    public class Margin {
        public int bottom;
        public int left;
        public int right;
        public int top;

        public Margin(int i, int i2, int i3, int i4) {
            this.left = i;
            this.top = i2;
            this.right = i3;
            this.bottom = i4;
        }
    }

    private static class NullContentViewException extends Exception {
        NullContentViewException(String str) {
            super(str);
        }
    }

    public abstract View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle);

    public int getDialogHeight() {
        return -2;
    }

    public int getDimColor() {
        return R.color.dark_translucent_window_background;
    }

    public int getGravity() {
        return 17;
    }

    public int getMaxDialogWidth() {
        return Integer.MAX_VALUE;
    }

    /* access modifiers changed from: protected */
    public int getPopupShowLength() {
        return 2000;
    }

    public boolean isCancelable() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void onPopInEnded() {
    }

    /* access modifiers changed from: protected */
    public void onPopOutEnded() {
    }

    /* access modifiers changed from: protected */
    public boolean requiresKeyboard() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean shouldAnimateDown() {
        return false;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (requiresKeyboard()) {
            setStyle(2, 2131820888);
        } else {
            setStyle(2, 2131820889);
        }
        setCancelable(isCancelable());
        this.mHandler = new Handler(Looper.getMainLooper());
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LinearLayout linearLayout = (LinearLayout) layoutInflater.inflate(R.layout.base_dialog_fragment, viewGroup, false);
        this.mDialog = (LinearLayout) linearLayout.findViewById(R.id.base_dialog_content);
        this.mDialog.setOnClickListener(getClickListener());
        this.mDialog.setBackgroundColor(WishApplication.getInstance().getResources().getColor(getDimColor()));
        this.mDialog.setGravity(getGravity());
        this.mProgressView = (FrameLayout) linearLayout.findViewById(R.id.base_dialog_fragment_progress);
        if (ExperimentDataCenter.getInstance().shouldSeeNewProgressBar()) {
            View findViewById = this.mProgressView.findViewById(R.id.base_dialog_fragment_primary_progress);
            View findViewById2 = this.mProgressView.findViewById(R.id.base_dialog_fragment_three_dot_progress);
            if (!(findViewById2 == null || findViewById == null)) {
                findViewById.setVisibility(8);
                findViewById2.setVisibility(0);
            }
        }
        this.mContent = getContentView(layoutInflater, viewGroup, bundle);
        if (this.mContent == null) {
            dismiss();
            setShowsDialog(false);
            Crashlytics.logException(new NullContentViewException(String.format(Locale.US, "ContentView of %s is null", new Object[]{getClass().getSimpleName()})));
            return linearLayout;
        }
        LayoutParams layoutParams = (LayoutParams) this.mContent.getLayoutParams();
        int dialogWidth = getDialogWidth();
        int dialogHeight = getDialogHeight();
        if (layoutParams == null) {
            layoutParams = new LayoutParams(dialogWidth, dialogHeight);
        } else {
            layoutParams.width = dialogWidth;
        }
        Margin dialogMargin = getDialogMargin();
        layoutParams.setMargins(dialogMargin.left, dialogMargin.top, dialogMargin.right, dialogMargin.bottom);
        this.mContent.setLayoutParams(layoutParams);
        this.mContent.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
            }
        });
        this.mDialog.addView(this.mContent);
        if (!(this instanceof PopupAnimationDialogFragment)) {
            onPopInEnded();
            if (shouldAnimateDown() && getBaseActivity() != null && !getBaseActivity().isFinishing()) {
                getHandler().postDelayed(this.mDismissAnimation, (long) getPopupShowLength());
            }
        }
        if (requiresKeyboard()) {
            getDialog().getWindow().setSoftInputMode(16);
        }
        this.mClosed = false;
        return linearLayout;
    }

    /* access modifiers changed from: protected */
    public void onPopOutAnimationEnded() {
        if (this.mContent != null) {
            this.mContent.setVisibility(8);
        }
        onPopOutEnded();
        if (getFragmentManager() != null) {
            dismissAllowingStateLoss();
        }
    }

    public void onResume() {
        super.onResume();
        Dialog dialog = getDialog();
        if (dialog != null) {
            WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
            attributes.width = DisplayUtil.getDisplayWidth();
            dialog.getWindow().setAttributes(attributes);
            dialog.setOnKeyListener(new OnKeyListener() {
                public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                    return i == 4 && keyEvent.getAction() == 1 && BaseDialogFragment.this.onBackPressed();
                }
            });
        }
    }

    public void onDismiss(DialogInterface dialogInterface) {
        if (!this.mClosed) {
            alertCallbackOfCancel();
            this.mClosed = true;
        }
        super.onDismiss(dialogInterface);
    }

    public int getDialogWidth() {
        float f;
        if (getDialog() == null) {
            return 0;
        }
        int displayWidth = DisplayUtil.getDisplayWidth();
        if (displayWidth < DisplayUtil.getDisplayHeight()) {
            f = getResources().getFraction(R.fraction.dialog_min_width_minor, 1, 1);
        } else {
            f = getResources().getFraction(R.fraction.dialog_min_width_major, 1, 1);
        }
        return Math.min((int) (((float) displayWidth) * f), getMaxDialogWidth());
    }

    public Margin getDialogMargin() {
        Margin margin = new Margin(0, 0, 0, 0);
        return margin;
    }

    public void showProgressSpinner() {
        this.mProgressView.setVisibility(0);
        this.mDialog.setVisibility(8);
    }

    public void hideProgressSpinner() {
        this.mProgressView.setVisibility(8);
        this.mDialog.setVisibility(0);
    }

    public boolean onBackPressed() {
        onPopOutEnded();
        return false;
    }

    public void withActivity(ActivityTask<A> activityTask) {
        BaseActivity baseActivity = getBaseActivity();
        if (baseActivity != null) {
            activityTask.performTask(baseActivity);
        }
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

    public A getBaseActivity() {
        return (BaseActivity) getActivity();
    }

    public void cancel() {
        alertCallbackOfCancel();
        this.mClosed = true;
        onPopOutEnded();
        if (getFragmentManager() != null) {
            dismissAllowingStateLoss();
        }
    }

    public void makeSelection(int i) {
        makeSelection(i, new Bundle());
    }

    public void makeSelection(Bundle bundle) {
        makeSelection(0, bundle);
    }

    public void makeSelection(int i, Bundle bundle) {
        alertCallbackOfChoice(i, bundle);
        this.mClosed = true;
        onPopOutEnded();
        dismissAllowingStateLoss();
    }

    private OnClickListener getClickListener() {
        return new OnClickListener() {
            public void onClick(View view) {
                if (BaseDialogFragment.this.isCancelable()) {
                    BaseDialogFragment.this.withActivity(new ActivityTask<A>() {
                        public void performTask(A a) {
                            a.dismissModal();
                        }
                    });
                }
            }
        };
    }

    private void alertCallbackOfCancel() {
        withActivity(new ActivityTask<A>() {
            public void performTask(A a) {
                a.onDialogCancel(this);
            }
        });
    }

    private void alertCallbackOfChoice(final int i, final Bundle bundle) {
        withActivity(new ActivityTask<A>() {
            public void performTask(A a) {
                a.onDialogSelection(this, i, bundle);
            }
        });
    }

    /* access modifiers changed from: protected */
    public Handler getHandler() {
        return this.mHandler;
    }
}
