package com.slemenceu.insuflow.insuflow.ui.home

import kotlinx.serialization.Serializable


@Serializable
data class BleQrData(
    val deviceName: String ,
    val serviceUUID: String ,
    val characteristics: String ,
)