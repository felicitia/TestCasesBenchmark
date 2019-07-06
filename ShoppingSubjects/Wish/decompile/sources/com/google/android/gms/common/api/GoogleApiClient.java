package com.google.android.gms.common.api;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.util.ArrayMap;
import android.view.View;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.Api.AnyClientKey;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.ApiOptions.HasOptions;
import com.google.android.gms.common.api.Api.ApiOptions.NotRequiredOptions;
import com.google.android.gms.common.api.Api.Client;
import com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl;
import com.google.android.gms.common.api.internal.LifecycleActivity;
import com.google.android.gms.common.api.internal.SignInConnectionListener;
import com.google.android.gms.common.api.internal.zzav;
import com.google.android.gms.common.api.internal.zzch;
import com.google.android.gms.common.api.internal.zzi;
import com.google.android.gms.common.api.internal.zzp;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.ClientSettings.OptionalApiSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.signin.SignIn;
import com.google.android.gms.signin.SignInClient;
import com.google.android.gms.signin.SignInOptions;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.locks.ReentrantLock;

public abstract class GoogleApiClient {
    /* access modifiers changed from: private */
    public static final Set<GoogleApiClient> zzcu = Collections.newSetFromMap(new WeakHashMap());

    public static final class Builder {
        private final Context mContext;
        private Looper zzcn;
        private final Set<Scope> zzcv = new HashSet();
        private final Set<Scope> zzcw = new HashSet();
        private int zzcx;
        private View zzcy;
        private String zzcz;
        private String zzda;
        private final Map<Api<?>, OptionalApiSettings> zzdb = new ArrayMap();
        private final Map<Api<?>, ApiOptions> zzdc = new ArrayMap();
        private LifecycleActivity zzdd;
        private int zzde = -1;
        private OnConnectionFailedListener zzdf;
        private GoogleApiAvailability zzdg = GoogleApiAvailability.getInstance();
        private AbstractClientBuilder<? extends SignInClient, SignInOptions> zzdh = SignIn.CLIENT_BUILDER;
        private final ArrayList<ConnectionCallbacks> zzdi = new ArrayList<>();
        private final ArrayList<OnConnectionFailedListener> zzdj = new ArrayList<>();
        private boolean zzdk = false;
        private Account zzs;

        public Builder(Context context) {
            this.mContext = context;
            this.zzcn = context.getMainLooper();
            this.zzcz = context.getPackageName();
            this.zzda = context.getClass().getName();
        }

        public final Builder addApi(Api<? extends NotRequiredOptions> api) {
            Preconditions.checkNotNull(api, "Api must not be null");
            this.zzdc.put(api, null);
            List impliedScopes = api.zzj().getImpliedScopes(null);
            this.zzcw.addAll(impliedScopes);
            this.zzcv.addAll(impliedScopes);
            return this;
        }

        public final <O extends HasOptions> Builder addApi(Api<O> api, O o) {
            Preconditions.checkNotNull(api, "Api must not be null");
            Preconditions.checkNotNull(o, "Null options are not permitted for this Api");
            this.zzdc.put(api, o);
            List impliedScopes = api.zzj().getImpliedScopes(o);
            this.zzcw.addAll(impliedScopes);
            this.zzcv.addAll(impliedScopes);
            return this;
        }

        public final Builder addConnectionCallbacks(ConnectionCallbacks connectionCallbacks) {
            Preconditions.checkNotNull(connectionCallbacks, "Listener must not be null");
            this.zzdi.add(connectionCallbacks);
            return this;
        }

        public final Builder addOnConnectionFailedListener(OnConnectionFailedListener onConnectionFailedListener) {
            Preconditions.checkNotNull(onConnectionFailedListener, "Listener must not be null");
            this.zzdj.add(onConnectionFailedListener);
            return this;
        }

        public final GoogleApiClient build() {
            boolean z;
            Preconditions.checkArgument(!this.zzdc.isEmpty(), "must call addApi() to add at least one API");
            ClientSettings buildClientSettings = buildClientSettings();
            Api api = null;
            Map optionalApiSettings = buildClientSettings.getOptionalApiSettings();
            ArrayMap arrayMap = new ArrayMap();
            ArrayMap arrayMap2 = new ArrayMap();
            ArrayList arrayList = new ArrayList();
            Iterator it = this.zzdc.keySet().iterator();
            boolean z2 = false;
            while (it.hasNext()) {
                Api api2 = (Api) it.next();
                Object obj = this.zzdc.get(api2);
                boolean z3 = optionalApiSettings.get(api2) != null;
                arrayMap.put(api2, Boolean.valueOf(z3));
                zzp zzp = new zzp(api2, z3);
                arrayList.add(zzp);
                AbstractClientBuilder zzk = api2.zzk();
                AbstractClientBuilder abstractClientBuilder = zzk;
                zzp zzp2 = zzp;
                Map map = optionalApiSettings;
                Api api3 = api2;
                Iterator it2 = it;
                Client buildClient = zzk.buildClient(this.mContext, this.zzcn, buildClientSettings, obj, zzp2, zzp2);
                arrayMap2.put(api3.getClientKey(), buildClient);
                if (abstractClientBuilder.getPriority() == 1) {
                    z2 = obj != null;
                }
                if (buildClient.providesSignIn()) {
                    if (api != null) {
                        String name = api3.getName();
                        String name2 = api.getName();
                        StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 21 + String.valueOf(name2).length());
                        sb.append(name);
                        sb.append(" cannot be used with ");
                        sb.append(name2);
                        throw new IllegalStateException(sb.toString());
                    }
                    api = api3;
                }
                optionalApiSettings = map;
                it = it2;
            }
            if (api == null) {
                z = true;
            } else if (z2) {
                String name3 = api.getName();
                StringBuilder sb2 = new StringBuilder(String.valueOf(name3).length() + 82);
                sb2.append("With using ");
                sb2.append(name3);
                sb2.append(", GamesOptions can only be specified within GoogleSignInOptions.Builder");
                throw new IllegalStateException(sb2.toString());
            } else {
                z = true;
                Preconditions.checkState(this.zzs == null, "Must not set an account in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead", api.getName());
                Preconditions.checkState(this.zzcv.equals(this.zzcw), "Must not set scopes in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead.", api.getName());
            }
            ClientSettings clientSettings = buildClientSettings;
            zzav zzav = new zzav(this.mContext, new ReentrantLock(), this.zzcn, clientSettings, this.zzdg, this.zzdh, arrayMap, this.zzdi, this.zzdj, arrayMap2, this.zzde, zzav.zza(arrayMap2.values(), z), arrayList, false);
            synchronized (GoogleApiClient.zzcu) {
                try {
                    GoogleApiClient.zzcu.add(zzav);
                } catch (Throwable th) {
                    while (true) {
                        throw th;
                    }
                }
            }
            if (this.zzde >= 0) {
                zzi.zza(this.zzdd).zza(this.zzde, zzav, this.zzdf);
            }
            return zzav;
        }

        public final ClientSettings buildClientSettings() {
            SignInOptions signInOptions = SignInOptions.DEFAULT;
            if (this.zzdc.containsKey(SignIn.API)) {
                signInOptions = (SignInOptions) this.zzdc.get(SignIn.API);
            }
            ClientSettings clientSettings = new ClientSettings(this.zzs, this.zzcv, this.zzdb, this.zzcx, this.zzcy, this.zzcz, this.zzda, signInOptions);
            return clientSettings;
        }

        public final Builder setHandler(Handler handler) {
            Preconditions.checkNotNull(handler, "Handler must not be null");
            this.zzcn = handler.getLooper();
            return this;
        }
    }

    public interface ConnectionCallbacks {
        void onConnected(Bundle bundle);

        void onConnectionSuspended(int i);
    }

    public interface OnConnectionFailedListener {
        void onConnectionFailed(ConnectionResult connectionResult);
    }

    public static Set<GoogleApiClient> getAllClients() {
        Set<GoogleApiClient> set;
        synchronized (zzcu) {
            set = zzcu;
        }
        return set;
    }

    public abstract ConnectionResult blockingConnect();

    public abstract PendingResult<Status> clearDefaultAccountAndReconnect();

    public abstract void connect();

    public void connect(int i) {
        throw new UnsupportedOperationException();
    }

    public abstract void disconnect();

    public abstract void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    public <A extends AnyClient, R extends Result, T extends ApiMethodImpl<R, A>> T enqueue(T t) {
        throw new UnsupportedOperationException();
    }

    public <A extends AnyClient, T extends ApiMethodImpl<? extends Result, A>> T execute(T t) {
        throw new UnsupportedOperationException();
    }

    public <C extends Client> C getClient(AnyClientKey<C> anyClientKey) {
        throw new UnsupportedOperationException();
    }

    public Context getContext() {
        throw new UnsupportedOperationException();
    }

    public Looper getLooper() {
        throw new UnsupportedOperationException();
    }

    public abstract boolean isConnected();

    public abstract boolean isConnecting();

    public boolean maybeSignIn(SignInConnectionListener signInConnectionListener) {
        throw new UnsupportedOperationException();
    }

    public void maybeSignOut() {
        throw new UnsupportedOperationException();
    }

    public abstract void reconnect();

    public abstract void registerConnectionCallbacks(ConnectionCallbacks connectionCallbacks);

    public abstract void registerConnectionFailedListener(OnConnectionFailedListener onConnectionFailedListener);

    public abstract void unregisterConnectionFailedListener(OnConnectionFailedListener onConnectionFailedListener);

    public void zza(zzch zzch) {
        throw new UnsupportedOperationException();
    }

    public void zzb(zzch zzch) {
        throw new UnsupportedOperationException();
    }
}
