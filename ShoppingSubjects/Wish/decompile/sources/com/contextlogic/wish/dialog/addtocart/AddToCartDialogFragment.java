package com.contextlogic.wish.dialog.addtocart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.ListView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.util.DisplayUtil;
import com.contextlogic.wish.util.IntentUtil;
import java.util.ArrayList;
import java.util.HashMap;

public class AddToCartDialogFragment<A extends BaseActivity> extends BaseDialogFragment<A> {
    /* access modifiers changed from: private */
    public AddToCartAdapter mAdapter;
    private AutoReleasableImageView mBackButton;
    private View mContainer;
    private boolean mHasCallback;
    private FrameLayout mHeaderContainer;
    private ThemedTextView mHeaderViewTextView;
    private AddToCartState mInitialState;
    private ListView mListView;
    private WishProduct mProduct;
    private NetworkImageView mProductImage;
    private HashMap<AddToCartState, String> mSelectedChoices;
    private boolean mShowModal;
    private boolean mShowProductImage;
    private Source mSource;
    /* access modifiers changed from: private */
    public int mState;
    private ArrayList<AddToCartState> mStates;
    private ThemedTextView mTitle;
    private AutoReleasableImageView mXButton;

    public enum AddToCartState {
        SIZE,
        COLOR
    }

    public int getGravity() {
        return 81;
    }

    public static AddToCartDialogFragment<BaseActivity> createAddToCartDialog(WishProduct wishProduct, Source source, boolean z) {
        return createAddToCartDialog(wishProduct, source, false, z);
    }

    public static AddToCartDialogFragment<BaseActivity> createAddToCartDialog(WishProduct wishProduct, Source source, boolean z, boolean z2) {
        return createAddToCartDialog(wishProduct, source, z, AddToCartState.SIZE, z2);
    }

    public static AddToCartDialogFragment<BaseActivity> createAddToCartDialog(WishProduct wishProduct, Source source, boolean z, AddToCartState addToCartState, boolean z2) {
        AddToCartDialogFragment<BaseActivity> addToCartDialogFragment = new AddToCartDialogFragment<>();
        Bundle bundle = new Bundle();
        IntentUtil.putLargeParcelableExtra(bundle, "ArgumentProduct", wishProduct);
        bundle.putString("ArgumentSource", source.toString());
        bundle.putBoolean("ArgumentShowProductImage", z);
        bundle.putSerializable("ArgumentInitialState", addToCartState);
        bundle.putSerializable("ArgumentShowModal", Boolean.valueOf(wishProduct.canShowAddToCartModal()));
        bundle.putBoolean("ArgumentHasCallback", z2);
        addToCartDialogFragment.setArguments(bundle);
        return addToCartDialogFragment;
    }

    public View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (!initializeState()) {
            return null;
        }
        this.mSource = Source.fromString(getArguments().getString("ArgumentSource"));
        if (!this.mShowModal) {
            addToCart(this.mProduct.getCommerceDefaultVariationId());
            return null;
        }
        this.mContainer = layoutInflater.inflate(R.layout.add_to_cart_dialog_fragment, viewGroup, false);
        this.mXButton = (AutoReleasableImageView) this.mContainer.findViewById(R.id.add_to_cart_dialog_fragment_x_button);
        this.mXButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                AddToCartDialogFragment.this.handleCancel();
            }
        });
        this.mBackButton = (AutoReleasableImageView) this.mContainer.findViewById(R.id.add_to_cart_dialog_fragment_back_button);
        this.mBackButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                AddToCartDialogFragment.this.mState = AddToCartDialogFragment.this.mState - 1;
                AddToCartDialogFragment.this.refreshState();
            }
        });
        this.mProductImage = (NetworkImageView) this.mContainer.findViewById(R.id.add_to_cart_dialog_fragment_product_image);
        if (this.mShowProductImage) {
            this.mProductImage.setVisibility(0);
            this.mProductImage.setImage(this.mProduct.getImage());
        }
        this.mTitle = (ThemedTextView) this.mContainer.findViewById(R.id.add_to_cart_dialog_fragment_title);
        this.mListView = (ListView) this.mContainer.findViewById(R.id.add_to_cart_dialog_fragment_list_view);
        if (!(this.mProduct.getGroupBuyPrice() == null || this.mProduct.getGroupBuyAddToCartModalText() == null || this.mSource != Source.GROUP_BUY_CREATE)) {
            this.mHeaderViewTextView = new ThemedTextView(getContext());
            this.mHeaderViewTextView.setTextColor(WishApplication.getInstance().getResources().getColor(R.color.white));
            this.mHeaderViewTextView.setBackgroundColor(WishApplication.getInstance().getResources().getColor(R.color.main_primary));
            int dimensionPixelSize = WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.eight_padding);
            this.mHeaderViewTextView.setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
            this.mHeaderContainer = new FrameLayout(getContext());
            this.mHeaderContainer.addView(this.mHeaderViewTextView);
            this.mListView.addHeaderView(this.mHeaderContainer);
        }
        this.mAdapter = new AddToCartAdapter(getContext(), this.mProduct, this.mStates, this.mSource);
        this.mListView.setAdapter(this.mAdapter);
        this.mListView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                String item = AddToCartDialogFragment.this.mAdapter.getItem(i);
                if (item != null) {
                    AddToCartDialogFragment.this.handleOptionSelected(item);
                }
            }
        });
        this.mState = 0;
        refreshState();
        return this.mContainer;
    }

    public int getDialogWidth() {
        int displayWidth = DisplayUtil.getDisplayWidth();
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.bottom_dialog_fragment_max_width);
        return displayWidth > dimensionPixelSize ? dimensionPixelSize : displayWidth;
    }

    public boolean onBackPressed() {
        if (this.mState > 0) {
            this.mState--;
            refreshState();
        } else {
            cancel();
        }
        return true;
    }

    private boolean initializeState() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mProduct = (WishProduct) IntentUtil.getLargeParcelableExtra(arguments, "ArgumentProduct", WishProduct.class);
            this.mShowProductImage = arguments.getBoolean("ArgumentShowProductImage");
            this.mInitialState = (AddToCartState) arguments.getSerializable("ArgumentInitialState");
            this.mShowModal = getArguments().getBoolean("ArgumentShowModal");
            this.mHasCallback = arguments.getBoolean("ArgumentHasCallback");
        }
        this.mSelectedChoices = new HashMap<>();
        if (this.mProduct == null) {
            return false;
        }
        this.mStates = new ArrayList<>();
        if (this.mProduct.getVariationSizes().size() > 0) {
            this.mStates.add(AddToCartState.SIZE);
        }
        if (this.mProduct.getVariationColors().size() > 0) {
            this.mStates.add(AddToCartState.COLOR);
        }
        if (!(this.mInitialState == null || this.mStates.indexOf(this.mInitialState) == -1)) {
            this.mStates.remove(this.mStates.indexOf(this.mInitialState));
            this.mStates.add(0, this.mInitialState);
        }
        this.mState = 0;
        return true;
    }

    /* access modifiers changed from: private */
    public void refreshState() {
        if (this.mState > 0) {
            this.mBackButton.setVisibility(0);
        } else {
            this.mBackButton.setVisibility(8);
        }
        if (getState() == AddToCartState.SIZE) {
            this.mAdapter.resetSelectedSize();
            showSizePicker();
        } else if (getState() == AddToCartState.COLOR) {
            this.mAdapter.resetSelectedColor();
            showColorPicker();
        }
        this.mAdapter.setState(this.mState);
        handleHeaderView();
    }

    private void showSizePicker() {
        this.mTitle.setText(WishApplication.getInstance().getResources().getString(R.string.select_size));
    }

    private void showColorPicker() {
        this.mTitle.setText(WishApplication.getInstance().getResources().getString(R.string.select_color));
    }

    public void handleOptionSelected(String str) {
        if (this.mAdapter.isInStock(str)) {
            if (getState() == AddToCartState.SIZE) {
                this.mAdapter.setSelectedSize(str);
            } else if (getState() == AddToCartState.COLOR) {
                this.mAdapter.setSelectedColor(str);
            }
            handleStateTransition();
        }
    }

    private void handleHeaderView() {
        if (this.mHeaderViewTextView != null) {
            if (this.mAdapter.getHeaderText() != null) {
                this.mHeaderViewTextView.setText(this.mAdapter.getHeaderText());
                this.mHeaderViewTextView.setVisibility(0);
            } else {
                this.mHeaderViewTextView.setVisibility(8);
            }
        }
    }

    private void handleStateTransition() {
        this.mState++;
        if (this.mState > this.mStates.size() - 1) {
            handleDone();
        } else {
            refreshState();
        }
        this.mListView.setSelectionAfterHeaderView();
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_ADD_TO_CART_MODAL_SELECT);
    }

    private AddToCartState getState() {
        return (AddToCartState) this.mStates.get(this.mState);
    }

    private String updateAndGetSize() {
        if (this.mAdapter.getSelectedSize() != null) {
            this.mSelectedChoices.put(AddToCartState.SIZE, this.mAdapter.getSelectedSize());
        }
        return this.mAdapter.getSelectedSize();
    }

    private String updateAndGetColor() {
        if (this.mAdapter.getSelectedColor() != null) {
            this.mSelectedChoices.put(AddToCartState.COLOR, this.mAdapter.getSelectedColor());
        }
        return this.mAdapter.getSelectedColor();
    }

    private void handleDone() {
        addToCart(this.mProduct.getVariationId(updateAndGetSize(), updateAndGetColor()));
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_ADD_TO_CART_MODAL_DONE);
    }

    private void addToCart(String str) {
        if (this.mHasCallback) {
            Bundle bundle = new Bundle();
            bundle.putString("ResultProductId", this.mProduct.getCommerceProductId());
            bundle.putString("ResultVariationId", str);
            makeSelection(bundle);
            return;
        }
        String addToCartOfferId = this.mProduct.getAddToCartOfferId();
        performAddToCart(this.mProduct, str, this.mProduct.getDefaultShippingOptionId(str), addToCartOfferId);
    }

    /* access modifiers changed from: private */
    public void handleCancel() {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_ADD_TO_CART_MODAL_CANCEL);
        cancel();
    }

    public void performAddToCart(WishProduct wishProduct, String str, String str2, String str3) {
        final WishProduct wishProduct2 = wishProduct;
        final String str4 = str;
        final String str5 = str2;
        final String str6 = str3;
        AnonymousClass4 r0 = new ServiceTask<BaseActivity, ServiceFragment>() {
            public void performTask(BaseActivity baseActivity, ServiceFragment serviceFragment) {
                serviceFragment.addItemToCart(wishProduct2, str4, str5, str6, wishProduct2.getValue());
            }
        };
        withServiceFragment(r0);
    }
}
