package com.app.getstarted.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.app.getstarted.R

class GetStartedActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var toolbar : Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_started)
        toolbar = findViewById<Toolbar>(R.id.toolbar)
        navController = findNavController(R.id.fragment_container)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.getStartedFragment,
                R.id.createAccountFragment
            )
        )
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration)
        setSupportActionBar(toolbar)
        navController.addOnDestinationChangedListener(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        when (destination.id) {
            R.id.getStartedFragment -> {
                toolbar.setNavigationIcon(R.drawable.ic_netflix_v3)
                supportActionBar?.setIcon(null)
            }
            R.id.createAccountFragment, R.id.loginFragment ->{
                supportActionBar?.setIcon(R.drawable.ic_netflix_full)
            }
        }
    }

}