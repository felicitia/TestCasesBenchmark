package com.contextlogic.wish.activity.feed.dailyraffle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishImage;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;

public class EnterRaffleDialogFragment<A extends BaseActivity> extends BaseDialogFragment<A> {
    private static String ARGUMENT_ITEM_IMG = "ArgumentRaffleImage";
    private static String ARGUMENT_POPUP_MSG = "ArgumentPopupMsg";
    private static String ARGUMENT_POPUP_TITLE = "ArgumentPopupTitle";
    private static String ARGUMENT_TICKET_NUM = "ArgumentTicketNum";

    public static EnterRaffleDialogFragment<BaseActivity> createEnterRaffleDialogFragment(WishImage wishImage, String str, String str2, int i) {
        EnterRaffleDialogFragment<BaseActivity> enterRaffleDialogFragment = new EnterRaffleDialogFragment<>();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARGUMENT_ITEM_IMG, wishImage);
        bundle.putString(ARGUMENT_POPUP_TITLE, str);
        bundle.putString(ARGUMENT_POPUP_MSG, str2);
        bundle.putInt(ARGUMENT_TICKET_NUM, i);
        enterRaffleDialogFragment.setArguments(bundle);
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_DAILY_ENTER_RAFFLE);
        return enterRaffleDialogFragment;
    }

    public View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.enter_daily_raffle_dialog, viewGroup);
        ThemedTextView themedTextView = (ThemedTextView) inflate.findViewById(R.id.daily_raffle_continue_shopping_button);
        TextView textView = (TextView) inflate.findViewById(R.id.daily_raffle_ticket_number_text);
        NetworkImageView networkImageView = (NetworkImageView) inflate.findViewById(R.id.daily_raffle_dialog_ticket_product_img);
        ThemedTextView themedTextView2 = (ThemedTextView) inflate.findViewById(R.id.daily_raffle_dialog_title);
        ThemedTextView themedTextView3 = (ThemedTextView) inflate.findViewById(R.id.daily_raffle_dialog_msg);
        ((ImageView) inflate.findViewById(R.id.daily_raffle_x_close)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                EnterRaffleDialogFragment.this.cancel();
            }
        });
        themedTextView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                EnterRaffleDialogFragment.this.cancel();
            }
        });
        Bundle arguments = getArguments();
        if (arguments != null) {
            WishImage wishImage = (WishImage) arguments.getParcelable(ARGUMENT_ITEM_IMG);
            if (wishImage != null) {
                networkImageView.setImage(wishImage);
            }
            themedTextView2.setText(arguments.getString(ARGUMENT_POPUP_TITLE));
            themedTextView3.setText(arguments.getString(ARGUMENT_POPUP_MSG));
            textView.setText(getString(R.string.daily_raffle_ticket_number, Integer.valueOf(arguments.getInt(ARGUMENT_TICKET_NUM))));
        }
        return inflate;
    }
}
