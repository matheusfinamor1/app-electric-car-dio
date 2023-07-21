package com.example.eletriccarapp.data.local

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import android.util.Log
import com.example.eletriccarapp.data.local.CarsContract.CarEntry.COLUMN_NAME_BATERIA
import com.example.eletriccarapp.data.local.CarsContract.CarEntry.COLUMN_NAME_ID
import com.example.eletriccarapp.data.local.CarsContract.CarEntry.COLUMN_NAME_POTENCIA
import com.example.eletriccarapp.data.local.CarsContract.CarEntry.COLUMN_NAME_PRECO
import com.example.eletriccarapp.data.local.CarsContract.CarEntry.COLUMN_NAME_RECARGA
import com.example.eletriccarapp.data.local.CarsContract.CarEntry.COLUMN_NAME_URL_PHOTO
import com.example.eletriccarapp.domain.Car

class CarRepository(private val context: Context) {

    private fun saveOnDatabase(car: Car): Boolean {

        var isSaved = false

        try {
            val dbHelper = CarsDbHelper(context)
            val db = dbHelper.writableDatabase

            val values = ContentValues().apply {
                put(COLUMN_NAME_ID, car.id)
                put(COLUMN_NAME_PRECO, car.preco)
                put(COLUMN_NAME_BATERIA, car.bateria)
                put(COLUMN_NAME_POTENCIA, car.potencia)
                put(COLUMN_NAME_RECARGA, car.recarga)
                put(COLUMN_NAME_URL_PHOTO, car.urlPhoto)
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

    private fun findCarById(id: Int): Car {
        val dbHelper = CarsDbHelper(context)
        val db = dbHelper.readableDatabase

        /**
         * Listagem das colunas a serem exibidas no resultado da Query
         */
        val columns = arrayOf(
            BaseColumns._ID,
            COLUMN_NAME_ID,
            COLUMN_NAME_PRECO,
            COLUMN_NAME_BATERIA,
            COLUMN_NAME_POTENCIA,
            COLUMN_NAME_RECARGA,
            COLUMN_NAME_URL_PHOTO
        )

        val filter = "$COLUMN_NAME_ID = ?"

        val filterValues = arrayOf(id.toString())

        val cursor = db.query(
            CarsContract.CarEntry.TABLE_NAME,  // Nome da tabela
            columns, // Colunas a serem exibidas
            filter,  // where (fitro)
            filterValues, // valor do where, substituindo o parametro "?"
            null,
            null,
            null
        )

        var itemId: Long =  0
        var itemPreco = ""
        var itemBateria = ""
        var itemPotencia = ""
        var itemRecarga = ""
        var itemUrlPhoto = ""
        with(cursor) {
            while (moveToNext()) {
                 itemId = getLong(getColumnIndexOrThrow(COLUMN_NAME_ID))
                 itemPreco = getString(getColumnIndexOrThrow(COLUMN_NAME_PRECO))
                 itemBateria = getString(getColumnIndexOrThrow(COLUMN_NAME_BATERIA))
                 itemPotencia = getString(getColumnIndexOrThrow(COLUMN_NAME_POTENCIA))
                 itemRecarga = getString(getColumnIndexOrThrow(COLUMN_NAME_RECARGA))
                 itemUrlPhoto = getString(getColumnIndexOrThrow(COLUMN_NAME_URL_PHOTO))
            }
        }
        cursor.close()
        return Car(
            id = itemId.toInt(),
            preco = itemPreco,
            bateria = itemBateria,
            potencia = itemPotencia,
            recarga = itemRecarga,
            urlPhoto = itemUrlPhoto,
            isFavorite = true
        )
    }

    fun saveIfNotExist(carItem: Car){
        val car = findCarById(carItem.id)

        if(car.id == ID_WHEN_NO_CAR){
            saveOnDatabase(carItem)
        }

    }

    fun getAll(): List<Car>{
        val dbHelper = CarsDbHelper(context)
        val db = dbHelper.readableDatabase

        /**
         * Listagem das colunas a serem exibidas no resultado da Query
         */
        val columns = arrayOf(
            BaseColumns._ID,
            COLUMN_NAME_ID,
            COLUMN_NAME_PRECO,
            COLUMN_NAME_BATERIA,
            COLUMN_NAME_POTENCIA,
            COLUMN_NAME_RECARGA,
            COLUMN_NAME_URL_PHOTO
        )

        val cursor = db.query(
            CarsContract.CarEntry.TABLE_NAME,  // Nome da tabela
            columns, // Colunas a serem exibidas
            null,  // where (fitro)
            null, // valor do where, substituindo o parametro "?"
            null,
            null,
            null
        )

        val cars = mutableListOf<Car>()
        with(cursor) {
            while (moveToNext()) {
                val itemId = getLong(getColumnIndexOrThrow(COLUMN_NAME_ID))
                val itemPreco = getString(getColumnIndexOrThrow(COLUMN_NAME_PRECO))
                val itemBateria = getString(getColumnIndexOrThrow(COLUMN_NAME_BATERIA))
                val itemPotencia = getString(getColumnIndexOrThrow(COLUMN_NAME_POTENCIA))
                val itemRecarga = getString(getColumnIndexOrThrow(COLUMN_NAME_RECARGA))
                val itemUrlPhoto = getString(getColumnIndexOrThrow(COLUMN_NAME_URL_PHOTO))

                cars.add(
                    Car(
                        id = itemId.toInt(),
                        preco = itemPreco,
                        bateria = itemBateria,
                        potencia = itemPotencia,
                        recarga = itemRecarga,
                        urlPhoto = itemUrlPhoto,
                        isFavorite = true
                    )
                )
            }
        }
        cursor.close()
        return cars
    }

    companion object{
        const val ID_WHEN_NO_CAR = 0
    }
}