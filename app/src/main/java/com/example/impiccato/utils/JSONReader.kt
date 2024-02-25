package com.example.impiccato.utils

import android.content.Context

fun readJSONFromAssets(context: Context, path: String): String {
    return context.assets.open(path).bufferedReader().use { it.readText() }
}