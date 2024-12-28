package com.example.pertemuan12

import android.app.Application
import com.example.pertemuan12.ui.AppContainer
import com.example.pertemuan12.ui.MahasiswaContainer

class MahasiswaApplications: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = MahasiswaContainer()
    }
}
