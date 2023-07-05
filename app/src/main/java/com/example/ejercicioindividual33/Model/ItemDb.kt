package com.example.ejercicioindividual33.Model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Item:: class], version = 1)
abstract class ItemDb: RoomDatabase() {

    abstract fun getItemDao(): ItemDao

    companion object{
        @Volatile
        private var INSTANCE: ItemDb? = null

        fun getDatabase(context: Context):ItemDb{
            val tempInstance = INSTANCE

            if (tempInstance!=null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ItemDb::class.java,
                    "ItemEntity"
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}