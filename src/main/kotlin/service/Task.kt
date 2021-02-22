package service

import java.time.ZonedDateTime
import java.util.*

data class Task(
        var title: String,
        val id: String,
        var dueDate: ZonedDateTime,
        var priority: Int,
        var isDone: Boolean,
        var description: String
        )