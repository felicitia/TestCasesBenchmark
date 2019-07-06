package com.google.android.gms.wallet;

import android.app.Activity;
import android.app.Fragment;
import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import com.google.android.gms.tasks.c;
import com.google.android.gms.tasks.f;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class AutoResolveHelper {
    @VisibleForTesting
    static long a = SystemClock.elapsedRealtime();
    private static final long b = TimeUnit.MINUTES.toMillis(10);

    @VisibleForTesting
    static class a<TResult extends a> implements c<TResult>, Runnable {
        @VisibleForTesting
        static final SparseArray<a<?>> a = new SparseArray<>(2);
        @VisibleForTesting
        private static final Handler c = new Handler(Looper.getMainLooper());
        private static final AtomicInteger d = new AtomicInteger();
        int b;
        private zzb e;
        private f<TResult> f;

        a() {
        }

        private final void a() {
            if (this.f != null && this.e != null) {
                a.delete(this.b);
                c.removeCallbacks(this);
                this.e.zzb(this.f);
            }
        }

        public final void a(zzb zzb) {
            this.e = zzb;
            a();
        }

        public final void b(zzb zzb) {
            if (this.e == zzb) {
                this.e = null;
            }
        }

        public final void onComplete(@NonNull f<TResult> fVar) {
            this.f = fVar;
            a();
        }

        public final void run() {
            a.delete(this.b);
        }
    }

    public static class zzb extends Fragment {
        private static String zzw = "resolveCallId";
        private static String zzx = "requestCode";
        private static String zzy = "initializationElapsedRealtime";
        private static String zzz = "delivered";
        private int zzaa;
        private a<?> zzab;
        @VisibleForTesting
        private boolean zzac;

        /* access modifiers changed from: private */
        public static Fragment zza(int i, int i2) {
            Bundle bundle = new Bundle();
            bundle.putInt(zzw, i);
            bundle.putInt(zzx, i2);
            bundle.putLong(zzy, AutoResolveHelper.a);
            zzb zzb = new zzb();
            zzb.setArguments(bundle);
            return zzb;
        }

        /* access modifiers changed from: private */
        public final void zzb(@Nullable f<? extends a> fVar) {
            if (!this.zzac) {
                this.zzac = true;
                Activity activity = getActivity();
                activity.getFragmentManager().beginTransaction().remove(this).commit();
                if (fVar != null) {
                    AutoResolveHelper.b(activity, this.zzaa, fVar);
                    return;
                }
                AutoResolveHelper.b(activity, this.zzaa, 0, new Intent());
            }
        }

        private final void zzc() {
            if (this.zzab != null) {
                this.zzab.b(this);
            }
        }

        public final void onCreate(@Nullable Bundle bundle) {
            super.onCreate(bundle);
            this.zzaa = getArguments().getInt(zzx);
            this.zzab = AutoResolveHelper.a != getArguments().getLong(zzy) ? null : (a) a.a.get(getArguments().getInt(zzw));
            this.zzac = bundle != null && bundle.getBoolean(zzz);
        }

        public final void onPause() {
            super.onPause();
            zzc();
        }

        public final void onResume() {
            super.onResume();
            if (this.zzab != null) {
                this.zzab.a(this);
                return;
            }
            if (Log.isLoggable("AutoResolveHelper", 5)) {
                Log.w("AutoResolveHelper", "Sending canceled result for garbage collected task!");
            }
            zzb(null);
        }

        public final void onSaveInstanceState(Bundle bundle) {
            super.onSaveInstanceState(bundle);
            bundle.putBoolean(zzz, this.zzac);
            zzc();
        }
    }

    public static void a(@NonNull Intent intent, @Nullable Status status) {
        if (status == null) {
            intent.removeExtra("com.google.android.gms.common.api.AutoResolveHelper.status");
        } else {
            intent.putExtra("com.google.android.gms.common.api.AutoResolveHelper.status", status);
        }
    }

    /* access modifiers changed from: private */
    public static void b(Activity activity, int i, int i2, Intent intent) {
        PendingIntent createPendingResult = activity.createPendingResult(i, intent, ErrorDialogData.SUPPRESSED);
        if (createPendingResult != null) {
            try {
                createPendingResult.send(i2);
            } catch (CanceledException e) {
                if (Log.isLoggable("AutoResolveHelper", 6)) {
                    Log.e("AutoResolveHelper", "Exception sending pending result", e);
                }
            }
        } else if (Log.isLoggable("AutoResolveHelper", 5)) {
            Log.w("AutoResolveHelper", "Null pending result returned when trying to deliver task result!");
        }
    }

    /* access modifiers changed from: private */
    public static void b(Activity activity, int i, f<? extends a> fVar) {
        if (activity.isFinishing()) {
            if (Log.isLoggable("AutoResolveHelper", 3)) {
                Log.d("AutoResolveHelper", "Ignoring task result for, Activity is finishing.");
            }
        } else if (fVar.e() instanceof ResolvableApiException) {
            try {
                ((ResolvableApiException) fVar.e()).startResolutionForResult(activity, i);
            } catch (SendIntentException e) {
                if (Log.isLoggable("AutoResolveHelper", 6)) {
                    Log.e("AutoResolveHelper", "Error starting pending intent!", e);
                }
            }
        } else {
            Intent intent = new Intent();
            int i2 = 1;
            if (fVar.b()) {
                i2 = -1;
                ((a) fVar.d()).putIntoIntent(intent);
            } else if (fVar.e() instanceof ApiException) {
                ApiException apiException = (ApiException) fVar.e();
                a(intent, new Status(apiException.getStatusCode(), apiException.getMessage(), null));
            } else {
                if (Log.isLoggable("AutoResolveHelper", 6)) {
                    Log.e("AutoResolveHelper", "Unexpected non API exception!", fVar.e());
                }
                a(intent, new Status(8, "Unexpected non API exception when trying to deliver the task result to an activity!"));
            }
            b(activity, i, i2, intent);
        }
    }
}
