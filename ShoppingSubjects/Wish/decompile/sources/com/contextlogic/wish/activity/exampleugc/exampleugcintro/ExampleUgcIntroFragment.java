package com.contextlogic.wish.activity.exampleugc.exampleugcintro;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.LoadingUiFragment;
import com.contextlogic.wish.activity.camera.camerapreview.CameraActivity;
import com.contextlogic.wish.activity.exampleugc.ExampleUgcItemsActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishRating;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.button.ThemedButton;
import com.contextlogic.wish.ui.listview.HorizontalListView;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.util.PreferenceUtil;
import java.util.ArrayList;
import java.util.HashMap;

public class ExampleUgcIntroFragment extends LoadingUiFragment<ExampleUgcIntroActivity> {
    private ExampleUgcIntroItemsAdapter mAdapter;
    private ThemedButton mContinueButton;
    private ImageHttpPrefetcher mImagePrefetcher;
    private HorizontalListView mListView;
    private ThemedTextView mPointsQuestionButton;
    ArrayList<WishRating> mRatings;
    private ThemedTextView mSharePurchaseDescription;
    private ThemedTextView mSharePurchaseTitle;
    HashMap<String, String> mTextContentMap;
    private ThemedTextView mViewMoreButton;

    public boolean canPullToRefresh() {
        return false;
    }

    public int getLoadingContentLayoutResourceId() {
        return R.layout.example_ugc_intro_fragment;
    }

    public void handleReload() {
    }

    public void initializeLoadingContentView(View view) {
        this.mListView = (HorizontalListView) findViewById(R.id.example_ugc_intro_horizontal_scroll_view);
        this.mSharePurchaseTitle = (ThemedTextView) findViewById(R.id.share_purchase_title);
        this.mSharePurchaseDescription = (ThemedTextView) findViewById(R.id.share_purchase_description);
        this.mPointsQuestionButton = (ThemedTextView) findViewById(R.id.points_question_button);
        this.mViewMoreButton = (ThemedTextView) findViewById(R.id.view_more_button);
        this.mContinueButton = (ThemedButton) findViewById(R.id.continue_button);
        this.mImagePrefetcher = new ImageHttpPrefetcher();
        this.mAdapter = new ExampleUgcIntroItemsAdapter(this, this.mImagePrefetcher);
        this.mListView.setAdapter(this.mAdapter);
        setupClickListeners();
        loadExampleUgcItems();
    }

    private void setupClickListeners() {
        this.mPointsQuestionButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ExampleUgcIntroFragment.this.withActivity(new ActivityTask<ExampleUgcIntroActivity>() {
                    public void performTask(ExampleUgcIntroActivity exampleUgcIntroActivity) {
                        exampleUgcIntroActivity.startDialog(ExampleUgcIntroDialogFragment.createPointsDialogFragment((String) ExampleUgcIntroFragment.this.mTextContentMap.get("PointsTitle"), ((String) ExampleUgcIntroFragment.this.mTextContentMap.get("PointsDescKey")).split("\\r?\\n")));
                    }
                });
            }
        });
        this.mViewMoreButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ExampleUgcIntroFragment.this.withActivity(new ActivityTask<ExampleUgcIntroActivity>() {
                    public void performTask(ExampleUgcIntroActivity exampleUgcIntroActivity) {
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_EXAMPLE_UGC_INTRO_VIEW_EXAMPLES_BUTTON);
                        exampleUgcIntroActivity.startActivity(new Intent(exampleUgcIntroActivity, ExampleUgcItemsActivity.class));
                    }
                });
            }
        });
        this.mContinueButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_EXAMPLE_UGC_INTRO_OPEN_CAMERA_BUTTON);
                ExampleUgcIntroFragment.this.withActivity(new ActivityTask<ExampleUgcIntroActivity>() {
                    public void performTask(ExampleUgcIntroActivity exampleUgcIntroActivity) {
                        if (exampleUgcIntroActivity.getIntent().getBooleanExtra(ExampleUgcIntroActivity.EXTRA_OPENED_FROM_CAMERA, false)) {
                            exampleUgcIntroActivity.finishActivity();
                        } else {
                            CameraActivity.startCameraActivityIfPermissionsGranted(exampleUgcIntroActivity);
                        }
                    }
                });
            }
        });
    }

    private void loadExampleUgcItems() {
        withServiceFragment(new ServiceTask<BaseActivity, ExampleUgcIntroServiceFragment>() {
            public void performTask(BaseActivity baseActivity, ExampleUgcIntroServiceFragment exampleUgcIntroServiceFragment) {
                exampleUgcIntroServiceFragment.getExampleUgcItems(Integer.valueOf(6));
            }
        });
    }

    public void handleLoadingSuccess(ArrayList<WishRating> arrayList, HashMap<String, String> hashMap) {
        this.mRatings = arrayList;
        this.mTextContentMap = hashMap;
        this.mSharePurchaseTitle.setText((CharSequence) hashMap.get("SharePurchaseTitleKey"));
        this.mSharePurchaseDescription.setText((CharSequence) hashMap.get("SharePurchaseDescKey"));
        this.mAdapter.setRatings(arrayList);
        PreferenceUtil.setBoolean("CameraFeatureSeen", true);
        getLoadingPageView().markLoadingComplete();
    }

    public int getHorizontalListViewHeight() {
        if (this.mListView != null) {
            return this.mListView.getHeight();
        }
        return 0;
    }

    public void handleLoadingFailure(String str) {
        getLoadingPageView().markLoadingErrored();
    }

    public boolean hasItems() {
        return getLoadingPageView().isLoadingComplete();
    }

    public void restoreImages() {
        if (this.mImagePrefetcher != null) {
            this.mImagePrefetcher.pausePrefetching();
        }
    }

    public void releaseImages() {
        if (this.mImagePrefetcher != null) {
            this.mImagePrefetcher.resumePrefetching();
        }
    }

    public void onDestroy() {
        if (this.mListView != null) {
            this.mListView.teardown();
        }
        super.onDestroy();
    }
}
