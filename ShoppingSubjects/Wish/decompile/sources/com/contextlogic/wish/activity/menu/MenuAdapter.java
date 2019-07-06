package com.contextlogic.wish.activity.menu;

import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.api.datacenter.ConfigDataCenter;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.datacenter.ProfileDataCenter;
import com.contextlogic.wish.api.datacenter.StatusDataCenter;
import com.contextlogic.wish.api.model.WishCommerceCashUserInfo;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;
import com.contextlogic.wish.ui.spannable.RoundedBackgroundSpan;
import com.contextlogic.wish.ui.view.ProfileImageView;
import java.util.ArrayList;

public class MenuAdapter extends BaseAdapter {
    private BaseActivity mBaseActivity;
    private String mCurrentMenuKey;
    private ArrayList<String> mMenuItems;
    private boolean mSeeReferralProgram;

    static class ItemRowHolder {
        View badge;
        LinearLayout contentContainer;
        AutoReleasableImageView rowFlagImage;
        AutoReleasableImageView rowImage;
        TextView rowText;
        View separator;

        ItemRowHolder() {
        }
    }

    static class ProfileRowHolder {
        AutoReleasableImageView background;
        TextView name;
        ProfileImageView profileImageView;

        ProfileRowHolder() {
        }
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public int getItemViewType(int i) {
        return i == 0 ? 0 : 1;
    }

    public int getViewTypeCount() {
        return 2;
    }

    public boolean isEnabled(int i) {
        return true;
    }

    public MenuAdapter(BaseActivity baseActivity, String str) {
        this.mBaseActivity = baseActivity;
        this.mCurrentMenuKey = str;
        setupMenu();
    }

    public void setupMenu() {
        this.mMenuItems = new ArrayList<>();
        this.mSeeReferralProgram = ExperimentDataCenter.getInstance().shouldSeeReferralProgram();
        this.mMenuItems.add(MenuFragment.MENU_KEY_PROFILE);
        if (!ExperimentDataCenter.getInstance().shouldShowBottomNavigation()) {
            this.mMenuItems.add(MenuFragment.MENU_KEY_BROWSE);
        }
        if (this.mSeeReferralProgram) {
            this.mMenuItems.add(MenuFragment.MENU_KEY_REFERRAL_PROGRAM);
        }
        if (!ExperimentDataCenter.getInstance().shouldShowBottomNavigation()) {
            this.mMenuItems.add(MenuFragment.MENU_KEY_SEARCH);
        }
        this.mMenuItems.add(MenuFragment.MENU_KEY_NOTIFICATIONS);
        if (!ExperimentDataCenter.getInstance().shouldShowBottomNavigation()) {
            this.mMenuItems.add(MenuFragment.MENU_KEY_CART);
        }
        this.mMenuItems.add(MenuFragment.MENU_KEY_ORDER_HISTORY);
        if (ExperimentDataCenter.getInstance().canSeeCommerceCash() || ExperimentDataCenter.getInstance().shouldSeePayNearMe() || ExperimentDataCenter.getInstance().shouldSeeReferralProgram()) {
            this.mMenuItems.add(MenuFragment.MENU_KEY_COMMERCE_CASH);
        }
        if (ExperimentDataCenter.getInstance().shouldSeePriceWatch()) {
            this.mMenuItems.add(MenuFragment.MENU_KEY_PRICE_WATCH);
        }
        if (!ExperimentDataCenter.getInstance().turnOffRewards()) {
            this.mMenuItems.add(MenuFragment.MENU_KEY_REWARDS);
        }
        if (ExperimentDataCenter.getInstance().shouldShowDayPrizes()) {
            this.mMenuItems.add(MenuFragment.MENU_KEY_DAILY_LOGIN_BONUS);
        }
        if (ConfigDataCenter.getInstance().getInviteCouponSpec() != null && !this.mSeeReferralProgram) {
            this.mMenuItems.add(MenuFragment.MENU_KEY_INVITE_FRIENDS);
        }
        if (ExperimentDataCenter.getInstance().shouldSeePartnerAccounts()) {
            this.mMenuItems.add(MenuFragment.MENU_KEY_WISH_PARTNER);
        }
        if (ExperimentDataCenter.getInstance().shouldSeePromoSideNav()) {
            this.mMenuItems.add(MenuFragment.MENU_KEY_PROMO_CODE);
        }
        this.mMenuItems.add(MenuFragment.MENU_KEY_MORE_APPS);
        if (ConfigDataCenter.getInstance().getInviteCouponSpec() == null && !this.mSeeReferralProgram) {
            this.mMenuItems.add(MenuFragment.MENU_KEY_INVITE_FRIENDS);
        }
        this.mMenuItems.add(MenuFragment.MENU_KEY_CUSTOMER_SUPPORT);
        this.mMenuItems.add(MenuFragment.MENU_KEY_FAQ);
        this.mMenuItems.add(MenuFragment.MENU_KEY_SETTINGS);
    }

    public int getCount() {
        return this.mMenuItems.size();
    }

    public String getItem(int i) {
        return (String) this.mMenuItems.get(i);
    }

    private String countryCodeToFileName(String str) {
        if (str == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("flag_");
        sb.append(str.toLowerCase());
        return sb.toString();
    }

    private String getCountryCode() {
        return ProfileDataCenter.getInstance().getCountryCode();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ItemRowHolder itemRowHolder;
        ProfileRowHolder profileRowHolder;
        if (getItemViewType(i) == 0) {
            if (view == null) {
                LayoutInflater layoutInflater = this.mBaseActivity.getLayoutInflater();
                ProfileRowHolder profileRowHolder2 = new ProfileRowHolder();
                View inflate = layoutInflater.inflate(R.layout.menu_fragment_profile_row, viewGroup, false);
                profileRowHolder2.background = (AutoReleasableImageView) inflate.findViewById(R.id.menu_profile_background);
                profileRowHolder2.name = (TextView) inflate.findViewById(R.id.menu_profile_name);
                profileRowHolder2.profileImageView = (ProfileImageView) inflate.findViewById(R.id.menu_profile_image_view);
                inflate.setTag(profileRowHolder2);
                ProfileRowHolder profileRowHolder3 = profileRowHolder2;
                view = inflate;
                profileRowHolder = profileRowHolder3;
            } else {
                profileRowHolder = (ProfileRowHolder) view.getTag();
            }
            profileRowHolder.name.setText(ProfileDataCenter.getInstance().getName());
            profileRowHolder.profileImageView.setup(ProfileDataCenter.getInstance().getProfileImage(), ProfileDataCenter.getInstance().getName());
        } else if (getItemViewType(i) == 1) {
            if (view == null) {
                LayoutInflater layoutInflater2 = this.mBaseActivity.getLayoutInflater();
                itemRowHolder = new ItemRowHolder();
                view = layoutInflater2.inflate(R.layout.menu_fragment_row, viewGroup, false);
                itemRowHolder.contentContainer = (LinearLayout) view.findViewById(R.id.menu_fragment_row_content_container);
                itemRowHolder.rowText = (TextView) view.findViewById(R.id.menu_fragment_row_text);
                itemRowHolder.rowImage = (AutoReleasableImageView) view.findViewById(R.id.menu_fragment_row_image);
                itemRowHolder.rowFlagImage = (AutoReleasableImageView) view.findViewById(R.id.menu_fragment_row_flag_image);
                itemRowHolder.badge = view.findViewById(R.id.menu_fragment_row_badge);
                itemRowHolder.separator = view.findViewById(R.id.fragment_menu_separator);
                view.setTag(itemRowHolder);
            } else {
                itemRowHolder = (ItemRowHolder) view.getTag();
            }
            String item = getItem(i);
            populateRows(itemRowHolder, item);
            if (this.mCurrentMenuKey == null || !item.equals(this.mCurrentMenuKey)) {
                itemRowHolder.contentContainer.setBackgroundResource(R.drawable.menu_fragment_row_selector);
            } else {
                itemRowHolder.contentContainer.setBackgroundColor(WishApplication.getInstance().getResources().getColor(R.color.menu_selected));
            }
        }
        return view;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00df, code lost:
        if (r7.getScreenSeen() == false) goto L_0x00e3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x01d3, code lost:
        if ((!com.contextlogic.wish.util.PreferenceUtil.getBoolean("SawDailyLoginScreen")) != false) goto L_0x00e3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void populateRows(com.contextlogic.wish.activity.menu.MenuAdapter.ItemRowHolder r6, java.lang.String r7) {
        /*
            r5 = this;
            android.view.View r0 = r6.separator
            r1 = 8
            r0.setVisibility(r1)
            com.contextlogic.wish.ui.image.AutoReleasableImageView r0 = r6.rowFlagImage
            r0.setVisibility(r1)
            java.lang.String r0 = com.contextlogic.wish.activity.menu.MenuFragment.MENU_KEY_BROWSE
            boolean r0 = r7.equals(r0)
            r2 = 0
            if (r0 == 0) goto L_0x0020
            r7 = 2131755194(0x7f1000ba, float:1.914126E38)
            r0 = 2131165961(0x7f070309, float:1.7946154E38)
            r5.populateRow(r6, r7, r0)
            goto L_0x0237
        L_0x0020:
            java.lang.String r0 = com.contextlogic.wish.activity.menu.MenuFragment.MENU_KEY_REFERRAL_PROGRAM
            boolean r0 = r7.equals(r0)
            r3 = 1
            if (r0 == 0) goto L_0x005a
            com.contextlogic.wish.api.datacenter.ConfigDataCenter r7 = com.contextlogic.wish.api.datacenter.ConfigDataCenter.getInstance()
            java.lang.String r7 = r7.getReferralMenuTitle()
            boolean r7 = android.text.TextUtils.isEmpty(r7)
            r0 = 2131165971(0x7f070313, float:1.7946174E38)
            if (r7 != 0) goto L_0x0046
            com.contextlogic.wish.api.datacenter.ConfigDataCenter r7 = com.contextlogic.wish.api.datacenter.ConfigDataCenter.getInstance()
            java.lang.String r7 = r7.getReferralMenuTitle()
            r5.populateRow(r6, r7, r0)
            goto L_0x004c
        L_0x0046:
            r7 = 2131755776(0x7f100300, float:1.914244E38)
            r5.populateRow(r6, r7, r0)
        L_0x004c:
            java.lang.String r7 = "SawCashReferral"
            boolean r7 = com.contextlogic.wish.util.PreferenceUtil.getBoolean(r7)
            r7 = r7 ^ r3
            android.view.View r0 = r6.separator
            r0.setVisibility(r2)
            goto L_0x0238
        L_0x005a:
            java.lang.String r0 = com.contextlogic.wish.activity.menu.MenuFragment.MENU_KEY_SEARCH
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L_0x0076
            r7 = 2131756257(0x7f1004e1, float:1.9143416E38)
            r0 = 2131165973(0x7f070315, float:1.7946178E38)
            r5.populateRow(r6, r7, r0)
            boolean r7 = r5.mSeeReferralProgram
            if (r7 != 0) goto L_0x0237
            android.view.View r7 = r6.separator
            r7.setVisibility(r2)
            goto L_0x0237
        L_0x0076:
            java.lang.String r0 = com.contextlogic.wish.activity.menu.MenuFragment.MENU_KEY_NOTIFICATIONS
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L_0x0099
            r7 = 2131755878(0x7f100366, float:1.9142648E38)
            r0 = 2131165968(0x7f070310, float:1.7946168E38)
            r5.populateRow(r6, r7, r0)
            java.lang.String r7 = "HideNotificationRedDot"
            boolean r7 = com.contextlogic.wish.util.PreferenceUtil.getBoolean(r7)
            if (r7 != 0) goto L_0x0237
            com.contextlogic.wish.api.datacenter.StatusDataCenter r7 = com.contextlogic.wish.api.datacenter.StatusDataCenter.getInstance()
            int r7 = r7.getUnviewedNotificationCount()
            goto L_0x0238
        L_0x0099:
            java.lang.String r0 = com.contextlogic.wish.activity.menu.MenuFragment.MENU_KEY_CART
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L_0x00ac
            r7 = 2131756336(0x7f100530, float:1.9143577E38)
            r0 = 2131165976(0x7f070318, float:1.7946184E38)
            r5.populateRow(r6, r7, r0)
            goto L_0x0237
        L_0x00ac:
            java.lang.String r0 = com.contextlogic.wish.activity.menu.MenuFragment.MENU_KEY_ORDER_HISTORY
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L_0x00bf
            r7 = 2131756026(0x7f1003fa, float:1.9142948E38)
            r0 = 2131165969(0x7f070311, float:1.794617E38)
            r5.populateRow(r6, r7, r0)
            goto L_0x0237
        L_0x00bf:
            java.lang.String r0 = com.contextlogic.wish.activity.menu.MenuFragment.MENU_KEY_COMMERCE_CASH
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L_0x00e6
            android.text.SpannableString r7 = getCommerceCashSpannable()
            r0 = 2131165962(0x7f07030a, float:1.7946156E38)
            r5.populateRow(r6, r7, r0)
            com.contextlogic.wish.api.datacenter.StatusDataCenter r7 = com.contextlogic.wish.api.datacenter.StatusDataCenter.getInstance()
            com.contextlogic.wish.api.model.WishCommerceCashUserInfo r7 = r7.getCommerceCashUserInfo()
            if (r7 == 0) goto L_0x00e2
            boolean r7 = r7.getScreenSeen()
            if (r7 != 0) goto L_0x00e2
            goto L_0x00e3
        L_0x00e2:
            r3 = 0
        L_0x00e3:
            r7 = r3
            goto L_0x0238
        L_0x00e6:
            java.lang.String r0 = com.contextlogic.wish.activity.menu.MenuFragment.MENU_KEY_REWARDS
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L_0x0102
            android.text.SpannableString r7 = r5.getRewardsString()
            r0 = 2131165972(0x7f070314, float:1.7946176E38)
            r5.populateRow(r6, r7, r0)
            com.contextlogic.wish.api.datacenter.StatusDataCenter r7 = com.contextlogic.wish.api.datacenter.StatusDataCenter.getInstance()
            int r7 = r7.getRewardCount()
            goto L_0x0238
        L_0x0102:
            java.lang.String r0 = com.contextlogic.wish.activity.menu.MenuFragment.MENU_KEY_MORE_APPS
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L_0x011a
            r7 = 2131756334(0x7f10052e, float:1.9143573E38)
            r0 = 2131165975(0x7f070317, float:1.7946182E38)
            r5.populateRow(r6, r7, r0)
            android.view.View r7 = r6.separator
            r7.setVisibility(r2)
            goto L_0x0237
        L_0x011a:
            java.lang.String r0 = com.contextlogic.wish.activity.menu.MenuFragment.MENU_KEY_INVITE_FRIENDS
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L_0x0152
            com.contextlogic.wish.api.datacenter.ConfigDataCenter r7 = com.contextlogic.wish.api.datacenter.ConfigDataCenter.getInstance()
            com.contextlogic.wish.api.model.WishInviteCouponSpec r7 = r7.getInviteCouponSpec()
            if (r7 == 0) goto L_0x0147
            com.contextlogic.wish.api.datacenter.ConfigDataCenter r7 = com.contextlogic.wish.api.datacenter.ConfigDataCenter.getInstance()
            com.contextlogic.wish.api.model.WishInviteCouponSpec r7 = r7.getInviteCouponSpec()
            java.lang.String r7 = r7.getMenuTitle()
            r0 = 2131165966(0x7f07030e, float:1.7946164E38)
            r5.populateRow(r6, r7, r0)
            java.lang.String r7 = "SawInviteCouponScreen"
            boolean r7 = com.contextlogic.wish.util.PreferenceUtil.getBoolean(r7)
            r7 = r7 ^ r3
            goto L_0x0238
        L_0x0147:
            r7 = 2131755695(0x7f1002af, float:1.9142277E38)
            r0 = 2131165967(0x7f07030f, float:1.7946166E38)
            r5.populateRow(r6, r7, r0)
            goto L_0x0237
        L_0x0152:
            java.lang.String r0 = com.contextlogic.wish.activity.menu.MenuFragment.MENU_KEY_CUSTOMER_SUPPORT
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L_0x0165
            r7 = 2131755393(0x7f100181, float:1.9141664E38)
            r0 = 2131165963(0x7f07030b, float:1.7946158E38)
            r5.populateRow(r6, r7, r0)
            goto L_0x0237
        L_0x0165:
            java.lang.String r0 = com.contextlogic.wish.activity.menu.MenuFragment.MENU_KEY_FAQ
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L_0x0178
            r7 = 2131755596(0x7f10024c, float:1.9142076E38)
            r0 = 2131165965(0x7f07030d, float:1.7946162E38)
            r5.populateRow(r6, r7, r0)
            goto L_0x0237
        L_0x0178:
            java.lang.String r0 = com.contextlogic.wish.activity.menu.MenuFragment.MENU_KEY_SETTINGS
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L_0x01bb
            r7 = 2131756283(0x7f1004fb, float:1.914347E38)
            r0 = 2131165974(0x7f070316, float:1.794618E38)
            r5.populateRow(r6, r7, r0)
            java.lang.String r7 = r5.getCountryCode()
            if (r7 == 0) goto L_0x0237
            com.contextlogic.wish.application.WishApplication r7 = com.contextlogic.wish.application.WishApplication.getInstance()
            android.content.res.Resources r7 = r7.getResources()
            java.lang.String r0 = r5.getCountryCode()
            java.lang.String r0 = r5.countryCodeToFileName(r0)
            java.lang.String r3 = "drawable"
            com.contextlogic.wish.activity.BaseActivity r4 = r5.mBaseActivity
            android.content.Context r4 = r4.getApplicationContext()
            java.lang.String r4 = r4.getPackageName()
            int r7 = r7.getIdentifier(r0, r3, r4)
            com.contextlogic.wish.ui.image.AutoReleasableImageView r0 = r6.rowFlagImage
            r0.setVisibility(r2)
            com.contextlogic.wish.ui.image.AutoReleasableImageView r0 = r6.rowFlagImage
            r0.setImageResource(r7)
            goto L_0x0237
        L_0x01bb:
            java.lang.String r0 = com.contextlogic.wish.activity.menu.MenuFragment.MENU_KEY_DAILY_LOGIN_BONUS
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L_0x01d7
            r7 = 2131755405(0x7f10018d, float:1.9141688E38)
            r0 = 2131165964(0x7f07030c, float:1.794616E38)
            r5.populateRow(r6, r7, r0)
            java.lang.String r7 = "SawDailyLoginScreen"
            boolean r7 = com.contextlogic.wish.util.PreferenceUtil.getBoolean(r7)
            r7 = r7 ^ r3
            if (r7 == 0) goto L_0x00e2
            goto L_0x00e3
        L_0x01d7:
            java.lang.String r0 = com.contextlogic.wish.activity.menu.MenuFragment.MENU_KEY_WISH_PARTNER
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L_0x01e9
            r7 = 2131756538(0x7f1005fa, float:1.9143986E38)
            r0 = 2131165998(0x7f07032e, float:1.7946229E38)
            r5.populateRow(r6, r7, r0)
            goto L_0x0237
        L_0x01e9:
            java.lang.String r0 = com.contextlogic.wish.activity.menu.MenuFragment.MENU_KEY_PROMO_CODE
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L_0x01fb
            r7 = 2131755104(0x7f100060, float:1.9141078E38)
            r0 = 2131165997(0x7f07032d, float:1.7946227E38)
            r5.populateRow(r6, r7, r0)
            goto L_0x0237
        L_0x01fb:
            java.lang.String r0 = com.contextlogic.wish.activity.menu.MenuFragment.MENU_KEY_PRICE_WATCH
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L_0x0237
            com.contextlogic.wish.api.datacenter.StatusDataCenter r7 = com.contextlogic.wish.api.datacenter.StatusDataCenter.getInstance()
            boolean r7 = r7.showPriceWatchNewBadge()
            r0 = 2131166104(0x7f070398, float:1.7946444E38)
            r3 = 2131756127(0x7f10045f, float:1.9143153E38)
            if (r7 == 0) goto L_0x0223
            com.contextlogic.wish.application.WishApplication r7 = com.contextlogic.wish.application.WishApplication.getInstance()
            java.lang.String r7 = r7.getString(r3)
            android.text.SpannableString r7 = getNewBadge(r7)
            r5.populateRow(r6, r7, r0)
            goto L_0x022e
        L_0x0223:
            com.contextlogic.wish.application.WishApplication r7 = com.contextlogic.wish.application.WishApplication.getInstance()
            java.lang.String r7 = r7.getString(r3)
            r5.populateRow(r6, r7, r0)
        L_0x022e:
            com.contextlogic.wish.api.datacenter.StatusDataCenter r7 = com.contextlogic.wish.api.datacenter.StatusDataCenter.getInstance()
            boolean r7 = r7.showPriceWatchNotification()
            goto L_0x0238
        L_0x0237:
            r7 = 0
        L_0x0238:
            if (r7 <= 0) goto L_0x024a
            com.contextlogic.wish.api.datacenter.ExperimentDataCenter r7 = com.contextlogic.wish.api.datacenter.ExperimentDataCenter.getInstance()
            boolean r7 = r7.shouldShowRedDot()
            if (r7 == 0) goto L_0x024a
            android.view.View r6 = r6.badge
            r6.setVisibility(r2)
            goto L_0x024f
        L_0x024a:
            android.view.View r6 = r6.badge
            r6.setVisibility(r1)
        L_0x024f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.activity.menu.MenuAdapter.populateRows(com.contextlogic.wish.activity.menu.MenuAdapter$ItemRowHolder, java.lang.String):void");
    }

    public static SpannableString getNewBadge(String str) {
        String string = WishApplication.getInstance().getString(R.string.new_tag);
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("  ");
        String sb2 = sb.toString();
        int length = sb2.length();
        int length2 = string.length() + length;
        RoundedBackgroundSpan roundedBackgroundSpan = new RoundedBackgroundSpan(WishApplication.getInstance().getResources().getColor(R.color.wish_blue), WishApplication.getInstance().getResources().getColor(R.color.white), (float) WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.text_size_caption));
        StringBuilder sb3 = new StringBuilder();
        sb3.append(sb2);
        sb3.append(string);
        SpannableString spannableString = new SpannableString(sb3.toString());
        spannableString.setSpan(roundedBackgroundSpan, length, length2, 33);
        return spannableString;
    }

    public static SpannableString getCommerceCashSpannable() {
        String name = WishApplication.getName();
        String string = WishApplication.getInstance().getString(R.string.commerce_cash, new Object[]{name});
        WishCommerceCashUserInfo commerceCashUserInfo = StatusDataCenter.getInstance().getCommerceCashUserInfo();
        if (commerceCashUserInfo == null || commerceCashUserInfo.getBalance() == null) {
            return new SpannableString(string);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(string);
        sb.append(": ");
        String sb2 = sb.toString();
        String formattedString = commerceCashUserInfo.getBalance().toFormattedString(false, false);
        int length = sb2.length();
        int length2 = formattedString.length() + length;
        StringBuilder sb3 = new StringBuilder();
        sb3.append(sb2);
        sb3.append(formattedString);
        SpannableString spannableString = new SpannableString(sb3.toString());
        spannableString.setSpan(new ForegroundColorSpan(WishApplication.getInstance().getResources().getColor(R.color.main_dark)), length, length2, 33);
        return spannableString;
    }

    private SpannableString getRewardsString() {
        int rewardPoints = StatusDataCenter.getInstance().getRewardPoints();
        String quantityString = WishApplication.getInstance().getResources().getQuantityString(R.plurals.reward_points_number, rewardPoints, new Object[]{Integer.valueOf(rewardPoints)});
        StringBuilder sb = new StringBuilder();
        sb.append(WishApplication.getInstance().getString(R.string.rewards));
        sb.append(": ");
        String sb2 = sb.toString();
        int length = sb2.length();
        int length2 = quantityString.length() + length;
        StringBuilder sb3 = new StringBuilder();
        sb3.append(sb2);
        sb3.append(quantityString);
        SpannableString spannableString = new SpannableString(sb3.toString());
        spannableString.setSpan(new ForegroundColorSpan(WishApplication.getInstance().getResources().getColor(R.color.main_dark)), length, length2, 33);
        return spannableString;
    }

    private void populateRow(ItemRowHolder itemRowHolder, CharSequence charSequence, int i) {
        itemRowHolder.rowText.setText(charSequence);
        itemRowHolder.rowImage.setImageResource(i);
    }

    private void populateRow(ItemRowHolder itemRowHolder, int i, int i2) {
        populateRow(itemRowHolder, (CharSequence) WishApplication.getInstance().getString(i), i2);
    }

    public void releaseImages(ListView listView) {
        if (listView != null) {
            for (int i = 0; i < listView.getChildCount(); i++) {
                Object tag = listView.getChildAt(i).getTag();
                if (tag instanceof ItemRowHolder) {
                    ((ItemRowHolder) tag).rowImage.releaseImages();
                } else if (tag instanceof ProfileRowHolder) {
                    ProfileRowHolder profileRowHolder = (ProfileRowHolder) tag;
                    if (profileRowHolder.profileImageView != null) {
                        profileRowHolder.profileImageView.releaseImages();
                    }
                    profileRowHolder.background.releaseImages();
                }
            }
        }
    }

    public void restoreImages(ListView listView) {
        if (listView != null) {
            for (int i = 0; i < listView.getChildCount(); i++) {
                Object tag = listView.getChildAt(i).getTag();
                if (tag instanceof ProfileRowHolder) {
                    ProfileRowHolder profileRowHolder = (ProfileRowHolder) tag;
                    if (profileRowHolder.profileImageView != null) {
                        profileRowHolder.profileImageView.restoreImages();
                    }
                    profileRowHolder.background.restoreImages();
                }
            }
        }
    }
}
