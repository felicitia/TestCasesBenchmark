package com.google.android.gms.auth.api.signin.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResults;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.StatusPendingResult;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.http.entity.mime.MIME;

public final class c implements Runnable {
    private static final Logger a = new Logger("RevokeAccessOperation", new String[0]);
    private final String b;
    private final StatusPendingResult c = new StatusPendingResult((GoogleApiClient) null);

    private c(String str) {
        Preconditions.checkNotEmpty(str);
        this.b = str;
    }

    public static PendingResult<Status> a(String str) {
        if (str == null) {
            return PendingResults.immediateFailedResult(new Status(4), null);
        }
        c cVar = new c(str);
        new Thread(cVar).start();
        return cVar.c;
    }

    public final void run() {
        Logger logger;
        String str;
        String str2;
        String str3;
        Status status = Status.RESULT_INTERNAL_ERROR;
        try {
            String valueOf = String.valueOf("https://accounts.google.com/o/oauth2/revoke?token=");
            String valueOf2 = String.valueOf(this.b);
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf)).openConnection();
            httpURLConnection.setRequestProperty(MIME.CONTENT_TYPE, "application/x-www-form-urlencoded");
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == 200) {
                status = Status.RESULT_SUCCESS;
            } else {
                a.e("Unable to revoke access!", new Object[0]);
            }
            Logger logger2 = a;
            StringBuilder sb = new StringBuilder(26);
            sb.append("Response Code: ");
            sb.append(responseCode);
            logger2.d(sb.toString(), new Object[0]);
        } catch (IOException e) {
            logger = a;
            str2 = "IOException when revoking access: ";
            str3 = String.valueOf(e.toString());
            if (str3.length() == 0) {
                str = new String(str2);
                logger.e(str, new Object[0]);
                this.c.setResult(status);
            }
            str = str2.concat(str3);
            logger.e(str, new Object[0]);
            this.c.setResult(status);
        } catch (Exception e2) {
            logger = a;
            str2 = "Exception when revoking access: ";
            str3 = String.valueOf(e2.toString());
            if (str3.length() == 0) {
                str = new String(str2);
                logger.e(str, new Object[0]);
                this.c.setResult(status);
            }
            str = str2.concat(str3);
            logger.e(str, new Object[0]);
            this.c.setResult(status);
        }
        this.c.setResult(status);
    }
}
