package com.example.navigationcomponentdrawerlayout.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.*
import com.example.navigationcomponentdrawerlayout.NavGraphDirections
import com.example.navigationcomponentdrawerlayout.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    fun init() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()
        setSupportActionBar(toolbar)
        appBarConfiguration =
            AppBarConfiguration(setOf(R.id.homeFragment, R.id.historyFragment), drawerLayoutID)

        //set fragment title on toolbar
        setupActionBarWithNavController(navController, appBarConfiguration)
        // connect bottom navigation
        bottom_nav.setupWithNavController(navController)
        // connect navigation drawer layout
        navigation_drawerID.setupWithNavController(navController)

        // block the navigation drawer layout except home fragment
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.homeFragment -> {
                    drawerLayoutID.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
                }
                else -> {
                    drawerLayoutID.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)

                }
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.termAndConditionFragment) {
            val action = NavGraphDirections.actionGlobalTermAndConditionFragment()
            navController.navigate(action)
            true
        } else {
            item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}