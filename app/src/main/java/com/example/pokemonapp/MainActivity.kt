package com.example.pokemonapp

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.pokemonapp.databinding.ActivityMainBinding
import com.example.pokemonapp.fragments.AddPokemonFragment
import com.example.pokemonapp.fragments.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.wait


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var _binding : ActivityMainBinding
    private val binding get() = _binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val appConfiguration = AppBarConfiguration(setOf(R.id.homeFragment2))
        bottomNav.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, nd: NavDestination, _ ->
            // the IDs of fragments as defined in the `navigation_graph`
            if (nd.id==R.id.addPokemonFragment2){
                binding.background.background = getDrawable(R.drawable.whiteback)
            }
            if (nd.id == R.id.homeFragment2 || nd.id == R.id.bookmarkFragment
            ) {
                bottomNav.visibility = View.VISIBLE
            } else {
                bottomNav.visibility = View.GONE
            }
        }

    }
}