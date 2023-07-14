package com.example.eletriccarapp.data.local

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.example.eletriccarapp.domain.Car

class CarRepository(private val context: Context) {

    fun saveOnDatabase(car: Car): Boolean {

        var isSaved = false

        try {
            val dbHelper = CarsDbHelper(context)
            val db = dbHelper.writableDatabase

            val values = ContentValues().apply {
                put(CarsContract.CarEntry.COLUMN_NAME_PRECO, car.preco)
                put(CarsContract.CarEntry.COLUMN_NAME_BATERIA, car.bateria)
                put(CarsContract.CarEntry.COLUMN_NAME_POTENCIA, car.potencia)
                put(CarsContract.CarEntry.COLUMN_NAME_RECARGA, car.recarga)
                put(CarsContract.CarEntry.COLUMN_NAME_URL_PHOTO, car.urlPhoto)
            }

            val insertDb = db?.insert(CarsContract.CarEntry.TABLE_NAME, null, values)

            if (insertDb != null)
                isSaved = true

        } catch (ex: Exception) {
            ex.message?.let {
                Log.e("Error ->", it)
            }
        }
        return isSaved
    }
}