package com.tuananh.app.spotiutils.util

import android.content.Context
import androidx.security.crypto.EncryptedFile
import androidx.security.crypto.MasterKey
import androidx.security.crypto.MasterKeys
import com.tuananh.app.spotiutils.ui.App
import java.io.*
import java.nio.charset.StandardCharsets

object MyEncryptedSharedPrefs {

    @Synchronized
    fun writeFile(fileName: String, o: Any) {
        App.getAppContext()?.let {
            val masterKey = MasterKey.Builder(it)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build()

            val fileToWrite = fileName
            val myFile = File(it.filesDir, fileToWrite)
            if(myFile.exists()) {
                deleteFile(fileName)
            }
            val encryptedFile = EncryptedFile.Builder(
                it,
                myFile,
                masterKey,
                EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
            ).build()

            val oos = ObjectOutputStream(encryptedFile.openFileOutput())
            oos.writeObject(o)
            oos.close()
        }
    }

    @Synchronized
    fun readFile(fileName: String): Any? {
        App.getAppContext()?.let {
            val masterKey = MasterKey.Builder(it)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build()

            val fileToRead = fileName
            val encryptedFile = EncryptedFile.Builder(
                it,
                File(it.filesDir, fileToRead),
                masterKey,
                EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
            ).build()

            val inputStream = encryptedFile.openFileInput()
            val ois = ObjectInputStream(inputStream)
            val myObject = ois.readObject()
            return myObject
        }
        return null
    }

    fun deleteFile(fileName: String) {
        File(fileName).delete()
    }

}