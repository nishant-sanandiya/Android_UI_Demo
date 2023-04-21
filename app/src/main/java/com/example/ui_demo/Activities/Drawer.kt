package com.example.ui_demo.Activities

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.ui_demo.R
import com.google.android.material.navigation.NavigationView


class Drawer : AppCompatActivity(), BaseActivity {

    lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer)
        uiBinding()
        attachListners()
        drawerUiBinding()
        drawerAttachListeners()
    }

    private fun drawerUiBinding() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_camera, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navigationView.setupWithNavController(navController)
    }

    private fun drawerAttachListeners() {
    }

    override fun uiBinding() {
//        val toolbar = findViewById<Toolbar>(R.id.toolbar)
//        this.setSupportActionBar(toolbar)
        drawerLayout = findViewById(R.id.activity_drawer)
        navigationView = findViewById(R.id.navView)
    }

    override fun attachListners() {

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.custom_menu, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}