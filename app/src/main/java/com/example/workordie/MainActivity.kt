package com.example.workordie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.workordie.ui.screen.*
import com.example.workordie.ui.theme.WorkOrDieTheme
import android.app.Application
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkOrDieTheme {
                WorkorDieApp()
            }
        }
    }
}

@Composable
fun WorkorDieApp(){
    //State Hoisting
    //use navController to navigate between screens
    val navController = rememberNavController()

    //viewModel owner
    val owner = LocalViewModelStoreOwner.current
    owner?.let {
        val viewModel: TaskViewModel = viewModel(
            it,
            "TaskViewModel",
            TaskViewModelFactory(
                LocalContext.current.applicationContext
                        as Application)
        )

        //ScreenSetup(viewModel)
    }

    //NavHost holds the NavGraph
    //list the screen you want to navigate below
    NavHost(
        navController = navController,
        startDestination = NavScreen.Home.route
    ){
        composable(route = NavScreen.Home.route){
            Home(navController = navController)
        }
        composable(route = NavScreen.AllTasks.route){
            AllTasks(navController = navController)
        }
        composable(route = NavScreen.AddTask.route){
            AddTask(navController = navController)
        }
        composable(route = NavScreen.FinishSubmit.route){
            FinishSubmit(navController = navController)
        }
        composable(route = NavScreen.FinishPopup.route){
            FinishPopup()
        }
        composable(route = NavScreen.CountingTime.route){
            CountingTime()
        }
        composable(route = NavScreen.calendar.route){
            Calendar(navController = navController)
        }
        composable(route = NavScreen.dailyTask.route){
            DailyTask()
        }
    }
}

//viewModel Factory
class TaskViewModelFactory(val application: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TaskViewModel(application) as T
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WorkOrDieTheme {
        WorkorDieApp()
    }
}