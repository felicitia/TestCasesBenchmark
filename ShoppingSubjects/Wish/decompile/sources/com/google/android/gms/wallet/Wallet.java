package com.google.android.gms.wallet;

import android.accounts.Account;
import android.app.Activity;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.ApiOptions.HasAccountOptions;
import com.google.android.gms.common.api.Api.ClientKey;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.internal.wallet.zzad;
import com.google.android.gms.internal.wallet.zzal;
import com.google.android.gms.internal.wallet.zzam;
import com.google.android.gms.internal.wallet.zze;
import com.google.android.gms.internal.wallet.zzw;
import com.google.android.gms.wallet.wobs.WalletObjects;
import java.util.Locale;

public final class Wallet {
    public static final Api<WalletOptions> API = new Api<>("Wallet.API", CLIENT_BUILDER, CLIENT_KEY);
    private static final AbstractClientBuilder<zzad, WalletOptions> CLIENT_BUILDER = new zzap();
    private static final ClientKey<zzad> CLIENT_KEY = new ClientKey<>();
    @Deprecated
    public static final Payments Payments = new zzw();
    private static final WalletObjects zzep = new zzam();
    private static final zze zzeq = new zzal();

    public static final class WalletOptions implements HasAccountOptions {
        private final Account account;
        public final int environment;
        public final int theme;
        final boolean zzer;

        public static final class Builder {
            /* access modifiers changed from: private */
            public int environment = 3;
            /* access modifiers changed from: private */
            public int theme = 0;
            /* access modifiers changed from: private */
            public boolean zzer = true;

            public final WalletOptions build() {
                return new WalletOptions(this, null);
            }

            public final Builder setEnvironment(int i) {
                if (i == 0 || i == 0 || i == 2 || i == 1 || i == 3) {
                    this.environment = i;
                    return this;
                }
                throw new IllegalArgumentException(String.format(Locale.US, "Invalid environment value %d", new Object[]{Integer.valueOf(i)}));
            }

            public final Builder setTheme(int i) {
                if (i == 0 || i == 1) {
                    this.theme = i;
                    return this;
                }
                throw new IllegalArgumentException(String.format(Locale.US, "Invalid theme value %d", new Object[]{Integer.valueOf(i)}));
            }
        }

        private WalletOptions() {
            this(new Builder());
        }

        private WalletOptions(Builder builder) {
            this.environment = builder.environment;
            this.theme = builder.theme;
            this.zzer = builder.zzer;
            this.account = null;
        }

        /* synthetic */ WalletOptions(Builder builder, zzap zzap) {
            this(builder);
        }

        /* synthetic */ WalletOptions(zzap zzap) {
            this();
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof WalletOptions)) {
                return false;
            }
            WalletOptions walletOptions = (WalletOptions) obj;
            return Objects.equal(Integer.valueOf(this.environment), Integer.valueOf(walletOptions.environment)) && Objects.equal(Integer.valueOf(this.theme), Integer.valueOf(walletOptions.theme)) && Objects.equal(null, null) && Objects.equal(Boolean.valueOf(this.zzer), Boolean.valueOf(walletOptions.zzer));
        }

        public final Account getAccount() {
            return null;
        }

        public final int hashCode() {
            return Objects.hashCode(Integer.valueOf(this.environment), Integer.valueOf(this.theme), null, Boolean.valueOf(this.zzer));
        }
    }

    public static abstract class zza<R extends Result> extends ApiMethodImpl<R, zzad> {
        public zza(GoogleApiClient googleApiClient) {
            super(Wallet.API, googleApiClient);
        }

        /* access modifiers changed from: protected */
        /* renamed from: zza */
        public abstract void doExecute(zzad zzad) throws RemoteException;
    }

    public static abstract class zzb extends zza<Status> {
        public zzb(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        /* access modifiers changed from: protected */
        public /* synthetic */ Result createFailedResult(Status status) {
            return status;
        }
    }

    private Wallet() {
    }

    public static PaymentsClient getPaymentsClient(Activity activity, WalletOptions walletOptions) {
        return new PaymentsClient(activity, walletOptions);
    }
}
