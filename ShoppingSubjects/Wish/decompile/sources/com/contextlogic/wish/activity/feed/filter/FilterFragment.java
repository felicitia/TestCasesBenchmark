package com.contextlogic.wish.activity.feed.filter;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.UiTask;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.feed.ProductFeedFragment;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishFilter;
import com.contextlogic.wish.api.model.WishFilterOption;
import com.contextlogic.wish.cache.StateStoreCache;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class FilterFragment extends UiFragment<DrawerActivity> {
    private ImageView mBackButton;
    private TextView mClearButton;
    private TextView mDoneButton;
    /* access modifiers changed from: private */
    public FilterAdapter mFilterAdapter;
    private int mLevel;
    private ListView mListView;
    private ArrayList<WishFilter> mMainCategories;
    private int mPosition;

    /* access modifiers changed from: protected */
    public int getLayoutResourceId() {
        return R.layout.filter_fragment;
    }

    public boolean onBackPressed() {
        return true;
    }

    public void releaseImages() {
    }

    public void restoreImages() {
    }

    /* access modifiers changed from: protected */
    public void initialize() {
        this.mBackButton = (ImageView) findViewById(R.id.fragment_filter_back_button);
        this.mBackButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                FilterFragment.this.handleBackClicked();
            }
        });
        this.mClearButton = (TextView) findViewById(R.id.fragment_filter_clear);
        this.mClearButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                FilterFragment.this.handleClear();
            }
        });
        this.mDoneButton = (TextView) findViewById(R.id.fragment_filter_done);
        this.mDoneButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                FilterFragment.this.handleDone();
            }
        });
        this.mListView = (ListView) findViewById(R.id.fragment_filter_listview);
        this.mPosition = 0;
        this.mLevel = 0;
        this.mMainCategories = new ArrayList<>();
        withActivity(new ActivityTask<DrawerActivity>() {
            public void performTask(DrawerActivity drawerActivity) {
                FilterFragment.this.mFilterAdapter = new FilterAdapter(drawerActivity, FilterFragment.this);
            }
        });
        this.mListView.setAdapter(this.mFilterAdapter);
        this.mListView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                FilterFragment.this.handleItemClicked(i);
            }
        });
        if (getSavedInstanceState() != null) {
            if (!(getSavedInstanceState().getSerializable("SavedStateSelectedFilters") == null || this.mFilterAdapter == null)) {
                this.mFilterAdapter.setSelectedFilters((HashMap) getSavedInstanceState().getSerializable("SavedStateSelectedFilters"));
            }
            ArrayList parcelableList = StateStoreCache.getInstance().getParcelableList(getSavedInstanceState(), "SavedStateMainCategories", WishFilter.class);
            if (parcelableList != null) {
                updateMainCategories(parcelableList);
            }
        }
    }

    public void updateMainCategories(ArrayList<WishFilter> arrayList) {
        this.mMainCategories = arrayList;
        refreshFilters();
    }

    public void updatePosition(int i) {
        if (i != this.mPosition) {
            this.mPosition = i;
            if (this.mFilterAdapter != null) {
                this.mFilterAdapter.setPosition(i);
            }
            refreshFilters();
        }
    }

    public void refreshFilters() {
        if (this.mMainCategories != null && this.mMainCategories.size() >= 1) {
            WishFilter wishFilter = (WishFilter) this.mMainCategories.get(this.mPosition);
            if (!(wishFilter == null || wishFilter.getChildFilterGroups() == null || wishFilter.getChildFilterGroups().size() <= 0)) {
                if (this.mFilterAdapter != null) {
                    this.mFilterAdapter.setRootFilter(wishFilter.getChildFilterGroups());
                }
                this.mLevel = 0;
                this.mBackButton.setVisibility(8);
            }
        }
    }

    /* access modifiers changed from: private */
    public void handleBackClicked() {
        boolean z = true;
        this.mLevel--;
        if (this.mLevel == 0) {
            this.mBackButton.setVisibility(8);
        }
        if (this.mFilterAdapter != null) {
            FilterAdapter filterAdapter = this.mFilterAdapter;
            if (this.mLevel != 0) {
                z = false;
            }
            filterAdapter.backToParent(z);
        }
    }

    /* access modifiers changed from: private */
    public void handleItemClicked(int i) {
        if (this.mFilterAdapter != null) {
            WishFilterOption item = this.mFilterAdapter.getItem(i);
            if (item.getChildFilters() == null || item.getChildFilters().size() <= 0) {
                this.mFilterAdapter.selectFilter(this.mFilterAdapter.getItem(i));
                withUiFragment(new UiTask<BaseActivity, ProductFeedFragment>() {
                    public void performTask(BaseActivity baseActivity, ProductFeedFragment productFeedFragment) {
                        productFeedFragment.applyFilter();
                    }
                }, "FragmentTagMainContent");
                return;
            }
            this.mFilterAdapter.setFilters(item.getChildFilters());
            handleNextLevel();
        }
    }

    public void handleNextLevel() {
        this.mLevel++;
        this.mBackButton.setVisibility(0);
    }

    public void handleClear() {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_FEED_FILTER_CANCEL);
        if (this.mFilterAdapter != null) {
            this.mFilterAdapter.clearAllFilters();
        }
        withUiFragment(new UiTask<BaseActivity, ProductFeedFragment>() {
            public void performTask(BaseActivity baseActivity, ProductFeedFragment productFeedFragment) {
                productFeedFragment.handleFilterApply();
            }
        }, "FragmentTagMainContent");
    }

    public void handleDone() {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_FEED_FILTER_APPLY);
        withUiFragment(new UiTask<BaseActivity, ProductFeedFragment>() {
            public void performTask(BaseActivity baseActivity, ProductFeedFragment productFeedFragment) {
                productFeedFragment.handleFilterApply();
            }
        }, "FragmentTagMainContent");
    }

    public void backToRoot() {
        if (this.mFilterAdapter != null) {
            this.mFilterAdapter.backToParent(true);
        }
    }

    public ArrayList<WishFilter> getSelectedFilters(int i) {
        if (this.mFilterAdapter != null) {
            HashMap selectedFilters = this.mFilterAdapter.getSelectedFilters();
            if (selectedFilters.get(Integer.valueOf(i)) != null) {
                ArrayList<WishFilter> arrayList = new ArrayList<>();
                Iterator it = ((HashSet) selectedFilters.get(Integer.valueOf(i))).iterator();
                while (it.hasNext()) {
                    arrayList.add(new WishFilter((String) it.next()));
                }
                return arrayList;
            }
        }
        return null;
    }

    public void setScreenshotSelectedFilters(ArrayList<String> arrayList) {
        this.mFilterAdapter.selectScreenshotFilters(arrayList);
    }

    public void handleSaveInstanceState(Bundle bundle) {
        super.handleSaveInstanceState(bundle);
        if (this.mFilterAdapter != null) {
            bundle.putString("SavedStateMainCategories", StateStoreCache.getInstance().storeParcelableList(this.mMainCategories));
            bundle.putSerializable("SavedStateSelectedFilters", this.mFilterAdapter.getSelectedFilters());
        }
    }
}
