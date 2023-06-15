package com.dokari4.sekeca.utils

import android.content.Context
import android.widget.Toast
import org.json.JSONArray
import java.io.BufferedReader
import kotlin.math.max
import kotlin.math.min

object Helper {
    private fun getLevenshteinDistance(X: String, Y: String): Int {
        val m = X.length
        val n = Y.length
        val T = Array(m + 1) { IntArray(n + 1) }
        for (i in 1..m) {
            T[i][0] = i
        }
        for (j in 1..n) {
            T[0][j] = j
        }
        var cost: Int
        for (i in 1..m) {
            for (j in 1..n) {
                cost = if (X[i - 1] == Y[j - 1]) 0 else 1
                T[i][j] = min(min(T[i - 1][j] + 1, T[i][j - 1] + 1),
                    T[i - 1][j - 1] + cost)
            }
        }
        return T[m][n]
    }

    fun findSimilarity(x: String?, y: String?): Double {
        require(!(x == null || y == null)) { "Strings should not be null" }

        val maxLength = max(x.length, y.length)
        return if (maxLength > 0) {
            (maxLength * 1.0 - getLevenshteinDistance(x, y)) / maxLength * 1.0
        } else 1.0
    }

    fun createToast(context: Context, msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    //Load JSON Array
    fun loadJSONArray(context: Context, resource: Int): JSONArray {
        //obtain input byte
        val inputStream = context.resources.openRawResource(resource)
        //using Buffered reader to read the inputstream byte
        BufferedReader(inputStream.reader()).use {
            return JSONArray(it.readText())
        }
    }
}