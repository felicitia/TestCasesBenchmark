package com.contextlogic.wish.activity.crosspromo;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.LoadingUiFragment;
import com.contextlogic.wish.api.model.WishCrossPromoApp;
import com.contextlogic.wish.cache.StateStoreCache;
import java.util.ArrayList;

public class CrossPromoFragment extends LoadingUiFragment<CrossPromoActivity> {
    /* access modifiers changed from: private */
    public CrossPromoAdapter mAdapter;
    private ArrayList<WishCrossPromoApp> mCrossPromoApps;
    private ListView mListView;

    public boolean canPullToRefresh() {
        return false;
    }

    public int getLoadingContentLayoutResourceId() {
        return R.layout.cross_promo_fragment;
    }

    public void initializeLoadingContentView(View view) {
        this.mCrossPromoApps = new ArrayList<>();
        this.mListView = (ListView) view.findViewById(R.id.cross_promo_fragment_listview);
        this.mAdapter = new CrossPromoAdapter((CrossPromoActivity) getBaseActivity(), this);
        this.mListView.setAdapter(this.mAdapter);
        this.mListView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                final WishCrossPromoApp item = CrossPromoFragment.this.mAdapter.getItem(i);
                CrossPromoFragment.this.withServiceFragment(new ServiceTask<BaseActivity, CrossPromoServiceFragment>() {
                    public void performTask(BaseActivity baseActivity, CrossPromoServiceFragment crossPromoServiceFragment) {
                        crossPromoServiceFragment.handleCrossPromoAppClicked(item);
                    }
                });
            }
        });
        getLoadingPageView().setNoItemsMessage(getString(R.string.no_apps_found));
        initializeValues();
    }

    private void initializeValues() {
        if (getSavedInstanceState() != null) {
            updateCrossPromoApps(StateStoreCache.getInstance().getParcelableList(getSavedInstanceState(), "SavedStateData", WishCrossPromoApp.class));
        }
    }

    public void handleResume() {
        super.handleResume();
        if (!getLoadingPageView().isLoadingComplete()) {
            handleReload();
        }
    }

    public void restoreImages() {
        if (this.mAdapter != null) {
            this.mAdapter.restoreImages(this.mListView);
        }
    }

    public void releaseImages() {
        if (this.mAdapter != null) {
            this.mAdapter.releaseImages(this.mListView);
        }
    }

    public void handleSaveInstanceState(Bundle bundle) {
        if (getLoadingPageView() != null && getLoadingPageView().isLoadingComplete()) {
            bundle.putString("SavedStateData", StateStoreCache.getInstance().storeParcelableList(this.mCrossPromoApps));
        }
    }

    public void handleReload() {
        updateCrossPromoApps(null);
        withServiceFragment(new ServiceTask<BaseActivity, CrossPromoServiceFragment>() {
            public void performTask(BaseActivity baseActivity, CrossPromoServiceFragment crossPromoServiceFragment) {
                crossPromoServiceFragment.loadCrossPromoApps();
            }
        });
    }

    public boolean hasItems() {
        return this.mCrossPromoApps.size() > 0;
    }

    public ArrayList<WishCrossPromoApp> getCrossPromoApps() {
        return this.mCrossPromoApps;
    }

    public void handleLoadingErrored() {
        getLoadingPageView().markLoadingErrored();
    }

    public void handleLoadingSuccess(ArrayList<WishCrossPromoApp> arrayList) {
        updateCrossPromoApps(arrayList);
    }

    public void updateCrossPromoApps(ArrayList<WishCrossPromoApp> arrayList) {
        this.mCrossPromoApps.clear();
        if (arrayList != null) {
            this.mCrossPromoApps.addAll(arrayList);
            getLoadingPageView().markLoadingComplete();
        }
        if (this.mAdapter != null) {
            this.mAdapter.notifyDataSetChanged();
        }
    }
}
