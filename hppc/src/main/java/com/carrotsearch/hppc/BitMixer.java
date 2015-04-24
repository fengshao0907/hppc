package com.carrotsearch.hppc;

/**
 * Key hash bit mixing.
 * 
 * {@link #mix0(Object, int)} allows a <code>null</code> key, other methods are used
 * from generated code and are simply delegations. 
 */
public final class BitMixer {
  
  // Don't bother mixing very small key domains much.
  public static int mix (byte key)   { return key * 0x85ebca6b; }
  public static int mix0(byte key)   { return key * 0x85ebca6b; }

  public static int mix (short key)  { int k = key * 0x85ebca6b; return k ^= k >>> 13; }
  public static int mix0(short key)  { int k = key * 0x85ebca6b; return k ^= k >>> 13; }
  public static int mix (char  key)  { int k = key * 0x85ebca6b; return k ^= k >>> 13; }
  public static int mix0(char  key)  { int k = key * 0x85ebca6b; return k ^= k >>> 13; }

  // Do mix larger key domains.
  public static int mix (int key)    { return murmurHash3(key); }
  public static int mix0(int key)    { return murmurHash3(key); }

  public static int mix (float key)  { return murmurHash3(Float.floatToIntBits(key)); }
  public static int mix0(float key)  { return murmurHash3(Float.floatToIntBits(key)); }

  public static int mix (double key) { long v = Double.doubleToLongBits(key); return murmurHash3((int)((v >>> 32) ^ v)); }
  public static int mix0(double key) { long v = Double.doubleToLongBits(key); return murmurHash3((int)((v >>> 32) ^ v)); }

  public static int mix (Object key) { return murmurHash3(key.hashCode()); }
  public static int mix0(Object key) { return key == null ? 0 : murmurHash3(key.hashCode()); }

  public static int mix (byte key, int seed)  { return mix(key ^ seed); }
  public static int mix0(byte key, int seed)  { return mix0(key ^ seed); }

  public static int mix (short key, int seed) { return mix(key ^ seed); }
  public static int mix0(short key, int seed) { return mix0(key ^ seed); }
  public static int mix (char key, int seed)  { return mix(key ^ seed); }
  public static int mix0(char key, int seed)  { return mix0(key ^ seed); }

  public static int mix (int key, int seed)   { return mix0(key ^ seed); }
  public static int mix0(int key, int seed)   { return mix0(key ^ seed); }

  public static int mix (float key, int seed)  { return murmurHash3(Float.floatToIntBits(key) ^ seed); }
  public static int mix0(float key, int seed)  { return murmurHash3(Float.floatToIntBits(key) ^ seed); }

  public static int mix (double key, int seed)  { long v = Double.doubleToLongBits(key); return murmurHash3((int)((v >>> 32) ^ v) ^ seed); }
  public static int mix0(double key, int seed)  { long v = Double.doubleToLongBits(key); return murmurHash3((int)((v >>> 32) ^ v) ^ seed); }

  public static int mix (Object key, int seed)  { return murmurHash3(key.hashCode() ^ seed); }
  public static int mix0(Object key, int seed)  { return key == null ? 0 : murmurHash3(key.hashCode() ^ seed); }

  /*
   * MurmurHash3's finalization step.
   */
  
  public static int murmurHash3(int k) {
    k ^= k >>> 16;
    k *= 0x85ebca6b;
    k ^= k >>> 13;
    k *= 0xc2b2ae35;
    k ^= k >>> 16;
    return k;
  }

  /*
   * Golden ratio bit mixers.
   */

  public static int phiMix(byte k)   { final int h = k * 0x9E3779B9; return h ^ (h >>> 16); }
  public static int phiMix(char k)   { final int h = k * 0x9E3779B9; return h ^ (h >>> 16); }
  public static int phiMix(short k)  { final int h = k * 0x9E3779B9; return h ^ (h >>> 16); }
  public static int phiMix(int k)    { final int h = k * 0x9E3779B9; return h ^ (h >>> 16); }
  public static int phiMix(float k)  { final int h = Float.floatToIntBits(k) * 0x9E3779B9; return h ^ (h >>> 16); }
  public static int phiMix(double k) { final long h = Double.doubleToLongBits(k) * 0x9E3779B97F4A7C15L; return (int) (h ^ (h >>> 32)); }
  public static int phiMix(long k)   { final long h = k * 0x9E3779B97F4A7C15L; return (int) (h ^ (h >>> 32)); }
  public static int phiMix(Object k) { final int h = (k == null ? 0 : k.hashCode() * 0x9E3779B9); return h ^ (h >>> 16); }
}
