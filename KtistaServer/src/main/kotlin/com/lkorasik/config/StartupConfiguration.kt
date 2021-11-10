package com.lkorasik.config

import kotlinx.serialization.Serializable

@Serializable
data class StartupConfiguration(val host: String, val port: Int, val databasePath: String)
