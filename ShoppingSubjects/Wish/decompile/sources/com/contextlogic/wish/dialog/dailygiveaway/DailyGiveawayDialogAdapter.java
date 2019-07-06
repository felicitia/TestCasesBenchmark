package com.contextlogic.wish.dialog.dailygiveaway;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishImage;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.image.NetworkImageView.ResizeType;
import com.contextlogic.wish.ui.listview.HorizontalListView.Adapter;
import java.util.ArrayList;

public class DailyGiveawayDialogAdapter extends Adapter {
    private Context mContext;
    private ArrayList<WishImage> mGiveaways;

    public DailyGiveawayDialogAdapter(Context context, ArrayList<WishImage> arrayList) {
        this.mContext = context;
        this.mGiveaways = arrayList;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        NetworkImageView networkImageView;
        FrameLayout frameLayout;
        if (view != null) {
            frameLayout = (FrameLayout) view;
            networkImageView = (NetworkImageView) frameLayout.getTag();
        } else {
            frameLayout = new FrameLayout(this.mContext);
            frameLayout.setBackgroundResource(R.color.cool_gray4);
            int dimensionPixelOffset = WishApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.daily_giveaway_notification_image_border);
            frameLayout.setPadding(dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset);
            networkImageView = new NetworkImageView(this.mContext);
            frameLayout.addView(networkImageView);
            frameLayout.setTag(networkImageView);
        }
        networkImageView.setImage(getItem(i), ResizeType.FIT);
        return frameLayout;
    }

    public int getItemWidth(int i) {
        return (int) WishApplication.getInstance().getResources().getDimension(R.dimen.daily_giveaway_notification_image_size);
    }

    public int getItemHeight(int i) {
        return (int) WishApplication.getInstance().getResources().getDimension(R.dimen.daily_giveaway_notification_image_size);
    }

    public int getCount() {
        return this.mGiveaways.size();
    }

    public WishImage getItem(int i) {
        return (WishImage) this.mGiveaways.get(i);
    }
}
