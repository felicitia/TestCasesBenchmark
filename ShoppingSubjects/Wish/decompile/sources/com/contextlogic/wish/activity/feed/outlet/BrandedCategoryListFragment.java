package com.contextlogic.wish.activity.feed.outlet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.actionbar.ActionBarManager.Theme;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishCategory;
import com.contextlogic.wish.cache.StateStoreCache;
import com.contextlogic.wish.util.ValueUtil;
import java.util.ArrayList;

public class BrandedCategoryListFragment extends UiFragment<BrandedCategoryListActivity> {
    /* access modifiers changed from: private */
    public BrandedCategoryListAdapter mAdapter;
    /* access modifiers changed from: private */
    public ArrayList<WishCategory> mCategories;
    private ListView mListView;

    /* access modifiers changed from: protected */
    public final int getLayoutResourceId() {
        return R.layout.branded_category_list_fragment;
    }

    public void releaseImages() {
    }

    public void restoreImages() {
    }

    public final void initialize() {
        this.mListView = (ListView) findViewById(R.id.branded_category_list_view);
        this.mListView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (i < BrandedCategoryListFragment.this.mCategories.size()) {
                    BrandedCategoryListFragment.this.handleCategorySelected((WishCategory) BrandedCategoryListFragment.this.mCategories.get(i));
                }
            }
        });
        this.mAdapter = new BrandedCategoryListAdapter((BrandedCategoryListActivity) getBaseActivity(), this);
        this.mListView.setAdapter(this.mAdapter);
        withActivity(new ActivityTask<BrandedCategoryListActivity>() {
            public void performTask(BrandedCategoryListActivity brandedCategoryListActivity) {
                BrandedCategoryListFragment.this.mCategories = brandedCategoryListActivity.getCategories();
                BrandedCategoryListFragment.this.mAdapter.setCategories(BrandedCategoryListFragment.this.mCategories);
            }
        });
        TextView textView = new TextView(getContext());
        textView.setHeight((int) ValueUtil.convertDpToPx(30.0f));
        this.mListView.addFooterView(textView);
        initializeValues();
    }

    private void initializeValues() {
        withActivity(new ActivityTask<BrandedCategoryListActivity>() {
            public void performTask(BrandedCategoryListActivity brandedCategoryListActivity) {
                brandedCategoryListActivity.getActionBarManager().setTheme(Theme.WHITE_BACKGROUND);
            }
        });
        if (getSavedInstanceState() != null) {
            ArrayList<WishCategory> parcelableList = StateStoreCache.getInstance().getParcelableList(getSavedInstanceState(), "SavedStateCategories", WishCategory.class);
            if (parcelableList != null) {
                this.mCategories = parcelableList;
                this.mAdapter.setCategories(this.mCategories);
            }
        }
    }

    public void handleCategorySelected(final WishCategory wishCategory) {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_BRANDED_PRODUCT_SELECT_CATEGORY_ROW);
        withActivity(new ActivityTask<BrandedCategoryListActivity>() {
            public void performTask(BrandedCategoryListActivity brandedCategoryListActivity) {
                Intent intent = new Intent();
                intent.setClass(brandedCategoryListActivity, BrandedFeedActivity.class);
                intent.putExtra(BrandedFeedActivity.EXTRA_CATEGORY, wishCategory);
                brandedCategoryListActivity.startActivity(intent);
            }
        });
    }

    public void handleResume() {
        super.handleResume();
    }

    public void handleSaveInstanceState(Bundle bundle) {
        bundle.putString("SavedStateCategories", StateStoreCache.getInstance().storeParcelableList(this.mCategories));
    }
}
