package android.support.v7.view.menu;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.view.menu.MenuPresenter.Callback;
import android.view.View;
import android.widget.PopupWindow.OnDismissListener;

public class MenuPopupHelper {
    private View mAnchorView;
    private final Context mContext;
    private int mDropDownGravity;
    private boolean mForceShowIcon;
    private final OnDismissListener mInternalOnDismissListener;
    private final MenuBuilder mMenu;
    private OnDismissListener mOnDismissListener;
    private final boolean mOverflowOnly;
    private MenuPopup mPopup;
    private final int mPopupStyleAttr;
    private final int mPopupStyleRes;
    private Callback mPresenterCallback;

    public MenuPopupHelper(Context context, MenuBuilder menuBuilder, View view, boolean z, int i) {
        this(context, menuBuilder, view, z, i, 0);
    }

    public MenuPopupHelper(Context context, MenuBuilder menuBuilder, View view, boolean z, int i, int i2) {
        this.mDropDownGravity = 8388611;
        this.mInternalOnDismissListener = new OnDismissListener() {
            public void onDismiss() {
                MenuPopupHelper.this.onDismiss();
            }
        };
        this.mContext = context;
        this.mMenu = menuBuilder;
        this.mAnchorView = view;
        this.mOverflowOnly = z;
        this.mPopupStyleAttr = i;
        this.mPopupStyleRes = i2;
    }

    public void setOnDismissListener(OnDismissListener onDismissListener) {
        this.mOnDismissListener = onDismissListener;
    }

    public void setAnchorView(View view) {
        this.mAnchorView = view;
    }

    public void setForceShowIcon(boolean z) {
        this.mForceShowIcon = z;
        if (this.mPopup != null) {
            this.mPopup.setForceShowIcon(z);
        }
    }

    public void setGravity(int i) {
        this.mDropDownGravity = i;
    }

    public void show() {
        if (!tryShow()) {
            throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
        }
    }

    public MenuPopup getPopup() {
        if (this.mPopup == null) {
            this.mPopup = createPopup();
        }
        return this.mPopup;
    }

    public boolean tryShow() {
        if (isShowing()) {
            return true;
        }
        if (this.mAnchorView == null) {
            return false;
        }
        showPopup(0, 0, false, false);
        return true;
    }

    public boolean tryShow(int i, int i2) {
        if (isShowing()) {
            return true;
        }
        if (this.mAnchorView == null) {
            return false;
        }
        showPopup(i, i2, true, true);
        return true;
    }

    /* JADX WARNING: type inference failed for: r0v7, types: [android.support.v7.view.menu.MenuPopup] */
    /* JADX WARNING: type inference failed for: r7v0, types: [android.support.v7.view.menu.StandardMenuPopup] */
    /* JADX WARNING: type inference failed for: r1v12, types: [android.support.v7.view.menu.CascadingMenuPopup] */
    /* JADX WARNING: type inference failed for: r7v1, types: [android.support.v7.view.menu.StandardMenuPopup] */
    /* JADX WARNING: type inference failed for: r1v13, types: [android.support.v7.view.menu.CascadingMenuPopup] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r7v1, types: [android.support.v7.view.menu.StandardMenuPopup]
      assigns: [android.support.v7.view.menu.StandardMenuPopup, android.support.v7.view.menu.CascadingMenuPopup]
      uses: [android.support.v7.view.menu.StandardMenuPopup, android.support.v7.view.menu.MenuPopup, android.support.v7.view.menu.CascadingMenuPopup]
      mth insns count: 49
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at jadx.core.dex.visitors.typeinference.TypeSearch$$Lambda$100/871566395.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at jadx.core.dex.visitors.DepthTraversal$$Lambda$34/1534130292.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
    	at jadx.api.JadxDecompiler$$Lambda$28/1037163664.run(Unknown Source)
     */
    /* JADX WARNING: Unknown variable types count: 3 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.support.v7.view.menu.MenuPopup createPopup() {
        /*
            r14 = this;
            android.content.Context r0 = r14.mContext
            java.lang.String r1 = "window"
            java.lang.Object r0 = r0.getSystemService(r1)
            android.view.WindowManager r0 = (android.view.WindowManager) r0
            android.view.Display r0 = r0.getDefaultDisplay()
            android.graphics.Point r1 = new android.graphics.Point
            r1.<init>()
            int r2 = android.os.Build.VERSION.SDK_INT
            r3 = 17
            if (r2 < r3) goto L_0x001d
            r0.getRealSize(r1)
            goto L_0x0020
        L_0x001d:
            r0.getSize(r1)
        L_0x0020:
            int r0 = r1.x
            int r1 = r1.y
            int r0 = java.lang.Math.min(r0, r1)
            android.content.Context r1 = r14.mContext
            android.content.res.Resources r1 = r1.getResources()
            int r2 = android.support.v7.appcompat.R.dimen.abc_cascading_menus_min_smallest_width
            int r1 = r1.getDimensionPixelSize(r2)
            if (r0 < r1) goto L_0x0038
            r0 = 1
            goto L_0x0039
        L_0x0038:
            r0 = 0
        L_0x0039:
            if (r0 == 0) goto L_0x004c
            android.support.v7.view.menu.CascadingMenuPopup r0 = new android.support.v7.view.menu.CascadingMenuPopup
            android.content.Context r2 = r14.mContext
            android.view.View r3 = r14.mAnchorView
            int r4 = r14.mPopupStyleAttr
            int r5 = r14.mPopupStyleRes
            boolean r6 = r14.mOverflowOnly
            r1 = r0
            r1.<init>(r2, r3, r4, r5, r6)
            goto L_0x005e
        L_0x004c:
            android.support.v7.view.menu.StandardMenuPopup r0 = new android.support.v7.view.menu.StandardMenuPopup
            android.content.Context r8 = r14.mContext
            android.support.v7.view.menu.MenuBuilder r9 = r14.mMenu
            android.view.View r10 = r14.mAnchorView
            int r11 = r14.mPopupStyleAttr
            int r12 = r14.mPopupStyleRes
            boolean r13 = r14.mOverflowOnly
            r7 = r0
            r7.<init>(r8, r9, r10, r11, r12, r13)
        L_0x005e:
            android.support.v7.view.menu.MenuBuilder r1 = r14.mMenu
            r0.addMenu(r1)
            android.widget.PopupWindow$OnDismissListener r1 = r14.mInternalOnDismissListener
            r0.setOnDismissListener(r1)
            android.view.View r1 = r14.mAnchorView
            r0.setAnchorView(r1)
            android.support.v7.view.menu.MenuPresenter$Callback r1 = r14.mPresenterCallback
            r0.setCallback(r1)
            boolean r1 = r14.mForceShowIcon
            r0.setForceShowIcon(r1)
            int r1 = r14.mDropDownGravity
            r0.setGravity(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.view.menu.MenuPopupHelper.createPopup():android.support.v7.view.menu.MenuPopup");
    }

    private void showPopup(int i, int i2, boolean z, boolean z2) {
        MenuPopup popup = getPopup();
        popup.setShowTitle(z2);
        if (z) {
            if ((GravityCompat.getAbsoluteGravity(this.mDropDownGravity, ViewCompat.getLayoutDirection(this.mAnchorView)) & 7) == 5) {
                i += this.mAnchorView.getWidth();
            }
            popup.setHorizontalOffset(i);
            popup.setVerticalOffset(i2);
            int i3 = (int) ((this.mContext.getResources().getDisplayMetrics().density * 48.0f) / 2.0f);
            popup.setEpicenterBounds(new Rect(i - i3, i2 - i3, i + i3, i2 + i3));
        }
        popup.show();
    }

    public void dismiss() {
        if (isShowing()) {
            this.mPopup.dismiss();
        }
    }

    /* access modifiers changed from: protected */
    public void onDismiss() {
        this.mPopup = null;
        if (this.mOnDismissListener != null) {
            this.mOnDismissListener.onDismiss();
        }
    }

    public boolean isShowing() {
        return this.mPopup != null && this.mPopup.isShowing();
    }

    public void setPresenterCallback(Callback callback) {
        this.mPresenterCallback = callback;
        if (this.mPopup != null) {
            this.mPopup.setCallback(callback);
        }
    }
}