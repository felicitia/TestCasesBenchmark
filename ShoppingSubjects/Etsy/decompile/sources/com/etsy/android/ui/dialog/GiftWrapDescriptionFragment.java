package com.etsy.android.ui.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.models.apiv3.Image;
import com.etsy.android.ui.EtsyFragment;
import com.etsy.android.uikit.view.FullImageView;

public class GiftWrapDescriptionFragment extends EtsyFragment {
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_gift_wrap_description, viewGroup, false);
        buildView(inflate, getActivity().getIntent().getStringExtra("gift_info_description"), (Image) getActivity().getIntent().getExtras().get("gift_info_preview"));
        return inflate;
    }

    private void buildView(View view, String str, Image image) {
        FullImageView fullImageView = (FullImageView) view.findViewById(R.id.gift_wrap_photo);
        if (image != null) {
            fullImageView.setImageInfo(image, getImageBatch());
        } else {
            fullImageView.setVisibility(8);
        }
        ((TextView) view.findViewById(R.id.gift_wrap_description)).setText(str);
    }
}
