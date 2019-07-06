package com.contextlogic.wish.link;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.browse.BrowseActivity;
import com.contextlogic.wish.activity.cart.CartActivity;
import com.contextlogic.wish.activity.cart.commerceloan.CommerceLoanCartActivity;
import com.contextlogic.wish.activity.commercecash.CommerceCashActivity;
import com.contextlogic.wish.activity.feed.brand.BrandFeedActivity;
import com.contextlogic.wish.activity.feed.search.SearchFeedActivity;
import com.contextlogic.wish.activity.feed.tag.TagFeedActivity;
import com.contextlogic.wish.activity.identityverification.IdentityVerificationActivity;
import com.contextlogic.wish.activity.managepayments.ManagePaymentsActivity;
import com.contextlogic.wish.activity.merchantprofile.MerchantProfileActivity;
import com.contextlogic.wish.activity.notifications.NotificationsActivity;
import com.contextlogic.wish.activity.pricewatch.PriceWatchActivity;
import com.contextlogic.wish.activity.productdetails.ProductDetailsActivity;
import com.contextlogic.wish.activity.profile.ProfileActivity;
import com.contextlogic.wish.activity.profile.update.UpdateProfileActivity;
import com.contextlogic.wish.activity.profile.wishlist.WishlistActivity;
import com.contextlogic.wish.activity.rewards.RewardsActivity;
import com.contextlogic.wish.activity.search.SearchActivity;
import com.contextlogic.wish.activity.settings.SettingsActivity;
import com.contextlogic.wish.activity.settings.accountsettings.AccountSettingsActivity;
import com.contextlogic.wish.activity.settings.changecurrency.ChangeCurrencyActivity;
import com.contextlogic.wish.activity.settings.changeemail.ChangeEmailActivity;
import com.contextlogic.wish.activity.settings.changepassword.ChangePasswordActivity;
import com.contextlogic.wish.activity.settings.changephonenumber.ChangePhoneNumberActivity;
import com.contextlogic.wish.activity.settings.datacontrol.DataControlSettingsActivity;
import com.contextlogic.wish.activity.settings.notifications.EmailSettingsActivity;
import com.contextlogic.wish.activity.settings.notifications.NotificationSettingsActivity;
import com.contextlogic.wish.activity.settings.push.PushNotificationSettingsActivity;
import com.contextlogic.wish.activity.share.ShareActivity;
import com.contextlogic.wish.activity.signup.SignupFreeGift.SignupFreeGiftActivity;
import com.contextlogic.wish.activity.webview.KlarnaWebViewActivity;
import com.contextlogic.wish.activity.webview.WebViewActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishBrandFilter;
import com.contextlogic.wish.api.model.WishNotification;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.util.IntentUtil;
import com.contextlogic.wish.util.StoreUtil;

public class DeepLinkManager {
    private static DeepLinkManager sInstance = new DeepLinkManager();
    private boolean mAppLaunchDeepLinkSent;
    private DeepLink mLastAppLaunchDeepLink;

    private DeepLinkManager() {
    }

    public static DeepLinkManager getInstance() {
        return sInstance;
    }

    public static boolean processDeepLink(BaseActivity baseActivity, DeepLink deepLink) {
        return processDeepLink(baseActivity, deepLink, true);
    }

    public static boolean processDeepLink(BaseActivity baseActivity, DeepLink deepLink, boolean z) {
        return processDeepLink(baseActivity, deepLink, z, null);
    }

    public static boolean processDeepLink(BaseActivity baseActivity, DeepLink deepLink, boolean z, WishNotification wishNotification) {
        Intent intent = getIntent(deepLink, z, wishNotification);
        if (intent == null) {
            return false;
        }
        baseActivity.startActivityForResult(intent, 999);
        return true;
    }

    public static Intent getIntent(DeepLink deepLink, boolean z, WishNotification wishNotification) {
        String str;
        PackageManager packageManager = WishApplication.getInstance().getPackageManager();
        Intent intent = null;
        switch (deepLink.getTargetType()) {
            case RATE_APP:
                intent = new Intent("android.intent.action.VIEW", StoreUtil.getStoreUri());
                break;
            case SEND_EMAIL:
                Intent intent2 = new Intent("android.intent.action.SEND");
                intent2.setType("message/rfc822");
                if (deepLink.getToEmail() != null) {
                    intent2.putExtra("android.intent.extra.EMAIL", new String[]{deepLink.getToEmail()});
                }
                if (deepLink.getBodyText() != null) {
                    intent2.putExtra("android.intent.extra.TEXT", deepLink.getBodyText());
                }
                if (deepLink.getSubjectText() != null) {
                    intent2.putExtra("android.intent.extra.SUBJECT", deepLink.getSubjectText());
                }
                if (intent2.resolveActivity(packageManager) != null) {
                    intent = intent2;
                    break;
                }
                break;
            case WEBSITE:
                if (z) {
                    intent = new Intent();
                    intent.setClass(WishApplication.getInstance(), WebViewActivity.class);
                    intent.putExtra("ExtraUrl", deepLink.getWebUrl());
                    break;
                }
                break;
            case OTHER_APP:
                intent = new Intent("android.intent.action.VIEW", deepLink.getLinkUri());
                if (intent.resolveActivity(packageManager) == null) {
                    String lowerCase = deepLink.getLinkUri().getScheme().toLowerCase();
                    Resources resources = WishApplication.getInstance().getResources();
                    StringBuilder sb = new StringBuilder();
                    sb.append("package_name_");
                    sb.append(lowerCase);
                    int identifier = resources.getIdentifier(sb.toString(), "string", WishApplication.getInstance().getPackageName());
                    if (identifier != 0) {
                        str = WishApplication.getInstance().getString(identifier);
                    } else {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("com.contextlogic.");
                        sb2.append(lowerCase);
                        str = sb2.toString();
                    }
                    intent = new Intent("android.intent.action.VIEW", StoreUtil.getStoreUri(str));
                    break;
                }
                break;
            case CHANGE_PASSWORD:
                intent = new Intent();
                intent.setClass(WishApplication.getInstance(), ChangePasswordActivity.class);
                break;
            case CHANGE_EMAIL:
                intent = new Intent();
                intent.setClass(WishApplication.getInstance(), ChangeEmailActivity.class);
                break;
            case CHANGE_CURRENCY:
                intent = new Intent();
                intent.setClass(WishApplication.getInstance(), ChangeCurrencyActivity.class);
                break;
            case CHANGE_PHONE_NUMBER:
                intent = new Intent();
                intent.setClass(WishApplication.getInstance(), ChangePhoneNumberActivity.class);
                break;
            case NOTIFICATIONS:
                intent = new Intent();
                intent.setClass(WishApplication.getInstance(), NotificationsActivity.class);
                break;
            case NOTIFICATION_SETTINGS:
                intent = new Intent();
                intent.setClass(WishApplication.getInstance(), NotificationSettingsActivity.class);
                break;
            case EMAIL_NOTIFICATION_SETTINGS:
                intent = new Intent();
                intent.setClass(WishApplication.getInstance(), EmailSettingsActivity.class);
                break;
            case ACCOUNT_SETTINGS:
                intent = new Intent();
                intent.setClass(WishApplication.getInstance(), AccountSettingsActivity.class);
                break;
            case PUSH_NOTIFICATION_SETTINGS:
                intent = new Intent();
                intent.setClass(WishApplication.getInstance(), PushNotificationSettingsActivity.class);
                break;
            case DATA_CONTROL_SETTINGS:
                intent = new Intent();
                intent.setClass(WishApplication.getInstance(), DataControlSettingsActivity.class);
                break;
            case SETTINGS:
                intent = new Intent();
                intent.setClass(WishApplication.getInstance(), SettingsActivity.class);
                break;
            case INVITE_FRIENDS:
                intent = new Intent();
                intent.setClass(WishApplication.getInstance(), ShareActivity.class);
                intent.putExtra(ShareActivity.EXTRA_USE_DEFAULT_INVITE_MESSAGE, true);
                break;
            case UPDATE_PROFILE:
                intent = new Intent();
                intent.setClass(WishApplication.getInstance(), UpdateProfileActivity.class);
                break;
            case MERCHANT_FEED:
                intent = new Intent();
                intent.setClass(WishApplication.getInstance(), MerchantProfileActivity.class);
                intent.putExtra(MerchantProfileActivity.EXTRA_MERCHANT, deepLink.getMerchant());
                intent.putExtra(MerchantProfileActivity.EXTRA_MERCHANT_ID, deepLink.getMerchant());
                break;
            case PROFILE:
                intent = new Intent();
                intent.setClass(WishApplication.getInstance(), ProfileActivity.class);
                intent.putExtra(ProfileActivity.EXTRA_USER_ID, deepLink.getUserId());
                break;
            case SEARCH:
                intent = new Intent();
                intent.setClass(WishApplication.getInstance(), SearchActivity.class);
                break;
            case SEARCH_FEED:
                intent = new Intent();
                intent.setClass(WishApplication.getInstance(), SearchFeedActivity.class);
                intent.putExtra(SearchFeedActivity.EXTRA_QUERY, deepLink.getQuery());
                break;
            case BRAND_FEED:
                intent = new Intent();
                intent.setClass(WishApplication.getInstance(), BrandFeedActivity.class);
                WishBrandFilter wishBrandFilter = new WishBrandFilter(deepLink.getBrand(), deepLink.getTitle(), deepLink.getPrice(), deepLink.getTagId(), deepLink.getProducts(), deepLink.getCredit(), false);
                IntentUtil.putParcelableExtra(intent, BrandFeedActivity.EXTRA_BRAND_FILTER, wishBrandFilter);
                break;
            case CHANGE_PROFILE_PICTURE:
                intent = new Intent();
                intent.setClass(WishApplication.getInstance(), ProfileActivity.class);
                intent.putExtra(ProfileActivity.EXTRA_CHANGE_PROFILE_PICTURE, true);
                break;
            case CART:
                if (!deepLink.getKlarnaPayPal() && !deepLink.getShowCartError()) {
                    intent = new Intent();
                    intent.setClass(WishApplication.getInstance(), CartActivity.class);
                    if (!(deepLink.getProductId() == null || deepLink.getVariationId() == null)) {
                        intent.putExtra(CartActivity.EXTRA_ADD_TO_CART_PRODUCT_ID, deepLink.getProductId());
                        intent.putExtra(CartActivity.EXTRA_ADD_TO_CART_VARIATION_ID, deepLink.getVariationId());
                        break;
                    }
                } else {
                    intent = new Intent();
                    intent.setClass(WishApplication.getInstance(), KlarnaWebViewActivity.class);
                    intent.putExtra(CartActivity.EXTRA_CHOSE_PAYPAL_FROM_KLARNA, deepLink.getKlarnaPayPal());
                    intent.putExtra(CartActivity.EXTRA_SHOW_CART_ERROR, deepLink.getShowCartError());
                    break;
                }
                break;
            case COMMERCE_LOAN_CART:
                intent = new Intent();
                intent.setClass(WishApplication.getInstance(), CommerceLoanCartActivity.class);
                break;
            case BROWSE:
                intent = new Intent();
                if (deepLink.getTagId() == null) {
                    intent.setClass(WishApplication.getInstance(), BrowseActivity.class);
                    if (deepLink.getCategoryId() != null) {
                        intent.putExtra("ExtraCategoryId", deepLink.getCategoryId());
                        break;
                    }
                } else {
                    intent.setClass(WishApplication.getInstance(), TagFeedActivity.class);
                    intent.putExtra(TagFeedActivity.EXTRA_TAG_ID, deepLink.getTagId());
                    break;
                }
                break;
            case REWARDS:
                intent = new Intent();
                if (!ExperimentDataCenter.getInstance().turnOffRewards()) {
                    intent.setClass(WishApplication.getInstance(), RewardsActivity.class);
                    break;
                } else {
                    intent.setClass(WishApplication.getInstance(), BrowseActivity.class);
                    break;
                }
            case FEED:
                intent = new Intent();
                intent.setClass(WishApplication.getInstance(), BrowseActivity.class);
                if (deepLink.getCategoryId() != null) {
                    intent.putExtra("ExtraCategoryId", deepLink.getCategoryId());
                    break;
                }
                break;
            case PRODUCT:
                intent = new Intent();
                intent.setClass(WishApplication.getInstance(), ProductDetailsActivity.class);
                ProductDetailsActivity.addInitialProduct(intent, new WishProduct(deepLink.getProductId()));
                if (deepLink.getExtraParams() != null) {
                    intent.putExtra("ArgExtraInfo", deepLink.getExtraParams());
                }
                String productRatingId = deepLink.getProductRatingId();
                if (productRatingId != null) {
                    intent.putExtra("ArgExtraProductRatingId", productRatingId);
                    break;
                }
                break;
            case SIGNUP_FREE_GIFT:
                intent = new Intent();
                intent.putExtra("ArgStartedFomNotification", true);
                intent.setClass(WishApplication.getInstance(), SignupFreeGiftActivity.class);
                break;
            case EXTERNAL_LINK:
                intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse(deepLink.getQuery()));
                break;
            case GET_GIVE_COUPON:
                intent = new Intent();
                intent.setClass(WishApplication.getInstance(), ShareActivity.class);
                intent.putExtra(ShareActivity.EXTRA_USE_DEFAULT_INVITE_MESSAGE, true);
                break;
            case COMMERCE_CASH:
                intent = new Intent();
                intent.setClass(WishApplication.getInstance(), CommerceCashActivity.class);
                break;
            case MANAGE_ADDRESSES:
                intent = new Intent();
                intent.putExtra(CartActivity.EXTRA_START_ON_ADDRESS_BOOK, true);
                intent.setClass(WishApplication.getInstance(), CartActivity.class);
                break;
            case MANAGE_PAYMENTS:
                intent = new Intent();
                intent.setClass(WishApplication.getInstance(), ManagePaymentsActivity.class);
                break;
            case PRICE_WATCH:
                intent = new Intent();
                intent.setClass(WishApplication.getInstance(), PriceWatchActivity.class);
                break;
            case WISHLIST:
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_WISHLIST_PAGE_DEEPLINK);
                intent = new Intent();
                intent.putExtra(WishlistActivity.EXTRA_WISHLIST_ID, deepLink.getWishlistId());
                intent.setClass(WishApplication.getInstance(), WishlistActivity.class);
                break;
            case ONFIDO:
                intent = new Intent();
                intent.setClass(WishApplication.getInstance(), IdentityVerificationActivity.class);
                intent.addFlags(67108864);
                Bundle bundle = new Bundle();
                bundle.putString("ExtraApplicantId", deepLink.getOnfidoApplicantId());
                intent.putExtras(bundle);
                break;
        }
        if (!(intent == null || wishNotification == null)) {
            IntentUtil.putParcelableExtra(intent, "ExtraSourceNotification", wishNotification);
        }
        if (intent != null) {
            intent.putExtra("ExtraIgnoreMainFeedDefaultLoad", true);
        }
        return intent;
    }

    public DeepLink getLastAppLaunchDeepLinkToSend() {
        this.mAppLaunchDeepLinkSent = true;
        return this.mLastAppLaunchDeepLink;
    }

    public void setLastAppLaunchDeepLink(DeepLink deepLink) {
        this.mLastAppLaunchDeepLink = deepLink;
        this.mAppLaunchDeepLinkSent = false;
    }

    public boolean hasAppLaunchDeepLinkSent() {
        return this.mAppLaunchDeepLinkSent;
    }
}
