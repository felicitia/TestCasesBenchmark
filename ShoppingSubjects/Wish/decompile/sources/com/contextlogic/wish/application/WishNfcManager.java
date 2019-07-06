package com.contextlogic.wish.application;

import android.content.Intent;
import android.nfc.NfcAdapter.CreateNdefMessageCallback;
import android.nfc.NfcAdapter.OnNdefPushCompleteCallback;

public abstract class WishNfcManager implements CreateNdefMessageCallback, OnNdefPushCompleteCallback {
    public abstract void processNdefIntent(Intent intent);
}
