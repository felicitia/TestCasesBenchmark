package com.contextlogic.wish.activity.cart.shipping;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.actionbar.ActionBarManager.HomeButtonMode;
import com.contextlogic.wish.activity.cart.CartActivity;
import com.contextlogic.wish.activity.cart.CartFragment;
import com.contextlogic.wish.activity.cart.CartServiceFragment;
import com.contextlogic.wish.activity.cart.CartServiceFragment.UpdateAddressBookCallback;
import com.contextlogic.wish.activity.cart.CartUiView;
import com.contextlogic.wish.activity.cart.shipping.AddressBookAdapter.AddressBookAdapterCallback;
import com.contextlogic.wish.activity.cart.shipping.AddressBookAdapter.AddressBookType;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishShippingInfo;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.dialog.BaseDialogFragment.BaseDialogCallback;
import com.contextlogic.wish.dialog.bottomsheet.SuccessBottomSheetDialog;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import com.contextlogic.wish.util.DrawableUtil;
import java.util.ArrayList;
import java.util.List;

@SuppressLint({"ViewConstructor"})
public class AddressBookView extends CartUiView implements AddressBookAdapterCallback {
    private AddressBookAdapter mAdapter;
    private View mFooterView;
    private ListView mListView;

    public boolean onBackPressed() {
        return false;
    }

    public void recycle() {
    }

    public void releaseImages() {
    }

    public void restoreImages() {
    }

    public AddressBookView(CartFragment cartFragment, CartActivity cartActivity, Bundle bundle) {
        super(cartFragment, cartActivity, bundle);
        if (cartActivity.shouldStartInAddressBook()) {
            this.mAdapter = new AddressBookAdapter(cartActivity, this, AddressBookType.accountSettings);
        } else {
            this.mAdapter = new AddressBookAdapter(cartActivity, this, AddressBookType.cart);
        }
    }

    public void initializeUi(Bundle bundle) {
        LayoutInflater from = LayoutInflater.from(getContext());
        from.inflate(R.layout.cart_fragment_cart_shipping_redesign, this);
        this.mListView = (ListView) findViewById(R.id.addresses_on_file_listview);
        this.mListView.addHeaderView(from.inflate(R.layout.address_book_header, this.mListView, false));
        this.mListView.addFooterView(createFooterTextView(false));
        this.mListView.setAdapter(this.mAdapter);
        getCartFragment().withServiceFragment(new ServiceTask<CartActivity, CartServiceFragment>() {
            public void performTask(CartActivity cartActivity, CartServiceFragment cartServiceFragment) {
                cartServiceFragment.getUserShippingInfo(new UpdateAddressBookCallback() {
                    public void onSuccess(List<WishShippingInfo> list, String str) {
                        AddressBookView.this.updateAddressBook(list, str);
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public void updateAddressBook(List<WishShippingInfo> list, String str) {
        if (list == null) {
            list = new ArrayList<>();
        }
        this.mAdapter.updateAddressBook(list, str);
        if (this.mFooterView != null) {
            this.mListView.removeFooterView(this.mFooterView);
        }
        this.mListView.addFooterView(createFooterTextView(!list.isEmpty()));
    }

    public View createFooterTextView(boolean z) {
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(getContext()).inflate(R.layout.address_book_footer, this.mListView, false);
        if (!z) {
            View findViewById = viewGroup.findViewById(R.id.address_book_footer_top_border);
            View findViewById2 = viewGroup.findViewById(R.id.address_book_footer_top_border_2);
            View findViewById3 = viewGroup.findViewById(R.id.address_book_footer_top_spacing);
            findViewById.setVisibility(8);
            findViewById2.setVisibility(8);
            findViewById3.setVisibility(8);
        }
        DrawableUtil.tintCompoundDrawables((TextView) viewGroup.findViewById(R.id.address_book_footer_text), getResources().getColor(R.color.main_primary));
        viewGroup.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                AddressBookView.this.getCartFragment().withActivity(new ActivityTask<CartActivity>() {
                    public void performTask(CartActivity cartActivity) {
                        if (cartActivity.shouldStartInAddressBook()) {
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_MANAGE_ADDRESSES_ADD_NEW);
                        } else {
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_ADD_NEW_ADDRESS_FROM_CART);
                        }
                    }
                });
                AddressBookView.this.getCartFragment().addAddressToAddressBook();
            }
        });
        this.mFooterView = viewGroup;
        return viewGroup;
    }

    public void setAddress(final WishShippingInfo wishShippingInfo) {
        getCartFragment().withServiceFragment(new ServiceTask<BaseActivity, CartServiceFragment>() {
            public void performTask(BaseActivity baseActivity, CartServiceFragment cartServiceFragment) {
                cartServiceFragment.setShippingAddress(wishShippingInfo);
            }
        });
    }

    public void editAddress(WishShippingInfo wishShippingInfo) {
        getCartFragment().editAddressFromAddressBook(wishShippingInfo);
    }

    public void deleteAddress(final WishShippingInfo wishShippingInfo) {
        getCartFragment().withServiceFragment(new ServiceTask<CartActivity, CartServiceFragment>() {
            public void performTask(final CartActivity cartActivity, final CartServiceFragment cartServiceFragment) {
                MultiButtonDialogFragment createMultiButtonYesDialog = MultiButtonDialogFragment.createMultiButtonYesDialog(AddressBookView.this.getResources().getString(R.string.delete_this_address), AddressBookView.this.getResources().getString(R.string.delete_address_description), AddressBookView.this.getResources().getString(R.string.delete_address), R.drawable.main_button_selector);
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_MOBILE_MANAGE_ADDRESSES_DELETE_DIALOG);
                cartActivity.startDialog(createMultiButtonYesDialog, new BaseDialogCallback() {
                    public void onCancel(BaseDialogFragment baseDialogFragment) {
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_MANAGE_ADDRESSES_DELETE_CANCEL);
                    }

                    public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_MANAGE_ADDRESSES_DELETE_CONFIRM);
                        cartServiceFragment.deleteShippingAddress(wishShippingInfo, new UpdateAddressBookCallback() {
                            public void onSuccess(List<WishShippingInfo> list, String str) {
                                AddressBookView.this.updateAddressBook(list, str);
                                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_MOBILE_MANAGE_ADDRESSES_DELETE_SUCCESS_DIALOG);
                                SuccessBottomSheetDialog.create(cartActivity).setTitle(AddressBookView.this.getResources().getString(R.string.address_has_been_deleted)).autoDismiss().show();
                            }
                        });
                    }
                });
            }
        });
    }

    public void refreshUi() {
        if (this.mAdapter != null && getCartFragment().getCartContext() != null && getCartFragment().getCartContext().getShippingInfo() != null) {
            this.mAdapter.updateDefaultId(getCartFragment().getCartContext().getShippingInfo().getId());
        }
    }

    public void updateActionBar() {
        getCartFragment().withActivity(new ActivityTask<CartActivity>() {
            public void performTask(CartActivity cartActivity) {
                cartActivity.getActionBarManager().setTitle(WishApplication.getInstance().getResources().getString(R.string.address_book));
                cartActivity.getActionBarManager().setHomeButtonMode(HomeButtonMode.BACK_ARROW);
            }
        });
    }
}
