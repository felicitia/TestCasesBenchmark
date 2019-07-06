package android.support.v4.app;

import android.app.Activity;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.SparseIntArray;
import android.view.FrameMetrics;
import android.view.Window;
import android.view.Window.OnFrameMetricsAvailableListener;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

public class FrameMetricsAggregator {
    private FrameMetricsBaseImpl mInstance;

    private static class FrameMetricsApi24Impl extends FrameMetricsBaseImpl {
        private static Handler sHandler;
        private static HandlerThread sHandlerThread;
        private ArrayList<WeakReference<Activity>> mActivities = new ArrayList<>();
        OnFrameMetricsAvailableListener mListener = new OnFrameMetricsAvailableListener() {
            public void onFrameMetricsAvailable(Window window, FrameMetrics frameMetrics, int i) {
                if ((FrameMetricsApi24Impl.this.mTrackingFlags & 1) != 0) {
                    FrameMetricsApi24Impl.this.addDurationItem(FrameMetricsApi24Impl.this.mMetrics[0], frameMetrics.getMetric(8));
                }
                if ((FrameMetricsApi24Impl.this.mTrackingFlags & 2) != 0) {
                    FrameMetricsApi24Impl.this.addDurationItem(FrameMetricsApi24Impl.this.mMetrics[1], frameMetrics.getMetric(1));
                }
                if ((FrameMetricsApi24Impl.this.mTrackingFlags & 4) != 0) {
                    FrameMetricsApi24Impl.this.addDurationItem(FrameMetricsApi24Impl.this.mMetrics[2], frameMetrics.getMetric(3));
                }
                if ((FrameMetricsApi24Impl.this.mTrackingFlags & 8) != 0) {
                    FrameMetricsApi24Impl.this.addDurationItem(FrameMetricsApi24Impl.this.mMetrics[3], frameMetrics.getMetric(4));
                }
                if ((FrameMetricsApi24Impl.this.mTrackingFlags & 16) != 0) {
                    FrameMetricsApi24Impl.this.addDurationItem(FrameMetricsApi24Impl.this.mMetrics[4], frameMetrics.getMetric(5));
                }
                if ((FrameMetricsApi24Impl.this.mTrackingFlags & 64) != 0) {
                    FrameMetricsApi24Impl.this.addDurationItem(FrameMetricsApi24Impl.this.mMetrics[6], frameMetrics.getMetric(7));
                }
                if ((FrameMetricsApi24Impl.this.mTrackingFlags & 32) != 0) {
                    FrameMetricsApi24Impl.this.addDurationItem(FrameMetricsApi24Impl.this.mMetrics[5], frameMetrics.getMetric(6));
                }
                if ((FrameMetricsApi24Impl.this.mTrackingFlags & 128) != 0) {
                    FrameMetricsApi24Impl.this.addDurationItem(FrameMetricsApi24Impl.this.mMetrics[7], frameMetrics.getMetric(0));
                }
                if ((FrameMetricsApi24Impl.this.mTrackingFlags & 256) != 0) {
                    FrameMetricsApi24Impl.this.addDurationItem(FrameMetricsApi24Impl.this.mMetrics[8], frameMetrics.getMetric(2));
                }
            }
        };
        /* access modifiers changed from: private */
        public SparseIntArray[] mMetrics = new SparseIntArray[9];
        /* access modifiers changed from: private */
        public int mTrackingFlags;

        FrameMetricsApi24Impl(int i) {
            super();
            this.mTrackingFlags = i;
        }

        /* access modifiers changed from: 0000 */
        public void addDurationItem(SparseIntArray sparseIntArray, long j) {
            if (sparseIntArray != null) {
                int i = (int) ((j + 500000) / 1000000);
                if (j >= 0) {
                    sparseIntArray.put(i, sparseIntArray.get(i) + 1);
                }
            }
        }

        public void add(Activity activity) {
            if (sHandlerThread == null) {
                sHandlerThread = new HandlerThread("FrameMetricsAggregator");
                sHandlerThread.start();
                sHandler = new Handler(sHandlerThread.getLooper());
            }
            for (int i = 0; i <= 8; i++) {
                if (this.mMetrics[i] == null && (this.mTrackingFlags & (1 << i)) != 0) {
                    this.mMetrics[i] = new SparseIntArray();
                }
            }
            activity.getWindow().addOnFrameMetricsAvailableListener(this.mListener, sHandler);
            this.mActivities.add(new WeakReference(activity));
        }

        public SparseIntArray[] remove(Activity activity) {
            Iterator it = this.mActivities.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                WeakReference weakReference = (WeakReference) it.next();
                if (weakReference.get() == activity) {
                    this.mActivities.remove(weakReference);
                    break;
                }
            }
            activity.getWindow().removeOnFrameMetricsAvailableListener(this.mListener);
            return this.mMetrics;
        }
    }

    private static class FrameMetricsBaseImpl {
        public void add(Activity activity) {
        }

        public SparseIntArray[] remove(Activity activity) {
            return null;
        }

        private FrameMetricsBaseImpl() {
        }
    }

    public FrameMetricsAggregator() {
        this(1);
    }

    public FrameMetricsAggregator(int i) {
        if (VERSION.SDK_INT >= 24) {
            this.mInstance = new FrameMetricsApi24Impl(i);
        } else {
            this.mInstance = new FrameMetricsBaseImpl();
        }
    }

    public void add(Activity activity) {
        this.mInstance.add(activity);
    }

    public SparseIntArray[] remove(Activity activity) {
        return this.mInstance.remove(activity);
    }
}
