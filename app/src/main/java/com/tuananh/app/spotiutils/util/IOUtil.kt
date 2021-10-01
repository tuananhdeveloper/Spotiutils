package com.tuananh.app.spotiutils.util

import java.io.*
import java.lang.StringBuilder

object IOUtil {

    fun convertToStringBuilder(inputStream: InputStream): StringBuilder {
        val br = BufferedReader(InputStreamReader(inputStream))
        val stringBuilder = StringBuilder()
        br.forEachLine {
            stringBuilder.append(it)
        }
        inputStream.close()
        return stringBuilder
    }

    fun writeData(data: String, outputStream: OutputStream) {
        val outputStreamWriter = OutputStreamWriter(outputStream)
        outputStreamWriter.write(data)
        outputStreamWriter.close()
        outputStream.close()
    }

}