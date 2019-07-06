package com.google.ads.conversiontracking;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface q extends IInterface {

    public static abstract class a extends Binder implements q {

        /* renamed from: com.google.ads.conversiontracking.q$a$a reason: collision with other inner class name */
        private static class C0129a implements q {
            private IBinder a;

            C0129a(IBinder iBinder) {
                this.a = iBinder;
            }

            public String a() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                    this.a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String a(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                    obtain.writeString(str);
                    this.a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(String str, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                    obtain.writeString(str);
                    obtain.writeInt(z ? 1 : 0);
                    this.a.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean a(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                    obtain.writeInt(z ? 1 : 0);
                    boolean z2 = false;
                    this.a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z2 = true;
                    }
                    return z2;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.a;
            }
        }

        public static q a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof q)) ? new C0129a(iBinder) : (q) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                boolean z = false;
                switch (i) {
                    case 1:
                        parcel.enforceInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                        String a = a();
                        parcel2.writeNoException();
                        parcel2.writeString(a);
                        return true;
                    case 2:
                        parcel.enforceInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                        if (parcel.readInt() != 0) {
                            z = true;
                        }
                        boolean a2 = a(z);
                        parcel2.writeNoException();
                        parcel2.writeInt(a2 ? 1 : 0);
                        return true;
                    case 3:
                        parcel.enforceInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                        String a3 = a(parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeString(a3);
                        return true;
                    case 4:
                        parcel.enforceInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                        String readString = parcel.readString();
                        if (parcel.readInt() != 0) {
                            z = true;
                        }
                        a(readString, z);
                        parcel2.writeNoException();
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            } else {
                parcel2.writeString("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                return true;
            }
        }
    }

    String a() throws RemoteException;

    String a(String str) throws RemoteException;

    void a(String str, boolean z) throws RemoteException;

    boolean a(boolean z) throws RemoteException;
}
