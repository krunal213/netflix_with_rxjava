package com.app.main.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.app.main.R

abstract class DownloadSearchMenuFragment : Fragment() {

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_download_search_toolbar, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> findNavController().navigateUp()
        R.id.fragmentDownloads -> {
            findNavController()
                .navigate(
                    Uri.parse("example://secondary"),
                    NavOptions.Builder()
                        .setPopUpTo(
                            findNavController().graph.findStartDestination().id,
                            inclusive = false,
                            saveState = true
                        )
                        .build()
                )
            super.onOptionsItemSelected(item)
        }
        R.id.activitySearch->{
            startActivity(Intent().apply {
                setClassName(requireActivity(),"com.app.search.activity.SearchActivity")
            })
            super.onOptionsItemSelected(item)
        }
        else -> NavigationUI.onNavDestinationSelected(
            item,
            findNavController()
        ) || super.onOptionsItemSelected(item)
    }

}