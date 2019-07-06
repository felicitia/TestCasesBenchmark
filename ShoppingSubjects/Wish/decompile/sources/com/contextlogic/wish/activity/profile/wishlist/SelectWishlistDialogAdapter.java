package com.contextlogic.wish.activity.profile.wishlist;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishImage;
import com.contextlogic.wish.api.model.WishImage.ImageSize;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.api.model.WishWishlist;
import com.contextlogic.wish.ui.image.NetworkImageView;
import java.util.ArrayList;

public class SelectWishlistDialogAdapter extends ArrayAdapter<WishWishlist> {
    private ArrayList<WishWishlist> mWishlists = new ArrayList<>();

    static class ItemRowHolder {
        NetworkImageView imageView;
        TextView titleText;

        ItemRowHolder() {
        }
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public SelectWishlistDialogAdapter(Context context) {
        super(context, R.layout.select_wishlist_dialog_row);
    }

    public void setWishlists(ArrayList<WishWishlist> arrayList) {
        this.mWishlists = arrayList;
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.mWishlists.size();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ItemRowHolder itemRowHolder;
        if (view == null) {
            view = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.select_wishlist_dialog_row, viewGroup, false);
            itemRowHolder = new ItemRowHolder();
            itemRowHolder.titleText = (TextView) view.findViewById(R.id.fragment_select_wishlist_item_text);
            itemRowHolder.imageView = (NetworkImageView) view.findViewById(R.id.fragment_select_wishlist_item_image);
            itemRowHolder.imageView.setBackgroundColor(getContext().getResources().getColor(R.color.light_gray_3));
            view.setTag(itemRowHolder);
        } else {
            itemRowHolder = (ItemRowHolder) view.getTag();
        }
        itemRowHolder.titleText.setTextColor(getContext().getResources().getColor(R.color.text_primary));
        WishWishlist wishWishlist = (WishWishlist) this.mWishlists.get(i);
        itemRowHolder.imageView.setVisibility(0);
        itemRowHolder.titleText.setGravity(8388627);
        itemRowHolder.titleText.setText(wishWishlist.getName());
        itemRowHolder.imageView.setImage(null);
        itemRowHolder.imageView.setImageBitmap(null);
        if (wishWishlist.getProductPreviews() == null || wishWishlist.getProductPreviews().size() <= 0) {
            itemRowHolder.imageView.setImageResource(R.drawable.select_wishlist_dialog_row_image_empty);
        } else {
            itemRowHolder.imageView.setImage(new WishImage(((WishProduct) wishWishlist.getProductPreviews().get(0)).getImage().getUrlString(ImageSize.MEDIUM)));
        }
        return view;
    }

    public WishWishlist getItem(int i) {
        return (WishWishlist) this.mWishlists.get(i);
    }
}
