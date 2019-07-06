package com.etsy.android.ui.cart.viewholders;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout.LayoutParams;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.models.BaseModelImage;
import com.etsy.android.lib.models.apiv3.cart.CartGroupItem;
import com.etsy.android.lib.models.apiv3.cart.CartListing;
import com.etsy.android.lib.models.apiv3.cart.CartVariation;
import com.etsy.android.lib.models.apiv3.cart.Promotion;
import com.etsy.android.lib.models.apiv3.vespa.ServerDrivenAction;
import com.etsy.android.ui.cart.a.b;
import com.etsy.android.ui.cart.view.PromotionView;
import com.etsy.android.uikit.EtsySpinnerArrayAdapterWithClickListener;
import com.etsy.android.uikit.EtsySpinnerArrayAdapterWithClickListener.a;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.ListingFullImageView;
import java.util.List;

public class CartListingViewHolder extends BaseCartGroupItemViewHolder {
    private final Button mBtnRemove = ((Button) findViewById(R.id.btn_remove));
    private final Button mBtnSaveForLater = ((Button) findViewById(R.id.btn_save_for_later));
    /* access modifiers changed from: private */
    public b mClickHandler;
    private TextView mDescription = ((TextView) findViewById(R.id.txt_listing_description));
    private final ListingFullImageView mImage = ((ListingFullImageView) findViewById(R.id.image_listing));
    private c mImageBatch;
    private final TextView mItemPrice = ((TextView) findViewById(R.id.txt_item_price));
    private final TextView mPromotionPrice = ((TextView) findViewById(R.id.txt_price));
    private final PromotionView mPromotionView = ((PromotionView) findViewById(R.id.promotion_info));
    private final EtsySpinnerArrayAdapterWithClickListener<Integer> mQuantityAdapter = new EtsySpinnerArrayAdapterWithClickListener<>(this.itemView.getContext(), this.mSpinnerQuantity);
    private final Spinner mSpinnerQuantity = ((Spinner) findViewById(R.id.spinner_quantity));
    private final Drawable mSpinnerQuantityBackground = this.mSpinnerQuantity.getBackground();
    private final TextView mTitle = ((TextView) findViewById(R.id.txt_listing_title));
    private final TextView mTotalPrice = ((TextView) findViewById(R.id.txt_total_price));
    private final TextView mTxtCustomOrder = ((TextView) findViewById(R.id.txt_custom_order));
    private final TextView mTxtDigitalDownload = ((TextView) findViewById(R.id.txt_digital_download));
    private final TextView mTxtFreeShipping;
    private final TextView mTxtNudge;
    private final TextView mTxtNudgeOnlyOne = ((TextView) findViewById(R.id.txt_nudge_only_one));
    private final TextView mTxtRegistry = ((TextView) findViewById(R.id.txt_registry));
    private final TextView mTxtTitleQuantity = ((TextView) findViewById(R.id.txt_title_quantity));
    private final TextView mUnitPrice = ((TextView) findViewById(R.id.txt_unit_price));
    private final Drawable mVariation1Background = this.mVariation1Value.getBackground();
    private View mVariation1Row = findViewById(R.id.row_variation1);
    private TextView mVariation1Title = ((TextView) findViewById(R.id.txt_variation1_title));
    private TextView mVariation1Value = ((TextView) findViewById(R.id.txt_variation1));
    private final Drawable mVariation2Background = this.mVariation2Value.getBackground();
    private View mVariation2Row = findViewById(R.id.row_variation2);
    private TextView mVariation2Title = ((TextView) findViewById(R.id.txt_variation2_title));
    private TextView mVariation2Value = ((TextView) findViewById(R.id.txt_variation2));
    private View mVariationsAndQuantity = findViewById(R.id.variations_and_quantity);

    public CartListingViewHolder(ViewGroup viewGroup, c cVar, b bVar) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_msco_cart_listing, viewGroup, false));
        this.mImageBatch = cVar;
        this.mClickHandler = bVar;
        this.mPromotionPrice.setPaintFlags(this.mPromotionPrice.getPaintFlags() | 16);
        this.mTxtFreeShipping = (TextView) findViewById(R.id.txt_free_shipping);
        this.mTxtNudge = (TextView) findViewById(R.id.txt_nudge);
    }

    public void bindCartGroupItem(final CartGroupItem cartGroupItem) {
        final CartListing cartListing = (CartListing) cartGroupItem.getData();
        this.mTitle.setText(cartListing.getTitle());
        BaseModelImage listingImage = cartListing.getListingImage();
        if (listingImage != null) {
            this.mImage.setImageInfo(listingImage, this.mImageBatch);
            this.mImage.setContentDescription(this.mTitle.getText());
        }
        bindItemDecorators(cartListing);
        bindQuantityAndVariations(cartListing, cartGroupItem.getAction(ServerDrivenAction.TYPE_RESOLVE_CUSTOMIZATION));
        if (this.mClickHandler != null) {
            this.itemView.setOnClickListener(new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    CartListingViewHolder.this.mClickHandler.a(cartListing);
                }
            });
            this.mBtnRemove.setOnClickListener(new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    CartListingViewHolder.this.mClickHandler.a(CartListingViewHolder.this.getRootView(), cartGroupItem.getAction("remove"), R.string.toast_removed);
                }
            });
            this.mBtnSaveForLater.setOnClickListener(new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    CartListingViewHolder.this.mClickHandler.a(CartListingViewHolder.this.getRootView(), cartGroupItem.getAction(ServerDrivenAction.TYPE_SAVE_CART_LISTING), R.string.toast_saved_for_later);
                }
            });
            this.mQuantityAdapter.setOnItemClickListener(new a<Integer>() {
                public void a(Integer num) {
                    ServerDrivenAction action = cartGroupItem.getAction(ServerDrivenAction.TYPE_UPDATE_QUANTITY);
                    action.addParam("quantity", num.toString());
                    CartListingViewHolder.this.mClickHandler.c(CartListingViewHolder.this.getRootView(), action);
                }

                public boolean b(@NonNull Integer num) {
                    return num.intValue() != cartListing.getPurchaseQuantity();
                }
            });
        }
        String complianceDescription = cartListing.getComplianceDescription();
        int i = 8;
        if (!TextUtils.isEmpty(complianceDescription)) {
            this.mDescription.setVisibility(0);
            this.mDescription.setText(complianceDescription);
        } else {
            this.mDescription.setVisibility(8);
        }
        Promotion promotion = cartListing.getPromotion();
        this.mPromotionView.setVisibility(promotion == null ? 8 : 0);
        this.mPromotionPrice.setVisibility(promotion == null ? 8 : 0);
        if (promotion != null) {
            AnonymousClass5 r2 = null;
            if (cartGroupItem.getAction(ServerDrivenAction.TYPE_DELETE_COUPON) != null) {
                r2 = new TrackingOnClickListener() {
                    public void onViewClick(View view) {
                        if (CartListingViewHolder.this.mClickHandler != null) {
                            ServerDrivenAction action = cartGroupItem.getAction(ServerDrivenAction.TYPE_DELETE_COUPON);
                            if (action != null) {
                                CartListingViewHolder.this.mClickHandler.c(CartListingViewHolder.this.getRootView(), action);
                            }
                        }
                    }
                };
            }
            this.mPromotionView.bind(promotion, r2);
            this.mPromotionPrice.setText(promotion.getPrice().toString());
        }
        boolean c = com.etsy.android.lib.config.a.a().d().c(com.etsy.android.lib.config.b.bC);
        TextView textView = this.mTxtFreeShipping;
        if (c && cartListing.hasFreeShipping()) {
            i = 0;
        }
        textView.setVisibility(i);
    }

    /* access modifiers changed from: protected */
    public void bindQuantityAndVariations(CartListing cartListing, final ServerDrivenAction serverDrivenAction) {
        this.mVariationsAndQuantity.setVisibility(0);
        this.mVariation1Row.setVisibility(8);
        this.mVariation2Row.setVisibility(8);
        this.mTotalPrice.setText(cartListing.getTotalPriceString());
        if (cartListing.getUnitPriceString() != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("(");
            sb.append(cartListing.getUnitPriceString());
            sb.append(")");
            this.mUnitPrice.setText(sb.toString());
            this.mUnitPrice.setVisibility(0);
        } else {
            this.mUnitPrice.setVisibility(8);
        }
        if (cartListing.getPurchaseQuantity() > 1) {
            this.mItemPrice.setVisibility(0);
            this.mItemPrice.setText(this.itemView.getResources().getString(R.string.cart_listing_item_price, new Object[]{cartListing.getItemPriceString()}));
        } else {
            this.mItemPrice.setVisibility(8);
        }
        this.mQuantityAdapter.clear();
        String displayText = cartListing.getNudge() != null ? cartListing.getNudge().getDisplayText() : "";
        this.mTxtNudgeOnlyOne.setVisibility(8);
        this.mTxtNudge.setVisibility(8);
        if (cartListing.getQuantity() <= 1 || cartListing.isSingleQuantity()) {
            this.mSpinnerQuantity.setEnabled(false);
            this.mSpinnerQuantity.setBackground(null);
            this.mQuantityAdapter.add(Integer.valueOf(1));
            if (!TextUtils.isEmpty(displayText)) {
                this.mTxtTitleQuantity.setVisibility(8);
                this.mSpinnerQuantity.setVisibility(8);
                this.mTxtNudgeOnlyOne.setText(displayText);
                this.mTxtNudgeOnlyOne.setVisibility(0);
                LayoutParams layoutParams = (LayoutParams) this.mTotalPrice.getLayoutParams();
                layoutParams.bottomToBottom = -1;
                layoutParams.baselineToBaseline = R.id.txt_nudge_only_one;
                this.mTotalPrice.setLayoutParams(layoutParams);
            }
        } else {
            this.mSpinnerQuantity.setEnabled(true);
            this.mSpinnerQuantity.setBackground(this.mSpinnerQuantityBackground);
            for (int i = 1; i <= cartListing.getQuantity(); i++) {
                this.mQuantityAdapter.add(Integer.valueOf(i));
            }
            this.mSpinnerQuantity.setSelection(cartListing.getPurchaseQuantity() - 1);
            this.mTxtTitleQuantity.setVisibility(0);
            this.mSpinnerQuantity.setVisibility(0);
            LayoutParams layoutParams2 = (LayoutParams) this.mTotalPrice.getLayoutParams();
            layoutParams2.bottomToBottom = 0;
            layoutParams2.baselineToBaseline = -1;
            this.mTotalPrice.setLayoutParams(layoutParams2);
            if (!TextUtils.isEmpty(displayText)) {
                this.mTxtNudge.setText(displayText);
                this.mTxtNudge.setVisibility(0);
            }
        }
        if (cartListing.hasVariations()) {
            List<CartVariation> variations = cartListing.getVariations();
            for (CartVariation cartVariation : variations) {
                final CartVariation cartVariation2 = (CartVariation) variations.get(0);
                this.mVariation1Row.setVisibility(0);
                this.mVariation1Title.setText(cartVariation2.getLabel());
                this.mVariation1Value.setText(cartVariation2.getDisplayValue());
                this.mVariation1Value.setOnClickListener(new TrackingOnClickListener() {
                    public void onViewClick(View view) {
                        if (CartListingViewHolder.this.mClickHandler != null && serverDrivenAction != null) {
                            serverDrivenAction.setParams(cartVariation2.getActionBodyParams());
                            CartListingViewHolder.this.mClickHandler.b(CartListingViewHolder.this.getRootView(), serverDrivenAction);
                        }
                    }
                });
                if (cartVariation2.isEnabled()) {
                    this.mVariation1Value.setEnabled(true);
                    this.mVariation1Value.setBackground(this.mVariation1Background);
                } else {
                    this.mVariation1Value.setEnabled(false);
                    this.mVariation1Value.setBackground(null);
                }
                if (variations.size() > 1) {
                    final CartVariation cartVariation3 = (CartVariation) variations.get(1);
                    this.mVariation2Row.setVisibility(0);
                    this.mVariation2Title.setText(cartVariation3.getLabel());
                    this.mVariation2Value.setText(cartVariation3.getDisplayValue());
                    this.mVariation2Value.setOnClickListener(new TrackingOnClickListener() {
                        public void onViewClick(View view) {
                            if (CartListingViewHolder.this.mClickHandler != null && serverDrivenAction != null) {
                                serverDrivenAction.setParams(cartVariation3.getActionBodyParams());
                                CartListingViewHolder.this.mClickHandler.b(CartListingViewHolder.this.getRootView(), serverDrivenAction);
                            }
                        }
                    });
                    if (cartVariation3.isEnabled()) {
                        this.mVariation2Value.setEnabled(true);
                        this.mVariation2Value.setBackground(this.mVariation2Background);
                    } else {
                        this.mVariation2Value.setEnabled(false);
                        this.mVariation2Value.setBackground(null);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void bindItemDecorators(CartListing cartListing) {
        if (cartListing.isCustomOrder()) {
            this.mTxtCustomOrder.setVisibility(0);
        } else {
            this.mTxtCustomOrder.setVisibility(8);
        }
        if (cartListing.isRegistryItem()) {
            this.mTxtRegistry.setText(cartListing.getRegistryName());
            this.mTxtRegistry.setVisibility(0);
        } else {
            this.mTxtRegistry.setVisibility(8);
        }
        if (cartListing.isDigital()) {
            this.mTxtDigitalDownload.setVisibility(0);
        } else {
            this.mTxtDigitalDownload.setVisibility(8);
        }
    }
}
