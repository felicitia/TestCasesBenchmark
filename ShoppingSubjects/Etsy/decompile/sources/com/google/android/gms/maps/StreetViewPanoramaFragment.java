package com.google.android.gms.maps;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.os.StrictMode.ThreadPolicy.Builder;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.c;
import com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate;
import com.google.android.gms.maps.internal.d;
import com.google.android.gms.maps.internal.e;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.ArrayList;
import java.util.List;

@TargetApi(11)
public class StreetViewPanoramaFragment extends Fragment {
    private final b zzbs = new b(this);

    @VisibleForTesting
    static class a implements com.google.android.gms.maps.internal.b {
        private final Fragment a;
        private final IStreetViewPanoramaFragmentDelegate b;

        public a(Fragment fragment, IStreetViewPanoramaFragmentDelegate iStreetViewPanoramaFragmentDelegate) {
            this.b = (IStreetViewPanoramaFragmentDelegate) Preconditions.checkNotNull(iStreetViewPanoramaFragmentDelegate);
            this.a = (Fragment) Preconditions.checkNotNull(fragment);
        }

        public final View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            try {
                Bundle bundle2 = new Bundle();
                d.a(bundle, bundle2);
                IObjectWrapper onCreateView = this.b.onCreateView(ObjectWrapper.wrap(layoutInflater), ObjectWrapper.wrap(viewGroup), bundle2);
                d.a(bundle2, bundle);
                return (View) ObjectWrapper.unwrap(onCreateView);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void a() {
            try {
                this.b.onStart();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void a(Activity activity, Bundle bundle, Bundle bundle2) {
            try {
                Bundle bundle3 = new Bundle();
                d.a(bundle2, bundle3);
                this.b.onInflate(ObjectWrapper.wrap(activity), null, bundle3);
                d.a(bundle3, bundle2);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void a(Bundle bundle) {
            try {
                Bundle bundle2 = new Bundle();
                d.a(bundle, bundle2);
                Bundle arguments = this.a.getArguments();
                if (arguments != null && arguments.containsKey("StreetViewPanoramaOptions")) {
                    d.a(bundle2, "StreetViewPanoramaOptions", arguments.getParcelable("StreetViewPanoramaOptions"));
                }
                this.b.onCreate(bundle2);
                d.a(bundle2, bundle);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void a(f fVar) {
            try {
                this.b.getStreetViewPanoramaAsync(new l(this, fVar));
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void b() {
            try {
                this.b.onResume();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void b(Bundle bundle) {
            try {
                Bundle bundle2 = new Bundle();
                d.a(bundle, bundle2);
                this.b.onSaveInstanceState(bundle2);
                d.a(bundle2, bundle);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void c() {
            try {
                this.b.onPause();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void d() {
            try {
                this.b.onStop();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void e() {
            try {
                this.b.onDestroyView();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void f() {
            try {
                this.b.onDestroy();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void g() {
            try {
                this.b.onLowMemory();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
    }

    @VisibleForTesting
    static class b extends com.google.android.gms.dynamic.a<a> {
        private final Fragment a;
        private c<a> b;
        private Activity c;
        private final List<f> d = new ArrayList();

        @VisibleForTesting
        b(Fragment fragment) {
            this.a = fragment;
        }

        /* access modifiers changed from: private */
        public final void a(Activity activity) {
            this.c = activity;
            i();
        }

        private final void i() {
            if (!(this.c == null || this.b == null || a() != null)) {
                try {
                    d.a(this.c);
                    this.b.a(new a(this.a, e.a((Context) this.c).zzd(ObjectWrapper.wrap(this.c))));
                    for (f a2 : this.d) {
                        ((a) a()).a(a2);
                    }
                    this.d.clear();
                } catch (RemoteException e) {
                    throw new RuntimeRemoteException(e);
                } catch (GooglePlayServicesNotAvailableException unused) {
                }
            }
        }

        /* access modifiers changed from: protected */
        public final void a(c<a> cVar) {
            this.b = cVar;
            i();
        }

        public final void a(f fVar) {
            if (a() != null) {
                ((a) a()).a(fVar);
            } else {
                this.d.add(fVar);
            }
        }
    }

    public static StreetViewPanoramaFragment newInstance() {
        return new StreetViewPanoramaFragment();
    }

    public static StreetViewPanoramaFragment newInstance(StreetViewPanoramaOptions streetViewPanoramaOptions) {
        StreetViewPanoramaFragment streetViewPanoramaFragment = new StreetViewPanoramaFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("StreetViewPanoramaOptions", streetViewPanoramaOptions);
        streetViewPanoramaFragment.setArguments(bundle);
        return streetViewPanoramaFragment;
    }

    public void getStreetViewPanoramaAsync(f fVar) {
        Preconditions.checkMainThread("getStreetViewPanoramaAsync() must be called on the main thread");
        this.zzbs.a(fVar);
    }

    public void onActivityCreated(Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(StreetViewPanoramaFragment.class.getClassLoader());
        }
        super.onActivityCreated(bundle);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.zzbs.a(activity);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.zzbs.a(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return this.zzbs.a(layoutInflater, viewGroup, bundle);
    }

    public void onDestroy() {
        this.zzbs.g();
        super.onDestroy();
    }

    public void onDestroyView() {
        this.zzbs.f();
        super.onDestroyView();
    }

    @SuppressLint({"NewApi"})
    public void onInflate(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new Builder(threadPolicy).permitAll().build());
        try {
            super.onInflate(activity, attributeSet, bundle);
            this.zzbs.a(activity);
            this.zzbs.a(activity, new Bundle(), bundle);
        } finally {
            StrictMode.setThreadPolicy(threadPolicy);
        }
    }

    public void onLowMemory() {
        this.zzbs.h();
        super.onLowMemory();
    }

    public void onPause() {
        this.zzbs.d();
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        this.zzbs.c();
    }

    public void onSaveInstanceState(Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(StreetViewPanoramaFragment.class.getClassLoader());
        }
        super.onSaveInstanceState(bundle);
        this.zzbs.b(bundle);
    }

    public void onStart() {
        super.onStart();
        this.zzbs.b();
    }

    public void onStop() {
        this.zzbs.e();
        super.onStop();
    }

    public void setArguments(Bundle bundle) {
        super.setArguments(bundle);
    }
}
