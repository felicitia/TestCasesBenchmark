package com.contextlogic.wish.dialog.freegift;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.ui.image.NetworkImageView;

public class GiftConfirmedDialogFragment<A extends BaseActivity> extends BaseDialogFragment<A> {
    public boolean isCancelable() {
        return false;
    }

    public static GiftConfirmedDialogFragment<BaseActivity> createGiftConfirmedDialog(WishProduct wishProduct, String str, String str2, String str3) {
        GiftConfirmedDialogFragment<BaseActivity> giftConfirmedDialogFragment = new GiftConfirmedDialogFragment<>();
        Bundle bundle = new Bundle();
        bundle.putParcelable("ArgumentProduct", wishProduct);
        bundle.putString("ArgumentTitle", str);
        bundle.putString("ArgumentDescription", str2);
        bundle.putString("ArgumentButton", str3);
        giftConfirmedDialogFragment.setArguments(bundle);
        return giftConfirmedDialogFragment;
    }

    public View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Bundle arguments = getArguments();
        WishProduct wishProduct = (WishProduct) arguments.getParcelable("ArgumentProduct");
        String string = arguments.getString("ArgumentTitle");
        String string2 = arguments.getString("ArgumentDescription");
        String string3 = arguments.getString("ArgumentButton");
        View inflate = layoutInflater.inflate(R.layout.gift_confirmed_dialog_fragment, viewGroup, false);
        TextView textView = (TextView) inflate.findViewById(R.id.gift_confirmed_dialog_fragment_title);
        TextView textView2 = (TextView) inflate.findViewById(R.id.gift_confirmed_dialog_fragment_description);
        Button button = (Button) inflate.findViewById(R.id.gift_confirmed_dialog_fragment_button);
        ((NetworkImageView) inflate.findViewById(R.id.gift_confirmed_dialog_fragment_image)).setImage(wishProduct.getImage());
        textView.setText(string);
        textView2.setText(string2);
        button.setText(string3);
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                GiftConfirmedDialogFragment.this.cancel();
            }
        });
        return inflate;
    }

    public int getMaxDialogWidth() {
        return getResources().getDimensionPixelSize(R.dimen.gift_confirmed_dialog_max_width);
    }
}
