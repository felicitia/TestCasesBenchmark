package com.etsy.android.uikit.ui.core;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.lib.a.e;
import com.etsy.android.lib.a.g;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.core.j;
import com.etsy.android.lib.core.posts.a;
import com.etsy.android.lib.util.l;
import com.etsy.android.uikit.c;
import com.etsy.android.uikit.f;

public abstract class BaseDialogFragment extends DialogFragment implements f {
    private static final String TAG = com.etsy.android.lib.logger.f.a(BaseDialogFragment.class);
    private static final float WINDOW_WIDTH_RATIO_LARGE_LANDSCAPE = 0.66f;
    private static final float WINDOW_WIDTH_RATIO_LARGE_PORTRAIT = 0.9f;
    private a mBaseFragmentDelegate = new a(this);

    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return null;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        com.etsy.android.lib.logger.f.c(TAG, "onCreate: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
        this.mBaseFragmentDelegate.a(bundle);
    }

    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        AnonymousClass1 r3 = new Dialog(getActivity(), getTheme()) {
            public void setContentView(View view) {
                super.setContentView(view);
                getWindow().getAttributes().width = BaseDialogFragment.this.getDialogWidth();
            }
        };
        if (l.c((Activity) getActivity())) {
            r3.getWindow().requestFeature(1);
            r3.getWindow().clearFlags(2);
        }
        r3.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                return i == 4 && keyEvent.getAction() == 1 && BaseDialogFragment.this.handleBackPressed();
            }
        });
        r3.setCanceledOnTouchOutside(false);
        r3.getWindow().setSoftInputMode(16);
        return r3;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        com.etsy.android.lib.logger.f.c(TAG, "onActivityCreated: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
    }

    public void onResume() {
        super.onResume();
        com.etsy.android.lib.logger.f.c(TAG, "onResume: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
        this.mBaseFragmentDelegate.a();
    }

    public void onStart() {
        super.onStart();
        com.etsy.android.lib.logger.f.c(TAG, "onStart: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
    }

    public void onPause() {
        super.onPause();
        com.etsy.android.lib.logger.f.c(TAG, "onPause: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
    }

    public void onFragmentResume() {
        com.etsy.android.lib.logger.f.c(TAG, "onFragmentResume: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
    }

    public void onDestroy() {
        super.onDestroy();
        com.etsy.android.lib.logger.f.c(TAG, "onDestroy: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
        this.mBaseFragmentDelegate.b();
    }

    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        com.etsy.android.lib.logger.f.c(TAG, "onDismiss: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
        if (getActivity() != null) {
            ActivityCompat.finishAfterTransition(getActivity());
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        com.etsy.android.lib.logger.f.c(TAG, "onCreateView: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
        View inflate = layoutInflater.inflate(k.dialog_fragment_container, viewGroup, false);
        ViewGroup viewGroup2 = (ViewGroup) inflate.findViewById(i.content_frame);
        View onCreateContentView = onCreateContentView(layoutInflater, viewGroup2, bundle);
        if (onCreateContentView != viewGroup2) {
            viewGroup2.addView(onCreateContentView);
        }
        return inflate;
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        Toolbar toolbar = (Toolbar) view.findViewById(i.app_bar_toolbar);
        if (toolbar != null && (getActivity() instanceof AppCompatActivity)) {
            toolbar.setNavigationIcon(c.a(toolbar.getContext(), g.sk_ic_close, e.sk_gray_70));
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        }
    }

    public void onDestroyView() {
        this.mBaseFragmentDelegate.c();
        super.onDestroyView();
        com.etsy.android.lib.logger.f.c(TAG, "onDestroyView: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        com.etsy.android.lib.logger.f.c(TAG, "onAttach: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
    }

    public void onDetach() {
        super.onDetach();
        com.etsy.android.lib.logger.f.c(TAG, "onDetach: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.mBaseFragmentDelegate.b(bundle);
        com.etsy.android.lib.logger.f.c(TAG, "onSaveInstanceState: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
    }

    public void goBack() {
        if (getActivity() != null) {
            getActivity().finish();
        }
    }

    public int getDialogWidth() {
        l lVar = new l(getActivity());
        return (int) (((float) lVar.d()) * (lVar.f() ? WINDOW_WIDTH_RATIO_LARGE_LANDSCAPE : WINDOW_WIDTH_RATIO_LARGE_PORTRAIT));
    }

    public j getRequestQueue() {
        return this.mBaseFragmentDelegate.d();
    }

    public a getPostManager() {
        return this.mBaseFragmentDelegate.e();
    }

    public com.etsy.android.lib.core.img.c getImageBatch() {
        return this.mBaseFragmentDelegate.f();
    }

    public boolean handleBackPressed() {
        return this.mBaseFragmentDelegate.handleBackPressed();
    }
}
