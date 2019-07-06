package com.contextlogic.wish.activity.feed.newusergiftpack;

import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.NewUserGiftPackSpec.SplashSpec;
import com.contextlogic.wish.api.model.WishTextViewSpec;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.link.DeepLink;
import com.contextlogic.wish.link.DeepLinkManager;
import com.contextlogic.wish.ui.button.ThemedButton;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.util.ColorUtil;

public class UserGiftPackV3SplashView extends LinearLayout {
    private ThemedButton mButton;
    private GiftPackHeaderCardView mDay1Card;
    private GiftPackHeaderCardView mDay2Card;
    private View mDividerLayout;
    private ThemedTextView mDividerText;
    private ThemedTextView mHeaderSubtext;
    private ThemedTextView mHeaderText;

    public UserGiftPackV3SplashView(Context context) {
        this(context, null);
    }

    public UserGiftPackV3SplashView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public UserGiftPackV3SplashView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.user_gift_pack_v3_splash, this, true);
        setOrientation(1);
        setBackgroundResource(R.color.gray7);
        setGravity(17);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.twenty_four_padding);
        setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
        setBackgroundResource(R.drawable.ugp_splash_v3_bg);
        this.mHeaderText = (ThemedTextView) findViewById(R.id.ugp_splash_header_text);
        this.mHeaderSubtext = (ThemedTextView) findViewById(R.id.ugp_splash_header_subtext);
        this.mDay1Card = (GiftPackHeaderCardView) findViewById(R.id.ugp_splash_card1);
        this.mDividerLayout = findViewById(R.id.ugp_splash_divider_layout);
        this.mDividerText = (ThemedTextView) this.mDividerLayout.findViewById(R.id.ugp_splash_divider_text);
        this.mDay2Card = (GiftPackHeaderCardView) findViewById(R.id.ugp_splash_card2);
        this.mButton = (ThemedButton) findViewById(R.id.ugp_splash_button);
    }

    public void setup(BaseDialogFragment baseDialogFragment, SplashSpec splashSpec) {
        WishTextViewSpec.applyTextViewSpec(this.mHeaderText, splashSpec.getHeaderText());
        WishTextViewSpec.applyTextViewSpec(this.mHeaderSubtext, splashSpec.getHeaderSubtext());
        if (splashSpec.getDay1CardSpec() == null || splashSpec.getDay2CardSpec() == null) {
            throw new NullPointerException("NUGP v3 Splash Card Specs are null!");
        }
        this.mDay1Card.setup(splashSpec.getDay1CardSpec(), null);
        this.mDay2Card.setup(splashSpec.getDay2CardSpec(), null);
        if (!TextUtils.isEmpty(splashSpec.getDividerText())) {
            this.mDividerText.setText(splashSpec.getDividerText());
        } else {
            this.mDividerLayout.setVisibility(8);
        }
        setupButton(splashSpec, baseDialogFragment);
    }

    private void setupButton(final SplashSpec splashSpec, final BaseDialogFragment baseDialogFragment) {
        WishTextViewSpec.applyTextViewSpec(this.mButton, splashSpec.getButtonText());
        this.mButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                UserGiftPackV3SplashView.this.onButtonClick(splashSpec, baseDialogFragment);
            }
        });
        int safeParseColor = ColorUtil.safeParseColor(splashSpec.getButtonBackgroundColor(), 0);
        if (safeParseColor != 0) {
            this.mButton.getBackground().setColorFilter(safeParseColor, Mode.MULTIPLY);
        }
    }

    /* access modifiers changed from: private */
    public void onButtonClick(SplashSpec splashSpec, BaseDialogFragment baseDialogFragment) {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_PROMO_SPLASH_BUTTON);
        if (TextUtils.isEmpty(splashSpec.getDeeplink())) {
            baseDialogFragment.dismissAllowingStateLoss();
            return;
        }
        DeepLinkManager.processDeepLink(baseDialogFragment.getBaseActivity(), new DeepLink(splashSpec.getDeeplink()));
    }
}
