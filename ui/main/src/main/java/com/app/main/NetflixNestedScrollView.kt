package com.app.main

import android.content.Context
import android.util.AttributeSet
import androidx.core.widget.NestedScrollView
import kotlin.math.max
import kotlin.math.min

class NetflixNestedScrollView : NestedScrollView, NestedScrollView.OnScrollChangeListener {
    private var mHeaderDiffTotal = 0
    private var mMinHeaderTranslation = 0
    private var netflixOnScrollChangeListener: NetflixOnScrollChangeListener? = null

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onScrollChange(
        v: NestedScrollView,
        scrollX: Int,
        scrollY: Int,
        oldScrollX: Int,
        oldScrollY: Int
    ) {
        val diff = oldScrollY - scrollY
        mHeaderDiffTotal = if (diff <= 0) {
            max(
                (mHeaderDiffTotal + diff).toDouble(),
                mMinHeaderTranslation.toDouble()
            ).toInt()
        } else {
            min(
                max(
                    (mHeaderDiffTotal + diff).toDouble(),
                    mMinHeaderTranslation.toDouble()
                ), 0.0
            ).toInt()
        }
        netflixOnScrollChangeListener?.setTranslationY(mHeaderDiffTotal)
    }

    fun setNetflixOnScrollChangeListener(netflixOnScrollChangeListener: NetflixOnScrollChangeListener?) {
        if (netflixOnScrollChangeListener != null) {
            viewTreeObserver.addOnGlobalLayoutListener {
                mMinHeaderTranslation = netflixOnScrollChangeListener.minHeaderTranslation
                this@NetflixNestedScrollView.netflixOnScrollChangeListener =
                    netflixOnScrollChangeListener
                this@NetflixNestedScrollView.setOnScrollChangeListener(this@NetflixNestedScrollView)
            }
        }
    }

    interface NetflixOnScrollChangeListener {
        fun setTranslationY(mHeaderDiffTotal: Int)
        val minHeaderTranslation: Int
    }
}
