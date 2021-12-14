package com.lkorasik.ktistaclient.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lkorasik.ktistaclient.ui.models.PostModel
import com.lkorasik.ktistaclient.ui.TestDataClass
import android.graphics.BitmapFactory

import android.R

import android.provider.MediaStore

import android.content.Intent

import androidx.core.app.ActivityCompat.startActivityForResult

import android.os.Bundle

import android.app.Activity
import android.view.View


class ProfileViewModel : ViewModel() {
    private val mutablePostsData: MutableLiveData<ArrayList<PostModel>> = MutableLiveData()
    val postsData: LiveData<ArrayList<PostModel>> = mutablePostsData

    init {
        testLoadPosts()
    }

    private fun testLoadPosts() {
        mutablePostsData.postValue(TestDataClass.getPostsData())

    }

    private fun loadPosts() {}
}
