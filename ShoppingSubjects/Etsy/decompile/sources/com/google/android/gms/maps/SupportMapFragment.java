package com.google.android.gms.maps;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.os.StrictMode.ThreadPolicy.Builder;
import android.support.v4.app.Fragment;
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
import com.google.android.gms.maps.internal.IMapFragmentDelegate;
import com.google.android.gms.maps.internal.d;
import com.google.android.gms.maps.internal.e;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.ArrayList;
import java.util.List;

public class SupportMapFragment extends Fragment {
    private final b zzcg = new b(this);

    @VisibleForTesting
    static class a implements com.google.android.gms.maps.internal.a {
        private final Fragment a;
        private final IMapFragmentDelegate b;

        public a(Fragment fragment, IMapFragmentDelegate iMapFragmentDelegate) {
            this.b = (IMapFragmentDelegate) Preconditions.checkNotNull(iMapFragmentDelegate);
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
            GoogleMapOptions googleMapOptions = (GoogleMapOptions) bundle.getParcelable("MapOptions");
            try {
                Bundle bundle3 = new Bundle();
                d.a(bundle2, bundle3);
                this.b.onInflate(ObjectWrapper.wrap(activity), googleMapOptions, bundle3);
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
                if (arguments != null && arguments.containsKey("MapOptions")) {
                    d.a(bundle2, "MapOptions", arguments.getParcelable("MapOptions"));
                }
                this.b.onCreate(bundle2);
                d.a(bundle2, bundle);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public final void a(e eVar) {
            try {
                this.b.getMapAsync(new n(this, eVar));
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

        public final void c(Bundle bundle) {
            try {
                Bundle bundle2 = new Bundle();
                d.a(bundle, bundle2);
                this.b.onEnterAmbient(bundle2);
                d.a(bundle2, bundle);
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

        public final void h() {
            try {
                this.b.onExitAmbient();
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
        private final List<e> d = new ArrayList();

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
                    IMapFragmentDelegate zzc = e.a((Context) this.c).zzc(ObjectWrapper.wrap(this.c));
                    if (zzc != null) {
                        this.b.a(new a(this.a, zzc));
                        for (e a2 : this.d) {
                            ((a) a()).a(a2);
                        }
                        this.d.clear();
                    }
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

        public final void a(e eVar) {
            if (a() != null) {
                ((a) a()).a(eVar);
            } else {
                this.d.add(eVar);
            }
        }
    }

    public static SupportMapFragment newInstance() {
        return new SupportMapFragment();
    }

    public static SupportMapFragment newInstance(GoogleMapOptions googleMapOptions) {
        SupportMapFragment supportMapFragment = new SupportMapFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("MapOptions", googleMapOptions);
        supportMapFragment.setArguments(bundle);
        return supportMapFragment;
    }

    public void getMapAsync(e eVar) {
        Preconditions.checkMainThread("getMapAsync must be called on the main thread.");
        this.zzcg.a(eVar);
    }

    public void onActivityCreated(Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(SupportMapFragment.class.getClassLoader());
        }
        super.onActivityCreated(bundle);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.zzcg.a(activity);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.zzcg.a(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View a2 = this.zzcg.a(layoutInflater, viewGroup, bundle);
        a2.setClickable(true);
        return a2;
    }

    public void onDestroy() {
        this.zzcg.g();
        super.onDestroy();
    }

    public void onDestroyView() {
        this.zzcg.f();
        super.onDestroyView();
    }

    public final void onEnterAmbient(Bundle bundle) {
        Preconditions.checkMainThread("onEnterAmbient must be called on the main thread.");
        b bVar = this.zzcg;
        if (bVar.a() != null) {
            ((a) bVar.a()).c(bundle);
        }
    }

    public final void onExitAmbient() {
        Preconditions.checkMainThread("onExitAmbient must be called on the main thread.");
        b bVar = this.zzcg;
        if (bVar.a() != null) {
            ((a) bVar.a()).h();
        }
    }

    public void onInflate(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new Builder(threadPolicy).permitAll().build());
        try {
            super.onInflate(activity, attributeSet, bundle);
            this.zzcg.a(activity);
            GoogleMapOptions createFromAttributes = GoogleMapOptions.createFromAttributes(activity, attributeSet);
            Bundle bundle2 = new Bundle();
            bundle2.putParcelable("MapOptions", createFromAttributes);
            this.zzcg.a(activity, bundle2, bundle);
        } finally {
            StrictMode.setThreadPolicy(threadPolicy);
        }
    }

    public void onLowMemory() {
        this.zzcg.h();
        super.onLowMemory();
    }

    public void onPause() {
        this.zzcg.d();
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        this.zzcg.c();
    }

    public void onSaveInstanceState(Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(SupportMapFragment.class.getClassLoader());
        }
        super.onSaveInstanceState(bundle);
        this.zzcg.b(bundle);
    }

    public void onStart() {
        super.onStart();
        this.zzcg.b();
    }

    public void onStop() {
        this.zzcg.e();
        super.onStop();
    }

    public void setArguments(Bundle bundle) {
        super.setArguments(bundle);
    }
}
