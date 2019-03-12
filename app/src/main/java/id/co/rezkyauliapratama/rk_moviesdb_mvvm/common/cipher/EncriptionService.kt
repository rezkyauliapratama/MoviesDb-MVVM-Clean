package id.co.rezkyauliapratama.rk_moviesdb_mvvm.common.cipher

import android.util.Base64
import javax.crypto.Cipher
import javax.inject.Inject

class EncriptionService @Inject internal constructor(private val keyStoreWrapper: KeyStoreWrapper) {
    /**
     * The place to keep all constants.
     */
    companion object {
        val DEFAULT_KEY_STORE_NAME = "default_keystore"

        val MASTER_KEY = "MASTER_KEY"

        val KEY_VALIDATION_DATA = byteArrayOf(0, 1, 0, 1)
        val CONFIRM_CREDENTIALS_VALIDATION_DELAY = 120 // Seconds
    }

    /*
       * Encryption Stage
       */

    /**
     * Create and save cryptography key, to protect Secrets with.
     */
    fun createMasterKey(password: String? = null) {
        keyStoreWrapper.createAndroidKeyStoreAsymmetricKey(MASTER_KEY)
    }

    /**
     * Remove master cryptography key. May be used for re sign up functionality.
     */
    fun removeMasterKey() {
        keyStoreWrapper.removeAndroidKeyStoreKey(MASTER_KEY)
    }

    fun getSymmetricPairInformation() : Pair<String,String>{
        val pairInformation = keyStoreWrapper.generateNewSymmetricPair()

        return Pair(encryptAsymetric(pairInformation.first.encodeToString()),
            encryptAsymetric(pairInformation.second.encodeToString()))
    }

    fun encrypt(data: String, pair: Pair<String,String>) : String{
        val pairKeySpec = keyStoreWrapper.getSymmetricKey(decryptAsymetric(pair.first).decodeToByteArray(),
            decryptAsymetric(pair.second).decodeToByteArray())

        val cipher = Cipher.getInstance(CipherWrapper.TRANSFORMATION_SYMMETRIC)
        cipher.init(Cipher.ENCRYPT_MODE, pairKeySpec.first, pairKeySpec.second)

        val encrypted = cipher.doFinal(data.toByteArray())
        return encrypted.encodeToString()
    }

    fun decrypt(encrypted: String, pair: Pair<String,String>): String {
        val pairKeySpec = keyStoreWrapper.getSymmetricKey(decryptAsymetric(pair.first).decodeToByteArray(),
            decryptAsymetric(pair.second).decodeToByteArray())

        val cipher = Cipher.getInstance(CipherWrapper.TRANSFORMATION_SYMMETRIC)
        cipher.init(Cipher.DECRYPT_MODE, pairKeySpec.first, pairKeySpec.second)

        val original = cipher.doFinal(encrypted.decodeToByteArray())
        return original.encodeToString()
    }

    /**
     * Encrypt user Secrets with created master key.
     */
    private fun encryptAsymetric(data: String): String {
        val keyPair = keyStoreWrapper.getAndroidKeyStoreAsymmetricKeyPair(MASTER_KEY)
        CipherWrapper(CipherWrapper.TRANSFORMATION_ASYMMETRIC).apply {
            return encrypt(data,keyPair?.public)
        }
    }

    /**
     * Decrypt user Secrets with created master key.
     */
    private fun decryptAsymetric(data: String): String {
        val keyPair = keyStoreWrapper.getAndroidKeyStoreAsymmetricKeyPair(MASTER_KEY)
        CipherWrapper(CipherWrapper.TRANSFORMATION_ASYMMETRIC).apply {
            return decrypt(data,keyPair?.private)
        }
    }


    private fun String.decodeToByteArray(): ByteArray = Base64.decode(this, Base64.DEFAULT)

    private fun ByteArray.encodeToString(): String = Base64.encodeToString(this, Base64.DEFAULT)
}