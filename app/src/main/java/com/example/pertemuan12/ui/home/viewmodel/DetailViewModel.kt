package com.example.pertemuan12.ui.home.viewmodel

import com.example.pertemuan12.model.Mahasiswa

sealed class DetailUiState{
    data class Success(val mahasiswa: Mahasiswa) : DetailUiState()
    object Error : DetailUiState()
    object Loading : DetailUiState()
}

