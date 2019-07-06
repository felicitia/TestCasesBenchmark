package com.crashlytics.android.core;

import android.content.Context;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.persistence.FileStore;
import java.io.File;
import java.util.Set;

class LogFileManager {
    private static final NoopLogStore NOOP_LOG_STORE = new NoopLogStore();
    private final Context context;
    private FileLogStore currentLog;
    private final FileStore fileStore;

    private static final class NoopLogStore implements FileLogStore {
        public void closeLogFile() {
        }

        public void deleteLogFile() {
        }

        public ByteString getLogAsByteString() {
            return null;
        }

        public void writeToLog(long j, String str) {
        }

        private NoopLogStore() {
        }
    }

    public LogFileManager(Context context2, FileStore fileStore2) {
        this(context2, fileStore2, null);
    }

    public LogFileManager(Context context2, FileStore fileStore2, String str) {
        this.context = context2;
        this.fileStore = fileStore2;
        this.currentLog = NOOP_LOG_STORE;
        setCurrentSession(str);
    }

    public final void setCurrentSession(String str) {
        this.currentLog.closeLogFile();
        this.currentLog = NOOP_LOG_STORE;
        if (str != null) {
            if (!isLoggingEnabled()) {
                Fabric.getLogger().d("CrashlyticsCore", "Preferences requested no custom logs. Aborting log file creation.");
            } else {
                setLogFile(getWorkingFileForSession(str), 65536);
            }
        }
    }

    public void writeToLog(long j, String str) {
        this.currentLog.writeToLog(j, str);
    }

    public ByteString getByteStringForLog() {
        return this.currentLog.getLogAsByteString();
    }

    public void clearLog() {
        this.currentLog.deleteLogFile();
    }

    public void discardOldLogFiles(Set<String> set) {
        File[] listFiles = getLogFileDir().listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                if (!set.contains(getSessionIdForFile(file))) {
                    file.delete();
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void setLogFile(File file, int i) {
        this.currentLog = new QueueFileLogStore(file, i);
    }

    private File getWorkingFileForSession(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("crashlytics-userlog-");
        sb.append(str);
        sb.append(".temp");
        return new File(getLogFileDir(), sb.toString());
    }

    private String getSessionIdForFile(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".temp");
        if (lastIndexOf == -1) {
            return name;
        }
        return name.substring("crashlytics-userlog-".length(), lastIndexOf);
    }

    private boolean isLoggingEnabled() {
        return CommonUtils.getBooleanResourceValue(this.context, "com.crashlytics.CollectCustomLogs", true);
    }

    private File getLogFileDir() {
        File file = new File(this.fileStore.getFilesDir(), "log-files");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }
}
