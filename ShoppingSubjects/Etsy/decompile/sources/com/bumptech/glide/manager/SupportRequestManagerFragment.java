package com.bumptech.glide.manager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.v4.app.Fragment;
import com.bumptech.glide.h;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SupportRequestManagerFragment extends Fragment {
    private final HashSet<SupportRequestManagerFragment> childRequestManagerFragments;
    private final a lifecycle;
    private h requestManager;
    private final j requestManagerTreeNode;
    private SupportRequestManagerFragment rootRequestManagerFragment;

    private class a implements j {
        private a() {
        }
    }

    public SupportRequestManagerFragment() {
        this(new a());
    }

    @SuppressLint({"ValidFragment"})
    public SupportRequestManagerFragment(a aVar) {
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

    private void addChildRequestManagerFragment(SupportRequestManagerFragment supportRequestManagerFragment) {
        this.childRequestManagerFragments.add(supportRequestManagerFragment);
    }

    private void removeChildRequestManagerFragment(SupportRequestManagerFragment supportRequestManagerFragment) {
        this.childRequestManagerFragments.remove(supportRequestManagerFragment);
    }

    public Set<SupportRequestManagerFragment> getDescendantRequestManagerFragments() {
        if (this.rootRequestManagerFragment == null) {
            return Collections.emptySet();
        }
        if (this.rootRequestManagerFragment == this) {
            return Collections.unmodifiableSet(this.childRequestManagerFragments);
        }
        HashSet hashSet = new HashSet();
        for (SupportRequestManagerFragment supportRequestManagerFragment : this.rootRequestManagerFragment.getDescendantRequestManagerFragments()) {
            if (isDescendant(supportRequestManagerFragment.getParentFragment())) {
                hashSet.add(supportRequestManagerFragment);
            }
        }
        return Collections.unmodifiableSet(hashSet);
    }

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
        this.rootRequestManagerFragment = RequestManagerRetriever.get().getSupportRequestManagerFragment(getActivity().getSupportFragmentManager());
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

    public void onLowMemory() {
        super.onLowMemory();
        if (this.requestManager != null) {
            this.requestManager.a();
        }
    }
}
