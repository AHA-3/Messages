package com.M3.Messages.interfaces

import androidx.room.Dao
import androidx.room.Query
import com.M3.Messages.models.MessageAttachment

@Dao
interface MessageAttachmentsDao {
    @Query("SELECT * FROM message_attachments")
    fun getAll(): List<MessageAttachment>
}
