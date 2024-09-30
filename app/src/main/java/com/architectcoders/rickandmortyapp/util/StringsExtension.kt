package com.architectcoders.rickandmortyapp.util

fun String?.checkNull(defaultValue: String = "") = this ?: defaultValue