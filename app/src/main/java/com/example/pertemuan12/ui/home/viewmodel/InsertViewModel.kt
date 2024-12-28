package com.example.pertemuan12.ui.home.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.pertemuan12.model.Mahasiswa
import com.example.pertemuan12.repository.MahasiswaRepository

class InsertViewModel(private val mhs: MahasiswaRepository) : ViewModel(){


}

data class InsertUiState(
    val insertUiEvent:InsertUiEvent = InsertUiEvent()
)

data class InsertUiEvent(
    val nim:String = "",
    val nama:String = "",
    val alamat:String = "",
    val jenisKelamin:String = "",
    val kelas:String = "",
    val angkatan:String = "",
)

fun InsertUiEvent.toMhs():Mahasiswa = Mahasiswa(
    nim = nim,
    nama = nama,
    alamat = alamat,
    jenisKelamin = jenisKelamin,
    kelas = kelas,
    angkatan = angkatan
)
