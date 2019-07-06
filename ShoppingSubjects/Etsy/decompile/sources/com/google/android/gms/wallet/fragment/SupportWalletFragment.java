package com.google.android.gms.wallet.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import com.etsy.android.lib.models.ResponseConstants;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.SupportFragmentWrapper;
import com.google.android.gms.internal.wallet.f;
import com.google.android.gms.internal.wallet.zzl;
import com.google.android.gms.internal.wallet.zzp;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;

public final class SupportWalletFragment extends Fragment {
    /* access modifiers changed from: private */
    public final Fragment fragment = this;
    /* access modifiers changed from: private */
    public c zzfb;
    /* access modifiers changed from: private */
    public boolean zzfc = false;
    /* access modifiers changed from: private */
    public final SupportFragmentWrapper zzfd = SupportFragmentWrapper.wrap(this);
    private final d zzfe = new d();
    /* access modifiers changed from: private */
    public b zzff = new b(this);
    /* access modifiers changed from: private */
    public WalletFragmentOptions zzfg;
    /* access modifiers changed from: private */
    public WalletFragmentInitParams zzfh;
    /* access modifiers changed from: private */
    public MaskedWalletRequest zzfi;
    /* access modifiers changed from: private */
    public MaskedWallet zzfj;
    /* access modifiers changed from: private */
    public Boolean zzfk;

    public interface a {
        void a(SupportWalletFragment supportWalletFragment, int i, int i2, Bundle bundle);
    }

    static class b extends zzp {
        private a a;
        private final SupportWalletFragment b;

        b(SupportWalletFragment supportWalletFragment) {
            this.b = supportWalletFragment;
        }

        public final void a(a aVar) {
            this.a = aVar;
        }

        public final void zza(int i, int i2, Bundle bundle) {
            if (this.a != null) {
                this.a.a(this.b, i, i2, bundle);
            }
        }
    }

    private static class c implements com.google.android.gms.dynamic.b {
        private final zzl a;

        private c(zzl zzl) {
            this.a = zzl;
        }

        /* access modifiers changed from: private */
        public final void a(int i, int i2, Intent intent) {
            try {
                this.a.onActivityResult(i, i2, intent);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        /* access modifiers changed from: private */
        public final void a(MaskedWallet maskedWallet) {
            try {
                this.a.updateMaskedWallet(maskedWallet);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        /* access modifiers changed from: private */
        public final void a(MaskedWalletRequest maskedWalletRequest) {
            try {
                this.a.updateMaskedWalletRequest(maskedWalletRequest);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        /* access modifiers changed from: private */
        public final void a(WalletFragmentInitParams walletFragmentInitParams) {
            try {
                this.a.initialize(walletFragmentInitParams);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        /* access modifiers changed from: private */
        public final void a(boolean z) {
            try {
                this.a.setEnabled(z);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        /* access modifiers changed from: private */
        public final int h() {
            try {
                return this.a.getState();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        public final View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            try {
                return (View) ObjectWrapper.unwrap(this.a.onCreateView(ObjectWrapper.wrap(layoutInflater), ObjectWrapper.wrap(viewGroup), bundle));
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        public final void a() {
            try {
                this.a.onStart();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        public final void a(Activity activity, Bundle bundle, Bundle bundle2) {
            try {
                this.a.zza(ObjectWrapper.wrap(activity), (WalletFragmentOptions) bundle.getParcelable("extraWalletFragmentOptions"), bundle2);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        public final void a(Bundle bundle) {
            try {
                this.a.onCreate(bundle);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        public final void b() {
            try {
                this.a.onResume();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        public final void b(Bundle bundle) {
            try {
                this.a.onSaveInstanceState(bundle);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        public final void c() {
            try {
                this.a.onPause();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        public final void d() {
            try {
                this.a.onStop();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        public final void e() {
        }

        public final void f() {
        }

        public final void g() {
        }
    }

    private class d extends com.google.android.gms.dynamic.a<c> implements OnClickListener {
        private d() {
        }

        /* access modifiers changed from: protected */
        public final void a(FrameLayout frameLayout) {
            Button button = new Button(SupportWalletFragment.this.fragment.getActivity());
            button.setText(com.google.android.gms.wallet.c.a.wallet_buy_button_place_holder);
            int i = -2;
            int i2 = -1;
            if (SupportWalletFragment.this.zzfg != null) {
                WalletFragmentStyle fragmentStyle = SupportWalletFragment.this.zzfg.getFragmentStyle();
                if (fragmentStyle != null) {
                    DisplayMetrics displayMetrics = SupportWalletFragment.this.fragment.getResources().getDisplayMetrics();
                    i2 = fragmentStyle.zza("buyButtonWidth", displayMetrics, -1);
                    i = fragmentStyle.zza("buyButtonHeight", displayMetrics, -2);
                }
            }
            button.setLayoutParams(new LayoutParams(i2, i));
            button.setOnClickListener(this);
            frameLayout.addView(button);
        }

        /* access modifiers changed from: protected */
        public final void a(com.google.android.gms.dynamic.c<c> cVar) {
            FragmentActivity activity = SupportWalletFragment.this.fragment.getActivity();
            if (SupportWalletFragment.this.zzfb == null && SupportWalletFragment.this.zzfc && activity != null) {
                try {
                    SupportWalletFragment.this.zzfb = new c(f.a(activity, SupportWalletFragment.this.zzfd, SupportWalletFragment.this.zzfg, SupportWalletFragment.this.zzff));
                    SupportWalletFragment.this.zzfg = null;
                    cVar.a(SupportWalletFragment.this.zzfb);
                    if (SupportWalletFragment.this.zzfh != null) {
                        SupportWalletFragment.this.zzfb.a(SupportWalletFragment.this.zzfh);
                        SupportWalletFragment.this.zzfh = null;
                    }
                    if (SupportWalletFragment.this.zzfi != null) {
                        SupportWalletFragment.this.zzfb.a(SupportWalletFragment.this.zzfi);
                        SupportWalletFragment.this.zzfi = null;
                    }
                    if (SupportWalletFragment.this.zzfj != null) {
                        SupportWalletFragment.this.zzfb.a(SupportWalletFragment.this.zzfj);
                        SupportWalletFragment.this.zzfj = null;
                    }
                    if (SupportWalletFragment.this.zzfk != null) {
                        SupportWalletFragment.this.zzfb.a(SupportWalletFragment.this.zzfk.booleanValue());
                        SupportWalletFragment.this.zzfk = null;
                    }
                } catch (GooglePlayServicesNotAvailableException unused) {
                }
            }
        }

        public final void onClick(View view) {
            FragmentActivity activity = SupportWalletFragment.this.fragment.getActivity();
            GooglePlayServicesUtil.showErrorDialogFragment(GooglePlayServicesUtil.isGooglePlayServicesAvailable(activity, GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE), activity, -1);
        }
    }

    public static SupportWalletFragment newInstance(WalletFragmentOptions walletFragmentOptions) {
        SupportWalletFragment supportWalletFragment = new SupportWalletFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("extraWalletFragmentOptions", walletFragmentOptions);
        supportWalletFragment.fragment.setArguments(bundle);
        return supportWalletFragment;
    }

    public final int getState() {
        if (this.zzfb != null) {
            return this.zzfb.h();
        }
        return 0;
    }

    public final void initialize(WalletFragmentInitParams walletFragmentInitParams) {
        if (this.zzfb != null) {
            this.zzfb.a(walletFragmentInitParams);
            this.zzfh = null;
            return;
        }
        if (this.zzfh == null) {
            this.zzfh = walletFragmentInitParams;
            if (this.zzfi != null) {
                Log.w("SupportWalletFragment", "updateMaskedWalletRequest() was called before initialize()");
            }
            if (this.zzfj != null) {
                Log.w("SupportWalletFragment", "updateMaskedWallet() was called before initialize()");
            }
        } else {
            Log.w("SupportWalletFragment", "initialize(WalletFragmentInitParams) was called more than once. Ignoring.");
        }
    }

    public final void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (this.zzfb != null) {
            this.zzfb.a(i, i2, intent);
        }
    }

    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            bundle.setClassLoader(WalletFragmentOptions.class.getClassLoader());
            WalletFragmentInitParams walletFragmentInitParams = (WalletFragmentInitParams) bundle.getParcelable("walletFragmentInitParams");
            if (walletFragmentInitParams != null) {
                if (this.zzfh != null) {
                    Log.w("SupportWalletFragment", "initialize(WalletFragmentInitParams) was called more than once.Ignoring.");
                }
                this.zzfh = walletFragmentInitParams;
            }
            if (this.zzfi == null) {
                this.zzfi = (MaskedWalletRequest) bundle.getParcelable("maskedWalletRequest");
            }
            if (this.zzfj == null) {
                this.zzfj = (MaskedWallet) bundle.getParcelable("maskedWallet");
            }
            if (bundle.containsKey("walletFragmentOptions")) {
                this.zzfg = (WalletFragmentOptions) bundle.getParcelable("walletFragmentOptions");
            }
            if (bundle.containsKey(ResponseConstants.ENABLED)) {
                this.zzfk = Boolean.valueOf(bundle.getBoolean(ResponseConstants.ENABLED));
            }
        } else if (this.fragment.getArguments() != null) {
            WalletFragmentOptions walletFragmentOptions = (WalletFragmentOptions) this.fragment.getArguments().getParcelable("extraWalletFragmentOptions");
            if (walletFragmentOptions != null) {
                walletFragmentOptions.zza(this.fragment.getActivity());
                this.zzfg = walletFragmentOptions;
            }
        }
        this.zzfc = true;
        this.zzfe.a(bundle);
    }

    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return this.zzfe.a(layoutInflater, viewGroup, bundle);
    }

    public final void onDestroy() {
        super.onDestroy();
        this.zzfc = false;
    }

    public final void onInflate(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        super.onInflate(activity, attributeSet, bundle);
        if (this.zzfg == null) {
            this.zzfg = WalletFragmentOptions.zza((Context) activity, attributeSet);
        }
        Bundle bundle2 = new Bundle();
        bundle2.putParcelable("attrKeyWalletFragmentOptions", this.zzfg);
        this.zzfe.a(activity, bundle2, bundle);
    }

    public final void onPause() {
        super.onPause();
        this.zzfe.d();
    }

    public final void onResume() {
        super.onResume();
        this.zzfe.c();
        FragmentManager supportFragmentManager = this.fragment.getActivity().getSupportFragmentManager();
        Fragment findFragmentByTag = supportFragmentManager.findFragmentByTag(GooglePlayServicesUtil.GMS_ERROR_DIALOG);
        if (findFragmentByTag != null) {
            supportFragmentManager.beginTransaction().remove(findFragmentByTag).commit();
            GooglePlayServicesUtil.showErrorDialogFragment(GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.fragment.getActivity(), GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE), this.fragment.getActivity(), -1);
        }
    }

    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.setClassLoader(WalletFragmentOptions.class.getClassLoader());
        this.zzfe.b(bundle);
        if (this.zzfh != null) {
            bundle.putParcelable("walletFragmentInitParams", this.zzfh);
            this.zzfh = null;
        }
        if (this.zzfi != null) {
            bundle.putParcelable("maskedWalletRequest", this.zzfi);
            this.zzfi = null;
        }
        if (this.zzfj != null) {
            bundle.putParcelable("maskedWallet", this.zzfj);
            this.zzfj = null;
        }
        if (this.zzfg != null) {
            bundle.putParcelable("walletFragmentOptions", this.zzfg);
            this.zzfg = null;
        }
        if (this.zzfk != null) {
            bundle.putBoolean(ResponseConstants.ENABLED, this.zzfk.booleanValue());
            this.zzfk = null;
        }
    }

    public final void onStart() {
        super.onStart();
        this.zzfe.b();
    }

    public final void onStop() {
        super.onStop();
        this.zzfe.e();
    }

    public final void setEnabled(boolean z) {
        Boolean valueOf;
        if (this.zzfb != null) {
            this.zzfb.a(z);
            valueOf = null;
        } else {
            valueOf = Boolean.valueOf(z);
        }
        this.zzfk = valueOf;
    }

    public final void setOnStateChangedListener(a aVar) {
        this.zzff.a(aVar);
    }

    public final void updateMaskedWallet(MaskedWallet maskedWallet) {
        if (this.zzfb != null) {
            this.zzfb.a(maskedWallet);
            this.zzfj = null;
            return;
        }
        this.zzfj = maskedWallet;
    }

    public final void updateMaskedWalletRequest(MaskedWalletRequest maskedWalletRequest) {
        if (this.zzfb != null) {
            this.zzfb.a(maskedWalletRequest);
            this.zzfi = null;
            return;
        }
        this.zzfi = maskedWalletRequest;
    }
}
