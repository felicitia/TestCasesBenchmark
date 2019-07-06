package com.etsy.android.uikit.ui.dialog.text;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;
import com.etsy.android.uikit.ui.core.TrackingBaseFragment;
import com.etsy.android.uikit.util.EtsyLinkify;

public class TextInfoDialog extends TrackingBaseFragment {
    private String mContent;
    private TextView mText;

    @NonNull
    public String getTrackingName() {
        return "popup_help";
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContent = getArguments().getString("text");
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(k.fragment_text_info, viewGroup, false);
        this.mText = (TextView) inflate.findViewById(i.text);
        this.mText.setText(this.mContent);
        EtsyLinkify.a((Context) getActivity(), this.mText);
        return inflate;
    }
}
