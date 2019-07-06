package com.etsy.android.uikit.ui.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.a.p;
import com.etsy.android.lib.logger.f;
import com.etsy.android.uikit.ui.core.TrackingBaseFragment;
import com.etsy.android.uikit.util.j;

public class PermissionDeniedDialogFragment extends TrackingBaseFragment {
    private static final double DIM_AMOUNT = 0.5d;
    private static final String TAG = f.a(PermissionDeniedDialogFragment.class);
    protected IDialogFragment mParentDialog;
    private String mPermission;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mPermission = getArguments().getString("denied_permission");
        if (this.mPermission == null || (!"android.permission.ACCESS_FINE_LOCATION".equals(this.mPermission) && !"android.permission.RECORD_AUDIO".equals(this.mPermission))) {
            f.a(new RuntimeException("Either Location or Microphone permission must be specified"));
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(k.fragment_swiper_permanently_denied_dialog, viewGroup, false);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (getParentFragment() instanceof IDialogFragment) {
            this.mParentDialog = (IDialogFragment) getParentFragment();
            this.mParentDialog.hideHeader();
            this.mParentDialog.setWindowAnimation(p.DialogAnimBottom);
            this.mParentDialog.setWindowBackgroundDim(0.5f);
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        setupBackground(view);
        showPermanentlyDenied(view);
    }

    private void setupBackground(View view) {
        final ImageView imageView = (ImageView) view.findViewById(i.image_background);
        final FrameLayout frameLayout = (FrameLayout) imageView.getParent();
        ViewTreeObserver viewTreeObserver = frameLayout.getViewTreeObserver();
        if (viewTreeObserver != null) {
            viewTreeObserver.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
                public void onGlobalLayout() {
                    j.b(frameLayout.getViewTreeObserver(), (OnGlobalLayoutListener) this);
                    imageView.setLayoutParams(new LayoutParams(frameLayout.getWidth(), frameLayout.getHeight()));
                    imageView.setScaleType(ScaleType.CENTER);
                    imageView.setAdjustViewBounds(false);
                    imageView.invalidate();
                }
            });
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x005a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void showPermanentlyDenied(android.view.View r7) {
        /*
            r6 = this;
            int r0 = com.etsy.android.lib.a.i.permission_message
            android.view.View r0 = r7.findViewById(r0)
            android.widget.TextView r0 = (android.widget.TextView) r0
            int r1 = com.etsy.android.lib.a.i.permission_to_request
            android.view.View r1 = r7.findViewById(r1)
            android.widget.TextView r1 = (android.widget.TextView) r1
            int r2 = com.etsy.android.lib.a.i.permission_icon
            android.view.View r2 = r7.findViewById(r2)
            com.etsy.android.iconsy.views.IconView r2 = (com.etsy.android.iconsy.views.IconView) r2
            java.lang.String r3 = r6.mPermission
            int r4 = r3.hashCode()
            r5 = -1888586689(0xffffffff8f6e743f, float:-1.1756694E-29)
            if (r4 == r5) goto L_0x0033
            r5 = 1831139720(0x6d24f988, float:3.1910754E27)
            if (r4 == r5) goto L_0x0029
            goto L_0x003d
        L_0x0029:
            java.lang.String r4 = "android.permission.RECORD_AUDIO"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x003d
            r3 = 0
            goto L_0x003e
        L_0x0033:
            java.lang.String r4 = "android.permission.ACCESS_FINE_LOCATION"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x003d
            r3 = 1
            goto L_0x003e
        L_0x003d:
            r3 = -1
        L_0x003e:
            switch(r3) {
                case 0: goto L_0x005a;
                case 1: goto L_0x0042;
                default: goto L_0x0041;
            }
        L_0x0041:
            goto L_0x0071
        L_0x0042:
            int r3 = com.etsy.android.lib.a.o.permission_location_denied
            java.lang.String r3 = r6.getString(r3)
            r0.setText(r3)
            int r0 = com.etsy.android.lib.a.o.permisson_location_to_request
            java.lang.String r0 = r6.getString(r0)
            r1.setText(r0)
            com.etsy.android.lib.util.fonts.StandardFontIcon r0 = com.etsy.android.lib.util.fonts.StandardFontIcon.LOCATION
            r2.setIcon(r0)
            goto L_0x0071
        L_0x005a:
            int r3 = com.etsy.android.lib.a.o.permission_microphone_denied
            java.lang.String r3 = r6.getString(r3)
            r0.setText(r3)
            int r0 = com.etsy.android.lib.a.o.permisson_to_request_microphone
            java.lang.String r0 = r6.getString(r0)
            r1.setText(r0)
            com.etsy.android.lib.util.fonts.StandardFontIcon r0 = com.etsy.android.lib.util.fonts.StandardFontIcon.MICROPHONE
            r2.setIcon(r0)
        L_0x0071:
            int r0 = com.etsy.android.lib.a.i.dialog_yes
            android.view.View r7 = r7.findViewById(r0)
            android.widget.TextView r7 = (android.widget.TextView) r7
            com.etsy.android.uikit.ui.dialog.PermissionDeniedDialogFragment$2 r0 = new com.etsy.android.uikit.ui.dialog.PermissionDeniedDialogFragment$2
            r0.<init>()
            r7.setOnClickListener(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.etsy.android.uikit.ui.dialog.PermissionDeniedDialogFragment.showPermanentlyDenied(android.view.View):void");
    }
}
