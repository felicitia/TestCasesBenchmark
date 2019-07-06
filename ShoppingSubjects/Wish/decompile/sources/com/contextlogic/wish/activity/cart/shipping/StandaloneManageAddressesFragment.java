package com.contextlogic.wish.activity.cart.shipping;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseActivity.ActivityResultCallback;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.cart.shipping.AddressBookAdapter.AddressBookAdapterCallback;
import com.contextlogic.wish.activity.cart.shipping.AddressBookAdapter.AddressBookType;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishShippingInfo;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.dialog.BaseDialogFragment.BaseDialogCallback;
import com.contextlogic.wish.dialog.bottomsheet.SuccessBottomSheetDialog;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.util.DrawableUtil;
import com.contextlogic.wish.util.IntentUtil;
import java.util.ArrayList;
import java.util.List;

public class StandaloneManageAddressesFragment extends UiFragment<StandaloneManageAddressesActivity> implements AddressBookAdapterCallback {
    /* access modifiers changed from: private */
    public AddressBookAdapter mAdapter;
    private ThemedTextView mDoneButton;
    private View mFooterView;
    private ListView mListView;

    /* access modifiers changed from: protected */
    public int getLayoutResourceId() {
        return R.layout.standalone_manage_addresses_fragment;
    }

    public void releaseImages() {
    }

    public void restoreImages() {
    }

    /* access modifiers changed from: protected */
    public void initialize() {
        this.mListView = (ListView) findViewById(R.id.standalone_manage_addresses_list);
        this.mDoneButton = (ThemedTextView) findViewById(R.id.standalone_manage_addresses_done_button);
        this.mAdapter = new AddressBookAdapter(getContext(), this, AddressBookType.standalone);
        this.mListView.addHeaderView(LayoutInflater.from(getContext()).inflate(R.layout.address_book_header, this.mListView, false));
        this.mListView.setAdapter(this.mAdapter);
        this.mDoneButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                StandaloneManageAddressesFragment.this.handleDone();
            }
        });
        reload();
    }

    /* access modifiers changed from: private */
    public void handleDone() {
        withActivity(new ActivityTask<StandaloneManageAddressesActivity>() {
            public void performTask(StandaloneManageAddressesActivity standaloneManageAddressesActivity) {
                standaloneManageAddressesActivity.finishActivity();
            }
        });
    }

    private void updateActivityResult() {
        withActivity(new ActivityTask<StandaloneManageAddressesActivity>() {
            public void performTask(StandaloneManageAddressesActivity standaloneManageAddressesActivity) {
                WishShippingInfo currentlySelectedItem = StandaloneManageAddressesFragment.this.mAdapter == null ? null : StandaloneManageAddressesFragment.this.mAdapter.getCurrentlySelectedItem();
                if (currentlySelectedItem != null) {
                    Intent intent = new Intent();
                    intent.putExtra("ResultExtraCurrentShipping", currentlySelectedItem);
                    standaloneManageAddressesActivity.setResult(-1, intent);
                }
            }
        });
    }

    private void updateAddressBook(List<WishShippingInfo> list, String str) {
        if (list == null) {
            list = new ArrayList<>();
        }
        this.mAdapter.updateAddressBook(list, str);
        if (this.mFooterView == null) {
            this.mFooterView = createFooterView();
            this.mListView.addFooterView(this.mFooterView);
        }
        updateFooterView(!list.isEmpty());
        updateActivityResult();
    }

    private View createFooterView() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.address_book_footer, this.mListView, false);
        DrawableUtil.tintCompoundDrawables((TextView) inflate.findViewById(R.id.address_book_footer_text), getResources().getColor(R.color.main_primary));
        inflate.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_MANAGE_ADDRESSES_ADD_NEW);
                StandaloneManageAddressesFragment.this.startStandaloneShippingInfoActivity(null);
            }
        });
        return inflate;
    }

    public void updateFooterView(boolean z) {
        int i = z ? 0 : 8;
        this.mFooterView.findViewById(R.id.address_book_footer_top_border).setVisibility(i);
        this.mFooterView.findViewById(R.id.address_book_footer_top_border_2).setVisibility(i);
        this.mFooterView.findViewById(R.id.address_book_footer_top_spacing).setVisibility(i);
    }

    /* access modifiers changed from: private */
    public void reload() {
        withServiceFragment(new ServiceTask<BaseActivity, StandaloneManageAddressesServiceFragment>() {
            public void performTask(BaseActivity baseActivity, StandaloneManageAddressesServiceFragment standaloneManageAddressesServiceFragment) {
                standaloneManageAddressesServiceFragment.loadUserShippingInfo();
            }
        });
    }

    /* access modifiers changed from: private */
    public void startStandaloneShippingInfoActivity(final WishShippingInfo wishShippingInfo) {
        withActivity(new ActivityTask<StandaloneManageAddressesActivity>() {
            public void performTask(StandaloneManageAddressesActivity standaloneManageAddressesActivity) {
                Intent intent = new Intent();
                intent.setClass(standaloneManageAddressesActivity, StandaloneShippingInfoActivity.class);
                if (wishShippingInfo != null) {
                    IntentUtil.putParcelableExtra(intent, "ExtraEditAddressShippingInfo", wishShippingInfo);
                }
                standaloneManageAddressesActivity.startActivityForResult(intent, standaloneManageAddressesActivity.addResultCodeCallback(new ActivityResultCallback() {
                    public void onActivityResult(BaseActivity baseActivity, int i, int i2, Intent intent) {
                        if (i2 == -1) {
                            StandaloneManageAddressesFragment.this.reload();
                        }
                    }
                }));
            }
        });
    }

    public void setAddress(final WishShippingInfo wishShippingInfo) {
        withServiceFragment(new ServiceTask<BaseActivity, StandaloneManageAddressesServiceFragment>() {
            public void performTask(BaseActivity baseActivity, StandaloneManageAddressesServiceFragment standaloneManageAddressesServiceFragment) {
                standaloneManageAddressesServiceFragment.setShippingAddress(wishShippingInfo);
            }
        });
    }

    public void editAddress(WishShippingInfo wishShippingInfo) {
        startStandaloneShippingInfoActivity(wishShippingInfo);
    }

    public void deleteAddress(final WishShippingInfo wishShippingInfo) {
        withServiceFragment(new ServiceTask<StandaloneManageAddressesActivity, StandaloneManageAddressesServiceFragment>() {
            public void performTask(StandaloneManageAddressesActivity standaloneManageAddressesActivity, final StandaloneManageAddressesServiceFragment standaloneManageAddressesServiceFragment) {
                MultiButtonDialogFragment createMultiButtonYesDialog = MultiButtonDialogFragment.createMultiButtonYesDialog(StandaloneManageAddressesFragment.this.getResources().getString(R.string.delete_this_address), StandaloneManageAddressesFragment.this.getResources().getString(R.string.delete_address_description), StandaloneManageAddressesFragment.this.getResources().getString(R.string.delete_address), R.drawable.main_button_selector);
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_MOBILE_MANAGE_ADDRESSES_DELETE_DIALOG);
                standaloneManageAddressesActivity.startDialog(createMultiButtonYesDialog, new BaseDialogCallback() {
                    public void onCancel(BaseDialogFragment baseDialogFragment) {
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_MANAGE_ADDRESSES_DELETE_CANCEL);
                    }

                    public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_MANAGE_ADDRESSES_DELETE_CONFIRM);
                        standaloneManageAddressesServiceFragment.deleteShippingAddress(wishShippingInfo);
                    }
                });
            }
        });
    }

    public void onUserSetAddressSuccess(WishShippingInfo wishShippingInfo) {
        if (this.mAdapter != null) {
            this.mAdapter.updateDefaultId(wishShippingInfo.getId());
        }
        updateActivityResult();
    }

    public void onUserShippingInfoLoadSuccess(List<WishShippingInfo> list, String str) {
        updateAddressBook(list, str);
    }

    public void onDeleteAddressSuccess(List<WishShippingInfo> list, String str) {
        updateAddressBook(list, str);
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_MOBILE_MANAGE_ADDRESSES_DELETE_SUCCESS_DIALOG);
        SuccessBottomSheetDialog.create(getBaseActivity()).setTitle(getResources().getString(R.string.address_has_been_deleted)).autoDismiss().show();
    }
}
