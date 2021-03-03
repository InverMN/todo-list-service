package service.models

import org.jetbrains.exposed.dao.*
import org.joda.time.DateTime

object Tasks : IntIdTable() {
    val title = varchar("title", 100)
    val priority = integer("priority")
    val isDone = bool("isDone")
    val description = varchar("description", 256)
    val list = reference("list", Lists).default(EntityID(1, Lists))
}

class TaskEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<TaskEntity>(Tasks)

    var title by Tasks.title
    var priority by Tasks.priority
    var isDone by Tasks.isDone
    var description by Tasks.description
    var list by Tasks.list

    fun toTask() = Task(id.value, title, priority, isDone, description, list.value)
}

data class Task(val id: Int, val title: String, val priority: Int, val isDone: Boolean, val description: String, val list: Int)