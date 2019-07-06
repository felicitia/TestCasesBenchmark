package com.contextlogic.wish.activity.webview;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.MailTo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.actionbar.ActionBarManager;
import com.contextlogic.wish.activity.actionbar.ActionBarManager.HomeButtonMode;
import com.contextlogic.wish.activity.cart.CartActivity;
import com.contextlogic.wish.activity.webview.WebViewActivity.Source;
import com.contextlogic.wish.activity.webview.WebViewSetupTask.WebViewSetupCallback;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.datacenter.StatusDataCenter;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import com.contextlogic.wish.link.DeepLink;
import com.contextlogic.wish.link.DeepLink.TargetType;
import com.contextlogic.wish.link.DeepLinkManager;
import com.contextlogic.wish.social.facebook.FacebookManager;
import com.contextlogic.wish.ui.view.TouchConsumingView;
import com.contextlogic.wish.util.VideoUtil.VideoSpecification;
import com.crashlytics.android.Crashlytics;
import com.klarna.checkout.KlarnaCheckout;
import com.klarna.checkout.SignalListener;
import java.net.URISyntaxException;
import java.util.Stack;
import org.json.JSONException;
import org.json.JSONObject;

public class WebViewFragment extends UiFragment<WebViewActivity> {
    private String mBackMarkerUrl;
    /* access modifiers changed from: private */
    public String mFirstUrl;
    /* access modifiers changed from: private */
    public boolean mHideActionBarItems;
    /* access modifiers changed from: private */
    public boolean mIsSmartActionBar;
    /* access modifiers changed from: private */
    public KlarnaCheckout mKlarnaCheckout;
    /* access modifiers changed from: private */
    public boolean mLoadKlarnaSDK;
    /* access modifiers changed from: private */
    public TouchConsumingView mLoadingView;
    private boolean mPurchaseComplete;
    /* access modifiers changed from: private */
    public Source mSource;
    /* access modifiers changed from: private */
    public Stack<String> mTitleBackStack;
    /* access modifiers changed from: private */
    public WebView mWebView;

    /* access modifiers changed from: protected */
    public int getLayoutResourceId() {
        return R.layout.webview_fragment;
    }

    public void releaseImages() {
    }

    public void restoreImages() {
    }

    public void handleResume() {
        super.handleResume();
        resumeWebView();
    }

    /* access modifiers changed from: protected */
    public void initialize() {
        this.mLoadingView = (TouchConsumingView) findViewById(R.id.webview_fragment_loading_view);
        if (ExperimentDataCenter.getInstance().shouldSeeNewProgressBar()) {
            View findViewById = this.mLoadingView.findViewById(R.id.webview_fragment_primary_progress_bar);
            View findViewById2 = this.mLoadingView.findViewById(R.id.webview_fragment_three_dot_progress_bar);
            if (!(findViewById2 == null || findViewById == null)) {
                findViewById.setVisibility(8);
                findViewById2.setVisibility(0);
            }
        }
        this.mIsSmartActionBar = ExperimentDataCenter.getInstance().shouldShowWebviewSmartActionbar();
        this.mPurchaseComplete = false;
        withActivity(new ActivityTask<WebViewActivity>() {
            public void performTask(WebViewActivity webViewActivity) {
                WebViewFragment.this.mFirstUrl = webViewActivity.getFirstUrl();
                WebViewFragment.this.mSource = webViewActivity.getSource();
                WebViewFragment.this.mLoadKlarnaSDK = webViewActivity.getLoadKlarnaSDK();
                WebViewFragment.this.mHideActionBarItems = webViewActivity.getHideActionBarItems();
            }
        });
        withServiceFragment(new ServiceTask<WebViewActivity, WebViewServiceFragment>() {
            public void performTask(WebViewActivity webViewActivity, WebViewServiceFragment webViewServiceFragment) {
                WebViewFragment.this.mWebView = webViewServiceFragment.getCachedWebView();
                if (WebViewFragment.this.mWebView != null) {
                    if (WebViewFragment.this.mWebView.getParent() != null) {
                        ((ViewGroup) WebViewFragment.this.mWebView.getParent()).removeView(WebViewFragment.this.mWebView);
                    }
                    WebViewFragment.this.attachToWebView();
                } else {
                    WebViewFragment.this.mWebView = new WebView(webViewActivity);
                    webViewServiceFragment.setCachedWebView(WebViewFragment.this.mWebView);
                    WebViewFragment.this.mLoadingView.setVisibility(0);
                    new WebViewSetupTask(WebViewFragment.this.mWebView, new WebViewSetupCallback() {
                        public void onWebviewReady() {
                            WebViewFragment.this.attachToWebView();
                            WebViewFragment.this.loadFirstUrl();
                        }
                    }).execute(new Void[0]);
                }
                if (WebViewFragment.this.mLoadKlarnaSDK) {
                    WebViewFragment.this.mKlarnaCheckout = new KlarnaCheckout(webViewActivity, "wish-klarna://klarna-checkout");
                    WebViewFragment.this.mKlarnaCheckout.setWebView(WebViewFragment.this.mWebView);
                    WebViewFragment.this.mKlarnaCheckout.setSignalListener(new SignalListener() {
                        public void onSignal(String str, JSONObject jSONObject) {
                            if (str.equals("complete")) {
                                try {
                                    WebViewFragment.this.mWebView.loadUrl(jSONObject.getString("uri"));
                                } catch (JSONException e) {
                                    Log.e(e.getMessage(), e.toString());
                                }
                            }
                        }
                    });
                }
                ((FrameLayout) WebViewFragment.this.findViewById(R.id.webview_fragment_webview_placeholder)).addView(WebViewFragment.this.mWebView, 0, new LayoutParams(-1, -1));
                WebViewFragment.this.resumeWebView();
            }
        });
        initializeValues();
        refreshActionBar();
    }

    private void refreshActionBar() {
        refreshActionBar(null);
    }

    private void refreshActionBar(String str) {
        refreshActionBar(str, null);
    }

    /* access modifiers changed from: private */
    public void refreshActionBar(final String str, final HomeButtonMode homeButtonMode) {
        if (this.mSource != Source.CART && !this.mHideActionBarItems) {
            setupBaseActionBar();
        }
        withActivity(new ActivityTask<WebViewActivity>() {
            public void performTask(WebViewActivity webViewActivity) {
                ActionBarManager actionBarManager = webViewActivity.getActionBarManager();
                String actionBarTitle = str != null ? str : webViewActivity.getActionBarTitle();
                if (actionBarTitle != null) {
                    actionBarManager.setTitle(actionBarTitle);
                }
                if (homeButtonMode != null) {
                    actionBarManager.setHomeButtonMode(homeButtonMode);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void resumeWebView() {
        if (this.mWebView != null) {
            this.mWebView.setVisibility(0);
            this.mWebView.setFocusable(true);
            this.mWebView.requestFocus(130);
            this.mWebView.onResume();
        }
    }

    private void initializeValues() {
        if (getSavedInstanceState() != null) {
            this.mBackMarkerUrl = getSavedInstanceState().getString("StoredStateBackMarkerUrl");
        }
    }

    /* access modifiers changed from: private */
    public void attachToWebView() {
        this.mTitleBackStack = new Stack<>();
        this.mWebView.setWebViewClient(new BaseWebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                return WebViewFragment.this.processWebViewUrl(str);
            }

            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                WebViewFragment.this.mLoadingView.setVisibility(0);
            }

            public void onReceivedError(WebView webView, int i, String str, String str2) {
                WebViewFragment.this.withActivity(new ActivityTask<WebViewActivity>() {
                    public void performTask(WebViewActivity webViewActivity) {
                        WebViewFragment.this.mLoadingView.setVisibility(8);
                        webViewActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(webViewActivity.getString(R.string.webview_loading_error)));
                    }
                });
            }

            public void onPageFinished(WebView webView, String str) {
                WebViewFragment.this.mLoadingView.setVisibility(8);
                if (WebViewFragment.this.mIsSmartActionBar) {
                    BaseActivity baseActivity = (BaseActivity) WebViewFragment.this.getActivity();
                    if (baseActivity != null) {
                        String mapUrlToTitle = mapUrlToTitle(baseActivity, str);
                        if (mapUrlToTitle != null) {
                            String title = baseActivity.getActionBarManager().getTitle();
                            if (!title.equals(mapUrlToTitle)) {
                                if (WebViewFragment.this.mTitleBackStack.empty() || !title.equals(WebViewFragment.this.mTitleBackStack.peek())) {
                                    WebViewFragment.this.mTitleBackStack.push(title);
                                }
                                WebViewFragment.this.refreshActionBar(mapUrlToTitle, HomeButtonMode.BACK_ARROW);
                            }
                        }
                    }
                }
            }
        });
    }

    public void completeImageUpload(String str, String str2, String str3) {
        if (this.mWebView != null) {
            this.mWebView.loadUrl(String.format("javascript:$(window).trigger('imageUrlReceived',['%s', '%s', '%s']);", new Object[]{str, str2, str3}));
        }
    }

    public void completeVideoUpload(String str) {
        if (this.mWebView != null) {
            this.mWebView.loadUrl(String.format("javascript:$(window).trigger('videoUrlReceived',['%s']);", new Object[]{str}));
        }
    }

    private void closeBrowser() {
        withActivity(new ActivityTask<WebViewActivity>() {
            public void performTask(WebViewActivity webViewActivity) {
                webViewActivity.finishActivity();
            }
        });
    }

    /* access modifiers changed from: private */
    public boolean processWebViewUrl(String str) {
        if (str == null) {
            return false;
        }
        final Uri parse = Uri.parse(str);
        if (parse == null) {
            return false;
        }
        String host = parse.getHost();
        boolean z = parse.getScheme() != null && parse.getScheme().equalsIgnoreCase(WishApplication.getDeepLinkProtocol());
        if (z && host != null && host.equalsIgnoreCase("close-browser")) {
            closeBrowser();
            return true;
        } else if (z && host.equalsIgnoreCase("purchase-complete")) {
            this.mPurchaseComplete = true;
            StatusDataCenter.getInstance().updateCartCount(0);
            StatusDataCenter.getInstance().refresh();
            withActivity(new ActivityTask<WebViewActivity>() {
                public void performTask(WebViewActivity webViewActivity) {
                    FacebookManager.getInstance().logPurchase(webViewActivity.getTransactionCartAmount(), webViewActivity.getTransactionCurrencyCode(), webViewActivity.getTransactionCartItemIds());
                    GoogleAnalyticsLogger.getInstance().logPurchase(webViewActivity.getTransactionId(), webViewActivity.getTransactionCartAmount(), webViewActivity.getTransactionCurrencyCode());
                }
            });
            return true;
        } else if (z && host.equalsIgnoreCase("cart-reload-required")) {
            StatusDataCenter.getInstance().refresh();
            return true;
        } else if (z && host.equalsIgnoreCase("purchase-complete-close-browser")) {
            closeBrowser();
            return true;
        } else if (z && host.equalsIgnoreCase("open-cart-close-browser")) {
            withActivity(new ActivityTask<WebViewActivity>() {
                public void performTask(WebViewActivity webViewActivity) {
                    Intent intent = new Intent();
                    intent.setClass(webViewActivity, CartActivity.class);
                    webViewActivity.startActivity(intent);
                }
            });
            closeBrowser();
            return true;
        } else if (z && host.equalsIgnoreCase("set-back-marker")) {
            this.mBackMarkerUrl = getCurrentUrl();
            return true;
        } else if (z && host.equalsIgnoreCase("upload-image")) {
            withServiceFragment(new ServiceTask<BaseActivity, WebViewServiceFragment>() {
                public void performTask(BaseActivity baseActivity, WebViewServiceFragment webViewServiceFragment) {
                    String queryParameter = parse.getQueryParameter("id");
                    String queryParameter2 = parse.getQueryParameter("bucket");
                    if (queryParameter2 == null) {
                        queryParameter2 = "ticket-image-uploads";
                    }
                    webViewServiceFragment.uploadImage(queryParameter, queryParameter2);
                }
            });
            return true;
        } else if (z && host.equalsIgnoreCase("upload-video")) {
            withServiceFragment(new ServiceTask<BaseActivity, WebViewServiceFragment>() {
                public void performTask(BaseActivity baseActivity, WebViewServiceFragment webViewServiceFragment) {
                    String queryParameter = parse.getQueryParameter("upload_url");
                    String queryParameter2 = parse.getQueryParameter("max_duration");
                    String queryParameter3 = parse.getQueryParameter("max_width");
                    String queryParameter4 = parse.getQueryParameter("max_height");
                    String queryParameter5 = parse.getQueryParameter("frame_rate");
                    String queryParameter6 = parse.getQueryParameter("bit_rate");
                    String queryParameter7 = parse.getQueryParameter("i_frame_interval");
                    VideoSpecification videoSpecification = new VideoSpecification();
                    if (queryParameter2 != null) {
                        try {
                            videoSpecification.maxDuration = (long) Integer.parseInt(queryParameter2);
                        } catch (NumberFormatException unused) {
                        }
                    }
                    if (queryParameter3 != null) {
                        try {
                            videoSpecification.maxWidth = Integer.parseInt(queryParameter3);
                        } catch (NumberFormatException unused2) {
                        }
                    }
                    if (queryParameter4 != null) {
                        try {
                            videoSpecification.maxHeight = Integer.parseInt(queryParameter4);
                        } catch (NumberFormatException unused3) {
                        }
                    }
                    if (queryParameter5 != null) {
                        try {
                            videoSpecification.frameRate = Integer.parseInt(queryParameter5);
                        } catch (NumberFormatException unused4) {
                        }
                    }
                    if (queryParameter6 != null) {
                        try {
                            videoSpecification.bitRate = Integer.parseInt(queryParameter6);
                        } catch (NumberFormatException unused5) {
                        }
                    }
                    if (queryParameter7 != null) {
                        try {
                            videoSpecification.iFrameInterval = Integer.parseInt(queryParameter7);
                        } catch (NumberFormatException unused6) {
                        }
                    }
                    webViewServiceFragment.uploadVideo(queryParameter, videoSpecification);
                }
            });
            return true;
        } else if (parse.getScheme().equals("mailto")) {
            try {
                MailTo parse2 = MailTo.parse(str);
                final Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("message/rfc822");
                if (parse2.getTo() != null) {
                    intent.putExtra("android.intent.extra.EMAIL", new String[]{parse2.getTo()});
                }
                if (parse2.getBody() != null) {
                    intent.putExtra("android.intent.extra.TEXT", parse2.getBody());
                }
                if (parse2.getSubject() != null) {
                    intent.putExtra("android.intent.extra.SUBJECT", parse2.getSubject());
                }
                if (parse2.getCc() != null) {
                    intent.putExtra("android.intent.extra.CC", parse2.getCc());
                }
                withActivity(new ActivityTask<WebViewActivity>() {
                    public void performTask(WebViewActivity webViewActivity) {
                        webViewActivity.startActivity(intent);
                    }
                });
            } catch (Throwable unused) {
                withActivity(new ActivityTask<WebViewActivity>() {
                    public void performTask(WebViewActivity webViewActivity) {
                        webViewActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(webViewActivity.getString(R.string.no_email_client)));
                    }
                });
            }
            return true;
        } else if (z) {
            final DeepLink deepLink = new DeepLink(parse);
            if (!(deepLink.getTargetType() == TargetType.NONE || deepLink.getTargetType() == TargetType.WEBSITE)) {
                withActivity(new ActivityTask<WebViewActivity>() {
                    public void performTask(WebViewActivity webViewActivity) {
                        DeepLinkManager.processDeepLink(webViewActivity, deepLink);
                    }
                });
            }
            return true;
        } else if (parse.toString().endsWith(".pdf")) {
            final Intent intent2 = new Intent();
            intent2.setDataAndType(parse, "application/pdf");
            if (intent2.resolveActivity(getActivity().getPackageManager()) != null) {
                withActivity(new ActivityTask<WebViewActivity>() {
                    public void performTask(WebViewActivity webViewActivity) {
                        webViewActivity.startActivity(intent2);
                    }
                });
            } else {
                this.mWebView.loadUrl(String.format("https://docs.google.com/gview?embedded=true&url=%s", new Object[]{parse.toString()}));
            }
            return true;
        } else if (host == null || !host.equals("m.me")) {
            return false;
        } else {
            try {
                startActivity(Intent.parseUri(str, 1));
            } catch (ActivityNotFoundException unused2) {
            } catch (URISyntaxException e) {
                StringBuilder sb = new StringBuilder();
                sb.append("Messenger link in WebView returned bad URI intent with message ");
                sb.append(e.getMessage());
                Crashlytics.logException(new Exception(sb.toString()));
            }
            return true;
        }
    }

    /* access modifiers changed from: private */
    public void loadFirstUrl() {
        this.mWebView.loadUrl(this.mFirstUrl);
    }

    public String getCurrentUrl() {
        String url = this.mWebView != null ? this.mWebView.getUrl() : null;
        return url == null ? this.mFirstUrl : url;
    }

    public void handleSaveInstanceState(Bundle bundle) {
        bundle.putString("StoredStateBackMarkerUrl", this.mBackMarkerUrl);
    }

    public boolean onBackPressed() {
        String currentUrl = getCurrentUrl();
        boolean z = (this.mBackMarkerUrl == null || currentUrl == null || !currentUrl.equals(this.mBackMarkerUrl)) ? false : true;
        if (this.mWebView != null && this.mWebView.canGoBack() && !z) {
            if (this.mIsSmartActionBar && !this.mTitleBackStack.empty()) {
                refreshActionBar((String) this.mTitleBackStack.pop(), this.mTitleBackStack.empty() ? HomeButtonMode.MENU_INDICATOR : HomeButtonMode.BACK_ARROW);
            }
            this.mWebView.goBack();
            return true;
        } else if (this.mSource != Source.CART || this.mPurchaseComplete) {
            return false;
        } else {
            withActivity(new ActivityTask<WebViewActivity>() {
                public void performTask(WebViewActivity webViewActivity) {
                    Intent intent = new Intent();
                    intent.setClass(webViewActivity, CartActivity.class);
                    webViewActivity.startActivity(intent);
                    webViewActivity.finish();
                }
            });
            return true;
        }
    }

    public void refreshWebView() {
        if (this.mWebView != null) {
            this.mWebView.reload();
        }
    }
}
