package com.lkorasik.ktistaclient.changepassword

data class PasswordValues(
    val currentPassword: String,
    val newPassword: String,
    val repeatNewPassword: String)