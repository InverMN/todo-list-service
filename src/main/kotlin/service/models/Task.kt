package service.models

import org.jetbrains.exposed.dao.*

//data class Task(var title: String, val id: String, var dueDate: ZonedDateTime, var priority: Int, var isDone: Boolean, var description: String)

object Tasks : IntIdTable() {
    val title = varchar("title", 100)
    val dueTime = datetime("dueTime")
    val priority = integer("priority")
    val isDone = bool("isDone")
    val description = varchar("description", 256)
    val list = reference("list", Lists)
}

class Task(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Task>(Tasks)

    var title by Tasks.title
    var dueTime by Tasks.dueTime
    var priority by Tasks.priority
    var isDone by Tasks.isDone
    var description by Tasks.description
}