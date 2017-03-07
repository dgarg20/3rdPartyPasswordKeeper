package com.dgarg20.encrypt_decrypt

import java.security.MessageDigest
import java.util._
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec
import java.util.Base64


/**
  * Created by deepanshu.garg@yatraonline.local on 28/2/17.
  */
class EncryptDecrypt {


  def encrypt(key: String, value: String): String = {
    val cipher: Cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
    cipher.init(Cipher.ENCRYPT_MODE, keyToSpec(key))
    new String(Base64.getEncoder.encode(cipher.doFinal(value.getBytes("UTF-8"))))
  }

  def decrypt(key: String, encryptedValue: String): String = {
    val cipher: Cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING")
    cipher.init(Cipher.DECRYPT_MODE, keyToSpec(key))
    new String(cipher.doFinal(Base64.getDecoder.decode(encryptedValue)))
  }

  def keyToSpec(key: String): SecretKeySpec = {
    var keyBytes: Array[Byte] = (key).getBytes("UTF-8")
    val sha: MessageDigest = MessageDigest.getInstance("SHA-256")
    keyBytes = sha.digest(keyBytes)
    keyBytes = Arrays.copyOf(keyBytes, 16)
    new SecretKeySpec(keyBytes, "AES")
  }
}