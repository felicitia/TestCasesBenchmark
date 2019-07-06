package com.google.android.gms.signin.internal;

import android.accounts.Account;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.common.internal.AuthAccountRequest;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.IResolveAccountCallbacks;
import com.google.android.gms.common.internal.ResolveAccountRequest;
import com.google.android.gms.internal.stable.a;
import com.google.android.gms.internal.stable.zza;
import com.google.android.gms.internal.stable.zzb;

public interface ISignInService extends IInterface {

    public static abstract class Stub extends zzb implements ISignInService {

        public static class Proxy extends zza implements ISignInService {
            Proxy(IBinder iBinder) {
                super(iBinder, "com.google.android.gms.signin.internal.ISignInService");
            }

            public void authAccount(AuthAccountRequest authAccountRequest, ISignInCallbacks iSignInCallbacks) throws RemoteException {
                Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                a.a(obtainAndWriteInterfaceToken, (Parcelable) authAccountRequest);
                a.a(obtainAndWriteInterfaceToken, (IInterface) iSignInCallbacks);
                transactAndReadExceptionReturnVoid(2, obtainAndWriteInterfaceToken);
            }

            public void clearAccountFromSessionStore(int i) throws RemoteException {
                Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                obtainAndWriteInterfaceToken.writeInt(i);
                transactAndReadExceptionReturnVoid(7, obtainAndWriteInterfaceToken);
            }

            public void getCurrentAccount(ISignInCallbacks iSignInCallbacks) throws RemoteException {
                Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                a.a(obtainAndWriteInterfaceToken, (IInterface) iSignInCallbacks);
                transactAndReadExceptionReturnVoid(11, obtainAndWriteInterfaceToken);
            }

            public void onCheckServerAuthorization(CheckServerAuthResult checkServerAuthResult) throws RemoteException {
                Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                a.a(obtainAndWriteInterfaceToken, (Parcelable) checkServerAuthResult);
                transactAndReadExceptionReturnVoid(3, obtainAndWriteInterfaceToken);
            }

            public void onUploadServerAuthCode(boolean z) throws RemoteException {
                Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                a.a(obtainAndWriteInterfaceToken, z);
                transactAndReadExceptionReturnVoid(4, obtainAndWriteInterfaceToken);
            }

            public void recordConsent(RecordConsentRequest recordConsentRequest, ISignInCallbacks iSignInCallbacks) throws RemoteException {
                Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                a.a(obtainAndWriteInterfaceToken, (Parcelable) recordConsentRequest);
                a.a(obtainAndWriteInterfaceToken, (IInterface) iSignInCallbacks);
                transactAndReadExceptionReturnVoid(10, obtainAndWriteInterfaceToken);
            }

            public void resolveAccount(ResolveAccountRequest resolveAccountRequest, IResolveAccountCallbacks iResolveAccountCallbacks) throws RemoteException {
                Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                a.a(obtainAndWriteInterfaceToken, (Parcelable) resolveAccountRequest);
                a.a(obtainAndWriteInterfaceToken, (IInterface) iResolveAccountCallbacks);
                transactAndReadExceptionReturnVoid(5, obtainAndWriteInterfaceToken);
            }

            public void saveAccountToSessionStore(int i, Account account, ISignInCallbacks iSignInCallbacks) throws RemoteException {
                Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                obtainAndWriteInterfaceToken.writeInt(i);
                a.a(obtainAndWriteInterfaceToken, (Parcelable) account);
                a.a(obtainAndWriteInterfaceToken, (IInterface) iSignInCallbacks);
                transactAndReadExceptionReturnVoid(8, obtainAndWriteInterfaceToken);
            }

            public void saveDefaultAccountToSharedPref(IAccountAccessor iAccountAccessor, int i, boolean z) throws RemoteException {
                Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                a.a(obtainAndWriteInterfaceToken, (IInterface) iAccountAccessor);
                obtainAndWriteInterfaceToken.writeInt(i);
                a.a(obtainAndWriteInterfaceToken, z);
                transactAndReadExceptionReturnVoid(9, obtainAndWriteInterfaceToken);
            }

            public void setGamesHasBeenGreeted(boolean z) throws RemoteException {
                Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                a.a(obtainAndWriteInterfaceToken, z);
                transactAndReadExceptionReturnVoid(13, obtainAndWriteInterfaceToken);
            }

            public void signIn(SignInRequest signInRequest, ISignInCallbacks iSignInCallbacks) throws RemoteException {
                Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                a.a(obtainAndWriteInterfaceToken, (Parcelable) signInRequest);
                a.a(obtainAndWriteInterfaceToken, (IInterface) iSignInCallbacks);
                transactAndReadExceptionReturnVoid(12, obtainAndWriteInterfaceToken);
            }
        }

        public Stub() {
            super("com.google.android.gms.signin.internal.ISignInService");
        }

        public static ISignInService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.signin.internal.ISignInService");
            return queryLocalInterface instanceof ISignInService ? (ISignInService) queryLocalInterface : new Proxy(iBinder);
        }

        /* access modifiers changed from: protected */
        public boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            switch (i) {
                case 2:
                    authAccount((AuthAccountRequest) a.a(parcel, AuthAccountRequest.CREATOR), com.google.android.gms.signin.internal.ISignInCallbacks.Stub.asInterface(parcel.readStrongBinder()));
                    break;
                case 3:
                    onCheckServerAuthorization((CheckServerAuthResult) a.a(parcel, CheckServerAuthResult.CREATOR));
                    break;
                case 4:
                    onUploadServerAuthCode(a.a(parcel));
                    break;
                case 5:
                    resolveAccount((ResolveAccountRequest) a.a(parcel, ResolveAccountRequest.CREATOR), com.google.android.gms.common.internal.IResolveAccountCallbacks.Stub.asInterface(parcel.readStrongBinder()));
                    break;
                case 7:
                    clearAccountFromSessionStore(parcel.readInt());
                    break;
                case 8:
                    saveAccountToSessionStore(parcel.readInt(), (Account) a.a(parcel, Account.CREATOR), com.google.android.gms.signin.internal.ISignInCallbacks.Stub.asInterface(parcel.readStrongBinder()));
                    break;
                case 9:
                    saveDefaultAccountToSharedPref(com.google.android.gms.common.internal.IAccountAccessor.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), a.a(parcel));
                    break;
                case 10:
                    recordConsent((RecordConsentRequest) a.a(parcel, RecordConsentRequest.CREATOR), com.google.android.gms.signin.internal.ISignInCallbacks.Stub.asInterface(parcel.readStrongBinder()));
                    break;
                case 11:
                    getCurrentAccount(com.google.android.gms.signin.internal.ISignInCallbacks.Stub.asInterface(parcel.readStrongBinder()));
                    break;
                case 12:
                    signIn((SignInRequest) a.a(parcel, SignInRequest.CREATOR), com.google.android.gms.signin.internal.ISignInCallbacks.Stub.asInterface(parcel.readStrongBinder()));
                    break;
                case 13:
                    setGamesHasBeenGreeted(a.a(parcel));
                    break;
                default:
                    return false;
            }
            parcel2.writeNoException();
            return true;
        }
    }

    void authAccount(AuthAccountRequest authAccountRequest, ISignInCallbacks iSignInCallbacks) throws RemoteException;

    void clearAccountFromSessionStore(int i) throws RemoteException;

    void getCurrentAccount(ISignInCallbacks iSignInCallbacks) throws RemoteException;

    void onCheckServerAuthorization(CheckServerAuthResult checkServerAuthResult) throws RemoteException;

    void onUploadServerAuthCode(boolean z) throws RemoteException;

    void recordConsent(RecordConsentRequest recordConsentRequest, ISignInCallbacks iSignInCallbacks) throws RemoteException;

    void resolveAccount(ResolveAccountRequest resolveAccountRequest, IResolveAccountCallbacks iResolveAccountCallbacks) throws RemoteException;

    void saveAccountToSessionStore(int i, Account account, ISignInCallbacks iSignInCallbacks) throws RemoteException;

    void saveDefaultAccountToSharedPref(IAccountAccessor iAccountAccessor, int i, boolean z) throws RemoteException;

    void setGamesHasBeenGreeted(boolean z) throws RemoteException;

    void signIn(SignInRequest signInRequest, ISignInCallbacks iSignInCallbacks) throws RemoteException;
}
