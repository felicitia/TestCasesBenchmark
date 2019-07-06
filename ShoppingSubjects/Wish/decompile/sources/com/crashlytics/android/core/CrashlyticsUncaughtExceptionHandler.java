package com.crashlytics.android.core;

import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import com.crashlytics.android.core.internal.models.SessionEventData;
import com.google.android.gms.measurement.AppMeasurement;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.common.DeliveryMechanism;
import io.fabric.sdk.android.services.common.IdManager;
import io.fabric.sdk.android.services.persistence.FileStore;
import io.fabric.sdk.android.services.settings.Settings;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class CrashlyticsUncaughtExceptionHandler implements UncaughtExceptionHandler {
    static final FilenameFilter ANY_SESSION_FILENAME_FILTER = new FilenameFilter() {
        public boolean accept(File file, String str) {
            return CrashlyticsUncaughtExceptionHandler.SESSION_FILE_PATTERN.matcher(str).matches();
        }
    };
    private static final String[] INITIAL_SESSION_PART_TAGS = {"SessionUser", "SessionApp", "SessionOS", "SessionDevice"};
    static final Comparator<File> LARGEST_FILE_NAME_FIRST = new Comparator<File>() {
        public int compare(File file, File file2) {
            return file2.getName().compareTo(file.getName());
        }
    };
    /* access modifiers changed from: private */
    public static final Map<String, String> SEND_AT_CRASHTIME_HEADER = Collections.singletonMap("X-CRASHLYTICS-SEND-FLAGS", "1");
    static final FilenameFilter SESSION_FILE_FILTER = new FilenameFilter() {
        public boolean accept(File file, String str) {
            return str.length() == ".cls".length() + 35 && str.endsWith(".cls");
        }
    };
    /* access modifiers changed from: private */
    public static final Pattern SESSION_FILE_PATTERN = Pattern.compile("([\\d|A-Z|a-z]{12}\\-[\\d|A-Z|a-z]{4}\\-[\\d|A-Z|a-z]{4}\\-[\\d|A-Z|a-z]{12}).+");
    static final Comparator<File> SMALLEST_FILE_NAME_FIRST = new Comparator<File>() {
        public int compare(File file, File file2) {
            return file.getName().compareTo(file2.getName());
        }
    };
    /* access modifiers changed from: private */
    public final CrashlyticsCore crashlyticsCore;
    private final UncaughtExceptionHandler defaultHandler;
    private final DevicePowerStateListener devicePowerStateListener;
    private final AtomicInteger eventCounter = new AtomicInteger(0);
    private final CrashlyticsExecutorServiceWrapper executorServiceWrapper;
    private final FileStore fileStore;
    private final IdManager idManager;
    /* access modifiers changed from: private */
    public final AtomicBoolean isHandlingException;
    /* access modifiers changed from: private */
    public final LogFileManager logFileManager;
    private final String unityVersion;

    private static class AnySessionPartFileFilter implements FilenameFilter {
        private AnySessionPartFileFilter() {
        }

        public boolean accept(File file, String str) {
            return !CrashlyticsUncaughtExceptionHandler.SESSION_FILE_FILTER.accept(file, str) && CrashlyticsUncaughtExceptionHandler.SESSION_FILE_PATTERN.matcher(str).matches();
        }
    }

    static class FileNameContainsFilter implements FilenameFilter {
        private final String string;

        public FileNameContainsFilter(String str) {
            this.string = str;
        }

        public boolean accept(File file, String str) {
            return str.contains(this.string) && !str.endsWith(".cls_temp");
        }
    }

    private static final class SendSessionRunnable implements Runnable {
        private final CrashlyticsCore crashlyticsCore;
        private final File fileToSend;

        public SendSessionRunnable(CrashlyticsCore crashlyticsCore2, File file) {
            this.crashlyticsCore = crashlyticsCore2;
            this.fileToSend = file;
        }

        public void run() {
            if (CommonUtils.canTryConnection(this.crashlyticsCore.getContext())) {
                Fabric.getLogger().d("CrashlyticsCore", "Attempting to send crash report at time of crash...");
                CreateReportSpiCall createReportSpiCall = this.crashlyticsCore.getCreateReportSpiCall(Settings.getInstance().awaitSettingsData());
                if (createReportSpiCall != null) {
                    new ReportUploader(createReportSpiCall).forceUpload(new SessionReport(this.fileToSend, CrashlyticsUncaughtExceptionHandler.SEND_AT_CRASHTIME_HEADER));
                }
            }
        }
    }

    static class SessionPartFileFilter implements FilenameFilter {
        private final String sessionId;

        public SessionPartFileFilter(String str) {
            this.sessionId = str;
        }

        public boolean accept(File file, String str) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.sessionId);
            sb.append(".cls");
            boolean z = false;
            if (str.equals(sb.toString())) {
                return false;
            }
            if (str.contains(this.sessionId) && !str.endsWith(".cls_temp")) {
                z = true;
            }
            return z;
        }
    }

    CrashlyticsUncaughtExceptionHandler(UncaughtExceptionHandler uncaughtExceptionHandler, CrashlyticsExecutorServiceWrapper crashlyticsExecutorServiceWrapper, IdManager idManager2, UnityVersionProvider unityVersionProvider, FileStore fileStore2, CrashlyticsCore crashlyticsCore2) {
        this.defaultHandler = uncaughtExceptionHandler;
        this.executorServiceWrapper = crashlyticsExecutorServiceWrapper;
        this.idManager = idManager2;
        this.crashlyticsCore = crashlyticsCore2;
        this.unityVersion = unityVersionProvider.getUnityVersion();
        this.fileStore = fileStore2;
        this.isHandlingException = new AtomicBoolean(false);
        Context context = crashlyticsCore2.getContext();
        this.logFileManager = new LogFileManager(context, fileStore2);
        this.devicePowerStateListener = new DevicePowerStateListener(context);
    }

    public synchronized void uncaughtException(final Thread thread, final Throwable th) {
        AtomicBoolean atomicBoolean;
        this.isHandlingException.set(true);
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("Crashlytics is handling uncaught exception \"");
            sb.append(th);
            sb.append("\" from thread ");
            sb.append(thread.getName());
            Fabric.getLogger().d("CrashlyticsCore", sb.toString());
            this.devicePowerStateListener.dispose();
            final Date date = new Date();
            this.executorServiceWrapper.executeSyncLoggingException(new Callable<Void>() {
                public Void call() throws Exception {
                    CrashlyticsUncaughtExceptionHandler.this.handleUncaughtException(date, thread, th);
                    return null;
                }
            });
            Fabric.getLogger().d("CrashlyticsCore", "Crashlytics completed exception processing. Invoking default exception handler.");
            this.defaultHandler.uncaughtException(thread, th);
            atomicBoolean = this.isHandlingException;
        } catch (Exception e) {
            try {
                Fabric.getLogger().e("CrashlyticsCore", "An error occurred in the uncaught exception handler", e);
                Fabric.getLogger().d("CrashlyticsCore", "Crashlytics completed exception processing. Invoking default exception handler.");
                this.defaultHandler.uncaughtException(thread, th);
                atomicBoolean = this.isHandlingException;
            } catch (Throwable th2) {
                Fabric.getLogger().d("CrashlyticsCore", "Crashlytics completed exception processing. Invoking default exception handler.");
                this.defaultHandler.uncaughtException(thread, th);
                this.isHandlingException.set(false);
                throw th2;
            }
        }
        atomicBoolean.set(false);
        return;
    }

    /* access modifiers changed from: private */
    public void handleUncaughtException(Date date, Thread thread, Throwable th) throws Exception {
        this.crashlyticsCore.createCrashMarker();
        writeFatal(date, thread, th);
        doCloseSessions();
        doOpenSession();
        trimSessionFiles();
        if (!this.crashlyticsCore.shouldPromptUserBeforeSendingCrashReports()) {
            sendSessionReports();
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean isHandlingException() {
        return this.isHandlingException.get();
    }

    /* access modifiers changed from: 0000 */
    public void writeToLog(final long j, final String str) {
        this.executorServiceWrapper.executeAsync((Callable<T>) new Callable<Void>() {
            public Void call() throws Exception {
                if (!CrashlyticsUncaughtExceptionHandler.this.isHandlingException.get()) {
                    CrashlyticsUncaughtExceptionHandler.this.logFileManager.writeToLog(j, str);
                }
                return null;
            }
        });
    }

    /* access modifiers changed from: 0000 */
    public void writeNonFatalException(final Thread thread, final Throwable th) {
        final Date date = new Date();
        this.executorServiceWrapper.executeAsync((Runnable) new Runnable() {
            public void run() {
                if (!CrashlyticsUncaughtExceptionHandler.this.isHandlingException.get()) {
                    CrashlyticsUncaughtExceptionHandler.this.doWriteNonFatal(date, thread, th);
                }
            }
        });
    }

    /* access modifiers changed from: 0000 */
    public void cacheUserData(final String str, final String str2, final String str3) {
        this.executorServiceWrapper.executeAsync((Callable<T>) new Callable<Void>() {
            public Void call() throws Exception {
                new MetaDataStore(CrashlyticsUncaughtExceptionHandler.this.getFilesDir()).writeUserData(CrashlyticsUncaughtExceptionHandler.this.getCurrentSessionId(), new UserMetaData(str, str2, str3));
                return null;
            }
        });
    }

    /* access modifiers changed from: 0000 */
    public void cacheKeyData(final Map<String, String> map) {
        this.executorServiceWrapper.executeAsync((Callable<T>) new Callable<Void>() {
            public Void call() throws Exception {
                new MetaDataStore(CrashlyticsUncaughtExceptionHandler.this.getFilesDir()).writeKeyData(CrashlyticsUncaughtExceptionHandler.this.getCurrentSessionId(), map);
                return null;
            }
        });
    }

    /* access modifiers changed from: 0000 */
    public void openSession() {
        this.executorServiceWrapper.executeAsync((Callable<T>) new Callable<Void>() {
            public Void call() throws Exception {
                CrashlyticsUncaughtExceptionHandler.this.doOpenSession();
                return null;
            }
        });
    }

    /* access modifiers changed from: private */
    public String getCurrentSessionId() {
        File[] listSortedSessionBeginFiles = listSortedSessionBeginFiles();
        if (listSortedSessionBeginFiles.length > 0) {
            return getSessionIdFromSessionFile(listSortedSessionBeginFiles[0]);
        }
        return null;
    }

    private String getPreviousSessionId() {
        File[] listSortedSessionBeginFiles = listSortedSessionBeginFiles();
        if (listSortedSessionBeginFiles.length > 1) {
            return getSessionIdFromSessionFile(listSortedSessionBeginFiles[1]);
        }
        return null;
    }

    private String getSessionIdFromSessionFile(File file) {
        return file.getName().substring(0, 35);
    }

    /* access modifiers changed from: 0000 */
    public boolean finalizeSessions() {
        return ((Boolean) this.executorServiceWrapper.executeSyncLoggingException(new Callable<Boolean>() {
            public Boolean call() throws Exception {
                if (CrashlyticsUncaughtExceptionHandler.this.isHandlingException.get()) {
                    Fabric.getLogger().d("CrashlyticsCore", "Skipping session finalization because a crash has already occurred.");
                    return Boolean.FALSE;
                }
                Fabric.getLogger().d("CrashlyticsCore", "Finalizing previously open sessions.");
                SessionEventData externalCrashEventData = CrashlyticsUncaughtExceptionHandler.this.crashlyticsCore.getExternalCrashEventData();
                if (externalCrashEventData != null) {
                    CrashlyticsUncaughtExceptionHandler.this.writeExternalCrashEvent(externalCrashEventData);
                }
                CrashlyticsUncaughtExceptionHandler.this.doCloseSessions(true);
                Fabric.getLogger().d("CrashlyticsCore", "Closed all previously open sessions");
                return Boolean.TRUE;
            }
        })).booleanValue();
    }

    /* access modifiers changed from: private */
    public void doOpenSession() throws Exception {
        Date date = new Date();
        String clsuuid = new CLSUUID(this.idManager).toString();
        StringBuilder sb = new StringBuilder();
        sb.append("Opening an new session with ID ");
        sb.append(clsuuid);
        Fabric.getLogger().d("CrashlyticsCore", sb.toString());
        writeBeginSession(clsuuid, date);
        writeSessionApp(clsuuid);
        writeSessionOS(clsuuid);
        writeSessionDevice(clsuuid);
        this.logFileManager.setCurrentSession(clsuuid);
    }

    /* access modifiers changed from: 0000 */
    public void doCloseSessions() throws Exception {
        doCloseSessions(false);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Incorrect type for immutable var: ssa=boolean, code=int, for r3v0, types: [boolean, int] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void doCloseSessions(int r3) throws java.lang.Exception {
        /*
            r2 = this;
            int r0 = r3 + 8
            r2.trimOpenSessions(r0)
            java.io.File[] r0 = r2.listSortedSessionBeginFiles()
            int r1 = r0.length
            if (r1 > r3) goto L_0x0018
            io.fabric.sdk.android.Logger r3 = io.fabric.sdk.android.Fabric.getLogger()
            java.lang.String r0 = "CrashlyticsCore"
            java.lang.String r1 = "No open sessions to be closed."
            r3.d(r0, r1)
            return
        L_0x0018:
            r1 = r0[r3]
            java.lang.String r1 = r2.getSessionIdFromSessionFile(r1)
            r2.writeSessionUser(r1)
            com.crashlytics.android.core.CrashlyticsCore r1 = r2.crashlyticsCore
            io.fabric.sdk.android.services.settings.SessionSettingsData r1 = com.crashlytics.android.core.CrashlyticsCore.getSessionSettingsData()
            if (r1 != 0) goto L_0x0035
            io.fabric.sdk.android.Logger r3 = io.fabric.sdk.android.Fabric.getLogger()
            java.lang.String r0 = "CrashlyticsCore"
            java.lang.String r1 = "Unable to close session. Settings are not loaded."
            r3.d(r0, r1)
            return
        L_0x0035:
            int r1 = r1.maxCustomExceptionEvents
            r2.closeOpenSessions(r0, r3, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.crashlytics.android.core.CrashlyticsUncaughtExceptionHandler.doCloseSessions(boolean):void");
    }

    private void closeOpenSessions(File[] fileArr, int i, int i2) {
        Fabric.getLogger().d("CrashlyticsCore", "Closing open sessions.");
        while (i < fileArr.length) {
            File file = fileArr[i];
            String sessionIdFromSessionFile = getSessionIdFromSessionFile(file);
            StringBuilder sb = new StringBuilder();
            sb.append("Closing session: ");
            sb.append(sessionIdFromSessionFile);
            Fabric.getLogger().d("CrashlyticsCore", sb.toString());
            writeSessionPartsToSessionFile(file, sessionIdFromSessionFile, i2);
            i++;
        }
    }

    private void closeWithoutRenamingOrLog(ClsFileOutputStream clsFileOutputStream) {
        if (clsFileOutputStream != null) {
            try {
                clsFileOutputStream.closeInProgressStream();
            } catch (IOException e) {
                Fabric.getLogger().e("CrashlyticsCore", "Error closing session file stream in the presence of an exception", e);
            }
        }
    }

    private void deleteSessionPartFilesFor(String str) {
        for (File delete : listSessionPartFilesFor(str)) {
            delete.delete();
        }
    }

    private File[] listSessionPartFilesFor(String str) {
        return listFilesMatching(new SessionPartFileFilter(str));
    }

    private File[] listCompleteSessionFiles() {
        return listFilesMatching(SESSION_FILE_FILTER);
    }

    /* access modifiers changed from: 0000 */
    public File[] listSessionBeginFiles() {
        return listFilesMatching(new FileNameContainsFilter("BeginSession"));
    }

    private File[] listSortedSessionBeginFiles() {
        File[] listSessionBeginFiles = listSessionBeginFiles();
        Arrays.sort(listSessionBeginFiles, LARGEST_FILE_NAME_FIRST);
        return listSessionBeginFiles;
    }

    /* access modifiers changed from: private */
    public File[] listFilesMatching(FilenameFilter filenameFilter) {
        return ensureFileArrayNotNull(getFilesDir().listFiles(filenameFilter));
    }

    private File[] ensureFileArrayNotNull(File[] fileArr) {
        return fileArr == null ? new File[0] : fileArr;
    }

    private void trimSessionEventFiles(String str, int i) {
        File filesDir = getFilesDir();
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("SessionEvent");
        Utils.capFileCount(filesDir, new FileNameContainsFilter(sb.toString()), i, SMALLEST_FILE_NAME_FIRST);
    }

    /* access modifiers changed from: 0000 */
    public void trimSessionFiles() {
        Utils.capFileCount(getFilesDir(), SESSION_FILE_FILTER, 4, SMALLEST_FILE_NAME_FIRST);
    }

    private void trimOpenSessions(int i) {
        File[] listFilesMatching;
        HashSet hashSet = new HashSet();
        File[] listSortedSessionBeginFiles = listSortedSessionBeginFiles();
        int min = Math.min(i, listSortedSessionBeginFiles.length);
        for (int i2 = 0; i2 < min; i2++) {
            hashSet.add(getSessionIdFromSessionFile(listSortedSessionBeginFiles[i2]));
        }
        this.logFileManager.discardOldLogFiles(hashSet);
        for (File file : listFilesMatching(new AnySessionPartFileFilter())) {
            String name = file.getName();
            Matcher matcher = SESSION_FILE_PATTERN.matcher(name);
            matcher.matches();
            if (!hashSet.contains(matcher.group(1))) {
                StringBuilder sb = new StringBuilder();
                sb.append("Trimming open session file: ");
                sb.append(name);
                Fabric.getLogger().d("CrashlyticsCore", sb.toString());
                file.delete();
            }
        }
    }

    private File[] getTrimmedNonFatalFiles(String str, File[] fileArr, int i) {
        if (fileArr.length <= i) {
            return fileArr;
        }
        Fabric.getLogger().d("CrashlyticsCore", String.format(Locale.US, "Trimming down to %d logged exceptions.", new Object[]{Integer.valueOf(i)}));
        trimSessionEventFiles(str, i);
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("SessionEvent");
        return listFilesMatching(new FileNameContainsFilter(sb.toString()));
    }

    /* access modifiers changed from: 0000 */
    public void cleanInvalidTempFiles() {
        this.executorServiceWrapper.executeAsync((Runnable) new Runnable() {
            public void run() {
                CrashlyticsUncaughtExceptionHandler.this.doCleanInvalidTempFiles(CrashlyticsUncaughtExceptionHandler.this.listFilesMatching(ClsFileOutputStream.TEMP_FILENAME_FILTER));
            }
        });
    }

    /* access modifiers changed from: 0000 */
    public void doCleanInvalidTempFiles(File[] fileArr) {
        File[] listFilesMatching;
        deleteLegacyInvalidCacheDir();
        for (File file : fileArr) {
            StringBuilder sb = new StringBuilder();
            sb.append("Found invalid session part file: ");
            sb.append(file);
            Fabric.getLogger().d("CrashlyticsCore", sb.toString());
            final String sessionIdFromSessionFile = getSessionIdFromSessionFile(file);
            AnonymousClass13 r4 = new FilenameFilter() {
                public boolean accept(File file, String str) {
                    return str.startsWith(sessionIdFromSessionFile);
                }
            };
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Deleting all part files for invalid session: ");
            sb2.append(sessionIdFromSessionFile);
            Fabric.getLogger().d("CrashlyticsCore", sb2.toString());
            for (File file2 : listFilesMatching(r4)) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Deleting session file: ");
                sb3.append(file2);
                Fabric.getLogger().d("CrashlyticsCore", sb3.toString());
                file2.delete();
            }
        }
    }

    private void deleteLegacyInvalidCacheDir() {
        File file = new File(this.crashlyticsCore.getSdkDirectory(), "invalidClsFiles");
        if (file.exists()) {
            if (file.isDirectory()) {
                for (File delete : file.listFiles()) {
                    delete.delete();
                }
            }
            file.delete();
        }
    }

    private void writeFatal(Date date, Thread thread, Throwable th) {
        ClsFileOutputStream clsFileOutputStream;
        CodedOutputStream codedOutputStream = null;
        try {
            String currentSessionId = getCurrentSessionId();
            if (currentSessionId == null) {
                Fabric.getLogger().e("CrashlyticsCore", "Tried to write a fatal exception while no session was open.", null);
                CommonUtils.flushOrLog(null, "Failed to flush to session begin file.");
                CommonUtils.closeOrLog(null, "Failed to close fatal exception file output stream.");
                return;
            }
            CrashlyticsCore.recordFatalExceptionEvent(currentSessionId);
            File filesDir = getFilesDir();
            StringBuilder sb = new StringBuilder();
            sb.append(currentSessionId);
            sb.append("SessionCrash");
            clsFileOutputStream = new ClsFileOutputStream(filesDir, sb.toString());
            try {
                CodedOutputStream newInstance = CodedOutputStream.newInstance(clsFileOutputStream);
                try {
                    writeSessionEvent(newInstance, date, thread, th, AppMeasurement.CRASH_ORIGIN, true);
                    CommonUtils.flushOrLog(newInstance, "Failed to flush to session begin file.");
                } catch (Exception e) {
                    e = e;
                    codedOutputStream = newInstance;
                    try {
                        Fabric.getLogger().e("CrashlyticsCore", "An error occurred in the fatal exception logger", e);
                        CommonUtils.flushOrLog(codedOutputStream, "Failed to flush to session begin file.");
                        CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close fatal exception file output stream.");
                    } catch (Throwable th2) {
                        th = th2;
                        CommonUtils.flushOrLog(codedOutputStream, "Failed to flush to session begin file.");
                        CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close fatal exception file output stream.");
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    codedOutputStream = newInstance;
                    CommonUtils.flushOrLog(codedOutputStream, "Failed to flush to session begin file.");
                    CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close fatal exception file output stream.");
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
                Fabric.getLogger().e("CrashlyticsCore", "An error occurred in the fatal exception logger", e);
                CommonUtils.flushOrLog(codedOutputStream, "Failed to flush to session begin file.");
                CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close fatal exception file output stream.");
            }
            CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close fatal exception file output stream.");
        } catch (Exception e3) {
            e = e3;
            clsFileOutputStream = null;
            Fabric.getLogger().e("CrashlyticsCore", "An error occurred in the fatal exception logger", e);
            CommonUtils.flushOrLog(codedOutputStream, "Failed to flush to session begin file.");
            CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close fatal exception file output stream.");
        } catch (Throwable th4) {
            th = th4;
            clsFileOutputStream = null;
            CommonUtils.flushOrLog(codedOutputStream, "Failed to flush to session begin file.");
            CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close fatal exception file output stream.");
            throw th;
        }
    }

    /* access modifiers changed from: private */
    public void writeExternalCrashEvent(SessionEventData sessionEventData) throws IOException {
        ClsFileOutputStream clsFileOutputStream;
        CodedOutputStream codedOutputStream = null;
        try {
            String previousSessionId = getPreviousSessionId();
            if (previousSessionId == null) {
                Fabric.getLogger().e("CrashlyticsCore", "Tried to write a native crash while no session was open.", null);
                CommonUtils.flushOrLog(null, "Failed to flush to session begin file.");
                CommonUtils.closeOrLog(null, "Failed to close fatal exception file output stream.");
                return;
            }
            CrashlyticsCore.recordFatalExceptionEvent(previousSessionId);
            File filesDir = getFilesDir();
            StringBuilder sb = new StringBuilder();
            sb.append(previousSessionId);
            sb.append("SessionCrash");
            clsFileOutputStream = new ClsFileOutputStream(filesDir, sb.toString());
            try {
                CodedOutputStream newInstance = CodedOutputStream.newInstance(clsFileOutputStream);
                try {
                    NativeCrashWriter.writeNativeCrash(sessionEventData, new LogFileManager(this.crashlyticsCore.getContext(), this.fileStore, previousSessionId), new MetaDataStore(getFilesDir()).readKeyData(previousSessionId), newInstance);
                    CommonUtils.flushOrLog(newInstance, "Failed to flush to session begin file.");
                } catch (Exception e) {
                    e = e;
                    codedOutputStream = newInstance;
                    try {
                        Fabric.getLogger().e("CrashlyticsCore", "An error occurred in the native crash logger", e);
                        CommonUtils.flushOrLog(codedOutputStream, "Failed to flush to session begin file.");
                        CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close fatal exception file output stream.");
                    } catch (Throwable th) {
                        th = th;
                        CommonUtils.flushOrLog(codedOutputStream, "Failed to flush to session begin file.");
                        CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close fatal exception file output stream.");
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    codedOutputStream = newInstance;
                    CommonUtils.flushOrLog(codedOutputStream, "Failed to flush to session begin file.");
                    CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close fatal exception file output stream.");
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
                Fabric.getLogger().e("CrashlyticsCore", "An error occurred in the native crash logger", e);
                CommonUtils.flushOrLog(codedOutputStream, "Failed to flush to session begin file.");
                CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close fatal exception file output stream.");
            }
            CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close fatal exception file output stream.");
        } catch (Exception e3) {
            e = e3;
            clsFileOutputStream = null;
            Fabric.getLogger().e("CrashlyticsCore", "An error occurred in the native crash logger", e);
            CommonUtils.flushOrLog(codedOutputStream, "Failed to flush to session begin file.");
            CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close fatal exception file output stream.");
        } catch (Throwable th3) {
            th = th3;
            clsFileOutputStream = null;
            CommonUtils.flushOrLog(codedOutputStream, "Failed to flush to session begin file.");
            CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close fatal exception file output stream.");
            throw th;
        }
    }

    /* access modifiers changed from: private */
    public void doWriteNonFatal(Date date, Thread thread, Throwable th) {
        ClsFileOutputStream clsFileOutputStream;
        String currentSessionId = getCurrentSessionId();
        CodedOutputStream codedOutputStream = null;
        if (currentSessionId == null) {
            Fabric.getLogger().e("CrashlyticsCore", "Tried to write a non-fatal exception while no session was open.", null);
            return;
        }
        CrashlyticsCore.recordLoggedExceptionEvent(currentSessionId);
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("Crashlytics is logging non-fatal exception \"");
            sb.append(th);
            sb.append("\" from thread ");
            sb.append(thread.getName());
            Fabric.getLogger().d("CrashlyticsCore", sb.toString());
            String padWithZerosToMaxIntWidth = CommonUtils.padWithZerosToMaxIntWidth(this.eventCounter.getAndIncrement());
            StringBuilder sb2 = new StringBuilder();
            sb2.append(currentSessionId);
            sb2.append("SessionEvent");
            sb2.append(padWithZerosToMaxIntWidth);
            clsFileOutputStream = new ClsFileOutputStream(getFilesDir(), sb2.toString());
            try {
                CodedOutputStream newInstance = CodedOutputStream.newInstance(clsFileOutputStream);
                try {
                    writeSessionEvent(newInstance, date, thread, th, "error", false);
                    CommonUtils.flushOrLog(newInstance, "Failed to flush to non-fatal file.");
                } catch (Exception e) {
                    e = e;
                    codedOutputStream = newInstance;
                    try {
                        Fabric.getLogger().e("CrashlyticsCore", "An error occurred in the non-fatal exception logger", e);
                        CommonUtils.flushOrLog(codedOutputStream, "Failed to flush to non-fatal file.");
                        CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close non-fatal file output stream.");
                        trimSessionEventFiles(currentSessionId, 64);
                    } catch (Throwable th2) {
                        th = th2;
                        CommonUtils.flushOrLog(codedOutputStream, "Failed to flush to non-fatal file.");
                        CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close non-fatal file output stream.");
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    codedOutputStream = newInstance;
                    CommonUtils.flushOrLog(codedOutputStream, "Failed to flush to non-fatal file.");
                    CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close non-fatal file output stream.");
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
                Fabric.getLogger().e("CrashlyticsCore", "An error occurred in the non-fatal exception logger", e);
                CommonUtils.flushOrLog(codedOutputStream, "Failed to flush to non-fatal file.");
                CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close non-fatal file output stream.");
                trimSessionEventFiles(currentSessionId, 64);
            }
        } catch (Exception e3) {
            e = e3;
            clsFileOutputStream = null;
            Fabric.getLogger().e("CrashlyticsCore", "An error occurred in the non-fatal exception logger", e);
            CommonUtils.flushOrLog(codedOutputStream, "Failed to flush to non-fatal file.");
            CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close non-fatal file output stream.");
            trimSessionEventFiles(currentSessionId, 64);
        } catch (Throwable th4) {
            th = th4;
            clsFileOutputStream = null;
            CommonUtils.flushOrLog(codedOutputStream, "Failed to flush to non-fatal file.");
            CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close non-fatal file output stream.");
            throw th;
        }
        CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close non-fatal file output stream.");
        try {
            trimSessionEventFiles(currentSessionId, 64);
        } catch (Exception e4) {
            Fabric.getLogger().e("CrashlyticsCore", "An error occurred when trimming non-fatal files.", e4);
        }
    }

    private void writeBeginSession(String str, Date date) throws Exception {
        ClsFileOutputStream clsFileOutputStream;
        CodedOutputStream newInstance;
        CodedOutputStream codedOutputStream = null;
        try {
            File filesDir = getFilesDir();
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append("BeginSession");
            clsFileOutputStream = new ClsFileOutputStream(filesDir, sb.toString());
            try {
                newInstance = CodedOutputStream.newInstance(clsFileOutputStream);
            } catch (Throwable th) {
                th = th;
                CommonUtils.flushOrLog(codedOutputStream, "Failed to flush to session begin file.");
                CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close begin session file.");
                throw th;
            }
            try {
                SessionProtobufHelper.writeBeginSession(newInstance, str, String.format(Locale.US, "Crashlytics Android SDK/%s", new Object[]{this.crashlyticsCore.getVersion()}), date.getTime() / 1000);
                CommonUtils.flushOrLog(newInstance, "Failed to flush to session begin file.");
                CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close begin session file.");
            } catch (Throwable th2) {
                th = th2;
                codedOutputStream = newInstance;
                CommonUtils.flushOrLog(codedOutputStream, "Failed to flush to session begin file.");
                CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close begin session file.");
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            clsFileOutputStream = null;
            CommonUtils.flushOrLog(codedOutputStream, "Failed to flush to session begin file.");
            CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close begin session file.");
            throw th;
        }
    }

    private void writeSessionApp(String str) throws Exception {
        CodedOutputStream codedOutputStream;
        ClsFileOutputStream clsFileOutputStream;
        Throwable th;
        try {
            File filesDir = getFilesDir();
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append("SessionApp");
            clsFileOutputStream = new ClsFileOutputStream(filesDir, sb.toString());
            try {
                codedOutputStream = CodedOutputStream.newInstance(clsFileOutputStream);
            } catch (Throwable th2) {
                th = th2;
                codedOutputStream = null;
                CommonUtils.flushOrLog(codedOutputStream, "Failed to flush to session app file.");
                CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close session app file.");
                throw th;
            }
            try {
                SessionProtobufHelper.writeSessionApp(codedOutputStream, this.idManager.getAppIdentifier(), this.crashlyticsCore.getApiKey(), this.crashlyticsCore.getVersionCode(), this.crashlyticsCore.getVersionName(), this.idManager.getAppInstallIdentifier(), DeliveryMechanism.determineFrom(this.crashlyticsCore.getInstallerPackageName()).getId(), this.unityVersion);
                CommonUtils.flushOrLog(codedOutputStream, "Failed to flush to session app file.");
                CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close session app file.");
            } catch (Throwable th3) {
                th = th3;
                CommonUtils.flushOrLog(codedOutputStream, "Failed to flush to session app file.");
                CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close session app file.");
                throw th;
            }
        } catch (Throwable th4) {
            clsFileOutputStream = null;
            th = th4;
            codedOutputStream = null;
            CommonUtils.flushOrLog(codedOutputStream, "Failed to flush to session app file.");
            CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close session app file.");
            throw th;
        }
    }

    private void writeSessionOS(String str) throws Exception {
        ClsFileOutputStream clsFileOutputStream;
        CodedOutputStream newInstance;
        CodedOutputStream codedOutputStream = null;
        try {
            File filesDir = getFilesDir();
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append("SessionOS");
            clsFileOutputStream = new ClsFileOutputStream(filesDir, sb.toString());
            try {
                newInstance = CodedOutputStream.newInstance(clsFileOutputStream);
            } catch (Throwable th) {
                th = th;
                CommonUtils.flushOrLog(codedOutputStream, "Failed to flush to session OS file.");
                CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close session OS file.");
                throw th;
            }
            try {
                SessionProtobufHelper.writeSessionOS(newInstance, CommonUtils.isRooted(this.crashlyticsCore.getContext()));
                CommonUtils.flushOrLog(newInstance, "Failed to flush to session OS file.");
                CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close session OS file.");
            } catch (Throwable th2) {
                Throwable th3 = th2;
                codedOutputStream = newInstance;
                th = th3;
                CommonUtils.flushOrLog(codedOutputStream, "Failed to flush to session OS file.");
                CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close session OS file.");
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            clsFileOutputStream = null;
            CommonUtils.flushOrLog(codedOutputStream, "Failed to flush to session OS file.");
            CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close session OS file.");
            throw th;
        }
    }

    private void writeSessionDevice(String str) throws Exception {
        CodedOutputStream codedOutputStream;
        ClsFileOutputStream clsFileOutputStream;
        try {
            File filesDir = getFilesDir();
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append("SessionDevice");
            clsFileOutputStream = new ClsFileOutputStream(filesDir, sb.toString());
            try {
                codedOutputStream = CodedOutputStream.newInstance(clsFileOutputStream);
            } catch (Throwable th) {
                th = th;
                codedOutputStream = null;
                Throwable th2 = th;
                CommonUtils.flushOrLog(codedOutputStream, "Failed to flush session device info.");
                CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close session device file.");
                throw th2;
            }
            try {
                Context context = this.crashlyticsCore.getContext();
                StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
                CodedOutputStream codedOutputStream2 = codedOutputStream;
                SessionProtobufHelper.writeSessionDevice(codedOutputStream2, this.idManager.getDeviceUUID(), CommonUtils.getCpuArchitectureInt(), Build.MODEL, Runtime.getRuntime().availableProcessors(), CommonUtils.getTotalRamInBytes(), ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize()), CommonUtils.isEmulator(context), this.idManager.getDeviceIdentifiers(), CommonUtils.getDeviceState(context), Build.MANUFACTURER, Build.PRODUCT);
                CommonUtils.flushOrLog(codedOutputStream, "Failed to flush session device info.");
                CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close session device file.");
            } catch (Throwable th3) {
                th = th3;
                Throwable th22 = th;
                CommonUtils.flushOrLog(codedOutputStream, "Failed to flush session device info.");
                CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close session device file.");
                throw th22;
            }
        } catch (Throwable th4) {
            th = th4;
            clsFileOutputStream = null;
            codedOutputStream = null;
            Throwable th222 = th;
            CommonUtils.flushOrLog(codedOutputStream, "Failed to flush session device info.");
            CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close session device file.");
            throw th222;
        }
    }

    private void writeSessionUser(String str) throws Exception {
        ClsFileOutputStream clsFileOutputStream;
        CodedOutputStream newInstance;
        CodedOutputStream codedOutputStream = null;
        try {
            File filesDir = getFilesDir();
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append("SessionUser");
            clsFileOutputStream = new ClsFileOutputStream(filesDir, sb.toString());
            try {
                newInstance = CodedOutputStream.newInstance(clsFileOutputStream);
            } catch (Throwable th) {
                th = th;
                CommonUtils.flushOrLog(codedOutputStream, "Failed to flush session user file.");
                CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close session user file.");
                throw th;
            }
            try {
                UserMetaData userMetaData = getUserMetaData(str);
                if (userMetaData.isEmpty()) {
                    CommonUtils.flushOrLog(newInstance, "Failed to flush session user file.");
                    CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close session user file.");
                    return;
                }
                SessionProtobufHelper.writeSessionUser(newInstance, userMetaData.id, userMetaData.name, userMetaData.email);
                CommonUtils.flushOrLog(newInstance, "Failed to flush session user file.");
                CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close session user file.");
            } catch (Throwable th2) {
                th = th2;
                codedOutputStream = newInstance;
                CommonUtils.flushOrLog(codedOutputStream, "Failed to flush session user file.");
                CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close session user file.");
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            clsFileOutputStream = null;
            CommonUtils.flushOrLog(codedOutputStream, "Failed to flush session user file.");
            CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close session user file.");
            throw th;
        }
    }

    private void writeSessionEvent(CodedOutputStream codedOutputStream, Date date, Thread thread, Throwable th, String str, boolean z) throws Exception {
        Thread[] threadArr;
        Map map;
        Map attributes;
        Context context = this.crashlyticsCore.getContext();
        long time = date.getTime() / 1000;
        float batteryLevel = CommonUtils.getBatteryLevel(context);
        int batteryVelocity = CommonUtils.getBatteryVelocity(context, this.devicePowerStateListener.isPowerConnected());
        boolean proximitySensorEnabled = CommonUtils.getProximitySensorEnabled(context);
        int i = context.getResources().getConfiguration().orientation;
        long totalRamInBytes = CommonUtils.getTotalRamInBytes() - CommonUtils.calculateFreeRamInBytes(context);
        long calculateUsedDiskSpaceInBytes = CommonUtils.calculateUsedDiskSpaceInBytes(Environment.getDataDirectory().getPath());
        RunningAppProcessInfo appProcessInfo = CommonUtils.getAppProcessInfo(context.getPackageName(), context);
        LinkedList linkedList = new LinkedList();
        StackTraceElement[] stackTrace = th.getStackTrace();
        String buildId = this.crashlyticsCore.getBuildId();
        String appIdentifier = this.idManager.getAppIdentifier();
        int i2 = 0;
        if (z) {
            Map allStackTraces = Thread.getAllStackTraces();
            Thread[] threadArr2 = new Thread[allStackTraces.size()];
            for (Entry entry : allStackTraces.entrySet()) {
                threadArr2[i2] = (Thread) entry.getKey();
                linkedList.add(entry.getValue());
                i2++;
            }
            threadArr = threadArr2;
        } else {
            threadArr = new Thread[0];
        }
        if (!CommonUtils.getBooleanResourceValue(context, "com.crashlytics.CollectCustomKeys", true)) {
            attributes = new TreeMap();
        } else {
            attributes = this.crashlyticsCore.getAttributes();
            if (attributes != null && attributes.size() > 1) {
                map = new TreeMap(attributes);
                SessionProtobufHelper.writeSessionEvent(codedOutputStream, time, str, th, thread, stackTrace, threadArr, linkedList, map, this.logFileManager, appProcessInfo, i, appIdentifier, buildId, batteryLevel, batteryVelocity, proximitySensorEnabled, totalRamInBytes, calculateUsedDiskSpaceInBytes);
            }
        }
        map = attributes;
        SessionProtobufHelper.writeSessionEvent(codedOutputStream, time, str, th, thread, stackTrace, threadArr, linkedList, map, this.logFileManager, appProcessInfo, i, appIdentifier, buildId, batteryLevel, batteryVelocity, proximitySensorEnabled, totalRamInBytes, calculateUsedDiskSpaceInBytes);
    }

    private void writeSessionPartsToSessionFile(File file, String str, int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("Collecting session parts for ID ");
        sb.append(str);
        Fabric.getLogger().d("CrashlyticsCore", sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str);
        sb2.append("SessionCrash");
        File[] listFilesMatching = listFilesMatching(new FileNameContainsFilter(sb2.toString()));
        boolean z = listFilesMatching != null && listFilesMatching.length > 0;
        Fabric.getLogger().d("CrashlyticsCore", String.format(Locale.US, "Session %s has fatal exception: %s", new Object[]{str, Boolean.valueOf(z)}));
        StringBuilder sb3 = new StringBuilder();
        sb3.append(str);
        sb3.append("SessionEvent");
        File[] listFilesMatching2 = listFilesMatching(new FileNameContainsFilter(sb3.toString()));
        boolean z2 = listFilesMatching2 != null && listFilesMatching2.length > 0;
        Fabric.getLogger().d("CrashlyticsCore", String.format(Locale.US, "Session %s has non-fatal exceptions: %s", new Object[]{str, Boolean.valueOf(z2)}));
        if (z || z2) {
            synthesizeSessionFile(file, str, getTrimmedNonFatalFiles(str, listFilesMatching2, i), z ? listFilesMatching[0] : null);
        } else {
            StringBuilder sb4 = new StringBuilder();
            sb4.append("No events present for session ID ");
            sb4.append(str);
            Fabric.getLogger().d("CrashlyticsCore", sb4.toString());
        }
        StringBuilder sb5 = new StringBuilder();
        sb5.append("Removing session part files for ID ");
        sb5.append(str);
        Fabric.getLogger().d("CrashlyticsCore", sb5.toString());
        deleteSessionPartFilesFor(str);
    }

    private void synthesizeSessionFile(File file, String str, File[] fileArr, File file2) {
        CodedOutputStream codedOutputStream;
        ClsFileOutputStream clsFileOutputStream;
        boolean z = file2 != null;
        CodedOutputStream codedOutputStream2 = null;
        try {
            clsFileOutputStream = new ClsFileOutputStream(getFilesDir(), str);
            try {
                codedOutputStream = CodedOutputStream.newInstance(clsFileOutputStream);
                try {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Collecting SessionStart data for session ID ");
                    sb.append(str);
                    Fabric.getLogger().d("CrashlyticsCore", sb.toString());
                    writeToCosFromFile(codedOutputStream, file);
                    codedOutputStream.writeUInt64(4, new Date().getTime() / 1000);
                    codedOutputStream.writeBool(5, z);
                    codedOutputStream.writeUInt32(11, 1);
                    codedOutputStream.writeEnum(12, 3);
                    writeInitialPartsTo(codedOutputStream, str);
                    writeNonFatalEventsTo(codedOutputStream, fileArr, str);
                    if (z) {
                        writeToCosFromFile(codedOutputStream, file2);
                    }
                    CommonUtils.flushOrLog(codedOutputStream, "Error flushing session file stream");
                    CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close CLS file");
                } catch (Exception e) {
                    e = e;
                    codedOutputStream2 = codedOutputStream;
                    try {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Failed to write session file for session ID: ");
                        sb2.append(str);
                        Fabric.getLogger().e("CrashlyticsCore", sb2.toString(), e);
                        CommonUtils.flushOrLog(codedOutputStream2, "Error flushing session file stream");
                        closeWithoutRenamingOrLog(clsFileOutputStream);
                    } catch (Throwable th) {
                        th = th;
                        codedOutputStream = codedOutputStream2;
                        CommonUtils.flushOrLog(codedOutputStream, "Error flushing session file stream");
                        CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close CLS file");
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    CommonUtils.flushOrLog(codedOutputStream, "Error flushing session file stream");
                    CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close CLS file");
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
                StringBuilder sb22 = new StringBuilder();
                sb22.append("Failed to write session file for session ID: ");
                sb22.append(str);
                Fabric.getLogger().e("CrashlyticsCore", sb22.toString(), e);
                CommonUtils.flushOrLog(codedOutputStream2, "Error flushing session file stream");
                closeWithoutRenamingOrLog(clsFileOutputStream);
            }
        } catch (Exception e3) {
            e = e3;
            clsFileOutputStream = null;
            StringBuilder sb222 = new StringBuilder();
            sb222.append("Failed to write session file for session ID: ");
            sb222.append(str);
            Fabric.getLogger().e("CrashlyticsCore", sb222.toString(), e);
            CommonUtils.flushOrLog(codedOutputStream2, "Error flushing session file stream");
            closeWithoutRenamingOrLog(clsFileOutputStream);
        } catch (Throwable th3) {
            th = th3;
            clsFileOutputStream = null;
            codedOutputStream = null;
            CommonUtils.flushOrLog(codedOutputStream, "Error flushing session file stream");
            CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close CLS file");
            throw th;
        }
    }

    private static void writeNonFatalEventsTo(CodedOutputStream codedOutputStream, File[] fileArr, String str) {
        Arrays.sort(fileArr, CommonUtils.FILE_MODIFIED_COMPARATOR);
        for (File file : fileArr) {
            try {
                Fabric.getLogger().d("CrashlyticsCore", String.format(Locale.US, "Found Non Fatal for session ID %s in %s ", new Object[]{str, file.getName()}));
                writeToCosFromFile(codedOutputStream, file);
            } catch (Exception e) {
                Fabric.getLogger().e("CrashlyticsCore", "Error writting non-fatal to session.", e);
            }
        }
    }

    private void writeInitialPartsTo(CodedOutputStream codedOutputStream, String str) throws IOException {
        String[] strArr;
        for (String str2 : INITIAL_SESSION_PART_TAGS) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(str2);
            File[] listFilesMatching = listFilesMatching(new FileNameContainsFilter(sb.toString()));
            if (listFilesMatching.length == 0) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Can't find ");
                sb2.append(str2);
                sb2.append(" data for session ID ");
                sb2.append(str);
                Fabric.getLogger().e("CrashlyticsCore", sb2.toString(), null);
            } else {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Collecting ");
                sb3.append(str2);
                sb3.append(" data for session ID ");
                sb3.append(str);
                Fabric.getLogger().d("CrashlyticsCore", sb3.toString());
                writeToCosFromFile(codedOutputStream, listFilesMatching[0]);
            }
        }
    }

    private static void writeToCosFromFile(CodedOutputStream codedOutputStream, File file) throws IOException {
        FileInputStream fileInputStream;
        if (!file.exists()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Tried to include a file that doesn't exist: ");
            sb.append(file.getName());
            Fabric.getLogger().e("CrashlyticsCore", sb.toString(), null);
            return;
        }
        try {
            fileInputStream = new FileInputStream(file);
            try {
                copyToCodedOutputStream(fileInputStream, codedOutputStream, (int) file.length());
                CommonUtils.closeOrLog(fileInputStream, "Failed to close file input stream.");
            } catch (Throwable th) {
                th = th;
                CommonUtils.closeOrLog(fileInputStream, "Failed to close file input stream.");
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            fileInputStream = null;
            CommonUtils.closeOrLog(fileInputStream, "Failed to close file input stream.");
            throw th;
        }
    }

    private static void copyToCodedOutputStream(InputStream inputStream, CodedOutputStream codedOutputStream, int i) throws IOException {
        byte[] bArr = new byte[i];
        int i2 = 0;
        while (i2 < bArr.length) {
            int read = inputStream.read(bArr, i2, bArr.length - i2);
            if (read < 0) {
                break;
            }
            i2 += read;
        }
        codedOutputStream.writeRawBytes(bArr);
    }

    private UserMetaData getUserMetaData(String str) {
        return isHandlingException() ? new UserMetaData(this.crashlyticsCore.getUserIdentifier(), this.crashlyticsCore.getUserName(), this.crashlyticsCore.getUserEmail()) : new MetaDataStore(getFilesDir()).readUserData(str);
    }

    private void sendSessionReports() {
        for (File sendSessionRunnable : listCompleteSessionFiles()) {
            this.executorServiceWrapper.executeAsync((Runnable) new SendSessionRunnable(this.crashlyticsCore, sendSessionRunnable));
        }
    }

    /* access modifiers changed from: private */
    public File getFilesDir() {
        return this.fileStore.getFilesDir();
    }
}
