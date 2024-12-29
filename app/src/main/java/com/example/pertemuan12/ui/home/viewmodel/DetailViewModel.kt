package com.example.pertemuan12.ui.home.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pertemuan12.model.Mahasiswa
import com.example.pertemuan12.repository.MahasiswaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class DetailUiState{
    data class Success(val mahasiswa: Mahasiswa) : DetailUiState()
    object Error : DetailUiState()
    object Loading : DetailUiState()
}

class DetailMhsViewModel(
    savedStateHandle: SavedStateHandle,
    private val mhs: MahasiswaRepository
): ViewModel(){

    private val _nim: String = checkNotNull(savedStateHandle[DestinasiDetail.NIM])

    private val _detalUiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val detailUiState: StateFlow<DetailUiState> = _detalUiState

    init {
        getDetailMahasiswa()
    }

    fun getDetailMahasiswa(){
        viewModelScope.launch {
            try {

                _detalUiState.value = DetailUiState.Loading
                val mahasiswa = mhs.getMahasiswabyNim(_nim)
                if (mahasiswa != null){
                    _detalUiState.value = DetailUiState.Success(mahasiswa)
                }else{
                    _detalUiState.value = DetailUiState.Error
                }
            }catch (e:Exception){
                _detalUiState.value = DetailUiState.Error
            }
        }
    }
}

fun Mahasiswa.toDetailUiEvent():InsertUiEvent{
    return InsertUiEvent(
        nim = nim,
        nama = nama,
        alamat = alamat,
        jenisKelamin = jenisKelamin,
        kelas = kelas,
        angkatan = angkatan
    )
}