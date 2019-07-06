package com.contextlogic.wish.activity.feed.dailyraffle;

import android.content.Context;
import android.content.Intent;
import android.text.SpannableStringBuilder;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.feed.BaseProductFeedFragment;
import com.contextlogic.wish.activity.feed.dailygiveaway.DailyGiveawayFeedView.DailyGiveawayState;
import com.contextlogic.wish.activity.feed.dailyraffle.DailyRaffleFeedView.DailyRaffleWinState;
import com.contextlogic.wish.activity.productdetails.ProductDetailsActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.listview.HorizontalListView;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.ui.timer.BaseCountdownTimerView.DoneCallback;
import com.contextlogic.wish.ui.timer.CountdownTimerView;
import com.contextlogic.wish.ui.view.Recyclable;
import com.contextlogic.wish.util.IntentUtil;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class DailyRaffleListCellView extends LinearLayout implements ImageRestorable, Recyclable {
    /* access modifiers changed from: private */
    public DailyRaffleListAdapter mAdapter;
    /* access modifiers changed from: private */
    public BaseActivity mBaseActivity;
    private StyleSpan mBoldSpan;
    private RelativeLayout mContainer;
    private TextView mEnterRaffleButton;
    private ThemedTextView mEntryNumber;
    /* access modifiers changed from: private */
    public BaseProductFeedFragment mFragment;
    private ThemedTextView mImageBannerText;
    private FrameLayout mImageContainer;
    /* access modifiers changed from: private */
    public WishProduct mItem;
    private AutoReleasableImageView mLowerLeftImage;
    private ThemedTextView mLowerLeftText;
    private NetworkImageView mProductImage;
    private TextView mQuantityTextView;
    private TextView mSelectedBadge;
    private DailyGiveawayState mState;
    private StrikethroughSpan mStrikeThroughSpan;
    private TextView mTitleTextView;
    private AutoReleasableImageView mTopBannerStar;
    /* access modifiers changed from: private */
    public CountdownTimerView mTopBannerTimer;
    private LinearLayout mTopCardBanner;
    private ThemedTextView mTopCardText;
    private UserListHorizontalAdapter mUserListAdapter;
    private ThemedTextView mUsersNotShownText;
    private ThemedTextView mViewAllButton;
    private HorizontalListView mWinnersListView;

    public DailyRaffleListCellView(Context context) {
        this(context, null);
    }

    public DailyRaffleListCellView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public void init() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.daily_raffle_list_cell_view, this);
        this.mProductImage = (NetworkImageView) inflate.findViewById(R.id.daily_raffle_product_tile_image);
        this.mTitleTextView = (TextView) inflate.findViewById(R.id.daily_raffle_product_title_price);
        this.mQuantityTextView = (TextView) inflate.findViewById(R.id.daily_raffle_quantity_text);
        this.mEnterRaffleButton = (TextView) inflate.findViewById(R.id.daily_raffle_enter_raffle_button);
        this.mSelectedBadge = (TextView) inflate.findViewById(R.id.daily_raffle_selected_badge);
        this.mContainer = (RelativeLayout) inflate.findViewById(R.id.daily_raffle_card_container);
        this.mWinnersListView = (HorizontalListView) inflate.findViewById(R.id.daily_raffle_users_list);
        this.mUserListAdapter = new UserListHorizontalAdapter(getContext());
        this.mWinnersListView.setAdapter(this.mUserListAdapter);
        this.mUsersNotShownText = (ThemedTextView) inflate.findViewById(R.id.daily_raffle_extra_winner_count_text);
        this.mTopCardBanner = (LinearLayout) inflate.findViewById(R.id.daily_raffle_top_card_banner);
        this.mTopCardText = (ThemedTextView) inflate.findViewById(R.id.daily_raffle_card_top_banner_text);
        this.mImageContainer = (FrameLayout) inflate.findViewById(R.id.daily_raffle_product_tile_image_container);
        this.mContainer.setBackgroundResource(R.drawable.listview_card_border);
        this.mImageBannerText = (ThemedTextView) inflate.findViewById(R.id.daily_raffle_card_image_banner_text);
        this.mViewAllButton = (ThemedTextView) inflate.findViewById(R.id.daily_raffle_card_view_all_button);
        this.mTopBannerStar = (AutoReleasableImageView) inflate.findViewById(R.id.daily_raffle_card_top_banner_star);
        this.mEntryNumber = (ThemedTextView) inflate.findViewById(R.id.daily_raffle_entry_number);
        this.mLowerLeftImage = (AutoReleasableImageView) inflate.findViewById(R.id.daily_raffle_card_lower_left_star);
        this.mLowerLeftText = (ThemedTextView) inflate.findViewById(R.id.daily_raffle_card_lower_left_text);
        this.mTopBannerTimer = (CountdownTimerView) inflate.findViewById(R.id.daily_raffle_card_top_banner_timer);
        this.mStrikeThroughSpan = new StrikethroughSpan();
        this.mBoldSpan = new StyleSpan(1);
    }

    public void setup(BaseActivity baseActivity, BaseProductFeedFragment baseProductFeedFragment, DailyRaffleListAdapter dailyRaffleListAdapter, ImageHttpPrefetcher imageHttpPrefetcher) {
        this.mBaseActivity = baseActivity;
        this.mFragment = baseProductFeedFragment;
        refreshState(dailyRaffleListAdapter);
        refreshButtonState();
        if (this.mProductImage != null) {
            this.mProductImage.setImagePrefetcher(imageHttpPrefetcher);
        }
    }

    public void refreshState(DailyRaffleListAdapter dailyRaffleListAdapter) {
        this.mAdapter = dailyRaffleListAdapter;
        DailyGiveawayState dailyGiveawayState = this.mState;
        this.mState = this.mAdapter.getState();
        if (dailyGiveawayState != this.mState) {
            refreshButtonState();
        }
    }

    public void setProduct(WishProduct wishProduct) {
        this.mItem = wishProduct;
        this.mProductImage.setImage(wishProduct.getImage());
        defaultCellView();
        String formattedString = wishProduct.getCommerceValue().toFormattedString();
        if (wishProduct.getValue().getValue() == 0.0d) {
            formattedString = getResources().getString(R.string.free);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(formattedString);
        sb.append(" ");
        sb.append(wishProduct.getName());
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(sb.toString());
        spannableStringBuilder.setSpan(this.mBoldSpan, 0, formattedString.length(), 33);
        this.mTitleTextView.setText(spannableStringBuilder);
        int dailyGiveawayQuantity = wishProduct.getDailyGiveawayQuantity();
        this.mQuantityTextView.setText(getResources().getQuantityString(R.plurals.daily_raffle_free_items_and_coupons_text, dailyGiveawayQuantity, new Object[]{Integer.valueOf(dailyGiveawayQuantity)}));
        this.mEntryNumber.setText(getResources().getQuantityString(R.plurals.daily_raffle_entries_text, wishProduct.getRaffleEntryNumber(), new Object[]{Integer.valueOf(wishProduct.getRaffleEntryNumber())}));
        this.mProductImage.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_DAILY_GIVEAWAY_CURRENT_PRODUCT_DETAILS, DailyRaffleListCellView.this.mAdapter.getExtraInfo());
                Intent intent = new Intent();
                intent.setClass(DailyRaffleListCellView.this.mBaseActivity, ProductDetailsActivity.class);
                ProductDetailsActivity.addInitialProduct(intent, DailyRaffleListCellView.this.mItem);
                DailyRaffleListCellView.this.mBaseActivity.startActivity(intent);
            }
        });
        if (this.mState == DailyGiveawayState.WINNERS_PICKED || this.mState == DailyGiveawayState.RAFFLE_PRIZE_CLAIMED || this.mState == DailyGiveawayState.RAFFLE_PRIZE_CHECKED_OUT || this.mState == DailyGiveawayState.RAFFLE_PRIZE_CLAIM_ENDED) {
            setWinnersList();
        }
        this.mEnterRaffleButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_DAILY_RAFFLE_ENTER_RAFFLE);
                DailyRaffleListCellView.this.mFragment.enterDailyRaffle(DailyRaffleListCellView.this.mItem, DailyRaffleListCellView.this.mAdapter.getFeedView());
            }
        });
        this.mViewAllButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_DAILY_RAFFLE_VIEW_ALL);
                Intent intent = new Intent(DailyRaffleListCellView.this.mBaseActivity, DailyRaffleWinnersListActivity.class);
                IntentUtil.putLargeParcelableExtra(intent, "DailyRaffleListProduct", DailyRaffleListCellView.this.mItem);
                DailyRaffleListCellView.this.mBaseActivity.startActivity(intent);
            }
        });
        this.mWinnersListView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_DAILY_RAFFLE_VIEW_ALL);
                Intent intent = new Intent(DailyRaffleListCellView.this.mBaseActivity, DailyRaffleWinnersListActivity.class);
                IntentUtil.putLargeParcelableExtra(intent, "DailyRaffleListProduct", DailyRaffleListCellView.this.mItem);
                DailyRaffleListCellView.this.mBaseActivity.startActivity(intent);
            }
        });
    }

    private void defaultCellView() {
        if (this.mState == DailyGiveawayState.WINNERS_PICKED || this.mState == DailyGiveawayState.RAFFLE_PRIZE_CLAIMED || this.mState == DailyGiveawayState.RAFFLE_PRIZE_CHECKED_OUT || this.mState == DailyGiveawayState.RAFFLE_PRIZE_CLAIM_ENDED) {
            this.mQuantityTextView.setVisibility(8);
            this.mViewAllButton.setVisibility(0);
            this.mWinnersListView.setVisibility(0);
            this.mUsersNotShownText.setVisibility(0);
            this.mUsersNotShownText.setTextSize(0, getResources().getDimension(R.dimen.daily_raffle_additional_winner_text_size));
            this.mLowerLeftImage.setVisibility(0);
            this.mLowerLeftText.setText(getResources().getString(R.string.winners));
            this.mEnterRaffleButton.setVisibility(8);
        } else {
            this.mQuantityTextView.setVisibility(0);
            this.mViewAllButton.setVisibility(8);
            this.mWinnersListView.setVisibility(8);
            this.mUsersNotShownText.setVisibility(8);
            this.mLowerLeftImage.setVisibility(8);
            this.mLowerLeftText.setText(getResources().getString(R.string.prizes_available));
            this.mEnterRaffleButton.setVisibility(0);
        }
        this.mSelectedBadge.setVisibility(8);
        this.mContainer.setBackgroundResource(R.drawable.raffle_grey_five_border_rounded);
        this.mTopCardBanner.setVisibility(8);
        this.mImageBannerText.setVisibility(8);
        this.mTopBannerStar.setVisibility(8);
        this.mImageContainer.setBackgroundResource(R.color.gray7);
    }

    public void releaseImages() {
        if (this.mProductImage != null) {
            this.mProductImage.releaseImages();
        }
        if (this.mWinnersListView != null) {
            this.mWinnersListView.releaseImages();
        }
    }

    public void restoreImages() {
        if (this.mProductImage != null) {
            this.mProductImage.restoreImages();
        }
        if (this.mWinnersListView != null) {
            this.mWinnersListView.restoreImages();
        }
    }

    public void recycle() {
        if (this.mProductImage != null) {
            this.mProductImage.recycle();
        }
    }

    /* access modifiers changed from: 0000 */
    public void refreshButtonState() {
        if (this.mEnterRaffleButton != null) {
            if (this.mState == DailyGiveawayState.NOT_STARTED || this.mState == DailyGiveawayState.STARTING_SOON || this.mState == DailyGiveawayState.ALREADY_CLAIMED || this.mState == DailyGiveawayState.ENDED) {
                this.mEnterRaffleButton.setBackgroundResource(R.drawable.daily_giveaway_claim_blue_button_claimed);
                this.mEnterRaffleButton.setEnabled(false);
            } else if (this.mState == DailyGiveawayState.ONGOING) {
                this.mEnterRaffleButton.setBackgroundResource(R.drawable.daily_giveaway_claim_button_active);
                this.mEnterRaffleButton.setEnabled(true);
            } else if (this.mState == DailyGiveawayState.WINNERS_PICKED) {
                this.mEnterRaffleButton.setEnabled(true);
            } else if (this.mState == DailyGiveawayState.RAFFLE_PRIZE_CLAIMED || this.mState == DailyGiveawayState.RAFFLE_PRIZE_CHECKED_OUT) {
                this.mEnterRaffleButton.setEnabled(false);
            } else if (this.mState == DailyGiveawayState.RAFFLE_PRIZE_CLAIM_ENDED) {
                this.mEnterRaffleButton.setEnabled(false);
                this.mEnterRaffleButton.setVisibility(8);
            }
        }
    }

    private void setWinnersList() {
        List winnerList = this.mAdapter.getWinnerList(this.mItem == null ? null : this.mItem.getCommerceProductId());
        if (winnerList == null || winnerList.size() <= 0) {
            this.mViewAllButton.setVisibility(8);
            this.mWinnersListView.setVisibility(8);
            this.mUsersNotShownText.setText(getResources().getString(R.string.daily_raffle_no_winners_text));
            this.mUsersNotShownText.setVisibility(0);
            this.mUsersNotShownText.setTextSize(0, getResources().getDimension(R.dimen.text_size_caption));
            return;
        }
        this.mWinnersListView.setVisibility(0);
        this.mUserListAdapter.setUsers(winnerList);
        this.mWinnersListView.getLayoutParams().width = ((this.mUserListAdapter.getItemWidth(0) + this.mUserListAdapter.getItemMargin()) * this.mUserListAdapter.getCount()) - this.mUserListAdapter.getItemMargin();
        this.mWinnersListView.setAdapter(this.mUserListAdapter);
        this.mUserListAdapter.notifyDataSetChanged();
        if (this.mUserListAdapter.getUsersNotShownCount() > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("+");
            sb.append(this.mUserListAdapter.getUsersNotShownCount());
            this.mUsersNotShownText.setText(sb.toString());
            this.mUsersNotShownText.setVisibility(0);
        } else {
            this.mUsersNotShownText.setVisibility(8);
        }
        this.mViewAllButton.setVisibility(0);
    }

    public void setSelected() {
        this.mSelectedBadge.setVisibility(0);
        this.mContainer.setBackgroundResource(R.drawable.raffle_selected_discounted_border_rounded);
    }

    public void setRaffleLost(String str) {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_DAILY_RAFFLE_LOST);
        this.mTopCardBanner.setVisibility(0);
        this.mTopCardBanner.setBackgroundResource(R.color.gray3);
        if (str != null) {
            this.mTopCardText.setText(str);
        }
        this.mContainer.setBackgroundResource(R.drawable.raffle_lost_border_rounded);
        this.mImageContainer.setBackgroundResource(R.color.gray3);
        this.mEnterRaffleButton.setVisibility(8);
    }

    public void setRaffleWon(String str) {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_DAILY_RAFFLE_WON_ITEM);
        this.mTopBannerStar.setVisibility(0);
        this.mTopCardBanner.setVisibility(0);
        this.mTopCardBanner.setBackgroundResource(R.color.yellow_dark);
        if (str != null) {
            this.mTopCardText.setText(str);
        }
        this.mContainer.setBackgroundResource(R.drawable.raffle_won_border_rounded);
        if (this.mItem != null) {
            String string = getResources().getString(R.string.free);
            StringBuilder sb = new StringBuilder();
            sb.append(string);
            sb.append(" ");
            sb.append(this.mItem.getName());
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(sb.toString());
            spannableStringBuilder.setSpan(this.mBoldSpan, 0, string.length(), 33);
            this.mTitleTextView.setText(spannableStringBuilder);
        }
        this.mImageBannerText.setBackgroundResource(R.color.yellow_dark);
        this.mImageBannerText.setText(R.string.free);
        this.mImageBannerText.setVisibility(0);
        this.mImageContainer.setBackgroundResource(R.color.yellow_dark);
        this.mEnterRaffleButton.setText(R.string.claim_prize);
        if (this.mState == DailyGiveawayState.WINNERS_PICKED) {
            this.mEnterRaffleButton.setBackgroundResource(R.drawable.raffle_won_button_active);
        } else {
            this.mEnterRaffleButton.setBackgroundResource(R.drawable.raffle_won_button_inactive);
        }
        this.mEnterRaffleButton.setVisibility(0);
        this.mEnterRaffleButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                HashMap hashMap = new HashMap();
                hashMap.put("raffle_winner_type", DailyRaffleWinState.FREE.name());
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_DAILY_RAFFLE_CLAIM, hashMap);
                DailyRaffleListCellView.this.mFragment.claimGiveaway(DailyRaffleListCellView.this.mItem);
            }
        });
    }

    public void setTimeToClaim(Date date) {
        if (this.mState == DailyGiveawayState.RAFFLE_PRIZE_CHECKED_OUT || this.mState == DailyGiveawayState.RAFFLE_PRIZE_CLAIM_ENDED || date == null) {
            this.mTopBannerTimer.setVisibility(8);
            return;
        }
        this.mTopBannerTimer.setDigitPadding(0).disableExpiredText().setColonPadding(WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.four_padding)).setup(date, getResources().getDimensionPixelSize(R.dimen.add_to_cart_offer_group_buy_row_timer_height), getResources().getColor(R.color.transparent), getResources().getColor(R.color.white), getResources().getColor(R.color.white), CountdownTimerView.NO_BACKGROUND, false, true, new DoneCallback() {
            public void onCountdownEnd() {
                DailyRaffleListCellView.this.mTopBannerTimer.setVisibility(8);
                DailyRaffleListCellView.this.mAdapter.reload();
            }
        });
        this.mTopBannerTimer.startTimer();
    }
}
