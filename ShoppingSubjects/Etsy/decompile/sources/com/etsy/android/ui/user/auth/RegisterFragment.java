package com.etsy.android.ui.user.auth;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.auth.f.a.C0058a;
import com.etsy.android.lib.auth.f.a.d;
import com.etsy.android.lib.auth.h;
import com.etsy.android.lib.config.b;
import com.etsy.android.lib.core.b.a;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.elk.f;
import com.etsy.android.lib.models.PaymentMethod;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.ShopAbout.Link;
import com.etsy.android.lib.models.SuggestUsernameResult;
import com.etsy.android.lib.requests.apiv3.SuggestUsernameEndpoint;
import com.etsy.android.lib.util.ExternalAccountUtil;
import com.etsy.android.lib.util.ExternalAccountUtil.AccountType;
import com.etsy.android.lib.util.ExternalAccountUtil.SignInFlow;
import com.etsy.android.lib.util.ah;
import com.etsy.android.lib.util.aj;
import com.etsy.android.stylekit.e;
import com.etsy.android.ui.EtsyFragment;
import com.etsy.android.ui.dialog.EtsyDialogFragment;
import com.etsy.android.uikit.ui.dialog.IDialogFragment.WindowMode;
import com.etsy.android.uikit.util.EtsyLinkify;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.util.i;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;

public class RegisterFragment extends EtsyFragment implements a {
    public static final String EXTRA_SHOW_SOCIAL_BUTTONS = "show_social_buttons";
    public static final String GENDER_NAME_FEMALE = "female";
    public static final String GENDER_NAME_MALE = "male";
    public static final String GENDER_NAME_PRIVATE = "private";
    private static final String LOG_NAMESPACE = "register";
    private io.reactivex.disposables.a disposables = new io.reactivex.disposables.a();
    f elkLogger;
    private String mAccountId;
    private String mAccountTypeName;
    /* access modifiers changed from: private */
    public SignInActivity mActivity;
    private String mAuthCode;
    private String mAuthToken;
    private View mAvatar;
    private ImageView mAvatarImage;
    private ImageView mAvatarSource;
    private i mDialogUtil;
    private View mHeader;
    h mLoginRequester;
    private View mOptionalFields;
    /* access modifiers changed from: private */
    public EtsyDialogFragment mParentDialog;
    private String mPreviousSuggestion;
    private String[] mPreviousSuggestionParams;
    a mSignInHandler;
    private View mSocialHeader;
    /* access modifiers changed from: private */
    public AutoCompleteTextView mTxtEmail;
    private TextView mTxtError;
    /* access modifiers changed from: private */
    public EditText mTxtFirstName;
    /* access modifiers changed from: private */
    public EditText mTxtLastName;
    /* access modifiers changed from: private */
    public EditText mTxtPassword;
    /* access modifiers changed from: private */
    public EditText mTxtPasswordConfirm;
    /* access modifiers changed from: private */
    public EditText mTxtUsername;
    private ah mUIErrors;
    private TextView mUsernameMsg;
    private View mView;
    com.etsy.android.lib.f.a schedulers;
    SuggestUsernameEndpoint suggestUsernameEndpoint;

    @NonNull
    public String getTrackingName() {
        return "register_view";
    }

    public void onStart() {
        super.onStart();
        this.disposables.a(this.mLoginRequester.a().b(io.reactivex.e.a.b()).a(io.reactivex.a.b.a.a()).a((Consumer<? super T>) new c<Object>(this)));
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void lambda$onStart$0$RegisterFragment(com.etsy.android.lib.auth.f.a aVar) throws Exception {
        if (aVar instanceof d) {
            this.mDialogUtil.a((int) R.string.signing_in);
        } else if (aVar instanceof C0058a) {
            this.mDialogUtil.a();
            this.mDialogUtil.a(this.mTxtError, (int) R.string.error_sign_in);
        } else {
            this.mDialogUtil.a();
            this.mSignInHandler.a(aVar, this.mLoginRequester);
        }
    }

    public void onStop() {
        super.onStop();
        this.disposables.a();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mView = layoutInflater.inflate(R.layout.fragment_register, viewGroup, false);
        return this.mView;
    }

    public void onActivityCreated(Bundle bundle) {
        String str;
        super.onActivityCreated(bundle);
        this.mParentDialog = (EtsyDialogFragment) getParentFragment();
        this.mParentDialog.hideHeader();
        this.mParentDialog.setWindowMode(WindowMode.WRAP);
        this.mActivity = (SignInActivity) getActivity();
        this.mDialogUtil = this.mActivity.getDialogHelper();
        TextView textView = (TextView) this.mView.findViewById(R.id.text_terms_of_use);
        if (getConfigMap().c(b.bM)) {
            textView.setText(Html.fromHtml(getResources().getString(R.string.user_registration_agreement)));
        } else {
            textView.setText(Html.fromHtml(getResources().getString(R.string.terms_of_use)));
        }
        EtsyLinkify.a((Context) getActivity(), textView, false);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                com.etsy.android.lib.logger.legacy.b.a().a("mobileweb_termsofservice");
            }
        });
        this.mHeader = this.mView.findViewById(R.id.header);
        this.mSocialHeader = this.mView.findViewById(R.id.social_header);
        this.mOptionalFields = this.mView.findViewById(R.id.optional_fields);
        this.mAvatar = this.mView.findViewById(R.id.avatar);
        this.mAvatarImage = (ImageView) this.mView.findViewById(R.id.avatar_image);
        this.mAvatarSource = (ImageView) this.mView.findViewById(R.id.avatar_source);
        TextView textView2 = (TextView) this.mView.findViewById(R.id.social_title);
        this.mTxtError = (TextView) this.mView.findViewById(R.id.txt_error);
        this.mTxtPassword = (EditText) this.mView.findViewById(R.id.edit_register_password);
        this.mTxtPasswordConfirm = (EditText) this.mView.findViewById(R.id.edit_password_confirm);
        e.a((TextView) this.mTxtPassword, (int) R.string.sk_typeface_normal);
        e.a((TextView) this.mTxtPasswordConfirm, (int) R.string.sk_typeface_normal);
        this.mTxtFirstName = (EditText) this.mView.findViewById(R.id.edit_first_name);
        this.mTxtLastName = (EditText) this.mView.findViewById(R.id.edit_last_name);
        this.mTxtEmail = (AutoCompleteTextView) this.mView.findViewById(R.id.edit_email);
        this.mTxtUsername = (EditText) this.mView.findViewById(R.id.edit_username);
        this.mUsernameMsg = (TextView) this.mView.findViewById(R.id.username_msg);
        initializeSocialSignInButtons();
        ((Button) this.mView.findViewById(R.id.button_register)).setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                String trim = RegisterFragment.this.mTxtFirstName.getText().toString().trim();
                String trim2 = RegisterFragment.this.mTxtLastName.getText().toString().trim();
                String trim3 = RegisterFragment.this.mTxtEmail.getText().toString().trim();
                String obj = RegisterFragment.this.mTxtPassword.getText().toString();
                String obj2 = RegisterFragment.this.mTxtPasswordConfirm.getText().toString();
                RegisterFragment.this.register(RegisterFragment.this.mTxtUsername.getText().toString().trim(), obj, obj2, trim, trim2, trim3, "private");
            }
        });
        this.mUIErrors = new ah.a().a("password", this.mTxtPassword).a("password_mismatch", this.mTxtPasswordConfirm).a(ResponseConstants.USERNAME, this.mTxtUsername).a("email", this.mTxtEmail).a(ResponseConstants.FIRST_NAME, this.mTxtFirstName).a(ResponseConstants.LAST_NAME, this.mTxtLastName).a();
        TextView textView3 = (TextView) this.mView.findViewById(R.id.switch_to_sign_in);
        aj.a(textView3, (int) R.string.register_switch_to_sign_in, (int) R.string.register_switch_to_sign_in_highlight, (int) R.color.sk_orange_30);
        textView3.setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                RegisterFragment.this.mParentDialog.dismissAllowingStateLoss(false);
                RegisterFragment.this.mActivity.showSignIn();
            }
        });
        Bundle arguments = getArguments();
        if (arguments.containsKey("auth_token")) {
            this.mSocialHeader.setVisibility(0);
            this.mHeader.setVisibility(8);
            this.mOptionalFields.setVisibility(8);
            getImageBatch().b(arguments.getString("avatar_url"), this.mAvatarImage, getResources().getDimensionPixelOffset(R.dimen.user_avatar_image));
            str = arguments.getString("account_type_name");
            char c = 65535;
            int hashCode = str.hashCode();
            if (hashCode != -1240244679) {
                if (hashCode == 497130182 && str.equals(Link.FACEBOOK_TITLE)) {
                    c = 1;
                }
            } else if (str.equals(PaymentMethod.TYPE_GOOGLE_WALLET)) {
                c = 0;
            }
            switch (c) {
                case 0:
                    this.mAvatarSource.setImageResource(R.drawable.ic_google);
                    break;
                case 1:
                    this.mAvatarSource.setImageResource(R.drawable.ic_facebook);
                    break;
                default:
                    this.mAvatarSource.setVisibility(8);
                    break;
            }
            this.mAvatar.setVisibility(0);
            String string = arguments.getString(ResponseConstants.FIRST_NAME, null);
            if (string == null) {
                textView2.setText(getString(R.string.external_register_header_personalized_nameless));
            } else {
                textView2.setText(getString(R.string.external_register_header_personalized, string));
            }
        } else {
            str = null;
        }
        if (this.mTxtFirstName != null && !TextUtils.isEmpty(arguments.getString(ResponseConstants.FIRST_NAME))) {
            this.mTxtFirstName.setText(arguments.getString(ResponseConstants.FIRST_NAME));
        }
        if (this.mTxtLastName != null && !TextUtils.isEmpty(arguments.getString(ResponseConstants.LAST_NAME))) {
            this.mTxtLastName.setText(arguments.getString(ResponseConstants.LAST_NAME));
        }
        if (this.mTxtEmail != null) {
            setupEmailAddressField(arguments);
        }
        setupUsernameSuggestionListeners();
        this.mAccountId = getArguments().getString("account_id");
        this.mAuthToken = getArguments().getString("auth_token");
        this.mAuthCode = getArguments().getString("auth_code");
        this.mAccountTypeName = getArguments().getString("account_type_name");
        moveFocusToFirstEmptyField();
        ExternalAccountUtil.a("register_view", str);
    }

    private void initializeSocialSignInButtons() {
        int i = 0;
        if (!getArguments().getBoolean("show_social_buttons", false)) {
            i = 8;
        }
        View findViewById = this.mView.findViewById(R.id.register_button_google);
        View findViewById2 = this.mView.findViewById(R.id.register_button_facebook);
        findViewById.setVisibility(i);
        findViewById2.setVisibility(i);
        findViewById.setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                RegisterFragment.this.mLoginRequester.a(AccountType.GOOGLE);
            }
        });
        findViewById2.setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                RegisterFragment.this.mLoginRequester.a(AccountType.FACEBOOK);
            }
        });
    }

    private void setupUsernameSuggestionListeners() {
        EditText[] editTextArr = {this.mTxtFirstName, this.mTxtLastName, this.mTxtEmail};
        int length = editTextArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            EditText editText = editTextArr[i];
            if (editText != null && !TextUtils.isEmpty(editText.getText())) {
                fetchUsernameSuggestions();
                break;
            }
            i++;
        }
        for (EditText onFocusChangeListener : editTextArr) {
            onFocusChangeListener.setOnFocusChangeListener(new d(this));
        }
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void lambda$setupUsernameSuggestionListeners$1$RegisterFragment(View view, boolean z) {
        if (!z) {
            fetchUsernameSuggestions();
        }
    }

    private void setupEmailAddressField(Bundle bundle) {
        if (!TextUtils.isEmpty(bundle.getString("email"))) {
            this.mTxtEmail.setText(bundle.getString("email"));
            return;
        }
        List emailsFromAccountManager = getEmailsFromAccountManager();
        if (emailsFromAccountManager.size() > 1) {
            this.mTxtEmail.setAdapter(new ArrayAdapter(getActivity(), 17367050, emailsFromAccountManager));
            this.mTxtEmail.setOnItemClickListener(new e(this));
        } else if (emailsFromAccountManager.size() == 1) {
            this.mTxtEmail.setText((CharSequence) emailsFromAccountManager.get(0));
            getAnalyticsContext().a("single_email_populated", new HashMap<AnalyticsLogAttribute, Object>() {
                {
                    put(AnalyticsLogAttribute.FLOW_TYPE, RegisterFragment.this.getExternalAccountFlow().toString());
                }
            });
        }
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void lambda$setupEmailAddressField$2$RegisterFragment(AdapterView adapterView, View view, int i, long j) {
        this.mTxtPassword.requestFocus();
        getAnalyticsContext().a("email_picked_autocomplete", new HashMap<AnalyticsLogAttribute, Object>() {
            {
                put(AnalyticsLogAttribute.FLOW_TYPE, RegisterFragment.this.getExternalAccountFlow().toString());
            }
        });
    }

    private void fetchUsernameSuggestions() {
        String obj = this.mTxtFirstName.getText().toString();
        String obj2 = this.mTxtLastName.getText().toString();
        String obj3 = this.mTxtEmail.getText().toString();
        boolean z = false;
        String[] strArr = {obj, obj2, obj3};
        if (!Arrays.deepEquals(this.mPreviousSuggestionParams, strArr)) {
            this.mPreviousSuggestionParams = strArr;
            if ((TextUtils.isEmpty(obj) || TextUtils.isEmpty(obj2)) && TextUtils.isEmpty(obj3)) {
                z = true;
            }
            if (!z) {
                this.disposables.a(this.suggestUsernameEndpoint.getSuggestedUsername(obj3, obj, obj2).b(this.schedulers.a()).a(this.schedulers.b()).a((Consumer<? super T>) new f<Object>(this), (Consumer<? super Throwable>) new g<Object>(this)));
                com.etsy.android.lib.logger.legacy.b.a().b("register", "username suggestion fired");
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void lambda$fetchUsernameSuggestions$3$RegisterFragment(Throwable th) throws Exception {
        f fVar = this.elkLogger;
        StringBuilder sb = new StringBuilder();
        sb.append("Error fetching suggested usernames: ");
        sb.append(th.toString());
        fVar.a(sb.toString());
    }

    public void handleSuggestedUsernameResults(SuggestUsernameResult suggestUsernameResult) {
        List suggestions = suggestUsernameResult.getSuggestions();
        getAnalyticsContext().a("username_suggestion", new HashMap<AnalyticsLogAttribute, Object>() {
            {
                put(AnalyticsLogAttribute.FLOW_TYPE, RegisterFragment.this.getExternalAccountFlow().toString());
            }
        });
        if (suggestions != null && !suggestions.isEmpty()) {
            String str = (String) suggestions.get(0);
            String obj = this.mTxtUsername.getText().toString();
            if (TextUtils.isEmpty(obj) || obj.equals(this.mPreviousSuggestion)) {
                this.mTxtUsername.setText(str);
                this.mUsernameMsg.setVisibility(0);
                this.mPreviousSuggestion = str;
                StringBuilder sb = new StringBuilder();
                sb.append("username suggestion was successfully populated: ");
                sb.append(str);
                com.etsy.android.lib.logger.legacy.b.a().b("register", sb.toString());
                return;
            }
            getAnalyticsContext().a("username_suggestion_too_late", new HashMap<AnalyticsLogAttribute, Object>() {
                {
                    put(AnalyticsLogAttribute.FLOW_TYPE, RegisterFragment.this.getExternalAccountFlow().toString());
                }
            });
            com.etsy.android.lib.logger.legacy.b.a().b("register", "username suggestions took too long");
        }
    }

    private List<String> getEmailsFromAccountManager() {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        Account[] accounts = AccountManager.get(getActivity()).getAccounts();
        HashSet hashSet = new HashSet();
        for (Account account : accounts) {
            if (pattern.matcher(account.name).matches()) {
                hashSet.add(account.name);
            }
        }
        return new ArrayList(hashSet);
    }

    /* JADX WARNING: type inference failed for: r3v7, types: [com.etsy.android.lib.auth.a$b$c] */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void register(java.lang.String r14, java.lang.String r15, java.lang.String r16, java.lang.String r17, java.lang.String r18, java.lang.String r19, java.lang.String r20) {
        /*
            r13 = this;
            r0 = r13
            boolean r1 = android.text.TextUtils.isEmpty(r14)
            r2 = 0
            if (r1 == 0) goto L_0x0016
            android.widget.EditText r1 = r0.mTxtUsername
            r3 = 2131755739(0x7f1002db, float:1.9142366E38)
            java.lang.String r3 = r0.getString(r3)
            r1.setError(r3)
            r1 = r2
            goto L_0x0017
        L_0x0016:
            r1 = 1
        L_0x0017:
            boolean r3 = android.text.TextUtils.isEmpty(r19)
            if (r3 == 0) goto L_0x002a
            android.widget.AutoCompleteTextView r1 = r0.mTxtEmail
            r3 = 2131755715(0x7f1002c3, float:1.9142317E38)
            java.lang.String r3 = r0.getString(r3)
            r1.setError(r3)
            r1 = r2
        L_0x002a:
            boolean r3 = android.text.TextUtils.isEmpty(r15)
            r4 = 2131755729(0x7f1002d1, float:1.9142346E38)
            if (r3 == 0) goto L_0x003d
            android.widget.EditText r1 = r0.mTxtPassword
            java.lang.String r3 = r0.getString(r4)
            r1.setError(r3)
            r1 = r2
        L_0x003d:
            boolean r3 = android.text.TextUtils.isEmpty(r16)
            if (r3 == 0) goto L_0x004e
            android.widget.EditText r1 = r0.mTxtPasswordConfirm
            java.lang.String r3 = r0.getString(r4)
            r1.setError(r3)
            r3 = r2
            goto L_0x004f
        L_0x004e:
            r3 = r1
        L_0x004f:
            if (r15 == 0) goto L_0x0059
            if (r16 == 0) goto L_0x0059
            boolean r4 = r15.equals(r16)
            if (r4 != 0) goto L_0x0066
        L_0x0059:
            android.widget.EditText r3 = r0.mTxtPasswordConfirm
            r4 = 2131755730(0x7f1002d2, float:1.9142348E38)
            java.lang.String r4 = r0.getString(r4)
            r3.setError(r4)
            r3 = r2
        L_0x0066:
            com.etsy.android.lib.util.NetworkUtils r4 = com.etsy.android.lib.util.NetworkUtils.a()
            boolean r4 = r4.b()
            if (r4 != 0) goto L_0x007b
            com.etsy.android.uikit.util.i r3 = r0.mDialogUtil
            android.widget.TextView r4 = r0.mTxtError
            r5 = 2131756507(0x7f1005db, float:1.9143923E38)
            r3.a(r4, r5)
            goto L_0x007c
        L_0x007b:
            r2 = r3
        L_0x007c:
            if (r2 == 0) goto L_0x00ed
            android.os.Bundle r2 = r0.getArguments()
            java.lang.String r3 = "birthday"
            java.lang.String r11 = r2.getString(r3)
            android.os.Bundle r2 = r0.getArguments()
            java.lang.String r3 = "avatar_url"
            java.lang.String r12 = r2.getString(r3)
            com.etsy.android.lib.auth.a$a$b r2 = new com.etsy.android.lib.auth.a$a$b
            r4 = r2
            r5 = r14
            r6 = r15
            r7 = r19
            r8 = r17
            r9 = r18
            r10 = r20
            r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12)
            boolean r1 = r0.linkingAccounts()
            if (r1 != 0) goto L_0x00b7
            com.etsy.android.lib.logger.w r1 = r0.getAnalyticsContext()
            java.lang.String r3 = "register_button_tapped"
            com.etsy.android.ui.user.auth.RegisterFragment$10 r4 = new com.etsy.android.ui.user.auth.RegisterFragment$10
            r4.<init>()
            r1.a(r3, r4)
            goto L_0x00e8
        L_0x00b7:
            java.lang.String r1 = r0.mAuthToken
            boolean r1 = com.etsy.android.lib.util.af.b(r1)
            if (r1 == 0) goto L_0x00c7
            com.etsy.android.lib.auth.external.a$b r1 = new com.etsy.android.lib.auth.external.a$b
            java.lang.String r3 = r0.mAuthToken
            r1.<init>(r3)
            goto L_0x00ce
        L_0x00c7:
            com.etsy.android.lib.auth.external.a$a r1 = new com.etsy.android.lib.auth.external.a$a
            java.lang.String r3 = r0.mAuthCode
            r1.<init>(r3)
        L_0x00ce:
            com.etsy.android.lib.auth.a$b$c r3 = new com.etsy.android.lib.auth.a$b$c
            com.etsy.android.lib.auth.a$a$b r2 = (com.etsy.android.lib.auth.a.C0053a.b) r2
            java.lang.String r4 = r0.mAccountTypeName
            java.lang.String r5 = r0.mAccountId
            r3.<init>(r2, r4, r5, r1)
            com.etsy.android.lib.logger.w r1 = r0.getAnalyticsContext()
            java.lang.String r2 = "register_button_tapped"
            com.etsy.android.ui.user.auth.RegisterFragment$11 r4 = new com.etsy.android.ui.user.auth.RegisterFragment$11
            r4.<init>()
            r1.a(r2, r4)
            r2 = r3
        L_0x00e8:
            com.etsy.android.lib.auth.h r1 = r0.mLoginRequester
            r1.a(r2)
        L_0x00ed:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.etsy.android.ui.user.auth.RegisterFragment.register(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String):void");
    }

    private void moveFocusToFirstEmptyField() {
        if (this.mTxtFirstName != null && TextUtils.isEmpty(this.mTxtFirstName.getText())) {
            this.mTxtFirstName.requestFocus();
        } else if (this.mTxtLastName != null && TextUtils.isEmpty(this.mTxtLastName.getText())) {
            this.mTxtLastName.requestFocus();
        } else if (this.mTxtEmail != null && TextUtils.isEmpty(this.mTxtEmail.getText())) {
            this.mTxtEmail.requestFocus();
        } else if (this.mTxtPassword != null) {
            this.mTxtPassword.requestFocus();
        }
    }

    private boolean linkingAccounts() {
        return (this.mAccountTypeName == null || this.mAccountId == null || (this.mAuthToken == null && this.mAuthCode == null)) ? false : true;
    }

    /* access modifiers changed from: private */
    public SignInFlow getExternalAccountFlow() {
        return linkingAccounts() ? SignInFlow.LINK : SignInFlow.REGULAR;
    }

    public boolean handleBackPressed() {
        final SignInFlow signInFlow = linkingAccounts() ? SignInFlow.LINK : SignInFlow.REGULAR;
        getAnalyticsContext().a("register_aborted", new HashMap<AnalyticsLogAttribute, Object>() {
            {
                put(AnalyticsLogAttribute.FLOW_TYPE, signInFlow.toString());
            }
        });
        return super.handleBackPressed();
    }
}
