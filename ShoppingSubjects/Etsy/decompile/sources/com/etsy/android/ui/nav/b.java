package com.etsy.android.ui.nav;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.TaskStackBuilder;
import android.text.TextUtils;
import com.etsy.android.R;
import com.etsy.android.lib.config.a;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.messaging.EtsyAction;
import com.etsy.android.lib.models.Attendee;
import com.etsy.android.lib.models.BaseModelImage;
import com.etsy.android.lib.models.Conversation;
import com.etsy.android.lib.models.Listing;
import com.etsy.android.lib.models.LocalMarket;
import com.etsy.android.lib.models.PaymentOption;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.Shop;
import com.etsy.android.lib.models.TaxonomyNode;
import com.etsy.android.lib.models.apiv3.Collection;
import com.etsy.android.lib.models.apiv3.Image;
import com.etsy.android.lib.models.apiv3.ListingCollection;
import com.etsy.android.lib.models.apiv3.LocalBrowseModule;
import com.etsy.android.lib.models.apiv3.Offering;
import com.etsy.android.lib.models.apiv3.cart.AndroidPayDataContract;
import com.etsy.android.lib.models.apiv3.cart.SingleListingCart;
import com.etsy.android.lib.models.apiv3.vespa.ServerDrivenAction;
import com.etsy.android.lib.models.convo.context.ManufacturerProject;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.finds.FindsUrl;
import com.etsy.android.lib.models.homescreen.HomescreenTab;
import com.etsy.android.lib.models.homescreen.LandingPageInfo;
import com.etsy.android.lib.models.homescreen.LandingPageLink;
import com.etsy.android.lib.models.interfaces.AppreciationPhotoLike;
import com.etsy.android.lib.models.interfaces.BasicListingLike;
import com.etsy.android.lib.models.interfaces.ListingLike;
import com.etsy.android.lib.models.interfaces.ShopLike;
import com.etsy.android.lib.models.interfaces.ShopShareable;
import com.etsy.android.lib.shophome.ShopHomeInitialLoadConfiguration;
import com.etsy.android.lib.util.ExternalAccountUtil.SignInFlow;
import com.etsy.android.lib.util.af;
import com.etsy.android.lib.util.k;
import com.etsy.android.lib.util.l;
import com.etsy.android.lib.util.n;
import com.etsy.android.ui.BOEVespaDemoActivity;
import com.etsy.android.ui.CropImageActivity;
import com.etsy.android.ui.EtsyPreferenceActivity;
import com.etsy.android.ui.EtsyWebActivity;
import com.etsy.android.ui.ExploreActivity;
import com.etsy.android.ui.cart.CartVariationSelectActivity;
import com.etsy.android.ui.cart.CartWithSavedActivity;
import com.etsy.android.ui.convos.ConvoBaseActivity;
import com.etsy.android.ui.convos.ConvoComposeActivity;
import com.etsy.android.ui.convos.ConvoComposeDialogActivity;
import com.etsy.android.ui.convos.ConvoViewActivity;
import com.etsy.android.ui.convos.ManufacturerProjectActivity;
import com.etsy.android.ui.convos.convolistredesign.ConvoListActivity;
import com.etsy.android.ui.convos.convoredesign.ConvoActivity;
import com.etsy.android.ui.core.CoreActivity;
import com.etsy.android.ui.core.DetailedImageActivity;
import com.etsy.android.ui.core.EtsyDialogLauncherActivity;
import com.etsy.android.ui.core.GiftWrapDescriptionActivity;
import com.etsy.android.ui.core.ShopAboutVideoActivity;
import com.etsy.android.ui.core.SingleListingCheckoutActivity;
import com.etsy.android.ui.dialog.TextInfoActivity;
import com.etsy.android.ui.favorites.CollectionEditActivity;
import com.etsy.android.ui.favorites.FavoritesActivity;
import com.etsy.android.ui.favorites.ListingCollectionsActivity;
import com.etsy.android.ui.feedback.FeedbackActivity;
import com.etsy.android.ui.finds.FindsActivity;
import com.etsy.android.ui.giftcards.GiftCardCreateActivity;
import com.etsy.android.ui.homescreen.HomescreenTabsActivity;
import com.etsy.android.ui.homescreen.LandingPageActivity;
import com.etsy.android.ui.homescreen.RecentlyViewedListingsActivity;
import com.etsy.android.ui.homescreen.ShopSharePageActivity;
import com.etsy.android.ui.local.LocalSearchActivity;
import com.etsy.android.ui.local.marketdetails.LocalDatesAttendingActivity;
import com.etsy.android.ui.local.marketdetails.LocalEventActivity;
import com.etsy.android.ui.local.marketdetails.LocalStoreInfoDetailsActivity;
import com.etsy.android.ui.promos.VersionPromo;
import com.etsy.android.ui.promos.VersionPromoActivity;
import com.etsy.android.ui.search.v2.SearchOptions;
import com.etsy.android.ui.search.v2.SearchV2Activity;
import com.etsy.android.ui.shop.AppreciationPhotoLandingPageActivity;
import com.etsy.android.ui.shop.ShopSubActivity;
import com.etsy.android.ui.shophome.ShopHomeActivity;
import com.etsy.android.ui.user.CirclesActivity;
import com.etsy.android.ui.user.LeaveFeedbackActivity;
import com.etsy.android.ui.user.LeaveFeedbackDialogActivity;
import com.etsy.android.ui.user.NotificationSettingsActivity;
import com.etsy.android.ui.user.PhabletsActivity;
import com.etsy.android.ui.user.PurchasesActivity;
import com.etsy.android.ui.user.SettingsActivity;
import com.etsy.android.ui.user.ShareDialogActivity;
import com.etsy.android.ui.user.ShareFeedbackDialogActivity;
import com.etsy.android.ui.user.SocialShareDialogActivity;
import com.etsy.android.ui.user.UserActivity;
import com.etsy.android.ui.user.auth.ForgotPasswordDialogActivity;
import com.etsy.android.ui.user.auth.SignInActivity;
import com.etsy.android.uikit.CustomViewDemoActivity;
import com.etsy.android.uikit.image.CropImageUtil.Options;
import com.etsy.android.uikit.nav.ActivityNavigator;
import com.etsy.android.uikit.nav.ActivityNavigator.AnimationMode;
import com.etsy.android.uikit.ui.core.BaseDialogFragment;
import com.google.android.gms.wallet.MaskedWallet;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import org.parceler.d;

/* compiled from: EtsyActivityNavigator */
public class b extends ActivityNavigator<b> {
    private boolean k;
    private Intent[] l;
    private Bundle m;

    /* access modifiers changed from: protected */
    @NonNull
    /* renamed from: d */
    public b c() {
        return this;
    }

    b(Activity activity) {
        super(activity);
        this.g = AnimationMode.SLIDE_RIGHT;
    }

    public b a(Bundle bundle) {
        this.m = bundle;
        return this;
    }

    public b e() {
        this.b = true;
        this.a = true;
        return this;
    }

    public b f() {
        this.c = true;
        return this;
    }

    public b a(@NonNull String str) {
        this.f = str;
        return this;
    }

    public b a(AnimationMode animationMode) {
        this.g = animationMode;
        return this;
    }

    public b a(int i) {
        this.e = i;
        return this;
    }

    public b a(Intent... intentArr) {
        this.l = intentArr;
        return this;
    }

    public b a(int i, Fragment fragment) {
        this.e = i;
        this.j = fragment;
        return this;
    }

    public b g() {
        this.d = true;
        return this;
    }

    public void h() {
        a(false);
    }

    public void a(boolean z) {
        if (z) {
            a(new Intent(this.i, CartWithSavedActivity.class));
        }
        Intent intent = new Intent(this.i, GiftCardCreateActivity.class);
        com.etsy.android.lib.logger.legacy.b.a().a("create_gift_card");
        b(intent);
    }

    public void a(EtsyAction etsyAction, Bundle bundle) {
        Intent intent = new Intent(this.i, SignInActivity.class);
        intent.setAction(etsyAction.getAction());
        intent.putExtra(EtsyAction.ACTION_TYPE_NAME, etsyAction.getName());
        intent.putExtra(etsyAction.getName(), bundle);
        a(300);
        a(intent, etsyAction);
    }

    public void a(EtsyAction etsyAction, String str) {
        Intent intent = new Intent(this.i, SignInActivity.class);
        intent.setAction(etsyAction.getAction());
        intent.putExtra(EtsyAction.ACTION_TYPE_NAME, etsyAction.getName());
        intent.putExtra(etsyAction.getName(), str);
        a(300);
        a(intent, etsyAction);
    }

    public void b(boolean z) {
        Intent intent = new Intent(this.i, SignInActivity.class);
        intent.putExtra(SignInActivity.EXTRA_SIGN_IN, false);
        a(intent, z ? EtsyAction.VIEW_FEED : EtsyAction.VIEW);
    }

    public void i() {
        Intent intent = new Intent(this.i, SignInActivity.class);
        intent.putExtra(SignInActivity.EXTRA_SIGN_IN, true);
        intent.putExtra("show_social_buttons", true);
        a(intent, EtsyAction.VIEW);
    }

    public void j() {
        Intent intent = new Intent(this.i, SignInActivity.class);
        intent.putExtra(SignInActivity.EXTRA_REGISTER, true);
        intent.putExtra("show_social_buttons", true);
        a(intent, EtsyAction.VIEW);
    }

    public void a(EtsyAction etsyAction) {
        Intent intent = new Intent(this.i, SignInActivity.class);
        intent.setAction(etsyAction.getAction());
        intent.putExtra(EtsyAction.ACTION_TYPE_NAME, etsyAction.getName());
        a(301);
        a(intent, etsyAction);
    }

    private void a(Intent intent, EtsyAction etsyAction) {
        intent.putExtra("type", false);
        this.h = true;
        this.g = AnimationMode.FADE_SLOW;
        b().a("login_nag_displayed", new EtsyActivityNavigator$1(this, etsyAction));
        b(intent);
    }

    public void a(EtsyId etsyId) {
        Intent intent = new Intent(this.i, CoreActivity.class);
        intent.putExtra("listing_id", etsyId);
        b(intent);
    }

    public void a(EtsyId etsyId, Bundle bundle) {
        Intent intent = new Intent(this.i, CoreActivity.class);
        intent.putExtra("listing_id", etsyId);
        intent.putExtra("referral_args", bundle);
        b(intent);
    }

    public void b(EtsyId etsyId) {
        Intent intent = new Intent(this.i, k());
        intent.putExtra("shop_id", etsyId);
        b(intent);
    }

    public void a(EtsyId etsyId, EtsyId etsyId2) {
        Intent intent = new Intent(this.i, k());
        intent.putExtra("shop_id", etsyId);
        intent.putExtra("user_id", etsyId2);
        b(intent);
    }

    public void b(EtsyId etsyId, Bundle bundle) {
        Intent intent = new Intent(this.i, k());
        intent.putExtra("shop_id", etsyId);
        intent.putExtra("referral_args", bundle);
        b(intent);
    }

    public void a(@NonNull EtsyId etsyId, @Nullable Bundle bundle, @Nullable ShopHomeInitialLoadConfiguration shopHomeInitialLoadConfiguration) {
        Intent intent = new Intent(this.i, k());
        intent.putExtra("shop_id", etsyId);
        if (shopHomeInitialLoadConfiguration != null) {
            intent.putExtra("shop_home_load_configuration", d.a(shopHomeInitialLoadConfiguration));
        }
        if (bundle != null) {
            intent.putExtra("referral_args", bundle);
        }
        b(intent);
    }

    public void a(@NonNull String str, @Nullable Bundle bundle, @Nullable ShopHomeInitialLoadConfiguration shopHomeInitialLoadConfiguration) {
        Intent intent = new Intent(this.i, k());
        intent.putExtra(ResponseConstants.SHOP_NAME, str);
        if (shopHomeInitialLoadConfiguration != null) {
            intent.putExtra("shop_home_load_configuration", d.a(shopHomeInitialLoadConfiguration));
        }
        if (bundle != null) {
            intent.putExtra("referral_args", bundle);
        }
        b(intent);
    }

    public void a(String str, Bundle bundle) {
        Intent intent = new Intent(this.i, k());
        intent.putExtra(ResponseConstants.SHOP_NAME, str);
        intent.putExtra("referral_args", bundle);
        b(intent);
    }

    public Class k() {
        if (a.a().d().c(com.etsy.android.lib.config.b.bE)) {
            return ShopHomeActivity.class;
        }
        return CoreActivity.class;
    }

    public void c(EtsyId etsyId, Bundle bundle) {
        a(etsyId, bundle, 3);
    }

    public void d(EtsyId etsyId, Bundle bundle) {
        a(etsyId, bundle, 2);
    }

    public void a(EtsyId etsyId, Bundle bundle, int i) {
        a(etsyId, bundle, new ShopHomeInitialLoadConfiguration(i));
    }

    public void a(Shop shop) {
        Intent intent = new Intent(this.i, ShopSubActivity.class);
        intent.putExtra(ResponseConstants.SHOP, shop);
        b(intent);
    }

    public void e(EtsyId etsyId, Bundle bundle) {
        Intent intent = new Intent(this.i, ShopSubActivity.class);
        intent.putExtra("referral_args", bundle);
        intent.putExtra("shop_id", etsyId);
        b(intent);
    }

    public void a(Collection collection) {
        Intent intent = new Intent(this.i, CoreActivity.class);
        intent.putExtra(Collection.TYPE_COLLECTION, collection);
        b(intent);
    }

    public void a(VersionPromo versionPromo) {
        Intent intent = new Intent(this.i, VersionPromoActivity.class);
        intent.putExtra("version_promo", versionPromo);
        this.h = true;
        this.g = AnimationMode.FADE_SLOW;
        b(intent);
    }

    public void c(EtsyId etsyId) {
        Intent intent = new Intent(this.i, CoreActivity.class);
        intent.putExtra("user_id", etsyId);
        b(intent);
    }

    public void f(EtsyId etsyId, Bundle bundle) {
        Intent intent = new Intent(this.i, CoreActivity.class);
        intent.putExtra("user_id", etsyId);
        intent.putExtra("referral_args", bundle);
        b(intent);
    }

    public void d(EtsyId etsyId) {
        Intent intent = new Intent(this.i, CoreActivity.class);
        intent.putExtra(ResponseConstants.RECEIPT_ID, etsyId);
        b(intent);
    }

    public void e(EtsyId etsyId) {
        Intent intent = new Intent(this.i, CoreActivity.class);
        intent.putExtra("receipt_transaction_id", etsyId);
        b(intent);
    }

    public void g(EtsyId etsyId, Bundle bundle) {
        Intent intent = new Intent(this.i, CoreActivity.class);
        intent.putExtra(ResponseConstants.RECEIPT_ID, etsyId);
        intent.putExtra("type", false);
        intent.putExtra("referral_args", bundle);
        b(intent);
    }

    public void a(ArrayList<? extends BaseModelImage> arrayList, int i) {
        a(arrayList, i, false);
    }

    public void a(ArrayList<? extends BaseModelImage> arrayList, int i, boolean z) {
        a(new Intent(this.i, DetailedImageActivity.class), arrayList, i, z);
    }

    private void a(Intent intent, ArrayList<? extends BaseModelImage> arrayList, int i, boolean z) {
        intent.putExtra("image_list", arrayList);
        intent.putExtra("position", i);
        intent.putExtra("SHOW_THUMBNAILS", z);
        b(intent);
    }

    public void l() {
        b(new Intent(this.i, UserActivity.class));
    }

    public void m() {
        Intent intent = new Intent(this.i, SettingsActivity.class);
        this.h = true;
        this.g = AnimationMode.FADE_SLOW;
        b(intent);
    }

    public void n() {
        Intent intent = new Intent(this.i, NotificationSettingsActivity.class);
        this.h = true;
        this.g = AnimationMode.FADE_SLOW;
        b(intent);
    }

    public void o() {
        b(new Intent(this.i, PhabletsActivity.class));
    }

    public void p() {
        b(new Intent(this.i, FeedbackActivity.class));
    }

    public void q() {
        b(new Intent(this.i, SearchV2Activity.class));
    }

    public void a(String str, String str2, String str3) {
        Intent intent = new Intent(this.i, SearchV2Activity.class);
        intent.setAction("android.intent.action.SEARCH");
        intent.putExtra(ResponseConstants.QUERY, str);
        Bundle bundle = new Bundle();
        bundle.putString("ref", String.valueOf(str2));
        bundle.putString("as_prefix", str3);
        intent.putExtra("SEARCH_REQUEST_PARAMS", bundle);
        b(intent);
    }

    public void a(FindsUrl findsUrl) {
        TaxonomyNode taxonomyNode = findsUrl.getTaxonomyNode();
        String query = findsUrl.getQuery();
        String marketplace = findsUrl.getMarketplace();
        EtsyId anchorListingId = findsUrl.getAnchorListingId();
        String maxPrice = findsUrl.getMaxPrice();
        String minPrice = findsUrl.getMinPrice();
        Intent intent = new Intent(this.i, SearchV2Activity.class);
        if (!TextUtils.isEmpty(query)) {
            intent.setAction("android.intent.action.SEARCH");
            intent.putExtra(ResponseConstants.QUERY, query);
        }
        if (taxonomyNode != null) {
            intent.putExtra("SEARCH_TAXONOMY_NODE", d.a(taxonomyNode));
        }
        if (anchorListingId != null) {
            intent.putExtra("ANCHOR_LISTING_ID", anchorListingId.toString());
        }
        if (!TextUtils.isEmpty(marketplace)) {
            intent.putExtra("SEARCH_MARKETPLACE_NAME", marketplace);
        }
        if (!TextUtils.isEmpty(maxPrice)) {
            intent.putExtra("SEARCH_MAX_PRICE", maxPrice);
        }
        if (!TextUtils.isEmpty(minPrice)) {
            intent.putExtra("SEARCH_MIN_PRICE", minPrice);
        }
        b(intent);
    }

    public void a(@NonNull TaxonomyNode taxonomyNode, @Nullable String str) {
        Intent intent = new Intent(this.i, SearchV2Activity.class);
        intent.putExtra("SEARCH_TAXONOMY_NODE", d.a(taxonomyNode));
        intent.putExtra("SEARCH_TYPE", "SEARCH_TYPE_CATEGORY");
        if (str != null) {
            intent.putExtra("ANCHOR_LISTING_ID", str);
        }
        this.g = AnimationMode.SLIDE_RIGHT;
        b(intent);
    }

    public void a(@Nullable String str, @Nullable SearchOptions searchOptions, @Nullable TaxonomyNode taxonomyNode, @Nullable String str2, Bundle bundle) {
        new d().b(bundle);
        Intent intent = new Intent(this.i, SearchV2Activity.class);
        intent.setAction("android.intent.action.SEARCH");
        if (str != null) {
            intent.putExtra(ResponseConstants.QUERY, str);
        }
        if (searchOptions != null) {
            intent.putExtra("SEARCH_OPTIONS", searchOptions);
        }
        if (taxonomyNode != null) {
            intent.putExtra("SEARCH_TAXONOMY_NODE", d.a(taxonomyNode));
        }
        intent.putExtra("ANCHOR_LISTING_ID", str2);
        intent.putExtra("SEARCH_TYPE", "SEARCH_TYPE_LISTING");
        intent.putExtra("SEARCH_INITIATED_FROM_WITHIN_APP", false);
        this.g = AnimationMode.SLIDE_RIGHT;
        b(intent);
    }

    public void a(String str, HashMap<String, String> hashMap, @Nullable SearchOptions searchOptions) {
        Intent intent = new Intent(this.i, SearchV2Activity.class);
        intent.putExtra("SEARCH_CATEGORY_REDIRECT", str);
        intent.putExtra("SEARCH_CATEGORY_REDIRECT_QUERY_PARAMS", hashMap);
        intent.putExtra("SEARCH_INITIATED_FROM_WITHIN_APP", false);
        if (searchOptions != null) {
            intent.putExtra("SEARCH_OPTIONS", searchOptions);
        }
        this.g = AnimationMode.SLIDE_RIGHT;
        b(intent);
    }

    public void r() {
        b((String) null);
    }

    public void b(@Nullable String str) {
        Intent intent = new Intent();
        intent.setClass(this.i, CartWithSavedActivity.class);
        if (str != null) {
            intent.putExtra(ResponseConstants.PAGE_LINK, str);
        }
        b(intent);
    }

    public void a(@Nullable AndroidPayDataContract androidPayDataContract, EtsyId etsyId, boolean z) {
        Intent intent = new Intent();
        intent.setClass(this.i, CartWithSavedActivity.class);
        if (etsyId != null && etsyId.hasId()) {
            intent.putExtra(CartWithSavedActivity.CHECKED_OUT_CART, androidPayDataContract);
            intent.putExtra(CartWithSavedActivity.LAST_ORDER_ID, etsyId);
            intent.putExtra("should_show_social_invites_prompt", z);
        }
        b(intent);
    }

    public void a(int i, ServerDrivenAction serverDrivenAction) {
        Intent intent = new Intent();
        intent.setClass(this.i, CartVariationSelectActivity.class);
        intent.putExtra("cart_action_position", i);
        intent.putExtra("cart_action", d.a(serverDrivenAction));
        this.g = AnimationMode.FADE_SLOW;
        b(intent);
    }

    public void a(Listing listing, String str, @Nullable Offering offering) {
        Intent intent = new Intent(this.i, SingleListingCheckoutActivity.class);
        intent.putExtra("listing", listing);
        intent.putExtra("quantity", str);
        if (offering != null) {
            intent.putExtra(ResponseConstants.OFFERING_ID, offering.getOfferingId().getId());
        }
        this.h = true;
        this.g = AnimationMode.FADE_SLOW;
        b(intent);
    }

    public void a(Listing listing, SingleListingCart singleListingCart, String str, @Nullable Offering offering) {
        Intent intent = new Intent(this.i, SingleListingCheckoutActivity.class);
        intent.putExtra("listing", listing);
        intent.putExtra("single_listing_cart", singleListingCart);
        intent.putExtra("quantity", str);
        if (offering != null) {
            intent.putExtra(ResponseConstants.OFFERING_ID, offering.getOfferingId().getId());
        }
        this.h = true;
        this.g = AnimationMode.FADE_SLOW;
        b(intent);
    }

    public void s() {
        c(false);
    }

    public void c(boolean z) {
        this.k = true;
        Intent intent = new Intent();
        intent.putExtra("HOME_RESET", z);
        intent.setClass(this.i, HomescreenTabsActivity.class);
        b(intent);
    }

    public void b(Bundle bundle) {
        this.k = true;
        Intent intent = new Intent();
        intent.setClass(this.i, HomescreenTabsActivity.class);
        intent.putExtras(bundle);
        b(intent);
    }

    public void c(String str) {
        this.k = true;
        Intent intent = new Intent();
        intent.setClass(this.i, HomescreenTabsActivity.class);
        intent.putExtra(ResponseConstants.PAGE_LINK, str);
        b(intent);
    }

    public void d(String str) {
        Intent intent = new Intent(this.i, LandingPageActivity.class);
        LandingPageLink landingPageLink = new LandingPageLink();
        landingPageLink.setPageTitle(this.i.getString(R.string.similar_items));
        boolean e = v.a().e();
        landingPageLink.setApiPath(String.format("/etsyapps/v3/public/listings/%s/similar/cards", new Object[]{str}));
        landingPageLink.setEventName(ResponseConstants.SIMILAR_LISTINGS);
        intent.putExtra(ResponseConstants.PAGE_LINK, d.a(landingPageLink));
        b(intent);
    }

    public void b(int i) {
        Intent intent = new Intent();
        intent.setClass(this.i, EtsyWebActivity.class);
        intent.putExtra("type", i);
        b(intent);
    }

    public void a(@Nullable LocalBrowseModule localBrowseModule, boolean z) {
        Intent intent = new Intent(this.i, LocalSearchActivity.class);
        intent.putExtra(LocalSearchActivity.START_FULLSCREEN_MAP, z);
        if (localBrowseModule != null) {
            intent.putExtra("local_browse_module", d.a(localBrowseModule));
            if (localBrowseModule.getLandingPage() != null) {
                com.etsy.android.lib.logger.legacy.b.a().a(localBrowseModule.getLandingPage().getAnalyticsEventName());
            }
        } else {
            com.etsy.android.lib.logger.legacy.b.a().a("local_browse_nearby");
        }
        b(intent);
    }

    public void a(EtsyId etsyId, boolean z) {
        Intent intent = new Intent(this.i, LocalEventActivity.class);
        intent.putExtra(ResponseConstants.LOCAL_MARKET_ID, etsyId);
        intent.putExtra("show_local_browse_link", z);
        b(intent);
    }

    public void a(LocalMarket localMarket, boolean z) {
        Intent intent = new Intent(this.i, LocalEventActivity.class);
        intent.putExtra(ResponseConstants.LOCAL_MARKET, d.a(localMarket));
        intent.putExtra("show_local_browse_link", z);
        b(intent);
    }

    public void a(Attendee attendee, LocalMarket localMarket) {
        Intent intent = new Intent(this.i, LocalDatesAttendingActivity.class);
        intent.putExtra("attendee", attendee);
        intent.putExtra(ResponseConstants.LOCAL_MARKET, localMarket);
        this.g = AnimationMode.SLIDE_BOTTOM;
        b(intent);
    }

    public void a(LocalMarket localMarket) {
        Intent intent = new Intent(this.i, LocalStoreInfoDetailsActivity.class);
        intent.putExtra(ResponseConstants.LOCAL_MARKET, localMarket);
        this.g = AnimationMode.SLIDE_BOTTOM;
        b(intent);
    }

    public void e(String str) {
        Intent intent = new Intent();
        intent.setClass(this.i, EtsyWebActivity.class);
        intent.putExtra("type", 3);
        intent.putExtra("url", str);
        b(intent);
    }

    public void b(EtsyId etsyId, EtsyId etsyId2) {
        Intent intent = new Intent(this.i, CoreActivity.class);
        intent.setAction("android.intent.action.VIEW");
        intent.putExtra(ResponseConstants.RECEIPT_ID, etsyId);
        Intent intent2 = new Intent();
        intent2.setClass(this.i, EtsyWebActivity.class);
        intent2.putExtra("type", 3);
        intent2.putExtra(ResponseConstants.RECEIPT_ID, etsyId);
        intent2.putExtra(ResponseConstants.RECEIPT_SHIPPING_ID, etsyId2);
        TaskStackBuilder create = TaskStackBuilder.create(this.i);
        create.addNextIntentWithParentStack(intent);
        create.addNextIntent(intent2);
        create.startActivities();
    }

    public void a(AndroidPayDataContract androidPayDataContract, boolean z) {
        com.etsy.android.ui.cart.a.a(b(), androidPayDataContract);
        Intent intent = new Intent();
        intent.setClass(this.i, EtsyWebActivity.class);
        intent.putExtra(CartWithSavedActivity.CHECKED_OUT_CART, androidPayDataContract);
        intent.putExtra(CartWithSavedActivity.CHECKED_OUT_IS_MSCO, z);
        intent.putExtra("type", 2);
        this.g = AnimationMode.SLIDE_BOTTOM;
        b(intent);
    }

    public void a(@NonNull String str, @NonNull String str2) {
        Intent intent = new Intent();
        intent.setClass(this.i, EtsyWebActivity.class);
        intent.putExtra(CartWithSavedActivity.CHECKED_OUT_CART_GROUP_ID, str);
        intent.putExtra(CartWithSavedActivity.CHECKED_OUT_PAYMENT_METHOD, str2);
        intent.putExtra(CartWithSavedActivity.CHECKED_OUT_IS_MSCO, true);
        intent.putExtra("type", 2);
        this.g = AnimationMode.SLIDE_BOTTOM;
        b(intent);
    }

    public void a(@NonNull Listing listing, @NonNull String str, @NonNull PaymentOption paymentOption, @Nullable String str2) {
        Intent intent = new Intent();
        intent.setClass(this.i, EtsyWebActivity.class);
        intent.putExtra(SingleListingCheckoutActivity.CHECKED_OUT_SINGLE_LISTING, true);
        intent.putExtra("type", 2);
        intent.putExtra("listing_id", listing.getListingId().getId());
        intent.putExtra("quantity", str);
        intent.putExtra("payment_option", paymentOption);
        if (!TextUtils.isEmpty(str2)) {
            intent.putExtra(ResponseConstants.OFFERING_ID, str2);
        } else {
            intent.putExtra(SingleListingCheckoutActivity.PARAM_LISTING_VARIATION, listing.getSelectedVariations());
        }
        this.g = AnimationMode.SLIDE_BOTTOM;
        b(intent);
    }

    public void a(@NonNull String str, @NonNull String str2, @Nullable Image image) {
        Intent intent = new Intent(this.i, GiftWrapDescriptionActivity.class);
        intent.putExtra(ResponseConstants.SHOP_NAME, str);
        intent.putExtra("gift_info_description", str2);
        intent.putExtra("gift_info_preview", image);
        this.g = AnimationMode.FADE_IN_OUT;
        b(intent);
    }

    public void a(@NonNull AndroidPayDataContract androidPayDataContract, @NonNull MaskedWallet maskedWallet, boolean z) {
        com.etsy.android.ui.cart.a.a(b(), androidPayDataContract);
        Intent intent = new Intent();
        intent.setClass(this.i, EtsyWebActivity.class);
        intent.putExtra("type", 2);
        intent.putExtra(CartWithSavedActivity.CHECKED_OUT_CART, androidPayDataContract);
        intent.putExtra(CartWithSavedActivity.CHECKED_OUT_IS_MSCO, z);
        intent.putExtra("android_pay_masked_wallet", maskedWallet);
        this.g = AnimationMode.SLIDE_BOTTOM;
        b(intent);
    }

    public void a(@NonNull AndroidPayDataContract androidPayDataContract, @NonNull MaskedWallet maskedWallet, @NonNull Listing listing, @NonNull String str, @Nullable String str2) {
        PaymentOption paymentOption = new PaymentOption();
        paymentOption.setPaymentMethod("android_pay");
        Intent intent = new Intent();
        intent.setClass(this.i, EtsyWebActivity.class);
        intent.putExtra("type", 2);
        intent.putExtra(SingleListingCheckoutActivity.CHECKED_OUT_SINGLE_LISTING, true);
        intent.putExtra("listing_id", listing.getListingId().getId());
        intent.putExtra("quantity", str);
        intent.putExtra("payment_option", paymentOption);
        intent.putExtra(CartWithSavedActivity.CHECKED_OUT_CART, androidPayDataContract);
        intent.putExtra("android_pay_masked_wallet", maskedWallet);
        if (!TextUtils.isEmpty(str2)) {
            intent.putExtra(ResponseConstants.OFFERING_ID, str2);
        } else {
            intent.putExtra(SingleListingCheckoutActivity.PARAM_LISTING_VARIATION, listing.getSelectedVariations());
        }
        this.g = AnimationMode.SLIDE_BOTTOM;
        b(intent);
    }

    public void f(String str) {
        try {
            if (n.c(new URL(str).getHost())) {
                Intent intent = new Intent();
                intent.setClass(this.i, EtsyWebActivity.class);
                intent.putExtra("type", 11);
                intent.putExtra("url", str);
                b(intent);
            }
        } catch (MalformedURLException unused) {
        }
    }

    public void t() {
        Intent intent = new Intent();
        intent.setClass(this.i, EtsyPreferenceActivity.class);
        b(intent);
    }

    public void c(Bundle bundle) {
        Intent intent = new Intent(this.i, ListingCollectionsActivity.class);
        intent.putExtras(bundle);
        a(intent);
    }

    public void a(ListingLike listingLike) {
        Intent intent = new Intent(this.i, ListingCollectionsActivity.class);
        intent.putExtra("listing", listingLike);
        a(intent);
    }

    /* access modifiers changed from: protected */
    public void a(Intent intent) {
        this.h = true;
        this.g = AnimationMode.FADE_SLOW;
        b(intent);
    }

    public void b(Collection collection) {
        Intent intent = new Intent(this.i, CollectionEditActivity.class);
        intent.putExtra(Collection.TYPE_COLLECTION, collection);
        this.h = true;
        this.g = AnimationMode.FADE_SLOW;
        b(intent);
    }

    public void a(LandingPageInfo landingPageInfo) {
        Intent intent = new Intent(this.i, LandingPageActivity.class);
        intent.putExtra(ResponseConstants.PAGE_LINK, d.a(landingPageInfo));
        b(intent);
    }

    public void b(LandingPageInfo landingPageInfo) {
        Intent intent = new Intent(this.i, RecentlyViewedListingsActivity.class);
        intent.putExtra(ResponseConstants.PAGE_LINK, d.a(landingPageInfo));
        b(intent);
    }

    public void c(LandingPageInfo landingPageInfo) {
        Intent intent = new Intent(this.i, ShopSharePageActivity.class);
        intent.putExtra(ResponseConstants.PAGE_LINK, d.a(landingPageInfo));
        b(intent);
    }

    public void d(@NonNull Bundle bundle) {
        Intent intent;
        if (v.a().e()) {
            intent = new Intent(this.i, ConvoBaseActivity.class);
        } else {
            intent = new Intent(this.i, HomescreenTabsActivity.class);
        }
        intent.setAction("android.intent.action.VIEW");
        Intent intent2 = new Intent();
        if (l.c(a())) {
            intent2.setClass(this.i, ConvoComposeDialogActivity.class);
            this.h = true;
        } else {
            intent2.setClass(this.i, ConvoComposeActivity.class);
            this.g = AnimationMode.SLIDE_BOTTOM;
        }
        intent2.putExtras(bundle);
        TaskStackBuilder create = TaskStackBuilder.create(this.i);
        create.addNextIntentWithParentStack(intent);
        create.addNextIntent(intent2);
        create.startActivities();
    }

    public void e(Bundle bundle) {
        Intent intent = new Intent();
        intent.putExtras(bundle);
        d(intent);
    }

    public void u() {
        d(new Intent());
    }

    private void d(Intent intent) {
        if (l.c(a())) {
            intent.setClass(this.i, ConvoComposeDialogActivity.class);
            this.h = true;
        } else {
            intent.setClass(this.i, ConvoComposeActivity.class);
            this.g = AnimationMode.SLIDE_BOTTOM;
        }
        b(intent);
    }

    public void v() {
        Intent intent = new Intent();
        if (a.a().d().c(com.etsy.android.lib.config.b.ca)) {
            intent.setClass(this.i, ConvoListActivity.class);
        } else {
            intent.setClass(this.i, ConvoBaseActivity.class);
        }
        b(intent);
    }

    public void a(Conversation conversation, boolean z) {
        Intent intent = new Intent(this.i, ConvoViewActivity.class);
        intent.putExtra("conversation", conversation);
        intent.putExtra("convo_change_read_state", z);
        b(intent);
    }

    public void g(String str) {
        Intent intent = new Intent(this.i, ConvoBaseActivity.class);
        intent.putExtra("convo_id", str);
        b(intent);
    }

    public void a(long j, boolean z) {
        Intent intent = new Intent(this.i, ConvoActivity.class);
        intent.putExtra("convo_id", j);
        intent.putExtra("convo_change_read_state", z);
        b(intent);
    }

    public void h(String str) {
        Intent intent = new Intent();
        intent.setClass(this.i, ConvoListActivity.class);
        intent.putExtra("convo_id", str);
        b(intent);
    }

    public void a(ManufacturerProject manufacturerProject) {
        Intent intent = new Intent(this.i, ManufacturerProjectActivity.class);
        intent.putExtra("manufacturer_project", d.a(manufacturerProject));
        b(intent);
    }

    public void a(EtsyId etsyId, int i) {
        a(etsyId, i, (String) null);
    }

    public void a(EtsyId etsyId, int i, String str) {
        Intent intent = new Intent(this.i, FavoritesActivity.class);
        intent.putExtra("user_id", etsyId);
        intent.putExtra("type", i);
        if (af.a(str)) {
            intent.putExtra(ResponseConstants.USERNAME, str);
        }
        b(intent);
    }

    public void a(EtsyId etsyId, String str, int i) {
        Intent intent = new Intent(this.i, CirclesActivity.class);
        intent.putExtra("user_id", etsyId);
        intent.putExtra(ResponseConstants.USERNAME, str);
        intent.putExtra("type", i);
        b(intent);
    }

    public void w() {
        b(new Intent(this.i, PurchasesActivity.class));
    }

    public void a(String str, SignInFlow signInFlow) {
        Intent intent = new Intent(this.i, ForgotPasswordDialogActivity.class);
        intent.putExtra(ResponseConstants.USERNAME, str);
        intent.putExtra("sign_in_flow", signInFlow);
        this.h = true;
        b(intent);
    }

    public void f(EtsyId etsyId) {
        Intent y = y();
        y.putExtra(ResponseConstants.TRANSACTION_ID, etsyId);
        b(y);
    }

    private Intent y() {
        if (l.c(a())) {
            Intent intent = new Intent(this.i, LeaveFeedbackDialogActivity.class);
            this.h = true;
            this.g = AnimationMode.FADE_SLOW;
            return intent;
        }
        Intent intent2 = new Intent(this.i, LeaveFeedbackActivity.class);
        this.g = AnimationMode.SLIDE_BOTTOM;
        return intent2;
    }

    public void f(Bundle bundle) {
        this.h = true;
        this.g = AnimationMode.FADE_SLOW;
        Intent intent = new Intent(this.i, ShareFeedbackDialogActivity.class);
        intent.putExtras(bundle);
        b(intent);
    }

    public void a(String str, String str2, String str3, String str4) {
        Intent intent = new Intent(this.i, ShareDialogActivity.class);
        intent.putExtra(ResponseConstants.SUBJECT, str);
        intent.putExtra("text", str2);
        intent.putExtra("url", str3);
        intent.putExtra(ResponseConstants.IMAGE_URL, str4);
        this.h = true;
        b(intent);
    }

    public void a(ShopShareable shopShareable) {
        Intent intent = new Intent(this.i, SocialShareDialogActivity.class);
        intent.putExtra("shop2", d.a(shopShareable));
        this.h = true;
        b(intent);
    }

    public void a(ShopLike shopLike) {
        Intent intent = new Intent(this.i, SocialShareDialogActivity.class);
        intent.putExtra(ResponseConstants.SHOP, shopLike);
        this.h = true;
        b(intent);
    }

    public void a(BasicListingLike basicListingLike) {
        Intent intent = new Intent(this.i, SocialShareDialogActivity.class);
        intent.putExtra("listing", basicListingLike);
        this.h = true;
        b(intent);
    }

    public void a(ListingCollection listingCollection, ListingLike listingLike) {
        Intent intent = new Intent(this.i, SocialShareDialogActivity.class);
        intent.putExtra("listing", listingLike);
        intent.putExtra(Collection.TYPE_COLLECTION, listingCollection);
        this.h = true;
        b(intent);
    }

    public void a(AppreciationPhotoLike appreciationPhotoLike) {
        Intent intent = new Intent(this.i, SocialShareDialogActivity.class);
        intent.putExtra(ResponseConstants.APPRECIATION_PHOTO, appreciationPhotoLike);
        this.h = true;
        b(intent);
    }

    public void b(LocalMarket localMarket) {
        Intent intent = new Intent(this.i, SocialShareDialogActivity.class);
        intent.putExtra(ResponseConstants.LOCAL_MARKET, d.a(localMarket));
        this.h = true;
        b(intent);
    }

    public void a(EtsyId etsyId, String str) {
        Intent intent = new Intent(this.i, ShopAboutVideoActivity.class);
        intent.putExtra("video_url", str);
        intent.putExtra("shop_id", etsyId);
        b().a("shop_about_video_tapped", new EtsyActivityNavigator$2(this, etsyId));
        b(intent);
    }

    public void a(@NonNull Uri uri, @NonNull Uri uri2, @Nullable Options options) {
        if (options == null) {
            options = new Options();
        }
        Intent intent = new Intent(this.i, CropImageActivity.class);
        intent.putExtra("source_uri", uri.toString());
        intent.putExtra("dest_uri", uri2.toString());
        intent.putExtra(ResponseConstants.OPTIONS, d.a(options));
        b(intent);
    }

    public void g(EtsyId etsyId) {
        Intent intent = new Intent(this.i, AppreciationPhotoLandingPageActivity.class);
        intent.putExtra(ResponseConstants.TRANSACTION_ID, etsyId);
        b(intent);
    }

    public void b(String str, String str2) {
        this.h = true;
        this.g = AnimationMode.FADE_SLOW;
        Intent intent = new Intent(this.i, TextInfoActivity.class);
        intent.putExtra("title", str);
        intent.putExtra("text", str2);
        b(intent);
    }

    public void a(String str, String str2, boolean z) {
        Intent intent = new Intent(this.i, FindsActivity.class);
        intent.putExtra("finds_slug", str);
        if (!TextUtils.isEmpty(str2)) {
            intent.putExtra("ANCHOR_LISTING_ID", str2);
        }
        intent.putExtra("finds_is_draft", z);
        b(intent);
    }

    public void i(String str) {
        Intent intent = new Intent(this.i, BOEVespaDemoActivity.class);
        if (!TextUtils.isEmpty(str)) {
            intent.putExtra(BOEVespaDemoActivity.MOCK_FILE_NAME, str);
        }
        b(intent);
    }

    public void x() {
        b(new Intent(this.i, CustomViewDemoActivity.class));
    }

    public void j(String str) {
        Intent intent = new Intent(this.i, NotificationActivity.class);
        intent.setData(Uri.parse(str));
        b(intent);
    }

    public void b(String str, String str2, String str3) {
        Intent intent = new Intent(this.i, ExploreActivity.class);
        intent.putExtra("data", new HomescreenTab(str, str2, str3));
        b(intent);
    }

    /* access modifiers changed from: protected */
    public void b(Intent intent) {
        boolean z;
        if (this.m != null && !intent.hasExtra("referral_args")) {
            intent.putExtra("referral_args", this.m);
        }
        if (this.h && this.g != AnimationMode.FADE_SLOW) {
            this.g = AnimationMode.FADE_SLOW;
        }
        Intent c = c(intent);
        if (this.e > 0) {
            if (this.j != null) {
                this.j.startActivityForResult(c, this.e);
            } else {
                if (this.c && !this.k && !this.i.getClass().equals(HomescreenTabsActivity.class)) {
                    Intent intent2 = new Intent(this.i, HomescreenTabsActivity.class);
                    intent2.addFlags(67108864);
                    this.i.startActivity(a(intent2, AnimationMode.DEFAULT_OUT));
                } else if (this.c) {
                    c.addFlags(67108864);
                }
                this.i.startActivityForResult(c, this.e);
            }
            a(this.i, this.g);
            return;
        }
        if (!this.c || this.k || this.i.getClass().equals(HomescreenTabsActivity.class)) {
            if (this.c) {
                c.addFlags(67108864);
            }
            e(c);
        } else {
            Intent intent3 = new Intent(this.i, HomescreenTabsActivity.class);
            intent3.addFlags(67108864);
            Intent a = a(intent3, AnimationMode.DEFAULT_OUT);
            if (k.b()) {
                z = false;
            } else {
                z = ActivityCompat.startActivities(this.i, a(c, a));
            }
            if (!z) {
                this.i.startActivity(a);
                e(c);
            }
        }
        a(this.i, this.g);
    }

    private void e(Intent intent) {
        if (this.l == null || this.l.length <= 0) {
            this.i.startActivity(intent);
            return;
        }
        TaskStackBuilder create = TaskStackBuilder.create(this.i);
        for (Intent addNextIntent : f(intent)) {
            create.addNextIntent(addNextIntent);
        }
        create.startActivities();
    }

    private Intent[] f(Intent intent) {
        return a(intent, (Intent) null);
    }

    private Intent[] a(Intent intent, Intent intent2) {
        ArrayList arrayList = new ArrayList();
        if (intent2 != null) {
            arrayList.add(intent2);
        }
        if (this.l != null && this.l.length > 0) {
            Collections.addAll(arrayList, this.l);
        }
        if (intent != null) {
            arrayList.add(intent);
        }
        return (Intent[]) arrayList.toArray(new Intent[arrayList.size()]);
    }

    /* access modifiers changed from: protected */
    public void a(Class<? extends BaseDialogFragment> cls, @StringRes int i, Bundle bundle) {
        a(EtsyDialogLauncherActivity.class, cls, i, bundle);
    }
}
