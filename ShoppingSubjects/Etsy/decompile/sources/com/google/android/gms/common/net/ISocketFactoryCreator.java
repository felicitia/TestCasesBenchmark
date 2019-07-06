package com.google.android.gms.common.net;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.stable.a;
import com.google.android.gms.internal.stable.zza;
import com.google.android.gms.internal.stable.zzb;

public interface ISocketFactoryCreator extends IInterface {

    public static abstract class Stub extends zzb implements ISocketFactoryCreator {

        public static class Proxy extends zza implements ISocketFactoryCreator {
            Proxy(IBinder iBinder) {
                super(iBinder, "com.google.android.gms.common.net.ISocketFactoryCreator");
            }

            public IObjectWrapper newSocketFactory(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3, boolean z) throws RemoteException {
                Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                a.a(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper);
                a.a(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper2);
                a.a(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper3);
                a.a(obtainAndWriteInterfaceToken, z);
                Parcel transactAndReadException = transactAndReadException(1, obtainAndWriteInterfaceToken);
                IObjectWrapper asInterface = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(transactAndReadException.readStrongBinder());
                transactAndReadException.recycle();
                return asInterface;
            }

            public IObjectWrapper newSocketFactoryWithCacheDir(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3, String str) throws RemoteException {
                Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                a.a(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper);
                a.a(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper2);
                a.a(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper3);
                obtainAndWriteInterfaceToken.writeString(str);
                Parcel transactAndReadException = transactAndReadException(2, obtainAndWriteInterfaceToken);
                IObjectWrapper asInterface = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(transactAndReadException.readStrongBinder());
                transactAndReadException.recycle();
                return asInterface;
            }
        }

        public Stub() {
            super("com.google.android.gms.common.net.ISocketFactoryCreator");
        }

        public static ISocketFactoryCreator asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.net.ISocketFactoryCreator");
            return queryLocalInterface instanceof ISocketFactoryCreator ? (ISocketFactoryCreator) queryLocalInterface : new Proxy(iBinder);
        }

        /* access modifiers changed from: protected */
        public boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            IObjectWrapper iObjectWrapper;
            switch (i) {
                case 1:
                    iObjectWrapper = newSocketFactory(com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), a.a(parcel));
                    break;
                case 2:
                    iObjectWrapper = newSocketFactoryWithCacheDir(com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                    break;
                default:
                    return false;
            }
            parcel2.writeNoException();
            a.a(parcel2, (IInterface) iObjectWrapper);
            return true;
        }
    }

    IObjectWrapper newSocketFactory(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3, boolean z) throws RemoteException;

    IObjectWrapper newSocketFactoryWithCacheDir(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3, String str) throws RemoteException;
}
