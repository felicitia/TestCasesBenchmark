package com.etsy.android.uikit.ui.core;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.core.j;
import com.etsy.android.lib.core.posts.a;
import com.etsy.android.uikit.f;

public abstract class BaseFragment extends Fragment implements f {
    private static final String TAG = com.etsy.android.lib.logger.f.a(BaseDialogFragment.class);
    private a mBaseFragmentDelegate = new a(this);

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        com.etsy.android.lib.logger.f.a(TAG, "onCreate: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
        this.mBaseFragmentDelegate.a(bundle);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        com.etsy.android.lib.logger.f.a(TAG, "onActivityCreated: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
    }

    public void onResume() {
        super.onResume();
        com.etsy.android.lib.logger.f.a(TAG, "onResume: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
        this.mBaseFragmentDelegate.a();
    }

    public void onStart() {
        super.onStart();
        com.etsy.android.lib.logger.f.a(TAG, "onStart: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
    }

    public void onPause() {
        super.onPause();
        com.etsy.android.lib.logger.f.a(TAG, "onPause: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
    }

    public void onFragmentResume() {
        com.etsy.android.lib.logger.f.a(TAG, "onFragmentResume: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
    }

    public void onDestroy() {
        super.onDestroy();
        com.etsy.android.lib.logger.f.a(TAG, "onDestroy: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
        this.mBaseFragmentDelegate.b();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        com.etsy.android.lib.logger.f.a(TAG, "onCreateView: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void onDestroyView() {
        this.mBaseFragmentDelegate.c();
        super.onDestroyView();
        com.etsy.android.lib.logger.f.a(TAG, "onDestroyView: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        com.etsy.android.lib.logger.f.a(TAG, "onAttach: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
    }

    public void onDetach() {
        super.onDetach();
        com.etsy.android.lib.logger.f.a(TAG, "onDetach: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.mBaseFragmentDelegate.b(bundle);
        com.etsy.android.lib.logger.f.a(TAG, "onSaveInstanceState: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
    }

    public j getRequestQueue() {
        return this.mBaseFragmentDelegate.d();
    }

    public a getPostManager() {
        return this.mBaseFragmentDelegate.e();
    }

    public c getImageBatch() {
        return this.mBaseFragmentDelegate.f();
    }

    public boolean handleBackPressed() {
        return this.mBaseFragmentDelegate.handleBackPressed();
    }
}
