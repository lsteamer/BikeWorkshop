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
import androidx.navigation.toRoute
import com.anjegonz.bikeworkshop.garage.presentation.motorcycle_add.MotorcycleAddRoot
import com.anjegonz.bikeworkshop.garage.presentation.motorcycle_add.MotorcycleAddViewModel
import com.anjegonz.bikeworkshop.garage.presentation.motorcycle_display.MotorcycleDisplayRoot
import com.anjegonz.bikeworkshop.garage.presentation.motorcycle_display.MotorcycleDisplayViewModel
import com.anjegonz.bikeworkshop.garage.presentation.motorcycle_list.MotorcycleListRoot
import com.anjegonz.bikeworkshop.garage.presentation.motorcycle_list.MotorcycleListViewModel
import com.anjegonz.bikeworkshop.navigation.NavRoute
import com.anjegonz.bikeworkshop.ui.theme.BikeWorkshopTheme
import org.koin.androidx.compose.koinViewModel
import kotlin.reflect.KClass

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BikeWorkshopTheme {
                val navController = rememberNavController()

                //Current screen's entry from navigation stack
                val currentBackStackEntry by navController.currentBackStackEntryAsState()

                //Just the destination info e.g. NavRoute.Motorcycle....
                val currentDestination = currentBackStackEntry?.destination

                // Determine title for the Toolbar
                val currentTitle = when {
                    currentDestination.isRoute(NavRoute.MotorcycleList::class) -> "My Motorcycles"
                    currentDestination.isRoute(NavRoute.MotorcycleAdd::class) -> "Add Motorcycle"
                    currentDestination.isRoute(NavRoute.MotorcycleDisplay::class) -> "Motorcycle Details"
                    else -> "Bike Workshop"
                }

                // are we on the ListScreen
                val isOnListScreen = currentDestination.isRoute(NavRoute.MotorcycleList::class)


                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(currentTitle) },
                            navigationIcon = {
                                //If we're not on ListScreen, we can navigate back
                                if (!isOnListScreen) {
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
                        // Only show FAB on list screen, takes us to MotorcycleAdd feature
                        if (isOnListScreen) {
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
                    //NavHost holding the three screens
                    NavHost(
                        navController = navController,
                        startDestination = NavRoute.MotorcycleList,
                        modifier = Modifier.padding(paddingValues)
                    ) {
                        //MotorcycleList Feature. Showing the screen
                        composable<NavRoute.MotorcycleList> {
                            val viewModel = koinViewModel<MotorcycleListViewModel>()
                            MotorcycleListRoot(
                                viewModel = viewModel,
                                onNavigation = { motorcycle ->
                                    when (motorcycle) {
                                        null -> navController.navigate(NavRoute.MotorcycleAdd)
                                        else -> navController.navigate(
                                            NavRoute.MotorcycleDisplay(
                                                motorcycle.id,
                                                motorcycle.manufacturer
                                            )
                                        )
                                    }
                                }
                            )
                        }
                        //MotorcycleAdd feature, showing the TextFiels to add a Motorcycle
                        composable<NavRoute.MotorcycleAdd> {
                            val viewModel = koinViewModel<MotorcycleAddViewModel>()
                            MotorcycleAddRoot(
                                viewModel = viewModel,
                                onNavigateBack = { navController.navigateUp() }
                            )
                        }
                        //MotorcycleDisplay
                        composable<NavRoute.MotorcycleDisplay> { backStackEntry ->
                            val args = backStackEntry.toRoute<NavRoute.MotorcycleDisplay>()
                            val viewModel = koinViewModel<MotorcycleDisplayViewModel>()
                            MotorcycleDisplayRoot(
                                viewModel = viewModel,
                                args.id,
                                args.manufacturerName,
                                onNavigateBack = { navController.navigateUp() }
                            )
                        }
                    }

                }


            }
        }
    }

    private fun NavDestination?.isRoute(route: KClass<out NavRoute>): Boolean {
        return this?.route?.contains(route.simpleName.orEmpty()) == true
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