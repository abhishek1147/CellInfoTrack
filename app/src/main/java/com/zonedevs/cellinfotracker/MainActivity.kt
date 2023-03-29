package com.zonedevs.cellinfotracker

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.zonedevs.cellinfotracker.view.InfoFragment
import com.zonedevs.cellinfotracker.view.MonitorFragment

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavView: BottomNavigationView
    private val monitorFragment = MonitorFragment()
    private val infoFragment = InfoFragment()
    private val tagMonitorFragment = "MonitorFragment"
    private val tagInfoFragment = "InfoFragment"
    lateinit var layout: FragmentContainerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("onCreate","Project Created")
        setupView()
    }

    fun setupView(){

        layout = findViewById(R.id.fragmentContainer)
        supportFragmentManager.switch(R.id.fragmentContainer, monitorFragment, tagMonitorFragment )
        bottomNavView = findViewById(R.id.bottomNavBar)
        bottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.monitor -> {
                    supportFragmentManager.switch(R.id.fragmentContainer, monitorFragment, tagMonitorFragment )
                    this.supportActionBar?.title = "Cell Info Tracker"
                }
                R.id.info -> {
                    supportFragmentManager.switch(R.id.fragmentContainer, infoFragment, tagInfoFragment )
                    this.supportActionBar?.title = "INFO"
                }
            }
            true
        }
    }

    private fun FragmentManager.switch(containerId: Int, newFrag: Fragment, tag: String) {

        var current = findFragmentByTag(tag)
        beginTransaction()
            .apply {
                //Hide the current fragment
                primaryNavigationFragment?.let { hide(it) }
                //Check if current fragment exists in fragmentManager
                if (current == null) {
                    current = newFrag
                    add(containerId, current!!, tag)
                } else {
                    show(current!!)
                }
            }
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .setPrimaryNavigationFragment(current)
            .setReorderingAllowed(true)
            .commitNowAllowingStateLoss()
    }
}