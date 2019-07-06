package com.contextlogic.wish.activity.login;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView.ScaleType;
import com.contextlogic.wish.R;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;

public class LoginProductAdapter extends BaseLoginProductAdapter {
    private Context mContext;

    public int getItemHeight(int i, int i2) {
        return i2;
    }

    public LoginProductAdapter(Context context) {
        this.mContext = context;
    }

    public int getCount() {
        return this.mContext.getResources().getInteger(R.integer.login_product_count) * 10;
    }

    public Integer getItem(int i) {
        int integer = i % this.mContext.getResources().getInteger(R.integer.login_product_count);
        Resources resources = this.mContext.getResources();
        StringBuilder sb = new StringBuilder();
        sb.append("login_product_");
        sb.append(integer);
        int identifier = resources.getIdentifier(sb.toString(), "drawable", this.mContext.getPackageName());
        if (identifier != 0) {
            return Integer.valueOf(identifier);
        }
        return null;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        AutoReleasableImageView autoReleasableImageView;
        if (view != null) {
            autoReleasableImageView = (AutoReleasableImageView) view;
        } else {
            autoReleasableImageView = new AutoReleasableImageView(this.mContext);
            autoReleasableImageView.setScaleType(ScaleType.CENTER_CROP);
        }
        Integer item = getItem(i);
        if (item != null) {
            autoReleasableImageView.setImageResource(item.intValue());
        }
        scaleDownView(autoReleasableImageView);
        return autoReleasableImageView;
    }
}
