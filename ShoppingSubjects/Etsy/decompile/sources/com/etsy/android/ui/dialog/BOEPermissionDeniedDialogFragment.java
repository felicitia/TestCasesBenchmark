package com.etsy.android.ui.dialog;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.uikit.ui.dialog.PermissionDeniedDialogFragment;

public class BOEPermissionDeniedDialogFragment extends PermissionDeniedDialogFragment {
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        ((ImageView) view.findViewById(R.id.app_icon)).setImageResource(R.drawable.ic_launcher);
        ((TextView) view.findViewById(R.id.permission_message)).setText(R.string.local_permission_location_denied);
        ((TextView) view.findViewById(R.id.permission_go_to_settings_line)).setText(R.string.etsy_settings);
    }
}
