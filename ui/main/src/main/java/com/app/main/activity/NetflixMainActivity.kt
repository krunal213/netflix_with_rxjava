package com.app.main.activity

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.updateLayoutParams
import androidx.core.view.updatePadding
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.app.main.NetflixNestedScrollView
import com.app.main.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout

class NetflixMainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener, NetflixNestedScrollView.NetflixOnScrollChangeListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var toolbar: Toolbar
    private lateinit var main: ConstraintLayout
    private lateinit var fragment: View
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_netflix_main)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        toolbar = findViewById(R.id.toolbar)
        tabLayout = findViewById(R.id.tabLayout)
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
        main = findViewById(R.id.main)
        fragment = findViewById(R.id.fragment_container)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        navController.addOnDestinationChangedListener(this)
        tabLayout.measure(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT)
        toolbar.measure(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

    private fun View.setMarginTop(value: Int) = updateLayoutParams<ViewGroup.MarginLayoutParams> {
        topMargin = value
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        when (destination.id) {
            R.id.fragmentHome -> {
                supportActionBar?.setIcon(null)
                toolbar.setNavigationIcon(R.drawable.ic_netflix_v3)
                ViewCompat.setOnApplyWindowInsetsListener(main) { view, windowInsets ->
                    toolbar.setMarginTop(windowInsets.systemWindowInsetTop)
                    //visibility is not possible until not use setOnApplyWindowInsetsListener
                    tabLayout.visibility = View.VISIBLE
                    fragment.updatePadding(
                        top = (toolbar.measuredHeight + windowInsets.systemWindowInsetTop + tabLayout.measuredHeight)
                    )
                    windowInsets
                }
            }
            R.id.fragmentGamesDetail->{
                ViewCompat.setOnApplyWindowInsetsListener(main) { view, windowInsets ->
                    toolbar.setMarginTop(windowInsets.systemWindowInsetTop)
                    //visibility is not possible until not use setOnApplyWindowInsetsListener
                    tabLayout.visibility = View.GONE
                    fragment.updatePadding(
                        top = 0
                    )
                    windowInsets
                }
            }
            else->{
                supportActionBar?.setIcon(null)
                ViewCompat.setOnApplyWindowInsetsListener(main) { view, windowInsets ->
                    toolbar.setMarginTop(windowInsets.systemWindowInsetTop)
                    //visibility is not possible until not use setOnApplyWindowInsetsListener
                    tabLayout.visibility = View.GONE
                    fragment.updatePadding(
                        top = (toolbar.layoutParams.height + windowInsets.systemWindowInsetTop)
                    )
                    windowInsets
                }
            }
        }
    }

    override fun setTranslationY(mHeaderDiffTotal: Int) {
        tabLayout.translationY = mHeaderDiffTotal.toFloat()
    }

    override val minHeaderTranslation = -tabLayout.height

}