package com.example.ejercicioindividual33.Model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: Item)

    @Update
    suspend fun updateItem(item: Item)

    @Delete
    suspend fun deleteItem(item: Item)

    @Query("DELETE FROM item_table")
    fun deleteAll()

    @Query("DELETE FROM item_table WHERE id = :itemId")
    fun deleteItemById(itemId:Int)

    @Query("SELECT * FROM item_table")
    fun getAllItems(): LiveData<List<Item>>

    @Query("SELECT * FROM item_table WHERE id = :itemId")
    fun getItemById(itemId: Int): LiveData<Item>

}