package com.qualtrics.qsiframework;

import android.app.Fragment;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.qualtrics.qsiframework.c.C0158c;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONObject;

@Deprecated
public class QSIView extends WebView {
    private a _callbackInterface = null;
    private String mBrandId = "ally";
    private String mInterceptId;
    private boolean mPageLoading = false;
    /* access modifiers changed from: private */
    public boolean mPageReady = false;
    /* access modifiers changed from: private */
    public boolean mQSIReady = false;
    private String mZoneId;
    /* access modifiers changed from: private */
    public QSIEventListener qsiEvents;
    /* access modifiers changed from: private */
    public ArrayList<b> queuedVariables;
    /* access modifiers changed from: private */
    public Handler uiHandler = new Handler();

    public interface QSIEventListener {

        public enum Error {
            NETWORK_ERROR,
            UNAVAILABLE_INVALID,
            NO_CREATIVE
        }

        void a();

        void b();
    }

    protected class a {
        private a b = null;
        private a c = null;

        protected a() {
        }

        public void a(a aVar) {
            this.b = aVar;
        }

        public void b(a aVar) {
            this.c = aVar;
        }

        @JavascriptInterface
        public void onError(String str) {
            StringBuilder sb = new StringBuilder();
            sb.append("onError: ");
            sb.append(str);
            Log.i("QSI", sb.toString());
        }

        @JavascriptInterface
        public void onOpen(String str) {
            Log.i("QSI", "onOpen");
            QSIView.this.uiHandler.post(new Runnable() {
                public void run() {
                    QSIView.this.setClickable(true);
                    QSIView.this.setVisibility(0);
                    if (QSIView.this.qsiEvents != null) {
                        QSIView.this.qsiEvents.a();
                    }
                }
            });
            try {
                Log.i("QSI", str);
                JSONObject jSONObject = new JSONObject(str);
                if (this.c != null) {
                    this.c.a(jSONObject.getString("fullTargetUrl"));
                    QSIView.this.uiHandler.post(this.c);
                }
            } catch (Exception e) {
                Log.i("QSI", e.toString());
            }
        }

        @JavascriptInterface
        public void onClose() {
            Log.i("QSI", "onClose");
            QSIView.this.uiHandler.post(new Runnable() {
                public void run() {
                    QSIView.this.setVisibility(8);
                    if (QSIView.this.qsiEvents != null) {
                        QSIView.this.qsiEvents.b();
                    }
                }
            });
            if (this.b != null) {
                QSIView.this.uiHandler.post(this.b);
            }
        }

        @JavascriptInterface
        public void onQSIReady() {
            Log.i("QSI", "--onQSIReady()--");
            QSIView.this.uiHandler.post(new Runnable() {
                public void run() {
                    QSIView.this.mQSIReady = true;
                    if (QSIView.this.queuedVariables.size() > 0) {
                        QSIView.this.unload();
                        Iterator it = QSIView.this.queuedVariables.iterator();
                        while (it.hasNext()) {
                            b bVar = (b) it.next();
                            StringBuilder sb = new StringBuilder();
                            sb.append("putting queued variable: ");
                            sb.append(bVar.a.toString());
                            sb.append(" value: ");
                            sb.append(bVar.b.toString());
                            Log.i("QSI", sb.toString());
                            QSIView.this.put(bVar.a, bVar.b, bVar.c);
                        }
                        QSIView.this.queuedVariables.clear();
                        QSIView.this.load();
                    }
                }
            });
        }

        @JavascriptInterface
        public void onMessage(String str) {
            StringBuilder sb = new StringBuilder();
            sb.append("onMessage: ");
            sb.append(str);
            Log.i("QSI", sb.toString());
        }
    }

    private class b {
        public String a;
        public String b;
        public boolean c;

        public b(String str, String str2, boolean z) {
            this.a = str;
            this.b = str2;
            this.c = z;
        }
    }

    public QSIView(Context context, String str, String str2, String str3) {
        super(context);
        this.mInterceptId = str;
        this.mZoneId = str2;
        this.mBrandId = str3;
        init(null, 0);
    }

    public QSIView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet, 0);
    }

    public QSIView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet, i);
    }

    public void removeOnCloseCallback() {
        this._callbackInterface.a(null);
    }

    public void removeOnOpenCallback() {
        this._callbackInterface.b(null);
    }

    public void setOnCloseCallback(a aVar) {
        this._callbackInterface.a(aVar);
    }

    public void setOnOpenCallback(a aVar) {
        this._callbackInterface.b(aVar);
    }

    public void start() {
        Log.i("QSI", "start");
        loadDataWithBaseURL("https://qualtrics.com", getHTML(), "text/html", "utf-8", null);
    }

    public void put(String str, String str2, boolean z) {
        Log.i("QSI", String.format("::put %s - %s", new Object[]{str, str2}));
        if (this.mPageReady) {
            executeJS("putVar", str, str2, Boolean.valueOf(z));
            return;
        }
        Log.i("QSI", "queue var");
        this.queuedVariables.add(new b(str, str2, z));
    }

    public void clearTemporaryVariables() {
        executeJSExpression("AppSI={}");
    }

    public void clearPersistentVariables() {
        executeJSExpression("localStorage.clear()");
    }

    public void load() {
        Log.i("QSI", "load");
        if (this.mPageReady) {
            Log.i("QSI", "mPageReady true");
            executeJSExpression("load()");
        } else if (!this.mPageLoading && !this.mQSIReady) {
            Log.i("QSI", "!mPageLoading && !mQSIReady");
            this.mPageLoading = true;
        }
    }

    public void unload() {
        Log.i("QSI", "UNLOADING OUT");
        if (this.mPageReady) {
            Log.i("QSI", "UNLOADING IN");
            removeOnCloseCallback();
            executeJSExpression("close()");
            executeJSExpression("unload()");
        }
    }

    public QSIView getWebView() {
        if (getParent() != null) {
            ((ViewGroup) getParent()).removeAllViews();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("WIDTH: ");
        sb.append(getWidth());
        Log.i("QSI", sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append("HEIGHT: ");
        sb2.append(getHeight());
        Log.i("QSI", sb2.toString());
        return this;
    }

    public Fragment getFragment() {
        QSIFragment qSIFragment = new QSIFragment();
        qSIFragment.init(getWebView());
        return qSIFragment;
    }

    public android.support.v4.app.Fragment getSupportFragment() {
        QSISupportFragment qSISupportFragment = new QSISupportFragment();
        qSISupportFragment.init(getWebView());
        return qSISupportFragment;
    }

    private void init(AttributeSet attributeSet, int i) {
        Log.i("QSI", "--INIT--");
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, C0158c.QSIEngine, i, 0);
            this.mInterceptId = obtainStyledAttributes.getString(C0158c.QSIEngine_interceptId);
            this.mZoneId = obtainStyledAttributes.getString(C0158c.QSIEngine_zoneId);
            obtainStyledAttributes.recycle();
        }
        this.queuedVariables = new ArrayList<>();
        setBackgroundColor(0);
        setPadding(0, 0, 0, 0);
        if (VERSION.SDK_INT >= 21) {
            setZ(Float.MAX_VALUE);
        } else {
            bringToFront();
        }
        setVisibility(8);
        getSettings().setJavaScriptEnabled(true);
        getSettings().setDomStorageEnabled(true);
        getSettings().setUseWideViewPort(true);
        this._callbackInterface = new a();
        addJavascriptInterface(this._callbackInterface, "native");
        setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView webView, String str) {
                Log.i("QSI", "onPageFinished ");
                QSIView.this.mPageReady = true;
                CookieManager.getInstance().setAcceptCookie(true);
                if (QSIView.this.queuedVariables.size() > 0) {
                    QSIView.this.unload();
                    Iterator it = QSIView.this.queuedVariables.iterator();
                    while (it.hasNext()) {
                        b bVar = (b) it.next();
                        StringBuilder sb = new StringBuilder();
                        sb.append("putting queued variable: ");
                        sb.append(bVar.a.toString());
                        sb.append(" value: ");
                        sb.append(bVar.b.toString());
                        Log.i("QSI", sb.toString());
                        QSIView.this.put(bVar.a, bVar.b, bVar.c);
                    }
                    QSIView.this.queuedVariables.clear();
                    QSIView.this.load();
                }
            }
        });
    }

    private String getHTML() {
        return String.format("<!doctype html><html><head><meta name='viewport' content='initial-scale=1' /><style>html, body {background-color: transparent;}.QSIPopOver {background-color: transparent !important;}.QSIEmbeddedTarget > div {border: none !important;border-radius: 3px !important;background-color: rgba(0,0,0,.5) !important;-webkit-animation: target-anim .6s;}@-webkit-keyframes target-anim {from {opacity: 0;-webkit-transform: scale(.7);}to {opacity: 1;-webkit-transform: scale(1);}}</style></head><body><div id='%s'></div><script src='https://%s-%s.siteintercept.qualtrics.com/WRSiteInterceptEngine/?Q_SIID=%s'></script><script type='text/javascript'>function getInterceptProperties(){return{'fullTargetUrl':QSI.util.tryGetTarget(),'targetUrl':QSI.reg[interceptId].options.targetURL,'type':QSI.reg[interceptId].type,'actionOptions':QSI.reg[interceptId].actionOptions}}function putVar(e,n,t){t?localStorage[e]=n:AppSI[e]=n}function load(){console.log(QSI.API.unloading),QSI?(QSI.API.load(),setInterceptListeners()):console.log(\"no QSI\")}function close(){document.querySelector('.QSIEmbeddedTarget').parentNode.removeChild(document.querySelector('.QSIEmbeddedTarget')),console.log('CLOSE CALLED')}function unload(){QSI?(QSI.API.unload(),console.log('unloaded')):console.log('did not unload')}function setInterceptListeners(){setTimeout(function(){QSI.reg&&QSI.reg[interceptId]&&!QSI.InterceptsRan?(QSI.util.removeObservers(),QSI.API.run()):setInterceptListeners()},100)}function handleIntercept(){if(QSI&&QSI.reg&&QSI.reg[interceptId]&&(QSI.reg[interceptId].willShow||QSI.reg[interceptId].displayed)){native.onQSIReady();try{var e=QSI.reg[interceptId].willShow||QSI.reg[interceptId].displayed;e.then(function(){native.onOpen(JSON.stringify(getInterceptProperties())),QSI.reg[interceptId].container&&QSI.reg[interceptId].container.addEventListener(\"DOMNodeRemoved\",function(){if(QSI.reg[interceptId].actionOptions&&QSI.reg[interceptId].actionOptions.targetEmbedded){var e=document.querySelector(\".QSIEmbeddedTarget\");e?(document.querySelector(\"iframe\").addEventListener(\"load\",function(){native.onMessage(\"frameloaded\")}),e.addEventListener(\"DOMNodeRemoved\",function(){native.onClose()})):native.onClose()}else native.onClose()})})}catch(n){native.onError(n.message||n||\"unknown error\")}}else setTimeout(function(){handleIntercept()},100)}function getVar(e){return localStorage.hasOwnProperty(e)?localStorage[e]:AppSI.hasOwnProperty(e)?AppSI[e]:\"\"}window.AppSI={},window.interceptId=\"%s\",window.zoneId=\"%s\",window.brandId=\"%s\",window.__interval=null,document.body.addEventListener(\"Resolved\",function(){window.depsResolved=!0,window.runningIntercept=!1,console.log(\"--RESOLVED--\"),native.onMessage(\"--resolved--\"),handleIntercept()},!1);</script></body></html>", new Object[]{this.mZoneId, this.mBrandId, this.mInterceptId, this.mInterceptId, this.mInterceptId, this.mZoneId, this.mBrandId, this.mInterceptId});
    }

    private void executeJSExpression(String str) {
        loadUrl(String.format("javascript:try{%s}catch(error){native.onError(error.message)}", new Object[]{str}));
    }

    private void executeJS(String str, Object... objArr) {
        StringBuilder sb = new StringBuilder();
        sb.append("javascript:try{");
        sb.append(str);
        sb.append("(");
        String str2 = "";
        for (Object obj : objArr) {
            sb.append(str2);
            str2 = ",";
            boolean z = obj instanceof String;
            if (z) {
                sb.append("'");
            }
            sb.append(obj);
            if (z) {
                sb.append("'");
            }
        }
        sb.append(")}catch(error){native.onError(error)}");
        try {
            loadUrl(sb.toString());
        } catch (Exception e) {
            Log.i("QSI", e.getMessage());
        }
    }

    public String getBrandId() {
        return this.mBrandId;
    }

    public void setBrandId(String str) {
        this.mBrandId = str;
    }

    public String getInterceptId() {
        return this.mInterceptId;
    }

    public void setInterceptId(String str) {
        this.mInterceptId = str;
    }

    public String getZoneId() {
        return this.mZoneId;
    }

    public void setZoneId(String str) {
        this.mZoneId = str;
    }

    public QSIEventListener getQsiEvents() {
        return this.qsiEvents;
    }

    public void setQsiEvents(QSIEventListener qSIEventListener) {
        this.qsiEvents = qSIEventListener;
    }
}
