package com.app.netflixwithrxjava.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
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
    }

    override fun onSupportNavigateUp() : Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

}