package com.google.android.gms.signin.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.internal.stable.a;
import com.google.android.gms.internal.stable.zza;
import com.google.android.gms.internal.stable.zzb;
import java.util.List;

public interface IOfflineAccessCallbacks extends IInterface {

    public static abstract class Stub extends zzb implements IOfflineAccessCallbacks {

        public static class Proxy extends zza implements IOfflineAccessCallbacks {
            Proxy(IBinder iBinder) {
                super(iBinder, "com.google.android.gms.signin.internal.IOfflineAccessCallbacks");
            }

            public void checkServerAuthorization(String str, List<Scope> list, ISignInService iSignInService) throws RemoteException {
                Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                obtainAndWriteInterfaceToken.writeString(str);
                obtainAndWriteInterfaceToken.writeTypedList(list);
                a.a(obtainAndWriteInterfaceToken, (IInterface) iSignInService);
                transactAndReadExceptionReturnVoid(2, obtainAndWriteInterfaceToken);
            }

            public void uploadServerAuthCode(String str, String str2, ISignInService iSignInService) throws RemoteException {
                Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                obtainAndWriteInterfaceToken.writeString(str);
                obtainAndWriteInterfaceToken.writeString(str2);
                a.a(obtainAndWriteInterfaceToken, (IInterface) iSignInService);
                transactAndReadExceptionReturnVoid(3, obtainAndWriteInterfaceToken);
            }
        }

        public Stub() {
            super("com.google.android.gms.signin.internal.IOfflineAccessCallbacks");
        }

        public static IOfflineAccessCallbacks asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.signin.internal.IOfflineAccessCallbacks");
            return queryLocalInterface instanceof IOfflineAccessCallbacks ? (IOfflineAccessCallbacks) queryLocalInterface : new Proxy(iBinder);
        }

        /* access modifiers changed from: protected */
        public boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            switch (i) {
                case 2:
                    checkServerAuthorization(parcel.readString(), parcel.createTypedArrayList(Scope.CREATOR), com.google.android.gms.signin.internal.ISignInService.Stub.asInterface(parcel.readStrongBinder()));
                    break;
                case 3:
                    uploadServerAuthCode(parcel.readString(), parcel.readString(), com.google.android.gms.signin.internal.ISignInService.Stub.asInterface(parcel.readStrongBinder()));
                    break;
                default:
                    return false;
            }
            parcel2.writeNoException();
            return true;
        }
    }

    void checkServerAuthorization(String str, List<Scope> list, ISignInService iSignInService) throws RemoteException;

    void uploadServerAuthCode(String str, String str2, ISignInService iSignInService) throws RemoteException;
}
