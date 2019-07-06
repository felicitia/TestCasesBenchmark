package com.google.android.gms.wallet;

import android.accounts.Account;
import android.os.RemoteException;
import android.support.annotation.VisibleForTesting;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.ApiOptions.HasAccountOptions;
import com.google.android.gms.common.api.Api.ClientKey;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.internal.wallet.g;
import com.google.android.gms.internal.wallet.h;
import com.google.android.gms.internal.wallet.k;
import com.google.android.gms.internal.wallet.l;
import java.util.Locale;

public final class d {
    public static final Api<a> a = new Api<>("Wallet.API", d, c);
    @Deprecated
    public static final b b = new l();
    private static final ClientKey<com.google.android.gms.internal.wallet.c> c = new ClientKey<>();
    private static final AbstractClientBuilder<com.google.android.gms.internal.wallet.c, a> d = new j();
    private static final com.google.android.gms.wallet.wobs.a e = new h();
    private static final k f = new g();

    public static final class a implements HasAccountOptions {
        public final int a;
        public final int b;
        @VisibleForTesting
        final boolean c;
        private final Account d;

        /* renamed from: com.google.android.gms.wallet.d$a$a reason: collision with other inner class name */
        public static final class C0148a {
            /* access modifiers changed from: private */
            public int a = 3;
            /* access modifiers changed from: private */
            public int b = 0;
            /* access modifiers changed from: private */
            public boolean c = true;

            public final C0148a a(int i) {
                if (i == 0 || i == 0 || i == 2 || i == 1 || i == 3) {
                    this.a = i;
                    return this;
                }
                throw new IllegalArgumentException(String.format(Locale.US, "Invalid environment value %d", new Object[]{Integer.valueOf(i)}));
            }

            public final a a() {
                return new a(this, null);
            }

            public final C0148a b(int i) {
                if (i == 0 || i == 1) {
                    this.b = i;
                    return this;
                }
                throw new IllegalArgumentException(String.format(Locale.US, "Invalid theme value %d", new Object[]{Integer.valueOf(i)}));
            }
        }

        private a() {
            this(new C0148a());
        }

        private a(C0148a aVar) {
            this.a = aVar.a;
            this.b = aVar.b;
            this.c = aVar.c;
            this.d = null;
        }

        /* synthetic */ a(C0148a aVar, j jVar) {
            this(aVar);
        }

        /* synthetic */ a(j jVar) {
            this();
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return Objects.equal(Integer.valueOf(this.a), Integer.valueOf(aVar.a)) && Objects.equal(Integer.valueOf(this.b), Integer.valueOf(aVar.b)) && Objects.equal(null, null) && Objects.equal(Boolean.valueOf(this.c), Boolean.valueOf(aVar.c));
        }

        public final Account getAccount() {
            return null;
        }

        public final int hashCode() {
            return Objects.hashCode(Integer.valueOf(this.a), Integer.valueOf(this.b), null, Boolean.valueOf(this.c));
        }
    }

    public static abstract class b<R extends Result> extends ApiMethodImpl<R, com.google.android.gms.internal.wallet.c> {
        public b(GoogleApiClient googleApiClient) {
            super(d.a, googleApiClient);
        }

        /* access modifiers changed from: protected */
        @VisibleForTesting
        /* renamed from: a */
        public abstract void doExecute(com.google.android.gms.internal.wallet.c cVar) throws RemoteException;
    }

    public static abstract class c extends b<Status> {
        public c(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        /* access modifiers changed from: protected */
        public /* synthetic */ Result createFailedResult(Status status) {
            return status;
        }
    }
}
