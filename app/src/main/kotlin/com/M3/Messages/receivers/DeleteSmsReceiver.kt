package com.M3.Messages.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import org.fossify.commons.extensions.notificationManager
import org.fossify.commons.helpers.ensureBackgroundThread
import com.M3.Messages.extensions.deleteMessage
import com.M3.Messages.extensions.updateLastConversationMessage
import com.M3.Messages.helpers.IS_MMS
import com.M3.Messages.helpers.MESSAGE_ID
import com.M3.Messages.helpers.THREAD_ID
import com.M3.Messages.helpers.refreshConversations
import com.M3.Messages.helpers.refreshMessages

class DeleteSmsReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val threadId = intent.getLongExtra(THREAD_ID, 0L)
        val messageId = intent.getLongExtra(MESSAGE_ID, 0L)
        val isMms = intent.getBooleanExtra(IS_MMS, false)
        context.notificationManager.cancel(threadId.hashCode())
        ensureBackgroundThread {
            context.deleteMessage(messageId, isMms)
            context.updateLastConversationMessage(threadId)
            refreshMessages()
            refreshConversations()
        }
    }
}
