package com.google.android.gms.signin.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.stable.a;
import com.google.android.gms.internal.stable.zza;
import com.google.android.gms.internal.stable.zzb;

public interface ISignInCallbacks extends IInterface {

    public static abstract class Stub extends zzb implements ISignInCallbacks {

        public static class Proxy extends zza implements ISignInCallbacks {
            Proxy(IBinder iBinder) {
                super(iBinder, "com.google.android.gms.signin.internal.ISignInCallbacks");
            }

            public void onAuthAccountComplete(ConnectionResult connectionResult, AuthAccountResult authAccountResult) throws RemoteException {
                Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                a.a(obtainAndWriteInterfaceToken, (Parcelable) connectionResult);
                a.a(obtainAndWriteInterfaceToken, (Parcelable) authAccountResult);
                transactAndReadExceptionReturnVoid(3, obtainAndWriteInterfaceToken);
            }

            public void onGetCurrentAccountComplete(Status status, GoogleSignInAccount googleSignInAccount) throws RemoteException {
                Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                a.a(obtainAndWriteInterfaceToken, (Parcelable) status);
                a.a(obtainAndWriteInterfaceToken, (Parcelable) googleSignInAccount);
                transactAndReadExceptionReturnVoid(7, obtainAndWriteInterfaceToken);
            }

            public void onRecordConsentComplete(Status status) throws RemoteException {
                Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                a.a(obtainAndWriteInterfaceToken, (Parcelable) status);
                transactAndReadExceptionReturnVoid(6, obtainAndWriteInterfaceToken);
            }

            public void onSaveAccountToSessionStoreComplete(Status status) throws RemoteException {
                Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                a.a(obtainAndWriteInterfaceToken, (Parcelable) status);
                transactAndReadExceptionReturnVoid(4, obtainAndWriteInterfaceToken);
            }

            public void onSignInComplete(SignInResponse signInResponse) throws RemoteException {
                Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                a.a(obtainAndWriteInterfaceToken, (Parcelable) signInResponse);
                transactAndReadExceptionReturnVoid(8, obtainAndWriteInterfaceToken);
            }
        }

        public Stub() {
            super("com.google.android.gms.signin.internal.ISignInCallbacks");
        }

        public static ISignInCallbacks asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.signin.internal.ISignInCallbacks");
            return queryLocalInterface instanceof ISignInCallbacks ? (ISignInCallbacks) queryLocalInterface : new Proxy(iBinder);
        }

        /* access modifiers changed from: protected */
        public boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            switch (i) {
                case 3:
                    onAuthAccountComplete((ConnectionResult) a.a(parcel, ConnectionResult.CREATOR), (AuthAccountResult) a.a(parcel, AuthAccountResult.CREATOR));
                    break;
                case 4:
                    onSaveAccountToSessionStoreComplete((Status) a.a(parcel, Status.CREATOR));
                    break;
                case 6:
                    onRecordConsentComplete((Status) a.a(parcel, Status.CREATOR));
                    break;
                case 7:
                    onGetCurrentAccountComplete((Status) a.a(parcel, Status.CREATOR), (GoogleSignInAccount) a.a(parcel, GoogleSignInAccount.CREATOR));
                    break;
                case 8:
                    onSignInComplete((SignInResponse) a.a(parcel, SignInResponse.CREATOR));
                    break;
                default:
                    return false;
            }
            parcel2.writeNoException();
            return true;
        }
    }

    void onAuthAccountComplete(ConnectionResult connectionResult, AuthAccountResult authAccountResult) throws RemoteException;

    void onGetCurrentAccountComplete(Status status, GoogleSignInAccount googleSignInAccount) throws RemoteException;

    void onRecordConsentComplete(Status status) throws RemoteException;

    void onSaveAccountToSessionStoreComplete(Status status) throws RemoteException;

    void onSignInComplete(SignInResponse signInResponse) throws RemoteException;
}
