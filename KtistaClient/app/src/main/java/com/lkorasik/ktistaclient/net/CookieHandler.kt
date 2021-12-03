package com.lkorasik.ktistaclient.net

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

class CookieHandler: CookieJar {
    private var cookies: MutableList<Cookie>? = null

    override fun saveFromResponse(url: HttpUrl, cookies: MutableList<Cookie>) {
        if(url.encodedPath().endsWith("registration/")){
            this.cookies = cookies
        }
    }

    override fun loadForRequest(url: HttpUrl): MutableList<Cookie> {
        /*
        if(!url.encodedPath().endsWith("registration") && cookies != null){
            return cookies as MutableList<Cookie>
        }
         */
        return mutableListOf()
    }
}