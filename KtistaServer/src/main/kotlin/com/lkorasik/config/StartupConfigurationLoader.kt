package com.lkorasik.config

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.File
import java.io.FileNotFoundException

class StartupConfigurationLoader {
    companion object{
        private const val filename = "config.json"
        val configuration: StartupConfiguration

        init {
            val file = File(filename)

            if(!file.exists())
                throw FileNotFoundException("You must add $filename before starting the server. You can find json " +
                        "schema in ${StartupConfiguration::class.java.name} data class")

            val data = file.readText(Charsets.UTF_8)

            configuration = Json.decodeFromString(data)
        }
    }
}