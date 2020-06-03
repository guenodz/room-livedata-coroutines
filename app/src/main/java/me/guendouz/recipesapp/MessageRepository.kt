package me.guendouz.recipesapp

import androidx.lifecycle.LiveData
import me.guendouz.recipesapp.dao.MessageDAO
import me.guendouz.recipesapp.entity.Message

class MessageRepository(private val messageDao: MessageDAO) {

    val allMessages: LiveData<List<Message>> = messageDao.getAllMessages()

    suspend fun insert(msg: Message) = messageDao.insert(msg)

    suspend fun deleteAll() = messageDao.deleteAll()
}