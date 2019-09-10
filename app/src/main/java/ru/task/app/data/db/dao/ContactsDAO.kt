package ru.task.app.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import ru.task.app.data.db.entity.Contact

@Dao
interface ContactsDAO {

    @Query("SELECT * FROM contact")
    fun getAll(): List<Contact>

    @Transaction
    fun clearAndInsert(contacts: List<Contact>) {
        nukeTable()
        insertAll(contacts)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(contacts: List<Contact>)

    @Delete
    fun delete(contact: Contact)

    @Query("DELETE FROM contact")
    fun nukeTable()
}