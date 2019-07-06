package com.etsy.android.uikit;

import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import com.etsy.android.lib.config.e;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.core.j;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.toolbar.AdminToolbarListener;
import com.etsy.android.lib.toolbar.TakeScreenshotTask;
import com.etsy.android.lib.util.CrashUtil;
import com.etsy.android.lib.util.l;
import com.etsy.android.lib.util.x;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.b;

public abstract class BaseActivity extends AppCompatActivity implements com.etsy.android.lib.toolbar.TakeScreenshotTask.a, b {
    private AppBarHelper mAppBarHelper;
    private c mImageBatch;
    DispatchingAndroidInjector<Fragment> mSupportFragmentInjector;
    private AdminToolbarListener mToolbarListener;
    private boolean mWasDestroyed;

    private class a extends TakeScreenshotTask {
        public a(FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(String str) {
            if (str == null || this.mContext == null) {
                f.d(getClass().getName(), "we lost our activity during screenshot save :/");
            } else {
                com.etsy.android.uikit.ui.bughunt.a.a((FragmentActivity) BaseActivity.this).a(str);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        l.a((Context) this);
        this.mAppBarHelper = new AppBarHelper();
        this.mAppBarHelper.init(this);
        initAdminToolbar();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.mToolbarListener != null) {
            this.mToolbarListener.register();
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        if (this.mToolbarListener != null) {
            try {
                this.mToolbarListener.unregister();
            } catch (IllegalArgumentException e) {
                CrashUtil.a().a((Throwable) e);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.mWasDestroyed = true;
        if (this.mImageBatch != null) {
            this.mImageBatch.a();
        }
    }

    public boolean isDestroyedCompat() {
        if (VERSION.SDK_INT >= 17) {
            return super.isDestroyed();
        }
        return this.mWasDestroyed;
    }

    public void setTitle(CharSequence charSequence) {
        String charSequence2 = charSequence == null ? "" : charSequence.toString();
        super.setTitle(charSequence2);
        this.mAppBarHelper.setTitle((CharSequence) charSequence2);
    }

    /* access modifiers changed from: protected */
    public void initAdminToolbar() {
        if (e.a()) {
            this.mToolbarListener = new AdminToolbarListener(this, this);
        }
    }

    public AppBarHelper getAppBarHelper() {
        return this.mAppBarHelper;
    }

    public void setContentView(int i) {
        super.setContentView(i);
        this.mAppBarHelper.init(this);
    }

    public void setContentView(View view) {
        super.setContentView(view);
        this.mAppBarHelper.init(this);
    }

    public void setContentView(View view, LayoutParams layoutParams) {
        super.setContentView(view, layoutParams);
        this.mAppBarHelper.init(this);
    }

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context context) {
        super.attachBaseContext(com.etsy.android.stylekit.c.a(context));
    }

    public void refreshAppBar() {
        this.mAppBarHelper.init(this);
    }

    public TakeScreenshotTask create(FragmentActivity fragmentActivity) {
        return new a(fragmentActivity);
    }

    public c getImageBatch() {
        if (this.mImageBatch == null) {
            this.mImageBatch = new c();
        }
        return this.mImageBatch;
    }

    public dagger.android.b<Fragment> supportFragmentInjector() {
        return this.mSupportFragmentInjector;
    }

    public j getRequestQueue() {
        return v.a().j();
    }

    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        x.a((Activity) this, i, strArr, iArr);
    }
}
