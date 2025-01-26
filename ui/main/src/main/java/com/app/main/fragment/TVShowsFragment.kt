package com.app.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.main.NetflixNestedScrollView
import com.app.main.R
import com.app.main.activity.NetflixMainActivity
import com.app.main.dummy.MoviesAdapter
import com.app.main.dummy.getTestData
import com.google.android.material.transition.FadeThroughProvider
import com.google.android.material.transition.MaterialSharedAxis
import com.google.android.material.transition.SlideDistanceProvider

class TVShowsFragment : DownloadSearchMenuFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.Y, true).apply {
            duration = 1000
            addTarget(R.id.main_view)
            val slideDistanceProvider = primaryAnimatorProvider as SlideDistanceProvider
            slideDistanceProvider.slideDistance = 400
            val data = secondaryAnimatorProvider as FadeThroughProvider
            data.progressThreshold = 0.7f
        }
        returnTransition = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_home,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        view.findViewById<RecyclerView>(R.id.moviesRecyclerView).apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = MoviesAdapter(getTestData(),{
                //findNavController().navigate(R.id.action_fragmentHome_to_fragmentMoviesDetail)
            })
        }
        view.findViewById<ImageView>(R.id.imageView).setOnClickListener {
            findNavController().navigate(R.id.action_fragmentHome_to_fragmentGamesDetail)
        }
        view
            .findViewById<NetflixNestedScrollView>(R.id.nestedScrollViewHome)
            .setNetflixOnScrollChangeListener(activity as NetflixMainActivity)

    }
}