package me.knox.zmz.ui.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import okio.ByteString;

/**
 * Created by KNOX.
 */

public class MD5Util {

  public static String md5Hex(String s) {
    try {
      MessageDigest messageDigest = MessageDigest.getInstance("MD5");
      byte[] md5bytes = messageDigest.digest(s.getBytes("UTF-8"));
      return ByteString.of(md5bytes).hex();
    } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
      throw new AssertionError(e);
    }
  }
}
