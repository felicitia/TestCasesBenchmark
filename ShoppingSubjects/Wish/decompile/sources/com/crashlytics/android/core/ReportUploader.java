package com.crashlytics.android.core;

import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Logger;
import io.fabric.sdk.android.services.common.ApiKey;
import io.fabric.sdk.android.services.common.BackgroundPriorityRunnable;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class ReportUploader {
    static final Map<String, String> HEADER_INVALID_CLS_FILE = Collections.singletonMap("X-CRASHLYTICS-INVALID-SESSION", "1");
    /* access modifiers changed from: private */
    public static final short[] RETRY_INTERVALS = {10, 20, 30, 60, 120, 300};
    private static final FilenameFilter crashFileFilter = new FilenameFilter() {
        public boolean accept(File file, String str) {
            return str.endsWith(".cls") && !str.contains("Session");
        }
    };
    private final CreateReportSpiCall createReportCall;
    private final Object fileAccessLock = new Object();
    /* access modifiers changed from: private */
    public Thread uploadThread;

    private class Worker extends BackgroundPriorityRunnable {
        private final float delay;

        Worker(float f) {
            this.delay = f;
        }

        public void onRun() {
            try {
                attemptUploadWithRetry();
            } catch (Exception e) {
                Fabric.getLogger().e("CrashlyticsCore", "An unexpected error occurred while attempting to upload crash reports.", e);
            }
            ReportUploader.this.uploadThread = null;
        }

        private void attemptUploadWithRetry() {
            StringBuilder sb = new StringBuilder();
            sb.append("Starting report processing in ");
            sb.append(this.delay);
            sb.append(" second(s)...");
            Fabric.getLogger().d("CrashlyticsCore", sb.toString());
            if (this.delay > 0.0f) {
                try {
                    Thread.sleep((long) (this.delay * 1000.0f));
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            CrashlyticsCore instance = CrashlyticsCore.getInstance();
            CrashlyticsUncaughtExceptionHandler handler = instance.getHandler();
            List<Report> findReports = ReportUploader.this.findReports();
            if (!handler.isHandlingException()) {
                if (findReports.isEmpty() || instance.canSendWithUserApproval()) {
                    int i = 0;
                    while (!findReports.isEmpty() && !CrashlyticsCore.getInstance().getHandler().isHandlingException()) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Attempting to send ");
                        sb2.append(findReports.size());
                        sb2.append(" report(s)");
                        Fabric.getLogger().d("CrashlyticsCore", sb2.toString());
                        for (Report forceUpload : findReports) {
                            ReportUploader.this.forceUpload(forceUpload);
                        }
                        findReports = ReportUploader.this.findReports();
                        if (!findReports.isEmpty()) {
                            int i2 = i + 1;
                            long j = (long) ReportUploader.RETRY_INTERVALS[Math.min(i, ReportUploader.RETRY_INTERVALS.length - 1)];
                            StringBuilder sb3 = new StringBuilder();
                            sb3.append("Report submisson: scheduling delayed retry in ");
                            sb3.append(j);
                            sb3.append(" seconds");
                            Fabric.getLogger().d("CrashlyticsCore", sb3.toString());
                            try {
                                Thread.sleep(j * 1000);
                                i = i2;
                            } catch (InterruptedException unused2) {
                                Thread.currentThread().interrupt();
                                return;
                            }
                        }
                    }
                    return;
                }
                StringBuilder sb4 = new StringBuilder();
                sb4.append("User declined to send. Removing ");
                sb4.append(findReports.size());
                sb4.append(" Report(s).");
                Fabric.getLogger().d("CrashlyticsCore", sb4.toString());
                for (Report remove : findReports) {
                    remove.remove();
                }
            }
        }
    }

    public ReportUploader(CreateReportSpiCall createReportSpiCall) {
        if (createReportSpiCall == null) {
            throw new IllegalArgumentException("createReportCall must not be null.");
        }
        this.createReportCall = createReportSpiCall;
    }

    public synchronized void uploadReports(float f) {
        if (this.uploadThread == null) {
            this.uploadThread = new Thread(new Worker(f), "Crashlytics Report Uploader");
            this.uploadThread.start();
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean forceUpload(Report report) {
        boolean z;
        synchronized (this.fileAccessLock) {
            z = false;
            try {
                boolean invoke = this.createReportCall.invoke(new CreateReportRequest(new ApiKey().getValue(CrashlyticsCore.getInstance().getContext()), report));
                Logger logger = Fabric.getLogger();
                String str = "CrashlyticsCore";
                StringBuilder sb = new StringBuilder();
                sb.append("Crashlytics report upload ");
                sb.append(invoke ? "complete: " : "FAILED: ");
                sb.append(report.getFileName());
                logger.i(str, sb.toString());
                if (invoke) {
                    report.remove();
                    z = true;
                }
            } catch (Exception e) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Error occurred sending report ");
                sb2.append(report);
                Fabric.getLogger().e("CrashlyticsCore", sb2.toString(), e);
            }
        }
        return z;
    }

    /* access modifiers changed from: 0000 */
    public List<Report> findReports() {
        File[] listFiles;
        Fabric.getLogger().d("CrashlyticsCore", "Checking for crash reports...");
        synchronized (this.fileAccessLock) {
            listFiles = CrashlyticsCore.getInstance().getSdkDirectory().listFiles(crashFileFilter);
        }
        LinkedList linkedList = new LinkedList();
        for (File file : listFiles) {
            StringBuilder sb = new StringBuilder();
            sb.append("Found crash report ");
            sb.append(file.getPath());
            Fabric.getLogger().d("CrashlyticsCore", sb.toString());
            linkedList.add(new SessionReport(file));
        }
        if (linkedList.isEmpty()) {
            Fabric.getLogger().d("CrashlyticsCore", "No reports found.");
        }
        return linkedList;
    }
}
