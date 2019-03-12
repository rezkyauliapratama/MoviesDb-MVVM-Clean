package id.co.rezkyauliapratama.rk_moviesdb_mvvm.common.cipher

import android.util.Base64
import java.security.Key
import javax.crypto.Cipher

/**
 * This class wraps [Cipher] apis with some additional possibilities.
 *
 * @throws RuntimeException if there is no algorithm defined with [transformation]
 * @throws RuntimeException if there is no padding defined with [transformation]
 */
internal class CipherWrapper(val transformation: String) {

    companion object {
        /**
         * For default created asymmetric keys
         */
        var TRANSFORMATION_ASYMMETRIC = "RSA/ECB/PKCS1Padding"

        /**
         * For default created symmetric keys
         */
        var TRANSFORMATION_SYMMETRIC = "AES/CBC/PKCS7Padding"

    }

    val cipher: Cipher = Cipher.getInstance(transformation)

    /**
     * Encrypts data using the key.
     *
     * @param data the data to encrypt
     * @param key the key to encrypt data with
     */
    fun encrypt(data: String, key: Key?): String {
        cipher.init(Cipher.ENCRYPT_MODE, key)

        val bytes = cipher.doFinal(data.toByteArray())
        return Base64.encodeToString(bytes, Base64.DEFAULT)
    }

    /**
     * Decrypts data using the key.
     *
     * @param data the data to decrypt
     * @param key the key to decrypt data with
     * **/

    fun decrypt(data: String, key: Key?): String {
        val encodedString: String = data

        cipher.init(Cipher.DECRYPT_MODE, key)

        val encryptedData = Base64.decode(encodedString, Base64.DEFAULT)
        val decodedData = cipher.doFinal(encryptedData)
        return String(decodedData)
    }

    /**
     * Wraps(encrypts) a key with another key.
     */
    fun wrapKey(keyToBeWrapped: Key, keyToWrapWith: Key?): String {
        cipher.init(Cipher.WRAP_MODE, keyToWrapWith)
        val decodedData = cipher.wrap(keyToBeWrapped)
        return Base64.encodeToString(decodedData, Base64.DEFAULT)
    }

    /**
     * Unwraps(decrypts) a key with another key. Requires wrapped key algorithm and type.
     */
    fun unWrapKey(wrappedKeyData: String, algorithm: String, wrappedKeyType: Int, keyToUnWrapWith: Key?): Key {
        val encryptedKeyData = Base64.decode(wrappedKeyData, Base64.DEFAULT)
        cipher.init(Cipher.UNWRAP_MODE, keyToUnWrapWith)
        return cipher.unwrap(encryptedKeyData, algorithm, wrappedKeyType)
    }
}
