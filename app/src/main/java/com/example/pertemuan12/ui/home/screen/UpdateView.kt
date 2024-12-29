package com.example.pertemuan12.ui.home.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pertemuan12.navigation.DestinasiNavigasi
import com.example.pertemuan12.ui.home.CostumeTopAppBar
import com.example.pertemuan12.ui.home.viewmodel.HomeViewModel
import com.example.pertemuan12.ui.home.viewmodel.PenyediaViewModel
import com.example.pertemuan12.ui.home.viewmodel.UpdateViewModel
import com.example.pertemuan12.ui.home.viewmodel.toMhs
import kotlinx.coroutines.launch
import org.jetbrains.annotations.ApiStatus.Experimental

object DestinasiUpdate : DestinasiNavigasi{
    override val route = "update"
    const val NIM = "nim"
    val routesWithArg = "$route/{$NIM}"
    override val titleRes = "Update Mhs"
}
 @OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateView(
    navigateBack:()-> Unit,
    modifier: Modifier = Modifier,
    viewModel: UpdateViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
     val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

     val uiState = viewModel.uiState.value

     Scaffold(
         modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
         topBar = {
             CostumeTopAppBar(
                 title = DestinasiUpdate.titleRes,
                 canNavigateBack = true,
                 scrollBehavior = scrollBehavior,
                 navigateUp = navigateBack
             )
         }
     ) { padding ->
         Column (
             modifier = Modifier
                 .fillMaxSize()
                 .padding(padding)
                 .padding(16.dp)
         ){

             EntryBody(
                 insertUiState = uiState,
                 onSiswaValueChange  = { updateValue->
                     viewModel.updateMhsState(updateValue)
                 },
                 onSaveClick = {
                     uiState.insertUiEvent?.let { insertUiEvent ->
                         coroutineScope.launch {
                             viewModel.updateMhs(
                                 nim = viewModel.nim,
                                 mahasiswa = insertUiEvent.toMhs()
                             )
                             navigateBack()
                         }
                     }
                 }
             )
         }
     }
}
