package com.example.logincompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.ActivityNavigator
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.example.logincompose.navigation.Destinations
import com.example.logincompose.presentation.home.HomeScreen
import com.example.logincompose.presentation.login.LoginScreen
import com.example.logincompose.presentation.login.LoginViewModel
import com.example.logincompose.presentation.registration.RegisterViewModel
import com.example.logincompose.presentation.registration.RegistrationScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.leonardom.loginjetpackcompose.ui.theme.LoginJetpackComposeTheme

@OptIn(ExperimentalAnimationApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginJetpackComposeTheme {

                val navController = rememberAnimatedNavController()

                BoxWithConstraints() {
                    AnimatedNavHost(
                        navController = navController,
                        startDestination = Destinations.Login.route
                    ){
                        addLogin(navController)

                        addRegister(navController)

                        addHome()
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.addLogin(
    navController: NavController
){
    composable(
        route = Destinations.Login.route,
        enterTransition = { _, _ ->
            slideInHorizontally(
                initialOffsetX = { 1000 },
                //Nos permite definir una animación de entrada
                animationSpec = tween(500)
            )
        },
        exitTransition = { _, _ ->
            slideOutHorizontally(
                targetOffsetX = { -1000 },
                //Nos permite definir una animación de entrada
                animationSpec = tween(500)
            )
        },

        popEnterTransition = { _, _ ->
            slideInHorizontally(
                initialOffsetX = { -1000 },
                //Nos permite definir una animación de entrada
                animationSpec = tween(500)
            )
        },

        popExitTransition = { _, _ ->
            slideOutHorizontally(
                targetOffsetX = { 1000 },
                //Nos permite definir una animación de entrada
                animationSpec = tween(500)
            )
        }



    ){
        val viewModel: LoginViewModel = hiltViewModel()
        val email = viewModel.state.value.email
        val password = viewModel.state.value.password

        if(viewModel.state.value.suscesLogin){
            LaunchedEffect(key1 = Unit){
                navController.navigate(
                    Destinations.Home.route + "/$email" + "/$password"
                ){
                    //Borrar pila de navegación para cuando el usuario quiera realizar el gesto  hacea atrás para que se salga de la app
                    popUpTo(Destinations.Login.route){
                        inclusive = true
                    }
                }
            }
        }else{
            LoginScreen(
                state = viewModel.state.value,
                onLogin = viewModel::login,
                onNavigateToRegister = {
                    navController.navigate(Destinations.Register.route)
                },
                onDismisDialog = viewModel::hideErrorDialog

            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.addRegister(
    navController: NavController
){
    composable(
        route = Destinations.Register.route,
        enterTransition = { _, _ ->
            slideInHorizontally(
                initialOffsetX = { 1000 },
                //Nos permite definir una animación de entrada
                animationSpec = tween(500)
            )
        },
        exitTransition = { _, _ ->
            slideOutHorizontally(
                targetOffsetX = { -1000 },
                //Nos permite definir una animación de entrada
                animationSpec = tween(500)
            )
        },

        popEnterTransition = { _, _ ->
            slideInHorizontally(
                initialOffsetX = { -1000 },
                //Nos permite definir una animación de entrada
                animationSpec = tween(500)
            )
        },

        popExitTransition = { _, _ ->
            slideOutHorizontally(
                targetOffsetX = { 1000 },
                //Nos permite definir una animación de entrada
                animationSpec = tween(500)
            )
        }



    ){

        val viewModel: RegisterViewModel = hiltViewModel()

        RegistrationScreen(
            state = viewModel.state.value,
            onRegister = viewModel::register,
            onBack = {
                navController.popBackStack()
            },
            onDismissDialog = viewModel::hideErrorDialog
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.addHome(){
    composable(
        route = Destinations.Home.route + "/{email}" + "/{password}",
        arguments = Destinations.Home.arguments

    ){ backStackEntry ->

        val email = backStackEntry.arguments?.getString("email") ?: ""
        val password = backStackEntry.arguments?.getString("password") ?: ""
        HomeScreen(email, password)
    }
}