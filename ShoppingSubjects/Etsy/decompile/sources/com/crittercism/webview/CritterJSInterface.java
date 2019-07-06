package com.crittercism.webview;

import android.webkit.JavascriptInterface;
import com.crittercism.internal.am;
import com.crittercism.internal.at;
import com.crittercism.internal.bm;
import com.crittercism.internal.bn;
import com.crittercism.internal.bp;
import com.crittercism.internal.cm;
import com.etsy.android.lib.convos.Draft;
import com.etsy.android.lib.core.http.request.BaseHttpRequest;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.requests.FavoriteListingsRequest;
import com.etsy.android.uikit.adapter.AbstractContextRecyclerViewAdapter;
import com.etsy.android.uikit.adapter.EndlessRecyclerViewAdapter;
import com.etsy.android.uikit.adapter.ReviewAdapter;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import org.json.JSONException;
import org.json.JSONObject;

public class CritterJSInterface {
    private am a;

    public CritterJSInterface(am amVar) {
        if (amVar == null) {
            a("CritterJSInterface");
        }
        this.a = amVar;
    }

    private static void a(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(CritterJSInterface.class.getName());
        sb.append(".");
        sb.append(str);
        sb.append("() badly initialized: null instance.");
        cm.b(sb.toString(), new NullPointerException());
    }

    private static void a(String str, String str2, long j) {
        StringBuilder sb = new StringBuilder("negative long integer: ");
        sb.append(j);
        b(str, str2, sb.toString());
    }

    private static void b(String str, String str2, String str3) {
        String str4;
        if (str2 == null || str2.length() <= 0) {
            str4 = "";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(str2);
            sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            str4 = sb.toString();
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(CritterJSInterface.class.getName());
        sb2.append(".");
        sb2.append(str);
        sb2.append("() given invalid ");
        sb2.append(str4);
        sb2.append("parameter: ");
        sb2.append(str3);
        sb2.append(". Request is being ignored.");
        cm.b(sb2.toString(), new IllegalArgumentException());
    }

    @JavascriptInterface
    public void logError(String str, String str2) {
        String str3;
        try {
            if (this.a == null) {
                a("logError");
                return;
            }
            if (str != null) {
                if (str.length() != 0) {
                    if (str2 != null) {
                        if (str2.length() != 0) {
                            String str4 = "";
                            String str5 = "";
                            String[] split = str.split(Draft.IMAGE_DELIMITER, 2);
                            if (split.length > 0) {
                                if (split[0].indexOf("Uncaught ") < 0) {
                                    str3 = split[0];
                                } else {
                                    str3 = split[0].substring(9);
                                }
                                str4 = str3.trim();
                            }
                            if (split.length > 1) {
                                str5 = split[1].trim();
                            }
                            this.a.a((Throwable) new bp(str4, str5, str2, false));
                        }
                    }
                }
            }
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            cm.b(th);
        }
    }

    @JavascriptInterface
    public void logHandledException(String str, String str2, String str3) {
        try {
            if (this.a == null) {
                a("logHandledException");
                return;
            }
            if (a(str, "logHandledException", ResponseConstants.NAME) && a(str2, "logHandledException", ResponseConstants.REASON) && a(str3, "logHandledException", "stack")) {
                this.a.a((Throwable) new bp(str, str2, str3, true));
            }
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            cm.b(th);
        }
    }

    @JavascriptInterface
    public void logUnhandledException(String str, String str2, String str3) {
        try {
            if (this.a == null) {
                a("logUnhandledException");
                return;
            }
            if (a(str, "logUnhandledException", ResponseConstants.NAME) && a(str2, "logUnhandledException", ResponseConstants.REASON) && a(str3, "logUnhandledException", "stack")) {
                this.a.a((Throwable) new bp(str, str2, str3, false));
            }
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            cm.b(th);
        }
    }

    @JavascriptInterface
    public void leaveBreadcrumb(String str) {
        try {
            if (this.a == null) {
                a("leaveBreadcrumb");
                return;
            }
            if (a(str, "leaveBreadcrumb", "breadcrumb")) {
                this.a.a(at.a(str));
            }
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            cm.b(th);
        }
    }

    @JavascriptInterface
    public void beginTransaction(String str) {
        try {
            if (this.a == null) {
                a("beginTransaction");
                return;
            }
            if (a(str, "beginTransaction", ResponseConstants.NAME)) {
                this.a.a(str);
            }
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            cm.b(th);
        }
    }

    @JavascriptInterface
    public void endTransaction(String str) {
        try {
            if (this.a == null) {
                a("endTransaction");
                return;
            }
            if (a(str, "endTransaction", ResponseConstants.NAME)) {
                this.a.b(str);
            }
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            cm.b(th);
        }
    }

    @JavascriptInterface
    public void failTransaction(String str) {
        try {
            if (this.a == null) {
                a("failTransaction");
                return;
            }
            if (a(str, "failTransaction", ResponseConstants.NAME)) {
                this.a.c(str);
            }
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            cm.b(th);
        }
    }

    @JavascriptInterface
    public void cancelTransaction(String str) {
        try {
            if (this.a == null) {
                a("cancelTransaction");
                return;
            }
            if (a(str, "cancelTransaction", ResponseConstants.NAME)) {
                this.a.d(str);
            }
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            cm.b(th);
        }
    }

    @JavascriptInterface
    public void setTransactionValue(String str, int i) {
        try {
            if (this.a == null) {
                a("setTransactionValue");
                return;
            }
            if (a(str, "setTransactionValue", "transactionName")) {
                this.a.a(str, i);
            }
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            cm.b(th);
        }
    }

    @JavascriptInterface
    public int getTransactionValue(String str) {
        try {
            if (this.a == null) {
                a("getTransactionValue");
                return -1;
            } else if (!a(str, "getTransactionValue", "transactionName")) {
                return -1;
            } else {
                return this.a.e(str);
            }
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            cm.b(th);
            return -1;
        }
    }

    @JavascriptInterface
    public void setMetadata(String str) {
        try {
            if (this.a == null) {
                a("setMetadata");
                return;
            }
            if (a(str, "setMetadata", "metadataJson")) {
                try {
                    this.a.a(new JSONObject(str));
                } catch (JSONException unused) {
                    StringBuilder sb = new StringBuilder("badly formatted json string. ");
                    sb.append(str);
                    b("setMetadata", "", sb.toString());
                }
            }
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            cm.b(th);
        }
    }

    @JavascriptInterface
    public void setUsername(String str) {
        try {
            if (this.a == null) {
                a("setUsername");
                return;
            }
            if (a(str, "setUsername", ResponseConstants.USERNAME)) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.putOpt(ResponseConstants.USERNAME, str);
                    this.a.a(jSONObject);
                } catch (JSONException e) {
                    cm.b("Crittercism.setUsername()", e);
                }
            }
        } catch (ThreadDeath e2) {
            throw e2;
        } catch (Throwable th) {
            cm.b(th);
        }
    }

    @JavascriptInterface
    public void logNetworkRequest(String str, String str2, long j, long j2, long j3, int i, int i2) {
        boolean z;
        String str3 = str;
        long j4 = j2;
        long j5 = j3;
        int i3 = i;
        try {
            if (this.a == null) {
                a("logNetworkRequest");
                return;
            }
            boolean z2 = false;
            if (a(str3, "logNetworkRequest", "httpMethod")) {
                String[] strArr = {BaseHttpRequest.GET, BaseHttpRequest.HEAD, BaseHttpRequest.POST, BaseHttpRequest.PUT, BaseHttpRequest.DELETE, BaseHttpRequest.TRACE, "CONNECT", BaseHttpRequest.OPTIONS, "PATCH"};
                for (int i4 = 0; i4 < 9; i4++) {
                    if (strArr[i4].equals(str3)) {
                        z = true;
                        break;
                    }
                }
                b("logNetworkRequest", "httpMethod", str3);
            }
            z = false;
            if (z) {
                String str4 = str2;
                if (a(str4, "logNetworkRequest", "url")) {
                    if (j4 < 0) {
                        a("logNetworkRequest", "bytesRead", j4);
                    } else if (j5 < 0) {
                        a("logNetworkRequest", "bytesSent", j5);
                    } else {
                        if (i3 >= 0) {
                            int[] iArr = {0, 100, 101, 200, 201, 202, 203, 204, 205, 206, 300, 301, 302, 303, 304, 305, 306, 307, 400, 401, 402, FavoriteListingsRequest.PRIVATE_ERROR_CODE, 404, 405, 406, 407, 408, 409, 410, 411, 412, 413, 414, 415, 416, 417, AbstractContextRecyclerViewAdapter.VIEW_TYPE_HEADER, AbstractContextRecyclerViewAdapter.VIEW_TYPE_FOOTER, EndlessRecyclerViewAdapter.VIEW_TYPE_ENDLESS_ERROR, EndlessRecyclerViewAdapter.VIEW_TYPE_ENDLESS_LOADING, 504, ReviewAdapter.CARD_WITH_APP_PHOTO};
                            int i5 = 0;
                            while (true) {
                                if (i5 >= 42) {
                                    StringBuilder sb = new StringBuilder("the given HTTP response is not allowed: ");
                                    sb.append(i3);
                                    b("logNetworkRequest", "responseCode", sb.toString());
                                    break;
                                } else if (iArr[i5] == i3) {
                                    z2 = true;
                                    break;
                                } else {
                                    i5++;
                                }
                            }
                        } else {
                            StringBuilder sb2 = new StringBuilder("negative integer: ");
                            sb2.append(i3);
                            b("logNetworkRequest", "responseCode", sb2.toString());
                        }
                        if (z2) {
                            this.a.a(str3, str4, j, j4, j5, i3, new bm(bn.e - 1, i2));
                        }
                    }
                }
            }
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            cm.b(th);
        }
    }

    private static boolean a(String str, String str2, String str3) {
        String str4;
        if (str == null) {
            if (str3.length() > 0) {
                StringBuilder sb = new StringBuilder();
                sb.append(str3);
                sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                str4 = sb.toString();
            } else {
                str4 = "";
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append(CritterJSInterface.class.getName());
            sb2.append(".");
            sb2.append(str2);
            sb2.append("() given invalid ");
            sb2.append(str4);
            sb2.append("parameter: null string or invalid javascript type. Request is being ignored...");
            cm.b(sb2.toString(), new NullPointerException());
            return false;
        } else if (str.length() != 0) {
            return true;
        } else {
            b(str2, str3, "empty string");
            return false;
        }
    }
}
