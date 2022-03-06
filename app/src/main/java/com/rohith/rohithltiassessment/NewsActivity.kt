package com.rohith.rohithltiassessment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController

class NewsActivity : AppCompatActivity() {

    /**
     * Our MainActivity is only responsible for setting the content view that contains the
     * Navigation Host.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val navController = findNavController(R.id.nav_host_fragment)
        val appConfiguration = AppBarConfiguration(navController.graph)

        setSupportActionBar(toolbar)
        toolbar.setupWithNavController(navController, appConfiguration)
    }
}
