package com.anjegonz.bikeworkshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.anjegonz.bikeworkshop.garage.presentation.motorcycle_add.MotorcycleAddRoot
import com.anjegonz.bikeworkshop.garage.presentation.motorcycle_add.MotorcycleAddViewModel
import com.anjegonz.bikeworkshop.garage.presentation.motorcycle_display.MotorcycleDisplayRoot
import com.anjegonz.bikeworkshop.garage.presentation.motorcycle_display.MotorcycleDisplayViewModel
import com.anjegonz.bikeworkshop.garage.presentation.motorcycle_list.MotorcycleListRoot
import com.anjegonz.bikeworkshop.garage.presentation.motorcycle_list.MotorcycleListViewModel
import com.anjegonz.bikeworkshop.navigation.NavRoute
import com.anjegonz.bikeworkshop.ui.theme.BikeWorkshopTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BikeWorkshopTheme {
                val navController = rememberNavController()
                val currentBackStackEntry by navController.currentBackStackEntryAsState()

                //current
                val currentDestination = currentBackStackEntry?.destination

                // Determine if we should show back button
                val canNavigateBack = !currentDestination.isRoute(NavRoute.MotorcycleList)

                // Determine title
                val currentTitle = when {
                    currentDestination.isRoute(NavRoute.MotorcycleList) -> "My Motorcycles"
                    currentDestination.isRoute(NavRoute.MotorcycleAdd) -> "Add Motorcycle"
                    currentDestination?.route?.contains("MotorcycleDisplay") == true -> "Motorcycle Details"
                    else -> "Bike Workshop"
                }

                // are we on the
                val isOnStartScreen = currentDestination.isRoute(NavRoute.MotorcycleList)


                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(currentTitle) },
                            navigationIcon = {
                                if (canNavigateBack) {
                                    IconButton(onClick = { navController.navigateUp() }) {
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                            contentDescription = "Back"
                                        )
                                    }
                                }
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        )
                    },
                    floatingActionButton = {
                        // Only show FAB on list screen
                        if (isOnStartScreen) {
                            FloatingActionButton(
                                onClick = { navController.navigate(NavRoute.MotorcycleAdd) },
                                containerColor = MaterialTheme.colorScheme.primary
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "Add Motorcycle",
                                    tint = MaterialTheme.colorScheme.onPrimary
                                )
                            }
                        }
                    }
                ) { paddingValues ->
                    NavHost(
                        navController = navController,
                        startDestination = NavRoute.MotorcycleList,
                        modifier = Modifier.padding(paddingValues)
                    ) {
                        composable<NavRoute.MotorcycleList> {
                            val viewModel = koinViewModel<MotorcycleListViewModel>()
                            MotorcycleListRoot(
                                viewModel = viewModel,
                                onNavigation = { motorcycle ->
                                    when (motorcycle) {
                                        null -> navController.navigate(NavRoute.MotorcycleAdd)
                                        else -> navController.navigate(
                                            NavRoute.MotorcycleDisplay(
                                                motorcycle.id
                                            )
                                        )
                                    }
                                }
                            )
                        }
                        composable<NavRoute.MotorcycleAdd> {
                            val viewModel = koinViewModel<MotorcycleAddViewModel>()
                            MotorcycleAddRoot(
                                viewModel = viewModel,
                                onNavigateBack = { navController.navigateUp() }
                            )
                        }

                        composable<NavRoute.MotorcycleDisplay> {
                            val viewModel = koinViewModel<MotorcycleDisplayViewModel>()
                            MotorcycleDisplayRoot(
                                viewModel = viewModel
                            )
                        }
                    }

                }


            }
        }
    }

    private fun NavDestination?.isRoute(route: NavRoute): Boolean {
        return this?.route?.contains(route::class.simpleName.orEmpty()) == true
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BikeWorkshopTheme {
        Greeting("Android")
    }
}