package com.contextlogic.wish.activity.feed.newusergiftpack;

import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.NewUserGiftPackSpec.SplashSpec;
import com.contextlogic.wish.api.model.WishTextViewSpec;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.link.DeepLink;
import com.contextlogic.wish.link.DeepLinkManager;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.util.ColorUtil;

public class UserGiftPackSplashView extends LinearLayout {
    private Button mButton;
    private NetworkImageView mCenterImage;
    private View mCenterLayout;
    private View mDivider;
    private BaseDialogFragment<BaseActivity> mFragment;
    private View mHeaderLayout;
    private TextView mHeaderSubText;
    private TextView mHeaderText;
    private TextView mPromoText;
    private View mSlantSliver;
    private TextView mSubText;
    private TextView mText;

    public UserGiftPackSplashView(Context context) {
        this(context, null);
    }

    public UserGiftPackSplashView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.user_gift_pack_splash, this, true);
        setGravity(17);
        setOrientation(1);
        this.mHeaderLayout = findViewById(R.id.ugp_splash_header_layout);
        this.mCenterLayout = findViewById(R.id.ugp_splash_center_layout);
        this.mHeaderText = (TextView) findViewById(R.id.ugp_splash_header_text);
        this.mHeaderSubText = (TextView) findViewById(R.id.ugp_splash_header_subtext);
        this.mSlantSliver = findViewById(R.id.ugp_splash_slant_sliver);
        this.mCenterImage = (NetworkImageView) findViewById(R.id.ugp_splash_center_image);
        this.mText = (TextView) findViewById(R.id.ugp_splash_text);
        this.mDivider = findViewById(R.id.ugp_splash_divider);
        this.mSubText = (TextView) findViewById(R.id.ugp_splash_subtext);
        this.mPromoText = (TextView) findViewById(R.id.ugp_splash_promo_text);
        this.mButton = (Button) findViewById(R.id.ugp_splash_button);
    }

    public void setup(BaseDialogFragment<BaseActivity> baseDialogFragment, SplashSpec splashSpec) {
        this.mFragment = baseDialogFragment;
        WishTextViewSpec.applyTextViewSpec(this.mHeaderText, splashSpec.getHeaderText());
        WishTextViewSpec.applyTextViewSpec(this.mHeaderSubText, splashSpec.getHeaderSubtext());
        WishTextViewSpec.applyTextViewSpec(this.mText, splashSpec.getText());
        WishTextViewSpec.applyTextViewSpec(this.mSubText, splashSpec.getSubtext());
        this.mHeaderLayout.setBackground(getHeaderBackground(splashSpec.getGradientColors()));
        int safeParseColor = ColorUtil.safeParseColor(splashSpec.getBackgroundColor(), ContextCompat.getColor(getContext(), R.color.gray7));
        setBackgroundColor(safeParseColor);
        setupCenterLayout(safeParseColor, splashSpec.getCenterImageUrl());
        setupDivider(splashSpec.getDividerColor());
        setupPromoText(splashSpec.getPromoText(), splashSpec.getPromoBorderColor());
        setupButton(splashSpec);
        if (splashSpec.getImpressionEventId() != -1) {
            WishAnalyticsLogger.trackEvent(splashSpec.getImpressionEventId());
        }
    }

    private void setupPromoText(WishTextViewSpec wishTextViewSpec, String str) {
        int safeParseColor = ColorUtil.safeParseColor(str, 0);
        if (safeParseColor != 0) {
            this.mPromoText.getBackground().setColorFilter(safeParseColor, Mode.MULTIPLY);
        }
        WishTextViewSpec.applyTextViewSpec(this.mPromoText, wishTextViewSpec);
    }

    private void setupCenterLayout(int i, String str) {
        this.mSlantSliver.setBackgroundColor(i);
        if (!TextUtils.isEmpty(str)) {
            this.mCenterImage.setImageUrl(str);
        } else {
            this.mCenterLayout.setVisibility(8);
        }
    }

    private void setupDivider(String str) {
        int safeParseColor = ColorUtil.safeParseColor(str, 0);
        if (safeParseColor != 0) {
            this.mDivider.setBackgroundColor(safeParseColor);
        } else {
            this.mDivider.setVisibility(8);
        }
    }

    private void setupButton(final SplashSpec splashSpec) {
        WishTextViewSpec.applyTextViewSpec(this.mButton, splashSpec.getButtonText());
        this.mButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                UserGiftPackSplashView.this.onButtonClick(splashSpec);
            }
        });
        int safeParseColor = ColorUtil.safeParseColor(splashSpec.getButtonBackgroundColor(), 0);
        if (safeParseColor != 0) {
            this.mButton.getBackground().setColorFilter(safeParseColor, Mode.MULTIPLY);
        }
    }

    /* access modifiers changed from: private */
    public void onButtonClick(final SplashSpec splashSpec) {
        if (this.mFragment != null) {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_PROMO_SPLASH_BUTTON);
            if (TextUtils.isEmpty(splashSpec.getDeeplink())) {
                this.mFragment.dismissAllowingStateLoss();
                this.mFragment.makeSelection(1);
            } else {
                this.mFragment.withActivity(new ActivityTask<BaseActivity>() {
                    public void performTask(BaseActivity baseActivity) {
                        DeepLinkManager.processDeepLink(baseActivity, new DeepLink(splashSpec.getDeeplink()));
                    }
                });
            }
        }
    }

    private Drawable getHeaderBackground(int[] iArr) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColors(iArr);
        gradientDrawable.setOrientation(Orientation.TL_BR);
        return gradientDrawable;
    }
}
