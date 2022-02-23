package com.lkorasik.ktistaclient.ui.feed.addPost

import android.content.Context
import android.graphics.*
import android.util.AttributeSet


class GradientTextView(context: Context, attrs: AttributeSet?, defStyle: Int): androidx.appcompat.widget.AppCompatTextView(context, attrs, defStyle) {
    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, -1)
    constructor(context: Context) : this(context, null, -1)

    var startColor: Int = 0
    var endColor: Int = 0

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        if (changed) {
            paint.shader = LinearGradient(width.toFloat(), height.toFloat(),0f, 0f,  startColor, endColor, Shader.TileMode.CLAMP)
        }
    }
}
