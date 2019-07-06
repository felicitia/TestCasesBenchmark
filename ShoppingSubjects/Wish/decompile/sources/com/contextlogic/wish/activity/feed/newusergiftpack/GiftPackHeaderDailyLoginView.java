package com.contextlogic.wish.activity.feed.newusergiftpack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.dailybonus.StampRowView;
import com.contextlogic.wish.api.model.NewUserGiftPackSpec.CardSpec;
import com.contextlogic.wish.api.model.WishTextViewSpec;
import com.contextlogic.wish.application.WishApplication;

public class GiftPackHeaderDailyLoginView extends LinearLayout {
    private TextView mBodyText;
    private StampRowView mStampRowView;
    private TextView mTitleText;

    public GiftPackHeaderDailyLoginView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.gift_pack_feed_header_daily_login_card_view, this);
        setOrientation(1);
        setGravity(17);
        this.mTitleText = (TextView) inflate.findViewById(R.id.gift_pack_feed_header_card_title);
        this.mBodyText = (TextView) inflate.findViewById(R.id.gift_pack_feed_header_card_body);
        this.mStampRowView = (StampRowView) inflate.findViewById(R.id.gift_pack_feed_stamp_row_view);
    }

    public void setup(CardSpec cardSpec) {
        WishTextViewSpec.applyTextViewSpec(this.mTitleText, cardSpec.getTitleTextSpec());
        WishTextViewSpec.applyTextViewSpec(this.mBodyText, cardSpec.getBodyTextSpec());
        int dimensionPixelSize = WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.daily_login_new_user_gift_pack_header_stamp_size);
        this.mStampRowView.setup(cardSpec.getNumStamps(), true, WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.daily_login_new_user_gift_pack_header_stamp_margin), dimensionPixelSize, dimensionPixelSize);
    }
}
