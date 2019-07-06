package com.contextlogic.wish.activity.productdetails.bundles;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishProduct;
import java.util.ArrayList;

public class BundlesProductInfoView extends LinearLayout {
    private ArrayList<WishProduct> mBundledProducts;
    ArrayList<BundlesProductInfoRowView> mRowViews;

    public BundlesProductInfoView(Context context) {
        super(context);
    }

    public void setup(ArrayList<WishProduct> arrayList, Context context, ArrayList<BundlesViewConnector> arrayList2) {
        this.mBundledProducts = arrayList;
        int dimension = (int) getResources().getDimension(R.dimen.screen_padding);
        setPadding((int) getResources().getDimension(R.dimen.half_screen_padding), dimension, dimension, dimension);
        setOrientation(1);
        this.mRowViews = new ArrayList<>();
        setBackgroundResource(R.drawable.bundles_mid_border);
        setLayoutParams(new LayoutParams(-1, -2));
        for (int i = 0; i < arrayList.size(); i++) {
            BundlesProductInfoRowView bundlesProductInfoRowView = new BundlesProductInfoRowView(context);
            bundlesProductInfoRowView.setup((WishProduct) arrayList.get(i), i, arrayList2);
            this.mRowViews.add(bundlesProductInfoRowView);
            addView(bundlesProductInfoRowView);
        }
    }

    public ArrayList<WishProduct> getSelectedProducts() {
        ArrayList<WishProduct> arrayList = new ArrayList<>();
        for (int i = 0; i < this.mRowViews.size(); i++) {
            if (((BundlesProductInfoRowView) this.mRowViews.get(i)).isChecked()) {
                arrayList.add(this.mBundledProducts.get(i));
            }
        }
        return arrayList;
    }
}
