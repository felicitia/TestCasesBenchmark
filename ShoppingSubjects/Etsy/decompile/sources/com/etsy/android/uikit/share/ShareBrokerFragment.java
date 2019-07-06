package com.etsy.android.uikit.share;

import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Telephony.Sms;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.Recycler;
import android.support.v7.widget.RecyclerView.State;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnDrawListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.o;
import com.etsy.android.lib.core.g;
import com.etsy.android.lib.core.k;
import com.etsy.android.lib.core.m;
import com.etsy.android.lib.core.z;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.ShortenedUrl;
import com.etsy.android.lib.util.p;
import com.etsy.android.uikit.adapter.BaseRecyclerViewAdapter;
import com.etsy.android.uikit.ui.core.TrackingBaseFragment;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.util.j;
import com.facebook.FacebookException;
import com.facebook.d;
import com.facebook.e;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.google.android.gms.plus.a.C0142a;
import com.pinterest.pinit.PinItButton;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class ShareBrokerFragment extends TrackingBaseFragment implements com.etsy.android.uikit.share.ShareIntentListAdapter.IntentItemHolder.a {
    private static a APP_PRIORITY = new a(FACEBOOK, FACEBOOK_MESSENGER, WHATSAPP, PINTEREST, GOOGLE_PLUS, GOOGLE_TALK, GMAIL, COPY_LINK, TWITTER, INSTAGRAM, TUMBLR);
    private static final b COPY_LINK = new b("com.google.android.apps.docs", "Clipboard");
    private static final b FACEBOOK = new b("com.facebook.katana");
    private static final b FACEBOOK_MESSENGER = new b("com.facebook.orca");
    private static final b GMAIL = new b("com.google.android.gm");
    private static final b GOOGLE_INBOX = new b("com.google.android.apps.inbox");
    private static final b GOOGLE_PLUS = new b("com.google.android.apps.plus");
    private static final b GOOGLE_TALK = new b("com.google.android.talk");
    private static final b INSTAGRAM = new b("com.instagram.android");
    private static final b PINTEREST = new b("com.pinterest");
    private static final b SMS = new b("com.android.mms");
    /* access modifiers changed from: private */
    public static final String TAG = f.a(ShareBrokerFragment.class);
    private static final b TUMBLR = new b("com.tumblr");
    private static final b TWITTER = new b("com.twitter.android");
    private static final b WHATSAPP = new b("com.whatsapp");
    protected List<ResolveInfo> mActivitiesArray;
    protected BaseRecyclerViewAdapter<ResolveInfo> mAdapter;
    protected d mCallbackManager;
    protected List<ResolveInfo> mEmailLikeActivitiesArray;
    protected boolean mFromSocialContentCreator = false;
    /* access modifiers changed from: private */
    public com.etsy.android.uikit.util.f mHashtagHelper = new com.etsy.android.uikit.util.f();
    protected String mImageUrl;
    protected LayoutManager mLayoutManager;
    protected View mLoadingView;
    @Nullable
    protected Uri mLocalImageUri;
    protected PackageManager mPkgManager;
    protected RecyclerView mRecyclerView;
    protected ShareDialog mShareDialog;
    protected LinearLayout mShareLayout;
    protected ShortenedUrl mShareUrl;
    protected String mSubject;
    protected String mText;
    protected int mTranslationY;
    protected String mType = "text/plain";
    protected String mUrl;

    public static class WrapContentLinearLayoutManager extends LinearLayoutManager {
        private int[] mMeasuredDimension = new int[2];

        public WrapContentLinearLayoutManager(Context context) {
            super(context);
            setAutoMeasureEnabled(false);
        }

        public void onMeasure(Recycler recycler, State state, int i, int i2) {
            int mode = MeasureSpec.getMode(i);
            int mode2 = MeasureSpec.getMode(i2);
            int size = MeasureSpec.getSize(i);
            int size2 = MeasureSpec.getSize(i2);
            int i3 = 0;
            int i4 = 0;
            for (int i5 = 0; i5 < getItemCount(); i5++) {
                measureScrapChild(recycler, i5, MeasureSpec.makeMeasureSpec(i5, 0), MeasureSpec.makeMeasureSpec(i5, 0), this.mMeasuredDimension);
                if (getOrientation() == 0) {
                    i3 += this.mMeasuredDimension[0];
                    if (i5 == 0) {
                        i4 = this.mMeasuredDimension[1];
                    }
                } else {
                    i4 += this.mMeasuredDimension[1];
                    if (i5 == 0) {
                        i3 = this.mMeasuredDimension[0];
                    }
                }
            }
            if (mode == 1073741824) {
                i3 = size;
            }
            if (mode2 == 1073741824) {
                i4 = size2;
            }
            setMeasuredDimension(i3, i4);
        }

        private void measureScrapChild(Recycler recycler, int i, int i2, int i3, int[] iArr) {
            View viewForPosition = recycler.getViewForPosition(i);
            if (viewForPosition != null) {
                LayoutParams layoutParams = (LayoutParams) viewForPosition.getLayoutParams();
                viewForPosition.measure(ViewGroup.getChildMeasureSpec(i2, getPaddingLeft() + getPaddingRight(), layoutParams.width), ViewGroup.getChildMeasureSpec(i3, getPaddingTop() + getPaddingBottom(), layoutParams.height));
                iArr[0] = viewForPosition.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
                iArr[1] = viewForPosition.getMeasuredHeight() + layoutParams.bottomMargin + layoutParams.topMargin;
                recycler.recycleView(viewForPosition);
            }
        }
    }

    private static class a implements Comparator<ResolveInfo> {
        private final List<b> a;

        public a(b... bVarArr) {
            this.a = Arrays.asList(bVarArr);
        }

        /* renamed from: a */
        public int compare(ResolveInfo resolveInfo, ResolveInfo resolveInfo2) {
            b bVar = new b((PackageItemInfo) resolveInfo.activityInfo);
            b bVar2 = new b((PackageItemInfo) resolveInfo2.activityInfo);
            if (this.a.contains(bVar) && this.a.contains(bVar2)) {
                return this.a.indexOf(bVar) - this.a.indexOf(bVar2);
            }
            if (this.a.contains(bVar)) {
                return -1;
            }
            return this.a.contains(bVar2) ? 1 : 0;
        }
    }

    private static class b extends PackageItemInfo {
        public b(String str) {
            this(str, null);
        }

        public b(PackageItemInfo packageItemInfo) {
            super(packageItemInfo);
        }

        public b(String str, String str2) {
            this.packageName = str;
            this.name = str2;
        }

        public boolean equals(Object obj) {
            boolean z = false;
            if (!(obj instanceof PackageItemInfo)) {
                return false;
            }
            PackageItemInfo packageItemInfo = (PackageItemInfo) obj;
            boolean z2 = this.packageName == null ? packageItemInfo.packageName == null : this.packageName.equals(packageItemInfo.packageName);
            boolean z3 = this.name == null || packageItemInfo.name == null || this.name.contains(packageItemInfo.name) || packageItemInfo.name.contains(this.name);
            if (z2 && z3) {
                z = true;
            }
            return z;
        }
    }

    protected class c implements e<com.facebook.share.c.a> {
        private ResolveInfo b;

        public c(ResolveInfo resolveInfo) {
            this.b = resolveInfo;
        }

        public void a(com.facebook.share.c.a aVar) {
            f.c(ShareBrokerFragment.TAG, "SUCCESS share to facebook {post_id:%s}", aVar.a());
        }

        public void a() {
            f.c(ShareBrokerFragment.TAG, "CANCEL share to facebook {post_id:%s}");
        }

        public void a(FacebookException facebookException) {
            ShareBrokerFragment.this.onShareError(this.b);
            f.c(ShareBrokerFragment.TAG, "ERROR share to facebook - %s", facebookException.getMessage());
        }
    }

    /* access modifiers changed from: protected */
    public d onBeforeShare(ResolveInfo resolveInfo, d dVar) {
        return dVar;
    }

    /* access modifiers changed from: protected */
    public void onShareError(ResolveInfo resolveInfo) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            readArguments(arguments);
        }
        if (this.mUrl == null) {
            f.a((RuntimeException) new IllegalArgumentException("Share url not specified"));
        }
        if (this.mAdapter == null) {
            this.mAdapter = createAdapter();
            ((ShareIntentListAdapter) this.mAdapter).setOnClickListener(this);
        }
    }

    /* access modifiers changed from: protected */
    public void readArguments(Bundle bundle) {
        this.mSubject = bundle.getString(ResponseConstants.SUBJECT);
        this.mText = bundle.getString("text");
        this.mUrl = bundle.getString("url");
        this.mImageUrl = bundle.getString(ResponseConstants.IMAGE_URL);
        this.mFromSocialContentCreator = bundle.getBoolean("from_social_content_creator", false);
        String string = bundle.getString("image_file_uri");
        if (!TextUtils.isEmpty(string)) {
            this.mLocalImageUri = Uri.parse(string);
        }
        String string2 = bundle.getString("share_type");
        if (!TextUtils.isEmpty(string2)) {
            this.mType = string2;
        }
    }

    /* access modifiers changed from: protected */
    public void shortenUrl() {
        if (this.mShareUrl == null) {
            this.mShareUrl = new ShortenedUrl(this.mUrl);
            if (!this.mShareUrl.isShortened()) {
                getRequestQueue().a((Object) this, (g<Result>) m.a(ShortenedUrl.class, "/etsyapps/v3/public/shorten-url").a("url", this.mShareUrl.getLongUrl()).a((com.etsy.android.lib.core.f.c<Result>) new com.etsy.android.lib.core.f.c<ShortenedUrl>() {
                    public void a(List<ShortenedUrl> list, int i, k<ShortenedUrl> kVar) {
                        if (ShareBrokerFragment.this.mShareUrl != null) {
                            ShareBrokerFragment.this.mShareUrl = (ShortenedUrl) list.get(0);
                            ShareBrokerFragment.this.mUrl = ShareBrokerFragment.this.mShareUrl.getShortUrl();
                        }
                    }
                }).a());
            }
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(com.etsy.android.lib.a.k.fragment_share, viewGroup, false);
        inflate.findViewById(i.button_cancel).setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                ShareBrokerFragment.this.dismiss();
            }
        });
        if (this.mUrl == null) {
            Toast.makeText(getActivity(), o.share_no_url_error, 0).show();
            com.etsy.android.uikit.nav.b.b(getActivity()).h();
        }
        return inflate;
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mRecyclerView = (RecyclerView) view.findViewById(i.recycler_view);
        if (this.mLayoutManager == null) {
            this.mLayoutManager = createLayoutManager();
            this.mRecyclerView.setLayoutManager(this.mLayoutManager);
        }
        this.mShareLayout = (LinearLayout) view.findViewById(i.share_layout);
        this.mLoadingView = view.findViewById(i.loading_view);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mPkgManager = getActivity().getPackageManager();
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mRecyclerView.setVisibility(8);
        this.mLoadingView.setVisibility(0);
        loadShareActivities();
    }

    private void loadShareActivities() {
        z.a(new AsyncTask<Void, Void, Void>() {
            /* access modifiers changed from: protected */
            /* renamed from: a */
            public void onPostExecute(Void voidR) {
                if (ShareBrokerFragment.this.mRecyclerView != null) {
                    ShareBrokerFragment.this.mAdapter.clear();
                    int[] iArr = new int[2];
                    ShareBrokerFragment.this.mLoadingView.getLocationInWindow(iArr);
                    final int i = iArr[1];
                    ShareBrokerFragment.this.mLoadingView.setVisibility(8);
                    ShareBrokerFragment.this.mRecyclerView.setVisibility(0);
                    j.a(ShareBrokerFragment.this.mRecyclerView.getViewTreeObserver(), (OnGlobalLayoutListener) new OnGlobalLayoutListener() {
                        public void onGlobalLayout() {
                            j.b(ShareBrokerFragment.this.mRecyclerView.getViewTreeObserver(), (OnGlobalLayoutListener) this);
                            int[] iArr = new int[2];
                            ShareBrokerFragment.this.mRecyclerView.getLocationInWindow(iArr);
                            ShareBrokerFragment.this.mTranslationY = i - iArr[1];
                            ShareBrokerFragment.this.mShareLayout.setTranslationY((float) ShareBrokerFragment.this.mTranslationY);
                        }
                    });
                    j.a(ShareBrokerFragment.this.mRecyclerView.getViewTreeObserver(), (OnDrawListener) new OnDrawListener() {
                        public void onDraw() {
                            j.b(ShareBrokerFragment.this.mRecyclerView.getViewTreeObserver(), (OnDrawListener) this);
                            ShareBrokerFragment.this.mShareLayout.animate().setDuration(300).translationY(0.0f).start();
                        }
                    });
                    ShareBrokerFragment.this.mAdapter.addItems(ShareBrokerFragment.this.mActivitiesArray);
                }
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public Void doInBackground(Void... voidArr) {
                if (ShareBrokerFragment.this.mType.startsWith("image/")) {
                    ShareBrokerFragment.this.setLocalImageUri();
                }
                ShareBrokerFragment.this.setActivitiesArray();
                ShareBrokerFragment.this.setEmailLikeActivitiesArray();
                return null;
            }
        }, new Void[0]);
    }

    /* access modifiers changed from: private */
    public void setLocalImageUri() {
        if (this.mImageUrl != null) {
            Bitmap a2 = com.etsy.android.lib.core.img.f.a().a(this.mImageUrl, 0, 0, false, true);
            Context context = getContext();
            if (!(a2 == null || context == null)) {
                File a3 = p.a(context);
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(a3);
                    a2.compress(CompressFormat.PNG, 90, fileOutputStream);
                    fileOutputStream.close();
                    this.mLocalImageUri = p.a(getContext(), a3);
                } catch (FileNotFoundException e) {
                    String str = TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("File not found: ");
                    sb.append(e.getMessage());
                    f.e(str, sb.toString());
                    com.etsy.android.lib.logger.legacy.b a4 = com.etsy.android.lib.logger.legacy.b.a();
                    String str2 = TAG;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Could not find file while writing image share: ");
                    sb2.append(e.getMessage());
                    a4.a(str2, sb2.toString());
                } catch (IOException e2) {
                    String str3 = TAG;
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("Error accessing file: ");
                    sb3.append(e2.getMessage());
                    f.e(str3, sb3.toString());
                    com.etsy.android.lib.logger.legacy.b a5 = com.etsy.android.lib.logger.legacy.b.a();
                    String str4 = TAG;
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append("Could not access file while writing image share: ");
                    sb4.append(e2.getMessage());
                    a5.a(str4, sb4.toString());
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void setActivitiesArray() {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType(this.mType);
        this.mActivitiesArray = this.mPkgManager.queryIntentActivities(intent, 0);
        Collections.sort(this.mActivitiesArray, APP_PRIORITY);
    }

    /* access modifiers changed from: private */
    public void setEmailLikeActivitiesArray() {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("message/rfc822");
        this.mEmailLikeActivitiesArray = this.mPkgManager.queryIntentActivities(intent, 0);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (this.mCallbackManager != null) {
            this.mCallbackManager.a(i, i2, intent);
        }
    }

    public void onDestroyView() {
        this.mLayoutManager = null;
        this.mRecyclerView = null;
        super.onDestroyView();
    }

    public void onIntentItemClick(int i) {
        String str;
        ResolveInfo resolveInfo = (ResolveInfo) this.mAdapter.getItem(i);
        d onBeforeShare = onBeforeShare(resolveInfo, new d().a(this.mSubject).b(this.mText).c(this.mUrl).d(this.mImageUrl).a(this.mLocalImageUri).e(this.mType));
        if (this.mFromSocialContentCreator) {
            onBeforeShare.b(copyShareTextWithUrlToClipboard(onBeforeShare));
        }
        b bVar = null;
        if (isPinterest(resolveInfo) && getConfigMap().c(com.etsy.android.lib.config.b.d)) {
            bVar = PINTEREST;
            onClickPinterest(resolveInfo, onBeforeShare);
        } else if (isFacebook(resolveInfo)) {
            bVar = FACEBOOK;
            if (onBeforeShare.e() == null) {
                onClickFacebookShareLink(resolveInfo, onBeforeShare);
            } else {
                com.etsy.android.lib.core.img.g.b(getContext(), onBeforeShare.e()).b(io.reactivex.e.a.b()).a(io.reactivex.a.b.a.a()).a((io.reactivex.functions.b<? super T, ? super Throwable>) new b<Object,Object>(this, resolveInfo, bVar));
                return;
            }
        } else if (isGooglePlus(resolveInfo)) {
            bVar = GOOGLE_PLUS;
            onClickGooglePlus(resolveInfo, onBeforeShare);
        } else if (isCopy(resolveInfo)) {
            bVar = COPY_LINK;
            onClickCopy(resolveInfo, onBeforeShare);
        } else if (isWhatsApp(resolveInfo)) {
            bVar = WHATSAPP;
            onClickWhatsApp(resolveInfo, onBeforeShare);
        } else if (isInstagram(resolveInfo)) {
            bVar = INSTAGRAM;
            onClickInstagram(resolveInfo, onBeforeShare);
        } else {
            onClickOther(resolveInfo, onBeforeShare);
        }
        if (bVar == null) {
            str = ResponseConstants.OTHER;
        } else {
            str = bVar.packageName;
        }
        trackUserShare(str);
        onShareComplete();
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void lambda$onIntentItemClick$0$ShareBrokerFragment(ResolveInfo resolveInfo, b bVar, Bitmap bitmap, Throwable th) throws Exception {
        onClickFacebookSharePhoto(resolveInfo, bitmap);
        trackUserShare(bVar.packageName);
        onShareComplete();
    }

    private void trackUserShare(final String str) {
        final int length = this.mText != null ? this.mText.length() : 0;
        getAnalyticsContext().a("scc_shared_to_network", new HashMap<AnalyticsLogAttribute, Object>() {
            {
                put(AnalyticsLogAttribute.NETWORK, str);
                put(AnalyticsLogAttribute.HASHTAGS, ShareBrokerFragment.this.mHashtagHelper.a(ShareBrokerFragment.this.mText));
                put(AnalyticsLogAttribute.CHARACTER_COUNT, Integer.valueOf(length));
            }
        });
    }

    private void onClickPinterest(final ResolveInfo resolveInfo, d dVar) {
        PinItButton pinItButton = new PinItButton(getActivity());
        pinItButton.setListener(new com.pinterest.pinit.b() {
            public void a(Exception exc) {
                super.a(exc);
                f.e(ShareBrokerFragment.TAG, "Error creating pin", exc);
                ShareBrokerFragment.this.onShareError(resolveInfo);
            }
        });
        String d = dVar.d();
        if (d != null) {
            pinItButton.setImageUrl(d);
        } else if (this.mLocalImageUri != null) {
            getContext().grantUriPermission("com.pinterest", this.mLocalImageUri, 1);
            pinItButton.setImageUri(this.mLocalImageUri);
        }
        pinItButton.setUrl(dVar.c());
        pinItButton.setDescription(dVar.b());
        pinItButton.performClick();
    }

    private void onClickFacebookShareLink(ResolveInfo resolveInfo, d dVar) {
        this.mCallbackManager = com.facebook.d.a.a();
        this.mShareDialog = new ShareDialog((Fragment) this);
        this.mShareDialog.a(this.mCallbackManager, (e) new c(resolveInfo));
        com.facebook.share.model.ShareLinkContent.a a2 = new com.facebook.share.model.ShareLinkContent.a().b(dVar.a()).a(dVar.b());
        if (dVar.c() != null) {
            a2.a(Uri.parse(dVar.c()));
        }
        if (dVar.d() != null) {
            a2.b(Uri.parse(dVar.d()));
        }
        this.mShareDialog.b(a2.a());
    }

    private void onClickFacebookSharePhoto(ResolveInfo resolveInfo, Bitmap bitmap) {
        SharePhotoContent a2 = new com.facebook.share.model.SharePhotoContent.a().a(new com.facebook.share.model.SharePhoto.a().a(bitmap).c()).a();
        this.mCallbackManager = com.facebook.d.a.a();
        this.mShareDialog = new ShareDialog((Fragment) this);
        this.mShareDialog.a(this.mCallbackManager, (e) new c(resolveInfo));
        this.mShareDialog.b(a2);
    }

    private void onClickGooglePlus(ResolveInfo resolveInfo, d dVar) {
        C0142a b2 = new C0142a(getActivity()).a((CharSequence) dVar.b()).a(dVar.f()).b(Uri.parse(dVar.c()));
        if (dVar.f().startsWith("image/") && dVar.e() != null) {
            b2.a(this.mLocalImageUri);
        }
        getActivity().startActivity(b2.a());
    }

    private void onClickCopy(ResolveInfo resolveInfo, d dVar) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setClassName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
        intent.putExtra("android.intent.extra.TEXT", dVar.c());
        intent.setType(dVar.f());
        if (dVar.f().startsWith("image/") && dVar.e() != null) {
            intent.putExtra("android.intent.extra.STREAM", dVar.e());
        }
        getActivity().startActivity(intent);
    }

    private void onClickWhatsApp(ResolveInfo resolveInfo, d dVar) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setClassName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
        intent.putExtra("android.intent.extra.TEXT", dVar.b());
        intent.setType(dVar.f());
        if (dVar.f().startsWith("image/") && dVar.e() != null) {
            intent.putExtra("android.intent.extra.STREAM", dVar.e());
        }
        getActivity().startActivity(intent);
    }

    private void onClickInstagram(ResolveInfo resolveInfo, d dVar) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setClassName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
        intent.setType(dVar.f());
        if (dVar.f().startsWith("image/") && dVar.e() != null) {
            intent.putExtra("android.intent.extra.STREAM", dVar.e());
        } else if (dVar.f().startsWith("text/") && dVar.b() != null) {
            intent.putExtra("android.intent.extra.TEXT", dVar.b());
        }
        getActivity().startActivity(intent);
    }

    private String copyShareTextWithUrlToClipboard(d dVar) {
        ClipboardManager clipboardManager = (ClipboardManager) getContext().getSystemService("clipboard");
        String format = String.format("%s %s", new Object[]{dVar.b(), dVar.c()});
        clipboardManager.setPrimaryClip(ClipData.newPlainText(ResponseConstants.LABEL, format));
        return format;
    }

    private void onClickOther(ResolveInfo resolveInfo, d dVar) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setClassName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
        if (dVar.a() != null) {
            intent.putExtra("android.intent.extra.SUBJECT", dVar.a());
        }
        intent.putExtra("android.intent.extra.TEXT", dVar.b());
        intent.setType(dVar.f());
        if (dVar.f().startsWith("image/") && dVar.e() != null) {
            intent.putExtra("android.intent.extra.STREAM", dVar.e());
        }
        getActivity().startActivity(intent);
    }

    /* access modifiers changed from: protected */
    public void onShareComplete() {
        com.etsy.android.uikit.nav.b.b(getActivity()).a(-1, getResultIntent());
    }

    protected static boolean isFacebook(ResolveInfo resolveInfo) {
        return FACEBOOK.equals(new b((PackageItemInfo) resolveInfo.activityInfo));
    }

    protected static boolean isPinterest(ResolveInfo resolveInfo) {
        return PINTEREST.equals(new b((PackageItemInfo) resolveInfo.activityInfo));
    }

    protected static boolean isTwitter(ResolveInfo resolveInfo) {
        return TWITTER.equals(new b((PackageItemInfo) resolveInfo.activityInfo));
    }

    protected static boolean isWhatsApp(ResolveInfo resolveInfo) {
        return WHATSAPP.equals(new b((PackageItemInfo) resolveInfo.activityInfo));
    }

    protected static boolean isGooglePlus(ResolveInfo resolveInfo) {
        return GOOGLE_PLUS.equals(new b((PackageItemInfo) resolveInfo.activityInfo));
    }

    protected static boolean isHangouts(ResolveInfo resolveInfo) {
        return GOOGLE_TALK.equals(new b((PackageItemInfo) resolveInfo.activityInfo));
    }

    protected static boolean isGmail(ResolveInfo resolveInfo) {
        return GMAIL.equals(new b((PackageItemInfo) resolveInfo.activityInfo));
    }

    protected static boolean isGoogleInbox(ResolveInfo resolveInfo) {
        return GOOGLE_INBOX.equals(new b((PackageItemInfo) resolveInfo.activityInfo));
    }

    protected static boolean isInstagram(ResolveInfo resolveInfo) {
        return INSTAGRAM.equals(new b((PackageItemInfo) resolveInfo.activityInfo));
    }

    protected static boolean isGoogleEmail(ResolveInfo resolveInfo) {
        return isGoogleInbox(resolveInfo) || isGmail(resolveInfo);
    }

    /* access modifiers changed from: protected */
    public boolean isEmailLike(ResolveInfo resolveInfo) {
        if (isGoogleEmail(resolveInfo)) {
            return true;
        }
        b bVar = new b((PackageItemInfo) resolveInfo.activityInfo);
        for (ResolveInfo resolveInfo2 : this.mEmailLikeActivitiesArray) {
            if (bVar.equals(new b((PackageItemInfo) resolveInfo2.activityInfo))) {
                return true;
            }
        }
        return false;
    }

    protected static boolean isCopy(ResolveInfo resolveInfo) {
        return COPY_LINK.equals(new b((PackageItemInfo) resolveInfo.activityInfo));
    }

    protected static boolean isTumblr(ResolveInfo resolveInfo) {
        return TUMBLR.equals(new b((PackageItemInfo) resolveInfo.activityInfo));
    }

    /* access modifiers changed from: protected */
    @TargetApi(19)
    public boolean isSms(ResolveInfo resolveInfo) {
        b bVar = new b((PackageItemInfo) resolveInfo.activityInfo);
        if (!com.etsy.android.lib.util.k.b() || !bVar.equals(new b(Sms.getDefaultSmsPackage(getActivity())))) {
            return SMS.equals(new b((PackageItemInfo) resolveInfo.activityInfo));
        }
        return true;
    }

    public Intent getResultIntent() {
        Intent intent = new Intent();
        intent.putExtra(ResponseConstants.SUBJECT, this.mSubject);
        intent.putExtra("text", this.mText);
        intent.putExtra("url", this.mUrl);
        intent.putExtra(ResponseConstants.IMAGE_URL, this.mImageUrl);
        if (this.mLocalImageUri != null) {
            intent.putExtra("image_file_uri", this.mLocalImageUri.toString());
        }
        intent.putExtra("from_social_content_creator", this.mFromSocialContentCreator);
        intent.putExtra("share_type", this.mType);
        return intent;
    }

    /* access modifiers changed from: protected */
    public void dismiss() {
        com.etsy.android.uikit.nav.b.b(getActivity()).h();
    }

    /* access modifiers changed from: protected */
    public LayoutManager createLayoutManager() {
        return new WrapContentLinearLayoutManager(getActivity());
    }

    /* access modifiers changed from: protected */
    public BaseRecyclerViewAdapter<ResolveInfo> createAdapter() {
        return new ShareIntentListAdapter(getActivity(), com.etsy.android.lib.a.k.standard_image_list_item);
    }
}
