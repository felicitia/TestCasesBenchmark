package com.etsy.android.ui.convos;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View.OnClickListener;
import com.etsy.android.R;
import com.etsy.android.lib.convos.BaseSplitView;
import com.etsy.android.lib.convos.contentprovider.b;
import com.etsy.android.lib.convos.g;
import com.etsy.android.lib.convos.h;
import com.etsy.android.lib.core.b.a;
import com.etsy.android.lib.core.http.loader.NetworkLoader;
import com.etsy.android.lib.core.http.request.EtsyApiV3Request;
import com.etsy.android.lib.core.z;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.messaging.EtsyAction;
import com.etsy.android.lib.models.Conversation;
import com.etsy.android.lib.util.NetworkUtils;
import com.etsy.android.ui.BOENavDrawerActivity;
import com.etsy.android.ui.nav.c;
import com.etsy.android.ui.nav.e;
import com.etsy.android.uikit.nav.FragmentNavigator.AnimationMode;
import java.util.List;

public class ConvoBaseActivity extends BOENavDrawerActivity implements g, a {
    private static final int CONVERSATION_REQUEST = 117;
    private static final String TAG = f.a(ConvoBaseActivity.class);
    private BaseSplitView mBaseView;
    private boolean mIsFirstTime;
    /* access modifiers changed from: private */
    public long mLastConvoId;
    private Conversation mNotifiedConvo;
    private String mNotifiedConvoId;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_convo);
        setTitle(R.string.conversations);
        this.mBaseView = (BaseSplitView) findViewById(R.id.base_split_view);
        this.mIsFirstTime = bundle == null;
        if (!(getIntent() == null || getIntent().getExtras() == null)) {
            Bundle extras = getIntent().getExtras();
            this.mNotifiedConvo = (Conversation) extras.getSerializable("conversation");
            this.mNotifiedConvoId = extras.getString("convo_id");
        }
        this.mLastConvoId = this.mNotifiedConvo == null ? h.a((Context) this, bundle) : this.mNotifiedConvo.getConversationId();
        requireSignIn(EtsyAction.VIEW_CONVO);
    }

    /* access modifiers changed from: protected */
    public void onUserSignedInForAction(EtsyAction etsyAction) {
        super.onUserSignedInForAction(etsyAction);
        if (this.mNotifiedConvo != null || TextUtils.isEmpty(this.mNotifiedConvoId)) {
            showConvo(this.mNotifiedConvo);
        } else {
            getConvo(this.mNotifiedConvoId);
        }
    }

    public void showConvo(Conversation conversation) {
        c cVar;
        if (this.mIsFirstTime) {
            cVar = e.a((FragmentActivity) this).f();
        } else {
            cVar = e.a((FragmentActivity) this).e();
        }
        this.mIsFirstTime = false;
        cVar.b((int) R.id.fragment_container).a("convo_fragment").B();
        if (this.mBaseView.isTwoPane()) {
            Bundle bundle = new Bundle();
            bundle.putBoolean("should_activate", true);
            bundle.putLong("last_convo", this.mLastConvoId);
            if (conversation != null) {
                bundle.putBoolean("reload_only", true);
            }
            cVar.a(bundle).r();
            if (conversation != null) {
                onItemSelected(conversation, true);
                return;
            }
            return;
        }
        com.etsy.android.lib.convos.a.a(getSupportFragmentManager());
        if (conversation != null) {
            cVar.r();
            e.a((FragmentActivity) this).a().a(conversation, true);
        } else if (this.mLastConvoId > 0) {
            z.a(new AsyncTask<Void, Void, Conversation>() {
                /* access modifiers changed from: protected */
                /* renamed from: a */
                public Conversation doInBackground(Void... voidArr) {
                    return b.b((Context) ConvoBaseActivity.this, ConvoBaseActivity.this.mLastConvoId);
                }

                /* access modifiers changed from: protected */
                /* renamed from: a */
                public void onPostExecute(Conversation conversation) {
                    e.a((FragmentActivity) ConvoBaseActivity.this).a().a(conversation, false);
                }
            }, new Void[0]);
        } else {
            cVar.r();
        }
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        if (!this.mBaseView.isTwoPane()) {
            menu.removeGroup(R.id.menu_group_convo_thread);
            menu.removeItem(R.id.menu_send_reply);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    public void onItemSelected(Conversation conversation, boolean z) {
        if (conversation == null) {
            return;
        }
        if (this.mBaseView.isTwoPane()) {
            e.a((FragmentActivity) this).e().a(AnimationMode.FADE).B().b((int) R.id.fragment_detail_container).a(conversation, z);
        } else {
            e.a((FragmentActivity) this).a().a(conversation, true);
        }
    }

    public boolean isShowingConvo() {
        return com.etsy.android.lib.convos.a.b(getSupportFragmentManager());
    }

    public boolean isTwoPane() {
        return this.mBaseView.isTwoPane();
    }

    public void onShowEmpty() {
        h.b(this);
        this.mBaseView.showEmptyView(getSupportFragmentManager());
    }

    public void onShowConvo() {
        this.mBaseView.showBase();
    }

    public void onShowErrorView(OnClickListener onClickListener) {
        this.mBaseView.setErrorViewRetryListener(onClickListener);
        this.mBaseView.showErrorView();
    }

    public void getConvo(String str) {
        if (NetworkUtils.a().b()) {
            loadDataFromNetwork(117, (EtsyApiV3Request) new EtsyApiV3Request.a(Conversation.class, String.format("/member/conversations/%s", new Object[]{str})).d(), new NetworkLoader.a<Conversation>() {
                public void a(@NonNull List<Conversation> list, int i, @NonNull com.etsy.android.lib.core.a.a<Conversation> aVar) {
                    ConvoBaseActivity.this.showConvo((Conversation) list.get(0));
                }

                public void a(int i, @Nullable String str, @NonNull com.etsy.android.lib.core.a.a<Conversation> aVar) {
                    ConvoBaseActivity.this.showConvo(null);
                }
            });
        }
    }
}
