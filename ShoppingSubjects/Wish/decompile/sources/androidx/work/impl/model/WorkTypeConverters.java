package androidx.work.impl.model;

import androidx.work.BackoffPolicy;
import androidx.work.NetworkType;
import androidx.work.State;

public class WorkTypeConverters {
    public static int stateToInt(State state) {
        switch (state) {
            case ENQUEUED:
                return 0;
            case RUNNING:
                return 1;
            case SUCCEEDED:
                return 2;
            case FAILED:
                return 3;
            case BLOCKED:
                return 4;
            case CANCELLED:
                return 5;
            default:
                StringBuilder sb = new StringBuilder();
                sb.append("Could not convert ");
                sb.append(state);
                sb.append(" to int");
                throw new IllegalArgumentException(sb.toString());
        }
    }

    public static State intToState(int i) {
        switch (i) {
            case 0:
                return State.ENQUEUED;
            case 1:
                return State.RUNNING;
            case 2:
                return State.SUCCEEDED;
            case 3:
                return State.FAILED;
            case 4:
                return State.BLOCKED;
            case 5:
                return State.CANCELLED;
            default:
                StringBuilder sb = new StringBuilder();
                sb.append("Could not convert ");
                sb.append(i);
                sb.append(" to State");
                throw new IllegalArgumentException(sb.toString());
        }
    }

    public static int backoffPolicyToInt(BackoffPolicy backoffPolicy) {
        switch (backoffPolicy) {
            case EXPONENTIAL:
                return 0;
            case LINEAR:
                return 1;
            default:
                StringBuilder sb = new StringBuilder();
                sb.append("Could not convert ");
                sb.append(backoffPolicy);
                sb.append(" to int");
                throw new IllegalArgumentException(sb.toString());
        }
    }

    public static BackoffPolicy intToBackoffPolicy(int i) {
        switch (i) {
            case 0:
                return BackoffPolicy.EXPONENTIAL;
            case 1:
                return BackoffPolicy.LINEAR;
            default:
                StringBuilder sb = new StringBuilder();
                sb.append("Could not convert ");
                sb.append(i);
                sb.append(" to BackoffPolicy");
                throw new IllegalArgumentException(sb.toString());
        }
    }

    public static int networkTypeToInt(NetworkType networkType) {
        switch (networkType) {
            case NOT_REQUIRED:
                return 0;
            case CONNECTED:
                return 1;
            case UNMETERED:
                return 2;
            case NOT_ROAMING:
                return 3;
            case METERED:
                return 4;
            default:
                StringBuilder sb = new StringBuilder();
                sb.append("Could not convert ");
                sb.append(networkType);
                sb.append(" to int");
                throw new IllegalArgumentException(sb.toString());
        }
    }

    public static NetworkType intToNetworkType(int i) {
        switch (i) {
            case 0:
                return NetworkType.NOT_REQUIRED;
            case 1:
                return NetworkType.CONNECTED;
            case 2:
                return NetworkType.UNMETERED;
            case 3:
                return NetworkType.NOT_ROAMING;
            case 4:
                return NetworkType.METERED;
            default:
                StringBuilder sb = new StringBuilder();
                sb.append("Could not convert ");
                sb.append(i);
                sb.append(" to NetworkType");
                throw new IllegalArgumentException(sb.toString());
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0058 A[SYNTHETIC, Splitter:B:27:0x0058] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x006f A[SYNTHETIC, Splitter:B:38:0x006f] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:31:0x0060=Splitter:B:31:0x0060, B:16:0x0046=Splitter:B:16:0x0046} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] contentUriTriggersToByteArray(androidx.work.ContentUriTriggers r4) {
        /*
            int r0 = r4.size()
            r1 = 0
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            r0.<init>()
            java.io.ObjectOutputStream r2 = new java.io.ObjectOutputStream     // Catch:{ IOException -> 0x0052 }
            r2.<init>(r0)     // Catch:{ IOException -> 0x0052 }
            int r1 = r4.size()     // Catch:{ IOException -> 0x004c, all -> 0x004a }
            r2.writeInt(r1)     // Catch:{ IOException -> 0x004c, all -> 0x004a }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ IOException -> 0x004c, all -> 0x004a }
        L_0x001d:
            boolean r1 = r4.hasNext()     // Catch:{ IOException -> 0x004c, all -> 0x004a }
            if (r1 == 0) goto L_0x003c
            java.lang.Object r1 = r4.next()     // Catch:{ IOException -> 0x004c, all -> 0x004a }
            androidx.work.ContentUriTriggers$Trigger r1 = (androidx.work.ContentUriTriggers.Trigger) r1     // Catch:{ IOException -> 0x004c, all -> 0x004a }
            android.net.Uri r3 = r1.getUri()     // Catch:{ IOException -> 0x004c, all -> 0x004a }
            java.lang.String r3 = r3.toString()     // Catch:{ IOException -> 0x004c, all -> 0x004a }
            r2.writeUTF(r3)     // Catch:{ IOException -> 0x004c, all -> 0x004a }
            boolean r1 = r1.shouldTriggerForDescendants()     // Catch:{ IOException -> 0x004c, all -> 0x004a }
            r2.writeBoolean(r1)     // Catch:{ IOException -> 0x004c, all -> 0x004a }
            goto L_0x001d
        L_0x003c:
            if (r2 == 0) goto L_0x0046
            r2.close()     // Catch:{ IOException -> 0x0042 }
            goto L_0x0046
        L_0x0042:
            r4 = move-exception
            r4.printStackTrace()
        L_0x0046:
            r0.close()     // Catch:{ IOException -> 0x0064 }
            goto L_0x0068
        L_0x004a:
            r4 = move-exception
            goto L_0x006d
        L_0x004c:
            r4 = move-exception
            r1 = r2
            goto L_0x0053
        L_0x004f:
            r4 = move-exception
            r2 = r1
            goto L_0x006d
        L_0x0052:
            r4 = move-exception
        L_0x0053:
            r4.printStackTrace()     // Catch:{ all -> 0x004f }
            if (r1 == 0) goto L_0x0060
            r1.close()     // Catch:{ IOException -> 0x005c }
            goto L_0x0060
        L_0x005c:
            r4 = move-exception
            r4.printStackTrace()
        L_0x0060:
            r0.close()     // Catch:{ IOException -> 0x0064 }
            goto L_0x0068
        L_0x0064:
            r4 = move-exception
            r4.printStackTrace()
        L_0x0068:
            byte[] r4 = r0.toByteArray()
            return r4
        L_0x006d:
            if (r2 == 0) goto L_0x0077
            r2.close()     // Catch:{ IOException -> 0x0073 }
            goto L_0x0077
        L_0x0073:
            r1 = move-exception
            r1.printStackTrace()
        L_0x0077:
            r0.close()     // Catch:{ IOException -> 0x007b }
            goto L_0x007f
        L_0x007b:
            r0 = move-exception
            r0.printStackTrace()
        L_0x007f:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.model.WorkTypeConverters.contentUriTriggersToByteArray(androidx.work.ContentUriTriggers):byte[]");
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0048 A[SYNTHETIC, Splitter:B:26:0x0048] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x005c A[SYNTHETIC, Splitter:B:37:0x005c] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:30:0x0050=Splitter:B:30:0x0050, B:16:0x0035=Splitter:B:16:0x0035} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static androidx.work.ContentUriTriggers byteArrayToContentUriTriggers(byte[] r6) {
        /*
            androidx.work.ContentUriTriggers r0 = new androidx.work.ContentUriTriggers
            r0.<init>()
            if (r6 != 0) goto L_0x0008
            return r0
        L_0x0008:
            java.io.ByteArrayInputStream r1 = new java.io.ByteArrayInputStream
            r1.<init>(r6)
            r6 = 0
            java.io.ObjectInputStream r2 = new java.io.ObjectInputStream     // Catch:{ IOException -> 0x003f, all -> 0x003b }
            r2.<init>(r1)     // Catch:{ IOException -> 0x003f, all -> 0x003b }
            int r6 = r2.readInt()     // Catch:{ IOException -> 0x0039 }
        L_0x0017:
            if (r6 <= 0) goto L_0x002b
            java.lang.String r3 = r2.readUTF()     // Catch:{ IOException -> 0x0039 }
            android.net.Uri r3 = android.net.Uri.parse(r3)     // Catch:{ IOException -> 0x0039 }
            boolean r4 = r2.readBoolean()     // Catch:{ IOException -> 0x0039 }
            r0.add(r3, r4)     // Catch:{ IOException -> 0x0039 }
            int r6 = r6 + -1
            goto L_0x0017
        L_0x002b:
            if (r2 == 0) goto L_0x0035
            r2.close()     // Catch:{ IOException -> 0x0031 }
            goto L_0x0035
        L_0x0031:
            r6 = move-exception
            r6.printStackTrace()
        L_0x0035:
            r1.close()     // Catch:{ IOException -> 0x0054 }
            goto L_0x0058
        L_0x0039:
            r6 = move-exception
            goto L_0x0043
        L_0x003b:
            r0 = move-exception
            r2 = r6
            r6 = r0
            goto L_0x005a
        L_0x003f:
            r2 = move-exception
            r5 = r2
            r2 = r6
            r6 = r5
        L_0x0043:
            r6.printStackTrace()     // Catch:{ all -> 0x0059 }
            if (r2 == 0) goto L_0x0050
            r2.close()     // Catch:{ IOException -> 0x004c }
            goto L_0x0050
        L_0x004c:
            r6 = move-exception
            r6.printStackTrace()
        L_0x0050:
            r1.close()     // Catch:{ IOException -> 0x0054 }
            goto L_0x0058
        L_0x0054:
            r6 = move-exception
            r6.printStackTrace()
        L_0x0058:
            return r0
        L_0x0059:
            r6 = move-exception
        L_0x005a:
            if (r2 == 0) goto L_0x0064
            r2.close()     // Catch:{ IOException -> 0x0060 }
            goto L_0x0064
        L_0x0060:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0064:
            r1.close()     // Catch:{ IOException -> 0x0068 }
            goto L_0x006c
        L_0x0068:
            r0 = move-exception
            r0.printStackTrace()
        L_0x006c:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.model.WorkTypeConverters.byteArrayToContentUriTriggers(byte[]):androidx.work.ContentUriTriggers");
    }
}
