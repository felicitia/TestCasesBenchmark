package com.etsy.android.uikit.ui.core;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView.RecyclerListener;
import android.widget.EditText;
import android.widget.ListView;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.core.j;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.toolbar.a;
import com.etsy.android.uikit.f;

@Deprecated
public abstract class BaseListFragment extends ListFragment implements f {
    private static final String TAG = com.etsy.android.lib.logger.f.a(BaseFragment.class);
    protected c mImageBatch;

    public boolean handleBackPressed() {
        return false;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        com.etsy.android.lib.logger.f.c(TAG, "onCreate: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
        this.mImageBatch = new c();
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        com.etsy.android.lib.logger.f.c(TAG, "onActivityCreated: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
        setFocusHackListener();
    }

    private void setFocusHackListener() {
        ListView listView = getListView();
        if (listView == null) {
            com.etsy.android.lib.logger.f.e("unable to apply the listview hack, sad pandas all around");
        } else {
            listView.setRecyclerListener(new RecyclerListener() {
                public void onMovedToScrapHeap(View view) {
                    if (view.hasFocus()) {
                        view.clearFocus();
                        if (view instanceof EditText) {
                            ((InputMethodManager) view.getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
                        }
                    }
                }
            });
        }
    }

    public void onResume() {
        super.onResume();
        com.etsy.android.lib.logger.f.c(TAG, "onResume: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
        a.a(getClass().getSimpleName());
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
        getRequestQueue().a((Object) this);
        this.mImageBatch.a();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        com.etsy.android.lib.logger.f.c(TAG, "onCreateView: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void onDestroyView() {
        if (!getRetainInstance()) {
            getRequestQueue().a((Object) this);
        }
        this.mImageBatch.a();
        com.etsy.android.uikit.util.c.a(getView());
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
        com.etsy.android.lib.logger.f.c(TAG, "onSaveInstanceState: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
    }

    public j getRequestQueue() {
        return v.a().j();
    }

    public com.etsy.android.lib.core.posts.a getPostManager() {
        return v.a().k();
    }

    public c getImageBatch() {
        return this.mImageBatch;
    }
}
