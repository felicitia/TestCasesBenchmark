package com.stripe.android;

import com.stripe.android.compat.AsyncTask;
import com.stripe.android.model.Card;
import com.stripe.android.util.TextUtils;
import com.stripe.exception.AuthenticationException;
import com.stripe.model.Token;
import com.stripe.net.RequestOptions;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Executor;

public class Stripe {
    private String defaultPublishableKey;
    public TokenCreator tokenCreator = new TokenCreator() {
        public void create(final Card card, final String str, Executor executor, final TokenCallback tokenCallback) {
            Stripe.this.executeTokenTask(executor, new AsyncTask<Void, Void, ResponseWrapper>() {
                /* access modifiers changed from: protected */
                public ResponseWrapper doInBackground(Void... voidArr) {
                    try {
                        Token create = Token.create(Stripe.this.hashMapFromCard(card), RequestOptions.builder().setApiKey(str).build());
                        return new ResponseWrapper(Stripe.this.androidTokenFromStripeToken(Stripe.this.androidCardFromStripeCard(create.getCard()), create), null);
                    } catch (Exception e) {
                        return new ResponseWrapper(null, e);
                    }
                }

                /* access modifiers changed from: protected */
                public void onPostExecute(ResponseWrapper responseWrapper) {
                    Stripe.this.tokenTaskPostExecution(responseWrapper, tokenCallback);
                }
            });
        }
    };
    public TokenRequester tokenRequester = new TokenRequester() {
    };

    private class ResponseWrapper {
        public final Exception error;
        public final com.stripe.android.model.Token token;

        private ResponseWrapper(com.stripe.android.model.Token token2, Exception exc) {
            this.error = exc;
            this.token = token2;
        }
    }

    interface TokenCreator {
        void create(Card card, String str, Executor executor, TokenCallback tokenCallback);
    }

    interface TokenRequester {
    }

    public Stripe() {
    }

    public Stripe(String str) throws AuthenticationException {
        setDefaultPublishableKey(str);
    }

    public void setDefaultPublishableKey(String str) throws AuthenticationException {
        validateKey(str);
        this.defaultPublishableKey = str;
    }

    private void validateKey(String str) throws AuthenticationException {
        if (str == null || str.length() == 0) {
            throw new AuthenticationException("Invalid Publishable Key: You must use a valid publishable key to create a token.  For more info, see https://stripe.com/docs/stripe.js.", null, Integer.valueOf(0));
        } else if (str.startsWith("sk_")) {
            throw new AuthenticationException("Invalid Publishable Key: You are using a secret key to create a token, instead of the publishable one. For more info, see https://stripe.com/docs/stripe.js", null, Integer.valueOf(0));
        }
    }

    public void createToken(Card card, String str, TokenCallback tokenCallback) {
        createToken(card, str, null, tokenCallback);
    }

    public void createToken(Card card, TokenCallback tokenCallback) {
        createToken(card, this.defaultPublishableKey, tokenCallback);
    }

    public void createToken(Card card, String str, Executor executor, TokenCallback tokenCallback) {
        if (card == null) {
            try {
                throw new RuntimeException("Required Parameter: 'card' is required to create a token");
            } catch (AuthenticationException e) {
                tokenCallback.onError(e);
            }
        } else if (tokenCallback == null) {
            throw new RuntimeException("Required Parameter: 'callback' is required to use the created token and handle errors");
        } else {
            validateKey(str);
            this.tokenCreator.create(card, str, executor, tokenCallback);
        }
    }

    /* access modifiers changed from: private */
    public Card androidCardFromStripeCard(com.stripe.model.Card card) {
        Card card2 = new Card(null, card.getExpMonth(), card.getExpYear(), null, card.getName(), card.getAddressLine1(), card.getAddressLine2(), card.getAddressCity(), card.getAddressState(), card.getAddressZip(), card.getAddressCountry(), card.getLast4(), card.getType(), card.getFingerprint(), card.getCountry());
        return card2;
    }

    /* access modifiers changed from: private */
    public com.stripe.android.model.Token androidTokenFromStripeToken(Card card, Token token) {
        com.stripe.android.model.Token token2 = new com.stripe.android.model.Token(token.getId(), token.getLivemode().booleanValue(), new Date(token.getCreated().longValue() * 1000), token.getUsed(), card);
        return token2;
    }

    /* access modifiers changed from: private */
    public void tokenTaskPostExecution(ResponseWrapper responseWrapper, TokenCallback tokenCallback) {
        if (responseWrapper.token != null) {
            tokenCallback.onSuccess(responseWrapper.token);
        } else if (responseWrapper.error != null) {
            tokenCallback.onError(responseWrapper.error);
        } else {
            tokenCallback.onError(new RuntimeException("Somehow got neither a token response or an error response"));
        }
    }

    /* access modifiers changed from: private */
    public void executeTokenTask(Executor executor, AsyncTask<Void, Void, ResponseWrapper> asyncTask) {
        if (executor != null) {
            asyncTask.executeOnExecutor(executor, new Void[0]);
        } else {
            asyncTask.execute(new Void[0]);
        }
    }

    /* access modifiers changed from: private */
    public Map<String, Object> hashMapFromCard(Card card) {
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        hashMap2.put("number", TextUtils.nullIfBlank(card.getNumber()));
        hashMap2.put("cvc", TextUtils.nullIfBlank(card.getCVC()));
        hashMap2.put("exp_month", card.getExpMonth());
        hashMap2.put("exp_year", card.getExpYear());
        hashMap2.put("name", TextUtils.nullIfBlank(card.getName()));
        hashMap2.put("currency", TextUtils.nullIfBlank(card.getCurrency()));
        hashMap2.put("address_line1", TextUtils.nullIfBlank(card.getAddressLine1()));
        hashMap2.put("address_line2", TextUtils.nullIfBlank(card.getAddressLine2()));
        hashMap2.put("address_city", TextUtils.nullIfBlank(card.getAddressCity()));
        hashMap2.put("address_zip", TextUtils.nullIfBlank(card.getAddressZip()));
        hashMap2.put("address_state", TextUtils.nullIfBlank(card.getAddressState()));
        hashMap2.put("address_country", TextUtils.nullIfBlank(card.getAddressCountry()));
        Iterator it = new HashSet(hashMap2.keySet()).iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (hashMap2.get(str) == null) {
                hashMap2.remove(str);
            }
        }
        hashMap.put("card", hashMap2);
        return hashMap;
    }
}
