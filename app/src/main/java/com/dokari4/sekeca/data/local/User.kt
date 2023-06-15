package com.dokari4.sekeca.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "nama")
    val nama: String,
    @ColumnInfo(name = "kelas")
    val kelas: String,
    @ColumnInfo(name = "sekolah")
    val sekolah: String,
    @ColumnInfo(name = "kabupatenAtauKota")
    val kabupatenAtauKota: String,
    @ColumnInfo(name = "provinsi")
    val provinsi: String,
    @ColumnInfo(name = "scoreTotal")
    val scoreTotal: Double? = null
)
