package com.M3.Messages.interfaces

import androidx.room.Dao
import androidx.room.Query
import com.M3.Messages.models.Attachment

@Dao
interface AttachmentsDao {
    @Query("SELECT * FROM attachments")
    fun getAll(): List<Attachment>
}
