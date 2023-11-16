package com.example.johndeereapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.window.layout.FoldingFeature
import androidx.window.layout.WindowInfoTracker
import com.example.johndeereapp.R
import com.example.johndeereapp.ui.screens.ProfileScreen
import com.example.johndeereapp.ui.screens.StoreScreen
import com.example.johndeereapp.ui.screens.TractorScreen
import com.example.johndeereapp.ui.theme.JohnDeereAppTheme
import com.example.johndeereapp.ui.viewmodels.UserViewModel
import com.example.johndeereapp.utils.DevicePosture
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class Screen(val route: String, @StringRes val resourceId: Int, val icon: ImageVector){
    data object Profile : Screen("profile", R.string.profile_title, Icons.Rounded.AccountCircle)
    data object Tractor : Screen("tractor", R.string.tractor_title, Icons.Rounded.Home)
    data object Store : Screen("store", R.string.store_title, Icons.Rounded.ShoppingCart)
}

@AndroidEntryPoint
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val devicePostureFlow = WindowInfoTracker.getOrCreate(this).windowLayoutInfo(this)
            .flowWithLifecycle(this.lifecycle)
            .map { layoutInfo ->
                val foldingFeature = layoutInfo.displayFeatures
                    .filterIsInstance<FoldingFeature>()
                    .firstOrNull()

                when {
                    foldingFeature?.state == FoldingFeature.State.HALF_OPENED -> DevicePosture.BookPosture(foldingFeature.bounds)
                    foldingFeature?.isSeparating == true ->
                        foldingFeature.let { DevicePosture.Separating(it.bounds, foldingFeature.orientation) }

                    else -> DevicePosture.NormalPosture
                }
            }.stateIn(
                scope = lifecycleScope,
                started = SharingStarted.Eagerly,
                initialValue = DevicePosture.NormalPosture
            )

        setContent {
            JohnDeereAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    calculateWindowSizeClass(this);
                    devicePostureFlow.collectAsState().value
                    JohnDeereApp()
                }
            }
        }
    }
}

@Composable
fun JohnDeereApp() {
    //Nav items
    val items = listOf(
        Screen.Profile,
        Screen.Tractor,
        Screen.Store
    )

    val navController = rememberNavController();

    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(screen.icon, contentDescription = null) },
                        label = { Text(stringResource(screen.resourceId)) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(navController, startDestination = Screen.Profile.route, Modifier.padding(innerPadding)) {
            composable(Screen.Profile.route) {
                // Creates a ViewModel from the current BackStackEntry
                // Available in the androidx.hilt:hilt-navigation-compose artifact
                val viewModel = hiltViewModel<UserViewModel>()
                ProfileScreen(navController, viewModel)
            }
            composable(Screen.Tractor.route) { TractorScreen(navController) }
            composable(Screen.Store.route) { StoreScreen(navController) }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PhonePreview() {
    JohnDeereAppTheme {
        JohnDeereApp()
    }
}