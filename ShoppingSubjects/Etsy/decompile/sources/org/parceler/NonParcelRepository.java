package org.parceler;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

final class NonParcelRepository implements e<org.parceler.d.b> {
    private static final NonParcelRepository a = new NonParcelRepository();
    private final Map<Class, org.parceler.d.b> b = new HashMap();

    public static final class BooleanArrayParcelable extends ConverterParcelable<boolean[]> {
        private static final org.parceler.a.b CONVERTER = new org.parceler.a.b();
        public static final a CREATOR = new a();

        private static final class a implements Creator<BooleanArrayParcelable> {
            private a() {
            }

            /* renamed from: a */
            public BooleanArrayParcelable createFromParcel(Parcel parcel) {
                return new BooleanArrayParcelable(parcel);
            }

            /* renamed from: a */
            public BooleanArrayParcelable[] newArray(int i) {
                return new BooleanArrayParcelable[i];
            }
        }

        public /* bridge */ /* synthetic */ int describeContents() {
            return super.describeContents();
        }

        public /* bridge */ /* synthetic */ void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
        }

        public BooleanArrayParcelable(Parcel parcel) {
            super(parcel, (f) CONVERTER);
        }

        public BooleanArrayParcelable(boolean[] zArr) {
            super((Object) zArr, (f) CONVERTER);
        }
    }

    public static final class BooleanParcelable extends ConverterParcelable<Boolean> {
        private static final org.parceler.a.k<Boolean> CONVERTER = new org.parceler.a.k<Boolean>() {
            /* renamed from: a */
            public Boolean b(Parcel parcel) {
                return Boolean.valueOf(parcel.createBooleanArray()[0]);
            }

            public void a(Boolean bool, Parcel parcel) {
                parcel.writeBooleanArray(new boolean[]{bool.booleanValue()});
            }
        };
        public static final a CREATOR = new a();

        private static final class a implements Creator<BooleanParcelable> {
            private a() {
            }

            /* renamed from: a */
            public BooleanParcelable createFromParcel(Parcel parcel) {
                return new BooleanParcelable(parcel);
            }

            /* renamed from: a */
            public BooleanParcelable[] newArray(int i) {
                return new BooleanParcelable[i];
            }
        }

        public /* bridge */ /* synthetic */ int describeContents() {
            return super.describeContents();
        }

        public /* bridge */ /* synthetic */ void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
        }

        public BooleanParcelable(Parcel parcel) {
            super(parcel, (f) CONVERTER);
        }

        public BooleanParcelable(boolean z) {
            super((Object) Boolean.valueOf(z), (f) CONVERTER);
        }
    }

    public static final class ByteArrayParcelable extends ConverterParcelable<byte[]> {
        private static final org.parceler.a.k<byte[]> CONVERTER = new org.parceler.a.k<byte[]>() {
            /* renamed from: a */
            public byte[] b(Parcel parcel) {
                return parcel.createByteArray();
            }

            public void a(byte[] bArr, Parcel parcel) {
                parcel.writeByteArray(bArr);
            }
        };
        public static final a CREATOR = new a();

        private static final class a implements Creator<ByteArrayParcelable> {
            private a() {
            }

            /* renamed from: a */
            public ByteArrayParcelable createFromParcel(Parcel parcel) {
                return new ByteArrayParcelable(parcel);
            }

            /* renamed from: a */
            public ByteArrayParcelable[] newArray(int i) {
                return new ByteArrayParcelable[i];
            }
        }

        public /* bridge */ /* synthetic */ int describeContents() {
            return super.describeContents();
        }

        public /* bridge */ /* synthetic */ void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
        }

        public ByteArrayParcelable(Parcel parcel) {
            super(parcel, (f) CONVERTER);
        }

        public ByteArrayParcelable(byte[] bArr) {
            super((Object) bArr, (f) CONVERTER);
        }
    }

    public static final class ByteParcelable extends ConverterParcelable<Byte> {
        private static final org.parceler.a.k<Byte> CONVERTER = new org.parceler.a.k<Byte>() {
            /* renamed from: a */
            public Byte b(Parcel parcel) {
                return Byte.valueOf(parcel.readByte());
            }

            public void a(Byte b, Parcel parcel) {
                parcel.writeByte(b.byteValue());
            }
        };
        public static final a CREATOR = new a();

        private static final class a implements Creator<ByteParcelable> {
            private a() {
            }

            /* renamed from: a */
            public ByteParcelable createFromParcel(Parcel parcel) {
                return new ByteParcelable(parcel);
            }

            /* renamed from: a */
            public ByteParcelable[] newArray(int i) {
                return new ByteParcelable[i];
            }
        }

        public /* bridge */ /* synthetic */ int describeContents() {
            return super.describeContents();
        }

        public /* bridge */ /* synthetic */ void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
        }

        public ByteParcelable(Parcel parcel) {
            super(parcel, (f) CONVERTER);
        }

        public ByteParcelable(Byte b) {
            super((Object) b, (f) CONVERTER);
        }
    }

    public static final class CharArrayParcelable extends ConverterParcelable<char[]> {
        private static final org.parceler.a.c CONVERTER = new org.parceler.a.c();
        public static final a CREATOR = new a();

        private static final class a implements Creator<CharArrayParcelable> {
            private a() {
            }

            /* renamed from: a */
            public CharArrayParcelable createFromParcel(Parcel parcel) {
                return new CharArrayParcelable(parcel);
            }

            /* renamed from: a */
            public CharArrayParcelable[] newArray(int i) {
                return new CharArrayParcelable[i];
            }
        }

        public /* bridge */ /* synthetic */ int describeContents() {
            return super.describeContents();
        }

        public /* bridge */ /* synthetic */ void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
        }

        public CharArrayParcelable(Parcel parcel) {
            super(parcel, (f) CONVERTER);
        }

        public CharArrayParcelable(char[] cArr) {
            super((Object) cArr, (f) CONVERTER);
        }
    }

    public static final class CharacterParcelable extends ConverterParcelable<Character> {
        private static final org.parceler.a.k<Character> CONVERTER = new org.parceler.a.k<Character>() {
            /* renamed from: a */
            public Character b(Parcel parcel) {
                return Character.valueOf(parcel.createCharArray()[0]);
            }

            public void a(Character ch, Parcel parcel) {
                parcel.writeCharArray(new char[]{ch.charValue()});
            }
        };
        public static final a CREATOR = new a();

        private static final class a implements Creator<CharacterParcelable> {
            private a() {
            }

            /* renamed from: a */
            public CharacterParcelable createFromParcel(Parcel parcel) {
                return new CharacterParcelable(parcel);
            }

            /* renamed from: a */
            public CharacterParcelable[] newArray(int i) {
                return new CharacterParcelable[i];
            }
        }

        public /* bridge */ /* synthetic */ int describeContents() {
            return super.describeContents();
        }

        public /* bridge */ /* synthetic */ void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
        }

        public CharacterParcelable(Parcel parcel) {
            super(parcel, (f) CONVERTER);
        }

        public CharacterParcelable(Character ch) {
            super((Object) ch, (f) CONVERTER);
        }
    }

    public static final class CollectionParcelable extends ConverterParcelable<Collection> {
        private static final org.parceler.a.d CONVERTER = new org.parceler.a.a() {
            public Object a(Parcel parcel) {
                return d.a(parcel.readParcelable(CollectionParcelable.class.getClassLoader()));
            }

            public void a(Object obj, Parcel parcel) {
                parcel.writeParcelable(d.a(obj), 0);
            }
        };
        public static final a CREATOR = new a();

        private static final class a implements Creator<CollectionParcelable> {
            private a() {
            }

            /* renamed from: a */
            public CollectionParcelable createFromParcel(Parcel parcel) {
                return new CollectionParcelable(parcel);
            }

            /* renamed from: a */
            public CollectionParcelable[] newArray(int i) {
                return new CollectionParcelable[i];
            }
        }

        public /* bridge */ /* synthetic */ int describeContents() {
            return super.describeContents();
        }

        public /* bridge */ /* synthetic */ void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
        }

        public CollectionParcelable(Parcel parcel) {
            super(parcel, (f) CONVERTER);
        }

        public CollectionParcelable(Collection collection) {
            super((Object) collection, (f) CONVERTER);
        }
    }

    private static class ConverterParcelable<T> implements Parcelable, c<T> {
        private final f<T, T> converter;
        private final T value;

        public int describeContents() {
            return 0;
        }

        private ConverterParcelable(Parcel parcel, f<T, T> fVar) {
            this((T) fVar.fromParcel(parcel), fVar);
        }

        private ConverterParcelable(T t, f<T, T> fVar) {
            this.converter = fVar;
            this.value = t;
        }

        public void writeToParcel(Parcel parcel, int i) {
            this.converter.toParcel(this.value, parcel);
        }

        public T getParcel() {
            return this.value;
        }
    }

    public static final class DoubleParcelable extends ConverterParcelable<Double> {
        private static final org.parceler.a.k<Double> CONVERTER = new org.parceler.a.k<Double>() {
            /* renamed from: a */
            public Double b(Parcel parcel) {
                return Double.valueOf(parcel.readDouble());
            }

            public void a(Double d, Parcel parcel) {
                parcel.writeDouble(d.doubleValue());
            }
        };
        public static final a CREATOR = new a();

        private static final class a implements Creator<DoubleParcelable> {
            private a() {
            }

            /* renamed from: a */
            public DoubleParcelable createFromParcel(Parcel parcel) {
                return new DoubleParcelable(parcel);
            }

            /* renamed from: a */
            public DoubleParcelable[] newArray(int i) {
                return new DoubleParcelable[i];
            }
        }

        public /* bridge */ /* synthetic */ int describeContents() {
            return super.describeContents();
        }

        public /* bridge */ /* synthetic */ void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
        }

        public DoubleParcelable(Parcel parcel) {
            super(parcel, (f) CONVERTER);
        }

        public DoubleParcelable(Double d) {
            super((Object) d, (f) CONVERTER);
        }
    }

    public static final class FloatParcelable extends ConverterParcelable<Float> {
        private static final org.parceler.a.k<Float> CONVERTER = new org.parceler.a.k<Float>() {
            /* renamed from: a */
            public Float b(Parcel parcel) {
                return Float.valueOf(parcel.readFloat());
            }

            public void a(Float f, Parcel parcel) {
                parcel.writeFloat(f.floatValue());
            }
        };
        public static final a CREATOR = new a();

        private static final class a implements Creator<FloatParcelable> {
            private a() {
            }

            /* renamed from: a */
            public FloatParcelable createFromParcel(Parcel parcel) {
                return new FloatParcelable(parcel);
            }

            /* renamed from: a */
            public FloatParcelable[] newArray(int i) {
                return new FloatParcelable[i];
            }
        }

        public /* bridge */ /* synthetic */ int describeContents() {
            return super.describeContents();
        }

        public /* bridge */ /* synthetic */ void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
        }

        public FloatParcelable(Parcel parcel) {
            super(parcel, (f) CONVERTER);
        }

        public FloatParcelable(Float f) {
            super((Object) f, (f) CONVERTER);
        }
    }

    public static final class IBinderParcelable extends ConverterParcelable<IBinder> {
        private static final org.parceler.a.k<IBinder> CONVERTER = new org.parceler.a.k<IBinder>() {
            /* renamed from: a */
            public IBinder b(Parcel parcel) {
                return parcel.readStrongBinder();
            }

            public void a(IBinder iBinder, Parcel parcel) {
                parcel.writeStrongBinder(iBinder);
            }
        };
        public static final a CREATOR = new a();

        private static final class a implements Creator<IBinderParcelable> {
            private a() {
            }

            /* renamed from: a */
            public IBinderParcelable createFromParcel(Parcel parcel) {
                return new IBinderParcelable(parcel);
            }

            /* renamed from: a */
            public IBinderParcelable[] newArray(int i) {
                return new IBinderParcelable[i];
            }
        }

        public /* bridge */ /* synthetic */ int describeContents() {
            return super.describeContents();
        }

        public /* bridge */ /* synthetic */ void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
        }

        public IBinderParcelable(Parcel parcel) {
            super(parcel, (f) CONVERTER);
        }

        public IBinderParcelable(IBinder iBinder) {
            super((Object) iBinder, (f) CONVERTER);
        }
    }

    public static final class IntegerParcelable extends ConverterParcelable<Integer> {
        private static final org.parceler.a.k<Integer> CONVERTER = new org.parceler.a.k<Integer>() {
            /* renamed from: a */
            public Integer b(Parcel parcel) {
                return Integer.valueOf(parcel.readInt());
            }

            public void a(Integer num, Parcel parcel) {
                parcel.writeInt(num.intValue());
            }
        };
        public static final a CREATOR = new a();

        private static final class a implements Creator<IntegerParcelable> {
            private a() {
            }

            /* renamed from: a */
            public IntegerParcelable createFromParcel(Parcel parcel) {
                return new IntegerParcelable(parcel);
            }

            /* renamed from: a */
            public IntegerParcelable[] newArray(int i) {
                return new IntegerParcelable[i];
            }
        }

        public /* bridge */ /* synthetic */ int describeContents() {
            return super.describeContents();
        }

        public /* bridge */ /* synthetic */ void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
        }

        public IntegerParcelable(Parcel parcel) {
            super(parcel, (f) CONVERTER);
        }

        public IntegerParcelable(Integer num) {
            super((Object) num, (f) CONVERTER);
        }
    }

    public static final class LinkedHashMapParcelable extends ConverterParcelable<LinkedHashMap> {
        private static final org.parceler.a.g CONVERTER = new org.parceler.a.g() {
            public void a(Object obj, Parcel parcel) {
                parcel.writeParcelable(d.a(obj), 0);
            }

            public void b(Object obj, Parcel parcel) {
                parcel.writeParcelable(d.a(obj), 0);
            }

            public Object a(Parcel parcel) {
                return d.a(parcel.readParcelable(MapParcelable.class.getClassLoader()));
            }

            public Object b(Parcel parcel) {
                return d.a(parcel.readParcelable(MapParcelable.class.getClassLoader()));
            }
        };
        public static final a CREATOR = new a();

        private static final class a implements Creator<LinkedHashMapParcelable> {
            private a() {
            }

            /* renamed from: a */
            public LinkedHashMapParcelable createFromParcel(Parcel parcel) {
                return new LinkedHashMapParcelable(parcel);
            }

            /* renamed from: a */
            public LinkedHashMapParcelable[] newArray(int i) {
                return new LinkedHashMapParcelable[i];
            }
        }

        public /* bridge */ /* synthetic */ int describeContents() {
            return super.describeContents();
        }

        public /* bridge */ /* synthetic */ void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
        }

        public LinkedHashMapParcelable(Parcel parcel) {
            super(parcel, (f) CONVERTER);
        }

        public LinkedHashMapParcelable(LinkedHashMap linkedHashMap) {
            super((Object) linkedHashMap, (f) CONVERTER);
        }
    }

    public static final class LinkedHashSetParcelable extends ConverterParcelable<LinkedHashSet> {
        private static final org.parceler.a.h CONVERTER = new org.parceler.a.h() {
            public Object a(Parcel parcel) {
                return d.a(parcel.readParcelable(LinkedHashSetParcelable.class.getClassLoader()));
            }

            public void a(Object obj, Parcel parcel) {
                parcel.writeParcelable(d.a(obj), 0);
            }
        };
        public static final a CREATOR = new a();

        private static final class a implements Creator<LinkedHashSetParcelable> {
            private a() {
            }

            /* renamed from: a */
            public LinkedHashSetParcelable createFromParcel(Parcel parcel) {
                return new LinkedHashSetParcelable(parcel);
            }

            /* renamed from: a */
            public LinkedHashSetParcelable[] newArray(int i) {
                return new LinkedHashSetParcelable[i];
            }
        }

        public /* bridge */ /* synthetic */ int describeContents() {
            return super.describeContents();
        }

        public /* bridge */ /* synthetic */ void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
        }

        public LinkedHashSetParcelable(Parcel parcel) {
            super(parcel, (f) CONVERTER);
        }

        public LinkedHashSetParcelable(LinkedHashSet linkedHashSet) {
            super((Object) linkedHashSet, (f) CONVERTER);
        }
    }

    public static final class LinkedListParcelable extends ConverterParcelable<LinkedList> {
        private static final org.parceler.a.i CONVERTER = new org.parceler.a.i() {
            public Object a(Parcel parcel) {
                return d.a(parcel.readParcelable(LinkedListParcelable.class.getClassLoader()));
            }

            public void a(Object obj, Parcel parcel) {
                parcel.writeParcelable(d.a(obj), 0);
            }
        };
        public static final a CREATOR = new a();

        private static final class a implements Creator<LinkedListParcelable> {
            private a() {
            }

            /* renamed from: a */
            public LinkedListParcelable createFromParcel(Parcel parcel) {
                return new LinkedListParcelable(parcel);
            }

            /* renamed from: a */
            public LinkedListParcelable[] newArray(int i) {
                return new LinkedListParcelable[i];
            }
        }

        public /* bridge */ /* synthetic */ int describeContents() {
            return super.describeContents();
        }

        public /* bridge */ /* synthetic */ void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
        }

        public LinkedListParcelable(Parcel parcel) {
            super(parcel, (f) CONVERTER);
        }

        public LinkedListParcelable(LinkedList linkedList) {
            super((Object) linkedList, (f) CONVERTER);
        }
    }

    public static final class ListParcelable extends ConverterParcelable<List> {
        private static final org.parceler.a.a CONVERTER = new org.parceler.a.a() {
            public Object a(Parcel parcel) {
                return d.a(parcel.readParcelable(ListParcelable.class.getClassLoader()));
            }

            public void a(Object obj, Parcel parcel) {
                parcel.writeParcelable(d.a(obj), 0);
            }
        };
        public static final a CREATOR = new a();

        private static final class a implements Creator<ListParcelable> {
            private a() {
            }

            /* renamed from: a */
            public ListParcelable createFromParcel(Parcel parcel) {
                return new ListParcelable(parcel);
            }

            /* renamed from: a */
            public ListParcelable[] newArray(int i) {
                return new ListParcelable[i];
            }
        }

        public /* bridge */ /* synthetic */ int describeContents() {
            return super.describeContents();
        }

        public /* bridge */ /* synthetic */ void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
        }

        public ListParcelable(Parcel parcel) {
            super(parcel, (f) CONVERTER);
        }

        public ListParcelable(List list) {
            super((Object) list, (f) CONVERTER);
        }
    }

    public static final class LongParcelable extends ConverterParcelable<Long> {
        private static final org.parceler.a.k<Long> CONVERTER = new org.parceler.a.k<Long>() {
            /* renamed from: a */
            public Long b(Parcel parcel) {
                return Long.valueOf(parcel.readLong());
            }

            public void a(Long l, Parcel parcel) {
                parcel.writeLong(l.longValue());
            }
        };
        public static final a CREATOR = new a();

        private static final class a implements Creator<LongParcelable> {
            private a() {
            }

            /* renamed from: a */
            public LongParcelable createFromParcel(Parcel parcel) {
                return new LongParcelable(parcel);
            }

            /* renamed from: a */
            public LongParcelable[] newArray(int i) {
                return new LongParcelable[i];
            }
        }

        public /* bridge */ /* synthetic */ int describeContents() {
            return super.describeContents();
        }

        public /* bridge */ /* synthetic */ void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
        }

        public LongParcelable(Parcel parcel) {
            super(parcel, (f) CONVERTER);
        }

        public LongParcelable(Long l) {
            super((Object) l, (f) CONVERTER);
        }
    }

    public static final class MapParcelable extends ConverterParcelable<Map> {
        private static final org.parceler.a.e CONVERTER = new org.parceler.a.e() {
            public void a(Object obj, Parcel parcel) {
                parcel.writeParcelable(d.a(obj), 0);
            }

            public void b(Object obj, Parcel parcel) {
                parcel.writeParcelable(d.a(obj), 0);
            }

            public Object a(Parcel parcel) {
                return d.a(parcel.readParcelable(MapParcelable.class.getClassLoader()));
            }

            public Object b(Parcel parcel) {
                return d.a(parcel.readParcelable(MapParcelable.class.getClassLoader()));
            }
        };
        public static final a CREATOR = new a();

        private static final class a implements Creator<MapParcelable> {
            private a() {
            }

            /* renamed from: a */
            public MapParcelable createFromParcel(Parcel parcel) {
                return new MapParcelable(parcel);
            }

            /* renamed from: a */
            public MapParcelable[] newArray(int i) {
                return new MapParcelable[i];
            }
        }

        public /* bridge */ /* synthetic */ int describeContents() {
            return super.describeContents();
        }

        public /* bridge */ /* synthetic */ void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
        }

        public MapParcelable(Parcel parcel) {
            super(parcel, (f) CONVERTER);
        }

        public MapParcelable(Map map) {
            super((Object) map, (f) CONVERTER);
        }
    }

    public static final class ParcelableParcelable implements Parcelable, c<Parcelable> {
        public static final a CREATOR = new a();
        private Parcelable parcelable;

        private static final class a implements Creator<ParcelableParcelable> {
            private a() {
            }

            /* renamed from: a */
            public ParcelableParcelable createFromParcel(Parcel parcel) {
                return new ParcelableParcelable(parcel);
            }

            /* renamed from: a */
            public ParcelableParcelable[] newArray(int i) {
                return new ParcelableParcelable[i];
            }
        }

        public int describeContents() {
            return 0;
        }

        private ParcelableParcelable(Parcel parcel) {
            this.parcelable = parcel.readParcelable(ParcelableParcelable.class.getClassLoader());
        }

        private ParcelableParcelable(Parcelable parcelable2) {
            this.parcelable = parcelable2;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.parcelable, i);
        }

        public Parcelable getParcel() {
            return this.parcelable;
        }
    }

    public static final class SetParcelable extends ConverterParcelable<Set> {
        private static final org.parceler.a.f CONVERTER = new org.parceler.a.f() {
            public Object a(Parcel parcel) {
                return d.a(parcel.readParcelable(SetParcelable.class.getClassLoader()));
            }

            public void a(Object obj, Parcel parcel) {
                parcel.writeParcelable(d.a(obj), 0);
            }
        };
        public static final a CREATOR = new a();

        private static final class a implements Creator<SetParcelable> {
            private a() {
            }

            /* renamed from: a */
            public SetParcelable createFromParcel(Parcel parcel) {
                return new SetParcelable(parcel);
            }

            /* renamed from: a */
            public SetParcelable[] newArray(int i) {
                return new SetParcelable[i];
            }
        }

        public /* bridge */ /* synthetic */ int describeContents() {
            return super.describeContents();
        }

        public /* bridge */ /* synthetic */ void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
        }

        public SetParcelable(Parcel parcel) {
            super(parcel, (f) CONVERTER);
        }

        public SetParcelable(Set set) {
            super((Object) set, (f) CONVERTER);
        }
    }

    public static final class SparseArrayParcelable extends ConverterParcelable<SparseArray> {
        private static final org.parceler.a.l CONVERTER = new org.parceler.a.l() {
            public Object a(Parcel parcel) {
                return d.a(parcel.readParcelable(SparseArrayParcelable.class.getClassLoader()));
            }

            public void a(Object obj, Parcel parcel) {
                parcel.writeParcelable(d.a(obj), 0);
            }
        };
        public static final a CREATOR = new a();

        private static final class a implements Creator<SparseArrayParcelable> {
            private a() {
            }

            /* renamed from: a */
            public SparseArrayParcelable createFromParcel(Parcel parcel) {
                return new SparseArrayParcelable(parcel);
            }

            /* renamed from: a */
            public SparseArrayParcelable[] newArray(int i) {
                return new SparseArrayParcelable[i];
            }
        }

        public /* bridge */ /* synthetic */ int describeContents() {
            return super.describeContents();
        }

        public /* bridge */ /* synthetic */ void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
        }

        public SparseArrayParcelable(Parcel parcel) {
            super(parcel, (f) CONVERTER);
        }

        public SparseArrayParcelable(SparseArray sparseArray) {
            super((Object) sparseArray, (f) CONVERTER);
        }
    }

    public static final class SparseBooleanArrayParcelable extends ConverterParcelable<SparseBooleanArray> {
        private static final org.parceler.a.k<SparseBooleanArray> CONVERTER = new org.parceler.a.k<SparseBooleanArray>() {
            /* renamed from: a */
            public SparseBooleanArray b(Parcel parcel) {
                return parcel.readSparseBooleanArray();
            }

            public void a(SparseBooleanArray sparseBooleanArray, Parcel parcel) {
                parcel.writeSparseBooleanArray(sparseBooleanArray);
            }
        };
        public static final a CREATOR = new a();

        private static final class a implements Creator<SparseBooleanArrayParcelable> {
            private a() {
            }

            /* renamed from: a */
            public SparseBooleanArrayParcelable createFromParcel(Parcel parcel) {
                return new SparseBooleanArrayParcelable(parcel);
            }

            /* renamed from: a */
            public SparseBooleanArrayParcelable[] newArray(int i) {
                return new SparseBooleanArrayParcelable[i];
            }
        }

        public /* bridge */ /* synthetic */ int describeContents() {
            return super.describeContents();
        }

        public /* bridge */ /* synthetic */ void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
        }

        public SparseBooleanArrayParcelable(Parcel parcel) {
            super(parcel, (f) CONVERTER);
        }

        public SparseBooleanArrayParcelable(SparseBooleanArray sparseBooleanArray) {
            super((Object) sparseBooleanArray, (f) CONVERTER);
        }
    }

    public static final class StringParcelable implements Parcelable, c<String> {
        public static final a CREATOR = new a();
        private String contents;

        private static final class a implements Creator<StringParcelable> {
            private a() {
            }

            /* renamed from: a */
            public StringParcelable createFromParcel(Parcel parcel) {
                return new StringParcelable(parcel);
            }

            /* renamed from: a */
            public StringParcelable[] newArray(int i) {
                return new StringParcelable[i];
            }
        }

        public int describeContents() {
            return 0;
        }

        private StringParcelable(Parcel parcel) {
            this.contents = parcel.readString();
        }

        private StringParcelable(String str) {
            this.contents = str;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.contents);
        }

        public String getParcel() {
            return this.contents;
        }
    }

    public static final class TreeMapParcelable extends ConverterParcelable<Map> {
        private static final org.parceler.a.m CONVERTER = new org.parceler.a.m() {
            public void a(Object obj, Parcel parcel) {
                parcel.writeParcelable(d.a(obj), 0);
            }

            public void b(Object obj, Parcel parcel) {
                parcel.writeParcelable(d.a(obj), 0);
            }

            public Object a(Parcel parcel) {
                return d.a(parcel.readParcelable(MapParcelable.class.getClassLoader()));
            }

            public Object b(Parcel parcel) {
                return d.a(parcel.readParcelable(MapParcelable.class.getClassLoader()));
            }
        };
        public static final a CREATOR = new a();

        private static final class a implements Creator<TreeMapParcelable> {
            private a() {
            }

            /* renamed from: a */
            public TreeMapParcelable createFromParcel(Parcel parcel) {
                return new TreeMapParcelable(parcel);
            }

            /* renamed from: a */
            public TreeMapParcelable[] newArray(int i) {
                return new TreeMapParcelable[i];
            }
        }

        public /* bridge */ /* synthetic */ int describeContents() {
            return super.describeContents();
        }

        public /* bridge */ /* synthetic */ void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
        }

        public TreeMapParcelable(Parcel parcel) {
            super(parcel, (f) CONVERTER);
        }

        public TreeMapParcelable(Map map) {
            super((Object) map, (f) CONVERTER);
        }
    }

    public static final class TreeSetParcelable extends ConverterParcelable<Set> {
        private static final org.parceler.a.n CONVERTER = new org.parceler.a.n() {
            public Object a(Parcel parcel) {
                return d.a(parcel.readParcelable(TreeSetParcelable.class.getClassLoader()));
            }

            public void a(Object obj, Parcel parcel) {
                parcel.writeParcelable(d.a(obj), 0);
            }
        };
        public static final a CREATOR = new a();

        private static final class a implements Creator<TreeSetParcelable> {
            private a() {
            }

            /* renamed from: a */
            public TreeSetParcelable createFromParcel(Parcel parcel) {
                return new TreeSetParcelable(parcel);
            }

            /* renamed from: a */
            public TreeSetParcelable[] newArray(int i) {
                return new TreeSetParcelable[i];
            }
        }

        public /* bridge */ /* synthetic */ int describeContents() {
            return super.describeContents();
        }

        public /* bridge */ /* synthetic */ void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
        }

        public TreeSetParcelable(Parcel parcel) {
            super(parcel, (f) CONVERTER);
        }

        public TreeSetParcelable(Set set) {
            super((Object) set, (f) CONVERTER);
        }
    }

    private static class a implements org.parceler.d.b<boolean[]> {
        private a() {
        }

        public Parcelable a(boolean[] zArr) {
            return new BooleanArrayParcelable(zArr);
        }
    }

    private static class b implements org.parceler.d.b<Boolean> {
        private b() {
        }

        public Parcelable a(Boolean bool) {
            return new BooleanParcelable(bool.booleanValue());
        }
    }

    private static class c implements org.parceler.d.b<Bundle> {
        public Parcelable a(Bundle bundle) {
            return bundle;
        }

        private c() {
        }
    }

    private static class d implements org.parceler.d.b<byte[]> {
        private d() {
        }

        public Parcelable a(byte[] bArr) {
            return new ByteArrayParcelable(bArr);
        }
    }

    private static class e implements org.parceler.d.b<Byte> {
        private e() {
        }

        public Parcelable a(Byte b) {
            return new ByteParcelable(b);
        }
    }

    private static class f implements org.parceler.d.b<char[]> {
        private f() {
        }

        public Parcelable a(char[] cArr) {
            return new CharArrayParcelable(cArr);
        }
    }

    private static class g implements org.parceler.d.b<Character> {
        private g() {
        }

        public Parcelable a(Character ch) {
            return new CharacterParcelable(ch);
        }
    }

    private static class h implements org.parceler.d.b<Collection> {
        private h() {
        }

        public Parcelable a(Collection collection) {
            return new CollectionParcelable(collection);
        }
    }

    private static class i implements org.parceler.d.b<Double> {
        private i() {
        }

        public Parcelable a(Double d) {
            return new DoubleParcelable(d);
        }
    }

    private static class j implements org.parceler.d.b<Float> {
        private j() {
        }

        public Parcelable a(Float f) {
            return new FloatParcelable(f);
        }
    }

    private static class k implements org.parceler.d.b<IBinder> {
        private k() {
        }

        public Parcelable a(IBinder iBinder) {
            return new IBinderParcelable(iBinder);
        }
    }

    private static class l implements org.parceler.d.b<Integer> {
        private l() {
        }

        public Parcelable a(Integer num) {
            return new IntegerParcelable(num);
        }
    }

    private static class m implements org.parceler.d.b<LinkedHashMap> {
        private m() {
        }

        public Parcelable a(LinkedHashMap linkedHashMap) {
            return new LinkedHashMapParcelable(linkedHashMap);
        }
    }

    private static class n implements org.parceler.d.b<LinkedHashSet> {
        private n() {
        }

        public Parcelable a(LinkedHashSet linkedHashSet) {
            return new LinkedHashSetParcelable(linkedHashSet);
        }
    }

    private static class o implements org.parceler.d.b<LinkedList> {
        private o() {
        }

        public Parcelable a(LinkedList linkedList) {
            return new LinkedListParcelable(linkedList);
        }
    }

    private static class p implements org.parceler.d.b<List> {
        private p() {
        }

        public Parcelable a(List list) {
            return new ListParcelable(list);
        }
    }

    private static class q implements org.parceler.d.b<Long> {
        private q() {
        }

        public Parcelable a(Long l) {
            return new LongParcelable(l);
        }
    }

    private static class r implements org.parceler.d.b<Map> {
        private r() {
        }

        public Parcelable a(Map map) {
            return new MapParcelable(map);
        }
    }

    static class s implements org.parceler.d.b<Parcelable> {
        s() {
        }

        public Parcelable a(Parcelable parcelable) {
            return new ParcelableParcelable(parcelable);
        }
    }

    private static class t implements org.parceler.d.b<Set> {
        private t() {
        }

        public Parcelable a(Set set) {
            return new SetParcelable(set);
        }
    }

    private static class u implements org.parceler.d.b<SparseArray> {
        private u() {
        }

        public Parcelable a(SparseArray sparseArray) {
            return new SparseArrayParcelable(sparseArray);
        }
    }

    private static class v implements org.parceler.d.b<SparseBooleanArray> {
        private v() {
        }

        public Parcelable a(SparseBooleanArray sparseBooleanArray) {
            return new SparseBooleanArrayParcelable(sparseBooleanArray);
        }
    }

    private static class w implements org.parceler.d.b<String> {
        private w() {
        }

        public Parcelable a(String str) {
            return new StringParcelable(str);
        }
    }

    private static class x implements org.parceler.d.b<Map> {
        private x() {
        }

        public Parcelable a(Map map) {
            return new TreeMapParcelable(map);
        }
    }

    private static class y implements org.parceler.d.b<Set> {
        private y() {
        }

        public Parcelable a(Set set) {
            return new TreeSetParcelable(set);
        }
    }

    private NonParcelRepository() {
        this.b.put(Collection.class, new h());
        this.b.put(List.class, new p());
        this.b.put(ArrayList.class, new p());
        this.b.put(Set.class, new t());
        this.b.put(HashSet.class, new t());
        this.b.put(TreeSet.class, new y());
        this.b.put(SparseArray.class, new u());
        this.b.put(Map.class, new r());
        this.b.put(HashMap.class, new r());
        this.b.put(TreeMap.class, new x());
        this.b.put(Integer.class, new l());
        this.b.put(Long.class, new q());
        this.b.put(Double.class, new i());
        this.b.put(Float.class, new j());
        this.b.put(Byte.class, new e());
        this.b.put(String.class, new w());
        this.b.put(Character.class, new g());
        this.b.put(Boolean.class, new b());
        this.b.put(byte[].class, new d());
        this.b.put(char[].class, new f());
        this.b.put(boolean[].class, new a());
        this.b.put(IBinder.class, new k());
        this.b.put(Bundle.class, new c());
        this.b.put(SparseBooleanArray.class, new v());
        this.b.put(LinkedList.class, new o());
        this.b.put(LinkedHashMap.class, new m());
        this.b.put(SortedMap.class, new x());
        this.b.put(SortedSet.class, new y());
        this.b.put(LinkedHashSet.class, new n());
    }

    public static NonParcelRepository a() {
        return a;
    }

    public Map<Class, org.parceler.d.b> b() {
        return this.b;
    }
}
