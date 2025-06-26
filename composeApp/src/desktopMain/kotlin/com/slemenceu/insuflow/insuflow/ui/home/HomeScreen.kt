package com.slemenceu.insuflow.insuflow.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.slemenceu.insuflow.insuflow.data.util.QrCodeGenerator
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.awt.font.ImageGraphicAttribute


@Composable
fun HomeScreen() {
    val bleQrData = BleQrData(
        deviceName = "BLE_Insulin_Pump",
        serviceUUID = "12345678-1234-5678-1234-56789abcdef0",
        characteristics = "abcdefab-1234-5678-9abc-def012345678",
    )
    val jsonString = Json.encodeToString(bleQrData)
    val qrImage = remember { QrCodeGenerator.generateQrCodeImage(jsonString, 300) }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                bitmap = qrImage,
                contentDescription = "Qrcode"
            )
            Text(
                text = "Scan here",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}