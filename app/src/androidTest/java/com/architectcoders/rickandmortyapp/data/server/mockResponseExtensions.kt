package com.architectcoders.rickandmortyapp.data.server

import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import okhttp3.mockwebserver.MockResponse
import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets

fun MockResponse.fromJson(fileName: String): MockResponse =
    setBody(readJsonFromFile(fileName))

private fun readJsonFromFile(path: String): String {
    val context = InstrumentationRegistry.getInstrumentation().context

    var bufferedReader: BufferedReader? = null
    try {
        bufferedReader = BufferedReader(
            InputStreamReader(
                context.assets.open(
                    path
                ), StandardCharsets.UTF_8
            )
        )

        var line: String?
        val text = StringBuilder()

        while (bufferedReader.readLine().also { line = it } != null) {
            text.append(line)
        }
        bufferedReader.close()
        Log.d("MockResponse", text.toString())
        return text.toString()
    } finally {
        bufferedReader?.close()
    }
}
