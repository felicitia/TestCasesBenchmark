package com.google.android.gms.internal.firebase-perf;

import java.io.IOException;
import java.util.List;
import java.util.Map;

interface zzei {
    int getTag();

    double readDouble() throws IOException;

    float readFloat() throws IOException;

    String readString() throws IOException;

    void readStringList(List<String> list) throws IOException;

    <T> T zza(zzej<T> zzej, zzbz zzbz) throws IOException;

    <T> void zza(List<T> list, zzej<T> zzej, zzbz zzbz) throws IOException;

    <K, V> void zza(Map<K, V> map, zzdm<K, V> zzdm, zzbz zzbz) throws IOException;

    @Deprecated
    <T> T zzb(zzej<T> zzej, zzbz zzbz) throws IOException;

    void zzb(List<Double> list) throws IOException;

    @Deprecated
    <T> void zzb(List<T> list, zzej<T> zzej, zzbz zzbz) throws IOException;

    long zzbt() throws IOException;

    long zzbu() throws IOException;

    int zzbv() throws IOException;

    long zzbw() throws IOException;

    int zzbx() throws IOException;

    boolean zzby() throws IOException;

    String zzbz() throws IOException;

    void zzc(List<Float> list) throws IOException;

    zzbd zzca() throws IOException;

    int zzcb() throws IOException;

    int zzcc() throws IOException;

    int zzcd() throws IOException;

    long zzce() throws IOException;

    int zzcf() throws IOException;

    long zzcg() throws IOException;

    int zzcq() throws IOException;

    boolean zzcr() throws IOException;

    void zzd(List<Long> list) throws IOException;

    void zze(List<Long> list) throws IOException;

    void zzf(List<Integer> list) throws IOException;

    void zzg(List<Long> list) throws IOException;

    void zzh(List<Integer> list) throws IOException;

    void zzi(List<Boolean> list) throws IOException;

    void zzj(List<String> list) throws IOException;

    void zzk(List<zzbd> list) throws IOException;

    void zzl(List<Integer> list) throws IOException;

    void zzm(List<Integer> list) throws IOException;

    void zzn(List<Integer> list) throws IOException;

    void zzo(List<Long> list) throws IOException;

    void zzp(List<Integer> list) throws IOException;

    void zzq(List<Long> list) throws IOException;
}
