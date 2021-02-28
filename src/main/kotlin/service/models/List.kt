package service.models

import org.jetbrains.exposed.dao.*

//data class List(val id: String, var name: String, val author: String, var tasks: Array<Task>)

object Lists : IntIdTable() {
    val name = varchar("name", 100)
    val author = varchar("author", 100)
}

class List(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<List>(Lists)
    var name by Lists.name
    var author by Lists.author
}