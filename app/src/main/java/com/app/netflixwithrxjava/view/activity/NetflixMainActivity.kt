package com.app.netflixwithrxjava.view.activity

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.updateLayoutParams
import androidx.core.view.updatePadding
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.app.netflixwithrxjava.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class NetflixMainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_netflix)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        navController = findNavController(R.id.fragment_container)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.fragmentHome,
                R.id.fragmentGames,
                R.id.fragmentNewAndHot,
                R.id.nav_graph_my_netflix
            )
        )
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration)
        setSupportActionBar(toolbar)
        val main = findViewById<ConstraintLayout>(R.id.main)
        val fragment = findViewById<View>(R.id.fragment_container)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        ViewCompat.setOnApplyWindowInsetsListener(main) { view, windowInsets ->
            toolbar.setMarginTop(windowInsets.systemWindowInsetTop)
            fragment.updatePadding(
                top = (toolbar.layoutParams.height + windowInsets.systemWindowInsetTop),
                bottom = (bottomNavigationView.layoutParams.height + windowInsets.systemWindowInsetBottom)
            )
            windowInsets
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

    private fun View.setMarginTop(value: Int) = updateLayoutParams<ViewGroup.MarginLayoutParams> {
        topMargin = value
    }

}