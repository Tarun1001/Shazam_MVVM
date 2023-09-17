package com.example.ferrari.Utils.animation

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.example.ferrari.R

class CenterFillBackgroundView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val backgroundColor = ContextCompat.getColor(context, R.color.start_color)
    private val endColor = ContextCompat.getColor(context, R.color.end_color)
    private val animationDuration = 1000L // Duration in milliseconds
    private val paint = Paint()
    private var animationProgress = 0f

    init {
        paint.color = backgroundColor
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val centerX = width / 2
        val centerY = height / 2
        val radius = width.coerceAtLeast(height) * animationProgress
        canvas.drawCircle(centerX.toFloat(), centerY.toFloat(), radius, paint)
    }

    fun startAnimation() {
        val startTime = System.currentTimeMillis()
        post(object : Runnable {
            override fun run() {
                val currentTime = System.currentTimeMillis()
                val elapsedTime = currentTime - startTime
                animationProgress = (elapsedTime.toFloat() / animationDuration).coerceAtMost(1f)

                paint.color = interpolateColor(backgroundColor, endColor, animationProgress)
                invalidate()

                if (animationProgress < 1f) {
                    post(this)
                }
            }
        })
    }

    private fun interpolateColor(startColor: Int, endColor: Int, fraction: Float): Int {
        val red = (Color.red(startColor) * (1 - fraction) + Color.red(endColor) * fraction).toInt()
        val green = (Color.green(startColor) * (1 - fraction) + Color.green(endColor) * fraction).toInt()
        val blue = (Color.blue(startColor) * (1 - fraction) + Color.blue(endColor) * fraction).toInt()
        return Color.rgb(red, green, blue)
    }
}
