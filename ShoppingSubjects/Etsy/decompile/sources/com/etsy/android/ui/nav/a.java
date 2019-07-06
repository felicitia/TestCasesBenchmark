package com.etsy.android.ui.nav;

import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.View.OnClickListener;
import com.etsy.android.R;
import com.etsy.android.lib.auth.ForgotPasswordFragment;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.models.Attendee;
import com.etsy.android.lib.models.Country;
import com.etsy.android.lib.models.LocalMarket;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.interfaces.ListingLike;
import com.etsy.android.lib.util.CountryUtil.b;
import com.etsy.android.lib.util.ExternalAccountUtil.SignInFlow;
import com.etsy.android.ui.convos.ConvoComposeFragment;
import com.etsy.android.ui.dialog.BOEPermissionDeniedDialogFragment;
import com.etsy.android.ui.dialog.CountryDialogFragment;
import com.etsy.android.ui.dialog.EtsyDialogFragment;
import com.etsy.android.ui.dialog.EtsyTrioDialogFragment;
import com.etsy.android.ui.dialog.GiftWrapDescriptionFragment;
import com.etsy.android.ui.dialog.PromoDialogFragment;
import com.etsy.android.ui.dialog.ShopShareOnboarding;
import com.etsy.android.ui.dialog.SingleListingCheckoutDialog;
import com.etsy.android.ui.dialog.SingleListingCheckoutStandalonePayPalDialog;
import com.etsy.android.ui.favorites.ListingCollectionsFragment;
import com.etsy.android.ui.local.marketdetails.LocalDatesAttendingFragment;
import com.etsy.android.ui.user.LeaveFeedbackFragment;
import com.etsy.android.ui.user.UserSettingsFragment;
import com.etsy.android.ui.user.auth.RegisterFragment;
import com.etsy.android.ui.user.auth.SignInActivity;
import com.etsy.android.ui.user.auth.SignInFragment;
import com.etsy.android.ui.user.auth.SignInNagFragment;
import com.etsy.android.ui.user.auth.SignInTwoFactorFragment;
import com.etsy.android.uikit.share.ShareBrokerFragment;
import com.etsy.android.uikit.ui.dialog.text.TextInfoDialog;
import java.util.ArrayList;

/* compiled from: DialogNavigator */
public class a {
    private static final String a = f.a(a.class);
    private final FragmentActivity b;
    private final d c = new d();
    private Bundle d = new Bundle();
    private OnDismissListener e;
    private String f;

    a(FragmentActivity fragmentActivity) {
        this.b = fragmentActivity;
    }

    public a a(Bundle bundle) {
        this.d = bundle;
        return this;
    }

    public a a(OnDismissListener onDismissListener) {
        this.e = onDismissListener;
        return this;
    }

    public EtsyDialogFragment a(int i, String str, OnClickListener onClickListener) {
        PromoDialogFragment newInstance = PromoDialogFragment.newInstance(i, str, onClickListener);
        newInstance.setArguments(this.d);
        return a((String) null, (Fragment) newInstance, EtsyDialogFragment.OPT_X_BUTTON, newInstance.getOnClickListener(), true);
    }

    public EtsyDialogFragment a() {
        UserSettingsFragment userSettingsFragment = new UserSettingsFragment();
        userSettingsFragment.setArguments(this.d);
        String string = this.b.getString(R.string.settings);
        this.c.r();
        return a(string, (Fragment) userSettingsFragment, EtsyDialogFragment.OPT_X_BUTTON, userSettingsFragment.getOnClickListener(), true);
    }

    public EtsyDialogFragment b() {
        SignInNagFragment signInNagFragment = new SignInNagFragment();
        signInNagFragment.setArguments(this.d);
        this.c.s();
        return a("", (Fragment) signInNagFragment, "", (OnClickListener) null, true);
    }

    public EtsyDialogFragment a(SignInFlow signInFlow) {
        return a(signInFlow, false);
    }

    public EtsyDialogFragment a(SignInFlow signInFlow, boolean z) {
        this.c.a(signInFlow);
        this.f = "signIn";
        String string = this.b.getString(R.string.sign_in);
        SignInFragment signInFragment = new SignInFragment();
        this.d.putBoolean("show_social_buttons", z);
        signInFragment.setArguments(this.d);
        return a(string, (Fragment) signInFragment, "", (OnClickListener) null, true);
    }

    public EtsyDialogFragment b(SignInFlow signInFlow) {
        return b(signInFlow, false);
    }

    public EtsyDialogFragment b(SignInFlow signInFlow, boolean z) {
        this.c.b(signInFlow);
        this.f = SignInActivity.EXTRA_REGISTER;
        String string = this.b.getString(R.string.register);
        RegisterFragment registerFragment = new RegisterFragment();
        this.d.putBoolean("show_social_buttons", z);
        registerFragment.setArguments(this.d);
        return a(string, (Fragment) registerFragment, "", (OnClickListener) null, true);
    }

    public EtsyDialogFragment a(String str, String str2, SignInFlow signInFlow) {
        SignInTwoFactorFragment signInTwoFactorFragment = new SignInTwoFactorFragment();
        this.d.putString(ResponseConstants.USERNAME, str);
        this.d.putString("workflow_key", str2);
        String string = this.b.getString(R.string.sign_in);
        signInTwoFactorFragment.setArguments(this.d);
        this.c.c(signInFlow);
        return a(string, (Fragment) signInTwoFactorFragment, EtsyDialogFragment.OPT_X_BUTTON, (OnClickListener) null, true);
    }

    public EtsyDialogFragment a(com.etsy.android.ui.dialog.EtsyTrioDialogFragment.a aVar, int i, int i2, int i3, String str) {
        EtsyTrioDialogFragment newInstance = EtsyTrioDialogFragment.newInstance(aVar, i, i2, i3);
        this.d.putBoolean("USE_DIM", true);
        newInstance.setArguments(this.d);
        return a(str, (Fragment) newInstance, "", (OnClickListener) null, true);
    }

    @Deprecated
    public EtsyDialogFragment a(b bVar, ArrayList<Country> arrayList, ArrayList<Country> arrayList2, String str) {
        CountryDialogFragment newInstance = CountryDialogFragment.newInstance(bVar, arrayList, arrayList2);
        this.d.putBoolean("USE_DIM", true);
        String string = this.b.getResources().getString(R.string.countries);
        this.c.a(str);
        return a(string, (Fragment) newInstance, EtsyDialogFragment.OPT_X_BUTTON, (OnClickListener) null, true);
    }

    public EtsyDialogFragment c() {
        ConvoComposeFragment convoComposeFragment = new ConvoComposeFragment();
        if (this.d.containsKey("convo_id")) {
            this.d.putString("TRACKING_NAME", "conversations_thread_reply");
        } else {
            this.d.putString("TRACKING_NAME", "conversations_new");
        }
        convoComposeFragment.setArguments(this.d);
        String string = this.b.getString(R.string.convo_compose_new_title);
        this.f = "convoCompose";
        EtsyDialogFragment a2 = a(string, (Fragment) convoComposeFragment, EtsyDialogFragment.OPT_X_BUTTON, (OnClickListener) null, true);
        a2.setWindowAnimation(R.style.DialogAnimBottom);
        this.c.a(this.d);
        return a2;
    }

    public EtsyDialogFragment b(OnDismissListener onDismissListener) {
        LeaveFeedbackFragment leaveFeedbackFragment = new LeaveFeedbackFragment();
        this.e = onDismissListener;
        leaveFeedbackFragment.setArguments(this.d);
        EtsyDialogFragment a2 = a(this.b.getString(R.string.write_a_review), (Fragment) leaveFeedbackFragment, EtsyDialogFragment.OPT_X_BUTTON, (OnClickListener) null, true);
        a2.setWindowAnimation(R.style.DialogAnimBottom);
        return a2;
    }

    @Deprecated
    public EtsyDialogFragment a(EtsyId etsyId, String str) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("listing_id", etsyId);
        bundle.putString(ResponseConstants.LISTING_IMAGE_URL, str);
        return b(bundle);
    }

    public EtsyDialogFragment a(ListingLike listingLike) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("listing", listingLike);
        return b(bundle);
    }

    private EtsyDialogFragment b(Bundle bundle) {
        ListingCollectionsFragment listingCollectionsFragment = new ListingCollectionsFragment();
        this.d.putAll(bundle);
        listingCollectionsFragment.setArguments(this.d);
        String string = this.b.getString(R.string.add_listing_to_collection_title);
        String string2 = this.b.getString(R.string.done);
        this.c.o();
        return a(string, (Fragment) listingCollectionsFragment, string2, listingCollectionsFragment.getOnClickListener(), false);
    }

    public EtsyDialogFragment c(SignInFlow signInFlow) {
        ForgotPasswordFragment forgotPasswordFragment = new ForgotPasswordFragment();
        forgotPasswordFragment.setArguments(this.d);
        EtsyDialogFragment a2 = a(this.b.getString(R.string.forgot_password_title), (Fragment) forgotPasswordFragment, EtsyDialogFragment.OPT_X_BUTTON, (OnClickListener) null, true);
        a2.setWindowAnimation(R.style.DialogAnimBottom);
        this.c.d(signInFlow);
        return a2;
    }

    public EtsyDialogFragment a(Attendee attendee, LocalMarket localMarket) {
        LocalDatesAttendingFragment localDatesAttendingFragment = new LocalDatesAttendingFragment();
        this.d.putSerializable("attendee", attendee);
        this.d.putSerializable(ResponseConstants.LOCAL_MARKET, localMarket);
        localDatesAttendingFragment.setArguments(this.d);
        return a("", (Fragment) localDatesAttendingFragment, "", (OnClickListener) null, false);
    }

    public EtsyDialogFragment d() {
        ShareBrokerFragment shareBrokerFragment = new ShareBrokerFragment();
        shareBrokerFragment.setArguments(this.d);
        EtsyDialogFragment a2 = a(this.b.getString(R.string.share_with), (Fragment) shareBrokerFragment, (String) null, (OnClickListener) null, true);
        a2.setWindowAnimation(R.style.DialogAnimBottom);
        return a2;
    }

    public EtsyDialogFragment a(String str, String str2) {
        TextInfoDialog textInfoDialog = new TextInfoDialog();
        this.d.putString("text", str2);
        textInfoDialog.setArguments(this.d);
        this.c.f();
        return a(str, (Fragment) textInfoDialog, EtsyDialogFragment.OPT_X_BUTTON, (OnClickListener) null, true);
    }

    public EtsyDialogFragment e() {
        this.d.putBoolean("USE_DIM", true);
        ShopShareOnboarding shopShareOnboarding = new ShopShareOnboarding();
        shopShareOnboarding.setArguments(this.d);
        this.f = "shopShareOnboarding";
        return a("", (Fragment) shopShareOnboarding, (String) null, shopShareOnboarding.getOnClickListener(), true);
    }

    public EtsyDialogFragment a(@NonNull String str) {
        BOEPermissionDeniedDialogFragment bOEPermissionDeniedDialogFragment = new BOEPermissionDeniedDialogFragment();
        this.d.putString("denied_permission", str);
        bOEPermissionDeniedDialogFragment.setArguments(this.d);
        return a((String) null, (Fragment) bOEPermissionDeniedDialogFragment, "", (OnClickListener) null, true);
    }

    public EtsyDialogFragment b(@Nullable String str) {
        this.f = "giftWrapDescription";
        return a(this.b.getString(R.string.listing_gift_wrap_dialog_title, new Object[]{str}), (Fragment) new GiftWrapDescriptionFragment(), EtsyDialogFragment.OPT_X_BUTTON, (OnClickListener) null, true);
    }

    public EtsyDialogFragment f() {
        SingleListingCheckoutDialog singleListingCheckoutDialog = new SingleListingCheckoutDialog();
        singleListingCheckoutDialog.setArguments(this.d);
        this.f = "singleListingCheckout";
        return a(this.b.getString(R.string.dialog_title_choose_payment), (Fragment) singleListingCheckoutDialog, EtsyDialogFragment.OPT_X_BUTTON, (OnClickListener) null, true);
    }

    public EtsyDialogFragment g() {
        SingleListingCheckoutStandalonePayPalDialog singleListingCheckoutStandalonePayPalDialog = new SingleListingCheckoutStandalonePayPalDialog();
        singleListingCheckoutStandalonePayPalDialog.setArguments(this.d);
        this.f = "singleListingCheckoutStandalonePayPal";
        return a(this.b.getString(R.string.dialog_title_checkout_paypal), (Fragment) singleListingCheckoutStandalonePayPalDialog, EtsyDialogFragment.OPT_X_BUTTON, (OnClickListener) null, true);
    }

    private EtsyDialogFragment a(String str, Fragment fragment, String str2, OnClickListener onClickListener, boolean z) {
        String str3;
        EtsyDialogFragment newInstance = EtsyDialogFragment.newInstance(fragment);
        newInstance.setArguments(this.d);
        newInstance.setTitle(str);
        newInstance.setOkButton(str2, onClickListener, z);
        newInstance.setOnDismissListener(this.e);
        newInstance.setCancelable(false);
        FragmentManager supportFragmentManager = this.b.getSupportFragmentManager();
        if (TextUtils.isEmpty(this.f)) {
            StringBuilder sb = new StringBuilder();
            sb.append(newInstance.getClass().getSimpleName());
            sb.append(newInstance.hashCode());
            str3 = sb.toString();
        } else {
            str3 = this.f;
        }
        if (supportFragmentManager.findFragmentByTag(str3) == null) {
            newInstance.show(supportFragmentManager, str3);
        }
        return newInstance;
    }
}
