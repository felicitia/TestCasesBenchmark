package com.etsy.android.ui.core;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.etsy.android.R;
import com.etsy.android.androidpay.LocalAndroidPayData;
import com.etsy.android.androidpay.LocalAndroidPayLineItem;
import com.etsy.android.lib.core.EtsyMoney;
import com.etsy.android.lib.core.http.request.BaseHttpRequest;
import com.etsy.android.lib.core.http.request.EtsyApiV3Request;
import com.etsy.android.lib.core.http.request.a.C0065a;
import com.etsy.android.lib.core.i;
import com.etsy.android.lib.core.k;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.logger.w;
import com.etsy.android.lib.messaging.EtsyAction;
import com.etsy.android.lib.models.EmptyResult;
import com.etsy.android.lib.models.Listing;
import com.etsy.android.lib.models.ListingPromotion;
import com.etsy.android.lib.models.Manufacturer;
import com.etsy.android.lib.models.Nudge;
import com.etsy.android.lib.models.Option;
import com.etsy.android.lib.models.PaymentMethod;
import com.etsy.android.lib.models.PaymentOption;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.ResponseConstants.Includes;
import com.etsy.android.lib.models.Shop;
import com.etsy.android.lib.models.SingleListingCheckout;
import com.etsy.android.lib.models.Variation;
import com.etsy.android.lib.models.apiv3.Collection;
import com.etsy.android.lib.models.apiv3.FAQ;
import com.etsy.android.lib.models.apiv3.FAQs;
import com.etsy.android.lib.models.apiv3.ListingCollection;
import com.etsy.android.lib.models.apiv3.Offering;
import com.etsy.android.lib.models.apiv3.OfferingOption;
import com.etsy.android.lib.models.apiv3.OfferingRangeSelect;
import com.etsy.android.lib.models.apiv3.OfferingResponse;
import com.etsy.android.lib.models.apiv3.OfferingSelect;
import com.etsy.android.lib.models.apiv3.OfferingUi;
import com.etsy.android.lib.models.apiv3.TranslatedListing;
import com.etsy.android.lib.models.apiv3.cart.AndroidPayDataContract;
import com.etsy.android.lib.models.apiv3.cart.SingleListingCart;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.datatypes.TrackedEtsyId;
import com.etsy.android.lib.models.interfaces.ListingLike;
import com.etsy.android.lib.qualtrics.QualtricsController;
import com.etsy.android.lib.requests.CartsRequest;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.ListingsRequest;
import com.etsy.android.lib.util.CrashUtil;
import com.etsy.android.lib.util.NetworkUtils;
import com.etsy.android.lib.util.ac;
import com.etsy.android.lib.util.af;
import com.etsy.android.lib.util.aj;
import com.etsy.android.lib.util.l;
import com.etsy.android.lib.util.t;
import com.etsy.android.stylekit.CompoundVectorTextView;
import com.etsy.android.stylekit.EtsyButton;
import com.etsy.android.stylekit.alerts.AlertLayout;
import com.etsy.android.ui.EtsyFragment;
import com.etsy.android.ui.adapters.OfferingVariationSpinnerAdapter;
import com.etsy.android.ui.adapters.VariationsSpinnerAdapter;
import com.etsy.android.ui.cart.googlewallet.GoogleWalletHelperBase.ReadyState;
import com.etsy.android.ui.cart.view.ListingPagePromotionView;
import com.etsy.android.ui.core.listingpanel.h;
import com.etsy.android.ui.core.listingpanel.q;
import com.etsy.android.ui.view.ListingFaqView;
import com.etsy.android.ui.view.ObservableScrollView;
import com.etsy.android.ui.view.ViewTouchInterceptor;
import com.etsy.android.uikit.adapter.ListingImagesPagerAdapter;
import com.etsy.android.uikit.adapter.ListingThumbnailAdapter;
import com.etsy.android.uikit.nav.ActivityNavigator.AnimationMode;
import com.etsy.android.uikit.nav.TrackingBaseActivity;
import com.etsy.android.uikit.util.EtsyLinkify;
import com.etsy.android.uikit.util.EtsyLinkify.CustomColorUnderlineURLSpan;
import com.etsy.android.uikit.util.MachineTranslationViewState;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.util.g;
import com.etsy.android.uikit.util.j;
import com.etsy.android.uikit.view.DynamicHeightViewPager;
import com.etsy.android.uikit.view.LoadingIndicatorDrawable;
import com.etsy.android.uikit.view.MachineTranslationButton;
import com.etsy.android.uikit.view.MachineTranslationOneClickView;
import com.etsy.android.uikit.view.ZeroSpinner;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.android.gms.common.api.BooleanResult;
import com.google.android.gms.common.api.ResultCallback;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class ListingFragment extends EtsyFragment implements com.etsy.android.lib.core.b.a, com.etsy.android.lib.qualtrics.b {
    private static final String DESCRIPTION_PANEL_VISIBLE_ARG = "DescriptionPanelIsVisibile";
    private static final float DISABLED_BUTTON_ALPHA = 0.3f;
    private static final String FEEDBACK_PANEL_VISIBLE_ARG = "FeedbackPanelIsVisibile";
    public static final String FRAGMENT_TAG = "listing";
    private static final int ICON_FAVED = 2131231088;
    private static final int ICON_IN_LIST = 2131231096;
    private static final int ICON_NOT_FAVED = 2131231083;
    private static final int ICON_NOT_IN_LIST = 2131231095;
    private static final double IMAGE_HEIGHT_RATIO = 0.75d;
    private static final String LISTING_ARG = "Listing";
    private static final int OFFERINGS_DATA_REQUEST_ID = 1;
    private static final String OVERVIEW_PANEL_VISIBLE_ARG = "OverviewPanelIsVisible";
    private static final String PARAM_LISTING_VARIATION_IDS = "listing_variation_ids[]";
    private static final String PARAM_SELECTED_QUANTITY = "selected_quantity";
    private static final String SHIPPING_AND_POLICIES_PANEL_VISIBLE_ARG = "ShippingAndPoliciesPanelIsVisible";
    private static final String SHIPPING_PANEL_VISIBLE_ARG = "ShippingPanelIsVisibile";
    private static final String SHOP_POLICIES_SCROLL_LINK = "#shop-policies";
    private static final String TAG = f.a(ListingFragment.class);
    com.etsy.android.lib.util.b.a fileSupport;
    private EtsyAction mAction;
    /* access modifiers changed from: private */
    public LocalAndroidPayData mAndroidPayData;
    /* access modifiers changed from: private */
    public View mBackgroundBufferTransparentOverlay;
    private View mBtnAndroidPay;
    /* access modifiers changed from: private */
    public EtsyButton mBtnCart;
    private EtsyButton mBtnExpressCheckout;
    private EtsyButton mBtnIneligibleAddress;
    private boolean mConfigListingFaqEnabled = false;
    /* access modifiers changed from: private */
    public boolean mConfigListingInventoryManagement = false;
    private boolean mConfigPriceTransparencyMessageEnabled = false;
    private boolean mConfigScoupons = false;
    private boolean mConfigShowFullListingImage = false;
    private boolean mConfigSingleListingCheckout = false;
    private boolean mConfigSingleListingCheckoutAndroidPay = false;
    private boolean mConfigStructuredPoliciesEnabled = false;
    /* access modifiers changed from: private */
    public boolean mConfigVariationsEnabled = false;
    /* access modifiers changed from: private */
    public com.etsy.android.ui.core.listingpanel.e mDescriptionPanel;
    private boolean mDescriptionPanelIsVisible;
    private View mErrorView;
    /* access modifiers changed from: private */
    public MachineTranslationViewState mFAQTranslationState = new MachineTranslationViewState();
    private Animation mFadeInAnimation;
    /* access modifiers changed from: private */
    public com.etsy.android.ui.util.e mFavUtil;
    /* access modifiers changed from: private */
    public com.etsy.android.ui.core.listingpanel.f mFeedbackPanel;
    private boolean mFeedbackPanelIsVisible;
    /* access modifiers changed from: private */
    public com.etsy.android.ui.cart.googlewallet.a mGoogleWalletHelper;
    /* access modifiers changed from: private */
    public float mHeaderFadeAlpha = 1.0f;
    private DynamicHeightViewPager mImagePager;
    private OnPreDrawListener mImagePagerPreDrawListener;
    /* access modifiers changed from: private */
    public ListingImagesPagerAdapter mImagesAdapter;
    /* access modifiers changed from: private */
    public ImageView mImgFavorite;
    private ImageView mImgLists;
    private g mIndicatorHelper;
    private AlertLayout mIneligibleAddress;
    /* access modifiers changed from: private */
    public ViewTouchInterceptor mInterceptor;
    /* access modifiers changed from: private */
    public boolean mIsInCart = false;
    /* access modifiers changed from: private */
    public boolean mIsLoadingOfferingData;
    private boolean mIsSellerListing = false;
    /* access modifiers changed from: private */
    public Listing mListing;
    /* access modifiers changed from: private */
    public EtsyId mListingId;
    private ArrayAdapter<Integer> mListingQuantityAdapter = null;
    private ListingThumbnailAdapter mListingThumbnailAdapter;
    /* access modifiers changed from: private */
    public TextView mListingTitle;
    /* access modifiers changed from: private */
    public int mListingTitleHeight;
    private View mListingView;
    private View mLoadingView;
    private ImageView mNudgeImage;
    private View mNudgePanel;
    private TextView mNudgeText;
    /* access modifiers changed from: private */
    public OfferingResponse mOfferingData;
    private ArrayList<OfferingVariationSpinnerAdapter> mOfferingVariationAdapters;
    private com.etsy.android.uikit.adapter.ImagesPagerAdapter.b mOnImageClickListener = new com.etsy.android.uikit.adapter.ImagesPagerAdapter.b() {
        public void onImageClick(int i) {
            com.etsy.android.lib.logger.legacy.b.a().d("photo_tapped", "view_listing");
            com.etsy.android.ui.nav.e.a((Activity) ListingFragment.this.mActivity).a(AnimationMode.ZOOM_IN_OUT).a(new ArrayList<>(ListingFragment.this.mImagesAdapter.getImages()), i, l.c((Activity) ListingFragment.this.getActivity()));
        }
    };
    private com.etsy.android.ui.core.listingpanel.g mOverviewPanel;
    private boolean mOverviewPanelIsVisible;
    private final OnPageChangeListener mPageChangeListener = new OnPageChangeListener() {
        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageSelected(int i) {
        }

        public void onPageScrollStateChanged(int i) {
            w analyticsContext = ListingFragment.this.getAnalyticsContext();
            if (analyticsContext != null && i == 2) {
                analyticsContext.a("listing_image_swipe", new HashMap<AnalyticsLogAttribute, Object>() {
                    {
                        put(AnalyticsLogAttribute.LISTING_ID, ListingFragment.this.mListingId);
                    }
                });
            }
        }
    };
    /* access modifiers changed from: private */
    public final com.etsy.android.ui.core.listingpanel.d.a mPanelToggleListener = new com.etsy.android.ui.core.listingpanel.d.a() {
        public void a(com.etsy.android.ui.core.listingpanel.d dVar) {
            if (dVar == ListingFragment.this.mShippingPanel) {
                ListingFragment.this.mDescriptionPanel.e();
                ListingFragment.this.mFeedbackPanel.e();
            } else if (dVar == ListingFragment.this.mShippingAndPoliciesPanel) {
                ListingFragment.this.mDescriptionPanel.e();
                ListingFragment.this.mFeedbackPanel.e();
            } else if (dVar == ListingFragment.this.mDescriptionPanel) {
                ListingFragment.this.mShippingPanel.e();
                ListingFragment.this.mShippingAndPoliciesPanel.e();
                ListingFragment.this.mFeedbackPanel.e();
            } else if (dVar == ListingFragment.this.mFeedbackPanel) {
                ListingFragment.this.mShippingPanel.e();
                ListingFragment.this.mShippingAndPoliciesPanel.e();
                ListingFragment.this.mDescriptionPanel.e();
            }
        }
    };
    private View mPriceLoadingIndicator;
    private ListingPagePromotionView mPromotionView;
    private View mPurchaseOptionsPanel;
    /* access modifiers changed from: private */
    public Disposable mScreenShotDisposable;
    /* access modifiers changed from: private */
    public AlertLayout mScreenShotshareAlertView;
    /* access modifiers changed from: private */
    public ObservableScrollView mScrollView;
    /* access modifiers changed from: private */
    public q mShippingAndPoliciesPanel;
    private boolean mShippingAndPoliciesPanelIsVisible;
    /* access modifiers changed from: private */
    public h mShippingPanel;
    private boolean mShippingPanelIsVisible;
    @Nullable
    private com.etsy.android.ui.util.f mShopHeaderHelper;
    /* access modifiers changed from: private */
    public l mShopListingsPresenter;
    /* access modifiers changed from: private */
    public View mShopView;
    private OnGlobalLayoutListener mShopViewLayoutListener;
    private boolean mShouldShowTermsAndConditions = false;
    /* access modifiers changed from: private */
    public boolean mShowOriginalTranslation;
    private m mSimilarListingsPresenter;
    private Spinner mSpinnerQuantity;
    private ZeroSpinner mSpinnerVariation0;
    private ZeroSpinner mSpinnerVariation1;
    /* access modifiers changed from: private */
    public boolean mSupportsAndroidPay = false;
    private View mTopPanel;
    /* access modifiers changed from: private */
    public MachineTranslationButton mTranslationButton;
    private final com.etsy.android.ui.core.listingpanel.e.a mTranslationListener = new com.etsy.android.ui.core.listingpanel.e.a() {
        public void a(MachineTranslationOneClickView machineTranslationOneClickView) {
            ListingFragment.this.mListingTitle.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
                public void onGlobalLayout() {
                    if (!l.c((Activity) ListingFragment.this.getActivity())) {
                        int measuredHeight = ListingFragment.this.mListingTitle.getMeasuredHeight();
                        if (ListingFragment.this.mListingTitleHeight != measuredHeight) {
                            ListingFragment.this.mScrollView.scrollBy(0, measuredHeight - ListingFragment.this.mListingTitleHeight);
                            ListingFragment.this.mListingTitleHeight = measuredHeight;
                        }
                    }
                    j.b(ListingFragment.this.mListingTitle.getViewTreeObserver(), (OnGlobalLayoutListener) this);
                }
            });
            ListingFragment.this.mListingTitleHeight = ListingFragment.this.mListingTitle.getMeasuredHeight();
            ListingFragment.this.mShowOriginalTranslation = !ListingFragment.this.mShowOriginalTranslation;
            if (ListingFragment.this.mShowOriginalTranslation) {
                ListingFragment.this.showTranslation(ListingFragment.this.mListing.getOriginalTranslation());
                com.etsy.android.lib.logger.a.a.a("machine_translation.listings.untranslate");
                return;
            }
            ListingFragment.this.showMachineTranslation(machineTranslationOneClickView);
        }
    };
    private TextView mTxtCurrency;
    private TextView mTxtDiscountDescription;
    private TextView mTxtFreeShipping;
    private TextView mTxtListedDate;
    private TextView mTxtNumFavorites;
    /* access modifiers changed from: private */
    public TextView mTxtPrice;
    private TextView mTxtQuantityTitle;
    private TextView mTxtStrikeThroughPrice;
    private TextView mTxtVariationTitle0;
    private TextView mTxtVariationTitle1;
    private View mUnavailable;
    private TextView mUnitPriceText;
    private ArrayList<VariationsSpinnerAdapter> mVariationAdapters;
    private View mView;
    com.etsy.android.lib.f.a schedulers;
    com.etsy.android.lib.util.sharedprefs.b sharedPreferencesProvider;
    i shippingDetailsRepository;

    private class CustomLinkSpan extends CustomColorUnderlineURLSpan {
        public CustomLinkSpan(String str, Context context) {
            super(context.getResources().getColor(R.color.grey), str, true);
        }

        public void onClick(View view) {
            if (getURL().equals(ListingFragment.SHOP_POLICIES_SCROLL_LINK)) {
                com.etsy.android.ui.core.listingpanel.d access$1600 = ListingFragment.this.mShippingAndPoliciesPanel.r() ? ListingFragment.this.mShippingAndPoliciesPanel : ListingFragment.this.mShippingPanel;
                if (!access$1600.i()) {
                    access$1600.g();
                }
                if (l.c(view)) {
                    ListingFragment.this.mPanelToggleListener.a(access$1600);
                }
                ListingFragment.this.scrollToShippingPanel(access$1600);
                return;
            }
            super.onClick(view);
        }
    }

    private class a extends i<EmptyResult> {
        private Listing c;

        public a(Listing listing) {
            this.c = listing;
        }

        /* access modifiers changed from: protected */
        public void b_() {
            ListingFragment.this.mBtnCart.setBackgroundResource(R.drawable.sk_btn_primary_alt_background_selector);
            ListingFragment.this.mBtnCart.setText("");
            ListingFragment.this.mBtnCart.setIcon((Drawable) new LoadingIndicatorDrawable(ListingFragment.this.getContext()));
        }

        /* access modifiers changed from: protected */
        public EtsyRequest<EmptyResult> a() {
            CartsRequest addToCart = CartsRequest.addToCart();
            ArrayMap arrayMap = new ArrayMap();
            arrayMap.put("listing_id", this.c.getListingId().getId());
            EtsyId access$5700 = ListingFragment.this.getResolvedOfferingId();
            if (access$5700 != null) {
                arrayMap.put(SingleListingCheckoutActivity.PARAM_LISTING_INVENTORY_ID, access$5700.getId());
            } else if (this.c.hasVariations()) {
                arrayMap.put(ResponseConstants.SELECTED_VARIATIONS, this.c.getSelectedVariations());
            }
            addToCart.setViewTracker(ListingFragment.this.getAnalyticsContext());
            arrayMap.put("quantity", String.valueOf(ListingFragment.this.getSelectedQuantity()));
            addToCart.addParams(arrayMap);
            return addToCart;
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public void a(k<EmptyResult> kVar) {
            ListingFragment.this.mBtnCart.clearIcon();
            if (kVar.a()) {
                ListingFragment.this.mIsInCart = true;
                ListingFragment.this.showInCartButton();
                com.etsy.android.ui.cart.b.a((Context) ListingFragment.this.mActivity);
                return;
            }
            ListingFragment.this.mIsInCart = false;
            ListingFragment.this.showAddToCartButton();
            Toast.makeText(ListingFragment.this.mActivity, R.string.cart_error, 0).show();
        }
    }

    private class b extends i<Listing> {
        private String c = "is_favorite,in_collections,is_digital,listing_id,user_id,title,description,price,currency_code,converted_price,converted_currency,quantity,processing_min,processing_max,is_private,url,state,num_favorers,creation_tsz,language,alternate_language,transparent_price_message,gift_info";
        private final boolean d;

        public b(boolean z) {
            this.d = z;
        }

        /* access modifiers changed from: protected */
        public void b_() {
            super.b_();
            ListingFragment.this.showLoadingView();
        }

        /* access modifiers changed from: protected */
        public EtsyRequest<Listing> a() {
            ListingsRequest listing = ListingsRequest.getListing(ListingFragment.this.mListingId);
            ArrayMap arrayMap = new ArrayMap();
            arrayMap.put("with_overview", "1");
            arrayMap.put("supports_android_pay", Boolean.toString(this.d));
            if (ListingFragment.this.getConfigMap().c(com.etsy.android.lib.config.b.a.a)) {
                StringBuilder sb = new StringBuilder();
                sb.append(this.c);
                sb.append(",single_listing_checkout");
                this.c = sb.toString();
            }
            arrayMap.put("fields", this.c);
            if (ListingFragment.this.getConfigMap().c(com.etsy.android.lib.config.b.aR)) {
                arrayMap.put("sort_on_listing_id", ListingFragment.this.mListingId.getId());
            }
            if (ListingFragment.this.mConfigListingInventoryManagement) {
                CrashUtil.a().a(ListingFragment.this.getTrackingName(), "request_offering_data");
                arrayMap.put("include_offerings", "1");
            }
            String format = String.format(Locale.ROOT, "Images(listing_image_id,url_570xN,full_height,full_width,red,green,blue):%d,", new Object[]{Integer.valueOf(ListingFragment.this.getConfigMap().d(com.etsy.android.lib.config.b.c.a))});
            StringBuilder sb2 = new StringBuilder();
            sb2.append(format);
            sb2.append("ShippingInfo(destination_country_id,destination_country_name,origin_country_name,primary_cost,secondary_cost,currency_code,converted_primary_cost,converted_secondary_cost,converted_currency_code):100/Region(region_id,region_name),PaymentInfo,Manufacturers,");
            sb2.append(i());
            sb2.append("Shop(");
            sb2.append("title,login_name,user_id,shop_id,shop_name,average_rating,total_rating_count,policy_welcome,policy_payment,policy_shipping,policy_refunds,policy_additional,policy_seller_info,icon_url_fullxfull,is_using_structured_policies");
            sb2.append(")/");
            sb2.append("User(user_id,login_name)/Profile(image_url_75x75,city,login_name,first_name,last_name)/Country(name)");
            sb2.append(",Shop(shop_id)/Reviews:");
            sb2.append(3);
            sb2.append(",Shop(shop_id)/");
            sb2.append("About(story_headline)/Members(url_190x190)");
            sb2.append(",Shop(shop_id)/");
            sb2.append(Includes.STRUCTURED_POLICIES);
            sb2.append(",Shop(shop_id)/");
            sb2.append(Includes.FAQ);
            sb2.append(",Shop(shop_id)/");
            sb2.append(Includes.SELLER_DETAILS);
            sb2.append(",Shop(shop_id)/");
            sb2.append("Listings(listing_id,title,price,currency_code,converted_price,converted_currency):active:6/Images(url_170x135,red,green,blue):1");
            sb2.append(",Translations");
            arrayMap.put("includes", sb2.toString());
            listing.addParams(arrayMap);
            listing.setViewTracker(ListingFragment.this.getAnalyticsContext());
            return listing;
        }

        private String i() {
            StringBuilder sb = new StringBuilder();
            if (ListingFragment.this.mConfigVariationsEnabled) {
                sb.append("Variations(property_id),");
            }
            return sb.toString();
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public void a(k<Listing> kVar) {
            FragmentActivity activity = ListingFragment.this.getActivity();
            boolean z = activity != null && !activity.isFinishing() && ListingFragment.this.isAdded() && !ListingFragment.this.isDetached();
            if (kVar != null && kVar.a() && kVar.i()) {
                ListingFragment.this.mListing = (Listing) kVar.g().get(0);
                ListingFragment.this.mOfferingData = ListingFragment.this.mListing.getOfferings();
                ListingFragment.this.mShowOriginalTranslation = !ListingFragment.this.mListing.isMachineTranslated();
                if (ListingFragment.this.mOfferingData != null) {
                    CrashUtil.a().a(ListingFragment.this.getTrackingName(), "receive_offering_data");
                }
                if (z) {
                    ListingFragment.this.fillListing(ListingFragment.this.mListing);
                    if (activity instanceof TrackingBaseActivity) {
                        ((TrackingBaseActivity) activity).getGraphiteTimerManager().b("view_listing.time_to_listing_loaded");
                    }
                    ListingFragment.this.mActivity.supportInvalidateOptionsMenu();
                }
            } else if (z) {
                ListingFragment.this.showErrorView();
            }
        }
    }

    private interface c {
        void a(SingleListingCart singleListingCart);
    }

    private class d implements OnItemSelectedListener {
        private VariationsSpinnerAdapter b;
        private Variation c;

        public void onNothingSelected(AdapterView<?> adapterView) {
        }

        public d(VariationsSpinnerAdapter variationsSpinnerAdapter, Variation variation) {
            this.b = variationsSpinnerAdapter;
            this.c = variation;
        }

        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            String str;
            this.b.setSelection(i);
            Option item = this.b.getItem(i);
            if (!ListingFragment.this.mListing.isSoldOut() && this.c.hasPriceDiff()) {
                if (ListingFragment.this.mListing.isConverted()) {
                    str = item.getConvertedPrice().withCurrency(ListingFragment.this.mListing.getCurrencyCode()).format();
                } else {
                    str = item.getPrice().withCurrency(ListingFragment.this.mListing.getCurrencyCode()).format();
                }
                ListingFragment.this.mTxtPrice.setText(str);
            }
            this.c.setOption(item);
            if (ListingFragment.this.mBtnCart != null && ListingFragment.this.mBtnCart.getTag() != null && ((Boolean) ListingFragment.this.mBtnCart.getTag()).booleanValue()) {
                ListingFragment.this.mIsInCart = false;
                ListingFragment.this.showAddToCartButton();
            }
        }
    }

    private class e implements OnItemSelectedListener {
        private OfferingVariationSpinnerAdapter b;
        private OfferingSelect c;

        public void onNothingSelected(AdapterView<?> adapterView) {
        }

        public e(OfferingVariationSpinnerAdapter offeringVariationSpinnerAdapter, OfferingSelect offeringSelect) {
            this.b = offeringVariationSpinnerAdapter;
            this.c = offeringSelect;
        }

        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            this.b.setSelection(i);
            if (!this.c.hasSelectedOption() || !this.c.getSelectedOption().equals(this.c.getOptions().get(i))) {
                ArrayList arrayList = new ArrayList();
                for (OfferingSelect offeringSelect : ListingFragment.this.mOfferingData.getUi().getSelects()) {
                    if (offeringSelect.equals(this.c)) {
                        arrayList.add(((OfferingOption) this.c.getOptions().get(i)).getValue().getId());
                    } else if (offeringSelect.hasSelectedOption()) {
                        arrayList.add(offeringSelect.getSelectedOption().getValue().getId());
                    }
                }
                ListingFragment.this.updateOfferingData(arrayList, ListingFragment.this.getSelectedQuantity());
            }
            if (ListingFragment.this.mBtnCart != null && ListingFragment.this.mBtnCart.getTag() != null && ((Boolean) ListingFragment.this.mBtnCart.getTag()).booleanValue()) {
                ListingFragment.this.mIsInCart = false;
                ListingFragment.this.showAddToCartButton();
            }
        }
    }

    @NonNull
    public String getTrackingName() {
        return "view_listing";
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        f.c(TAG, "onCreate");
        setRetainInstance(true);
        if (bundle == null) {
            getAnalyticsContext().a(com.etsy.android.lib.config.bucketing.g.a);
            getAnalyticsContext().a(com.etsy.android.lib.config.bucketing.g.b);
        }
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mListingId = (EtsyId) arguments.getSerializable("listing_id");
            com.etsy.android.lib.toolbar.a.a(this.mListingId);
            this.mAction = EtsyAction.fromName(arguments.getString(EtsyAction.ACTION_TYPE_NAME));
            this.mListing = (Listing) arguments.getSerializable("Listing");
        }
        doConfigChecks();
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        f.c(TAG, "onCreateView");
        this.mView = layoutInflater.inflate(R.layout.fragment_listing_redesign, viewGroup, false);
        if (!l.c((Activity) getActivity())) {
            this.mOverviewPanel = new com.etsy.android.ui.core.listingpanel.g(this.mListing, this.mActivity, getAnalyticsContext());
        }
        if (l.f((Activity) getActivity())) {
            ViewStub viewStub = (ViewStub) this.mView.findViewById(R.id.overlap_header_stub);
            viewStub.setLayoutResource(R.layout.shop_overlap_header);
            viewStub.inflate();
            this.mShopHeaderHelper = new com.etsy.android.ui.util.f(getResources(), "view_listing", false);
        }
        this.mScreenShotshareAlertView = (AlertLayout) this.mView.findViewById(R.id.share_alert_layout);
        getViewReferences();
        return this.mView;
    }

    private void registerScreenshotObserver() {
        if (getConfigMap().c(com.etsy.android.lib.config.b.k.a)) {
            if (this.mScreenShotDisposable == null || !this.mScreenShotDisposable.isDisposed()) {
                this.mScreenShotDisposable = ac.a((Activity) getActivity()).b(io.reactivex.e.a.b()).a(io.reactivex.a.b.a.a()).a((Consumer<? super T>) new Consumer<String>() {
                    /* renamed from: a */
                    public void accept(String str) throws Exception {
                        com.etsy.android.lib.logger.a.a.a(String.format("%s.user_took_screenshot", new Object[]{"listing_page.screenshot"}));
                        new com.etsy.android.uikit.share.a(ListingFragment.this.mScreenShotshareAlertView, new kotlin.jvm.a.a<kotlin.h>() {
                            /* renamed from: a */
                            public kotlin.h invoke() {
                                com.etsy.android.lib.logger.a.a.a(String.format("%s.user_shared_screenshot", new Object[]{"listing_page.screenshot"}));
                                com.etsy.android.ui.nav.e.a(ListingFragment.this.getActivity()).a().a((ListingCollection) null, (ListingLike) ListingFragment.this.mListing);
                                if (ListingFragment.this.mScreenShotDisposable != null) {
                                    ListingFragment.this.mScreenShotDisposable.dispose();
                                }
                                return null;
                            }
                        }, new kotlin.jvm.a.a<kotlin.h>() {
                            /* renamed from: a */
                            public kotlin.h invoke() {
                                if (ListingFragment.this.mScreenShotDisposable != null) {
                                    ListingFragment.this.mScreenShotDisposable.dispose();
                                }
                                return null;
                            }
                        });
                    }
                }, (Consumer<? super Throwable>) new Consumer<Throwable>() {
                    /* renamed from: a */
                    public void accept(Throwable th) throws Exception {
                        if (th instanceof SecurityException) {
                            com.etsy.android.lib.logger.a.a.a(String.format("%s.read_external_storage_permission_not_granted", new Object[]{"listing_page.screenshot"}));
                            return;
                        }
                        f.f(th.getMessage());
                    }
                });
            }
        }
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mFavUtil = new com.etsy.android.ui.util.e(this.mActivity, this, getAnalyticsContext());
        this.mFadeInAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
        h hVar = new h(this.mListing, this.mActivity, getAnalyticsContext(), this.shippingDetailsRepository, this.sharedPreferencesProvider, this.schedulers);
        this.mShippingPanel = hVar;
        q qVar = new q(this.mListing, this.mActivity, getAnalyticsContext(), this.mShouldShowTermsAndConditions, this.shippingDetailsRepository, this.sharedPreferencesProvider, this.schedulers);
        this.mShippingAndPoliciesPanel = qVar;
        this.mDescriptionPanel = new com.etsy.android.ui.core.listingpanel.e(this.mListing, this.mActivity, getAnalyticsContext());
        this.mFeedbackPanel = new com.etsy.android.ui.core.listingpanel.f(this.mListing, this.mActivity, getAnalyticsContext());
        com.etsy.android.ui.homescreen.b.a().a(this.mListingId.getId());
    }

    public void onDestroyView() {
        if (this.mImagePager != null) {
            this.mImagePager.setAdapter(null);
        }
        if (this.mSpinnerQuantity != null) {
            this.mSpinnerQuantity.setAdapter(null);
        }
        if (this.mSpinnerVariation0 != null) {
            this.mSpinnerVariation0.setAdapter((SpinnerAdapter) null);
        }
        if (this.mSpinnerVariation1 != null) {
            this.mSpinnerVariation1.setAdapter((SpinnerAdapter) null);
        }
        if (this.mShippingPanel != null) {
            this.mShippingPanel.b();
        }
        if (this.mShippingAndPoliciesPanel != null) {
            this.mShippingAndPoliciesPanel.b();
        }
        if (this.mDescriptionPanel != null) {
            this.mDescriptionPanel.b();
        }
        if (this.mFeedbackPanel != null) {
            this.mFeedbackPanel.b();
        }
        if (this.mOverviewPanel != null) {
            this.mOverviewPanel.b();
            this.mOverviewPanel = null;
        }
        if (this.mSimilarListingsPresenter != null) {
            this.mSimilarListingsPresenter.a();
        }
        if (this.mShopHeaderHelper != null) {
            this.mShopHeaderHelper.b();
            this.mShopHeaderHelper = null;
        }
        this.mSimilarListingsPresenter = null;
        this.mShopListingsPresenter = null;
        this.mListingQuantityAdapter = null;
        removeViewListeners();
        if (this.mIndicatorHelper != null) {
            this.mIndicatorHelper.a();
        }
        super.onDestroyView();
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        f.c(TAG, "onActivityCreated");
        boolean z = true;
        if (this.mImagesAdapter == null) {
            ListingImagesPagerAdapter listingImagesPagerAdapter = new ListingImagesPagerAdapter(this.mActivity, getImageBatch(), getAnalyticsContext(), this.mConfigShowFullListingImage ? ScaleType.FIT_CENTER : ScaleType.CENTER_CROP, this.fileSupport);
            this.mImagesAdapter = listingImagesPagerAdapter;
            this.mImagesAdapter.setOnImageClickListener(this.mOnImageClickListener);
            this.mImagesAdapter.setWebp(getConfigMap().c(com.etsy.android.lib.config.b.cB) || getConfigMap().c(com.etsy.android.lib.config.b.cC));
        } else {
            this.mImagesAdapter.setContext(this.mActivity);
        }
        if (this.mListingThumbnailAdapter == null && l.c((Activity) getActivity())) {
            this.mListingThumbnailAdapter = new ListingThumbnailAdapter(this.mActivity, getImageBatch());
        } else if (this.mListingThumbnailAdapter != null) {
            this.mListingThumbnailAdapter.refreshActivity(this.mActivity);
        }
        if (this.mShopHeaderHelper != null) {
            this.mShopHeaderHelper.a((Activity) this.mActivity, getImageBatch(), getAnalyticsContext());
        }
        if (bundle == null) {
            this.mShippingPanelIsVisible = false;
            this.mShippingAndPoliciesPanelIsVisible = false;
            this.mDescriptionPanelIsVisible = this.mOverviewPanel == null;
            this.mFeedbackPanelIsVisible = false;
            if (this.mOverviewPanel == null) {
                z = false;
            }
            this.mOverviewPanelIsVisible = z;
        } else {
            this.mShippingPanelIsVisible = bundle.getBoolean(SHIPPING_PANEL_VISIBLE_ARG, false);
            this.mShippingAndPoliciesPanelIsVisible = bundle.getBoolean(SHIPPING_AND_POLICIES_PANEL_VISIBLE_ARG, false);
            this.mDescriptionPanelIsVisible = bundle.getBoolean(DESCRIPTION_PANEL_VISIBLE_ARG, true);
            this.mFeedbackPanelIsVisible = bundle.getBoolean(FEEDBACK_PANEL_VISIBLE_ARG, false);
            this.mOverviewPanelIsVisible = bundle.getBoolean(OVERVIEW_PANEL_VISIBLE_ARG, false);
        }
        this.mShippingPanel.a(this.mActivity);
        this.mShippingAndPoliciesPanel.a(this.mActivity);
        this.mDescriptionPanel.a(this.mActivity);
        this.mFeedbackPanel.a(this.mActivity);
        this.mFeedbackPanel.a(getImageBatch());
        if (this.mOverviewPanel != null) {
            this.mOverviewPanel.a(this.mActivity);
        }
        refreshVariationAdapters();
        setUpScrollView();
        if (this.mConfigSingleListingCheckoutAndroidPay) {
            showLoadingView();
            this.mGoogleWalletHelper = new com.etsy.android.ui.cart.googlewallet.a(getActivity());
            this.mGoogleWalletHelper.a((com.etsy.android.ui.cart.googlewallet.b) new com.etsy.android.ui.cart.googlewallet.b() {
                public void a() {
                    ListingFragment.this.initListing();
                }

                public void b() {
                    ListingFragment.this.initListing();
                }
            });
            return;
        }
        initListing();
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mGoogleWalletHelper != null && this.mGoogleWalletHelper.b()) {
            this.mGoogleWalletHelper.c();
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 601 && intent != null) {
            ListingLike listingLike = (ListingLike) intent.getSerializableExtra("listing");
            ListingCollection listingCollection = (ListingCollection) intent.getSerializableExtra(Collection.TYPE_COLLECTION);
            if (listingLike != null && listingLike.getListingId().equals(this.mListingId)) {
                if (listingCollection != null && intent.getBooleanExtra("should_show_social_invites_prompt", false)) {
                    com.etsy.android.ui.util.d.a(getActivity(), (com.etsy.android.lib.logger.b) getAnalyticsContext(), listingCollection, listingLike);
                }
                switch (i2) {
                    case 614:
                        this.mListing.setHasCollections(true);
                        setUpLists(this.mListing);
                        return;
                    case 615:
                        this.mListing.setHasCollections(false);
                        setUpLists(this.mListing);
                        return;
                    default:
                        return;
                }
            }
        } else if (com.etsy.android.ui.cart.googlewallet.a.b(i)) {
            handleAndroidPayResponse(i, i2, intent);
        }
    }

    private void handleAndroidPayResponse(final int i, final int i2, final Intent intent) {
        createSingleListingCart(new c() {
            public void a(SingleListingCart singleListingCart) {
                Intent intent = intent;
                if (intent == null) {
                    intent = new Intent();
                }
                Intent intent2 = intent;
                String valueOf = String.valueOf(ListingFragment.this.getSelectedQuantity());
                String access$700 = ListingFragment.this.getOfferingId();
                ListingFragment.this.mAndroidPayData.setCartId(Integer.parseInt(singleListingCart.getCartId().getId()));
                ListingFragment.this.mAndroidPayData.setLineItems(ListingFragment.this.buildLineItems(singleListingCart));
                ListingFragment.this.mAndroidPayData.setTotal(singleListingCart.getTotal().asEtsyMoney());
                ListingFragment.this.mAndroidPayData.setPaymentMethod(new PaymentMethod("android_pay", "Android Pay", true));
                intent2.putExtra(SingleListingCheckoutActivity.CHECKED_OUT_SINGLE_LISTING, true);
                ListingFragment.this.mGoogleWalletHelper.a(ListingFragment.this.getActivity(), ListingFragment.this.mAndroidPayData, i, i2, intent2, ListingFragment.this.mListing, valueOf, access$700);
            }
        });
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putSerializable("listing_id", this.mListingId);
        bundle.putString(EtsyAction.ACTION_TYPE_NAME, this.mAction.getName());
        bundle.putBoolean("type", this.mIsSellerListing);
        if (this.mShippingPanel.a()) {
            bundle.putBoolean(SHIPPING_PANEL_VISIBLE_ARG, this.mShippingPanel.i());
        }
        if (this.mShippingAndPoliciesPanel.a()) {
            bundle.putBoolean(SHIPPING_AND_POLICIES_PANEL_VISIBLE_ARG, this.mShippingAndPoliciesPanel.i());
        }
        if (this.mFeedbackPanel.a()) {
            bundle.putBoolean(FEEDBACK_PANEL_VISIBLE_ARG, this.mFeedbackPanel.i());
        }
        if (this.mDescriptionPanel.a()) {
            bundle.putBoolean(DESCRIPTION_PANEL_VISIBLE_ARG, this.mDescriptionPanel.i());
        }
        if (this.mOverviewPanel != null && this.mOverviewPanel.a()) {
            bundle.putBoolean(OVERVIEW_PANEL_VISIBLE_ARG, this.mOverviewPanel.i());
        }
    }

    private void getViewReferences() {
        this.mListingView = this.mView.findViewById(R.id.loaded_content);
        this.mErrorView = this.mView.findViewById(R.id.no_internet);
        this.mUnavailable = this.mView.findViewById(R.id.listing_unavailable);
        this.mBtnAndroidPay = this.mView.findViewById(R.id.button_android_pay);
        this.mBtnExpressCheckout = (EtsyButton) this.mView.findViewById(R.id.button_express_checkout);
        this.mBtnCart = (EtsyButton) this.mView.findViewById(R.id.button_cart);
        this.mIneligibleAddress = (AlertLayout) this.mView.findViewById(R.id.listing_ineligible_shipping);
        this.mBtnIneligibleAddress = (EtsyButton) this.mView.findViewById(R.id.listing_ineligible_shipping_button);
        this.mListingTitle = (TextView) this.mView.findViewById(R.id.listing_title);
        this.mImgFavorite = (ImageView) this.mView.findViewById(R.id.button_favorite_listing);
        this.mImgLists = (ImageView) this.mView.findViewById(R.id.button_lists);
        this.mTxtPrice = (TextView) this.mView.findViewById(R.id.text_price);
        this.mTxtCurrency = (TextView) this.mView.findViewById(R.id.text_currency);
        this.mTxtListedDate = (TextView) this.mView.findViewById(R.id.txt_listed_date);
        this.mTxtNumFavorites = (TextView) this.mView.findViewById(R.id.txt_num_favorites);
        this.mPriceLoadingIndicator = this.mView.findViewById(R.id.price_loading_indicator);
        this.mPurchaseOptionsPanel = this.mView.findViewById(R.id.panel_purchase_options);
        this.mTopPanel = this.mView.findViewById(R.id.listing_panel_fixed_top);
        this.mLoadingView = this.mView.findViewById(R.id.loading_view);
        this.mTxtFreeShipping = (TextView) this.mView.findViewById(R.id.text_free_shipping);
        this.mTxtDiscountDescription = (TextView) this.mView.findViewById(R.id.text_discount_description);
        this.mTxtStrikeThroughPrice = (TextView) this.mView.findViewById(R.id.text_discounted_price);
        this.mTxtStrikeThroughPrice.setPaintFlags(this.mTxtStrikeThroughPrice.getPaintFlags() | 16);
        this.mPromotionView = (ListingPagePromotionView) this.mView.findViewById(R.id.promotion_view);
        this.mImagePager = (DynamicHeightViewPager) this.mView.findViewById(R.id.viewpager_listing_images);
        this.mShopView = this.mView.findViewById(R.id.shop);
        if (this.mShopHeaderHelper != null) {
            this.mShopHeaderHelper.a(this.mView.findViewById(R.id.panel_shop_header), this.mView.findViewById(R.id.shop_header_background));
        }
        this.mErrorView.findViewById(R.id.btn_retry_internet).setOnClickListener(new TrackingOnClickListener(new TrackedEtsyId(AnalyticsLogAttribute.LISTING_ID, this.mListingId)) {
            public void onViewClick(View view) {
                ListingFragment.this.loadListing();
            }
        });
        this.mTxtQuantityTitle = (TextView) this.mView.findViewById(R.id.txt_quantity_title);
        this.mTxtVariationTitle0 = (TextView) this.mView.findViewById(R.id.txt_variation_title_0);
        this.mTxtVariationTitle1 = (TextView) this.mView.findViewById(R.id.txt_variation_title_1);
        this.mSpinnerQuantity = (Spinner) this.mView.findViewById(R.id.spinner_quantity);
        this.mSpinnerVariation0 = (ZeroSpinner) this.mView.findViewById(R.id.spinner_variations_0);
        this.mSpinnerVariation1 = (ZeroSpinner) this.mView.findViewById(R.id.spinner_variations_1);
        this.mScrollView = (ObservableScrollView) this.mView.findViewById(R.id.scrollview);
        this.mBackgroundBufferTransparentOverlay = this.mView.findViewById(R.id.blur_overlay);
        this.mNudgePanel = this.mView.findViewById(R.id.nudge_panel);
        this.mNudgeImage = (ImageView) this.mView.findViewById(R.id.nudge_image);
        this.mNudgeText = (TextView) this.mView.findViewById(R.id.nudge_text);
        this.mUnitPriceText = (TextView) this.mView.findViewById(R.id.text_unit_price);
    }

    private void doConfigChecks() {
        getConfigMap().c(com.etsy.android.lib.config.b.d.c);
        this.mConfigVariationsEnabled = getConfigMap().c(com.etsy.android.lib.config.b.a);
        this.mConfigStructuredPoliciesEnabled = getConfigMap().c(com.etsy.android.lib.config.b.l.a.c);
        this.mConfigListingFaqEnabled = getConfigMap().c(com.etsy.android.lib.config.b.bJ);
        this.mConfigListingInventoryManagement = getConfigMap().c(com.etsy.android.lib.config.b.bK);
        this.mShouldShowTermsAndConditions = getConfigMap().c(com.etsy.android.lib.config.b.l.a.d);
        this.mConfigPriceTransparencyMessageEnabled = getConfigMap().c(com.etsy.android.lib.config.b.bL);
        this.mConfigSingleListingCheckout = getConfigMap().c(com.etsy.android.lib.config.b.a.a);
        this.mConfigSingleListingCheckoutAndroidPay = getConfigMap().c(com.etsy.android.lib.config.b.e.a) && getConfigMap().c(com.etsy.android.lib.config.b.a.b);
        this.mConfigShowFullListingImage = getConfigMap().c(com.etsy.android.lib.config.b.c.b);
        this.mConfigScoupons = getConfigMap().c(com.etsy.android.lib.config.b.bB);
    }

    public void onResume() {
        super.onResume();
        setTitle();
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            EtsyAction fromAction = EtsyAction.fromAction(intent.getAction());
            if (fromAction != null) {
                this.mAction = fromAction;
            }
        }
        getActivity().supportInvalidateOptionsMenu();
        QualtricsController.a((com.etsy.android.lib.qualtrics.b) this);
        registerScreenshotObserver();
    }

    public void onPause() {
        super.onPause();
        QualtricsController.b((com.etsy.android.lib.qualtrics.b) this);
        if (this.mScreenShotDisposable != null) {
            this.mScreenShotDisposable.dispose();
        }
    }

    public void onFragmentResume() {
        super.onFragmentResume();
        setTitle();
    }

    private void setTitle() {
        if (this.mListing != null) {
            this.mActivity.setTitle(this.mListing.getTranslatedTitle(this.mShowOriginalTranslation));
        } else {
            this.mActivity.setTitle("");
        }
    }

    private void setUpScrollView() {
        this.mImagePager.setHeightRatio(0.75d);
        this.mImagePager.setAdapter(this.mImagesAdapter);
        this.mImagePager.setVisibility(8);
    }

    private void createVariationAdapters(Listing listing) {
        if (listing.hasVariations()) {
            this.mVariationAdapters = new ArrayList<>();
            for (Variation options : listing.getVariations()) {
                this.mVariationAdapters.add(new VariationsSpinnerAdapter(this.mActivity, options.getOptions()));
            }
        }
    }

    /* access modifiers changed from: private */
    public void createOfferingVariationAdapters(OfferingUi offeringUi) {
        if (!offeringUi.getSelects().isEmpty()) {
            this.mOfferingVariationAdapters = new ArrayList<>();
            for (OfferingSelect options : offeringUi.getSelects()) {
                this.mOfferingVariationAdapters.add(new OfferingVariationSpinnerAdapter(this.mActivity, options.getOptions()));
            }
            return;
        }
        this.mOfferingVariationAdapters = null;
    }

    private void refreshVariationAdapters() {
        if (this.mVariationAdapters != null) {
            Iterator it = this.mVariationAdapters.iterator();
            while (it.hasNext()) {
                ((VariationsSpinnerAdapter) it.next()).refreshActivity(this.mActivity);
            }
        } else if (this.mOfferingVariationAdapters != null) {
            Iterator it2 = this.mOfferingVariationAdapters.iterator();
            while (it2.hasNext()) {
                ((OfferingVariationSpinnerAdapter) it2.next()).refreshActivity(this.mActivity);
            }
        }
    }

    /* access modifiers changed from: private */
    public void fillListing(Listing listing) {
        if (this.mOfferingData == null) {
            createVariationAdapters(listing);
            showListingNudge(listing);
        } else if (this.mOfferingData.getUi() != null) {
            createOfferingVariationAdapters(this.mOfferingData.getUi());
            showOfferingsNudge(this.mOfferingData);
        }
        Listing originalTranslation = this.mShowOriginalTranslation ? listing.getOriginalTranslation() : listing.getMachineTranslation();
        setTitle();
        if (!this.mIsSellerListing) {
            this.mIsSellerListing = isSeller(listing);
        }
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("Listing State: ");
        sb.append(listing.getState());
        f.c(str, sb.toString());
        if (listing.isVisible() || this.mIsSellerListing) {
            this.mShippingPanel.a(this.mListing);
            this.mShippingAndPoliciesPanel.a(this.mListing);
            this.mDescriptionPanel.a(this.mListing);
            this.mDescriptionPanel.b(originalTranslation);
            this.mFeedbackPanel.a(this.mListing);
            if (this.mOverviewPanel != null) {
                this.mOverviewPanel.a(this.mListing);
            }
            setShop(listing.getShop(), listing);
            boolean supportsAndroidPay = supportsAndroidPay(listing);
            if (this.mIsSellerListing) {
                hideAddToCartAndFavoriteBtns();
            } else {
                setUpIneligibleAddressButton(listing);
                if (supportsAndroidPay) {
                    setUpAndroidPayButton(listing);
                } else {
                    setUpExpressCheckoutButton(listing);
                }
                setUpCartButton(listing);
                setUpFavorite(listing);
            }
            setUpLists(listing);
            this.mListingTitle.setText(originalTranslation.getTitle());
            if (listing.isDigitalDownload()) {
                ((CompoundVectorTextView) this.mView.findViewById(R.id.instant_download)).setVisibility(0);
            }
            if (this.mOfferingData == null) {
                setUpVariations(listing);
                setPriceAndQuantity(listing);
                this.mUnitPriceText.setVisibility(8);
            } else if (this.mOfferingData.getUi() != null) {
                setUpVariationsWithOfferings(listing, this.mOfferingData.getUi());
                setOfferingPriceAndQuantity(listing, this.mOfferingData.getUi());
                setUnitPricing(this.mOfferingData.getUi());
            } else {
                this.mUnitPriceText.setVisibility(8);
            }
            setVATAndTransparentPricingInfo(listing);
            setupPromotion(listing);
            setUpContactButton(listing);
            showHidePurchasePanel();
            setUpPanels();
            setUpManufacturers(listing);
            setUpListingStats(listing);
            setupFaqs(listing);
            setupSimilarItems(listing);
            showTopPanel(listing);
            if (com.etsy.android.lib.config.a.a().d().c(com.etsy.android.lib.config.b.bC)) {
                setupFreeShipping(listing);
            }
            if (!supportsAndroidPay) {
                showListing();
            }
            trackListingComplementary(listing);
            return;
        }
        setUpAndShowUnavailable(listing);
    }

    private void showListingNudge(Listing listing) {
        Nudge nudge = listing.getNudge();
        if (nudge != null) {
            showNudge(nudge);
        } else {
            hideNudge();
        }
    }

    /* access modifiers changed from: private */
    public void showOfferingsNudge(OfferingResponse offeringResponse) {
        Nudge nudge = offeringResponse.getNudge();
        if (nudge != null) {
            showNudge(nudge);
        } else {
            hideNudge();
        }
    }

    private void showNudge(@NonNull Nudge nudge) {
        this.mNudgePanel.setVisibility(0);
        this.mNudgeText.setText(Html.fromHtml(nudge.getDisplayText()));
        this.mNudgeImage.setImageResource(nudge.getImageResId());
    }

    private void hideNudge() {
        this.mNudgePanel.setVisibility(8);
    }

    private boolean supportsAndroidPay(@NonNull Listing listing) {
        if (this.mSupportsAndroidPay) {
            SingleListingCheckout singleListingCheckout = listing.getSingleListingCheckout();
            if (!(singleListingCheckout == null || singleListingCheckout.getPaymentOptions() == null)) {
                List paymentOptions = singleListingCheckout.getPaymentOptions();
                int size = paymentOptions.size();
                for (int i = 0; i < size; i++) {
                    if (((PaymentOption) paymentOptions.get(i)).isAndroidPay()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void trackListingComplementary(@NonNull Listing listing) {
        w analyticsContext = getAnalyticsContext();
        if (analyticsContext != null) {
            analyticsContext.a("view_listing_complementary", listing.getTrackingParameters());
        }
    }

    private void setShop(Shop shop, Listing listing) {
        if (shop != null) {
            if (this.mShopHeaderHelper != null) {
                this.mShopHeaderHelper.b(shop.getUser(), shop);
            }
            populateShopCardView(shop, true, listing);
        }
    }

    private void populateShopCardView(final Shop shop, boolean z, Listing listing) {
        final Resources resources = getResources();
        final boolean z2 = z && !l.d((Activity) getActivity());
        ViewTreeObserver viewTreeObserver = this.mShopView.getViewTreeObserver();
        if (viewTreeObserver != null) {
            removeShopViewListeners();
            this.mShopViewLayoutListener = new OnGlobalLayoutListener() {
                public void onGlobalLayout() {
                    ListingFragment.this.removeShopViewListeners();
                    ListingFragment.this.mShopListingsPresenter = new l(ListingFragment.this.mActivity, ListingFragment.this.getImageBatch(), ListingFragment.this.mShopView.getMeasuredWidth() - (resources.getDimensionPixelSize(R.dimen.card_shadow_padding) * 2));
                    int integer = resources.getInteger(R.integer.listing_shop_card_item_columns);
                    ListingFragment.this.mShopListingsPresenter.a(ListingFragment.this.mShopListingsPresenter.a(ListingFragment.this.mShopView), shop, resources.getInteger(R.integer.listing_shop_card_item_rows), integer, z2);
                }
            };
            viewTreeObserver.addOnGlobalLayoutListener(this.mShopViewLayoutListener);
        }
        this.mShopView.requestLayout();
    }

    private void setupFreeShipping(Listing listing) {
        if (!listing.hasFreeShipping() || listing.getFreeShippingData().getMessage() == null) {
            this.mTxtFreeShipping.setVisibility(8);
            return;
        }
        String formattedMoney = listing.getFreeShippingData().getMessage().toString();
        this.mTxtFreeShipping.setVisibility(0);
        this.mTxtFreeShipping.setText(styleURLs(EtsyLinkify.a(getContext(), Html.fromHtml(formattedMoney), true, false, R.color.grey, null)));
        this.mTxtFreeShipping.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private Spannable styleURLs(Spanned spanned) {
        URLSpan[] uRLSpanArr;
        SpannableString spannableString = new SpannableString(spanned);
        for (URLSpan uRLSpan : (URLSpan[]) spannableString.getSpans(0, spannableString.length(), URLSpan.class)) {
            int spanStart = spannableString.getSpanStart(uRLSpan);
            int spanEnd = spannableString.getSpanEnd(uRLSpan);
            spannableString.removeSpan(uRLSpan);
            spannableString.setSpan(new CustomLinkSpan(uRLSpan.getURL(), getContext()), spanStart, spanEnd, 0);
        }
        return spannableString;
    }

    /* access modifiers changed from: private */
    public void scrollToShippingPanel(com.etsy.android.ui.core.listingpanel.d dVar) {
        LinearLayout q = dVar.q();
        q.getParent().requestChildFocus(q, q);
        this.mScrollView.post(new e(this));
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void lambda$scrollToShippingPanel$0$ListingFragment() {
        this.mScrollView.smoothScrollBy(0, 50);
    }

    private void setupPromotion(Listing listing) {
        if (this.mConfigScoupons) {
            ListingPromotion listingPromotion = listing.getListingPromotion();
            if (listingPromotion != null) {
                boolean hasMinimumCondition = listingPromotion.hasMinimumCondition();
                String disclaimer = listingPromotion.getDisclaimer();
                String description = listingPromotion.getDescription();
                if (!TextUtils.isEmpty(description)) {
                    ListingPagePromotionView listingPagePromotionView = this.mPromotionView;
                    if (!supportsAndroidPay(listing) && !hasMinimumCondition) {
                        disclaimer = null;
                    }
                    listingPagePromotionView.bind(description, disclaimer);
                    this.mPromotionView.setVisibility(0);
                } else {
                    this.mPromotionView.setVisibility(8);
                }
            }
        } else {
            this.mTxtDiscountDescription.setVisibility(8);
            this.mPromotionView.setVisibility(8);
            this.mTxtStrikeThroughPrice.setVisibility(8);
        }
    }

    /* access modifiers changed from: private */
    public void setupFaqs(final Listing listing) {
        View findViewById = this.mListingView.findViewById(R.id.panel_faq);
        LinearLayout linearLayout = (LinearLayout) this.mListingView.findViewById(R.id.faq_list);
        if (listing.getShop() == null) {
            findViewById.setVisibility(8);
            return;
        }
        FAQs fAQs = listing.getShop().getFAQs();
        if (!this.mConfigListingFaqEnabled || fAQs.isEmpty()) {
            findViewById.setVisibility(8);
        } else {
            ((TextView) this.mListingView.findViewById(R.id.txt_faq_title)).setText(getResources().getString(R.string.faq_title_listing, new Object[]{listing.getShopName()}));
            this.mTranslationButton = (MachineTranslationButton) this.mListingView.findViewById(R.id.translate);
            if (!fAQs.isTranslationEligible()) {
                this.mTranslationButton.setVisibility(8);
            } else {
                this.mTranslationButton.setVisibility(0);
                this.mTranslationButton.configureForState(this.mFAQTranslationState);
                this.mTranslationButton.setOnClickListener(new TrackingOnClickListener() {
                    public void onViewClick(View view) {
                        if (!ListingFragment.this.mFAQTranslationState.hasLoadedTranslation()) {
                            ListingFragment.this.mFAQTranslationState.setLoadingTranslation();
                        }
                        ListingFragment.this.mTranslationButton.configureForState(ListingFragment.this.mFAQTranslationState);
                        ListingFragment.this.machineTranslateFAQs(listing);
                    }
                });
            }
            linearLayout.removeAllViews();
            Iterator it = fAQs.iterator();
            while (it.hasNext()) {
                FAQ faq = (FAQ) it.next();
                ListingFaqView listingFaqView = new ListingFaqView(linearLayout.getContext());
                listingFaqView.setFaq(faq, linearLayout.getChildCount() > 0);
                linearLayout.addView(listingFaqView);
            }
            findViewById.setVisibility(0);
        }
    }

    private void setupSimilarItems(Listing listing) {
        this.mSimilarListingsPresenter = new m(this, this.mView, getImageBatch());
        this.mSimilarListingsPresenter.b(listing);
    }

    private void showTopPanel(@NonNull final Listing listing) {
        ViewTreeObserver viewTreeObserver = this.mImagePager.getViewTreeObserver();
        if (viewTreeObserver != null) {
            removeImagePagerPreDrawListener();
            this.mImagePagerPreDrawListener = new OnPreDrawListener() {
                public boolean onPreDraw() {
                    ListingFragment.this.removeImagePagerPreDrawListener();
                    ListingFragment.this.onPagerPreDraw(listing);
                    return true;
                }
            };
            viewTreeObserver.addOnPreDrawListener(this.mImagePagerPreDrawListener);
        }
        this.mImagePager.setVisibility(0);
    }

    /* access modifiers changed from: private */
    public void onPagerPreDraw(@NonNull Listing listing) {
        this.mImagesAdapter.setImageSizes(this.mImagePager.getMeasuredWidth(), this.mImagePager.getMeasuredHeight());
        fillImages(listing);
        if (l.d((Activity) getActivity()) || this.mTopPanel == null) {
            this.mInterceptor = null;
        } else {
            int measuredHeight = this.mTopPanel.getMeasuredHeight();
            this.mInterceptor = (ViewTouchInterceptor) this.mView.findViewById(R.id.touch_interceptor);
            this.mInterceptor.setInterceptView(this.mTopPanel);
            this.mInterceptor.setLayoutParams(new LayoutParams(-1, measuredHeight));
            this.mInterceptor.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    ListingFragment.this.mScrollView.smoothScrollTo(0, 0);
                }
            });
            this.mBackgroundBufferTransparentOverlay.setLayoutParams(new FrameLayout.LayoutParams(-1, measuredHeight));
            setUpHeaderFader(measuredHeight);
        }
        setPagerIndicator();
    }

    private void setUpHeaderFader(final int i) {
        this.mScrollView.setCallbacks(new com.etsy.android.ui.view.ObservableScrollView.a() {
            public void a(int i) {
                int i2 = i / 2;
                if (i == 0) {
                    ListingFragment.this.hideBackgroundBufferImage();
                    ListingFragment.this.mInterceptor.setSendingToInterceptView(true);
                    return;
                }
                if (ListingFragment.this.mInterceptor.isSendingToInterceptView()) {
                    ListingFragment.this.mInterceptor.setSendingToInterceptView(false);
                }
                float f = i >= i2 ? 1.0f : ((float) i) / ((float) i2);
                if (ListingFragment.this.mBackgroundBufferTransparentOverlay.getVisibility() == 8) {
                    ListingFragment.this.mBackgroundBufferTransparentOverlay.setVisibility(0);
                }
                if (((double) Math.abs(ListingFragment.this.mHeaderFadeAlpha - f)) > 1.0E-5d) {
                    ListingFragment.this.mHeaderFadeAlpha = f;
                    ListingFragment.this.mBackgroundBufferTransparentOverlay.setAlpha(f);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void hideBackgroundBufferImage() {
        this.mBackgroundBufferTransparentOverlay.setVisibility(8);
    }

    private void setPagerIndicator() {
        this.mIndicatorHelper = new g(this.mView);
        this.mIndicatorHelper.a(this.mImagePager, this.mPageChangeListener, this.mListingThumbnailAdapter);
    }

    private void setUpFavorite(Listing listing) {
        if (listing.canFavorite()) {
            final EtsyId listingId = listing.getListingId();
            this.mImgFavorite.setVisibility(0);
            this.mImgFavorite.setImageResource(this.mListing.isFavorite() ? R.drawable.ic_favorited_selector : R.drawable.ic_favorite_selector);
            ImageView imageView = this.mImgFavorite;
            final Listing listing2 = listing;
            AnonymousClass5 r1 = new TrackingOnClickListener(AnalyticsLogAttribute.LISTING_ID, listingId) {
                public void onViewClick(View view) {
                    if (v.a().e()) {
                        ListingFragment.this.mFavUtil.a(ListingFragment.this.mImgFavorite, R.drawable.ic_favorite_selector, R.drawable.ic_favorited_selector, ListingFragment.this.mListing.isFavorite());
                        ListingFragment.this.mFavUtil.a((ListingLike) listing2, (com.etsy.android.ui.util.e.b) null, ListingFragment.this.mListing.isFavorite());
                        return;
                    }
                    ((com.etsy.android.ui.nav.b) com.etsy.android.ui.nav.e.a((Activity) ListingFragment.this.mActivity).a((com.etsy.android.lib.logger.j) ListingFragment.this)).a(EtsyAction.FAVORITE, listingId.getId());
                }
            };
            imageView.setOnClickListener(r1);
            return;
        }
        this.mImgFavorite.setVisibility(8);
    }

    private void setUpLists(final Listing listing) {
        this.mImgLists.setImageResource(v.a().e() ? listing.hasCollections() : false ? R.drawable.ic_listing_lists_added : R.drawable.ic_listing_lists);
        this.mImgLists.setOnClickListener(new TrackingOnClickListener(new com.etsy.android.lib.logger.i[]{listing}) {
            public void onViewClick(View view) {
                if (!v.a().e()) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("listing", listing);
                    ((com.etsy.android.ui.nav.b) com.etsy.android.ui.nav.e.a((Activity) ListingFragment.this.mActivity).a((com.etsy.android.lib.logger.j) ListingFragment.this)).a(EtsyAction.MANAGE_ITEM_COLLECTIONS, bundle);
                    return;
                }
                com.etsy.android.ui.nav.e.a((Activity) ListingFragment.this.mActivity).a(601, (Fragment) ListingFragment.this).a((ListingLike) listing);
            }
        });
    }

    private boolean isSeller(Listing listing) {
        EtsyId m = v.a().m();
        return m.hasId() && m.equals(listing.getUserId());
    }

    private void setUpAndroidPayButton(@NonNull Listing listing) {
        createSingleListingCart(new f(this, listing));
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void lambda$setUpAndroidPayButton$1$ListingFragment(@NonNull Listing listing, SingleListingCart singleListingCart) {
        populateAndroidPayData(singleListingCart, listing);
        this.mGoogleWalletHelper.a(getActivity(), (AndroidPayDataContract) this.mAndroidPayData, (int) R.id.button_android_pay);
        this.mBtnAndroidPay.setVisibility(0);
        showListing();
    }

    private void populateAndroidPayData(SingleListingCart singleListingCart, @NonNull Listing listing) {
        this.mAndroidPayData = new LocalAndroidPayData(listing.getShopName());
        this.mAndroidPayData.setCartId(Integer.parseInt(singleListingCart.getCartId().getId()));
        this.mAndroidPayData.setLineItems(buildLineItems(singleListingCart));
        this.mAndroidPayData.setTotal(singleListingCart.getTotal().asEtsyMoney());
        this.mAndroidPayData.setPaymentMethod(new PaymentMethod("android_pay", "Android Pay", true));
    }

    /* access modifiers changed from: private */
    public List<LocalAndroidPayLineItem> buildLineItems(SingleListingCart singleListingCart) {
        ArrayList arrayList = new ArrayList();
        LocalAndroidPayLineItem localAndroidPayLineItem = new LocalAndroidPayLineItem();
        localAndroidPayLineItem.setQuantity(getSelectedQuantity().intValue());
        localAndroidPayLineItem.setPrice(this.mListing.getPrice());
        localAndroidPayLineItem.setCurrencyCode(singleListingCart.getItemTotal().getCurrencyCode());
        localAndroidPayLineItem.setDescription(this.mListing.getDescription());
        localAndroidPayLineItem.setRole(0);
        arrayList.add(localAndroidPayLineItem);
        if (singleListingCart.getTaxTotal() != null) {
            LocalAndroidPayLineItem localAndroidPayLineItem2 = new LocalAndroidPayLineItem();
            localAndroidPayLineItem2.setDescription(getContext().getString(R.string.tax_price_label));
            localAndroidPayLineItem2.setPrice(singleListingCart.getTaxTotal().asEtsyMoney());
            localAndroidPayLineItem2.setCurrencyCode(singleListingCart.getTaxTotal().getCurrencyCode());
            localAndroidPayLineItem2.setRole(1);
            arrayList.add(localAndroidPayLineItem2);
        }
        LocalAndroidPayLineItem localAndroidPayLineItem3 = new LocalAndroidPayLineItem();
        localAndroidPayLineItem3.setDescription(getContext().getString(R.string.shipping));
        localAndroidPayLineItem3.setPrice(singleListingCart.getShippingTotal().asEtsyMoney());
        localAndroidPayLineItem3.setCurrencyCode(singleListingCart.getShippingTotal().getCurrencyCode());
        localAndroidPayLineItem3.setRole(2);
        arrayList.add(localAndroidPayLineItem3);
        return arrayList;
    }

    private void setUpExpressCheckoutButton(Listing listing) {
        if (this.mConfigSingleListingCheckout) {
            this.mBtnExpressCheckout.setVisibility(listing.isSingleListingCheckoutAvailable() ? 0 : 8);
            this.mBtnExpressCheckout.setOnClickListener(getExpressCheckoutClickListener(listing));
        }
    }

    private void setUpCartButton(Listing listing) {
        if (listing.canAddToCart()) {
            if (this.mIsInCart) {
                showInCartButton();
            }
            this.mBtnCart.setOnClickListener(getCartButtonClick(listing));
            return;
        }
        if (listing.isSoldOut()) {
            this.mBtnCart.setText(R.string.sold_out);
        } else if (listing.isOnVacation()) {
            this.mBtnCart.setText(R.string.shop_on_vacation);
        } else if (listing.isRemoved()) {
            this.mBtnCart.setText(R.string.item_removed);
        } else if (listing.isExpired()) {
            this.mBtnCart.setText(R.string.item_expired);
        } else {
            this.mBtnCart.setText(R.string.unavailable);
        }
        this.mBtnCart.setEnabled(false);
        this.mBtnCart.setAlpha(DISABLED_BUTTON_ALPHA);
    }

    private void setUpIneligibleAddressButton(@NonNull Listing listing) {
        if (isIneligibleAddress(listing)) {
            this.mIneligibleAddress.setVisibility(0);
            this.mBtnIneligibleAddress.setOnClickListener(new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    com.etsy.android.ui.nav.e.a(ListingFragment.this.getActivity()).a().b(13);
                }
            });
        }
    }

    private boolean isIneligibleAddress(@NonNull Listing listing) {
        return listing.getSingleListingCheckout() != null && !listing.getSingleListingCheckout().isExpressCheckoutEligible() && Integer.valueOf(438).equals(listing.getSingleListingCheckout().getIneligibleErrorCode());
    }

    private OnClickListener getExpressCheckoutClickListener(final Listing listing) {
        return new TrackingOnClickListener() {
            public void onViewClick(View view) {
                w analyticsContext = ListingFragment.this.getAnalyticsContext();
                if (listing.getSingleListingCheckout() == null || !listing.getSingleListingCheckout().isInternational()) {
                    ListingFragment.this.openSingleListingCheckoutDialog(listing);
                } else {
                    ListingFragment.this.createSingleListingCart(new g(this, listing));
                }
                if (analyticsContext != null) {
                    analyticsContext.a("start_single_listing_checkout", new HashMap<AnalyticsLogAttribute, Object>() {
                        {
                            put(AnalyticsLogAttribute.LISTING_ID, listing.getListingId());
                        }
                    });
                }
            }

            /* access modifiers changed from: 0000 */
            public final /* synthetic */ void a(Listing listing, SingleListingCart singleListingCart) {
                ListingFragment.this.openInternationalSingleListingCheckoutDialog(listing, singleListingCart);
            }
        };
    }

    /* access modifiers changed from: private */
    public void createSingleListingCart(final c cVar) {
        HashMap hashMap = new HashMap();
        hashMap.put("listing_id", this.mListing.getListingId());
        hashMap.put("quantity", String.valueOf(getSelectedQuantity()));
        Offering offering = this.mOfferingData != null ? this.mOfferingData.getOffering() : null;
        if (offering != null) {
            hashMap.put(SingleListingCheckoutActivity.PARAM_LISTING_INVENTORY_ID, offering.getOfferingId());
        } else {
            hashMap.put(SingleListingCheckoutActivity.PARAM_LISTING_VARIATION, this.mListing.getSelectedVariations());
        }
        com.etsy.etsyapi.c cVar2 = new com.etsy.etsyapi.c();
        cVar2.a = com.etsy.android.lib.core.http.url.a.b.b();
        cVar2.b = SingleListingCart.class;
        cVar2.c = BaseHttpRequest.POST;
        cVar2.d = hashMap;
        v.a().j().a((com.etsy.android.lib.core.http.request.a<?, ?, ?>) (com.etsy.android.lib.core.http.request.d) ((com.etsy.android.lib.core.http.request.d.a) com.etsy.android.lib.core.http.request.d.a.a((EtsyApiV3Request) com.etsy.android.lib.core.http.request.EtsyApiV3Request.a.a(cVar2).d()).a((C0065a<ResultType>) new C0065a<com.etsy.android.lib.core.a.a<SingleListingCart>>() {
            /* access modifiers changed from: protected */
            public void a(@NonNull com.etsy.android.lib.core.a.a<SingleListingCart> aVar) {
                cVar.a((SingleListingCart) aVar.h());
            }
        }, (Fragment) this)).c());
    }

    /* access modifiers changed from: private */
    public void openSingleListingCheckoutDialog(Listing listing) {
        if (verifyListing(listing)) {
            com.etsy.android.ui.nav.e.a((Fragment) this).a().a(listing, String.valueOf(getSelectedQuantity()), this.mOfferingData != null ? this.mOfferingData.getOffering() : null);
        }
    }

    /* access modifiers changed from: private */
    public void openInternationalSingleListingCheckoutDialog(@NonNull Listing listing, @NonNull SingleListingCart singleListingCart) {
        if (verifyListing(listing)) {
            com.etsy.android.ui.nav.e.a((Fragment) this).a().a(listing, singleListingCart, String.valueOf(getSelectedQuantity()), this.mOfferingData != null ? this.mOfferingData.getOffering() : null);
        }
    }

    private OnClickListener getCartButtonClick(final Listing listing) {
        return new TrackingOnClickListener(new com.etsy.android.lib.logger.i[]{listing}) {
            public void onViewClick(View view) {
                if (view.getTag() != null && ((Boolean) view.getTag()).booleanValue()) {
                    com.etsy.android.lib.logger.legacy.b.a().d("in_cart_tapped", "view_listing");
                    ((com.etsy.android.ui.nav.b) com.etsy.android.ui.nav.e.a((Activity) ListingFragment.this.mActivity).a((com.etsy.android.lib.logger.j) ListingFragment.this)).r();
                } else if (ListingFragment.this.verifyListing(listing)) {
                    ListingFragment.this.addToCart();
                    w analyticsContext = ListingFragment.this.getAnalyticsContext();
                    if (analyticsContext != null) {
                        analyticsContext.a("add_to_cart", new HashMap<AnalyticsLogAttribute, Object>() {
                            {
                                put(AnalyticsLogAttribute.LISTING_ID, listing.getListingId().getId());
                            }
                        });
                    }
                    com.etsy.android.lib.logger.g.a(listing.getListingId());
                }
            }
        };
    }

    /* access modifiers changed from: private */
    public boolean verifyListing(Listing listing) {
        if (this.mOfferingData != null) {
            if (getResolvedOfferingId() != null) {
                return true;
            }
            StringBuilder sb = new StringBuilder();
            for (OfferingSelect offeringSelect : getUnsetVariations()) {
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append(offeringSelect.getLabel());
            }
            if (sb.length() > 0) {
                Toast.makeText(this.mActivity, String.format(getString(R.string.variation_select_option), new Object[]{sb.toString()}), 0).show();
            } else {
                Toast.makeText(this.mActivity, getString(R.string.option_select_error), 0).show();
            }
        } else if (!listing.hasVariations() || (listing.hasVariations() && listing.getSelectedVariationCount() == listing.getVariationCount())) {
            return true;
        } else {
            if (listing.hasVariations()) {
                StringBuilder sb2 = new StringBuilder();
                for (Variation variation : listing.getVariations()) {
                    if (!variation.hasOptionSet()) {
                        sb2.append(variation.getFormattedName());
                        sb2.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                    }
                }
                Toast.makeText(this.mActivity, String.format(getString(R.string.variation_select_option), new Object[]{sb2.toString()}), 0).show();
            }
        }
        return false;
    }

    private void setUpVariations(Listing listing) {
        if (!listing.isSoldOut() && listing.hasVariations()) {
            int i = 0;
            ZeroSpinner[] zeroSpinnerArr = {this.mSpinnerVariation0, this.mSpinnerVariation1};
            TextView[] textViewArr = {this.mTxtVariationTitle0, this.mTxtVariationTitle1};
            int variationCount = listing.getVariationCount();
            while (i < variationCount && i < zeroSpinnerArr.length) {
                setupVariationsSpinner(zeroSpinnerArr[i], (Variation) listing.getVariations().get(i), (VariationsSpinnerAdapter) this.mVariationAdapters.get(i), textViewArr[i]);
                i++;
            }
        }
    }

    private void setupVariationsSpinner(ZeroSpinner zeroSpinner, Variation variation, VariationsSpinnerAdapter variationsSpinnerAdapter, TextView textView) {
        zeroSpinner.setAdapter((SpinnerAdapter) variationsSpinnerAdapter);
        zeroSpinner.setPromptTextViewResource(R.layout.spinner_item_black);
        zeroSpinner.setPrompt(getString(R.string.variation_select, variation.getFormattedName()));
        zeroSpinner.setOnItemSelectedListener(new d(variationsSpinnerAdapter, variation));
        zeroSpinner.setVisibility(0);
        textView.setText(variation.getFormattedName());
        textView.setVisibility(0);
        if (variation.getOptions().size() <= 1) {
            zeroSpinner.setSelection(0);
            zeroSpinner.setEnabled(false);
        } else if (variationsSpinnerAdapter.getSelection() >= 0) {
            zeroSpinner.setSelection(variationsSpinnerAdapter.getSelection());
        }
    }

    /* access modifiers changed from: private */
    public void setUpVariationsWithOfferings(Listing listing, @NonNull OfferingUi offeringUi) {
        if (!listing.isSoldOut()) {
            List selects = offeringUi.getSelects();
            if (!selects.isEmpty()) {
                int i = 0;
                ZeroSpinner[] zeroSpinnerArr = {this.mSpinnerVariation0, this.mSpinnerVariation1};
                TextView[] textViewArr = {this.mTxtVariationTitle0, this.mTxtVariationTitle1};
                while (i < selects.size() && i < zeroSpinnerArr.length) {
                    setupVariationSpinnerWithOfferings(zeroSpinnerArr[i], (OfferingSelect) selects.get(i), (OfferingVariationSpinnerAdapter) this.mOfferingVariationAdapters.get(i), textViewArr[i]);
                    i++;
                }
            }
        }
    }

    private void setupVariationSpinnerWithOfferings(ZeroSpinner zeroSpinner, OfferingSelect offeringSelect, OfferingVariationSpinnerAdapter offeringVariationSpinnerAdapter, TextView textView) {
        zeroSpinner.setAdapter((SpinnerAdapter) offeringVariationSpinnerAdapter);
        zeroSpinner.setPromptTextViewResource(R.layout.spinner_item_black);
        zeroSpinner.setPrompt(offeringSelect.getPrompt());
        zeroSpinner.setOnItemSelectedListener(new e(offeringVariationSpinnerAdapter, offeringSelect));
        zeroSpinner.setVisibility(0);
        textView.setText(offeringSelect.getLabel());
        textView.setVisibility(0);
        if (!offeringSelect.isEnabled()) {
            offeringVariationSpinnerAdapter.setSelection(offeringSelect.getSelectedOptionIndex());
            if (offeringSelect.hasSelectedOption()) {
                zeroSpinner.setSelection(offeringSelect.getSelectedOptionIndex());
            }
            zeroSpinner.setEnabled(false);
        } else if (!this.mIsLoadingOfferingData) {
            offeringVariationSpinnerAdapter.setSelection(offeringSelect.getSelectedOptionIndex());
            if (offeringSelect.hasSelectedOption()) {
                zeroSpinner.setSelection(offeringSelect.getSelectedOptionIndex());
            }
        } else if (offeringVariationSpinnerAdapter.getSelection() >= 0) {
            zeroSpinner.setSelection(offeringVariationSpinnerAdapter.getSelection());
        }
    }

    private void setPriceAndQuantity(Listing listing) {
        String str;
        if (listing.isSoldOut()) {
            this.mView.findViewById(R.id.price_and_quantity_layout).setVisibility(8);
            return;
        }
        TextView textView = (TextView) this.mView.findViewById(R.id.text_quantity_available);
        EtsyMoney price = listing.getPrice();
        String conditionalCurrencyCodeIfNotEqualToSupplied = price.getConditionalCurrencyCodeIfNotEqualToSupplied("USD");
        if (!this.mConfigScoupons || listing.getDiscountedPrice() == null) {
            str = price.format();
        } else {
            str = listing.getDiscountedPrice().toString();
            this.mTxtStrikeThroughPrice.setVisibility(0);
            this.mTxtStrikeThroughPrice.setText(price.format());
            ListingPromotion listingPromotion = listing.getListingPromotion();
            if (listingPromotion != null) {
                String formattedMoney = listingPromotion.getDiscountDescripton().toString();
                if (!TextUtils.isEmpty(formattedMoney)) {
                    this.mTxtDiscountDescription.setVisibility(0);
                    this.mTxtDiscountDescription.setText(formattedMoney);
                }
            }
        }
        if (listing.hasVariations() && listing.hasPriceDiffVariation()) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append("+");
            str = sb.toString();
        }
        this.mTxtPrice.setText(str);
        this.mTxtCurrency.setText(conditionalCurrencyCodeIfNotEqualToSupplied);
        if (listing.isDigitalDownload()) {
            textView.setVisibility(8);
        } else if (listing.getQuantity() == 1) {
            textView.setText(R.string.only_one_available);
            textView.setVisibility(0);
        } else {
            textView.setVisibility(8);
            this.mListingQuantityAdapter = new ArrayAdapter<>(this.mActivity, R.layout.spinner_item_black, 16908308, createIntegerSequence(listing.getQuantity()));
            this.mListingQuantityAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
            this.mSpinnerQuantity.setAdapter(this.mListingQuantityAdapter);
            this.mSpinnerQuantity.setVisibility(0);
            this.mTxtQuantityTitle.setVisibility(0);
        }
    }

    private void setUnitPricing(@NonNull OfferingUi offeringUi) {
        String unitPrice = offeringUi.getUnitPrice();
        if (unitPrice == null || unitPrice.isEmpty()) {
            this.mUnitPriceText.setVisibility(8);
            return;
        }
        this.mUnitPriceText.setText(unitPrice);
        this.mUnitPriceText.setVisibility(0);
    }

    private void setVATAndTransparentPricingInfo(Listing listing) {
        TextView textView = (TextView) this.mView.findViewById(R.id.text_transparent_pricing);
        ((TextView) this.mView.findViewById(R.id.text_is_vat_inclusive)).setVisibility(listing.isVATInclusive() ? 0 : 8);
        if (!this.mConfigPriceTransparencyMessageEnabled || !listing.hasTransparentPriceMessage()) {
            textView.setVisibility(8);
            return;
        }
        textView.setText(Html.fromHtml(listing.getTransparentPriceMessage()));
        URLSpan[] urls = textView.getUrls();
        if (urls.length > 0) {
            final String url = urls[0].getURL();
            EtsyLinkify.a(textView, true, false, (OnClickListener) new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    com.etsy.android.ui.nav.e.a(ListingFragment.this.getActivity()).a().f(url);
                }
            });
        }
        textView.setVisibility(0);
    }

    /* access modifiers changed from: private */
    public void setOfferingPriceAndQuantity(Listing listing, @NonNull OfferingUi offeringUi) {
        boolean z;
        if (listing.isSoldOut()) {
            this.mView.findViewById(R.id.price_and_quantity_layout).setVisibility(8);
            return;
        }
        TextView textView = (TextView) this.mView.findViewById(R.id.text_quantity_available);
        TextView textView2 = (TextView) this.mView.findViewById(R.id.text_is_vat_inclusive);
        this.mTxtStrikeThroughPrice.setVisibility(8);
        this.mTxtDiscountDescription.setVisibility(8);
        if (!this.mConfigScoupons || offeringUi.getDiscountedPrice() == null) {
            this.mTxtPrice.setText(offeringUi.getFormattedPrice());
        } else {
            this.mTxtPrice.setText(offeringUi.getDiscountedPrice().toString());
            this.mTxtStrikeThroughPrice.setText(offeringUi.getPrice().toString());
            this.mTxtStrikeThroughPrice.setVisibility(0);
            if (offeringUi.getDiscountDescripton() != null) {
                String formattedMoney = offeringUi.getDiscountDescripton().toString();
                if (!TextUtils.isEmpty(formattedMoney)) {
                    this.mTxtDiscountDescription.setVisibility(0);
                    this.mTxtDiscountDescription.setText(formattedMoney);
                }
            }
        }
        this.mTxtCurrency.setVisibility(8);
        setUnitPricing(offeringUi);
        textView2.setVisibility(listing.isVATInclusive() ? 0 : 8);
        textView.setVisibility(8);
        if (!listing.isDigitalDownload()) {
            Integer[] numArr = {Integer.valueOf(1)};
            Integer valueOf = Integer.valueOf(1);
            OfferingRangeSelect quantity = offeringUi.getQuantity();
            if (quantity != null) {
                numArr = quantity.getIntegerSequence();
                if (quantity.hasSelectedValue()) {
                    valueOf = Integer.valueOf(quantity.getSelectedValue());
                }
                z = quantity.isEnabled();
                if (quantity.getMax() == 1) {
                    textView.setText(R.string.only_one_available);
                    textView.setVisibility(0);
                }
            } else {
                z = false;
            }
            if (offeringUi.hasVariableQuantity()) {
                this.mListingQuantityAdapter = new ArrayAdapter<>(this.mActivity, R.layout.spinner_item_black, 16908308, numArr);
                this.mListingQuantityAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
                this.mSpinnerQuantity.setAdapter(this.mListingQuantityAdapter);
                this.mSpinnerQuantity.setVisibility(0);
                this.mSpinnerQuantity.setEnabled(z);
                this.mSpinnerQuantity.setSelection(this.mListingQuantityAdapter.getPosition(valueOf));
                this.mTxtQuantityTitle.setVisibility(0);
            }
        }
    }

    /* access modifiers changed from: private */
    @NonNull
    public Integer getSelectedQuantity() {
        Integer valueOf = Integer.valueOf(1);
        return (this.mSpinnerQuantity.getVisibility() != 0 || this.mSpinnerQuantity.getSelectedItem() == null) ? valueOf : (Integer) this.mSpinnerQuantity.getSelectedItem();
    }

    /* access modifiers changed from: private */
    @Nullable
    public EtsyId getResolvedOfferingId() {
        if (this.mOfferingData == null || this.mOfferingData.getOffering() == null || !this.mOfferingData.getOffering().getOfferingId().isNumeric()) {
            return null;
        }
        return this.mOfferingData.getOffering().getOfferingId();
    }

    @NonNull
    private List<OfferingSelect> getUnsetVariations() {
        ArrayList arrayList = new ArrayList();
        if (!(this.mOfferingData == null || this.mOfferingData.getUi() == null)) {
            for (OfferingSelect offeringSelect : this.mOfferingData.getUi().getSelects()) {
                if (!offeringSelect.hasSelectedOption()) {
                    arrayList.add(offeringSelect);
                }
            }
        }
        return arrayList;
    }

    private Integer[] createIntegerSequence(int i) {
        Integer[] numArr = new Integer[i];
        int i2 = 0;
        while (i2 < i) {
            int i3 = i2 + 1;
            numArr[i2] = Integer.valueOf(i3);
            i2 = i3;
        }
        return numArr;
    }

    private void showHidePurchasePanel() {
        if (this.mSpinnerQuantity.getVisibility() != 0 && this.mSpinnerVariation0.getVisibility() != 0 && this.mSpinnerVariation1.getVisibility() != 0) {
            this.mPurchaseOptionsPanel.setVisibility(8);
        }
    }

    private void setUpContactButton(Listing listing) {
        View findViewById = this.mView.findViewById(R.id.contact_button);
        if (this.mIsSellerListing || listing.getShop() == null) {
            findViewById.setVisibility(8);
            return;
        }
        final String loginName = listing.getShop().getLoginName();
        StringBuilder sb = new StringBuilder();
        sb.append(listing.getShop().getShopName());
        sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        sb.append(listing.getTitle());
        final String sb2 = sb.toString();
        String string = getString(R.string.listing_message, listing.getUrl());
        findViewById.setVisibility(0);
        final String str = string;
        AnonymousClass13 r2 = new TrackingOnClickListener(new com.etsy.android.lib.logger.i[]{listing}) {
            public void onViewClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString(ResponseConstants.USERNAME, loginName);
                bundle.putString(ResponseConstants.SUBJECT, sb2);
                bundle.putString("message", str);
                if (v.a().e()) {
                    com.etsy.android.ui.nav.e.a((Activity) ListingFragment.this.mActivity).e(bundle);
                } else {
                    ((com.etsy.android.ui.nav.b) com.etsy.android.ui.nav.e.a((Activity) ListingFragment.this.mActivity).a((com.etsy.android.lib.logger.j) ListingFragment.this)).a(EtsyAction.CONTACT_USER, bundle);
                }
            }
        };
        findViewById.setOnClickListener(r2);
    }

    /* access modifiers changed from: private */
    public void showMachineTranslation(MachineTranslationOneClickView machineTranslationOneClickView) {
        if (this.mListing.getMachineTranslation() == null) {
            machineTranslateListing(machineTranslationOneClickView);
            com.etsy.android.lib.logger.a.a.a("machine_translation.listings.fetch");
            return;
        }
        showTranslation(this.mListing.getMachineTranslation());
        com.etsy.android.lib.logger.a.a.a("machine_translation.listings.translate");
    }

    /* access modifiers changed from: private */
    public void showTranslation(Listing listing) {
        this.mDescriptionPanel.b(listing);
        this.mDescriptionPanel.c(listing);
        this.mDescriptionPanel.s();
        setTitle();
        this.mListingTitle.setText(listing.getTitle());
    }

    private void machineTranslateListing(final MachineTranslationOneClickView machineTranslationOneClickView) {
        String d2 = t.d();
        machineTranslationOneClickView.hideErrorMessage();
        machineTranslationOneClickView.showSpinner();
        getRequestQueue().a((Object) this, ((com.etsy.android.lib.core.http.request.d.a) com.etsy.android.lib.core.http.request.d.a.a((EtsyApiV3Request) TranslatedListing.createRequestBuilder(this.mListingId, d2).d()).a((C0065a<ResultType>) new com.etsy.android.lib.core.http.request.d.b<TranslatedListing>() {
            public void a(@NonNull List<TranslatedListing> list, int i, @NonNull com.etsy.android.lib.core.a.a<TranslatedListing> aVar) {
                machineTranslationOneClickView.hideSpinner();
                TranslatedListing translatedListing = (TranslatedListing) list.get(0);
                ListingFragment.this.mListing.setMachineTranslation(translatedListing);
                ListingFragment.this.showTranslation(translatedListing);
            }

            public void a(int i, @Nullable String str, @NonNull com.etsy.android.lib.core.a.a<TranslatedListing> aVar) {
                machineTranslationOneClickView.hideSpinner();
                machineTranslationOneClickView.showErrorMessage();
                ListingFragment.this.mShowOriginalTranslation = !ListingFragment.this.mShowOriginalTranslation;
                ListingFragment.this.mDescriptionPanel.s();
            }
        }, (Fragment) this)).c());
    }

    public void machineTranslateFAQs(@NonNull final Listing listing) {
        if (listing.getShop() != null) {
            if (this.mFAQTranslationState.hasLoadedTranslation()) {
                this.mFAQTranslationState.toggleShowingOriginal();
                Iterator it = listing.getShop().getFAQs().iterator();
                while (it.hasNext()) {
                    ((FAQ) it.next()).setShowTranslatedFAQ(!this.mFAQTranslationState.isShowingOriginal());
                }
                setupFaqs(listing);
            } else {
                String d2 = t.d();
                com.etsy.android.lib.core.http.request.EtsyApiV3Request.a a2 = com.etsy.android.lib.core.http.request.EtsyApiV3Request.a.a(FAQ.class, com.etsy.android.lib.core.http.url.a.b.e.d(listing.getShop().getShopId()));
                a2.a("language", d2);
                getRequestQueue().a((Object) this, ((com.etsy.android.lib.core.http.request.d.a) com.etsy.android.lib.core.http.request.d.a.a((EtsyApiV3Request) a2.d()).a((C0065a<ResultType>) new com.etsy.android.lib.core.http.request.d.b<FAQ>() {
                    public void a(int i, @Nullable String str, @NonNull com.etsy.android.lib.core.a.a<FAQ> aVar) {
                    }

                    public void a(@NonNull List<FAQ> list, int i, @NonNull com.etsy.android.lib.core.a.a<FAQ> aVar) {
                        if (list.size() > 0 && ListingFragment.this.getActivity() != null) {
                            ListingFragment.this.mFAQTranslationState.setSuccessLoadingTranslation();
                            listing.getShop().getFAQs().updateTranslatedFAQs(list);
                            ListingFragment.this.setupFaqs(listing);
                        }
                    }
                }, (Fragment) this)).c());
            }
        }
    }

    private void setUpPanels() {
        this.mShippingPanel.a(this.mView, this.mShippingPanelIsVisible);
        this.mShippingAndPoliciesPanel.a(this.mView, this.mShippingAndPoliciesPanelIsVisible);
        if (!this.mConfigStructuredPoliciesEnabled || this.mListing.getShop() == null || !this.mListing.getShop().isUsingStructuredPolicies()) {
            this.mShippingPanel.s();
            this.mShippingPanel.a(this.mPanelToggleListener);
            this.mShippingAndPoliciesPanel.h();
        } else {
            this.mShippingAndPoliciesPanel.s();
            this.mShippingAndPoliciesPanel.a(this.mPanelToggleListener);
            this.mShippingPanel.h();
        }
        this.mFeedbackPanel.a(this.mView, this.mFeedbackPanelIsVisible);
        this.mFeedbackPanel.a(this.mPanelToggleListener);
        this.mDescriptionPanel.a(this.mView, this.mDescriptionPanelIsVisible);
        this.mDescriptionPanel.a(this.mPanelToggleListener);
        this.mDescriptionPanel.a(this.mTranslationListener);
        if (this.mOverviewPanel != null) {
            this.mOverviewPanel.a(this.mView, this.mOverviewPanelIsVisible);
            this.mOverviewPanel.a(this.mPanelToggleListener);
            return;
        }
        if (!(this.mShippingPanelIsVisible || this.mShippingAndPoliciesPanelIsVisible || this.mFeedbackPanelIsVisible || this.mDescriptionPanelIsVisible)) {
            this.mDescriptionPanel.f();
        }
    }

    private void setUpManufacturers(Listing listing) {
        if (listing.hasManufacturers()) {
            StringBuilder manufacturersAsString = getManufacturersAsString(listing.getManufacturers());
            if (manufacturersAsString != null) {
                View findViewById = this.mView.findViewById(R.id.human_scale);
                findViewById.setVisibility(0);
                TextView textView = (TextView) findViewById.findViewById(R.id.human_scale_manufacturers);
                manufacturersAsString.insert(0, getActivity().getString(R.string.listing_human_scale).trim());
                textView.setText(manufacturersAsString);
            }
        }
    }

    private StringBuilder getManufacturersAsString(List<Manufacturer> list) {
        if (list.size() <= 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            String name = ((Manufacturer) list.get(i)).getName();
            sb.append("\n");
            sb.append(name);
        }
        return sb;
    }

    private void setUpListingStats(Listing listing) {
        this.mTxtListedDate.setText(af.a(listing.getCreationDate()));
        this.mTxtNumFavorites.setText(String.valueOf(listing.getNumFavorers()));
    }

    /* access modifiers changed from: private */
    public void addToCart() {
        getRequestQueue().a((Object) this, (com.etsy.android.lib.core.g<Result>) new a<Result>(this.mListing));
    }

    /* access modifiers changed from: private */
    public void showAddToCartButton() {
        if (this.mBtnCart != null) {
            this.mBtnCart.setBackgroundResource(R.drawable.sk_btn_primary_background_selector);
            this.mBtnCart.setText(getString(R.string.add_to_cart));
            this.mBtnCart.setTextColor(-1);
            this.mBtnCart.clearIcon();
            this.mBtnCart.setTag(Boolean.FALSE);
            if (!this.mIsSellerListing) {
                this.mBtnCart.setVisibility(0);
            }
        }
    }

    /* access modifiers changed from: private */
    public void showInCartButton() {
        if (this.mBtnCart != null) {
            this.mBtnCart.setBackgroundResource(R.drawable.sk_btn_primary_alt_background_selector);
            this.mBtnCart.setText(getString(R.string.added_to_cart));
            this.mBtnCart.setTextColor(getResources().getColor(R.color.button_green));
            this.mBtnCart.setIcon(com.etsy.android.uikit.c.a((Activity) getActivity(), (int) R.drawable.ic_check_24dp));
            this.mBtnCart.setTag(Boolean.TRUE);
            if (!this.mIsSellerListing) {
                this.mBtnCart.setVisibility(0);
            }
        }
    }

    private void hideAddToCartAndFavoriteBtns() {
        this.mView.findViewById(R.id.non_seller_action_buttons).setVisibility(8);
    }

    private void setUpAndShowUnavailable(Listing listing) {
        Shop shop = listing.getShop();
        if (shop != null) {
            ((TextView) this.mUnavailable.findViewById(R.id.subtitle)).setText(getString(R.string.listing_unavailable_subtitle, shop.getShopName()));
            if (this.mShopHeaderHelper != null) {
                View findViewById = this.mUnavailable.findViewById(R.id.panel_shop_header);
                View findViewById2 = this.mUnavailable.findViewById(R.id.shop_header_background);
                if (!(findViewById == null || findViewById2 == null)) {
                    this.mShopHeaderHelper.a(findViewById, findViewById2);
                    this.mShopHeaderHelper.b(shop.getUser(), shop);
                }
            }
            this.mShopView = this.mUnavailable.findViewById(R.id.shop);
            populateShopCardView(shop, false, listing);
        }
        showUnavailableView();
    }

    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem findItem = menu.findItem(R.id.menu_share);
        if (findItem == null) {
            return;
        }
        if (this.mListing == null || !this.mListing.isVisible()) {
            findItem.setVisible(false);
        } else {
            findItem.setVisible(true);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.menu_share) {
            return super.onOptionsItemSelected(menuItem);
        }
        shareListing();
        return true;
    }

    private void shareListing() {
        com.etsy.android.lib.logger.legacy.b.a().a("share_listing", "share", "listing_id", (Object) this.mListingId.getId());
        String str = "";
        if (this.mListing.getImage() != null) {
            str = this.mListing.getImage().getUrl570xN();
        }
        com.etsy.android.ui.nav.b a2 = com.etsy.android.ui.nav.e.a(getActivity()).a();
        String string = getString(R.string.share_listing_subject);
        StringBuilder sb = new StringBuilder();
        sb.append(getString(R.string.share_listing_message));
        sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        sb.append(this.mListing.getUrl());
        a2.a(string, sb.toString(), this.mListing.getUrl(), str);
    }

    /* access modifiers changed from: private */
    public String getOfferingId() {
        Offering offering = this.mOfferingData != null ? this.mOfferingData.getOffering() : null;
        if (offering != null) {
            return offering.getOfferingId().getId();
        }
        return this.mListing.getSelectedVariations();
    }

    /* access modifiers changed from: private */
    public void initListing() {
        if (this.mListing == null) {
            loadListing();
        } else {
            fillListing(this.mListing);
        }
    }

    /* access modifiers changed from: private */
    public void loadListing() {
        if (this.mConfigSingleListingCheckoutAndroidPay) {
            final WeakReference weakReference = new WeakReference(this);
            this.mGoogleWalletHelper.a((ResultCallback<BooleanResult>) new ResultCallback<BooleanResult>() {
                /* renamed from: a */
                public void onResult(@NonNull BooleanResult booleanResult) {
                    ListingFragment listingFragment = (ListingFragment) weakReference.get();
                    if (listingFragment != null) {
                        listingFragment.mSupportsAndroidPay = com.etsy.android.ui.cart.googlewallet.a.a(booleanResult) == ReadyState.READY;
                        ListingFragment.this.getRequestQueue().a((Object) listingFragment, (com.etsy.android.lib.core.g<Result>) new b<Result>(listingFragment.mSupportsAndroidPay));
                    }
                }
            });
            return;
        }
        getRequestQueue().a((Object) this, (com.etsy.android.lib.core.g<Result>) new b<Result>(false));
    }

    /* access modifiers changed from: private */
    public void updateOfferingData(@NonNull List<String> list, @NonNull Integer num) {
        if (!NetworkUtils.a().b()) {
            if (this.mOfferingData.getUi() != null) {
                setUpVariationsWithOfferings(this.mListing, this.mOfferingData.getUi());
            }
            aj.b((Context) this.mActivity, (int) R.string.network_unavailable);
            return;
        }
        com.etsy.android.lib.core.http.request.EtsyApiV3Request.a aVar = (com.etsy.android.lib.core.http.request.EtsyApiV3Request.a) com.etsy.android.lib.core.http.request.EtsyApiV3Request.a.a(OfferingResponse.class, com.etsy.android.lib.core.http.url.a.b.C0068b.a.a(this.mListingId)).b(false).a(0);
        if (!list.isEmpty()) {
            aVar.a(PARAM_LISTING_VARIATION_IDS, list);
        }
        aVar.a(PARAM_SELECTED_QUANTITY, String.valueOf(num));
        EtsyApiV3Request etsyApiV3Request = (EtsyApiV3Request) aVar.d();
        this.mIsLoadingOfferingData = true;
        showOfferingLoadingView();
        loadDataFromNetwork(1, (BaseHttpRequest<?, ResultType, ?>) etsyApiV3Request, (com.etsy.android.lib.core.http.loader.NetworkLoader.b<ResultType>) new com.etsy.android.lib.core.http.loader.NetworkLoader.a<OfferingResponse>() {
            public void a(@NonNull List<OfferingResponse> list, int i, @NonNull com.etsy.android.lib.core.a.a<OfferingResponse> aVar) {
                ListingFragment.this.mIsLoadingOfferingData = false;
                ListingFragment.this.hideOfferingLoadingView();
                if (list.isEmpty()) {
                    a(null);
                    return;
                }
                ListingFragment.this.mOfferingData = (OfferingResponse) list.get(0);
                OfferingUi ui = ListingFragment.this.mOfferingData.getUi();
                if (ListingFragment.this.mOfferingData.getUi() != null) {
                    ListingFragment.this.createOfferingVariationAdapters(ui);
                    ListingFragment.this.setUpVariationsWithOfferings(ListingFragment.this.mListing, ui);
                    ListingFragment.this.setOfferingPriceAndQuantity(ListingFragment.this.mListing, ui);
                    ListingFragment.this.showOfferingsNudge(ListingFragment.this.mOfferingData);
                } else {
                    a(null);
                }
            }

            public void a(int i, @Nullable String str, @NonNull com.etsy.android.lib.core.a.a<OfferingResponse> aVar) {
                ListingFragment.this.mIsLoadingOfferingData = false;
                ListingFragment.this.hideOfferingLoadingView();
                a(str);
            }

            private void a(@Nullable String str) {
                if (TextUtils.isEmpty(str)) {
                    str = ListingFragment.this.getString(R.string.variation_update_error);
                }
                Toast.makeText(ListingFragment.this.mActivity, str, 0).show();
                if (ListingFragment.this.mOfferingData.getUi() != null) {
                    ListingFragment.this.setUpVariationsWithOfferings(ListingFragment.this.mListing, ListingFragment.this.mOfferingData.getUi());
                }
            }
        });
    }

    private void fillImages(@NonNull Listing listing) {
        List images = listing.getImages();
        if (images.size() > 0) {
            this.mImagesAdapter.setImages(images);
            this.mImagesAdapter.setHasVideo(listing.hasVideos());
            if (this.mListingThumbnailAdapter != null) {
                this.mListingThumbnailAdapter.clear();
                this.mListingThumbnailAdapter.addAll((java.util.Collection<? extends T>) images);
                this.mListingThumbnailAdapter.notifyDataSetChanged();
            }
        }
    }

    /* access modifiers changed from: private */
    public void showLoadingView() {
        this.mListingView.setVisibility(4);
        this.mErrorView.setVisibility(8);
        this.mUnavailable.setVisibility(8);
        this.mLoadingView.setVisibility(0);
    }

    private void showOfferingLoadingView() {
        this.mTxtPrice.setAlpha(0.6f);
        this.mPriceLoadingIndicator.setVisibility(0);
        this.mSpinnerVariation0.setEnabled(false);
        this.mSpinnerVariation1.setEnabled(false);
        this.mSpinnerQuantity.setEnabled(false);
    }

    /* access modifiers changed from: private */
    public void hideOfferingLoadingView() {
        this.mTxtPrice.setAlpha(1.0f);
        this.mPriceLoadingIndicator.setVisibility(8);
        this.mSpinnerVariation0.setEnabled(true);
        this.mSpinnerVariation1.setEnabled(true);
        this.mSpinnerQuantity.setEnabled(true);
    }

    private void showListing() {
        this.mListingView.startAnimation(this.mFadeInAnimation);
        this.mListingView.setVisibility(0);
        this.mErrorView.setVisibility(8);
        this.mUnavailable.setVisibility(8);
        this.mLoadingView.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void showErrorView() {
        this.mListingView.setVisibility(8);
        this.mErrorView.setVisibility(0);
        this.mUnavailable.setVisibility(8);
        this.mLoadingView.setVisibility(8);
    }

    private void showUnavailableView() {
        this.mListingView.setVisibility(8);
        this.mErrorView.setVisibility(8);
        this.mUnavailable.setVisibility(0);
        this.mLoadingView.setVisibility(8);
    }

    private void removeViewListeners() {
        removeShopViewListeners();
        removeImagePagerPreDrawListener();
    }

    /* access modifiers changed from: private */
    public void removeShopViewListeners() {
        if (this.mShopView != null && this.mShopViewLayoutListener != null) {
            j.b(this.mShopView.getViewTreeObserver(), this.mShopViewLayoutListener);
            this.mShopViewLayoutListener = null;
        }
    }

    /* access modifiers changed from: private */
    public void removeImagePagerPreDrawListener() {
        if (this.mImagePager != null && this.mImagePagerPreDrawListener != null) {
            j.b(this.mImagePager.getViewTreeObserver(), this.mImagePagerPreDrawListener);
            this.mImagePagerPreDrawListener = null;
        }
    }

    public ViewGroup getContainerRootViewGroupForQualtricsSurvey() {
        return (ViewGroup) this.mView;
    }

    @NonNull
    public Context getContextForQualtricsPrompt() {
        return this.mView.getContext();
    }
}
