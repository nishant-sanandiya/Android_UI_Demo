package com.example.ui_demo.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.ui_demo.Fragments.TabHomeFragment
import com.example.ui_demo.Fragments.TabMessagesFragment
import com.example.ui_demo.Fragments.TabProfileFragment
import com.example.ui_demo.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class TabActivity : AppCompatActivity(), BaseActivity {

    private val homeFragmentInstance: Fragment = TabHomeFragment()
    private val messagesFragmentInstance: Fragment = TabMessagesFragment()
    private val profileFragmentInstance: Fragment = TabProfileFragment()
    private lateinit var bottomTabView : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab)
        title = "Tab"
        uiBinding()
        attachListners()
        setCurrentFragment(homeFragmentInstance)
    }

    override fun uiBinding() {
        bottomTabView = findViewById(R.id.bottomTab)
    }

    override fun attachListners() {
        bottomTabView?.setOnItemSelectedListener {
            when(it.itemId){
                R.id.tabHome -> setCurrentFragment(homeFragmentInstance)
                R.id.tabMessages -> setCurrentFragment(messagesFragmentInstance)
                R.id.tabProfile -> setCurrentFragment(profileFragmentInstance)
            }
            true
        }
    }

    private fun setCurrentFragment(fragment :Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.tabFrameLayout,fragment).commit()
        }
    }
}