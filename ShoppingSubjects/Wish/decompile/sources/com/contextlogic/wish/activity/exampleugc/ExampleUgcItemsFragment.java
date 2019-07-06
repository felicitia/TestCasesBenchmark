package com.contextlogic.wish.activity.exampleugc;

import android.content.Intent;
import android.view.View;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.LoadingUiFragment;
import com.contextlogic.wish.activity.imageviewer.ImageViewerActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishProductExtraImage;
import com.contextlogic.wish.api.model.WishRating;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.grid.StaggeredGridView;
import com.contextlogic.wish.ui.grid.StaggeredGridView.OnItemClickListener;
import com.contextlogic.wish.util.IntentUtil;
import java.util.ArrayList;
import java.util.HashMap;

public class ExampleUgcItemsFragment extends LoadingUiFragment<ExampleUgcItemsActivity> {
    /* access modifiers changed from: private */
    public ExampleUgcItemsAdapter mAdapter;
    private ImageHttpPrefetcher mImagePrefetcher;
    /* access modifiers changed from: private */
    public ArrayList<WishRating> mRatings;
    private StaggeredGridView mRatingsView;

    public boolean canPullToRefresh() {
        return false;
    }

    public int getLoadingContentLayoutResourceId() {
        return R.layout.example_ugc_items_fragment;
    }

    public void initializeLoadingContentView(View view) {
        this.mRatingsView = (StaggeredGridView) findViewById(R.id.example_ugc_items_gridview);
        this.mImagePrefetcher = new ImageHttpPrefetcher();
        withActivity(new ActivityTask<ExampleUgcItemsActivity>() {
            public void performTask(ExampleUgcItemsActivity exampleUgcItemsActivity) {
                ExampleUgcItemsFragment.this.mAdapter = new ExampleUgcItemsAdapter(exampleUgcItemsActivity);
            }
        });
        this.mAdapter.setImagePrefetcher(this.mImagePrefetcher);
        this.mRatingsView.setAdapter(this.mAdapter);
        setupClickListeners();
        loadExampleUgcItems();
    }

    private void setupClickListeners() {
        this.mRatingsView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(int i, View view) {
                final WishRating wishRating = (WishRating) ExampleUgcItemsFragment.this.mRatings.get(i);
                final ArrayList access$200 = ExampleUgcItemsFragment.this.getExtraImageInfo(wishRating);
                ExampleUgcItemsFragment.this.trackEvent(wishRating);
                ExampleUgcItemsFragment.this.withActivity(new ActivityTask<ExampleUgcItemsActivity>() {
                    public void performTask(ExampleUgcItemsActivity exampleUgcItemsActivity) {
                        Intent intent = new Intent();
                        intent.setClass(exampleUgcItemsActivity, ImageViewerActivity.class);
                        intent.putExtra("ExtraImageUrl", wishRating.getImageLargeUrlString());
                        intent.putExtra("ExtraStartIndex", 0);
                        intent.putExtra("ExtraProductId", wishRating.getProductId());
                        intent.putExtra("ExtraAllowUpvote", false);
                        intent.putExtra("ExtraShowSingleImage", true);
                        IntentUtil.putParcelableArrayListExtra(intent, "ExtraMediaSources", access$200);
                        exampleUgcItemsActivity.startActivity(intent);
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public void trackEvent(WishRating wishRating) {
        HashMap hashMap = new HashMap();
        hashMap.put("product_id", wishRating.getProductId());
        hashMap.put("rating_id", wishRating.getRatingId());
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_EXAMPLE_UGC_ITEM, hashMap);
    }

    /* access modifiers changed from: private */
    public ArrayList<WishProductExtraImage> getExtraImageInfo(WishRating wishRating) {
        WishProductExtraImage wishProductExtraImage = new WishProductExtraImage(wishRating.getThumbnailImage());
        wishProductExtraImage.setIsUgc(true);
        wishProductExtraImage.setComment(wishRating.getComment());
        wishProductExtraImage.setUploader(wishRating.getAuthor());
        wishProductExtraImage.setTimestamp(wishRating.getTimestamp());
        ArrayList<WishProductExtraImage> arrayList = new ArrayList<>();
        arrayList.add(wishProductExtraImage);
        return arrayList;
    }

    public void handleReload() {
        getLoadingPageView().prepareForReload();
        loadExampleUgcItems();
    }

    public boolean hasItems() {
        return getLoadingPageView().isLoadingComplete();
    }

    public void releaseImages() {
        if (this.mRatingsView != null) {
            this.mRatingsView.releaseImages();
        }
        if (this.mImagePrefetcher != null) {
            this.mImagePrefetcher.pausePrefetching();
        }
    }

    public void restoreImages() {
        if (this.mRatingsView != null) {
            this.mRatingsView.restoreImages();
        }
        if (this.mImagePrefetcher != null) {
            this.mImagePrefetcher.resumePrefetching();
        }
    }

    public void handleLoadingSuccess(ArrayList<WishRating> arrayList, HashMap<String, String> hashMap) {
        this.mRatings = arrayList;
        setupHeader((String) hashMap.get("ExampleUgcItemsKey"), (String) hashMap.get("ExampleUgcItemsDesc"));
        this.mAdapter.setRatings(arrayList);
        this.mRatingsView.notifyDataSetChanged();
        getLoadingPageView().markLoadingComplete();
    }

    public void handleLoadingFailure(String str) {
        getLoadingPageView().markLoadingErrored();
    }

    private void loadExampleUgcItems() {
        withServiceFragment(new ServiceTask<BaseActivity, ExampleUgcItemsServiceFragment>() {
            public void performTask(BaseActivity baseActivity, ExampleUgcItemsServiceFragment exampleUgcItemsServiceFragment) {
                exampleUgcItemsServiceFragment.getExampleUgcItems();
            }
        });
    }

    private void setupHeader(String str, String str2) {
        ExampleUgcItemsHeader exampleUgcItemsHeader = new ExampleUgcItemsHeader(getContext());
        exampleUgcItemsHeader.setup(str, str2);
        this.mRatingsView.setHeaderView(exampleUgcItemsHeader);
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mRatingsView != null) {
            this.mRatingsView.teardown();
        }
        if (this.mImagePrefetcher != null) {
            this.mImagePrefetcher.cancelPrefetching();
        }
    }
}
