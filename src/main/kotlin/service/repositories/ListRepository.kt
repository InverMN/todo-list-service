package service.repositories

import org.jetbrains.exposed.sql.transactions.transaction
import service.models.List
import service.models.ListEntity

object ListRepository: Repository<List> {
    override fun findAll(): Iterable<List> = transaction {
        ListEntity.all().map(ListEntity::toList)
    }

    override fun findOneById(id: Int): List? = transaction {
        ListEntity.findById(id)?.toList()
    }

    override fun deleteOneById(id: Int) = transaction {
        ListEntity[id].delete()
    }

    override fun insertOne(model: List): Unit = transaction {
        ListEntity.new {
            this.author = model.author
            this.name = model.name
        }
    }

    override fun updateOne(model: List) = transaction {
        val list = ListEntity.findById(model.id)
        list?.name = model.name
        list?.author = model.author
    }
}