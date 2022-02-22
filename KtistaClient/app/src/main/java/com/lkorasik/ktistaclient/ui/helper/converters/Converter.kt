package com.lkorasik.ktistaclient.ui.helper.converters

interface Converter<T, R>{
    fun convert(input: T): R
}
