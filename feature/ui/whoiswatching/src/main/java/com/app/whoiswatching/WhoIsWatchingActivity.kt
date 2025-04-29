package com.app.whoiswatching

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.Menu
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback

class WhoIsWatchingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        setExitSharedElementCallback(MaterialContainerTransformSharedElementCallback())
        window.sharedElementsUseOverlay = false
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_who_is_watching)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        findViewById<RecyclerView>(R.id.recyclerview).apply {
            adapter = WhoIsWatchingAdapter {
                val intent: Intent = Intent().apply {
                    setClassName(
                        this@WhoIsWatchingActivity,
                        "com.app.main.activity.NetflixMainActivity"
                    )
                }
                val options: ActivityOptionsCompat = ActivityOptionsCompat
                    .makeSceneTransitionAnimation(
                        this@WhoIsWatchingActivity,
                        it,
                        it.transitionName
                    )
                startActivity(intent, options.toBundle())
            }
            addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect,
                    itemPosition: Int,
                    parent: RecyclerView
                ) {
                    outRect.left = 40
                    outRect.right = 40
                    outRect.top = 40
                    outRect.bottom = 40
                }
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_who_is_watching, menu)
        return super.onCreateOptionsMenu(menu)
    }

}