package com.example.notist.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notist.data.entity.SampleEntity

@Dao
interface SampleDao {
    @Query("SELECT * FROM SampleData")
    fun getAllData(): LiveData<List<SampleEntity>>

    @Query("SELECT * FROM sampleData WHERE id = id")
    fun getById(id: Int): SampleEntity

    @Insert
    suspend fun insert(item: List<SampleEntity>)

    @Update
    suspend fun update(item:SampleEntity
    )
    @Delete
    suspend fun delete(item:SampleEntity
    )
    @Query("DELETE FROM sampledata")
    suspend fun deleteAllRecord()
}