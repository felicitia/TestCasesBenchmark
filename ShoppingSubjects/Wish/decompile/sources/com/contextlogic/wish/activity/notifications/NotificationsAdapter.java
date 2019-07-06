package com.contextlogic.wish.activity.notifications;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.api.model.WishImage;
import com.contextlogic.wish.api.model.WishNotification;
import com.contextlogic.wish.api.model.WishNotification.NotificationDisplayType;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.util.DateUtil;
import java.util.ArrayList;

public class NotificationsAdapter extends ArrayAdapter<WishNotification> {
    private NotificationsFragment mFragment;
    private ListView mListView;
    private final ArrayList<WishNotification> mNotifications;
    private NotificationsView mNotificationsView;

    static class BaseCardRowHolder {
        int firstVisiblePosition;
        TextView newTagText;
        WishNotification notification;
        int position;
        TextView rowText;
        TextView rowTimestamp;

        public void releaseImages() {
        }

        public void restoreImages() {
        }

        BaseCardRowHolder() {
        }
    }

    static class DailyGiveawayCardRowHolder extends BaseCardRowHolder {
        AutoReleasableImageView imageView;

        DailyGiveawayCardRowHolder() {
        }

        public void releaseImages() {
            this.imageView.releaseImages();
        }

        public void restoreImages() {
            this.imageView.restoreImages();
        }
    }

    static class DealDashCardRowHolder extends BaseCardRowHolder {
        AutoReleasableImageView imageView;

        DealDashCardRowHolder() {
        }

        public void releaseImages() {
            this.imageView.releaseImages();
        }

        public void restoreImages() {
            this.imageView.restoreImages();
        }
    }

    static class DefaultCardRowHolder extends BaseCardRowHolder {
        NetworkImageView networkImageView;

        DefaultCardRowHolder() {
        }

        public void releaseImages() {
            this.networkImageView.releaseImages();
        }

        public void restoreImages() {
            this.networkImageView.restoreImages();
        }
    }

    static class GetGiveCouponCardRowHolder extends BaseCardRowHolder {
        AutoReleasableImageView imageView;

        GetGiveCouponCardRowHolder() {
        }
    }

    static class LargeCardRowHolder extends BaseCardRowHolder {
        NetworkImageView firstLargeImage;
        AutoReleasableImageView imageView;
        NetworkImageView secondLargeImage;
        NetworkImageView thirdLargeImage;

        LargeCardRowHolder() {
        }

        public void releaseImages() {
            this.firstLargeImage.releaseImages();
            this.secondLargeImage.releaseImages();
            this.thirdLargeImage.releaseImages();
            this.imageView.releaseImages();
        }

        public void restoreImages() {
            this.firstLargeImage.restoreImages();
            this.secondLargeImage.restoreImages();
            this.thirdLargeImage.restoreImages();
            this.imageView.restoreImages();
        }
    }

    static class RewardCardRowHolder extends BaseCardRowHolder {
        NetworkImageView networkImageView;

        RewardCardRowHolder() {
        }

        public void releaseImages() {
            this.networkImageView.releaseImages();
        }

        public void restoreImages() {
            this.networkImageView.restoreImages();
        }
    }

    static class SmallCardRowHolder extends BaseCardRowHolder {
        NetworkImageView networkImageView;

        SmallCardRowHolder() {
        }

        public void releaseImages() {
            this.networkImageView.releaseImages();
        }

        public void restoreImages() {
            this.networkImageView.restoreImages();
        }
    }

    public NotificationsAdapter(DrawerActivity drawerActivity, NotificationsFragment notificationsFragment, ArrayList<WishNotification> arrayList, ListView listView, NotificationsView notificationsView) {
        super(drawerActivity, R.layout.notifications_fragment_item_row, arrayList);
        this.mFragment = notificationsFragment;
        this.mListView = listView;
        this.mNotificationsView = notificationsView;
        this.mNotifications = arrayList;
    }

    public int getViewTypeCount() {
        return NotificationDisplayType.values().length;
    }

    public int getItemViewType(int i) {
        return getItem(i).getNotificationDisplayType().getValue() - 1;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        DefaultCardRowHolder defaultCardRowHolder;
        RewardCardRowHolder rewardCardRowHolder;
        SmallCardRowHolder smallCardRowHolder;
        LargeCardRowHolder largeCardRowHolder;
        DealDashCardRowHolder dealDashCardRowHolder;
        GetGiveCouponCardRowHolder getGiveCouponCardRowHolder;
        DailyGiveawayCardRowHolder dailyGiveawayCardRowHolder;
        DefaultCardRowHolder defaultCardRowHolder2;
        LayoutInflater layoutInflater = ((Activity) getContext()).getLayoutInflater();
        WishNotification item = getItem(i);
        switch (item.getNotificationDisplayType()) {
            case DEFAULT:
                if (view == null) {
                    DefaultCardRowHolder defaultCardRowHolder3 = new DefaultCardRowHolder();
                    View inflate = layoutInflater.inflate(R.layout.notifications_fragment_item_row_small, viewGroup, false);
                    initBaseCardRowHolder(i, defaultCardRowHolder3, item);
                    initDefaultCardRowHolder(defaultCardRowHolder3, inflate);
                    inflate.setTag(defaultCardRowHolder3);
                    defaultCardRowHolder = defaultCardRowHolder3;
                    view = inflate;
                } else {
                    defaultCardRowHolder = (DefaultCardRowHolder) view.getTag();
                }
                populateDefaultCardRowHolder(defaultCardRowHolder);
                populateBaseCardRowHolder(defaultCardRowHolder, item);
                break;
            case REWARD:
                if (view == null) {
                    RewardCardRowHolder rewardCardRowHolder2 = new RewardCardRowHolder();
                    View inflate2 = layoutInflater.inflate(R.layout.notifications_fragment_item_row_small, viewGroup, false);
                    initBaseCardRowHolder(i, rewardCardRowHolder2, item);
                    initRewardCardRowHolder(rewardCardRowHolder2, inflate2);
                    inflate2.setTag(rewardCardRowHolder2);
                    rewardCardRowHolder = rewardCardRowHolder2;
                    view = inflate2;
                } else {
                    rewardCardRowHolder = (RewardCardRowHolder) view.getTag();
                }
                populateRewardCardRowHolder(rewardCardRowHolder);
                populateBaseCardRowHolder(rewardCardRowHolder, item);
                break;
            case SMALL:
                if (view == null) {
                    SmallCardRowHolder smallCardRowHolder2 = new SmallCardRowHolder();
                    View inflate3 = layoutInflater.inflate(R.layout.notifications_fragment_item_row_small, viewGroup, false);
                    initBaseCardRowHolder(i, smallCardRowHolder2, item);
                    initSmallCardRowHolder(smallCardRowHolder2, inflate3);
                    inflate3.setTag(smallCardRowHolder2);
                    smallCardRowHolder = smallCardRowHolder2;
                    view = inflate3;
                } else {
                    smallCardRowHolder = (SmallCardRowHolder) view.getTag();
                }
                populateSmallCardRowHolder(smallCardRowHolder, item);
                populateBaseCardRowHolder(smallCardRowHolder, item);
                break;
            case LARGE:
                if (view == null) {
                    LargeCardRowHolder largeCardRowHolder2 = new LargeCardRowHolder();
                    View inflate4 = layoutInflater.inflate(R.layout.notifications_fragment_item_row_large, viewGroup, false);
                    initBaseCardRowHolder(i, largeCardRowHolder2, item);
                    initLargeCardRowHolder(largeCardRowHolder2, inflate4);
                    inflate4.setTag(largeCardRowHolder2);
                    largeCardRowHolder = largeCardRowHolder2;
                    view = inflate4;
                } else {
                    largeCardRowHolder = (LargeCardRowHolder) view.getTag();
                }
                populateLargeCardRowHolder(largeCardRowHolder, item);
                populateBaseCardRowHolder(largeCardRowHolder, item);
                break;
            case DEAL_DASH:
                if (view == null) {
                    DealDashCardRowHolder dealDashCardRowHolder2 = new DealDashCardRowHolder();
                    View inflate5 = layoutInflater.inflate(R.layout.notifications_fragment_item_row_large, viewGroup, false);
                    initBaseCardRowHolder(i, dealDashCardRowHolder2, item);
                    initDealDashCardRowHolder(dealDashCardRowHolder2, inflate5);
                    inflate5.setTag(dealDashCardRowHolder2);
                    dealDashCardRowHolder = dealDashCardRowHolder2;
                    view = inflate5;
                } else {
                    dealDashCardRowHolder = (DealDashCardRowHolder) view.getTag();
                }
                populateDealDashCardRowHolder(dealDashCardRowHolder);
                populateBaseCardRowHolder(dealDashCardRowHolder, item);
                break;
            case GET_GIVE_COUPON:
                if (view == null) {
                    GetGiveCouponCardRowHolder getGiveCouponCardRowHolder2 = new GetGiveCouponCardRowHolder();
                    View inflate6 = layoutInflater.inflate(R.layout.notifications_fragment_item_row_large, viewGroup, false);
                    initBaseCardRowHolder(i, getGiveCouponCardRowHolder2, item);
                    initGetGiveCouponCardRowHolder(getGiveCouponCardRowHolder2, inflate6);
                    inflate6.setTag(getGiveCouponCardRowHolder2);
                    getGiveCouponCardRowHolder = getGiveCouponCardRowHolder2;
                    view = inflate6;
                } else {
                    getGiveCouponCardRowHolder = (GetGiveCouponCardRowHolder) view.getTag();
                }
                populateGetGiveCouponCardRowHolder(getGiveCouponCardRowHolder);
                populateBaseCardRowHolder(getGiveCouponCardRowHolder, item);
                break;
            case DAILY_GIVEAWAY:
                if (view == null) {
                    DailyGiveawayCardRowHolder dailyGiveawayCardRowHolder2 = new DailyGiveawayCardRowHolder();
                    View inflate7 = layoutInflater.inflate(R.layout.notifications_fragment_item_row_large, viewGroup, false);
                    initBaseCardRowHolder(i, dailyGiveawayCardRowHolder2, item);
                    initDailyGiveawayCardRowHolder(dailyGiveawayCardRowHolder2, inflate7);
                    inflate7.setTag(dailyGiveawayCardRowHolder2);
                    dailyGiveawayCardRowHolder = dailyGiveawayCardRowHolder2;
                    view = inflate7;
                } else {
                    dailyGiveawayCardRowHolder = (DailyGiveawayCardRowHolder) view.getTag();
                }
                populateDailyGiveawayCardRowHolder(dailyGiveawayCardRowHolder);
                populateBaseCardRowHolder(dailyGiveawayCardRowHolder, item);
                break;
            case DAILY_LOGIN_BONUS:
                if (view == null) {
                    DefaultCardRowHolder defaultCardRowHolder4 = new DefaultCardRowHolder();
                    View inflate8 = layoutInflater.inflate(R.layout.notifications_fragment_item_row_small, viewGroup, false);
                    initBaseCardRowHolder(i, defaultCardRowHolder4, item);
                    initDefaultCardRowHolder(defaultCardRowHolder4, inflate8);
                    inflate8.setTag(defaultCardRowHolder4);
                    defaultCardRowHolder2 = defaultCardRowHolder4;
                    view = inflate8;
                } else {
                    defaultCardRowHolder2 = (DefaultCardRowHolder) view.getTag();
                }
                populateDailyLoginBonusCardRowHolder(defaultCardRowHolder2);
                populateBaseCardRowHolder(defaultCardRowHolder2, item);
                break;
        }
        return view;
    }

    public void releaseImages() {
        if (this.mListView != null) {
            for (int i = 0; i < this.mListView.getChildCount(); i++) {
                Object tag = this.mListView.getChildAt(i).getTag();
                if (tag instanceof BaseCardRowHolder) {
                    ((BaseCardRowHolder) tag).releaseImages();
                }
            }
        }
    }

    public void restoreImages() {
        if (this.mListView != null) {
            for (int i = 0; i < this.mListView.getChildCount(); i++) {
                Object tag = this.mListView.getChildAt(i).getTag();
                if (tag instanceof BaseCardRowHolder) {
                    ((BaseCardRowHolder) tag).restoreImages();
                }
            }
        }
    }

    public void refreshNewState() {
        if (this.mListView != null) {
            for (int i = 0; i < this.mListView.getChildCount(); i++) {
                Object tag = this.mListView.getChildAt(i).getTag();
                if (tag instanceof BaseCardRowHolder) {
                    BaseCardRowHolder baseCardRowHolder = (BaseCardRowHolder) tag;
                    if (!baseCardRowHolder.notification.isNew() || baseCardRowHolder.notification.isClicked()) {
                        baseCardRowHolder.newTagText.setVisibility(8);
                    } else {
                        baseCardRowHolder.newTagText.setVisibility(0);
                    }
                }
            }
        }
    }

    public void refreshTimestamps() {
        if (this.mListView != null) {
            for (int i = 0; i < this.mListView.getChildCount(); i++) {
                Object tag = this.mListView.getChildAt(i).getTag();
                if (tag instanceof BaseCardRowHolder) {
                    BaseCardRowHolder baseCardRowHolder = (BaseCardRowHolder) tag;
                    baseCardRowHolder.rowTimestamp.setText(DateUtil.getFuzzyDateFromNow(baseCardRowHolder.notification.getTimestamp()));
                }
            }
        }
    }

    public void initBaseCardRowHolder(int i, BaseCardRowHolder baseCardRowHolder, WishNotification wishNotification) {
        baseCardRowHolder.notification = wishNotification;
        baseCardRowHolder.position = i;
        baseCardRowHolder.firstVisiblePosition = this.mListView.getFirstVisiblePosition();
    }

    public void populateBaseCardRowHolder(BaseCardRowHolder baseCardRowHolder, WishNotification wishNotification) {
        baseCardRowHolder.rowText.setText(wishNotification.getMessage());
        baseCardRowHolder.rowTimestamp.setText(DateUtil.getFuzzyDateFromNow(wishNotification.getTimestamp()));
        if (!wishNotification.isNew() || wishNotification.isClicked()) {
            baseCardRowHolder.newTagText.setVisibility(8);
            return;
        }
        baseCardRowHolder.newTagText.setVisibility(0);
        wishNotification.markViewed();
        this.mFragment.markNotificationViewed(wishNotification);
    }

    public void initDefaultCardRowHolder(DefaultCardRowHolder defaultCardRowHolder, View view) {
        defaultCardRowHolder.newTagText = (TextView) view.findViewById(R.id.notifications_fragment_small_item_new_tag);
        defaultCardRowHolder.networkImageView = (NetworkImageView) view.findViewById(R.id.notifications_fragment_small_item_image);
        defaultCardRowHolder.rowText = (TextView) view.findViewById(R.id.notifications_fragment_small_item_text);
        defaultCardRowHolder.rowTimestamp = (TextView) view.findViewById(R.id.notifications_fragment_small_item_timestamp);
    }

    public void populateDefaultCardRowHolder(DefaultCardRowHolder defaultCardRowHolder) {
        defaultCardRowHolder.networkImageView.setImageBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(WishApplication.getInstance().getResources(), R.drawable.app_logo), (int) WishApplication.getInstance().getResources().getDimension(R.dimen.notifications_fragment_badge_image_size), (int) WishApplication.getInstance().getResources().getDimension(R.dimen.notifications_fragment_badge_image_size), true));
    }

    public void initRewardCardRowHolder(RewardCardRowHolder rewardCardRowHolder, View view) {
        rewardCardRowHolder.newTagText = (TextView) view.findViewById(R.id.notifications_fragment_small_item_new_tag);
        rewardCardRowHolder.networkImageView = (NetworkImageView) view.findViewById(R.id.notifications_fragment_small_item_image);
        rewardCardRowHolder.rowText = (TextView) view.findViewById(R.id.notifications_fragment_small_item_text);
        rewardCardRowHolder.rowTimestamp = (TextView) view.findViewById(R.id.notifications_fragment_small_item_timestamp);
    }

    public void populateRewardCardRowHolder(RewardCardRowHolder rewardCardRowHolder) {
        rewardCardRowHolder.networkImageView.setImageBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(WishApplication.getInstance().getResources(), R.drawable.badge_blue), (int) WishApplication.getInstance().getResources().getDimension(R.dimen.notifications_fragment_badge_image_size), (int) WishApplication.getInstance().getResources().getDimension(R.dimen.notifications_fragment_badge_image_size), true));
    }

    public void initSmallCardRowHolder(SmallCardRowHolder smallCardRowHolder, View view) {
        smallCardRowHolder.newTagText = (TextView) view.findViewById(R.id.notifications_fragment_small_item_new_tag);
        smallCardRowHolder.networkImageView = (NetworkImageView) view.findViewById(R.id.notifications_fragment_small_item_image);
        smallCardRowHolder.rowText = (TextView) view.findViewById(R.id.notifications_fragment_small_item_text);
        smallCardRowHolder.rowTimestamp = (TextView) view.findViewById(R.id.notifications_fragment_small_item_timestamp);
    }

    public void populateSmallCardRowHolder(SmallCardRowHolder smallCardRowHolder, WishNotification wishNotification) {
        smallCardRowHolder.networkImageView.setImage(new WishImage((String) wishNotification.getExtraImages().get(0)));
    }

    public void initLargeCardRowHolder(LargeCardRowHolder largeCardRowHolder, View view) {
        largeCardRowHolder.newTagText = (TextView) view.findViewById(R.id.notifications_fragment_large_item_new_tag);
        largeCardRowHolder.imageView = (AutoReleasableImageView) view.findViewById(R.id.notifications_fragment_large_item_image_full);
        largeCardRowHolder.firstLargeImage = (NetworkImageView) view.findViewById(R.id.notifications_fragment_large_item_image_1);
        largeCardRowHolder.secondLargeImage = (NetworkImageView) view.findViewById(R.id.notifications_fragment_large_item_image_2);
        largeCardRowHolder.thirdLargeImage = (NetworkImageView) view.findViewById(R.id.notifications_fragment_large_item_image_3);
        largeCardRowHolder.rowText = (TextView) view.findViewById(R.id.notifications_fragment_large_item_text);
        largeCardRowHolder.rowTimestamp = (TextView) view.findViewById(R.id.notifications_fragment_large_item_timestamp);
    }

    public void populateLargeCardRowHolder(LargeCardRowHolder largeCardRowHolder, WishNotification wishNotification) {
        largeCardRowHolder.imageView.setVisibility(8);
        largeCardRowHolder.firstLargeImage.setImage(new WishImage((String) wishNotification.getExtraImages().get(0)));
        largeCardRowHolder.secondLargeImage.setImage(new WishImage((String) wishNotification.getExtraImages().get(1)));
        largeCardRowHolder.thirdLargeImage.setImage(new WishImage((String) wishNotification.getExtraImages().get(2)));
    }

    public void initDealDashCardRowHolder(DealDashCardRowHolder dealDashCardRowHolder, View view) {
        dealDashCardRowHolder.newTagText = (TextView) view.findViewById(R.id.notifications_fragment_large_item_new_tag);
        dealDashCardRowHolder.imageView = (AutoReleasableImageView) view.findViewById(R.id.notifications_fragment_large_item_image_full);
        dealDashCardRowHolder.rowText = (TextView) view.findViewById(R.id.notifications_fragment_large_item_text);
        dealDashCardRowHolder.rowTimestamp = (TextView) view.findViewById(R.id.notifications_fragment_large_item_timestamp);
    }

    public void initGetGiveCouponCardRowHolder(GetGiveCouponCardRowHolder getGiveCouponCardRowHolder, View view) {
        getGiveCouponCardRowHolder.newTagText = (TextView) view.findViewById(R.id.notifications_fragment_large_item_new_tag);
        getGiveCouponCardRowHolder.imageView = (AutoReleasableImageView) view.findViewById(R.id.notifications_fragment_large_item_image_full);
        getGiveCouponCardRowHolder.rowText = (TextView) view.findViewById(R.id.notifications_fragment_large_item_text);
        getGiveCouponCardRowHolder.rowTimestamp = (TextView) view.findViewById(R.id.notifications_fragment_large_item_timestamp);
    }

    public void initDailyGiveawayCardRowHolder(DailyGiveawayCardRowHolder dailyGiveawayCardRowHolder, View view) {
        dailyGiveawayCardRowHolder.newTagText = (TextView) view.findViewById(R.id.notifications_fragment_large_item_new_tag);
        dailyGiveawayCardRowHolder.imageView = (AutoReleasableImageView) view.findViewById(R.id.notifications_fragment_large_item_image_full);
        dailyGiveawayCardRowHolder.rowText = (TextView) view.findViewById(R.id.notifications_fragment_large_item_text);
        dailyGiveawayCardRowHolder.rowTimestamp = (TextView) view.findViewById(R.id.notifications_fragment_large_item_timestamp);
    }

    public void populateDealDashCardRowHolder(DealDashCardRowHolder dealDashCardRowHolder) {
        dealDashCardRowHolder.imageView.setImageDrawable(WishApplication.getInstance().getResources().getDrawable(R.drawable.dealdash_banner));
    }

    public void populateGetGiveCouponCardRowHolder(GetGiveCouponCardRowHolder getGiveCouponCardRowHolder) {
        getGiveCouponCardRowHolder.imageView.setImageDrawable(WishApplication.getInstance().getResources().getDrawable(R.drawable.get_give_coupon_banner));
    }

    public void populateDailyGiveawayCardRowHolder(DailyGiveawayCardRowHolder dailyGiveawayCardRowHolder) {
        dailyGiveawayCardRowHolder.imageView.setImageDrawable(WishApplication.getInstance().getResources().getDrawable(R.drawable.giveaway_notification));
    }

    public void populateDailyLoginBonusCardRowHolder(DefaultCardRowHolder defaultCardRowHolder) {
        defaultCardRowHolder.networkImageView.setImageBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(WishApplication.getInstance().getResources(), R.drawable.daily_login_bonus_notification_stamp), (int) WishApplication.getInstance().getResources().getDimension(R.dimen.notifications_fragment_badge_image_size), (int) WishApplication.getInstance().getResources().getDimension(R.dimen.notifications_fragment_badge_image_size), true));
    }

    public int getCount() {
        if (this.mNotifications != null) {
            return this.mNotifications.size();
        }
        return 0;
    }

    public WishNotification getItem(int i) {
        if (this.mNotifications == null || i >= this.mNotifications.size()) {
            return null;
        }
        return (WishNotification) this.mNotifications.get(i);
    }
}
