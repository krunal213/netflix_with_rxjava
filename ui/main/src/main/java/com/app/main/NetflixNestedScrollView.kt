package com.app.main

import android.content.Context
import android.util.AttributeSet
import androidx.core.widget.NestedScrollView

class NetflixNestedScrollView(context: Context, attrs: AttributeSet?) :
    NestedScrollView(context, attrs), NestedScrollView.OnScrollChangeListener {

    private var previousScrollY = 0.0f
    private var initialScrollViewPosition = 0f
    private var shouldInitialize = true
    private var clipHeight = 0
    private var yLocationOfView = 0f
    private var heightOfView = 0
    private lateinit var l: NetflixOnScrollChangeListener

    fun setNetflixOnScrollChangeListener(l: NetflixOnScrollChangeListener?) {
        viewTreeObserver.addOnGlobalLayoutListener {
            if (l != null) {
                this.l = l
                yLocationOfView = this.l.getY()
                heightOfView = this.l.getHeight()
                setOnScrollChangeListener(this)
            }
        }
    }

    interface NetflixOnScrollChangeListener {
        fun setClipBounds(clipHeight: Int)

        fun scrolledY(Y: Float)

        fun getY(): Float

        fun getHeight(): Int
    }

    override fun onScrollChange(
        v: NestedScrollView,
        scrollX: Int,
        scrollY: Int,
        oldScrollX: Int,
        oldScrollY: Int
    ) {
        val dy = scrollY - oldScrollY
        val isScrollingUp = dy > previousScrollY
        val isVisible = l.getY() >= 0.0f
        val beforeInitialPosition = l.getY() < initialScrollViewPosition
        if (isScrollingUp && isVisible) {
            if (shouldInitialize) {
                initialScrollViewPosition = l.getY()
                shouldInitialize = false
            }
            l.scrolledY(l.getY() - dy)
        } else if (!isScrollingUp) {
            if (beforeInitialPosition) {
                l.scrolledY(
                    (l.getY() - dy).coerceIn(
                        (yLocationOfView - heightOfView),
                        yLocationOfView
                    )
                )
            } else {
                if (!shouldInitialize) {
                    l.scrolledY(initialScrollViewPosition)
                }
            }
        }
        if (dy == 0) {
            clipHeight = 0
            l.setClipBounds(clipHeight)
        } else if (dy > 0) {
            if (l.getHeight() > clipHeight) {
                clipHeight = clipHeight.plus(dy).coerceIn(0, l.getHeight())
                l.setClipBounds(clipHeight)
            }
        } else {
            if (isVisible) {
                if (clipHeight > 0) {
                    clipHeight = clipHeight.plus(dy).coerceIn(0, l.getHeight())
                    l.setClipBounds(clipHeight)
                }
            }
        }
    }

}