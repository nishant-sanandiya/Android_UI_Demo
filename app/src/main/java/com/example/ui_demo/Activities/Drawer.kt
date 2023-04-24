package com.example.ui_demo.Activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.ui_demo.Fragments.fragment_1
import com.example.ui_demo.Fragments.fragment_2
import com.example.ui_demo.R
import com.google.android.material.navigation.NavigationView


class Drawer : AppCompatActivity(), BaseActivity, NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer)
        uiBinding()
        drawerUiBinding()
        attachListners()
        drawerAttachListeners()
    }

    private fun drawerUiBinding() {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        navigationView = findViewById(R.id.navView)
        navigationView.setupWithNavController(navController)
        drawerLayout = findViewById(R.id.activity_drawer)
        toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        toolbar = findViewById(R.id.drawerToolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()
    }

    private fun drawerAttachListeners() {
        drawerLayout.addDrawerListener(toggle);
        navigationView.setNavigationItemSelectedListener(this);
    }

    override fun uiBinding() {
    }

    override fun attachListners() {

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.custom_menu, menu)
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_camera -> createEmptyFragmentAndReplace()

            R.id.nav_gallery -> createEmptyFragmentAndReplace()

            R.id.nav_slideshow -> createEmptyFragmentAndReplace()

            R.id.nav_manage -> createEmptyFragmentAndReplace()

            R.id.nav_share -> createEmptyFragmentAndReplace()

            R.id.nav_send -> Intent(this, MainActivity::class.java).let {
                startActivity(it)
            }
        }
        drawerLayout.close()
        return true
    }

    private fun createEmptyFragmentAndReplace() {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        val fragment = fragment_1()
        transaction.replace(R.id.nav_host_fragment_content_main, fragment)
        transaction.commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (toggle.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item!!)
    }
}