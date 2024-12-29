package com.example.pertemuan12.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pertemuan12.ui.home.screen.DestinasiDetail
import com.example.pertemuan12.ui.home.screen.DestinasiEntry
import com.example.pertemuan12.ui.home.screen.DestinasiHome
import com.example.pertemuan12.ui.home.screen.DestinasiUpdate
import com.example.pertemuan12.ui.home.screen.DetailMhsView
import com.example.pertemuan12.ui.home.screen.EntryMhsScreen
import com.example.pertemuan12.ui.home.screen.HomeScreen
import com.example.pertemuan12.ui.home.screen.UpdateView

@Composable
fun PengelolaHalaman(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()){
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier,
    ){
        composable(DestinasiHome.route){
            HomeScreen(
                navigateToItemEntry = {navController.navigate(DestinasiEntry.route)},
                onDetailClick = { nim ->
                    // Navigasi ke destinasi Detail dengan menyertakan nim
                    navController.navigate("${DestinasiDetail.route}/$nim") {
                        // Menggunakan popUpTo untuk memastikan navigasi ke Detail dan menghapus stack sebelumnya jika perlu
                        popUpTo(DestinasiHome.route) {
                            inclusive = true  // Termasuk juga destinasi yang akan dipopUp
                        }
                    }
                    println("PengelolaHalaman: nim = $nim")
                }
            )
        }
        composable(DestinasiEntry.route){
            EntryMhsScreen(navigateBack = {
                navController.navigate(DestinasiHome.route){
                    popUpTo(DestinasiHome.route){
                        inclusive = true
                    }
                }
            })
        }
        composable(DestinasiDetail.routeWithArg) { backStackEntry ->
            // Mendapatkan NIM dari argument route
            val nim = backStackEntry.arguments?.getString(DestinasiDetail.NIM)

            nim?.let {
                DetailMhsView(
                    nim = it, // Mengirimkan NIM ke DetailMhsView
                    navigateBack = {
                        // Aksi ketika tombol "Kembali" ditekan
                        navController.navigate(DestinasiHome.route) {
                            popUpTo(DestinasiHome.route) {
                                inclusive = true // Pop sampai ke DestinasiHome
                            }
                        }
                    },
                    onEditClick = {
                        // Navigasi ke halaman update dengan NIM sebagai argumen
                        navController.navigate("${DestinasiUpdate.route}/$it")
                    }
                )
            }
        }


        composable(
            DestinasiUpdate.routesWithArg, // Correct route with argument
            arguments = listOf(
                navArgument(DestinasiUpdate.NIM) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->

            // Retrieve the 'nim' argument from the navBackStackEntry
            val nim = backStackEntry.arguments?.getString(DestinasiUpdate.NIM)

            nim?.let {
                // Pass 'nim' to the UpdateView composable
                UpdateView(
                    navigateBack = {
                        navController.popBackStack()
                    },

                    modifier = modifier,

                    )
            }
        }


    }
}