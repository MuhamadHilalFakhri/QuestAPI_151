package com.example.pertemuan12.ui

import com.example.pertemuan12.repository.MahasiswaRepository
import com.example.pertemuan12.service.MahasiswaService
import kotlinx.serialization.json.Json
import retrofit2.Retrofit

interface AppContainer {
    val mahasiswaRepository: MahasiswaRepository
}
