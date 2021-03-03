package service.models

import org.jetbrains.exposed.dao.*


object Lists : IntIdTable() {
    val name = varchar("name", 100)
    val author = varchar("author", 100)
}

class ListEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ListEntity>(Lists)
    var name by Lists.name
    var author by Lists.author

    fun toList() = List(id.value, name, author)
}

data class List(val id: Int, val name: String, val author: String)