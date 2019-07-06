package androidx.work;

import android.content.Context;
import android.support.annotation.Keep;
import androidx.work.impl.Extras;

public abstract class Worker {
    private Context mAppContext;
    private Extras mExtras;
    private String mId;
    private Data mOutputData = Data.EMPTY;
    private volatile boolean mStopped;

    public enum WorkerResult {
        SUCCESS,
        FAILURE,
        RETRY
    }

    public abstract WorkerResult doWork();

    public void onStopped() {
    }

    public final Context getApplicationContext() {
        return this.mAppContext;
    }

    public final String getId() {
        return this.mId;
    }

    public final Data getInputData() {
        return this.mExtras.getInputData();
    }

    public final void setOutputData(Data data) {
        this.mOutputData = data;
    }

    public final Data getOutputData() {
        return this.mOutputData;
    }

    public final void stop() {
        this.mStopped = true;
        onStopped();
    }

    @Keep
    private void internalInit(Context context, String str, Extras extras) {
        this.mAppContext = context;
        this.mId = str;
        this.mExtras = extras;
    }

    public Extras getExtras() {
        return this.mExtras;
    }
}
