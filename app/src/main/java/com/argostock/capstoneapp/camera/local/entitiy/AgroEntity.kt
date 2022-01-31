package com.argostock.capstoneapp.camera.local.entitiy

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "agrostock_result_tabel")
data class AgroEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "file")
    val file: String,
    @ColumnInfo(name = "result")
    val result: String? = null,
    @ColumnInfo(name = "image")
    var image: String? = null,
    //agro entity
)