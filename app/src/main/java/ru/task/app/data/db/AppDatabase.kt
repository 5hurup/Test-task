package ru.task.app.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.task.app.data.db.dao.ContactsDAO
import ru.task.app.data.db.entity.Contact

@Database(entities = [Contact::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun contactsDao(): ContactsDAO
}