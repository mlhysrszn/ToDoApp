package com.mlhysrszn.todoapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavBar()
    }

    private fun bottomNavBar() {
        val bottomNavBar = findViewById<BottomNavigationView>(R.id.bottomNavBar)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        NavigationUI.setupWithNavController(
            bottomNavBar,
            navHostFragment.navController
        )
    }
}