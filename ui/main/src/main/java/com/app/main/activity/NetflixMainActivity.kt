package com.app.main.activity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.updateLayoutParams
import androidx.core.view.updatePadding
import androidx.core.widget.NestedScrollView
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.app.main.NetflixNestedScrollView
import com.app.main.R
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback

class NetflixMainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener,
    NetflixNestedScrollView.NetflixOnScrollChangeListener, View.OnClickListener {

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
    private lateinit var chipAllCategories: Chip
    private lateinit var appBarLayout: AppBarLayout
    private lateinit var layout: ConstraintLayout
    private val color = "20525E" //5D1C1C,616161,1B415B,56342D
    private val startColor = Color.parseColor("#$color")
    private val endColor = Color.parseColor("#000000")
    private val maxScroll = 300
    private val argbEvaluator = ArgbEvaluator()

    override fun onCreate(savedInstanceState: Bundle?) {
        setEnterSharedElementCallback(MaterialContainerTransformSharedElementCallback())
        window.sharedElementsUseOverlay = false
        postponeEnterTransition()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_netflix_main)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)

        bottomNavigationView.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                bottomNavigationView.viewTreeObserver.removeOnPreDrawListener(this)
                val menuView = bottomNavigationView.findViewById<ViewGroup>(R.id.nav_graph_my_netflix)
                val firstItemView = menuView.getChildAt(0)
                ViewCompat.setTransitionName(firstItemView, "shared_element")
                val transform = MaterialContainerTransform().apply {
                    addTarget(firstItemView)
                    duration = 1000
                }
                window.sharedElementEnterTransition = transform
                startPostponedEnterTransition()
                return true
            }
        })

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
        chipAllCategories = findViewById(R.id.chip_all_categories)
        layout = findViewById(R.id.main)
        appBarLayout = findViewById(R.id.appbarLayout)

        chipCategories.setOnClickListener(this)
        chipTvShows.setOnClickListener(this)
        chipMovies.setOnClickListener(this)
        chipClose.setOnClickListener(this)
        chipAllCategories.setOnClickListener(this)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        navController.addOnDestinationChangedListener(this)
        chipGroup.measure(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        toolbar.measure(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        chipClose.measure(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
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
                val gradientDrawable = GradientDrawable(
                    GradientDrawable.Orientation.TOP_BOTTOM,
                    intArrayOf(Color.parseColor("#$color"), Color.parseColor("#000000"))
                )
                gradientDrawable.cornerRadius = 0f
                layout.background = gradientDrawable
                chipCategories.text = "Categories"
                fadeOutAndHideViewsWithAnimatorSet(
                    listOf(chipClose, chipAllCategories),
                    object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            fadeInAndVisibleViewsWithAnimatorSet(
                                listOf(chipMovies, chipTvShows, chipCategories)
                            )
                        }
                    }
                )
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
                layout.setBackgroundResource(android.R.color.transparent)
                appBarLayout.setBackgroundResource(android.R.color.transparent)
                chipGroup.setBackgroundResource(android.R.color.transparent)
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
                val label = arguments?.getString("label")
                chipAllCategories.text = label ?: "All Categories"
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
                if (destination.id == R.id.fragmentTVShows) {
                    chipTvShows.visibility = View.VISIBLE
                    fadeOutAndHideViewsWithAnimatorSet(
                        listOf(chipMovies, chipCategories),
                        object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator) {
                                fadeInAndVisibleViewsWithAnimatorSet(
                                    listOf(chipClose, chipAllCategories)
                                )
                            }
                        }
                    )
                } else if (destination.id == R.id.fragmentMovies) {
                    chipMovies.visibility = View.VISIBLE
                    fadeOutAndHideViewsWithAnimatorSet(
                        listOf(chipTvShows, chipCategories),
                        object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator) {
                                fadeInAndVisibleViewsWithAnimatorSet(
                                    listOf(chipClose, chipAllCategories)
                                )
                            }
                        }
                    )
                }
            }

            R.id.fragmentCategories -> {
                chipCategories.visibility = View.VISIBLE
                chipClose.visibility = View.VISIBLE
                chipMovies.visibility = View.GONE
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
                layout.setBackgroundResource(android.R.color.transparent)
                appBarLayout.setBackgroundResource(android.R.color.transparent)
                chipGroup.setBackgroundResource(android.R.color.transparent)
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

    override fun onNetflixScrollChange(
        v: NestedScrollView,
        scrollX: Int,
        scrollY: Int,
        oldScrollX: Int,
        oldScrollY: Int
    ) {
        when(v.id){
            R.id.nestedScrollViewHome->{
                val ratio = (scrollY.coerceAtMost(maxScroll)).toFloat() / maxScroll
                val adjustedRatio = 0.29f + ratio * (1.0f - 0.29f)
                val color = argbEvaluator.evaluate(adjustedRatio, startColor, endColor) as Int
                appBarLayout.setBackgroundColor(color)
                chipGroup.setBackgroundColor(color)
                layout.setBackgroundColor(color)
            }
            else->{
                layout.setBackgroundColor(Color.TRANSPARENT)
                appBarLayout.setBackgroundColor(Color.TRANSPARENT)
                chipGroup.setBackgroundColor(Color.TRANSPARENT)
            }
        }
    }

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

            R.id.chip_close -> {
                if (navController.currentDestination?.id == R.id.fragmentTVShows) {
                    //chipTvShows.visibility = View.VISIBLE
                    fadeOutAndHideViewsWithAnimatorSet(
                        listOf(chipClose, chipAllCategories),
                        object : AnimatorListenerAdapter() {
                            /*override fun onAnimationEnd(animation: Animator) {
                                fadeInAndVisibleViewsWithAnimatorSet(
                                    listOf(chipMovies,chipCategories)
                                )
                            }*/
                        }
                    )
                }
                navController.popBackStack()
            }

            R.id.chip_all_categories -> {
                navController.navigate(
                    R.id.action_allCategoriesMenuFullScreenDialogFragment,
                    Bundle().apply {
                        putInt(
                            "destination", when (navController.currentDestination?.id) {
                                R.id.fragmentTVShows -> {
                                    R.id.action_allCategoriesMenuFullScreenDialogFragment_to_fragmentTVShows
                                }

                                R.id.fragmentMovies -> {
                                    R.id.action_allCategoriesMenuFullScreenDialogFragment_to_fragmentMovies
                                }

                                else -> 0
                            }
                        )
                    })
            }
        }
    }


    private fun fadeOutAndHideViewsWithAnimatorSet(
        views: List<View>,
        animatorListenerAdapter: AnimatorListenerAdapter? = null
    ) {
        val animatorSet = AnimatorSet()
        val animators: ArrayList<ObjectAnimator> = ArrayList()
        for (view in views) {
            val fadeOut = ObjectAnimator.ofFloat(view, View.ALPHA, 1f, 0f)
            fadeOut.setDuration(500)
            fadeOut.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    if (view.visibility != View.GONE) {
                        view.visibility = View.GONE
                    }
                }
            })
            animators.add(fadeOut)
        }
        if (animatorListenerAdapter != null) {
            animatorSet.addListener(animatorListenerAdapter)
        }
        animatorSet.playTogether(animators as Collection<Animator>)
        animatorSet.start()
    }

    private fun fadeInAndVisibleViewsWithAnimatorSet(
        views: List<View>,
        animatorListenerAdapter: AnimatorListenerAdapter? = null
    ) {
        val animatorSet = AnimatorSet()
        val animators: ArrayList<ObjectAnimator> = ArrayList()
        for (view in views) {
            if (view.visibility != View.VISIBLE) {
                view.visibility = View.VISIBLE
                val fadeOut = ObjectAnimator.ofFloat(view, View.ALPHA, 0f, 1f)
                fadeOut.setDuration(500)
                animators.add(fadeOut)
            }
        }
        if (animatorListenerAdapter != null) {
            animatorSet.addListener(animatorListenerAdapter)
        }
        animatorSet.playTogether(animators as Collection<Animator>)
        animatorSet.start()
    }

}