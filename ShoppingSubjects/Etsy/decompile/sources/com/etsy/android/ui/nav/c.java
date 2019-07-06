package com.etsy.android.ui.nav;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import com.etsy.android.R;
import com.etsy.android.lib.config.a;
import com.etsy.android.lib.config.b;
import com.etsy.android.lib.config.b.C0062b;
import com.etsy.android.lib.config.b.e;
import com.etsy.android.lib.config.g;
import com.etsy.android.lib.convos.k;
import com.etsy.android.lib.models.Attendee;
import com.etsy.android.lib.models.Conversation;
import com.etsy.android.lib.models.LocalMarket;
import com.etsy.android.lib.models.PaymentMethod;
import com.etsy.android.lib.models.PaymentOption;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.Shop;
import com.etsy.android.lib.models.TaxonomyNode;
import com.etsy.android.lib.models.apiv3.Collection;
import com.etsy.android.lib.models.apiv3.cart.AndroidPayDataContract;
import com.etsy.android.lib.models.convo.context.ManufacturerProject;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.ui.BOEVespaDemoFragment;
import com.etsy.android.ui.EtsyWebFragment;
import com.etsy.android.ui.cart.CartVariationSelectFragment;
import com.etsy.android.ui.cart.CartWithSavedActivity;
import com.etsy.android.ui.convos.ConvoComposeFragment;
import com.etsy.android.ui.convos.ConvoListFragment;
import com.etsy.android.ui.convos.ConvoThreadFragment;
import com.etsy.android.ui.convos.ManufacturerProjectFragment;
import com.etsy.android.ui.convos.convolistredesign.ConvosListFragment;
import com.etsy.android.ui.convos.convoredesign.ConvoThreadFragment2;
import com.etsy.android.ui.core.CollectionFragment;
import com.etsy.android.ui.core.DetailedImageFragment;
import com.etsy.android.ui.core.ListingFragment;
import com.etsy.android.ui.core.ShopAboutDetailedImageFragment;
import com.etsy.android.ui.core.ShopAboutVideoFragment;
import com.etsy.android.ui.core.SingleListingCheckoutActivity;
import com.etsy.android.ui.favorites.CollectionsEditFragment;
import com.etsy.android.ui.favorites.ListingCollectionsCreateFragment;
import com.etsy.android.ui.finds.FindsFragment;
import com.etsy.android.ui.homescreen.HomescreenFragment;
import com.etsy.android.ui.homescreen.LandingPageFragment;
import com.etsy.android.ui.homescreen.RecentlyViewedPageFragment;
import com.etsy.android.ui.homescreen.ShopSharePageFragment;
import com.etsy.android.ui.local.LocalBrowseMapFragment;
import com.etsy.android.ui.local.marketdetails.LocalDatesAttendingFragment;
import com.etsy.android.ui.local.marketdetails.LocalMarketFragment;
import com.etsy.android.ui.local.marketdetails.LocalStoreInfoDetailsFragment;
import com.etsy.android.ui.local.marketdetails.SocialShareLocalMarketBrokerFragment;
import com.etsy.android.ui.promos.VersionPromo;
import com.etsy.android.ui.search.v2.RootTaxonomyCategoryPageFragment;
import com.etsy.android.ui.search.v2.SearchCategoryPageRedirectFragment;
import com.etsy.android.ui.search.v2.SearchOptions;
import com.etsy.android.ui.search.v2.SearchResultsListingsFragment;
import com.etsy.android.ui.search.v2.SearchResultsShopsFragment;
import com.etsy.android.ui.search.v2.SearchTaxonomyCategoryPageFragment;
import com.etsy.android.ui.shop.AppreciationPhotoLandingPageFragment;
import com.etsy.android.ui.shop.FeedbackFragment;
import com.etsy.android.ui.shop.ShopHomeFragment;
import com.etsy.android.ui.user.CurrencySelectFragment;
import com.etsy.android.ui.user.LeaveFeedbackFragment;
import com.etsy.android.ui.user.LegalInfoFragment;
import com.etsy.android.ui.user.PhabletsFragment;
import com.etsy.android.ui.user.PurchasesFragment;
import com.etsy.android.ui.user.ReceiptFragment;
import com.etsy.android.ui.user.ShareFeedbackFragment;
import com.etsy.android.ui.user.profile.UserProfileFragment;
import com.etsy.android.ui.util.CollectionUtil;
import com.etsy.android.uikit.image.CropImageFragment;
import com.etsy.android.uikit.nav.FragmentNavigator;
import com.etsy.android.uikit.nav.FragmentNavigator.AnimationMode;
import com.etsy.android.uikit.nav.FragmentNavigator.FragmentTransactionMode;
import com.etsy.android.uikit.share.SocialShareAppreciationPhotoBrokerFragment;
import com.etsy.android.uikit.share.SocialShareBrokerFragment;
import com.etsy.android.uikit.share.SocialShareListingBrokerFragment;
import com.etsy.android.uikit.share.SocialShareShop2BrokerFragment;
import com.etsy.android.uikit.share.SocialShareShopBrokerFragment;
import com.etsy.android.uikit.ui.settings.NotificationSettingsFragment;
import com.etsy.android.uikit.util.SocialShareUtil;
import com.google.android.gms.wallet.MaskedWallet;
import java.util.HashMap;
import org.parceler.d;

/* compiled from: EtsyFragmentNavigator */
public class c extends FragmentNavigator<c> {
    private final d m;
    private float n;

    /* access modifiers changed from: protected */
    @NonNull
    /* renamed from: d */
    public c c() {
        return this;
    }

    c(@NonNull FragmentActivity fragmentActivity, FragmentManager fragmentManager, FragmentTransactionMode fragmentTransactionMode) {
        super(fragmentActivity, fragmentManager, fragmentTransactionMode);
        this.j = R.id.nav_content_frame;
        this.k = fragmentTransactionMode == FragmentTransactionMode.REPLACE ? AnimationMode.SLIDING : AnimationMode.NONE;
        this.m = new d();
        this.n = (float) a.a().d().e(C0062b.a);
        com.etsy.android.lib.logger.a.a.a(this.h, (double) this.n);
    }

    /* renamed from: e */
    public c B() {
        this.f = false;
        return this;
    }

    public c a(Bundle bundle) {
        if (bundle != null) {
            this.h = bundle;
            com.etsy.android.lib.logger.a.a.a(this.h, (double) this.n);
        }
        return this;
    }

    /* renamed from: a */
    public c b(int i) {
        this.i = i;
        return this;
    }

    public c a(AnimationMode animationMode) {
        this.k = animationMode;
        return this;
    }

    public c a(FragmentManager fragmentManager) {
        this.d = fragmentManager;
        return this;
    }

    public c a(String str) {
        this.c = true;
        this.e = str;
        return this;
    }

    public ListingFragment a(EtsyId etsyId) {
        this.e = "listing";
        this.h.putSerializable("listing_id", etsyId);
        ListingFragment listingFragment = new ListingFragment();
        listingFragment.setArguments(this.h);
        a((Fragment) listingFragment);
        b().c().c(b.h);
        this.m.a(b(), etsyId, this.h);
        return listingFragment;
    }

    public Fragment a(VersionPromo versionPromo) {
        Fragment fragment = versionPromo.getFragment();
        this.h.putSerializable("version_promo", versionPromo);
        fragment.setArguments(this.h);
        a(fragment);
        return fragment;
    }

    public Fragment a(EtsyId etsyId, EtsyId etsyId2) {
        ShopHomeFragment shopHomeFragment = new ShopHomeFragment();
        this.h.putParcelable("shop_id", d.a(etsyId));
        this.m.a(b(), etsyId, etsyId2, this.h);
        shopHomeFragment.setArguments(this.h);
        a((Fragment) shopHomeFragment);
        return shopHomeFragment;
    }

    public Fragment b(String str) {
        ShopHomeFragment shopHomeFragment = new ShopHomeFragment();
        this.h.putString(ResponseConstants.SHOP_NAME, str);
        this.m.a(b(), null, null, this.h);
        shopHomeFragment.setArguments(this.h);
        a((Fragment) shopHomeFragment);
        return shopHomeFragment;
    }

    public FeedbackFragment b(EtsyId etsyId) {
        FeedbackFragment feedbackFragment = new FeedbackFragment();
        this.h.putSerializable("shop_id", etsyId);
        feedbackFragment.setArguments(this.h);
        a((Fragment) feedbackFragment);
        this.m.a(etsyId, this.h);
        return feedbackFragment;
    }

    public FeedbackFragment a(Shop shop) {
        FeedbackFragment feedbackFragment = new FeedbackFragment();
        this.h.putSerializable(ResponseConstants.SHOP, shop);
        feedbackFragment.setArguments(this.h);
        a((Fragment) feedbackFragment);
        if (shop != null) {
            this.m.a(shop.getShopId(), this.h);
        }
        return feedbackFragment;
    }

    public LeaveFeedbackFragment f() {
        LeaveFeedbackFragment leaveFeedbackFragment = new LeaveFeedbackFragment();
        leaveFeedbackFragment.setArguments(this.h);
        a((Fragment) leaveFeedbackFragment);
        return leaveFeedbackFragment;
    }

    public ShareFeedbackFragment g() {
        ShareFeedbackFragment shareFeedbackFragment = new ShareFeedbackFragment();
        shareFeedbackFragment.setArguments(this.h);
        a((Fragment) shareFeedbackFragment);
        return shareFeedbackFragment;
    }

    public UserProfileFragment c(EtsyId etsyId) {
        this.h.putSerializable("user_id", etsyId);
        UserProfileFragment userProfileFragment = new UserProfileFragment();
        userProfileFragment.setArguments(this.h);
        a((Fragment) userProfileFragment);
        return userProfileFragment;
    }

    public CartVariationSelectFragment h() {
        CartVariationSelectFragment cartVariationSelectFragment = new CartVariationSelectFragment();
        cartVariationSelectFragment.setArguments(this.h);
        a((Fragment) cartVariationSelectFragment);
        this.m.n();
        return cartVariationSelectFragment;
    }

    public EtsyWebFragment c(String str) {
        EtsyWebFragment etsyWebFragment = new EtsyWebFragment();
        HashMap hashMap = new HashMap();
        hashMap.put("url", str);
        this.h.putSerializable("parameters", hashMap);
        etsyWebFragment.setArguments(this.h);
        a((Fragment) etsyWebFragment);
        return etsyWebFragment;
    }

    public EtsyWebFragment b(EtsyId etsyId, EtsyId etsyId2) {
        EtsyWebFragment etsyWebFragment = new EtsyWebFragment();
        HashMap hashMap = new HashMap();
        if (etsyId != null) {
            hashMap.put(ResponseConstants.RECEIPT_ID, etsyId.getId());
        }
        if (etsyId2 != null) {
            hashMap.put(ResponseConstants.RECEIPT_SHIPPING_ID, etsyId2.getId());
        }
        this.h.putSerializable("parameters", hashMap);
        this.h.putInt("redirect_id", 9);
        etsyWebFragment.setArguments(this.h);
        a((Fragment) etsyWebFragment);
        return etsyWebFragment;
    }

    public EtsyWebFragment a(@NonNull AndroidPayDataContract androidPayDataContract, @Nullable MaskedWallet maskedWallet) {
        this.h.putSerializable("parameters", a(androidPayDataContract, androidPayDataContract.getLastPaymentMethod()));
        this.h.putSerializable(CartWithSavedActivity.CHECKED_OUT_CART, androidPayDataContract);
        this.h.putBoolean(CartWithSavedActivity.CHECKED_OUT_IS_MSCO, false);
        this.h.putInt("redirect_id", 1);
        this.h.putString("TRACKING_NAME", "cart_checkout");
        this.h.putParcelable("android_pay_masked_wallet", maskedWallet);
        EtsyWebFragment etsyWebFragment = new EtsyWebFragment();
        etsyWebFragment.setArguments(this.h);
        a((Fragment) etsyWebFragment);
        this.m.a(androidPayDataContract);
        return etsyWebFragment;
    }

    public EtsyWebFragment a(@NonNull String str, @NonNull String str2) {
        return a(str, str2, (AndroidPayDataContract) null, (MaskedWallet) null);
    }

    public EtsyWebFragment a(@NonNull String str, @NonNull String str2, @Nullable AndroidPayDataContract androidPayDataContract, @Nullable MaskedWallet maskedWallet) {
        HashMap hashMap = new HashMap();
        hashMap.put(CartWithSavedActivity.CART_IDS, str);
        hashMap.put("payment_method", str2);
        this.h.putSerializable("parameters", hashMap);
        this.h.putInt("redirect_id", 13);
        this.h.putString("TRACKING_NAME", "cart_checkout");
        if (b().c().c(e.b)) {
            this.h.putSerializable(CartWithSavedActivity.CHECKED_OUT_CART, androidPayDataContract);
            this.h.putParcelable("android_pay_masked_wallet", maskedWallet);
        }
        EtsyWebFragment etsyWebFragment = new EtsyWebFragment();
        etsyWebFragment.setArguments(this.h);
        a((Fragment) etsyWebFragment);
        return etsyWebFragment;
    }

    public EtsyWebFragment a(@NonNull String str, @NonNull String str2, @NonNull PaymentOption paymentOption, @Nullable String str3, @Nullable String str4) {
        HashMap hashMap = new HashMap();
        hashMap.put("payment_method", paymentOption.getPaymentMethod());
        hashMap.put("listing_id", str);
        hashMap.put("quantity", str2);
        if (!TextUtils.isEmpty(str3)) {
            hashMap.put(SingleListingCheckoutActivity.PARAM_LISTING_INVENTORY_ID, str3);
        } else {
            hashMap.put(SingleListingCheckoutActivity.PARAM_LISTING_VARIATION, str4);
        }
        this.h.putSerializable("parameters", hashMap);
        this.h.putInt("redirect_id", 12);
        this.h.putString("TRACKING_NAME", ResponseConstants.SINGLE_LISTING_CHECKOUT);
        EtsyWebFragment etsyWebFragment = new EtsyWebFragment();
        etsyWebFragment.setArguments(this.h);
        a((Fragment) etsyWebFragment);
        return etsyWebFragment;
    }

    public EtsyWebFragment a(@NonNull String str, @NonNull String str2, @Nullable String str3, @Nullable String str4, @NonNull AndroidPayDataContract androidPayDataContract, @NonNull MaskedWallet maskedWallet) {
        HashMap hashMap = new HashMap();
        hashMap.put("payment_method", "android_pay");
        hashMap.put("listing_id", str);
        hashMap.put("quantity", str2);
        hashMap.put(SingleListingCheckoutActivity.PARAM_LISTING_INVENTORY_ID, str3);
        hashMap.put(SingleListingCheckoutActivity.PARAM_LISTING_VARIATION, str4);
        this.h.putSerializable("parameters", hashMap);
        this.h.putInt("redirect_id", 12);
        this.h.putString("TRACKING_NAME", ResponseConstants.SINGLE_LISTING_CHECKOUT);
        this.h.putSerializable(CartWithSavedActivity.CHECKED_OUT_CART, androidPayDataContract);
        this.h.putParcelable("android_pay_masked_wallet", maskedWallet);
        EtsyWebFragment etsyWebFragment = new EtsyWebFragment();
        etsyWebFragment.setArguments(this.h);
        a((Fragment) etsyWebFragment);
        return etsyWebFragment;
    }

    private HashMap<String, String> a(AndroidPayDataContract androidPayDataContract, PaymentMethod paymentMethod) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("cart_id", String.valueOf(androidPayDataContract.getCartId()));
        hashMap.put("payment_method", paymentMethod != null ? paymentMethod.getType() : "");
        if (androidPayDataContract.hasGiftCardApplied() && paymentMethod != null && paymentMethod.isDirectCheckout()) {
            hashMap.put("apply_gift_card_balance", String.valueOf(1));
        }
        return hashMap;
    }

    public EtsyWebFragment i() {
        EtsyWebFragment etsyWebFragment = new EtsyWebFragment();
        HashMap hashMap = new HashMap();
        hashMap.put(ResponseConstants.VERSION, g.a().f());
        this.h.putSerializable("parameters", hashMap);
        this.h.putInt("redirect_id", 4);
        this.h.putString("TRACKING_NAME", "help");
        etsyWebFragment.setArguments(this.h);
        a((Fragment) etsyWebFragment);
        this.m.e();
        return etsyWebFragment;
    }

    public EtsyWebFragment j() {
        EtsyWebFragment etsyWebFragment = new EtsyWebFragment();
        HashMap hashMap = new HashMap();
        if (b().c().c(b.ap)) {
            hashMap.put("url", b().c().b(b.ar));
        } else {
            hashMap.put("url", b().c().b(b.ak));
        }
        this.h.putString("TRACKING_NAME", "terms_of_use");
        this.h.putSerializable("parameters", hashMap);
        etsyWebFragment.setArguments(this.h);
        a((Fragment) etsyWebFragment);
        this.m.j();
        return etsyWebFragment;
    }

    public EtsyWebFragment k() {
        EtsyWebFragment etsyWebFragment = new EtsyWebFragment();
        HashMap hashMap = new HashMap();
        hashMap.put("url", b().c().b(b.an));
        this.h.putString("TRACKING_NAME", "privacy_policy");
        this.h.putSerializable("parameters", hashMap);
        etsyWebFragment.setArguments(this.h);
        a((Fragment) etsyWebFragment);
        this.m.k();
        return etsyWebFragment;
    }

    public EtsyWebFragment l() {
        EtsyWebFragment etsyWebFragment = new EtsyWebFragment();
        HashMap hashMap = new HashMap();
        hashMap.put("url", a.a().d().b(b.aq));
        this.h.putSerializable("parameters", hashMap);
        this.h.putString("TRACKING_NAME", "settings_legal");
        etsyWebFragment.setArguments(this.h);
        a((Fragment) etsyWebFragment);
        this.m.i();
        return etsyWebFragment;
    }

    public EtsyWebFragment m() {
        this.h.putSerializable("parameters", new HashMap());
        this.h.putInt("redirect_id", 14);
        this.h.putString("TRACKING_NAME", "user_addresses");
        EtsyWebFragment etsyWebFragment = new EtsyWebFragment();
        etsyWebFragment.setArguments(this.h);
        a((Fragment) etsyWebFragment);
        return etsyWebFragment;
    }

    public EtsyWebFragment d(String str) {
        EtsyWebFragment etsyWebFragment = new EtsyWebFragment();
        HashMap hashMap = new HashMap();
        hashMap.put("url", str);
        this.h.putSerializable("parameters", hashMap);
        etsyWebFragment.setArguments(this.h);
        a((Fragment) etsyWebFragment);
        return etsyWebFragment;
    }

    public EtsyWebFragment e(String str) {
        return d(str);
    }

    public EtsyWebFragment n() {
        EtsyWebFragment etsyWebFragment = new EtsyWebFragment();
        HashMap hashMap = new HashMap();
        hashMap.put("url", a.a().d().b(b.am));
        this.h.putSerializable("parameters", hashMap);
        this.h.putString("TRACKING_NAME", "electronic_communications_policy");
        etsyWebFragment.setArguments(this.h);
        a((Fragment) etsyWebFragment);
        this.m.l();
        return etsyWebFragment;
    }

    public NotificationSettingsFragment o() {
        NotificationSettingsFragment notificationSettingsFragment = new NotificationSettingsFragment();
        notificationSettingsFragment.setArguments(this.h);
        a((Fragment) notificationSettingsFragment);
        this.m.d();
        return notificationSettingsFragment;
    }

    public EtsyWebFragment f(String str) {
        EtsyWebFragment etsyWebFragment = new EtsyWebFragment();
        HashMap hashMap = new HashMap();
        hashMap.put("url", str);
        this.h.putSerializable("parameters", hashMap);
        etsyWebFragment.setArguments(this.h);
        a((Fragment) etsyWebFragment);
        return etsyWebFragment;
    }

    public CurrencySelectFragment a(CurrencySelectFragment.a aVar) {
        CurrencySelectFragment currencySelectFragment = new CurrencySelectFragment();
        currencySelectFragment.setCurrencySelectedCallback(aVar);
        a((Fragment) currencySelectFragment);
        this.m.h();
        return currencySelectFragment;
    }

    public LegalInfoFragment p() {
        LegalInfoFragment legalInfoFragment = new LegalInfoFragment();
        a((Fragment) legalInfoFragment);
        this.m.i();
        return legalInfoFragment;
    }

    public ListingCollectionsCreateFragment a(CollectionUtil.g gVar, EtsyId etsyId, String str) {
        ListingCollectionsCreateFragment listingCollectionsCreateFragment = new ListingCollectionsCreateFragment();
        this.h.putSerializable("listing_id", etsyId);
        this.h.putString(ResponseConstants.LISTING_IMAGE_URL, str);
        listingCollectionsCreateFragment.setArguments(this.h);
        listingCollectionsCreateFragment.setListingCollectionCreationListener(gVar);
        a((Fragment) listingCollectionsCreateFragment);
        this.m.q();
        return listingCollectionsCreateFragment;
    }

    public CollectionsEditFragment a(Collection collection) {
        CollectionsEditFragment collectionsEditFragment = new CollectionsEditFragment();
        this.h.putSerializable(Collection.TYPE_COLLECTION, collection);
        collectionsEditFragment.setArguments(this.h);
        a((Fragment) collectionsEditFragment);
        this.m.p();
        return collectionsEditFragment;
    }

    public LocalBrowseMapFragment q() {
        LocalBrowseMapFragment localBrowseMapFragment = new LocalBrowseMapFragment();
        localBrowseMapFragment.setArguments(this.h);
        a((Fragment) localBrowseMapFragment);
        return localBrowseMapFragment;
    }

    public Fragment a(EtsyId etsyId, boolean z) {
        this.h.putSerializable(ResponseConstants.LOCAL_MARKET_ID, etsyId);
        this.h.putBoolean("show_local_browse_link", z);
        LocalMarketFragment localMarketFragment = new LocalMarketFragment();
        localMarketFragment.setArguments(this.h);
        a((Fragment) localMarketFragment);
        this.m.a(etsyId);
        return localMarketFragment;
    }

    public Fragment a(LocalMarket localMarket, boolean z) {
        LocalMarketFragment localMarketFragment = new LocalMarketFragment();
        this.h.putParcelable(ResponseConstants.LOCAL_MARKET, d.a(localMarket));
        this.h.putBoolean("show_local_browse_link", z);
        localMarketFragment.setArguments(this.h);
        a((Fragment) localMarketFragment);
        this.m.a(localMarket.getLocalMarketId());
        return localMarketFragment;
    }

    public Fragment a(Attendee attendee, LocalMarket localMarket) {
        this.h.putSerializable("attendee", attendee);
        this.h.putSerializable(ResponseConstants.LOCAL_MARKET, localMarket);
        LocalDatesAttendingFragment localDatesAttendingFragment = new LocalDatesAttendingFragment();
        localDatesAttendingFragment.setArguments(this.h);
        a((Fragment) localDatesAttendingFragment);
        return localDatesAttendingFragment;
    }

    public Fragment a(LocalMarket localMarket) {
        this.h.putSerializable(ResponseConstants.LOCAL_MARKET, localMarket);
        LocalStoreInfoDetailsFragment localStoreInfoDetailsFragment = new LocalStoreInfoDetailsFragment();
        localStoreInfoDetailsFragment.setArguments(this.h);
        a((Fragment) localStoreInfoDetailsFragment);
        return localStoreInfoDetailsFragment;
    }

    public Fragment d(EtsyId etsyId) {
        this.h.putSerializable(ResponseConstants.RECEIPT_ID, etsyId);
        ReceiptFragment receiptFragment = new ReceiptFragment();
        this.e = ReceiptFragment.FRAGMENT_TAG;
        receiptFragment.setArguments(this.h);
        a((Fragment) receiptFragment);
        this.m.b(b(), etsyId, this.h);
        return receiptFragment;
    }

    public Fragment e(EtsyId etsyId) {
        this.h.putSerializable("receipt_transaction_id", etsyId);
        ReceiptFragment receiptFragment = new ReceiptFragment();
        this.e = ReceiptFragment.FRAGMENT_TAG;
        receiptFragment.setArguments(this.h);
        a((Fragment) receiptFragment);
        this.m.b(b(), etsyId, this.h);
        return receiptFragment;
    }

    public CollectionFragment b(Collection collection) {
        this.h.putSerializable(Collection.TYPE_COLLECTION, collection);
        CollectionFragment collectionFragment = new CollectionFragment();
        collectionFragment.setArguments(this.h);
        a((Fragment) collectionFragment);
        return collectionFragment;
    }

    public ConvoListFragment r() {
        ConvoListFragment instance = ConvoListFragment.getInstance();
        instance.setArguments(this.h);
        a((Fragment) instance);
        this.m.a();
        return instance;
    }

    public ConvosListFragment s() {
        ConvosListFragment convosListFragment = new ConvosListFragment();
        a((Fragment) convosListFragment);
        this.m.a();
        return convosListFragment;
    }

    public ConvoComposeFragment a(k kVar) {
        ConvoComposeFragment convoComposeFragment = new ConvoComposeFragment();
        if (this.h.containsKey("convo_id")) {
            this.h.putString("TRACKING_NAME", "conversations_thread_reply");
        } else {
            this.h.putString("TRACKING_NAME", "conversations_new");
        }
        convoComposeFragment.setArguments(this.h);
        a((Fragment) convoComposeFragment);
        this.m.a(this.h);
        return convoComposeFragment;
    }

    public ConvoThreadFragment a(Conversation conversation, boolean z) {
        this.h.putSerializable("conversation", conversation);
        this.h.putBoolean("convo_change_read_state", z);
        ConvoThreadFragment instance = ConvoThreadFragment.getInstance();
        instance.setArguments(this.h);
        a((Fragment) instance);
        this.m.a(b(), String.valueOf(conversation.getConversationId()));
        return instance;
    }

    public ConvoThreadFragment2 a(long j, boolean z) {
        this.h.putLong("convo_id", j);
        this.h.putBoolean("convo_change_read_state", z);
        ConvoThreadFragment2 convoThreadFragment2 = new ConvoThreadFragment2();
        convoThreadFragment2.setArguments(this.h);
        a((Fragment) convoThreadFragment2);
        this.m.a(b(), String.valueOf(j));
        return convoThreadFragment2;
    }

    public ManufacturerProjectFragment a(ManufacturerProject manufacturerProject) {
        ManufacturerProjectFragment manufacturerProjectFragment = new ManufacturerProjectFragment();
        this.h.putParcelable("manufacturer_project", d.a(manufacturerProject));
        manufacturerProjectFragment.setArguments(this.h);
        a((Fragment) manufacturerProjectFragment);
        return manufacturerProjectFragment;
    }

    public DetailedImageFragment t() {
        DetailedImageFragment detailedImageFragment = new DetailedImageFragment();
        detailedImageFragment.setArguments(this.h);
        a((Fragment) detailedImageFragment);
        this.m.g();
        return detailedImageFragment;
    }

    public ShopAboutDetailedImageFragment u() {
        ShopAboutDetailedImageFragment shopAboutDetailedImageFragment = new ShopAboutDetailedImageFragment();
        shopAboutDetailedImageFragment.setArguments(this.h);
        a((Fragment) shopAboutDetailedImageFragment);
        this.m.g();
        return shopAboutDetailedImageFragment;
    }

    public void a(String str, Bundle bundle) {
        SearchResultsListingsFragment searchResultsListingsFragment = new SearchResultsListingsFragment();
        this.h.putString("SEARCH_QUERY", str);
        if (bundle != null) {
            this.h.putBundle("SEARCH_REQUEST_PARAMS", bundle);
        }
        searchResultsListingsFragment.setArguments(this.h);
        a((Fragment) searchResultsListingsFragment);
        this.m.b(b(), str);
    }

    public void a(String str, @Nullable SearchOptions searchOptions, @Nullable String str2, @Nullable TaxonomyNode taxonomyNode) {
        SearchResultsListingsFragment searchResultsListingsFragment = new SearchResultsListingsFragment();
        this.h.putString("SEARCH_QUERY", str);
        if (searchOptions != null) {
            this.h.putParcelable("SEARCH_OPTIONS", searchOptions);
        }
        if (str2 != null) {
            this.h.putString("ANCHOR_LISTING_ID", str2);
        }
        if (taxonomyNode != null) {
            this.h.putParcelable("SEARCH_TAXONOMY_NODE", d.a(taxonomyNode));
        }
        searchResultsListingsFragment.setArguments(this.h);
        a((Fragment) searchResultsListingsFragment);
        this.m.b(b(), str);
    }

    public void g(String str) {
        SearchResultsShopsFragment searchResultsShopsFragment = new SearchResultsShopsFragment();
        this.h.putString("SEARCH_QUERY", str);
        searchResultsShopsFragment.setArguments(this.h);
        a((Fragment) searchResultsShopsFragment);
        this.m.c(b(), str);
    }

    public PurchasesFragment v() {
        PurchasesFragment purchasesFragment = new PurchasesFragment();
        purchasesFragment.setArguments(this.h);
        a((Fragment) purchasesFragment);
        this.m.m();
        return purchasesFragment;
    }

    public PhabletsFragment w() {
        PhabletsFragment phabletsFragment = new PhabletsFragment();
        phabletsFragment.setArguments(this.h);
        a((Fragment) phabletsFragment);
        return phabletsFragment;
    }

    public Fragment b(Bundle bundle) {
        LandingPageFragment landingPageFragment = new LandingPageFragment();
        this.h.putAll(bundle);
        landingPageFragment.setArguments(this.h);
        a((Fragment) landingPageFragment);
        return landingPageFragment;
    }

    public Fragment c(Bundle bundle) {
        HomescreenFragment homescreenFragment = new HomescreenFragment();
        homescreenFragment.setArguments(bundle);
        a((Fragment) homescreenFragment);
        return homescreenFragment;
    }

    public Fragment d(Bundle bundle) {
        if (!bundle.containsKey(ResponseConstants.PAGE_LINK)) {
            return null;
        }
        RecentlyViewedPageFragment recentlyViewedPageFragment = new RecentlyViewedPageFragment();
        this.h.putAll(bundle);
        recentlyViewedPageFragment.setArguments(this.h);
        a((Fragment) recentlyViewedPageFragment);
        return recentlyViewedPageFragment;
    }

    public Fragment e(Bundle bundle) {
        ShopSharePageFragment shopSharePageFragment = new ShopSharePageFragment();
        this.h.putAll(bundle);
        shopSharePageFragment.setArguments(this.h);
        a((Fragment) shopSharePageFragment);
        return shopSharePageFragment;
    }

    public Fragment f(Bundle bundle) {
        ShopAboutVideoFragment shopAboutVideoFragment = new ShopAboutVideoFragment();
        this.h.putAll(bundle);
        shopAboutVideoFragment.setArguments(this.h);
        a((Fragment) shopAboutVideoFragment);
        return shopAboutVideoFragment;
    }

    public AppreciationPhotoLandingPageFragment f(EtsyId etsyId) {
        this.h.putSerializable(ResponseConstants.TRANSACTION_ID, etsyId);
        AppreciationPhotoLandingPageFragment appreciationPhotoLandingPageFragment = new AppreciationPhotoLandingPageFragment();
        appreciationPhotoLandingPageFragment.setArguments(this.h);
        a((Fragment) appreciationPhotoLandingPageFragment);
        return appreciationPhotoLandingPageFragment;
    }

    public Fragment x() {
        SocialShareBrokerFragment socialShareBrokerFragment;
        if (this.h.containsKey("listing")) {
            socialShareBrokerFragment = new SocialShareListingBrokerFragment();
        } else if (this.h.containsKey(ResponseConstants.SHOP)) {
            socialShareBrokerFragment = new SocialShareShopBrokerFragment();
        } else if (this.h.containsKey(ResponseConstants.APPRECIATION_PHOTO)) {
            socialShareBrokerFragment = new SocialShareAppreciationPhotoBrokerFragment();
        } else if (this.h.containsKey(ResponseConstants.LOCAL_MARKET)) {
            socialShareBrokerFragment = new SocialShareLocalMarketBrokerFragment();
        } else if (this.h.containsKey("shop2")) {
            socialShareBrokerFragment = new SocialShareShop2BrokerFragment();
        } else {
            throw new RuntimeException("Args did not contain Shareable model");
        }
        a(AnimationMode.SLIDE_BOTTOM);
        socialShareBrokerFragment.setArguments(this.h);
        a((Fragment) socialShareBrokerFragment);
        b().a("share_sheet_shown", null);
        com.etsy.android.lib.logger.a.a.a(SocialShareUtil.a("share_sheet_shown"));
        return socialShareBrokerFragment;
    }

    public Fragment y() {
        CropImageFragment cropImageFragment = new CropImageFragment();
        cropImageFragment.setArguments(this.h);
        a((Fragment) cropImageFragment);
        return cropImageFragment;
    }

    public Fragment z() {
        RootTaxonomyCategoryPageFragment rootTaxonomyCategoryPageFragment = new RootTaxonomyCategoryPageFragment();
        rootTaxonomyCategoryPageFragment.setArguments(this.h);
        a((Fragment) rootTaxonomyCategoryPageFragment);
        return rootTaxonomyCategoryPageFragment;
    }

    public Fragment g(Bundle bundle) {
        SearchTaxonomyCategoryPageFragment searchTaxonomyCategoryPageFragment = new SearchTaxonomyCategoryPageFragment();
        this.h.putAll(bundle);
        searchTaxonomyCategoryPageFragment.setArguments(this.h);
        a((Fragment) searchTaxonomyCategoryPageFragment);
        return searchTaxonomyCategoryPageFragment;
    }

    public Fragment h(String str) {
        FindsFragment findsFragment = new FindsFragment();
        this.h.putString("finds_slug", str);
        findsFragment.setArguments(this.h);
        a((Fragment) findsFragment);
        return findsFragment;
    }

    public Fragment h(Bundle bundle) {
        SearchCategoryPageRedirectFragment searchCategoryPageRedirectFragment = new SearchCategoryPageRedirectFragment();
        searchCategoryPageRedirectFragment.setArguments(bundle);
        a((Fragment) searchCategoryPageRedirectFragment);
        return searchCategoryPageRedirectFragment;
    }

    public Fragment A() {
        BOEVespaDemoFragment bOEVespaDemoFragment = new BOEVespaDemoFragment();
        a((Fragment) bOEVespaDemoFragment);
        return bOEVespaDemoFragment;
    }
}
