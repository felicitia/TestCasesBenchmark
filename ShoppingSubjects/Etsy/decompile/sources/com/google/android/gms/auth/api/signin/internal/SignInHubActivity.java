package com.google.android.gms.auth.api.signin.internal;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.SignInAccount;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;

@KeepName
public class SignInHubActivity extends FragmentActivity {
    private static boolean zzfg = false;
    private boolean zzfh = false;
    private SignInConfiguration zzfi;
    private boolean zzfj;
    /* access modifiers changed from: private */
    public int zzfk;
    /* access modifiers changed from: private */
    public Intent zzfl;

    private class a implements LoaderCallbacks<Void> {
        private a() {
        }

        public final Loader<Void> onCreateLoader(int i, Bundle bundle) {
            return new zzf(SignInHubActivity.this, GoogleApiClient.getAllClients());
        }

        public final /* synthetic */ void onLoadFinished(Loader loader, Object obj) {
            SignInHubActivity.this.setResult(SignInHubActivity.this.zzfk, SignInHubActivity.this.zzfl);
            SignInHubActivity.this.finish();
        }

        public final void onLoaderReset(Loader<Void> loader) {
        }
    }

    private final void zzd(int i) {
        Status status = new Status(i);
        Intent intent = new Intent();
        intent.putExtra("googleSignInStatus", status);
        setResult(0, intent);
        finish();
        zzfg = false;
    }

    private final void zzu() {
        getSupportLoaderManager().initLoader(0, null, new a());
        zzfg = false;
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return true;
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (!this.zzfh) {
            setResult(0);
            if (i == 40962) {
                if (intent != null) {
                    SignInAccount signInAccount = (SignInAccount) intent.getParcelableExtra("signInAccount");
                    if (signInAccount != null && signInAccount.getGoogleSignInAccount() != null) {
                        GoogleSignInAccount googleSignInAccount = signInAccount.getGoogleSignInAccount();
                        l.a(this).a(this.zzfi.zzt(), googleSignInAccount);
                        intent.removeExtra("signInAccount");
                        intent.putExtra("googleSignInAccount", googleSignInAccount);
                        this.zzfj = true;
                        this.zzfk = i2;
                        this.zzfl = intent;
                        zzu();
                        return;
                    } else if (intent.hasExtra("errorCode")) {
                        zzd(intent.getIntExtra("errorCode", 8));
                        return;
                    }
                }
                zzd(8);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        String action = intent.getAction();
        if ("com.google.android.gms.auth.NO_IMPL".equals(action)) {
            zzd(12500);
        } else if (action.equals("com.google.android.gms.auth.GOOGLE_SIGN_IN") || action.equals("com.google.android.gms.auth.APPAUTH_SIGN_IN")) {
            this.zzfi = (SignInConfiguration) intent.getBundleExtra("config").getParcelable("config");
            if (this.zzfi == null) {
                Log.e("AuthSignInClient", "Activity started with invalid configuration.");
                setResult(0);
                finish();
                return;
            }
            if (!(bundle == null)) {
                this.zzfj = bundle.getBoolean("signingInGoogleApiClients");
                if (this.zzfj) {
                    this.zzfk = bundle.getInt("signInResultCode");
                    this.zzfl = (Intent) bundle.getParcelable("signInResultData");
                    zzu();
                }
            } else if (zzfg) {
                setResult(0);
                zzd(12502);
            } else {
                zzfg = true;
                Intent intent2 = new Intent(action);
                intent2.setPackage(action.equals("com.google.android.gms.auth.GOOGLE_SIGN_IN") ? "com.google.android.gms" : getPackageName());
                intent2.putExtra("config", this.zzfi);
                try {
                    startActivityForResult(intent2, 40962);
                } catch (ActivityNotFoundException unused) {
                    this.zzfh = true;
                    Log.w("AuthSignInClient", "Could not launch sign in Intent. Google Play Service is probably being updated...");
                    zzd(17);
                }
            }
        } else {
            String str = "AuthSignInClient";
            String str2 = "Unknown action: ";
            String valueOf = String.valueOf(intent.getAction());
            Log.e(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("signingInGoogleApiClients", this.zzfj);
        if (this.zzfj) {
            bundle.putInt("signInResultCode", this.zzfk);
            bundle.putParcelable("signInResultData", this.zzfl);
        }
    }
}
