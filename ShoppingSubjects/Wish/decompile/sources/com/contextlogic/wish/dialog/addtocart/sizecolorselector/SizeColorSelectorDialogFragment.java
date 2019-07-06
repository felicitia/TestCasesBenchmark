package com.contextlogic.wish.dialog.addtocart.sizecolorselector;

import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishLocalizedCurrencyValue;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.dialog.addtocart.Source;
import com.contextlogic.wish.dialog.addtocart.sizecolorselector.ProductVariantState.DataType;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.util.DisplayUtil;
import com.google.android.flexbox.FlexboxLayoutManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class SizeColorSelectorDialogFragment<A extends BaseActivity> extends BaseDialogFragment<A> {
    private ThemedTextView mAddToCartButton;
    private ColorSwatchAdapter mColorAdapter;
    private ThemedTextView mColorSelectedTextView;
    private ConstraintLayout mColorSwatchContainer;
    private RecyclerView mColorSwatchRecyclerView;
    private View mContainer;
    private boolean mHasCallback;
    private boolean mIsDailyGiveaway;
    private boolean mIsFreeGift;
    private ThemedTextView mListPriceTextView;
    private WishProduct mProduct;
    private NetworkImageView mProductImage;
    private String mSelectedVariantId;
    private boolean mShowModal;
    private boolean mShowProductImage;
    private SizeSwatchAdapter mSizeAdapter;
    private ThemedTextView mSizeSelectedTextView;
    private ConstraintLayout mSizeSwatchContainer;
    private RecyclerView mSizeSwatchRecyclerView;
    private Source mSource;
    private View mXButton;
    private ThemedTextView mYourPriceTextView;

    public int getGravity() {
        return 81;
    }

    public static SizeColorSelectorDialogFragment<BaseActivity> createSizeColorSelectorDialogFragment(WishProduct wishProduct, Source source, boolean z) {
        return createSizeColorSelectorDialogFragment(wishProduct, source, false, z);
    }

    public static SizeColorSelectorDialogFragment<BaseActivity> createSizeColorSelectorDialogFragment(WishProduct wishProduct, Source source, boolean z, boolean z2) {
        SizeColorSelectorDialogFragment<BaseActivity> sizeColorSelectorDialogFragment = new SizeColorSelectorDialogFragment<>();
        Bundle bundle = new Bundle();
        bundle.putParcelable("ArgumentProduct", wishProduct);
        bundle.putString("ArgumentSource", source.toString());
        bundle.putBoolean("ArgumentShowProductImage", z);
        bundle.putSerializable("ArgumentShowModal", Boolean.valueOf(wishProduct.canShowAddToCartModal()));
        bundle.putBoolean("ArgumentHasCallback", z2);
        sizeColorSelectorDialogFragment.setArguments(bundle);
        return sizeColorSelectorDialogFragment;
    }

    private void initializeValues() {
        Bundle arguments = getArguments();
        this.mProduct = (WishProduct) arguments.getParcelable("ArgumentProduct");
        this.mSource = Source.fromString(arguments.getString("ArgumentSource"));
        this.mShowProductImage = arguments.getBoolean("ArgumentShowProductImage");
        this.mHasCallback = arguments.getBoolean("ArgumentHasCallback");
        this.mShowModal = arguments.getBoolean("ArgumentShowModal");
        boolean z = false;
        this.mIsFreeGift = this.mSource == Source.FREE_GIFT;
        if (this.mSource == Source.DAILY_GIVEAWAY) {
            z = true;
        }
        this.mIsDailyGiveaway = z;
    }

    public View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        initializeValues();
        String str = null;
        if (this.mProduct == null) {
            return null;
        }
        if (!this.mShowModal) {
            addToCart(this.mProduct.getCommerceDefaultVariationId());
            return null;
        }
        this.mContainer = layoutInflater.inflate(R.layout.size_color_selector_dialog_fragment, viewGroup, false);
        this.mXButton = this.mContainer.findViewById(R.id.size_color_selector_dialog_fragment_x_button);
        this.mXButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SizeColorSelectorDialogFragment.this.cancel();
            }
        });
        this.mProductImage = (NetworkImageView) this.mContainer.findViewById(R.id.size_color_selector_dialog_fragment_product_image);
        if (this.mShowProductImage) {
            this.mProductImage.setVisibility(0);
            this.mProductImage.setImage(this.mProduct.getImage());
        }
        ThemedTextView themedTextView = (ThemedTextView) this.mContainer.findViewById(R.id.size_color_selector_dialog_fragment_group_buy_create);
        if (!(this.mSource != Source.GROUP_BUY_CREATE || this.mProduct.getGroupBuyPrice() == null || this.mProduct.getGroupBuyAddToCartModalText() == null)) {
            themedTextView.setVisibility(0);
            themedTextView.setText(this.mProduct.getGroupBuyAddToCartModalText());
        }
        this.mSizeSwatchContainer = (ConstraintLayout) this.mContainer.findViewById(R.id.size_color_selector_dialog_fragment_size_swatch_container);
        if (this.mProduct.getVariationSizes().size() > 0) {
            this.mSizeSelectedTextView = (ThemedTextView) this.mContainer.findViewById(R.id.size_color_selector_dialog_fragment_size_swatch_choice);
            this.mSizeSwatchRecyclerView = (RecyclerView) this.mContainer.findViewById(R.id.size_color_selector_dialog_fragment_size_swatch_list);
            SizeSwatchAdapter sizeSwatchAdapter = new SizeSwatchAdapter(getContext(), getSizeStates(), this.mSizeSwatchRecyclerView, this.mSizeSelectedTextView, this, false);
            this.mSizeAdapter = sizeSwatchAdapter;
            this.mSizeSwatchRecyclerView.setAdapter(this.mSizeAdapter);
            FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(getContext());
            flexboxLayoutManager.setJustifyContent(0);
            this.mSizeSwatchRecyclerView.setLayoutManager(flexboxLayoutManager);
            this.mSizeSwatchRecyclerView.setOverScrollMode(2);
            this.mSizeSwatchContainer.setVisibility(0);
            int firstAvailableIndex = this.mSizeAdapter.getFirstAvailableIndex();
            if (firstAvailableIndex != -1) {
                str = this.mSizeAdapter.getItem(firstAvailableIndex).getName();
                this.mSizeAdapter.setPreselectedSwatch(firstAvailableIndex);
            }
        }
        this.mColorSwatchContainer = (ConstraintLayout) this.mContainer.findViewById(R.id.size_color_selector_dialog_fragment_color_swatch_container);
        if (this.mProduct.getVariationColors().size() > 0) {
            this.mColorSelectedTextView = (ThemedTextView) this.mContainer.findViewById(R.id.size_color_selector_dialog_fragment_color_swatch_choice);
            this.mColorSwatchRecyclerView = (RecyclerView) this.mContainer.findViewById(R.id.size_color_selector_dialog_fragment_color_swatch_list);
            ColorSwatchAdapter colorSwatchAdapter = new ColorSwatchAdapter(getContext(), getColorStates(str, ExperimentDataCenter.getInstance().shouldShowSizeColorSelectorWithTextSwatchesOnly()), this.mColorSwatchRecyclerView, this.mColorSelectedTextView, this, false);
            this.mColorAdapter = colorSwatchAdapter;
            this.mColorSwatchRecyclerView.setAdapter(this.mColorAdapter);
            FlexboxLayoutManager flexboxLayoutManager2 = new FlexboxLayoutManager(getContext());
            flexboxLayoutManager2.setJustifyContent(0);
            this.mColorSwatchRecyclerView.setLayoutManager(flexboxLayoutManager2);
            this.mColorSwatchRecyclerView.setOverScrollMode(2);
            this.mColorSwatchContainer.setVisibility(0);
        }
        this.mYourPriceTextView = (ThemedTextView) this.mContainer.findViewById(R.id.size_color_selector_dialog_fragment_your_price);
        this.mListPriceTextView = (ThemedTextView) this.mContainer.findViewById(R.id.size_color_selector_dialog_fragment_list_price);
        if (ExperimentDataCenter.getInstance().shouldShowCrossedOutPriceToLeftInProductDetail()) {
            this.mYourPriceTextView.setVisibility(8);
            this.mListPriceTextView.setVisibility(8);
            ThemedTextView themedTextView2 = (ThemedTextView) this.mContainer.findViewById(R.id.size_color_selector_dialog_fragment_your_price_experiment_swapped);
            ThemedTextView themedTextView3 = (ThemedTextView) this.mContainer.findViewById(R.id.size_color_selector_dialog_fragment_list_price_experiment_swapped);
            themedTextView2.setVisibility(0);
            themedTextView3.setVisibility(0);
            this.mYourPriceTextView = themedTextView2;
            this.mListPriceTextView = themedTextView3;
        }
        this.mAddToCartButton = (ThemedTextView) this.mContainer.findViewById(R.id.size_color_selector_dialog_fragment_add_to_cart_button);
        this.mAddToCartButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SizeColorSelectorDialogFragment.this.handleDone();
            }
        });
        if (this.mIsFreeGift) {
            this.mYourPriceTextView.setPaintFlags(this.mListPriceTextView.getPaintFlags() | 16);
            this.mAddToCartButton.setText(getString(R.string.claim));
        } else if (this.mIsDailyGiveaway && !ExperimentDataCenter.getInstance().shouldShowDailyRaffle()) {
            this.mAddToCartButton.setBackground(getResources().getDrawable(R.drawable.main_button_selector));
            this.mYourPriceTextView.setTextColor(getResources().getColor(R.color.main_primary));
            this.mListPriceTextView.setPaintFlags(this.mListPriceTextView.getPaintFlags() | 16);
            this.mAddToCartButton.setText(getString(R.string.claim_for_free));
        } else if (this.mSource == Source.POINTS_REDEMPTION) {
            this.mAddToCartButton.setBackground(getResources().getDrawable(R.drawable.main_button_selector));
            this.mYourPriceTextView.setTextColor(getResources().getColor(R.color.main_primary));
            this.mListPriceTextView.setPaintFlags(this.mListPriceTextView.getPaintFlags() | 16);
            this.mAddToCartButton.setText(R.string.redeem);
        } else {
            this.mListPriceTextView.setPaintFlags(this.mListPriceTextView.getPaintFlags() | 16);
            this.mAddToCartButton.setText(getString(R.string.buy));
        }
        return this.mContainer;
    }

    public int getDialogWidth() {
        return Math.min(DisplayUtil.getDisplayWidth(), getResources().getDimensionPixelSize(R.dimen.bottom_dialog_fragment_max_width));
    }

    /* access modifiers changed from: protected */
    public void onPopInEnded() {
        if (hasColorOnly()) {
            this.mColorAdapter.autoSelectFirstAvailableState();
        } else {
            this.mSizeAdapter.autoSelectFirstAvailableState();
        }
    }

    public boolean onBackPressed() {
        cancel();
        return true;
    }

    public boolean isSizeInStock(String str) {
        if (hasSizeOnly()) {
            return this.mProduct.isInStock(this.mProduct.getVariationId(str, null));
        }
        return this.mProduct.isSizeInStock(str);
    }

    public boolean isColorInStock(String str) {
        if (hasColorOnly()) {
            return this.mProduct.isInStock(this.mProduct.getVariationId(null, str));
        }
        return this.mProduct.isColorInStock(str);
    }

    public boolean isColorAndSelectedSizeInStock(String str) {
        if (hasColorOnly()) {
            return this.mProduct.isInStock(this.mProduct.getVariationId(null, str));
        }
        return this.mProduct.isSatisfyingVariationInStock(getSelectedSize(), str);
    }

    public boolean isExpressShippingEligibleForColor(String str) {
        if (hasColorOnly()) {
            return this.mProduct.isExpressShippingEligible(this.mProduct.getVariationId(null, str));
        }
        return this.mProduct.isSatisfyingVariationExpressShippingEligible(getSelectedSize(), str);
    }

    public boolean isExpressShippingEligibleForSize(String str) {
        if (hasSizeOnly()) {
            return this.mProduct.isExpressShippingEligible(this.mProduct.getVariationId(str, null));
        }
        return this.mProduct.isSatisfyingVariationExpressShippingEligible(str, getSelectedColor());
    }

    public boolean hasSizeOnly() {
        return this.mProduct.getVariationColors().size() == 0 && this.mProduct.getVariationSizes().size() > 0;
    }

    public boolean hasColorOnly() {
        return this.mProduct.getVariationColors().size() > 0 && this.mProduct.getVariationSizes().size() == 0;
    }

    public String getCurrentVariation() {
        return this.mProduct.getVariationId(getSelectedSize(), getSelectedColor());
    }

    private ArrayList<ProductVariantState> getSizeStates() {
        ArrayList<ProductVariantState> arrayList = new ArrayList<>();
        Iterator it = this.mProduct.getVariationSizes().iterator();
        while (it.hasNext()) {
            arrayList.add(new ProductVariantState(this, DataType.SIZE, (String) it.next()));
        }
        return arrayList;
    }

    private ArrayList<ProductVariantState> getColorStates(String str, boolean z) {
        ProductVariantState productVariantState;
        ArrayList<ProductVariantState> arrayList = new ArrayList<>();
        ArrayList variationColors = this.mProduct.getVariationColors();
        HashMap variationColorHexes = this.mProduct.getVariationColorHexes();
        for (int i = 0; i < variationColors.size(); i++) {
            String str2 = (String) variationColors.get(i);
            if (z) {
                productVariantState = new ProductVariantState(this, DataType.COLOR, str2);
            } else {
                int forcedImage = ColorProductVariantSwatch.getForcedImage(str2);
                if (forcedImage == -1) {
                    String str3 = (String) variationColorHexes.get(str2);
                    if (!TextUtils.isEmpty(str3)) {
                        ArrayList arrayList2 = new ArrayList();
                        try {
                            for (String parseColor : str3.split(",")) {
                                arrayList2.add(Integer.valueOf(Color.parseColor(parseColor)));
                            }
                            productVariantState = new ProductVariantColorState(this, str2, (List<Integer>) arrayList2);
                        } catch (IllegalArgumentException unused) {
                            productVariantState = new ProductVariantState(this, DataType.COLOR, str2);
                        }
                    } else {
                        productVariantState = new ProductVariantState(this, DataType.COLOR, str2);
                    }
                } else {
                    productVariantState = new ProductVariantColorState(this, str2, ColorProductVariantSwatch.FORCED_IMAGE_RES_IDS[forcedImage]);
                }
            }
            productVariantState.setPreSelectedSizeSwatch(str);
            arrayList.add(productVariantState);
        }
        return arrayList;
    }

    public WishLocalizedCurrencyValue getPrice(String str, String str2) {
        String variationId = this.mProduct.getVariationId(str2, str);
        if (variationId == null) {
            return this.mProduct.getCommerceValue();
        }
        if (this.mSource == Source.GROUP_BUY_JOIN) {
            return this.mProduct.getVariationGroupPrice(variationId);
        }
        return this.mProduct.getVariationPrice(variationId);
    }

    public int getValueInPoints(String str, String str2) {
        String variationId = this.mProduct.getVariationId(str2, str);
        if (variationId == null || this.mSource != Source.POINTS_REDEMPTION) {
            return -1;
        }
        return this.mProduct.getValueInPointsForVariation(variationId);
    }

    public WishProduct getProduct() {
        return this.mProduct;
    }

    public String getSelectedColor() {
        if (this.mColorAdapter != null) {
            return this.mColorAdapter.getSelectedSwatch();
        }
        return null;
    }

    public String getSelectedSize() {
        if (this.mSizeAdapter != null) {
            return this.mSizeAdapter.getSelectedSwatch();
        }
        return null;
    }

    public void setSelections() {
        this.mSelectedVariantId = this.mProduct.getVariationId(getSelectedSize(), getSelectedColor());
        WishLocalizedCurrencyValue value = this.mProduct.getValue();
        WishLocalizedCurrencyValue price = getPrice(getSelectedColor(), getSelectedSize());
        if (value != null && price != null) {
            if (this.mIsFreeGift) {
                if (price.getValue() > 0.0d) {
                    this.mYourPriceTextView.setText(value.toFormattedString());
                } else {
                    this.mYourPriceTextView.setText(getString(R.string.free));
                }
            } else if (this.mSource != Source.POINTS_REDEMPTION || this.mProduct.getValueInPointsForVariation(this.mSelectedVariantId) <= 0) {
                if (price.getValue() > 0.0d) {
                    this.mYourPriceTextView.setText(price.toFormattedString());
                } else {
                    this.mYourPriceTextView.setText(getString(R.string.free));
                }
                if (value.getValue() <= price.getValue()) {
                    this.mListPriceTextView.setText("");
                } else if (value.getValue() > 0.0d) {
                    this.mListPriceTextView.setText(value.toFormattedString());
                } else {
                    this.mListPriceTextView.setText(getString(R.string.free));
                }
            } else {
                this.mYourPriceTextView.setText(getString(R.string.pts_amount, Integer.valueOf(this.mProduct.getValueInPointsForVariation(this.mSelectedVariantId))));
                this.mListPriceTextView.setText(value.getValue() > price.getValue() ? value.toFormattedString() : "");
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void refreshEnabledOptions() {
        if (this.mColorAdapter != null && this.mColorSwatchRecyclerView != null) {
            this.mColorAdapter.refreshEnabledOptions(this.mSizeAdapter.getSelectedSwatch());
        }
    }

    /* access modifiers changed from: private */
    public void handleDone() {
        addToCart(this.mSelectedVariantId);
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

    public void cancel() {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_ADD_TO_CART_MODAL_CANCEL);
        super.cancel();
    }

    public void performAddToCart(WishProduct wishProduct, String str, String str2, String str3) {
        final WishProduct wishProduct2 = wishProduct;
        final String str4 = str;
        final String str5 = str2;
        final String str6 = str3;
        AnonymousClass3 r0 = new ServiceTask<BaseActivity, ServiceFragment>() {
            public void performTask(BaseActivity baseActivity, ServiceFragment serviceFragment) {
                serviceFragment.addItemToCart(wishProduct2, str4, str5, str6, wishProduct2.getValue());
            }
        };
        withServiceFragment(r0);
    }
}
