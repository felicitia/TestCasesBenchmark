package com.android.volley.toolbox;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerFuture;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.android.volley.AuthFailureError;

public class AndroidAuthenticator implements Authenticator {
    private final Account mAccount;
    private final String mAuthTokenType;
    private final Context mContext;
    private final boolean mNotifyAuthFailure;

    public AndroidAuthenticator(Context context, Account account, String str) {
        this(context, account, str, false);
    }

    public AndroidAuthenticator(Context context, Account account, String str, boolean z) {
        this.mContext = context;
        this.mAccount = account;
        this.mAuthTokenType = str;
        this.mNotifyAuthFailure = z;
    }

    public Account getAccount() {
        return this.mAccount;
    }

    public String getAuthToken() throws AuthFailureError {
        AccountManagerFuture authToken = AccountManager.get(this.mContext).getAuthToken(this.mAccount, this.mAuthTokenType, this.mNotifyAuthFailure, null, null);
        try {
            Bundle bundle = (Bundle) authToken.getResult();
            String str = null;
            if (authToken.isDone() && !authToken.isCancelled()) {
                if (bundle.containsKey("intent")) {
                    throw new AuthFailureError((Intent) bundle.getParcelable("intent"));
                }
                str = bundle.getString("authtoken");
            }
            if (str != null) {
                return str;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Got null auth token for type: ");
            sb.append(this.mAuthTokenType);
            throw new AuthFailureError(sb.toString());
        } catch (Exception e) {
            throw new AuthFailureError("Error while retrieving auth token", e);
        }
    }

    public void invalidateAuthToken(String str) {
        AccountManager.get(this.mContext).invalidateAuthToken(this.mAccount.type, str);
    }
}
