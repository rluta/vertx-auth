package io.vertx.ext.auth.impl.hash;

import io.vertx.ext.auth.HashString;
import io.vertx.ext.auth.HashingAlgorithm;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class SHA512 implements HashingAlgorithm {

  private final MessageDigest md;

  public SHA512() {
    try {
      md = MessageDigest.getInstance("SHA-512");
    } catch (NoSuchAlgorithmException nsae) {
      throw new RuntimeException("SHA-512 is not available", nsae);
    }
  }

  @Override
  public String id() {
    return "sha512";
  }

  @Override
  public String hash(HashString hashString, String password) {
    return B64ENC.encodeToString(md.digest(password.getBytes(StandardCharsets.UTF_8)));
  }
}