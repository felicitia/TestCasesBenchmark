package androidx.work.impl.background.systemalarm;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager.WakeLock;
import android.text.TextUtils;
import android.util.Log;
import androidx.work.impl.ExecutionListener;
import androidx.work.impl.Processor;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.utils.WakeLocks;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SystemAlarmDispatcher implements ExecutionListener {
    private final ExecutorService mCommandExecutorService;
    /* access modifiers changed from: private */
    public final CommandHandler mCommandHandler;
    private CommandsCompletedListener mCompletedListener;
    /* access modifiers changed from: private */
    public final Context mContext;
    /* access modifiers changed from: private */
    public final List<Intent> mIntents;
    private final Handler mMainHandler;
    private final Processor mProcessor;
    private final WorkManagerImpl mWorkManager;
    private final WorkTimer mWorkTimer;

    static class AddRunnable implements Runnable {
        private final SystemAlarmDispatcher mDispatcher;
        private final Intent mIntent;
        private final int mStartId;

        AddRunnable(SystemAlarmDispatcher systemAlarmDispatcher, Intent intent, int i) {
            this.mDispatcher = systemAlarmDispatcher;
            this.mIntent = intent;
            this.mStartId = i;
        }

        public void run() {
            this.mDispatcher.add(this.mIntent, this.mStartId);
        }
    }

    static class CheckForCompletionRunnable implements Runnable {
        private final SystemAlarmDispatcher mDispatcher;

        CheckForCompletionRunnable(SystemAlarmDispatcher systemAlarmDispatcher) {
            this.mDispatcher = systemAlarmDispatcher;
        }

        public void run() {
            this.mDispatcher.checkForCommandsCompleted();
        }
    }

    interface CommandsCompletedListener {
        void onAllCommandsCompleted();
    }

    SystemAlarmDispatcher(Context context) {
        this(context, null, null);
    }

    SystemAlarmDispatcher(Context context, Processor processor, WorkManagerImpl workManagerImpl) {
        this.mContext = context.getApplicationContext();
        this.mCommandHandler = new CommandHandler(this.mContext);
        this.mWorkTimer = new WorkTimer();
        if (workManagerImpl == null) {
            workManagerImpl = WorkManagerImpl.getInstance();
        }
        this.mWorkManager = workManagerImpl;
        if (processor == null) {
            processor = this.mWorkManager.getProcessor();
        }
        this.mProcessor = processor;
        this.mProcessor.addExecutionListener(this);
        this.mIntents = new ArrayList();
        this.mMainHandler = new Handler(Looper.getMainLooper());
        this.mCommandExecutorService = Executors.newSingleThreadExecutor();
    }

    /* access modifiers changed from: 0000 */
    public void onDestroy() {
        this.mProcessor.removeExecutionListener(this);
        this.mCompletedListener = null;
    }

    public void onExecuted(String str, boolean z, boolean z2) {
        postOnMainThread(new AddRunnable(this, CommandHandler.createExecutionCompletedIntent(this.mContext, str, z, z2), 0));
    }

    public boolean add(Intent intent, int i) {
        assertMainThread();
        String action = intent.getAction();
        if (TextUtils.isEmpty(action)) {
            Log.w("SystemAlarmDispatcher", "Unknown command. Ignoring");
            return false;
        } else if ("ACTION_CONSTRAINTS_CHANGED".equals(action) && hasIntentWithAction("ACTION_CONSTRAINTS_CHANGED")) {
            return false;
        } else {
            intent.putExtra("KEY_START_ID", i);
            this.mIntents.add(intent);
            processCommand();
            return true;
        }
    }

    /* access modifiers changed from: 0000 */
    public void setCompletedListener(CommandsCompletedListener commandsCompletedListener) {
        if (this.mCompletedListener != null) {
            Log.e("SystemAlarmDispatcher", "A completion listener for SystemAlarmDispatcher already exists.");
        } else {
            this.mCompletedListener = commandsCompletedListener;
        }
    }

    /* access modifiers changed from: 0000 */
    public Processor getProcessor() {
        return this.mProcessor;
    }

    /* access modifiers changed from: 0000 */
    public WorkTimer getWorkTimer() {
        return this.mWorkTimer;
    }

    /* access modifiers changed from: 0000 */
    public WorkManagerImpl getWorkManager() {
        return this.mWorkManager;
    }

    /* access modifiers changed from: 0000 */
    public void postOnMainThread(Runnable runnable) {
        this.mMainHandler.post(runnable);
    }

    /* access modifiers changed from: private */
    public void checkForCommandsCompleted() {
        assertMainThread();
        if (!this.mCommandHandler.hasPendingCommands() && this.mIntents.isEmpty()) {
            Log.d("SystemAlarmDispatcher", "No more commands & intents.");
            if (this.mCompletedListener != null) {
                this.mCompletedListener.onAllCommandsCompleted();
            }
        }
    }

    private void processCommand() {
        assertMainThread();
        WakeLock newWakeLock = WakeLocks.newWakeLock(this.mContext, "ProcessCommand");
        try {
            newWakeLock.acquire();
            this.mCommandExecutorService.submit(new Runnable() {
                public void run() {
                    Intent intent = (Intent) SystemAlarmDispatcher.this.mIntents.get(0);
                    if (intent != null) {
                        String action = intent.getAction();
                        int intExtra = intent.getIntExtra("KEY_START_ID", 0);
                        Log.d("SystemAlarmDispatcher", String.format("Processing command %s, %s", new Object[]{intent, Integer.valueOf(intExtra)}));
                        WakeLock newWakeLock = WakeLocks.newWakeLock(SystemAlarmDispatcher.this.mContext, String.format("%s (%s)", new Object[]{action, Integer.valueOf(intExtra)}));
                        try {
                            Log.d("SystemAlarmDispatcher", String.format("Acquiring operation wake lock (%s) %s", new Object[]{action, newWakeLock}));
                            newWakeLock.acquire();
                            SystemAlarmDispatcher.this.mCommandHandler.onHandleIntent(intent, intExtra, SystemAlarmDispatcher.this);
                            synchronized (SystemAlarmDispatcher.this.mIntents) {
                                SystemAlarmDispatcher.this.mIntents.remove(0);
                            }
                            Log.d("SystemAlarmDispatcher", String.format("Releasing operation wake lock (%s) %s", new Object[]{action, newWakeLock}));
                            newWakeLock.release();
                            SystemAlarmDispatcher.this.postOnMainThread(new CheckForCompletionRunnable(SystemAlarmDispatcher.this));
                        } catch (Throwable th) {
                            synchronized (SystemAlarmDispatcher.this.mIntents) {
                                SystemAlarmDispatcher.this.mIntents.remove(0);
                                Log.d("SystemAlarmDispatcher", String.format("Releasing operation wake lock (%s) %s", new Object[]{action, newWakeLock}));
                                newWakeLock.release();
                                SystemAlarmDispatcher.this.postOnMainThread(new CheckForCompletionRunnable(SystemAlarmDispatcher.this));
                                throw th;
                            }
                        }
                    }
                }
            });
        } finally {
            newWakeLock.release();
        }
    }

    private boolean hasIntentWithAction(String str) {
        assertMainThread();
        for (Intent action : this.mIntents) {
            if (str.equals(action.getAction())) {
                return true;
            }
        }
        return false;
    }

    private void assertMainThread() {
        if (this.mMainHandler.getLooper().getThread() != Thread.currentThread()) {
            throw new IllegalStateException("Needs to be invoked on the main thread.");
        }
    }
}
