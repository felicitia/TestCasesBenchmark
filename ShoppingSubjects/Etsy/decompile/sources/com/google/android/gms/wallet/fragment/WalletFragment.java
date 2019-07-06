package com.google.android.gms.wallet.fragment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
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
import com.google.android.gms.dynamic.FragmentWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.wallet.f;
import com.google.android.gms.internal.wallet.zzl;
import com.google.android.gms.internal.wallet.zzp;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;

@TargetApi(12)
public final class WalletFragment extends Fragment {
    /* access modifiers changed from: private */
    public boolean zzfc = false;
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
    /* access modifiers changed from: private */
    public c zzfp;
    /* access modifiers changed from: private */
    public final FragmentWrapper zzfq = FragmentWrapper.wrap(this);
    private final d zzfr = new d();
    /* access modifiers changed from: private */
    public b zzfs = new b(this);
    /* access modifiers changed from: private */
    public final Fragment zzft = this;

    public interface a {
        void a(WalletFragment walletFragment, int i, int i2, Bundle bundle);
    }

    static class b extends zzp {
        private a a;
        private final WalletFragment b;

        b(WalletFragment walletFragment) {
            this.b = walletFragment;
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
            Button button = new Button(WalletFragment.this.zzft.getActivity());
            button.setText(com.google.android.gms.wallet.c.a.wallet_buy_button_place_holder);
            int i = -2;
            int i2 = -1;
            if (WalletFragment.this.zzfg != null) {
                WalletFragmentStyle fragmentStyle = WalletFragment.this.zzfg.getFragmentStyle();
                if (fragmentStyle != null) {
                    DisplayMetrics displayMetrics = WalletFragment.this.zzft.getResources().getDisplayMetrics();
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
            Activity activity = WalletFragment.this.zzft.getActivity();
            if (WalletFragment.this.zzfp == null && WalletFragment.this.zzfc && activity != null) {
                try {
                    WalletFragment.this.zzfp = new c(f.a(activity, WalletFragment.this.zzfq, WalletFragment.this.zzfg, WalletFragment.this.zzfs));
                    WalletFragment.this.zzfg = null;
                    cVar.a(WalletFragment.this.zzfp);
                    if (WalletFragment.this.zzfh != null) {
                        WalletFragment.this.zzfp.a(WalletFragment.this.zzfh);
                        WalletFragment.this.zzfh = null;
                    }
                    if (WalletFragment.this.zzfi != null) {
                        WalletFragment.this.zzfp.a(WalletFragment.this.zzfi);
                        WalletFragment.this.zzfi = null;
                    }
                    if (WalletFragment.this.zzfj != null) {
                        WalletFragment.this.zzfp.a(WalletFragment.this.zzfj);
                        WalletFragment.this.zzfj = null;
                    }
                    if (WalletFragment.this.zzfk != null) {
                        WalletFragment.this.zzfp.a(WalletFragment.this.zzfk.booleanValue());
                        WalletFragment.this.zzfk = null;
                    }
                } catch (GooglePlayServicesNotAvailableException unused) {
                }
            }
        }

        public final void onClick(View view) {
            Activity activity = WalletFragment.this.zzft.getActivity();
            GooglePlayServicesUtil.showErrorDialogFragment(GooglePlayServicesUtil.isGooglePlayServicesAvailable(activity, GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE), activity, -1);
        }
    }

    public static WalletFragment newInstance(WalletFragmentOptions walletFragmentOptions) {
        WalletFragment walletFragment = new WalletFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("extraWalletFragmentOptions", walletFragmentOptions);
        walletFragment.zzft.setArguments(bundle);
        return walletFragment;
    }

    public final int getState() {
        if (this.zzfp != null) {
            return this.zzfp.h();
        }
        return 0;
    }

    public final void initialize(WalletFragmentInitParams walletFragmentInitParams) {
        if (this.zzfp != null) {
            this.zzfp.a(walletFragmentInitParams);
            this.zzfh = null;
            return;
        }
        if (this.zzfh == null) {
            this.zzfh = walletFragmentInitParams;
            if (this.zzfi != null) {
                Log.w("WalletFragment", "updateMaskedWalletRequest() was called before initialize()");
            }
            if (this.zzfj != null) {
                Log.w("WalletFragment", "updateMaskedWallet() was called before initialize()");
            }
        } else {
            Log.w("WalletFragment", "initialize(WalletFragmentInitParams) was called more than once. Ignoring.");
        }
    }

    public final void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (this.zzfp != null) {
            this.zzfp.a(i, i2, intent);
        }
    }

    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            bundle.setClassLoader(WalletFragmentOptions.class.getClassLoader());
            WalletFragmentInitParams walletFragmentInitParams = (WalletFragmentInitParams) bundle.getParcelable("walletFragmentInitParams");
            if (walletFragmentInitParams != null) {
                if (this.zzfh != null) {
                    Log.w("WalletFragment", "initialize(WalletFragmentInitParams) was called more than once.Ignoring.");
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
        } else if (this.zzft.getArguments() != null) {
            WalletFragmentOptions walletFragmentOptions = (WalletFragmentOptions) this.zzft.getArguments().getParcelable("extraWalletFragmentOptions");
            if (walletFragmentOptions != null) {
                walletFragmentOptions.zza(this.zzft.getActivity());
                this.zzfg = walletFragmentOptions;
            }
        }
        this.zzfc = true;
        this.zzfr.a(bundle);
    }

    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return this.zzfr.a(layoutInflater, viewGroup, bundle);
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
        this.zzfr.a(activity, bundle2, bundle);
    }

    public final void onPause() {
        super.onPause();
        this.zzfr.d();
    }

    public final void onResume() {
        super.onResume();
        this.zzfr.c();
        FragmentManager fragmentManager = this.zzft.getActivity().getFragmentManager();
        Fragment findFragmentByTag = fragmentManager.findFragmentByTag(GooglePlayServicesUtil.GMS_ERROR_DIALOG);
        if (findFragmentByTag != null) {
            fragmentManager.beginTransaction().remove(findFragmentByTag).commit();
            GooglePlayServicesUtil.showErrorDialogFragment(GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.zzft.getActivity(), GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE), this.zzft.getActivity(), -1);
        }
    }

    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.setClassLoader(WalletFragmentOptions.class.getClassLoader());
        this.zzfr.b(bundle);
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
        this.zzfr.b();
    }

    public final void onStop() {
        super.onStop();
        this.zzfr.e();
    }

    public final void setEnabled(boolean z) {
        Boolean valueOf;
        if (this.zzfp != null) {
            this.zzfp.a(z);
            valueOf = null;
        } else {
            valueOf = Boolean.valueOf(z);
        }
        this.zzfk = valueOf;
    }

    public final void setOnStateChangedListener(a aVar) {
        this.zzfs.a(aVar);
    }

    public final void updateMaskedWallet(MaskedWallet maskedWallet) {
        if (this.zzfp != null) {
            this.zzfp.a(maskedWallet);
            this.zzfj = null;
            return;
        }
        this.zzfj = maskedWallet;
    }

    public final void updateMaskedWalletRequest(MaskedWalletRequest maskedWalletRequest) {
        if (this.zzfp != null) {
            this.zzfp.a(maskedWalletRequest);
            this.zzfi = null;
            return;
        }
        this.zzfi = maskedWalletRequest;
    }
}
