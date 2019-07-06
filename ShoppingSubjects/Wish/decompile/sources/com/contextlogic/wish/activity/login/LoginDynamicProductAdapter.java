package com.contextlogic.wish.activity.login;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView.ScaleType;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishImage;
import com.contextlogic.wish.ui.image.NetworkImageView;
import java.util.ArrayList;

public class LoginDynamicProductAdapter extends BaseLoginProductAdapter {
    private Context mContext;
    private final ArrayList<WishImage> mProductImages;

    public int getItemHeight(int i, int i2) {
        return i2;
    }

    public LoginDynamicProductAdapter(Context context, ArrayList<WishImage> arrayList) {
        this.mContext = context;
        this.mProductImages = arrayList;
    }

    public int getCount() {
        return this.mContext.getResources().getInteger(R.integer.login_product_count) * 10;
    }

    public WishImage getItem(int i) {
        return (WishImage) this.mProductImages.get(i % this.mProductImages.size());
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        NetworkImageView networkImageView;
        if (view != null) {
            networkImageView = (NetworkImageView) view;
        } else {
            networkImageView = new NetworkImageView(this.mContext);
            networkImageView.setScaleType(ScaleType.CENTER_CROP);
        }
        WishImage item = getItem(i);
        if (item != null) {
            networkImageView.setImage(item);
        }
        scaleDownView(networkImageView);
        return networkImageView;
    }
}
