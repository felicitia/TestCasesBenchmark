package com.etsy.android.uikit.navigationview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.view.menu.MenuPresenter.Callback;
import android.support.v7.view.menu.MenuView;
import android.support.v7.view.menu.SubMenuBuilder;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.etsy.android.lib.a.e;
import com.etsy.android.lib.a.f;
import com.etsy.android.lib.a.g;
import com.etsy.android.lib.a.k;
import com.etsy.android.uikit.c;
import java.util.ArrayList;

public class EtsyNavigationMenuPresenter implements MenuPresenter, OnItemClickListener {
    private static final String STATE_HIERARCHY = "android:menu:list";
    private a mAdapter;
    private Callback mCallback;
    private LinearLayout mHeader;
    /* access modifiers changed from: private */
    public ColorStateList mIconTintList;
    private int mId;
    /* access modifiers changed from: private */
    public Drawable mItemBackground;
    /* access modifiers changed from: private */
    public LayoutInflater mLayoutInflater;
    /* access modifiers changed from: private */
    public MenuBuilder mMenu;
    /* access modifiers changed from: private */
    public EtsyNavigationMenuView mMenuView;
    /* access modifiers changed from: private */
    public int mPaddingSeparator;
    private int mPaddingTopDefault;
    /* access modifiers changed from: private */
    public ColorStateList mTextColor;

    private class a extends BaseAdapter {
        private final ArrayList<b> b = new ArrayList<>();
        private ColorDrawable c;

        public boolean areAllItemsEnabled() {
            return false;
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public int getViewTypeCount() {
            return 3;
        }

        a() {
            a();
        }

        public int getCount() {
            return this.b.size();
        }

        /* renamed from: a */
        public b getItem(int i) {
            return (b) this.b.get(i);
        }

        public int getItemViewType(int i) {
            b a2 = getItem(i);
            if (a2.a()) {
                return 2;
            }
            return a2.d().hasSubMenu() ? 1 : 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            b a2 = getItem(i);
            switch (getItemViewType(i)) {
                case 0:
                    if (view == null) {
                        view = EtsyNavigationMenuPresenter.this.mLayoutInflater.inflate(k.etsy_navigation_item, viewGroup, false);
                    }
                    EtsyNavigationMenuItemView etsyNavigationMenuItemView = (EtsyNavigationMenuItemView) view;
                    etsyNavigationMenuItemView.setIconTintList(EtsyNavigationMenuPresenter.this.mIconTintList);
                    etsyNavigationMenuItemView.setTextColor(EtsyNavigationMenuPresenter.this.mTextColor);
                    etsyNavigationMenuItemView.setBackgroundDrawable(EtsyNavigationMenuPresenter.this.mItemBackground);
                    etsyNavigationMenuItemView.initialize(a2.d(), 0);
                    break;
                case 1:
                    if (view == null) {
                        view = EtsyNavigationMenuPresenter.this.mLayoutInflater.inflate(k.design_navigation_item_subheader, viewGroup, false);
                    }
                    ((TextView) view).setText(a2.d().getTitle());
                    break;
                case 2:
                    if (view == null) {
                        view = EtsyNavigationMenuPresenter.this.mLayoutInflater.inflate(k.etsy_navigation_item_separator, viewGroup, false);
                    }
                    view.setPadding(0, a2.b(), 0, a2.c());
                    break;
            }
            return view;
        }

        public boolean isEnabled(int i) {
            return getItem(i).e();
        }

        public void notifyDataSetChanged() {
            a();
            super.notifyDataSetChanged();
        }

        @SuppressLint({"RestrictedApi"})
        private void a() {
            this.b.clear();
            int size = EtsyNavigationMenuPresenter.this.mMenu.getVisibleItems().size();
            boolean z = false;
            int i = 0;
            int i2 = -1;
            for (int i3 = 0; i3 < size; i3++) {
                MenuItem menuItem = (MenuItem) EtsyNavigationMenuPresenter.this.mMenu.getVisibleItems().get(i3);
                if (!menuItem.hasSubMenu()) {
                    int groupId = menuItem.getGroupId();
                    if (groupId != i2) {
                        i = this.b.size();
                        z = menuItem.getIcon() != null;
                        if (i3 != 0) {
                            i++;
                            this.b.add(b.a(EtsyNavigationMenuPresenter.this.mPaddingSeparator, EtsyNavigationMenuPresenter.this.mPaddingSeparator));
                        }
                    } else if (!z && menuItem.getIcon() != null) {
                        a(i, this.b.size());
                        z = true;
                    }
                    if (z && menuItem.getIcon() == null) {
                        menuItem.setIcon(c.a(EtsyNavigationMenuPresenter.this.mMenuView.getContext(), g.sk_ic_close, e.sk_gray_70));
                    }
                    this.b.add(b.a(menuItem));
                    i2 = groupId;
                } else {
                    SubMenu subMenu = menuItem.getSubMenu();
                    if (subMenu.hasVisibleItems()) {
                        if (i3 != 0) {
                            this.b.add(b.a(EtsyNavigationMenuPresenter.this.mPaddingSeparator, 0));
                        }
                        this.b.add(b.a(menuItem));
                        int size2 = this.b.size();
                        int size3 = subMenu.size();
                        boolean z2 = false;
                        for (int i4 = 0; i4 < size3; i4++) {
                            MenuItem item = subMenu.getItem(i4);
                            if (item.isVisible()) {
                                if (!z2 && item.getIcon() != null) {
                                    z2 = true;
                                }
                                this.b.add(b.a(item));
                            }
                        }
                        if (z2) {
                            a(size2, this.b.size());
                        }
                    }
                }
            }
        }

        private void a(int i, int i2) {
            while (i < i2) {
                MenuItem d = ((b) this.b.get(i)).d();
                if (d.getIcon() == null) {
                    if (this.c == null) {
                        this.c = new ColorDrawable(17170445);
                    }
                    d.setIcon(this.c);
                }
                i++;
            }
        }
    }

    public static class b {
        private final MenuItem a;
        private final int b;
        private final int c;

        private b(MenuItem menuItem, int i, int i2) {
            this.a = menuItem;
            this.b = i;
            this.c = i2;
        }

        public static b a(MenuItem menuItem) {
            return new b(menuItem, 0, 0);
        }

        public static b a(int i, int i2) {
            return new b(null, i, i2);
        }

        public boolean a() {
            return this.a == null;
        }

        public int b() {
            return this.b;
        }

        public int c() {
            return this.c;
        }

        public MenuItem d() {
            return this.a;
        }

        public boolean e() {
            return this.a != null && !this.a.hasSubMenu() && this.a.isEnabled();
        }
    }

    public boolean collapseItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    public boolean expandItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    public boolean flagActionItems() {
        return false;
    }

    public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
        return false;
    }

    public void initForMenu(Context context, MenuBuilder menuBuilder) {
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mMenu = menuBuilder;
        Resources resources = context.getResources();
        this.mPaddingTopDefault = resources.getDimensionPixelOffset(f.design_navigation_padding_top_default);
        this.mPaddingSeparator = resources.getDimensionPixelOffset(f.design_navigation_separator_vertical_padding);
    }

    public MenuView getMenuView(ViewGroup viewGroup) {
        if (this.mMenuView == null) {
            this.mMenuView = (EtsyNavigationMenuView) this.mLayoutInflater.inflate(k.etsy_navigation_menu, viewGroup, false);
            if (this.mAdapter == null) {
                this.mAdapter = new a();
            }
            this.mHeader = (LinearLayout) this.mLayoutInflater.inflate(k.design_navigation_item_header, this.mMenuView, false);
            this.mMenuView.addHeaderView(this.mHeader);
            this.mMenuView.setAdapter(this.mAdapter);
            this.mMenuView.setOnItemClickListener(this);
        }
        return this.mMenuView;
    }

    public void updateMenuView(boolean z) {
        if (this.mAdapter != null) {
            this.mAdapter.notifyDataSetChanged();
        }
    }

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
        if (this.mCallback != null) {
            this.mCallback.onCloseMenu(menuBuilder, z);
        }
    }

    public int getId() {
        return this.mId;
    }

    public void setId(int i) {
        this.mId = i;
    }

    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        SparseArray sparseArray = new SparseArray();
        if (this.mMenuView != null) {
            this.mMenuView.saveHierarchyState(sparseArray);
        }
        bundle.putSparseParcelableArray("android:menu:list", sparseArray);
        return bundle;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        SparseArray sparseParcelableArray = ((Bundle) parcelable).getSparseParcelableArray("android:menu:list");
        if (sparseParcelableArray != null) {
            this.mMenuView.restoreHierarchyState(sparseParcelableArray);
        }
    }

    @SuppressLint({"RestrictedApi"})
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        int headerViewsCount = i - this.mMenuView.getHeaderViewsCount();
        if (headerViewsCount >= 0) {
            this.mMenu.performItemAction(this.mAdapter.getItem(headerViewsCount).d(), this, 0);
        }
    }

    public View inflateHeaderView(@LayoutRes int i) {
        View inflate = this.mLayoutInflater.inflate(i, this.mHeader, false);
        addHeaderView(inflate);
        return inflate;
    }

    public void addHeaderView(@NonNull View view) {
        this.mHeader.addView(view);
        this.mMenuView.setPadding(0, 0, 0, this.mMenuView.getPaddingBottom());
    }

    public void removeHeaderView(@NonNull View view) {
        this.mHeader.removeView(view);
        if (this.mHeader.getChildCount() == 0) {
            this.mMenuView.setPadding(0, this.mPaddingTopDefault, 0, this.mMenuView.getPaddingBottom());
        }
    }

    @Nullable
    public ColorStateList getItemTintList() {
        return this.mIconTintList;
    }

    public void setItemIconTintList(@Nullable ColorStateList colorStateList) {
        this.mIconTintList = colorStateList;
    }

    @Nullable
    public ColorStateList getItemTextColor() {
        return this.mTextColor;
    }

    public void setItemTextColor(@Nullable ColorStateList colorStateList) {
        this.mTextColor = colorStateList;
    }

    public Drawable getItemBackground() {
        return this.mItemBackground;
    }

    public void setItemBackground(Drawable drawable) {
        this.mItemBackground = drawable;
    }
}
