package com.contextlogic.wish.dialog.promotion.rotating;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishRotatingSaleNotificationSpec;
import com.contextlogic.wish.api.model.WishTag;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import java.util.ArrayList;

public class PromoRotatingNotiDialogFragment<A extends BaseActivity> extends BaseDialogFragment<A> {
    private Button mActionButton;
    /* access modifiers changed from: private */
    public PromoRotatingDialogTagAdapter mAdapter;
    private TextView mCancelText;
    private ListView mCategoryList;
    private View mCloseXButton;
    private TextView mSubtitleText;
    private TextView mTitleText;

    public static PromoRotatingNotiDialogFragment<BaseActivity> createSelectCategoryDialog() {
        return new PromoRotatingNotiDialogFragment<>();
    }

    public View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.promo_rotating_noti_category_dialog_fragment, viewGroup);
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_ROTATING_PROMO_NOTI_DIALOG);
        bindViews(inflate);
        requestSpec();
        return inflate;
    }

    private void bindViews(View view) {
        this.mTitleText = (TextView) view.findViewById(R.id.promo_rotating_noti_dialog_fragment_title);
        this.mSubtitleText = (TextView) view.findViewById(R.id.promo_rotating_noti_dialog_fragment_message);
        this.mActionButton = (Button) view.findViewById(R.id.promotion_rotating_noti_dialog_fragment_action_button);
        this.mCloseXButton = view.findViewById(R.id.promotion_rotating_noti_dialog_fragment_x);
        this.mCancelText = (TextView) view.findViewById(R.id.promotion_rotating_noti_dialog_fragment_close_text);
        this.mCategoryList = (ListView) view.findViewById(R.id.promotion_rotating_noti_dialog_fragment_list);
    }

    private void setupList(ArrayList<WishTag> arrayList, ArrayList<WishTag> arrayList2) {
        this.mAdapter = new PromoRotatingDialogTagAdapter(getContext(), arrayList, arrayList2);
        this.mCategoryList.addHeaderView(this.mAdapter.getSelectAllListHeader());
        this.mCategoryList.setAdapter(this.mAdapter);
        this.mCategoryList.setDivider(null);
    }

    private void setupListeners() {
        this.mActionButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                PromoRotatingNotiDialogFragment.this.updateTags();
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_ROTATING_PROMO_NOTI_DIALOG_CREATE_ALERT);
            }
        });
        this.mCancelText.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_ROTATING_PROMO_NOTI_DIALOG_NOT_RIGHT_NOW);
                PromoRotatingNotiDialogFragment.this.cancel();
            }
        });
        this.mCloseXButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_ROTATING_PROMO_NOTI_DIALOG_X_CLOSE);
                PromoRotatingNotiDialogFragment.this.cancel();
            }
        });
    }

    public void handleFailure(final String str) {
        withActivity(new ActivityTask<A>() {
            public void performTask(A a) {
                a.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(str));
            }
        });
    }

    private void requestSpec() {
        withServiceFragment(new ServiceTask<BaseActivity, ServiceFragment>() {
            public void performTask(BaseActivity baseActivity, ServiceFragment serviceFragment) {
                PromoRotatingNotiDialogFragment.this.showProgressSpinner();
                serviceFragment.requestRotatingPromoNotiSpec();
            }
        });
    }

    /* access modifiers changed from: private */
    public void updateTags() {
        if (this.mAdapter != null) {
            withServiceFragment(new ServiceTask<BaseActivity, ServiceFragment>() {
                public void performTask(BaseActivity baseActivity, ServiceFragment serviceFragment) {
                    PromoRotatingNotiDialogFragment.this.showProgressSpinner();
                    serviceFragment.updateRotatingPromoNotiTags(PromoRotatingNotiDialogFragment.this.mAdapter.getSelectedTagIds());
                }
            });
        }
    }

    public void handleSpecLoadSuccess(WishRotatingSaleNotificationSpec wishRotatingSaleNotificationSpec) {
        hideProgressSpinner();
        if (wishRotatingSaleNotificationSpec == null || wishRotatingSaleNotificationSpec.getAllTags() == null) {
            cancel();
            return;
        }
        if (!TextUtils.isEmpty(wishRotatingSaleNotificationSpec.getDialogTitle())) {
            this.mTitleText.setText(wishRotatingSaleNotificationSpec.getDialogTitle());
        }
        if (!TextUtils.isEmpty(wishRotatingSaleNotificationSpec.getDialogSubtitle())) {
            this.mSubtitleText.setText(wishRotatingSaleNotificationSpec.getDialogSubtitle());
        }
        if (!TextUtils.isEmpty(wishRotatingSaleNotificationSpec.getButtonText())) {
            this.mActionButton.setText(wishRotatingSaleNotificationSpec.getButtonText());
        }
        if (!TextUtils.isEmpty(wishRotatingSaleNotificationSpec.getCancelText())) {
            this.mCancelText.setText(wishRotatingSaleNotificationSpec.getCancelText());
        }
        setupList(wishRotatingSaleNotificationSpec.getSelectedTags(), wishRotatingSaleNotificationSpec.getAllTags());
        setupListeners();
    }

    public void handleTagUpdateSuccess() {
        hideProgressSpinner();
        cancel();
    }
}
