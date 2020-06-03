package me.guendouz.recipesapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import me.guendouz.recipesapp.entity.Message

@Dao
interface MessageDAO {

    @Query("SELECT * FROM Message ORDER BY text ASC")
    fun getAllMessages(): LiveData<List<Message>>

    @Query("SELECT * FROM Message WHERE id= :identifier")
    fun getMessageByID(identifier: Int): Message

    @Insert
    suspend fun insert(message: Message)

    @Query("DELETE FROM Message")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteMessage(msg: Message)

}