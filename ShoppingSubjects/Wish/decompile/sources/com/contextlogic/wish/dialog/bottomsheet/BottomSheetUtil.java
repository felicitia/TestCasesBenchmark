package com.contextlogic.wish.dialog.bottomsheet;

import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetBehavior.BottomSheetCallback;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;
import android.widget.FrameLayout;
import com.contextlogic.wish.R;

public class BottomSheetUtil {
    public static void expandDialogFully(final BottomSheetDialog bottomSheetDialog) {
        FrameLayout frameLayout = (FrameLayout) bottomSheetDialog.findViewById(R.id.design_bottom_sheet);
        if (frameLayout != null) {
            BottomSheetBehavior from = BottomSheetBehavior.from(frameLayout);
            from.setState(3);
            from.setBottomSheetCallback(new BottomSheetCallback() {
                public void onSlide(View view, float f) {
                }

                public void onStateChanged(View view, int i) {
                    if (i == 4 || i == 5) {
                        bottomSheetDialog.dismiss();
                    }
                }
            });
        }
    }
}
