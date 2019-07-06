package com.wish.android.shaky;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.hardware.SensorManager;
import android.net.Uri;
import android.support.v4.content.LocalBroadcastManager;
import com.squareup.seismic.ShakeDetector;
import com.squareup.seismic.ShakeDetector.Listener;
import com.threatmetrix.TrustDefender.StrongAuth;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class Shaky implements Listener {
    private static final long SHAKE_COOLDOWN_MS = TimeUnit.SECONDS.toMillis(5);
    /* access modifiers changed from: private */
    public Activity activity;
    /* access modifiers changed from: private */
    public CollectDataTask collectDataTask;
    /* access modifiers changed from: private */
    public final ShakeDelegate delegate;
    private long lastShakeTime;
    private final ShakeDetector shakeDetector = new ShakeDetector(this);

    Shaky(Context context, ShakeDelegate shakeDelegate) {
        Context applicationContext = context.getApplicationContext();
        this.delegate = shakeDelegate;
        this.shakeDetector.setSensitivity(getDetectorSensitivityLevel());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("StartFeedbackFlow");
        intentFilter.addAction("EndFeedbackFlow");
        LocalBroadcastManager.getInstance(applicationContext).registerReceiver(createReceiver(), intentFilter);
    }

    public static Shaky with(Application application, ShakeDelegate shakeDelegate) {
        Shaky shaky = new Shaky(application.getApplicationContext(), shakeDelegate);
        application.registerActivityLifecycleCallbacks(new LifecycleCallbacks(shaky));
        return shaky;
    }

    /* access modifiers changed from: 0000 */
    public void setActivity(Activity activity2) {
        this.activity = activity2;
        if (activity2 != null) {
            start();
            dismissCollectFeedbackDialogIfNecessary();
            return;
        }
        stop();
    }

    /* access modifiers changed from: private */
    public void doStartFeedbackFlow() {
        new CollectDataDialog().show(this.activity.getFragmentManager(), "CollectFeedbackData");
        this.collectDataTask = new CollectDataTask(this.activity, this.delegate, createCallback());
        this.collectDataTask.execute(new Bitmap[]{getScreenshotBitmap()});
    }

    private void start() {
        if (this.delegate.isEnabled()) {
            this.shakeDetector.start((SensorManager) this.activity.getSystemService("sensor"));
        }
    }

    private void stop() {
        this.shakeDetector.stop();
    }

    public void hearShake() {
        if (!shouldIgnoreShake() && canStartFeedbackFlow()) {
            new SendFeedbackDialog().show(this.activity.getFragmentManager(), "SendFeedback");
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean shouldIgnoreShake() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis < this.lastShakeTime + SHAKE_COOLDOWN_MS) {
            return true;
        }
        this.lastShakeTime = currentTimeMillis;
        return false;
    }

    /* access modifiers changed from: 0000 */
    public boolean canStartFeedbackFlow() {
        return this.activity != null && !(this.activity instanceof FeedbackActivity) && this.activity.getFragmentManager().findFragmentByTag("SendFeedback") == null && this.activity.getFragmentManager().findFragmentByTag("CollectFeedbackData") == null;
    }

    private Bitmap getScreenshotBitmap() {
        return Utils.capture(this.activity.getWindow().getDecorView().getRootView());
    }

    /* access modifiers changed from: private */
    public void dismissCollectFeedbackDialogIfNecessary() {
        if (this.collectDataTask == null && this.activity != null) {
            CollectDataDialog collectDataDialog = (CollectDataDialog) this.activity.getFragmentManager().findFragmentByTag("CollectFeedbackData");
            if (collectDataDialog != null) {
                collectDataDialog.dismiss();
            }
        }
    }

    private BroadcastReceiver createReceiver() {
        return new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                if ("StartFeedbackFlow".equals(intent.getAction())) {
                    if (Shaky.this.activity != null) {
                        Shaky.this.doStartFeedbackFlow();
                    }
                } else if ("EndFeedbackFlow".equals(intent.getAction())) {
                    Shaky.this.delegate.submit(Shaky.this.activity, Shaky.this.unpackResult(intent));
                }
            }
        };
    }

    private Callback createCallback() {
        return new Callback() {
            public void onDataReady(Result result) {
                boolean z = (Shaky.this.activity == null || Shaky.this.collectDataTask == null) ? false : true;
                Shaky.this.collectDataTask = null;
                Shaky.this.dismissCollectFeedbackDialogIfNecessary();
                if (z) {
                    Shaky shaky = Shaky.this;
                    if (result == null) {
                        result = new Result();
                    }
                    shaky.startFeedbackActivity(result);
                }
            }
        };
    }

    /* access modifiers changed from: private */
    public void startFeedbackActivity(Result result) {
        this.activity.startActivity(FeedbackActivity.newIntent(this.activity, result.getScreenshotUri(), result.getData()));
    }

    /* access modifiers changed from: private */
    public Result unpackResult(Intent intent) {
        Result result = new Result(intent.getBundleExtra("userData"));
        result.setScreenshotUri((Uri) intent.getParcelableExtra("screenshotUri"));
        result.setTitle(intent.getStringExtra(StrongAuth.AUTH_TITLE));
        result.setMessage(intent.getStringExtra("message"));
        ArrayList arrayList = new ArrayList();
        Iterator it = result.getAttachments().iterator();
        while (it.hasNext()) {
            arrayList.add(Utils.getProviderUri((Context) this.activity, (Uri) it.next()));
        }
        result.setAttachments(arrayList);
        return result;
    }

    public int getDetectorSensitivityLevel() {
        int sensitivityLevel = this.delegate.getSensitivityLevel();
        if (sensitivityLevel == 22) {
            return 11;
        }
        return sensitivityLevel == 24 ? 15 : 13;
    }
}
