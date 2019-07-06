package com.bumptech.glide.manager;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.os.Build.VERSION;
import com.bumptech.glide.h;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@TargetApi(11)
public class RequestManagerFragment extends Fragment {
    private final HashSet<RequestManagerFragment> childRequestManagerFragments;
    private final a lifecycle;
    private h requestManager;
    private final j requestManagerTreeNode;
    private RequestManagerFragment rootRequestManagerFragment;

    private class a implements j {
        private a() {
        }
    }

    public RequestManagerFragment() {
        this(new a());
    }

    @SuppressLint({"ValidFragment"})
    RequestManagerFragment(a aVar) {
        this.requestManagerTreeNode = new a();
        this.childRequestManagerFragments = new HashSet<>();
        this.lifecycle = aVar;
    }

    public void setRequestManager(h hVar) {
        this.requestManager = hVar;
    }

    /* access modifiers changed from: 0000 */
    public a getLifecycle() {
        return this.lifecycle;
    }

    public h getRequestManager() {
        return this.requestManager;
    }

    public j getRequestManagerTreeNode() {
        return this.requestManagerTreeNode;
    }

    private void addChildRequestManagerFragment(RequestManagerFragment requestManagerFragment) {
        this.childRequestManagerFragments.add(requestManagerFragment);
    }

    private void removeChildRequestManagerFragment(RequestManagerFragment requestManagerFragment) {
        this.childRequestManagerFragments.remove(requestManagerFragment);
    }

    @TargetApi(17)
    public Set<RequestManagerFragment> getDescendantRequestManagerFragments() {
        if (this.rootRequestManagerFragment == this) {
            return Collections.unmodifiableSet(this.childRequestManagerFragments);
        }
        if (this.rootRequestManagerFragment == null || VERSION.SDK_INT < 17) {
            return Collections.emptySet();
        }
        HashSet hashSet = new HashSet();
        for (RequestManagerFragment requestManagerFragment : this.rootRequestManagerFragment.getDescendantRequestManagerFragments()) {
            if (isDescendant(requestManagerFragment.getParentFragment())) {
                hashSet.add(requestManagerFragment);
            }
        }
        return Collections.unmodifiableSet(hashSet);
    }

    @TargetApi(17)
    private boolean isDescendant(Fragment fragment) {
        Fragment parentFragment = getParentFragment();
        while (fragment.getParentFragment() != null) {
            if (fragment.getParentFragment() == parentFragment) {
                return true;
            }
            fragment = fragment.getParentFragment();
        }
        return false;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.rootRequestManagerFragment = RequestManagerRetriever.get().getRequestManagerFragment(getActivity().getFragmentManager());
        if (this.rootRequestManagerFragment != this) {
            this.rootRequestManagerFragment.addChildRequestManagerFragment(this);
        }
    }

    public void onDetach() {
        super.onDetach();
        if (this.rootRequestManagerFragment != null) {
            this.rootRequestManagerFragment.removeChildRequestManagerFragment(this);
            this.rootRequestManagerFragment = null;
        }
    }

    public void onStart() {
        super.onStart();
        this.lifecycle.a();
    }

    public void onStop() {
        super.onStop();
        this.lifecycle.b();
    }

    public void onDestroy() {
        super.onDestroy();
        this.lifecycle.c();
    }

    public void onTrimMemory(int i) {
        if (this.requestManager != null) {
            this.requestManager.a(i);
        }
    }

    public void onLowMemory() {
        if (this.requestManager != null) {
            this.requestManager.a();
        }
    }
}
