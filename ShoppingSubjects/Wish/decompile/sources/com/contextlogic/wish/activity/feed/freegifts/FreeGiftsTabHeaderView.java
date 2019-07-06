package com.contextlogic.wish.activity.feed.freegifts;

import android.content.Context;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.feed.BaseFeedHeaderView;
import com.contextlogic.wish.activity.feed.ProductFeedFragment;
import com.contextlogic.wish.api.model.WishFreeGiftTabInfo;
import com.contextlogic.wish.util.DateUtil;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FreeGiftsTabHeaderView extends BaseFeedHeaderView {
    private final Context mContext;
    private ProductFeedFragment mFragment;
    private WishFreeGiftTabInfo mFreeGiftTabInfo;

    public void releaseImages() {
    }

    public void restoreImages() {
    }

    public FreeGiftsTabHeaderView(Context context, ProductFeedFragment productFeedFragment, WishFreeGiftTabInfo wishFreeGiftTabInfo) {
        super(context);
        this.mFragment = productFeedFragment;
        this.mContext = context;
        this.mFreeGiftTabInfo = wishFreeGiftTabInfo;
        init();
    }

    private void init() {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.free_gifts_tab_header_view, this);
        ((TextView) inflate.findViewById(R.id.free_gifts_tab_header_title)).setText(this.mFreeGiftTabInfo.getHeaderTitle());
        ((TextView) inflate.findViewById(R.id.free_gifts_tab_header_subtitle)).setText(this.mFreeGiftTabInfo.getHeaderSubtitle());
        TextView textView = (TextView) inflate.findViewById(R.id.free_gifts_tab_time_remaining);
        String fuzzyTimeRemaining = DateUtil.getFuzzyTimeRemaining(this.mFreeGiftTabInfo.getExpiry());
        Matcher matcher = Pattern.compile("\\d+").matcher(fuzzyTimeRemaining);
        if (matcher.find()) {
            String group = matcher.group();
            SpannableString spannableString = new SpannableString(fuzzyTimeRemaining);
            int indexOf = fuzzyTimeRemaining.indexOf(group);
            if (indexOf > 0) {
                spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.orange)), indexOf, group.length() + indexOf, 33);
                textView.setText(spannableString);
                return;
            }
            textView.setText(fuzzyTimeRemaining);
            return;
        }
        textView.setText(fuzzyTimeRemaining);
    }
}
