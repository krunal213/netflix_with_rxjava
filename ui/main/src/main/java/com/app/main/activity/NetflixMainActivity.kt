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
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class NetflixMainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener,
    NetflixNestedScrollView.NetflixOnScrollChangeListener,
    View.OnClickListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var toolbar: Toolbar
    private lateinit var main: ConstraintLayout
    private lateinit var fragment: View
    private lateinit var chipGroup: ChipGroup
    private lateinit var chipCategories: Chip
    private lateinit var chipTvShows: Chip
    private lateinit var chipMovies: Chip
    private lateinit var chipClose: Chip

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_netflix_main)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        toolbar = findViewById(R.id.toolbar)
        chipGroup = findViewById(R.id.chipGroup)
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
        chipCategories = findViewById(R.id.chip_categories)
        chipTvShows = findViewById(R.id.chip_tv_shows)
        chipMovies = findViewById(R.id.chip_movies)
        chipClose = findViewById(R.id.chip_close)
        chipCategories.setOnClickListener(this)
        chipTvShows.setOnClickListener(this)
        chipMovies.setOnClickListener(this)
        chipClose.setOnClickListener(this)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        navController.addOnDestinationChangedListener(this)
        chipGroup.measure(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        toolbar.measure(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
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
                chipMovies.visibility =  View.VISIBLE
                chipTvShows.visibility =  View.VISIBLE
                chipCategories.visibility = View.VISIBLE
                chipCategories.text = "Categories"
                chipClose.visibility = View.GONE
                supportActionBar?.setIcon(null)
                toolbar.setNavigationIcon(R.drawable.ic_netflix_v3)
                ViewCompat.setOnApplyWindowInsetsListener(main) { view, windowInsets ->
                    toolbar.setMarginTop(windowInsets.systemWindowInsetTop)
                    //visibility is not possible until not use setOnApplyWindowInsetsListener
                    chipGroup.visibility = View.VISIBLE
                    fragment.updatePadding(
                        top = (toolbar.measuredHeight + windowInsets.systemWindowInsetTop + chipGroup.measuredHeight)
                    )
                    windowInsets
                }
            }

            R.id.fragmentGamesDetail -> {
                ViewCompat.setOnApplyWindowInsetsListener(main) { view, windowInsets ->
                    toolbar.setMarginTop(windowInsets.systemWindowInsetTop)
                    //visibility is not possible until not use setOnApplyWindowInsetsListener
                    chipGroup.visibility = View.GONE
                    fragment.updatePadding(
                        top = 0
                    )
                    windowInsets
                }
            }

            R.id.fragmentTVShows, R.id.fragmentMovies -> {
                if (destination.id == R.id.fragmentTVShows) {
                    chipTvShows.visibility =  View.VISIBLE
                    chipClose.visibility = View.VISIBLE
                    chipMovies.visibility =  View.GONE
                    chipCategories.visibility = View.GONE
                } else if (destination.id == R.id.fragmentMovies) {
                    chipMovies.visibility =  View.VISIBLE
                    chipClose.visibility = View.VISIBLE
                    chipTvShows.visibility =  View.GONE
                    chipCategories.visibility = View.GONE
                }
                supportActionBar?.setIcon(null)
                ViewCompat.setOnApplyWindowInsetsListener(main) { view, windowInsets ->
                    toolbar.setMarginTop(windowInsets.systemWindowInsetTop)
                    //visibility is not possible until not use setOnApplyWindowInsetsListener
                    chipGroup.visibility = View.VISIBLE
                    fragment.updatePadding(
                        top = (toolbar.measuredHeight + windowInsets.systemWindowInsetTop + chipGroup.measuredHeight)
                    )
                    windowInsets
                }
            }

            R.id.fragmentCategories -> {
                chipCategories.visibility =  View.VISIBLE
                chipClose.visibility = View.VISIBLE
                chipMovies.visibility =  View.GONE
                chipTvShows.visibility = View.GONE
                supportActionBar?.setIcon(null)
                chipCategories.text = arguments?.getString("label")
                ViewCompat.setOnApplyWindowInsetsListener(main) { view, windowInsets ->
                    toolbar.setMarginTop(windowInsets.systemWindowInsetTop)
                    //visibility is not possible until not use setOnApplyWindowInsetsListener
                    chipGroup.visibility = View.VISIBLE
                    fragment.updatePadding(
                        top = (toolbar.measuredHeight + windowInsets.systemWindowInsetTop + chipGroup.measuredHeight)
                    )
                    windowInsets
                }
            }

            else -> {
                supportActionBar?.setIcon(null)
                ViewCompat.setOnApplyWindowInsetsListener(main) { view, windowInsets ->
                    toolbar.setMarginTop(windowInsets.systemWindowInsetTop)
                    //visibility is not possible until not use setOnApplyWindowInsetsListener
                    chipGroup.visibility = View.GONE
                    fragment.updatePadding(
                        top = (toolbar.layoutParams.height + windowInsets.systemWindowInsetTop)
                    )
                    windowInsets
                }
            }
        }
    }

    override fun setTranslationY(mHeaderDiffTotal: Int) {
        chipGroup.translationY = mHeaderDiffTotal.toFloat()
    }

    override fun minHeaderTranslation() = -chipGroup.height

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.chip_tv_shows -> {
                navController.navigate(R.id.action_tvShowsFragment)
            }

            R.id.chip_movies -> {
                navController.navigate(R.id.action_moviesFragment)
            }

            R.id.chip_categories -> {
                navController.navigate(R.id.action_categoriesMenuFullScreenDialogFragment)
            }

            R.id.chip_close ->{
                navController.popBackStack()
            }
        }
    }

}