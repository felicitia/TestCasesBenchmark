package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@bu
public final class kb implements js {
    @Nullable
    private final String a;

    public kb() {
        this(null);
    }

    public kb(@Nullable String str) {
        this.a = str;
    }

    @WorkerThread
    public final void a(String str) {
        StringBuilder sb;
        String message;
        HttpURLConnection httpURLConnection;
        String str2 = "Pinging URL: ";
        try {
            String valueOf = String.valueOf(str);
            ka.b(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            ajh.a();
            jp.a(true, httpURLConnection, this.a);
            jt jtVar = new jt();
            jtVar.a(httpURLConnection, (byte[]) null);
            int responseCode = httpURLConnection.getResponseCode();
            jtVar.a(httpURLConnection, responseCode);
            if (responseCode < 200 || responseCode >= 300) {
                StringBuilder sb2 = new StringBuilder(65 + String.valueOf(str).length());
                sb2.append("Received non-success response code ");
                sb2.append(responseCode);
                sb2.append(" from pinging URL: ");
                sb2.append(str);
                ka.e(sb2.toString());
            }
            httpURLConnection.disconnect();
        } catch (IndexOutOfBoundsException e) {
            String message2 = e.getMessage();
            sb = new StringBuilder(32 + String.valueOf(str).length() + String.valueOf(message2).length());
            sb.append("Error while parsing ping URL: ");
            sb.append(str);
            sb.append(". ");
            sb.append(message2);
            ka.e(sb.toString());
        } catch (IOException e2) {
            message = e2.getMessage();
            sb = new StringBuilder(27 + String.valueOf(str).length() + String.valueOf(message).length());
            sb.append("Error while pinging URL: ");
            sb.append(str);
            sb.append(". ");
            sb.append(message);
            ka.e(sb.toString());
        } catch (RuntimeException e3) {
            message = e3.getMessage();
            sb = new StringBuilder(27 + String.valueOf(str).length() + String.valueOf(message).length());
            sb.append("Error while pinging URL: ");
            sb.append(str);
            sb.append(". ");
            sb.append(message);
            ka.e(sb.toString());
        } catch (Throwable th) {
            httpURLConnection.disconnect();
            throw th;
        }
    }
}
