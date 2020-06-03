package me.guendouz.recipesapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.guendouz.recipesapp.database.MessagesDatabase
import me.guendouz.recipesapp.entity.Message

class MessageViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: MessageRepository
    val allMessages: LiveData<List<Message>>

    init {
        val messageDao = MessagesDatabase.getDatabase(application).messageDao()
        repository = MessageRepository(messageDao)
        allMessages = repository.allMessages
    }

    fun insert(msg: Message) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(msg)
    }

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }
}