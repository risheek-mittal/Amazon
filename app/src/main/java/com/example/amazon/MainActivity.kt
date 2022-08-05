package com.example.amazon

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.amazon.fragments.ItemDetailsFragment
import com.example.amazon.interfaces.Communicator
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), Communicator {
    private lateinit var navController: NavController
    private lateinit var rightController: NavController
    private lateinit var appBarConfig: AppBarConfiguration
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var listener : NavController.OnDestinationChangedListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.mainContainer) as NavHostFragment
        navController = navHostFragment.navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        setupWithNavController(bottomNavigationView, navController)

        rightController = findNavController(R.id.mainContainer)
        drawerLayout = findViewById(R.id.drawerLayout)
        appBarConfig = AppBarConfiguration(rightController.graph, drawerLayout)

        navigationView.setupWithNavController(rightController)
        setupActionBarWithNavController(rightController, appBarConfig)
    }

    override fun passDataCom(title: String, thumbnail: String, brand: String, price: String) {
        val bundle = Bundle()
        bundle.putString("title", title)
        bundle.putString("thumbnail", thumbnail)
        bundle.putString("brand", brand)
        bundle.putString("price", price)

        val transaction = this.supportFragmentManager.beginTransaction()
        val itemDetailsFragment = ItemDetailsFragment()
        itemDetailsFragment.arguments = bundle

        transaction.replace(R.id.mainContainer, itemDetailsFragment)
        transaction.commit()

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.mainContainer)
        return navController.navigateUp(appBarConfig) || super.onSupportNavigateUp()
    }

}