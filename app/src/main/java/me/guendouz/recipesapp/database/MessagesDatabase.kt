package me.guendouz.recipesapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import me.guendouz.recipesapp.dao.MessageDAO
import me.guendouz.recipesapp.entity.Message

@Database(entities = arrayOf(Message::class), version = 1)
abstract class MessagesDatabase : RoomDatabase() {

    abstract fun messageDao(): MessageDAO

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: MessagesDatabase? = null

        fun getDatabase(context: Context): MessagesDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MessagesDatabase::class.java,
                    "messages_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}