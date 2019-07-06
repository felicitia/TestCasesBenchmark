package com.etsy.android.ui.cardview.viewholders;

import android.content.res.Resources;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.logger.i;
import com.etsy.android.lib.models.ListingImage;
import com.etsy.android.lib.models.Variation;
import com.etsy.android.lib.models.apiv3.cart.SavedCart;
import com.etsy.android.lib.models.apiv3.cart.SavedCartListing;
import com.etsy.android.lib.util.l;
import com.etsy.android.ui.cardview.clickhandlers.SavedCartClickHandler;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.ListingFullImageView;
import com.etsy.android.vespa.viewholders.BaseViewHolder;
import java.util.List;

public class SavedCartListingCardViewHolder2 extends BaseViewHolder<SavedCart> {
    private View cartLoadingView = findViewById(R.id.cart_view_loading);
    Button mBtnMoveToCart;
    Button mBtnMoveToFavorites;
    /* access modifiers changed from: private */
    public SavedCartClickHandler mClickListener;
    TextView mFromShopName;
    private c mImageBatch;
    ListingFullImageView mListingImage;
    ViewGroup mListingItem;
    TextView mListingPrice;
    TextView mListingTitle;
    View mPlusShipping;
    TextView mQuantity;
    View mRemoveButton = findViewById(R.id.btn_remove);
    View mUnavailableMessageBubble;
    TextView mVariation1Name;
    TextView mVariation2Name;
    View mVariationsAndQuantity;

    public SavedCartListingCardViewHolder2(ViewGroup viewGroup, SavedCartClickHandler savedCartClickHandler, c cVar) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_card_view_saved_cart_listing_2, viewGroup, false));
        this.mClickListener = savedCartClickHandler;
        this.mImageBatch = cVar;
        boolean c = l.c(getRootView());
        this.mBtnMoveToCart = (Button) findViewById(c ? R.id.btn_move_to_cart_tablet : R.id.btn_move_to_cart_phone);
        this.mBtnMoveToFavorites = (Button) findViewById(R.id.btn_move_to_favorites);
        this.mListingItem = (ViewGroup) findViewById(R.id.cartlisting_title_and_variations);
        this.mListingTitle = (TextView) findViewById(R.id.text_cartlisting_title);
        this.mFromShopName = (TextView) findViewById(R.id.text_from_shop_name);
        this.mListingPrice = (TextView) findViewById(R.id.text_individual_price);
        this.mPlusShipping = findViewById(R.id.text_plus_shipping);
        this.mListingImage = (ListingFullImageView) findViewById(R.id.image_cartlisting);
        this.mVariationsAndQuantity = findViewById(R.id.variations_and_quantity);
        this.mVariation1Name = (TextView) findViewById(R.id.variation1_name);
        this.mVariation2Name = (TextView) findViewById(R.id.variation2_name);
        this.mQuantity = (TextView) findViewById(R.id.cartlisting_quantity);
        this.mUnavailableMessageBubble = findViewById(R.id.unavailable_msg_bubble);
    }

    public void bind(final SavedCart savedCart) {
        final SavedCartListing cartListing = savedCart.getCartListing();
        if (cartListing != null) {
            int i = 8;
            if (savedCart.getViewState().isLoading()) {
                this.cartLoadingView.setVisibility(0);
                this.cartLoadingView.setBackgroundColor(this.itemView.getResources().getColor(R.color.sk_bg_gray));
            } else {
                this.cartLoadingView.setVisibility(8);
            }
            boolean isAvailable = savedCart.isAvailable();
            this.mUnavailableMessageBubble.setVisibility(isAvailable ? 8 : 0);
            bindListing(cartListing, savedCart.getShopCard().getShopName(), new TrackingOnClickListener(new i[]{savedCart, cartListing}) {
                public void onViewClick(View view) {
                    if (SavedCartListingCardViewHolder2.this.mClickListener != null) {
                        SavedCartListingCardViewHolder2.this.mClickListener.a(cartListing);
                    }
                }
            });
            Button button = this.mBtnMoveToCart;
            if (isAvailable) {
                i = 0;
            }
            button.setVisibility(i);
            this.mBtnMoveToCart.setOnClickListener(new TrackingOnClickListener(new i[]{savedCart, cartListing}) {
                public void onViewClick(View view) {
                    if (SavedCartListingCardViewHolder2.this.mClickListener != null) {
                        SavedCartListingCardViewHolder2.this.mClickListener.c(savedCart, SavedCartListingCardViewHolder2.this.getAdapterPosition());
                    }
                }
            });
            this.mRemoveButton.setOnClickListener(new TrackingOnClickListener(new i[]{savedCart, cartListing}) {
                public void onViewClick(View view) {
                    if (SavedCartListingCardViewHolder2.this.mClickListener != null) {
                        SavedCartListingCardViewHolder2.this.mClickListener.d(savedCart, SavedCartListingCardViewHolder2.this.getAdapterPosition());
                    }
                }
            });
            this.mBtnMoveToFavorites.setOnClickListener(new TrackingOnClickListener(new i[]{savedCart, cartListing}) {
                public void onViewClick(View view) {
                    if (SavedCartListingCardViewHolder2.this.mClickListener != null) {
                        SavedCartListingCardViewHolder2.this.mClickListener.b(savedCart, SavedCartListingCardViewHolder2.this.getAdapterPosition());
                    }
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void bindListing(SavedCartListing savedCartListing, String str, TrackingOnClickListener trackingOnClickListener) {
        this.mListingTitle.setText(savedCartListing.getTitle());
        this.mFromShopName.setText(getFromShopFormattedText(str, this.mFromShopName.getResources()));
        this.mListingItem.setOnClickListener(trackingOnClickListener);
        this.mListingPrice.setText(savedCartListing.getItemPriceString());
        this.mPlusShipping.setVisibility(!savedCartListing.isDigital() && !savedCartListing.isGiftCard() ? 0 : 8);
        ListingImage listingImage = (ListingImage) savedCartListing.getListingImage();
        if (listingImage != null) {
            this.mListingImage.setClickable(true);
            this.mListingImage.setVisibility(0);
            this.mListingImage.setOnClickListener(trackingOnClickListener);
            this.mListingImage.setImageInfo(listingImage, this.mImageBatch);
        }
        this.mVariationsAndQuantity.setVisibility(8);
        this.mVariation1Name.setVisibility(8);
        this.mVariation2Name.setVisibility(8);
        this.mQuantity.setVisibility(8);
        List selectedVariations = savedCartListing.getSelectedVariations();
        if (savedCartListing.getPurchaseQuantity() > 1 || (selectedVariations != null && selectedVariations.size() > 0)) {
            this.mVariationsAndQuantity.setVisibility(0);
            if (savedCartListing.getPurchaseQuantity() > 1) {
                this.mQuantity.setVisibility(0);
                this.mQuantity.setText(this.mQuantity.getResources().getString(R.string.quantity_sub, new Object[]{String.valueOf(savedCartListing.getPurchaseQuantity())}));
            }
            if (selectedVariations.size() > 0) {
                this.mVariation1Name.setVisibility(0);
                Variation variation = (Variation) selectedVariations.get(0);
                TextView textView = this.mVariation1Name;
                StringBuilder sb = new StringBuilder();
                sb.append(variation.getFormattedName());
                sb.append(": ");
                sb.append(variation.getFormattedValue());
                textView.setText(sb.toString());
            }
            if (selectedVariations.size() > 1) {
                this.mVariation2Name.setVisibility(0);
                Variation variation2 = (Variation) selectedVariations.get(1);
                TextView textView2 = this.mVariation2Name;
                StringBuilder sb2 = new StringBuilder();
                sb2.append(variation2.getFormattedName());
                sb2.append(": ");
                sb2.append(variation2.getFormattedValue());
                textView2.setText(sb2.toString());
            }
        }
    }

    private SpannableStringBuilder getFromShopFormattedText(String str, Resources resources) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(resources.getString(R.string.from_shop_name, new Object[]{str}));
        spannableStringBuilder.setSpan(new ForegroundColorSpan(resources.getColor(R.color.dark_grey)), spannableStringBuilder.toString().indexOf(str), spannableStringBuilder.length(), 33);
        return spannableStringBuilder;
    }
}
