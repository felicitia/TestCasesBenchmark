package com.contextlogic.wish.activity.crosspromo;

import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishCrossPromoApp;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.image.NetworkImageView;

public class CrossPromoAdapter extends BaseAdapter {
    private CrossPromoActivity mActivity;
    private CrossPromoFragment mFragment;

    static class ItemRowHolder {
        TextView rowButton;
        NetworkImageView rowImage;
        TextView rowMessage;
        TextView rowTitle;

        ItemRowHolder() {
        }
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public int getItemViewType(int i) {
        return 0;
    }

    public int getViewTypeCount() {
        return 1;
    }

    public boolean isEnabled(int i) {
        return true;
    }

    public CrossPromoAdapter(CrossPromoActivity crossPromoActivity, CrossPromoFragment crossPromoFragment) {
        this.mActivity = crossPromoActivity;
        this.mFragment = crossPromoFragment;
    }

    public int getCount() {
        return this.mFragment.getCrossPromoApps().size();
    }

    public WishCrossPromoApp getItem(int i) {
        return (WishCrossPromoApp) this.mFragment.getCrossPromoApps().get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ItemRowHolder itemRowHolder;
        if (view == null) {
            LayoutInflater layoutInflater = this.mActivity.getLayoutInflater();
            itemRowHolder = new ItemRowHolder();
            view = layoutInflater.inflate(R.layout.cross_promo_fragment_row, viewGroup, false);
            itemRowHolder.rowTitle = (TextView) view.findViewById(R.id.cross_promo_fragment_row_title);
            itemRowHolder.rowMessage = (TextView) view.findViewById(R.id.cross_promo_fragment_row_message);
            itemRowHolder.rowImage = (NetworkImageView) view.findViewById(R.id.cross_promo_fragment_row_image);
            itemRowHolder.rowButton = (TextView) view.findViewById(R.id.cross_promo_fragment_row_button);
            itemRowHolder.rowImage.setPlaceholderColor(WishApplication.getInstance().getResources().getColor(R.color.image_placeholder_background));
            itemRowHolder.rowImage.disableTouchEvents();
            view.setTag(itemRowHolder);
        } else {
            itemRowHolder = (ItemRowHolder) view.getTag();
        }
        WishCrossPromoApp item = getItem(i);
        itemRowHolder.rowTitle.setText(item.getTitle());
        itemRowHolder.rowMessage.setText(item.getMessage());
        itemRowHolder.rowImage.setImageUrl(item.getImageUrl());
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius((float) WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.corner_radius));
        gradientDrawable.setColor(item.getActionButtonColor());
        itemRowHolder.rowButton.setBackgroundDrawable(gradientDrawable);
        itemRowHolder.rowButton.setText(item.getActionButtonText());
        return view;
    }

    public void releaseImages(ListView listView) {
        for (int i = 0; i < listView.getChildCount(); i++) {
            Object tag = listView.getChildAt(i).getTag();
            if (tag != null && (tag instanceof ItemRowHolder)) {
                ((ItemRowHolder) tag).rowImage.releaseImages();
            }
        }
    }

    public void restoreImages(ListView listView) {
        for (int i = 0; i < listView.getChildCount(); i++) {
            Object tag = listView.getChildAt(i).getTag();
            if (tag != null && (tag instanceof ItemRowHolder)) {
                ((ItemRowHolder) tag).rowImage.restoreImages();
            }
        }
    }
}
