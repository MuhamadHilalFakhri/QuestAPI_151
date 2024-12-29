package com.example.pertemuan12.ui.home.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.view.KeyEventDispatcher.Component
import com.example.pertemuan12.navigation.DestinasiNavigasi
import androidx.compose.ui.Modifier

object DestinasiDetail : DestinasiNavigasi{
    override val route = "detail"
    const val NIM = "nim"
    val routeWithArg = "$route/{$NIM}"
    override  val titleRes = "Detail Mhs"
}

@Composable
fun ComponentDetailMhs(
    judul: String,
    isinya: String
){
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "$judul :",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )

        Text(
            text =  isinya,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}